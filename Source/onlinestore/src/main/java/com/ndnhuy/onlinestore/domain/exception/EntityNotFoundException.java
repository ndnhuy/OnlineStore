package com.ndnhuy.onlinestore.domain.exception;

import org.springframework.http.HttpStatus;

import com.ndnhuy.onlinestore.commonutils.ApplicationContextProvider;

public class EntityNotFoundException extends AppException {

	
	public EntityNotFoundException(String entityName, String additionalUserMessage, String additionalDevMessage) {
		
		super(HttpStatus.NOT_FOUND.value(), 
				ApplicationContextProvider.getApplicationContext().getMessage("user.entity_not_found", new Object[] {entityName, additionalUserMessage}, null), 
				ApplicationContextProvider.getApplicationContext().getMessage("dev.entity_not_found", new Object[] {entityName, additionalDevMessage}, null));
	}
	
	public EntityNotFoundException(String entityName, String additionalUserMessage, String additionalDevMessage, Exception e) {
		super(HttpStatus.NOT_FOUND.value(), 
				ApplicationContextProvider.getApplicationContext().getMessage("user.entity_not_found", new Object[] {entityName, additionalUserMessage}, null), 
				ApplicationContextProvider.getApplicationContext().getMessage("dev.entity_not_found", new Object[] {entityName, additionalDevMessage}, null), 
				e);
	}
	
}
