package com.ndnhuy.onlinestore.app.service.customer;

import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.app.dto.customer.BasicCustomerDto;
import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.commonutils.Constant;
import com.ndnhuy.onlinestore.commonutils.CurrentUser;
import com.ndnhuy.onlinestore.commonutils.GmailService;
import com.ndnhuy.onlinestore.commonutils.SendMail;
import com.ndnhuy.onlinestore.domain.domainservice.customer.CustomerService;

@RestController
@RequestMapping("/account")
public class CustomerUserController {
	
	private final static Logger logger = Logger.getLogger(CustomerUserController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private CurrentUser currentUser;
	
	@RequestMapping(method=RequestMethod.GET)
	@Secured({Constant.ROLE_USER, Constant.ROLE_ADMIN})
	public RestSuccess getAccount() {
		logger.info("Get account of user " + currentUser.getUsername());
		
		BasicCustomerDto dto = customerService.findBasicCustomerInfo(currentUser.getCustomerId());
		
		return new RestSuccess(HttpStatus.OK.value(), dto, null);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public RestSuccess register(@RequestBody CustomerDto customerDto) throws MessagingException, IOException {
		logger.info("Register account " + ToStringBuilder.reflectionToString(customerDto));
		
		customerDto.setId(null);
		
		// Add user info to DB with disable
		customerDto = customerService.add(customerDto);
		
		// Create token, save to DB (user, token)
		String token = UUID.randomUUID().toString();
		customerService.createVerificationToken(customerDto.getId(), token);
		
		// Send email with token
		
		String message = "Confirm Registration";
//		String confirmLink = ServletUriComponentsBuilder.fromCurrentContextPath()
//				.path("/registryConfirm")
//				.queryParam("token", token)
//				.toUriString();
		
		SendMail.sendMessage(GmailService.getGmailService(), "me",
				SendMail.createEmail(customerDto.getEmail(), 
						"ndnhuy2504@gmail.com", 
						"Registration Confirmation", 
						message + " " + token));
		 
		
		return new RestSuccess(HttpStatus.CREATED.value(), customerDto, 
									"You can view the account info at " 
											+ ServletUriComponentsBuilder.fromCurrentContextPath().path("/account").toUriString());
		
	}
	
	@RequestMapping(value="/resetPassword", method=RequestMethod.POST)
	public RestSuccess resetPassword(@RequestParam("email") String email) throws MessagingException, IOException {
		String token = UUID.randomUUID().toString();
		
		customerService.createPasswordResetToken(email, token);
		
		// Send email with token to given email
		String message = "Password Reset Token";
		
		SendMail.sendMessage(GmailService.getGmailService(), "me",
				SendMail.createEmail(email, 
						"ndnhuy2504@gmail.com", 
						"Password Reset Confirmation", 
						message + " " + token));
		
		return new RestSuccess(HttpStatus.OK.value(), null, "The password reset token has been sent to your email " + email);
	}
}
