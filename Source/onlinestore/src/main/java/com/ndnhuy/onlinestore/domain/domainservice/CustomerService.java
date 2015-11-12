package com.ndnhuy.onlinestore.domain.domainservice;

import java.util.Collection;

import com.ndnhuy.onlinestore.app.dto.CustomerDto;
import com.ndnhuy.onlinestore.domain.entity.Customer;

public interface CustomerService {
	Customer getCustomer(Integer id);
	Collection<Customer> getCustomers();
	void add(CustomerDto addedCustomerDto);
}
