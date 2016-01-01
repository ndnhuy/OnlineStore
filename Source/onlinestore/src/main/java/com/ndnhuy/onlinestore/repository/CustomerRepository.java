package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.domain.entity.customer.Customer;
import com.ndnhuy.onlinestore.domain.entity.purchase.Purchase;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByUsername(String username);
	Customer findByUsernameAndEnabled(String username, Boolean enabled);
	Customer findByEmail(String email);
}
