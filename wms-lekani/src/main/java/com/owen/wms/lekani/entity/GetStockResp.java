package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetStockResp extends LKNBasicResp {
	
	@JsonProperty("RetValue")  
	private Integer retValue;

	public Integer getRetValue() {
		return retValue;
	}

	public void setRetValue(Integer retValue) {
		this.retValue = retValue;
	}

	
}
