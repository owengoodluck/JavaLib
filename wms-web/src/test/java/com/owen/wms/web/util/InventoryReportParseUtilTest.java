package com.owen.wms.web.util;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.owen.wms.web.utils.InventoryReportParseUtil;

public class InventoryReportParseUtilTest {

	@Test
	public void test(){
		File filename = new File("C:/Users/owen/Desktop/tmp/1305645790016850.txt");
		InventoryReportParseUtil.getLineList(filename );
	}
	

	@Test
	public void test1(){
		Set<String> parentSkuSet=new HashSet<String>();
		File filename = new File("C:/Users/owen/Desktop/tmp/1305532180016850.txt");
		List<String> list = InventoryReportParseUtil.getLineList(filename );
		int index1=0,index2=0;
		for(String line : list){
//			System.out.println(line);
			if(line.indexOf("8805")>-1){
				index1 = line.indexOf("(Parent SKU:");
				if(index1>-1){
					index2 = line.indexOf(")",index1);
					if(index2 >0){
						parentSkuSet.add(line.substring(index1+12,index2).trim());
					}
				}
			}
		}
		for(String sku : parentSkuSet){
			System.out.println(sku);
			
		}
	}
}
