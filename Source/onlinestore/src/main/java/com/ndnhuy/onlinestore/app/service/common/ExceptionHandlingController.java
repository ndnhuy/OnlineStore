package com.ndnhuy.onlinestore.app.service.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.common.RestError;
import com.ndnhuy.onlinestore.domain.exception.AppException;

@RestController
@ControllerAdvice
public class ExceptionHandlingController {
	
	private static final Logger logger = Logger.getLogger(ExceptionHandlingController.class);
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public RestError handleException(RuntimeException e) {
		
		logger.error(e.getMessage(), e);
		
		RestError error = new RestError();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setDeveloperMessage(e.getMessage());
		error.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		return error;
	}
	
	@ExceptionHandler(AppException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public RestError handleAppException(AppException e) {
		
		logger.error(e.getMessage(), e);
		
		RestError error = new RestError();
		error.setCode(e.getStatus());
		error.setDeveloperMessage(e.getDeveloperMessage());
		error.setMessage(e.getUserMessage());
		error.setStatus(e.getStatus());
		error.setError(ExceptionUtils.getRootCauseMessage(e.getCause()));
		return error;
	}
}
