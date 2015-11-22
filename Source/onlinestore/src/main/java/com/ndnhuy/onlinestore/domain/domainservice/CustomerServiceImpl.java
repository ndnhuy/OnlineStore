package com.ndnhuy.onlinestore.domain.domainservice;

import java.util.Collection;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.CustomerDto;
import com.ndnhuy.onlinestore.domain.entity.Customer;
import com.ndnhuy.onlinestore.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Customer getCustomer(Integer id) {
		return customerRepo.findOne(id);
	}

	@Override
	public Collection<Customer> getAll() {
		return customerRepo.findAll();
	}

	@Override
	@Transactional
	public void add(CustomerDto addedCustomerDto) {
		customerRepo.save(mapper.map(addedCustomerDto, Customer.class));
	}
	
	
}
