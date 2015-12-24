package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.domain.entity.Customer;
import com.ndnhuy.onlinestore.domain.entity.Purchase;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer findByUsername(String username);
}
