package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCategoryModelResp extends LKNBasicResp {
	
	@JsonProperty("RetValue")  
	private CategoryModel retValue;

	public CategoryModel getRetValue() {
		return retValue;
	}

	public void setRetValue(CategoryModel retValue) {
		this.retValue = retValue;
	}

	
}
