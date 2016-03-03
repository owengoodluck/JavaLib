package com.owen.wms.lekani.service;

import java.util.List;

import javax.xml.namespace.QName;

import com.owen.wms.lekani.entity.BrandModel;
import com.owen.wms.lekani.entity.CategoryModel;
import com.owen.wms.lekani.entity.GetBrandListResp;
import com.owen.wms.lekani.entity.GetBrandResp;
import com.owen.wms.lekani.entity.GetCategoryModelListResp;
import com.owen.wms.lekani.entity.GetCategoryModelResp;
import com.owen.wms.lekani.entity.GetIsOnSaleResp;
import com.owen.wms.lekani.entity.GetProductModelListPackage;
import com.owen.wms.lekani.entity.GetProductModelListResp;
import com.owen.wms.lekani.entity.GetProductModelResp;
import com.owen.wms.lekani.entity.GetStockResp;
import com.owen.wms.lekani.entity.LKNBasicResp;
import com.owen.wms.lekani.entity.ProductModel;
import com.owen.wms.lekani.util.JsonUtil;
import com.owen.wms.lekani.wsdl.LekaniStandardWebService;
import com.owen.wms.lekani.wsdl.LekaniStandardWebServiceSoap;

import jxl.common.Logger;

public class LKNService {
	private static Logger log = Logger.getLogger(LKNService.class);
	private static String key ="9b182969902c42049685b80199814d86";
    private static final QName SERVICE_NAME = new QName("http://tempuri.org/", "Lekani_Standard_WebService");
    private static LekaniStandardWebService ss = new LekaniStandardWebService(LekaniStandardWebService.WSDL_LOCATION, SERVICE_NAME);
    private static LekaniStandardWebServiceSoap port = ss.getLekaniStandardWebServiceSoap();  
    
    /**
     * Get Brand list
     * @return
     */
    public static List<BrandModel> getBrandList(){
    	log.info("Invoking getBRANDLIST...");
    	String _getBRANDLIST__return = port.getBRANDLIST(key);
    	log.info("getBRANDLIST.result=" + _getBRANDLIST__return);
    	GetBrandListResp resp = JsonUtil.convertJson2Object(_getBRANDLIST__return, GetBrandListResp.class);
    	if(resp != null){
    		if("0".equals(resp.getStatusCode())){
    			return resp.getRetValue();
    		}else{
    			log.error(resp.getErrorMessage());
    		}
    	}else{
    		log.warn("getBRANDLIST.result Fail !");
    	}
    	return null;
    } 
    
    /**
     * get brand info
     * @param brandID
     * @return
     */
    public static BrandModel getBrandInfo(int brandID ){
        log.info("Invoking getBRANDINFO...");
        int _getBRANDINFO_brandID = brandID;
        String _getBRANDINFO__return = port.getBRANDINFO(key, _getBRANDINFO_brandID);
        log.info("getBRANDINFO.result=" + _getBRANDINFO__return);
        
        GetBrandResp resp = JsonUtil.convertJson2Object(_getBRANDINFO__return, GetBrandResp.class);
    	Object obj = getFinalObject(resp);
    	return obj ==null ? null : (BrandModel) obj;
    }
    
    /**
     * get category list
     * @return
     */
    public static List<CategoryModel> getCategoryList(){
    	log.info("Invoking getCATLIST...");
        String _getCATLIST__return = port.getCATLIST(key);
        log.info("getCATLIST.result=" + _getCATLIST__return);
        GetCategoryModelListResp resp = JsonUtil.convertJson2Object(_getCATLIST__return, GetCategoryModelListResp.class);
    	Object obj = getFinalObject(resp);
    	return obj ==null ? null : (List<CategoryModel>) obj;
    }

    /**
     * get category info
     * @param categoryID
     * @return
     */
    public static CategoryModel getCategoryInfo(int categoryID ){
    	log.info("Invoking getCATINFHO...");
        String _getCATINFHO__return = port.getCATINFHO(key, categoryID);
        log.info("getCATINFHO.result=" + _getCATINFHO__return);
        GetCategoryModelResp resp = JsonUtil.convertJson2Object(_getCATINFHO__return, GetCategoryModelResp.class);
    	Object obj = getFinalObject(resp);
    	return obj ==null ? null : (CategoryModel) obj;
    }
    
    public static GetProductModelListPackage getProductList(int pageIndex,int categoryID,int brandID){
        log.info("Invoking getPRODUCTENLIST...");
        String _getPRODUCTENLIST_shopBeginTime = null;
        String _getPRODUCTENLIST_shopEndTime = null;
        String _getPRODUCTENLIST_skus = null;
        String _getPRODUCTENLIST__return = port.getPRODUCTENLIST(key, pageIndex, categoryID, brandID, _getPRODUCTENLIST_shopBeginTime, _getPRODUCTENLIST_shopEndTime, _getPRODUCTENLIST_skus);
        log.info("getPRODUCTENLIST.result=" + _getPRODUCTENLIST__return);
        _getPRODUCTENLIST__return = specialHandle4Attribute2(_getPRODUCTENLIST__return);
        GetProductModelListResp resp = JsonUtil.convertJson2Object(_getPRODUCTENLIST__return, GetProductModelListResp.class);
    	Object obj = getFinalObject(resp);
    	return obj ==null ? null : (GetProductModelListPackage) obj;
    }

    /**
     * 
		{"RetValue":
		{"ProductID":102421,
		"SKU":"LKNSPCN132-16",
		"BarCode":"7000000238819",
		"Name":"N132-16 hot brand new fashion popular chain necklace jewelry",
		"Price":4.00,
		"Stock":1210,
		"IsPackage":1,
		"Weight":7.50,
		"GrossWeight":22.00,
		"PackageHeight":10.00,
		"PackageLength":10.00,
		"PackageWidth":8.00,
		"ShopTime":"2014-07-22T13:09:15.74",
		"KeyWords":"fashion popular chain necklace jewelry",
		"CatID":216,
		"CatName":"Necklaces",
		"BrandID":10,
		"BrandName":"YUEYIN",
		"Attributes":"[
		{\"AttrID\":\"3\",\"AttrName\":\"Model Number\",\"AttrValue\":\"N132-16\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"10\",\"AttrName\":\"Material\",\"AttrValue\":\"None\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"284\",\"AttrName\":\"Gender\",\"AttrValue\":\"Unisex\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"326\",\"AttrName\":\"Style\",\"AttrValue\":\"Classic\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"100005859\",\"AttrName\":\"Metals Type\",\"AttrValue\":\"Silver Plated\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"200000171\",\"AttrName\":\"Chain Type\",\"AttrValue\":\"Snake Chain\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"200000221\",\"AttrName\":\"Necklace Type\",\"AttrValue\":\"Chains Necklaces\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"200000639\",\"AttrName\":\"Length\",\"AttrValue\":\"16inches*4MM\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"200000640\",\"AttrName\":\"Pendant Size\",\"AttrValue\":\"None\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"200000784\",\"AttrName\":\"Shape\\pattern\",\"AttrValue\":\"Animal\",\"IsSKU\":\"0\"},
		{\"AttrID\":\"200001034\",\"AttrName\":\"Metal Color\",\"AttrValue\":\"Champagne Gold\",\"IsSKU\":\"1\"}
		]",
		"Images":"http://img.pfhoo.com/pro/b/20140715/278dfa46-7bc3-44a6-8fd4-77a42e7736d5.jpg,http://img.pfhoo.com/pro/b/20140715/3b18478b-b2e7-48f3-b964-051de88e3cfc.jpg,http://img.pfhoo.com/pro/b/20140715/785ddfb6-0fca-4acf-8445-8bbba27f092b.jpg",
		"Description":"<img src=\"http://img.inalis.com/InfoImg/20140715/f7be8f40-787f-4838-9916-64d3c4e3b119.jpg\" alt=\"\" /><img src=\"http://img.inalis.com/InfoImg/20140715/ab86aa6c-5528-4ec7-bda9-af4446c6ce5e.jpg\" alt=\"\" /><img src=\"http://img.inalis.com/InfoImg/20140715/a36c869d-42bf-4213-bc5c-ac106c3bba42.jpg\" alt=\"\" />"},
		"StatusCode":0,
		"ErrorMessage":null}
     * @param prodcutID
     * @param sku
     * @return
     */
    public static ProductModel getProdInfo(int prodcutID,String sku){
        log.info("Invoking getPRODUCTENINFO...");
        String resp = port.getPRODUCTENINFO(key, prodcutID, sku);
        log.info("getPRODUCTENINFO.result=" + resp);
        //special handle for attributes
        resp = specialHandle4Attribute2(resp);
        
        GetProductModelResp getProductModelResp = JsonUtil.convertJson2Object(resp, GetProductModelResp.class);
    	Object obj = getFinalObject(getProductModelResp);
    	return obj ==null ? null : (ProductModel) obj;
    }
    
    private static String specialHandle4Attribute1(String input){
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

    public static boolean getIsOnSale(String sku){
        System.out.println("Invoking getISOFFSHELVES...");
        java.lang.String _getISOFFSHELVES__return = port.getISOFFSHELVES(key, sku);
        System.out.println("getISOFFSHELVES.result=" + _getISOFFSHELVES__return);

        GetIsOnSaleResp resp = JsonUtil.convertJson2Object(_getISOFFSHELVES__return, GetIsOnSaleResp.class);
    	if(resp!=null && "1".equals(resp.getRetValue())){
    		return true;
    	}else{
    		return false;
    	}
    }

    public static Integer getStock(String sku){
        System.out.println("Invoking getSTOCK...");
        java.lang.String _getSTOCK__return = port.getSTOCK(key, sku);
        System.out.println("getSTOCK.result=" + _getSTOCK__return);
        
        GetStockResp resp = JsonUtil.convertJson2Object(_getSTOCK__return, GetStockResp.class);
    	if(resp!=null ){
    		return resp.getRetValue();
    	}else{
    		return 0;
    	}
    }

//    public static void getXXX(LekaniStandardWebServiceSoap port ){
//    	
//    }
//
//    public static void getXXX(LekaniStandardWebServiceSoap port ){
//    	
//    }
//
//    public static void getXXX(LekaniStandardWebServiceSoap port ){
//    	
//    }
    private static Object getFinalObject(LKNBasicResp resp){
    	if(resp != null){
    		if("0".equals(resp.getStatusCode())){
    			 return resp.getRetValue();
    		}else{
    			log.error(resp.getErrorMessage());
    		}
    	}else{
    		log.warn("getBRANDLIST.result Fail !");
    	}
    	return null;
    }
}
