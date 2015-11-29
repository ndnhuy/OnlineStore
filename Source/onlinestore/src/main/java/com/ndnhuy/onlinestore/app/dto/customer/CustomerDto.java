package com.ndnhuy.onlinestore.app.dto.customer;

import java.io.Serializable;
import java.util.Collection;

import org.dozer.Mapping;

import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.entity.Purchase;

public class CustomerDto implements Serializable {
	
	@Mapping("id")
	private Integer id;
	
	@Mapping("name")
	private String name;
	
	@Mapping("email")
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
