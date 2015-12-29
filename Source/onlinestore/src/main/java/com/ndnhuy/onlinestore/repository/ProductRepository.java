package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.domain.entity.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
