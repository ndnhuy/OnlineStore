package com.ndnhuy.onlinestore.app.dto;

import org.dozer.Mapping;

public class PurchaseDto {
	
	@Mapping("id")
	private Integer id;
	
	@Mapping("customer.id")
	private Integer customerId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
}
