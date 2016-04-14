package com.owen.wms.web.form;

import java.util.Set;

import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;

public class ExpressScanForm {
	
	private String expressNumber;
	private String previousExpressNumber;
	private AmazonOrder order;
	private Set<AmazonOrderItem> orderItemSet;

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public String getPreviousExpressNumber() {
		return previousExpressNumber;
	}

	public void setPreviousExpressNumber(String previousExpressNumber) {
		this.previousExpressNumber = previousExpressNumber;
	}

	public AmazonOrder getOrder() {
		return order;
	}

	public void setOrder(AmazonOrder order) {
		this.order = order;
	}

	public Set<AmazonOrderItem> getOrderItemSet() {
		return orderItemSet;
	}

	public void setOrderItemSet(Set<AmazonOrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}
	
}
