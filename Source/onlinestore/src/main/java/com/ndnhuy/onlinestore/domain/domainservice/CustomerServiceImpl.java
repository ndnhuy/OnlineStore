package com.ndnhuy.onlinestore.domain.domainservice;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.domain.entity.Customer;
import com.ndnhuy.onlinestore.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Customer getCustomer(Integer id) {
		return customerRepo.findOne(id);
	}

	@Override
	public Collection<Customer> getCustomers() {
		return customerRepo.findAll();
	}
	
	
}
