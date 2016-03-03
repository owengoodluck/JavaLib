package com.owen.wms.lekani.test.util;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.owen.wms.common.util.FileUtil;
import com.owen.wms.lekani.entity.BrandModel;
import com.owen.wms.lekani.entity.GetBrandListResp;
import com.owen.wms.lekani.entity.GetProductModelListResp;
import com.owen.wms.lekani.entity.ProductModel;
import com.owen.wms.lekani.util.JsonUtil;

public class JsonUtilTest {
	private String s1="{\"RetValue\":[{\"BrandID\":29,\"BrandName\":\"潘多拉系列\",\"BrandEnName\":\"pandora\"},{\"BrandID\":27,\"BrandName\":\"婚饰系列\",\"BrandEnName\":\"Other\"},{\"BrandID\":13,\"BrandName\":\"古玛雅\",\"BrandEnName\":\"GOMAYA\"},{\"BrandID\":17,\"BrandName\":\"香芭拉\",\"BrandEnName\":\"Shambala\"},{\"BrandID\":21,\"BrandName\":\"时尚K金产品\",\"BrandEnName\":\"Other\"},{\"BrandID\":12,\"BrandName\":\"珍姿美\",\"BrandEnName\":\"JETHMY\"},{\"BrandID\":28,\"BrandName\":\"民族风系列\",\"BrandEnName\":\"Folk-custom\"},{\"BrandID\":25,\"BrandName\":\"夸张大牌产品\",\"BrandEnName\":\"Statement Jewelry\"},{\"BrandID\":11,\"BrandName\":\"依娜丽饰\",\"BrandEnName\":\"INALIS\"},{\"BrandID\":32,\"BrandName\":\"威妮华\",\"BrandEnName\":\"Viennois\"},{\"BrandID\":33,\"BrandName\":\"天然石系列\",\"BrandEnName\":\"Other\"},{\"BrandID\":18,\"BrandName\":\"品牌A\",\"BrandEnName\":\"A New Brand\"},{\"BrandID\":10,\"BrandName\":\"又一银\",\"BrandEnName\":\"YUEYIN\"},{\"BrandID\":20,\"BrandName\":\"K金锆石类\",\"BrandEnName\":\"KGP Zircon\"},{\"BrandID\":31,\"BrandName\":\"爆款品类\",\"BrandEnName\":\"Other\"},{\"BrandID\":30,\"BrandName\":\"韩风\",\"BrandEnName\":\"Other\"},{\"BrandID\":14,\"BrandName\":\"法伯丽\",\"BrandEnName\":\"FAVOURER\"},{\"BrandID\":22,\"BrandName\":\"时尚银饰产品\",\"BrandEnName\":\"Other\"},{\"BrandID\":19,\"BrandName\":\"品牌B\",\"BrandEnName\":\"B New Brand\"},{\"BrandID\":99,\"BrandName\":\"其它\",\"BrandEnName\":\"OTHER\"}],\"StatusCode\":0,\"ErrorMessage\":null}";
	
	@Test
	public void getBrandListResp1() throws Exception, JsonMappingException, IOException{
		GetBrandListResp resp =JsonUtil.convertJson2Object(s1, GetBrandListResp.class);
		List<BrandModel> arr = resp.getRetValue();
        for (BrandModel brand: arr) {
            System.out.println(brand.getBrandID()+"-"+brand.getBrandName()+"-"+brand.getBrandEnName());
        }
		System.out.println(resp);
	}
	
	
	@Test
	public void test1(){
			String input = FileUtil.readFile2String("C:/Users/owen/git/wms-lekani/src/test/resources/prodList.json");
			input = specialHandle4Attribute2(input);
	        GetProductModelListResp resp = JsonUtil.convertJson2Object(input, GetProductModelListResp.class);
	    	List<ProductModel> list = resp.getRetValue().getProductList();
	    	System.out.println("prod list size = "+list.size());
	}

    
    private static String specialHandle4Attribute(String input){
    	int index0=0,index1=0,index2=0;
    	String token1 = "\"Attributes\":\"[";
    	String token2 ="]\",\"Images";
    	
    	StringBuffer result = new StringBuffer();
    	while(  (index1 = input.indexOf(token1,index2)) > -1 ){
    		index2 = input.indexOf(token2, index1);
    		
    		result.append(input.substring(index0, index1));
    		String tmp = input.substring(index1, index2+1);
    		tmp = "\"Attributes\":[" + tmp.substring(token1.length());
    		tmp = tmp.replaceAll("\\\\", "");
    		
    		result.append(tmp);
    		index0= index2 +2;
    	}
    	result.append(input.substring(index0));
    	return result.toString();
    }
    
    private static String specialHandle4Attribute2(String input){
    	int index0=0,index1=0,index2=0;
    	String token1 = "\"Attributes\":\"[";
    	String token2 ="]\",\"Images";
    	
    	StringBuffer result = new StringBuffer();
    	while(  (index1 = input.indexOf(token1,index2)) > -1 ){
    		index2 = input.indexOf(token2, index1);
    		
    		result.append(input.substring(index0, index1));
    		String s = input.substring(index1, index2+1);
    		s = "\"Attributes\":[" + s.substring(token1.length());
        	s = s.replaceAll("\\\\\"AttrID\\\\\":\\\\", "\"AttrID\":");
        	s = s.replaceAll("\\\\\",\\\\\"AttrName\\\\\":\\\\", "\",\"AttrName\":");
        	s = s.replaceAll("\\\\\",\\\\\"AttrValue\\\\\":\\\\", "\",\"AttrValue\":");
        	s = s.replaceAll("\\\\\",\\\\\"IsSKU\\\\\":\\\\\"0\\\\", "\",\"IsSKU\":\"0");
        	s = s.replaceAll("\\\\\",\\\\\"IsSKU\\\\\":\\\\\"1\\\\", "\",\"IsSKU\":\"1");
    		result.append(s);
    		index0= index2 +2;
    	}
    	result.append(input.substring(index0));
    	return result.toString();
    }
	
    @Test
    public void testReplace(){
    	String  s= "{\\\"AttrID\\\":\\\"3\\\",\\\"AttrName\\\":\\\"Model Number\\\",\\\"AttrValue\\\":\\\"N011-20\\\" N011-24\\\",\\\"IsSKU\\\":\\\"0\\\"},";
    	System.out.println(s);
//    	s = s.replaceAll("\\\\", "");
    	s = s.replaceAll("\\\\\"AttrID\\\\\":\\\\", "\"AttrID\":");
    	System.out.println(s);
    	s = s.replaceAll("\\\\\",\\\\\"AttrName\\\\\":\\\\", "\",\"AttrName\":");
    	System.out.println(s);
    	s = s.replaceAll("\\\\\",\\\\\"AttrValue\\\\\":\\\\", "\",\"AttrValue\":");
    	System.out.println(s);
    	s = s.replaceAll("\\\\\",\\\\\"IsSKU\\\\\":\\\\\"0\\\\", "\",\"IsSKU\":\"0");
    	System.out.println(s);
    	s = s.replaceAll("\\\\\",\\\\\"IsSKU\\\\\":\\\\\"1\\\\", "\",\"IsSKU\":\"1");
    	System.out.println(s);
    }
}
