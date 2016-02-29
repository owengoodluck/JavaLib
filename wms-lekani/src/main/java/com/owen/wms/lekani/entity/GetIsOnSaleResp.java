package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetIsOnSaleResp extends LKNBasicResp {
	
	@JsonProperty("RetValue")  
	private String retValue;//0为下架，1为上架

	public String getRetValue() {
		return retValue;
	}

	public void setRetValue(String retValue) {
		this.retValue = retValue;
	}

	
}
