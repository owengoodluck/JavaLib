package com.owen.wms.web.form;

import java.util.Set;

import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.entity.YanWenExpressEntity;

public class ExpressScanForm {
	
	private String expressNumber;
	private String previousExpressNumber;
	private AmazonOrder order;
	private Set<AmazonOrderItem> orderItemSet;
	private YanWenExpressEntity express;

	
	public YanWenExpressEntity getExpress() {
		return express;
	}

	public void setExpress(YanWenExpressEntity express) {
		this.express = express;
	}

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
