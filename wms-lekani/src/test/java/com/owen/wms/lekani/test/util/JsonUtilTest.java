package com.owen.wms.lekani.test.util;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.owen.wms.lekani.entity.BrandModel;
import com.owen.wms.lekani.entity.GetBrandListResp;
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
//		String s = "{\"RetValue\":{\"ProductID\":102421,\"SKU\":\"LKNSPCN132-16\",\"BarCode\":\"7000000238819\",\"Name\":\"N132-16 hot brand new fashion popular chain necklace jewelry\",\"Price\":4.00,\"Stock\":1210,\"IsPackage\":1,\"Weight\":7.50,\"GrossWeight\":22.00,\"PackageHeight\":10.00,\"PackageLength\":10.00,\"PackageWidth\":8.00,\"ShopTime\":\"2014-07-22T13:09:15.74\",\"KeyWords\":\"fashion popular chain necklace jewelry\",\"CatID\":216,\"CatName\":\"Necklaces\",\"BrandID\":10,\"BrandName\":\"YUEYIN\",\"Attributes\":\"[{\\"AttrID\\":\\"3\\",\\"AttrName\\":\\"Model Number\\",\\"AttrValue\\":\\"N132-16\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"10\\",\\"AttrName\\":\\"Material\\",\\"AttrValue\\":\\"None\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"284\\",\\"AttrName\\":\\"Gender\\",\\"AttrValue\\":\\"Unisex\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"326\\",\\"AttrName\\":\\"Style\\",\\"AttrValue\\":\\"Classic\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"100005859\\",\\"AttrName\\":\\"Metals Type\\",\\"AttrValue\\":\\"Silver Plated\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"200000171\\",\\"AttrName\\":\\"Chain Type\\",\\"AttrValue\\":\\"Snake Chain\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"200000221\\",\\"AttrName\\":\\"Necklace Type\\",\\"AttrValue\\":\\"Chains Necklaces\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"200000639\\",\\"AttrName\\":\\"Length\\",\\"AttrValue\\":\\"16inches*4MM\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"200000640\\",\\"AttrName\\":\\"Pendant Size\\",\\"AttrValue\\":\\"None\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"200000784\\",\\"AttrName\\":\\"Shape\\pattern\\",\\"AttrValue\\":\\"Animal\\",\\"IsSKU\\":\\"0\\"},{\\"AttrID\\":\\"200001034\\",\\"AttrName\\":\\"Metal Color\\",\\"AttrValue\\":\\"Champagne Gold\\",\\"IsSKU\\":\\"1\\"}]\",\"Images\":\"http://img.pfhoo.com/pro/b/20140715/278dfa46-7bc3-44a6-8fd4-77a42e7736d5.jpg,http://img.pfhoo.com/pro/b/20140715/3b18478b-b2e7-48f3-b964-051de88e3cfc.jpg,http://img.pfhoo.com/pro/b/20140715/785ddfb6-0fca-4acf-8445-8bbba27f092b.jpg\",\"Description\":\"<img src=\\"http://img.inalis.com/InfoImg/20140715/f7be8f40-787f-4838-9916-64d3c4e3b119.jpg\\" alt=\\"\\" /><img src=\\"http://img.inalis.com/InfoImg/20140715/ab86aa6c-5528-4ec7-bda9-af4446c6ce5e.jpg\\" alt=\\"\\" /><img src=\\"http://img.inalis.com/InfoImg/20140715/a36c869d-42bf-4213-bc5c-ac106c3bba42.jpg\\" alt=\\"\\" />\"},\"StatusCode\":0,\"ErrorMessage\":null}";
	}

	
}
