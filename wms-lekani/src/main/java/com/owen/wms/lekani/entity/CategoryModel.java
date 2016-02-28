package com.owen.wms.lekani.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryModel {
	
	@JsonProperty("ParentID")
	private String parentID;
	
	@JsonProperty("CatID")
	private String catID;
	
	@JsonProperty("CatName")
	private String catName;
	
	@JsonProperty("CatEnName")
	private String catEnName;

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getCatID() {
		return catID;
	}

	public void setCatID(String catID) {
		this.catID = catID;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatEnName() {
		return catEnName;
	}

	public void setCatEnName(String catEnName) {
		this.catEnName = catEnName;
	}
	
	
}
