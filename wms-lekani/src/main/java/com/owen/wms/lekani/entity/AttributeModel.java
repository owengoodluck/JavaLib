package com.owen.wms.lekani.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Attribute_Model")
public class AttributeModel {
	
	@Id
	private String attributeModelID;
	
	@ManyToOne
	@JoinColumn(name="productID")
	private ProductModel product;//foreign key
	
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

	public String getAttributeModelID() {
		return attributeModelID;
	}

	public void setAttributeModelID(String attributeModelID) {
		this.attributeModelID = attributeModelID;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}
	
	
}
