package com.owen.wms.lekani.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCategoryModelListResp extends LKNBasicResp {
	
	@JsonProperty("RetValue")  
	private List<CategoryModel> retValue;

	public List<CategoryModel> getRetValue() {
		return retValue;
	}

	public void setRetValue(List<CategoryModel> retValue) {
		this.retValue = retValue;
	}
	
}
