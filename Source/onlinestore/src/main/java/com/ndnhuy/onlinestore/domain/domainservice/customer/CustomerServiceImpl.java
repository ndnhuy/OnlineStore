package com.ndnhuy.onlinestore.domain.domainservice.customer;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.commonutils.CurrentUser;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericServiceImpl;
import com.ndnhuy.onlinestore.domain.entity.Customer;
import com.ndnhuy.onlinestore.domain.entity.Purchase;
import com.ndnhuy.onlinestore.domain.exception.EntityNotFoundException;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, CustomerDto, Integer> implements CustomerService {
	
	private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);
	
	@Override
	public CustomerDto add(CustomerDto dto) {
		
		validator.validate(dto);
		
		Customer customer = mapper.map(dto, Customer.class);
		
		if (customer.getPurchases() != null) {
			for (Purchase p : customer.getPurchases()) {
				p.setCustomer(customer);
			}
		}
		
		validator.validate(customer);
		validator.checkIfFieldValueIsUniqueInRepo(dto, Customer.class);
		
		repository.saveAndFlush(customer);
		
		
		return mapper.map(customer, CustomerDto.class);
	}

	@Override
	public Collection<PurchaseDto> findPurchasesOfCustomer(Integer customerId) {
		if(logger.isDebugEnabled()) {
			logger.debug("Find all purchases of customer [id = " + customerId + "]");
		}
		
		Customer customer = repository.findOne(customerId);
		if (customer == null) {
			throw new EntityNotFoundException(Customer.class.getSimpleName(), "id = " + customerId, "id = " + customerId);
		}
		
		Collection<Purchase> purchases = customer.getPurchases();
		
		Collection<PurchaseDto> dtoPurchases = new ArrayList<PurchaseDto>();
		for (Purchase p : purchases) {
			dtoPurchases.add(mapper.map(p, PurchaseDto.class));
		}
		
		return dtoPurchases;
	}
	
	@Autowired
	CurrentUser currentUser;
	
	@Secured("ROLE_ADMIN")
	@Override
	public Collection<CustomerDto> findAll() {
		System.out.println("From CustomerServiceImpl, currentUser has id " + currentUser.getCustomerId());
		return super.findAll();
	}

}
