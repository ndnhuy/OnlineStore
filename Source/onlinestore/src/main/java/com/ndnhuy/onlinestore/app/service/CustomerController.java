package com.ndnhuy.onlinestore.app.service;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.CustomerDto;
import com.ndnhuy.onlinestore.domain.domainservice.CustomerService;

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
	public CustomerDto updatePartialCustomer(@PathVariable("id") Integer id, @RequestParam("fields") String[] fields ,@RequestBody CustomerDto dto) {
		String logFields = "";
		for (String field : fields) {
			logFields += field;
			logFields += ",";
		}
		logFields = StringUtils.chop(logFields.trim());

		logger.info("Partially update customer " + id + " [" + logFields + "] with given DTO [" + ToStringBuilder.reflectionToString(dto) + "]");
		
		
		
		return null;
	}
	
}
