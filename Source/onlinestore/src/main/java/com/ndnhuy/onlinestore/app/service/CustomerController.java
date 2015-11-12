package com.ndnhuy.onlinestore.appservice;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.domain.domainservice.CustomerService;
import com.ndnhuy.onlinestore.domain.entity.Customer;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public Collection<Customer> getCustomers() {
		return customerService.getCustomers();
	}
}
