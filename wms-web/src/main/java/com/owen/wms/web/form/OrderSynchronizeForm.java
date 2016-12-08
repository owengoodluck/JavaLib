package com.owen.wms.web.form;

public class OrderSynchronizeForm {

	private String marketPlace;
	private String startDateStr;
	private String endDateStr;
	
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getMarketPlace() {
		return marketPlace;
	}
	public void setMarketPlace(String marketPlace) {
		this.marketPlace = marketPlace;
	}
	
}
