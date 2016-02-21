package com.owen.wms.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryReportParseUtil {

	public static List<String> getLineList(File filename){
		List<String> list = new ArrayList<String>();
		if(filename == null || !filename.exists()){
			return list ;
		}
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = null;
			while ((line = br.readLine()) != null) {
				if(line!=null && line.trim().length()>0){
					list.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) { }
		}
		return list ;
	}
}
