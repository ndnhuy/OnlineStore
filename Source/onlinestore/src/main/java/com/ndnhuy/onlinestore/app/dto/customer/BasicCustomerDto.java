package com.ndnhuy.onlinestore.app.dto.customer;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.dozer.Mapping;
import org.hibernate.validator.constraints.Email;

import com.ndnhuy.onlinestore.annotation.UniqueInRepository;

public class BasicCustomerDto implements Serializable {
	
	
	@Mapping("username")
	@UniqueInRepository
	@Size(min = 0, max = 10, message = "'name' " + "{javax.validation.constraints.Size.message}")
	private String username;
	
	@Mapping("email")
	@UniqueInRepository
	@Size(min = 0, max = 20, message = "'email' " + "{javax.validation.constraints.Size.message}")
	@Email
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
