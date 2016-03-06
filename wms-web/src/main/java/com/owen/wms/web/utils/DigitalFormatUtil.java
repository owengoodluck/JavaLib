package com.owen.wms.web.utils;

import java.math.BigDecimal;

public class DigitalFormatUtil {

	public static double getFormatedDouble(double originalValue,int numberOfDecimal){
		BigDecimal   b   =   new   BigDecimal(originalValue);  
		return b.setScale(numberOfDecimal,   BigDecimal.ROUND_HALF_UP).doubleValue();  
	}
}
