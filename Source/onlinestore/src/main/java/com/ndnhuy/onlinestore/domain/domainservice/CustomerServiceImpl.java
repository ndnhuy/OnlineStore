package com.ndnhuy.onlinestore.domain.domainservice;

import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.domain.entity.Customer;
import com.ndnhuy.onlinestore.domain.entity.Purchase;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, CustomerDto, Integer> implements CustomerService {
	
	@Override
	public CustomerDto add(CustomerDto dto) {
		
		validator.checkIfFieldValueIsUniqueInRepo(dto, Customer.class);

		Customer customer = mapper.map(dto, Customer.class);
		
		if (customer.getPurchases() != null) {
			for (Purchase p : customer.getPurchases()) {
				p.setCustomer(customer);
			}
		}
		
		validator.validate(customer);
		
		repository.saveAndFlush(customer);
		
		return mapper.map(customer, CustomerDto.class);
	}

	
}
