package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBrandResp extends LKNBasicResp {
	
	@JsonProperty("RetValue")  
	private BrandModel retValue;

	public BrandModel getRetValue() {
		return retValue;
	}

	public void setRetValue(BrandModel retValue) {
		this.retValue = retValue;
	}

	
}
