package com.owen.wms.lekani.test.service;

import java.util.List;

import org.junit.Test;

import com.owen.wms.lekani.entity.AttributeModel;
import com.owen.wms.lekani.entity.BrandModel;
import com.owen.wms.lekani.entity.CategoryModel;
import com.owen.wms.lekani.entity.ProductModel;
import com.owen.wms.lekani.service.LKNService;

public class LKNServiceTest {

	@Test
	public void getBrandList(){
		List<BrandModel> list = LKNService.getBrandList();
		if(list!=null){
			for(BrandModel b : list){
				System.out.println(b.getBrandID() + "----" + b.getBrandName());
			}
		}
	}
	
	@Test
	public void getBrandInfo(){
		BrandModel brand = LKNService.getBrandInfo(29);
		System.out.println(brand.getBrandName());
	}
	
	@Test
	public void getCategoryList(){
		 List<CategoryModel> list = LKNService.getCategoryList();
		if(list!=null){
			for(CategoryModel b : list){
				System.out.println(b.getCatID() + "----" + b.getCatName());
			}
		}
	}
	
	@Test
	public void getCategoryInfo(){
		CategoryModel cat = LKNService.getCategoryInfo(205);
		System.out.println(cat.getCatName());
	}
	
	@Test
	public void getProdList(){
		int categoryID = 216;
		int brandID = 10;
		int pageIndex = 1 ;
		LKNService.getProductList(pageIndex , categoryID, brandID);
		
	}
	
	@Test
	public void getProdInfo(){
		String sku = "";
		int prodId = 102421;
		ProductModel prod = LKNService.getProdInfo(prodId, sku);
		System.out.println("getDescription =  "+prod.getDescription());
		List<AttributeModel> attrs = prod.getAttributes();
		for(AttributeModel ab : attrs){
			System.out.println(ab.getAttrName()+"---"+ab.getAttrValue());
		}
	}
}
