package com.owen.wms.web.constants;

public class AppConstant {
	public final static String picDownloadPath = "C:/Users/owen/Desktop/tmp_pic/";  
	public final static String defaultPdfDownloadPath = "C:/Users/owen/Desktop/Amazon/燕文物流/运单打印";
	public final static Double ShippingFeeEarnPerShip = 2.9;  //USD
	public final static Double ShippingFeeEarnPerItem = 1.95;  //USD
	public final static Double USDRate = 6.4;  
	public final static Double ShippingFeePay = 14.5;  //RMB
	public final static Double profit = 18.5;  //RMB
	
	public final static String manufacturer = "CandyVillage";
	
	//
	public static String picCopyFolder;

	public String getPicCopyFolder() {
		return picCopyFolder;
	}

	public void setPicCopyFolder(String picCopyFolder) {
		AppConstant.picCopyFolder = picCopyFolder;
	}
	
}
