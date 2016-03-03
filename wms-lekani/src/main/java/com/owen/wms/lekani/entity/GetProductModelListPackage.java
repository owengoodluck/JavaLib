package com.owen.wms.lekani.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductModelListPackage {
	
	@JsonProperty("PageIndex")
	private int pageIndex;
	
	@JsonProperty("PageTotal")
	private int pageTotal;
	
	@JsonProperty("PageSize")
	private int pageSize;
	
	@JsonProperty("RecordTotal")
	private int recordTotal;
	
	@JsonProperty("ProductList")  
	private List<ProductModel> productList;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}

	public List<ProductModel> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductModel> productList) {
		this.productList = productList;
	}

}
