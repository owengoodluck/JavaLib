package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeModel {
	@JsonProperty("AttrID")  
	private String attrID;

	@JsonProperty("AttrName")  
	private String attrName;

	@JsonProperty("AttrValue")  
	private String attrValue;

	@JsonProperty("IsSKU")  
	private String isSKU;

	public String getAttrID() {
		return attrID;
	}

	public void setAttrID(String attrID) {
		this.attrID = attrID;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getIsSKU() {
		return isSKU;
	}

	public void setIsSKU(String isSKU) {
		this.isSKU = isSKU;
	}
	
	
}
