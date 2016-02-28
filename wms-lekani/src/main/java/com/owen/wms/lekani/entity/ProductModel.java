package com.owen.wms.lekani.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductModel {

	@JsonProperty("ProductID")  
	private String productID;
	
	@JsonProperty("CatID")  
	private String catID;
	
	@JsonProperty("CatName")  
	private String catName;
	
	@JsonProperty("BrandID")  
	private String brandID;
	
	@JsonProperty("BrandName")  
	private String brandName;

	@JsonProperty("SKU")
	private String SKU;

	@JsonProperty("BarCode")
	private String barCode;

	@JsonProperty("Price")  
	private String price;

	@JsonProperty("Stock")  
	private String stock;

	@JsonProperty("IsPackage")  
	private String isPackage;

	@JsonProperty("Weight")  
	private String weight;

	@JsonProperty("GrossWeight")  
	private String grossWeight;

	@JsonProperty("PackageHeight")  
	private String packageHeight;

	@JsonProperty("PackageLength")  
	private String packageLength;

	@JsonProperty("PackageWidth")  
	private String packageWidth;

	@JsonProperty("ShopTime")  
	private String shopTime;

	@JsonProperty("KeyWords")  
	private String keyWords;

	@JsonProperty("Images")  
	private String images;

	@JsonProperty("Description")  
	private String description;

	@JsonProperty("Name")  
	private String name;
	
	// if use List need to update the json string:
	//1.jsonString = jsonString.replaceAll("\\\\", "");
	//2.remove quote around [ ] for "Attributes"
	//"Attributes":"[{"AttrID":"200000784","AttrName":"Shapepattern","AttrValue":"Animal","IsSKU":"0"},{"AttrID":"200001034","AttrName":"Metal Color","AttrValue":"Champagne Gold","IsSKU":"1"}]"
	@JsonProperty("Attributes")  
	private List<AttributeModel> attributes;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
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

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(String isPackage) {
		this.isPackage = isPackage;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getPackageHeight() {
		return packageHeight;
	}

	public void setPackageHeight(String packageHeight) {
		this.packageHeight = packageHeight;
	}

	public String getPackageWidth() {
		return packageWidth;
	}

	public void setPackageWidth(String packageWidth) {
		this.packageWidth = packageWidth;
	}

	public String getShopTime() {
		return shopTime;
	}

	public void setShopTime(String shopTime) {
		this.shopTime = shopTime;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AttributeModel> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeModel> attributes) {
		this.attributes = attributes;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getPackageLength() {
		return packageLength;
	}

	public void setPackageLength(String packageLength) {
		this.packageLength = packageLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
