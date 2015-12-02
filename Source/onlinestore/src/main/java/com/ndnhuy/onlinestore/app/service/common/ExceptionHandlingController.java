package com.ndnhuy.onlinestore.app.service.common;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.common.RestError;
import com.ndnhuy.onlinestore.commonutils.MyError;
import com.ndnhuy.onlinestore.domain.common.AppException;

@RestController
@ControllerAdvice
public class ExceptionHandlingController {
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public RestError handleException(RuntimeException e) {
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
		
		RestError error = new RestError();
		error.setCode(e.getStatus());
		error.setDeveloperMessage(e.getDeveloperMessage());
		error.setMessage(e.getUserMessage());
		error.setStatus(e.getStatus());
		error.setError(ExceptionUtils.getRootCauseMessage(e.getCause()));
		return error;
	}
}
