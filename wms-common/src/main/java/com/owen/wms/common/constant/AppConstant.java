package com.owen.wms.common.constant;

public class AppConstant {
	public final static String picDownloadPath = "C:/Users/owen/Desktop/tmp_pic/";  
	public final static String defaultPdfDownloadPath = "C:/Users/owen/Desktop/Amazon/燕文物流/运单打印";
	public final static Double ShippingFeeEarnPerShip = 2.9;  //USD
	public final static Double ShippingFeeEarnPerItem = 1.95;  //USD
	public final static Double USDRate = 6.4;  
	public final static Double ShippingFeePay = 14.5;  //RMB
	public final static Double profit = 18.5;  //RMB
	
	//yanwen
    public static String yanwenUserId ;
    public static String yanwenUserToken ;
    
    //amazon
    public static String manufacturer;
    public static String accessKeyId ;
    public static String secretAccessKey ;
    public static String sellerId ;
    public static String appName ;//"<Your Application or Company Name>";
    public static String appVersion ;
    public static String marketplaceIDUS ;

    //others 
    public static String picCopyFolder;

	public void setYanwenUserId(String yanwenUserId) {
		AppConstant.yanwenUserId = yanwenUserId;
	}

	public void setYanwenUserToken(String yanwenUserToken) {
		AppConstant.yanwenUserToken = yanwenUserToken;
	}

	public void setManufacturer(String manufacturer) {
		AppConstant.manufacturer = manufacturer;
	}

	public void setAccessKeyId(String accessKeyId) {
		AppConstant.accessKeyId = accessKeyId;
	}

	public void setSecretAccessKey(String secretAccessKey) {
		AppConstant.secretAccessKey = secretAccessKey;
	}

	public void setSellerId(String sellerId) {
		AppConstant.sellerId = sellerId;
	}

	public void setAppName(String appName) {
		AppConstant.appName = appName;
	}

	public void setAppVersion(String appVersion) {
		AppConstant.appVersion = appVersion;
	}

	public void setMarketplaceIDUS(String marketplaceIDUS) {
		AppConstant.marketplaceIDUS = marketplaceIDUS;
	}

	public void setPicCopyFolder(String picCopyFolder) {
		AppConstant.picCopyFolder = picCopyFolder;
	}
    
    
    
}
