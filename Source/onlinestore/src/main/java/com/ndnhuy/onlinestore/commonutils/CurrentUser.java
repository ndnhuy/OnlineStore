package com.ndnhuy.onlinestore.commonutils;

import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
	Integer customerId;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
}
