package com.owen.wms.lekani.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBrandListResp extends LKNBasicResp {
	
	@JsonProperty("RetValue")  
	private List<BrandModel> retValue;

	public List<BrandModel> getRetValue() {
		return retValue;
	}

	public void setRetValue(List<BrandModel> retValue) {
		this.retValue = retValue;
	}
	
}
