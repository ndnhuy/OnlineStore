package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.domain.entity.customer.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer>{
	VerificationToken findByToken(String token);
}
