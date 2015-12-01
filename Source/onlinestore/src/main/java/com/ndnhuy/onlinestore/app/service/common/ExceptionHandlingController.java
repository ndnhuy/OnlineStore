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
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public MyError handleException(RuntimeException e) {
		MyError error = new MyError();
		error.setCode(100);
		error.setMessage("TEST EX HANDLER");
		return error;
	}
	
	@ExceptionHandler(AppException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
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
