package com.owen.wms.lekani.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Product_Model")
public class ProductModel {

	@Id
	@JsonProperty("ProductID")  
	private String productID;

	private boolean onSale;
	
	private String status;//selected,discard,converted and empty
	
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
	private Integer stock;
	
	private Integer stockPrevious;

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
	@Column(length=1000)
	private String images;

	private String mainImage;
	
//  <img src=\"http://img.inalis.com/InfoImg/20140717/7eece521-6d4e-4070-995d-1e0acae3c3f7.jpg\" alt=\"\" />
//	<img src=\"http://img.inalis.com/InfoImg/20140717/741093a6-8f3e-4f8f-a411-0db9fdfc837f.jpg\" alt=\"\" />"
//	<img src=\"http://img.inalis.com/InfoImg/20140717/ddfdfc12-7b67-4c07-9f43-3093a5eefcd5.jpg\" alt=\"\" />"
//	<img src=\"http://img.inalis.com/InfoImg/20140717/bf272e58-e975-4b28-b434-580f3036dd73.jpg\" alt=\"\" />
	@JsonProperty("Description")  
	@Column(length=1000)
	private String description;

	public List<String> getDescriptionPicList(){
		ArrayList<String> list = new ArrayList<String>();
		if(description!=null){
			int index1=0;
			int index2=0;
			String pre="http://img",end=".jpg";
			index1= description.indexOf(pre,index2);
			while(index1!=-1){
				index2=description.indexOf(end,index1);
				if(index1!=-1){
					list.add(description.substring(index1, index2+4));
				}else{
					break;
				}
				index1= description.indexOf(pre,index2);
			}
		}
		return list;
	}
	
	@JsonProperty("Name")  
	private String name;
	
	// if use List need to update the json string:
	//1.jsonString = jsonString.replaceAll("\\\\", "");
	//2.remove quote around [ ] for "Attributes"
	//"Attributes":"[{"AttrID":"200000784","AttrName":"Shapepattern","AttrValue":"Animal","IsSKU":"0"},{"AttrID":"200001034","AttrName":"Metal Color","AttrValue":"Champagne Gold","IsSKU":"1"}]"
	@JsonProperty("Attributes")  
	@OneToMany(mappedBy="product",fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN}) 
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
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
		if(images!=null){
			String[] imageList = images.split(",");
			this.mainImage = imageList[0];
		}
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

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public boolean isOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	public Integer getStockPrevious() {
		return stockPrevious;
	}

	public void setStockPrevious(Integer stockPrevious) {
		this.stockPrevious = stockPrevious;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductModel [" + (productID != null ? "productID=" + productID + ", " : "") + "onSale=" + onSale + ", "
				+ (status != null ? "status=" + status + ", " : "") + (catID != null ? "catID=" + catID + ", " : "")
				+ (catName != null ? "catName=" + catName + ", " : "")
				+ (brandID != null ? "brandID=" + brandID + ", " : "")
				+ (brandName != null ? "brandName=" + brandName + ", " : "") + (SKU != null ? "SKU=" + SKU + ", " : "")
				+ (barCode != null ? "barCode=" + barCode + ", " : "") + (price != null ? "price=" + price + ", " : "")
				+ (stock != null ? "stock=" + stock + ", " : "")
				+ (stockPrevious != null ? "stockPrevious=" + stockPrevious + ", " : "")
				+ (isPackage != null ? "isPackage=" + isPackage + ", " : "")
				+ (weight != null ? "weight=" + weight + ", " : "")
				+ (grossWeight != null ? "grossWeight=" + grossWeight + ", " : "")
				+ (packageHeight != null ? "packageHeight=" + packageHeight + ", " : "")
				+ (packageLength != null ? "packageLength=" + packageLength + ", " : "")
				+ (packageWidth != null ? "packageWidth=" + packageWidth + ", " : "")
				+ (shopTime != null ? "shopTime=" + shopTime + ", " : "")
				+ (keyWords != null ? "keyWords=" + keyWords + ", " : "")
				+ (images != null ? "images=" + images + ", " : "")
				+ (mainImage != null ? "mainImage=" + mainImage + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (name != null ? "name=" + name + ", " : "") + (attributes != null ? "attributes=" + attributes : "")
				+ "]";
	}

	
	
}
