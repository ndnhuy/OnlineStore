package com.ndnhuy.onlinestore.app.service.account;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.domain.domainservice.customer.CustomerService;

@RestController
public class PasswordResetController {
	
	private static final Logger logger = Logger.getLogger(PasswordResetController.class);

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/passwordResetConfirm", method=RequestMethod.POST)
	public RestSuccess confirmPasswordReset(@RequestParam("token") String token, @RequestParam("new_password") String newPassword) {
		
		return new RestSuccess(HttpStatus.OK.value(), customerService.confirmPasswordReset(token, newPassword), "You have changed your password successfully");
	}
	
}
