package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.domain.entity.purchase.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
