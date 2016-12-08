package com.owen.wms.web.util;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.owen.wms.web.utils.ExcelUtil;

public class ExcelUtilTest {

	private Logger log = Logger.getLogger("ExcelUtilTest");
	
	@Test
	public void testReadYanwenBill() throws Exception{
		String input ="C:/Users/owen/Desktop/Amazon/燕文物流/bills/401598(2015-12-29-2015-12-29).xls";
		List<String[]> list = ExcelUtil.readExcel(new File(input),0,15,1);
		for(String[] row: list){
			for(String col:row){
				System.out.print(col+",");
			}
			System.out.println();
		}
	}
	
	
}
