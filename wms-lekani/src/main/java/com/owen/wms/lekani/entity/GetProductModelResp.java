package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductModelResp extends LKNBasicResp {
	
	@JsonProperty("RetValue")  
	private ProductModel retValue;

	public ProductModel getRetValue() {
		return retValue;
	}

	public void setRetValue(ProductModel retValue) {
		this.retValue = retValue;
	}

	
}
