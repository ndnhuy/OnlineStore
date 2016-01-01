package com.ndnhuy.onlinestore.app.dto.customer;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.constraints.Size;

import org.dozer.Mapping;
import org.hibernate.validator.constraints.Email;

import com.ndnhuy.onlinestore.annotation.UniqueInRepository;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;


public class CustomerDto extends BasicCustomerDto implements Serializable {
	@Mapping("id")
	private Integer id;
	
	@Mapping("password")
	private String password;
	
	@Mapping("enabled")
	private Boolean enabled;
	
	
//	@Mapping("purchases")
//	private Collection<PurchaseDto> purchases;
	
//	public Collection<PurchaseDto> getPurchases() {
//		return purchases;
//	}
//	public void setPurchases(Collection<PurchaseDto> purchases) {
//		this.purchases = purchases;
//	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
	
}
