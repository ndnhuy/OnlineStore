package com.ndnhuy.onlinestore.commonutils;

import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
	Integer customerId;
	String username;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
