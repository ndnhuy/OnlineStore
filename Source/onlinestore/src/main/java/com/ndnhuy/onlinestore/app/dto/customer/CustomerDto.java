package com.ndnhuy.onlinestore.app.dto.customer;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.constraints.Size;

import org.dozer.Mapping;
import org.hibernate.validator.constraints.Email;

import com.ndnhuy.onlinestore.annotation.UniqueInRepository;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;


public class CustomerDto implements Serializable {
	
	@Mapping("id")
	private Integer id;
	
	@Mapping("username")
	@UniqueInRepository
	@Size(min = 0, max = 10, message = "'name' " + "{javax.validation.constraints.Size.message}")
	private String username;
	
	@Mapping("email")
	@UniqueInRepository
	@Size(min = 0, max = 20, message = "'email' " + "{javax.validation.constraints.Size.message}")
	@Email
	private String email;
	
	@Mapping("password")
	private String password;
	
	@Mapping("purchases")
	private Collection<PurchaseDto> purchases;
	
	public Collection<PurchaseDto> getPurchases() {
		return purchases;
	}
	public void setPurchases(Collection<PurchaseDto> purchases) {
		this.purchases = purchases;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
