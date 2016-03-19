package com.owen.wms.lekani.test.service;

import java.util.List;

import org.junit.Test;

import com.owen.wms.lekani.entity.AttributeModel;
import com.owen.wms.lekani.entity.BrandModel;
import com.owen.wms.lekani.entity.CategoryModel;
import com.owen.wms.lekani.entity.GetProductModelListPackage;
import com.owen.wms.lekani.entity.ProductModel;
import com.owen.wms.lekani.service.LKNService;

public class LKNServiceTest {

	@Test
	public void getBrandList(){
		List<BrandModel> list = LKNService.getBrandList();
		if(list!=null){
			int i =0;
			for(BrandModel b : list){
				i++;
//				System.out.println(b.getBrandID() + "----" + b.getBrandName());
//				System.out.println("<form:option value=\""+b.getBrandID()+"\">"+b.getBrandName()+"</form:option>");
				System.out.println(b);
			}
			System.out.println(i);
		}
	}
	
	@Test
	public void getBrandInfo(){
		BrandModel brand = LKNService.getBrandInfo(29);
		System.out.println(brand);
	}
	
	@Test
	public void getCategoryList(){
		 List<CategoryModel> list = LKNService.getCategoryList();
		if(list!=null){
			for(CategoryModel b : list){
//				System.out.println(b.getCatID() + "----" + b.getCatName());
//				System.out.println("<form:option value=\""+b.getCatID()+"\">"+b.getCatName()+"</form:option>");
				System.out.println("categoryMap.put("+b.getCatID()+",\""+b.getCatName()+"\");");
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
		GetProductModelListPackage resp = LKNService.getProductList(pageIndex , categoryID, brandID);
		System.out.println("getPageSize = "+resp.getPageSize() +"---getPageTotal=" +resp.getPageTotal()+"---getRecordTotal="+resp.getRecordTotal());
		List<ProductModel> list = resp.getProductList();
		for(ProductModel p :list){
			System.out.println(p.getBrandName() +"-------" + p.getCatName()+"-------" + p.getName());
			List<AttributeModel> as = p.getAttributes();
			for(AttributeModel a : as){
				System.out.println(a.getAttrName()+"--"+a.getAttrValue());
			}
		}
	}
	
	@Test
	public void getProdInfo(){
		String sku = "";
		int prodId = 102421;
		ProductModel prod = LKNService.getProdInfo(prodId, sku);
		System.out.println(prod.getSKU() +" ---,getDescription =  "+prod.getDescription());
		List<AttributeModel> attrs = prod.getAttributes();
		for(AttributeModel ab : attrs){
			System.out.println(ab.getAttrName()+"---"+ab.getAttrValue());
		}
	}
	
	@Test
	public void getIsOnSale(){
		String sku = "LKNSPCH389";
		boolean resp = LKNService.getIsOnSale( sku);
		System.out.println(sku +" is on sale ? "+resp);
	}
	
	@Test
	public void getStock(){
		String sku = "LKNSPCH389";
		Integer resp = LKNService.getStock( sku);
		System.out.println(sku +" stock = "+resp);
	}
}
