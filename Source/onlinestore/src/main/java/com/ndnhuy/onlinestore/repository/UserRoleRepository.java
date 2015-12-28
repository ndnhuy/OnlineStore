package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.domain.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	UserRole findByRoleNameIgnoreCase(String roleName);
}
