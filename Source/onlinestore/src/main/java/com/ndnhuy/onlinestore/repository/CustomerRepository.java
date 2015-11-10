package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.domain.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
