package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductModelListResp extends LKNBasicResp {

	@JsonProperty("RetValue")  
	private GetProductModelListPackage retValue;

	public GetProductModelListPackage getRetValue() {
		return retValue;
	}

	public void setRetValue(GetProductModelListPackage retValue) {
		this.retValue = retValue;
	}

}
