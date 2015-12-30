package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.domain.entity.purchase.PurchaseStatus;

public interface PurchaseStatusRepository extends JpaRepository<PurchaseStatus, Integer> {

}
