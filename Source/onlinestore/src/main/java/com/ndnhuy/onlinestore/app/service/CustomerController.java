package com.ndnhuy.onlinestore.app.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.CustomerDto;
import com.ndnhuy.onlinestore.domain.domainservice.CustomerService;
import com.ndnhuy.onlinestore.domain.entity.Customer;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public List<CustomerDto> getCustomers() {
		List<CustomerDto> customers = new ArrayList<CustomerDto>();
		for (Customer customer : customerService.getCustomers()) {	
			customers.add(mapper.map(customer, CustomerDto.class));
		}
		return customers;
	}
	
	@RequestMapping(value="/customers", method=RequestMethod.POST)
	public void addCustomer(@RequestBody CustomerDto dto) {
		customerService.add(dto);
	}
}
