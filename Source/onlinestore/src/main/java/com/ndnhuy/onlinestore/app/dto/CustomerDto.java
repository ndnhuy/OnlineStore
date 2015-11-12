package com.ndnhuy.onlinestore.app.dto;

import org.dozer.Mapping;

public class CustomerDto {
	
	@Mapping("id")
	private Integer id;
	
	@Mapping("name")
	private String name;
	
	@Mapping("email")
	private String email;
	
	@Mapping("password")
	private String password;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
