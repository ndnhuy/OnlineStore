package com.ndnhuy.onlinestore.app.service.customer;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.sync.Patch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.domain.domainservice.customer.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<CustomerDto> getCustomers() {
		logger.info("Find all customers");
		
		return customerService.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public CustomerDto addCustomer(@RequestBody CustomerDto dto) {
		logger.info("Add customer " + ToStringBuilder.reflectionToString(dto));
		return customerService.add(dto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public CustomerDto upateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerDto dto) {
		logger.info("Update customer " + ToStringBuilder.reflectionToString(dto));
		
		dto.setId(id);
		
		return customerService.udpate(dto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PATCH)
	public CustomerDto updatePartialCustomer(@PathVariable("id") Integer id, @RequestBody Patch patch) {
		logger.info("Partially update customer " + id + " s: " + ToStringBuilder.reflectionToString(patch));
		
		return customerService.updateChanges(id, patch);
	}
	
}
