package com.owen.wms.web.utils;

public class JewelryMappingUtil {

	public static String getFeedProductTypeByItemType(String itemType){
		if(itemType!=null){
			itemType = itemType.toLowerCase();
			switch (itemType){
			case "pendant-necklaces":return "FashionNecklaceBraceletAnklet";
			case "link-bracelets":return"FashionNecklaceBraceletAnklet";
			case "rings":return"FashionRing";
			case "earrings":return"FashionEarring";
			default:return null;//TODO TBC
			}
		}else{
			return null;
		}
	}
}
