package com.owen.wms.lekani.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductModelListPackage {
	
	@JsonProperty("PageIndex")
	private String pageIndex;
	
	@JsonProperty("PageTotal")
	private String pageTotal;
	
	@JsonProperty("PageSize")
	private String pageSize;
	
	@JsonProperty("RecordTotal")
	private String recordTotal;
	
	@JsonProperty("ProductList")  
	private List<ProductModel> productList;

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(String pageTotal) {
		this.pageTotal = pageTotal;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(String recordTotal) {
		this.recordTotal = recordTotal;
	}

	public List<ProductModel> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductModel> productList) {
		this.productList = productList;
	}

}
