package com.ndnhuy.onlinestore.app.dto.purchase;

import java.io.Serializable;

import org.dozer.Mapping;

public class PurchaseDto implements Serializable {
	
	@Mapping("id")
	private Integer id;
	
	
	private Integer customerId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Mapping("customer.id")
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
}
