package com.ndnhuy.onlinestore.app.service.customer;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.app.dto.customer.BasicCustomerDto;
import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.commonutils.Constant;
import com.ndnhuy.onlinestore.commonutils.CurrentUser;
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
	public RestSuccess register(@RequestBody CustomerDto customerDto) {
		logger.info("Register account " + ToStringBuilder.reflectionToString(customerDto));
		
		customerDto.setId(null);
		
		return new RestSuccess(HttpStatus.CREATED.value(), customerService.add(customerDto), 
									"You can view the account info at " 
											+ ServletUriComponentsBuilder.fromCurrentContextPath().path("/account").toUriString());
		
	}
}
