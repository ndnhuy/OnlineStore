package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.domain.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{

}
