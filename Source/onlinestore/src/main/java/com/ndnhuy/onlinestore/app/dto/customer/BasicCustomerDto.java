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
	@Size(min = 0, max = 50, message = "'email' " + "{javax.validation.constraints.Size.message}")
	@Email
	private String email;
	
	@Mapping("customerDetail.address.street")
	@Size(min = 0, max = 100, message = "'street' " + "{javax.validation.constraints.Size.message}")
	private String street;
	
	@Mapping("customerDetail.address.city")
	@Size(min = 0, max = 30, message = "'city' " + "{javax.validation.constraints.Size.message}")
	private String city;
	
	@Mapping("userRole.roleName")
	private String role;

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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
