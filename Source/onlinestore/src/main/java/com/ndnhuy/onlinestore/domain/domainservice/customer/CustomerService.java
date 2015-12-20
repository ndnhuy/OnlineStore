package com.ndnhuy.onlinestore.domain.domainservice.customer;


import java.util.Collection;

import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericService;
import com.ndnhuy.onlinestore.domain.entity.Customer;

/**
 * @author Huy Nguyen
 */
public interface CustomerService extends GenericService<Customer, CustomerDto, Integer> {
	Collection<PurchaseDto> findPurchasesOfCustomer(Integer customerId);
}
