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
public class RegistrationController {
	
	private static final Logger logger = Logger.getLogger(RegistrationController.class);

	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/registryConfirm", method=RequestMethod.GET)
	public RestSuccess confirmRegistration(@RequestParam("token") String token) {
		logger.info("Confirm Registration with token " + token);
		
		return new RestSuccess(HttpStatus.OK.value(), customerService.confirmRegistration(token), "The account is activated");
	}
}
