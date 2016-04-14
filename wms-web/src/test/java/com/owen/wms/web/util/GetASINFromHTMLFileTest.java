package com.owen.wms.web.util;

import java.util.List;

import org.junit.Test;

import com.owen.wms.common.util.FileUtil;

public class GetASINFromHTMLFileTest {
	private String filePath="C:/Users/owen/Desktop/tmp/asinHtmlCopy.txt";
	
	@Test
	public void getASIN(){
		List<String> list = FileUtil.readFile2List(filePath,true);
		for(String str:list){
			if(str.startsWith("B0")){
				System.out.println(str);
			}
		}
	}
}
