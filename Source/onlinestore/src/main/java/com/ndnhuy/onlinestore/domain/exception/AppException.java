package com.ndnhuy.onlinestore.domain.exception;

public class AppException extends RuntimeException {
	protected Integer status;
	protected String userMessage;
	protected String developerMessage;
	protected Exception exception;
	
	public AppException(Integer status, String userMessage, String developerMessage) {
		super(developerMessage);
		this.status = status;
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
		this.exception = null;
	}
	
	public AppException(Integer status, String userMessage, String developerMessage, Exception e) {
		super(e);
		this.status = status;
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
		this.exception = e;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
}
