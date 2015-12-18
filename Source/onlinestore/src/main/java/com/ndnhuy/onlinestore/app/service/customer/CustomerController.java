package com.ndnhuy.onlinestore.app.service.customer;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.sync.Patch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.domain.domainservice.customer.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getCustomers() {
		logger.info("Find all customers");
		
		Collection<CustomerDto> customersDto = customerService.findAll();
		
		logger.info("Customers: ");
		for (CustomerDto c : customersDto) {
			logger.info("Id: " + c.getId() + ", name: " + c.getName() + ", email: " + c.getEmail());
		}
		
		return new RestSuccess(HttpStatus.OK.value(), customersDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public RestSuccess getCustomer(@PathVariable("id") Integer id) {
		logger.info("Get customer: id = " + id);
		
		CustomerDto customerDto = customerService.findOne(id);
		
		logger.info("Customer [id = " + customerDto.getId() + ", name = " + customerDto.getName() + ", email = " + customerDto.getEmail());
		
		return new RestSuccess(HttpStatus.OK.value(), customerDto, null);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public RestSuccess addCustomer(@RequestBody CustomerDto dto) {
		logger.info("Add customer[id: " + dto.getId() + ", name: " + dto.getName() + ", email: " + dto.getEmail());
		
		CustomerDto newlyCreatedCustomer = customerService.add(dto);
		
		return new RestSuccess(HttpStatus.CREATED.value(), newlyCreatedCustomer, "Location " + 
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newlyCreatedCustomer.getId()).toUriString());
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
