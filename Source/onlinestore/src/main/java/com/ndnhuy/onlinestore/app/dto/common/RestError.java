package com.ndnhuy.onlinestore.app.dto.common;

public class RestError {
	private Integer status;
	private Integer code;
	private String message;
	private String developerMessage;
	private Object error;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	public Object getError() {
		return error;
	}
	public void setError(Object error) {
		this.error = error;
	}

	
}
