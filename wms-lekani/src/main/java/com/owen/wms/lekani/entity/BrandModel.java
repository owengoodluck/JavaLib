package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandModel {

	@JsonProperty("BrandID")  
	private String BrandID;
	@JsonProperty("BrandName")  
	private String BrandName;
	@JsonProperty("BrandEnName")  
	private String BrandEnName;
	
	public String getBrandID() {
		return BrandID;
	}
	public void setBrandID(String brandID) {
		BrandID = brandID;
	}
	public String getBrandName() {
		return BrandName;
	}
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}
	public String getBrandEnName() {
		return BrandEnName;
	}
	public void setBrandEnName(String brandEnName) {
		BrandEnName = brandEnName;
	}
	@Override
	public String toString() {
		return "BrandModel [" + (BrandID != null ? "BrandID=" + BrandID + ", " : "")
				+ (BrandName != null ? "BrandName=" + BrandName + ", " : "")
				+ (BrandEnName != null ? "BrandEnName=" + BrandEnName : "") + "]";
	}
	
	
}
