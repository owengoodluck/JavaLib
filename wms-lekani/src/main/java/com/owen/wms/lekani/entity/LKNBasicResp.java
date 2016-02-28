package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LKNBasicResp {
	
	@JsonProperty("StatusCode")  
	private String statusCode;
	
	@JsonProperty("ErrorMessage")  
	private String errorMessage;
	
	@JsonProperty("RetValue")  
	private Object retValue;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getRetValue() {
		return retValue;
	}
	public void setRetValue(Object retValue) {
		this.retValue = retValue;
	}
	
	
}
