package com.owen.wms.web.service;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.dao.UpcDao;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.entity.UPC;
import com.owen.wms.web.utils.ExcelUtil;
import com.owen.wms.web.utils.StringUtils;

@Service
@Transactional
public class UpcService {

	private  Logger log = Logger.getLogger(UpcService.class);
	
	@Autowired
	private UpcDao upcDao;
	
	@Autowired
	@Qualifier("amazonJewelryDao")
	private AmazonJewelryDao amazonJewelryDao;
	
	public void loadUPCFromExcel(String excelPath){
		File excelFile = new File(excelPath);
		if( !excelFile.exists() || !excelFile.isFile()){
			log.warn(excelPath+" file doesn't exist !");
			return;
		}
		
		List<String[]> list = ExcelUtil.readExcel(excelFile, 0, 1, 0);
		for(String[] row : list){
			this.log.info(row[0]);
			UPC u =new UPC(row[0],"NEW");
			this.upcDao.save(u);
		}
	}
	
	public List<UPC> getCountOfNewUPC(int count){
		return this.upcDao.getCountOfNewUPC(count);
	}
	
	public void setUPC4JewelryList(String[] skuList ){
		for(String sku : skuList){
			this.setUPC4Jewelry(sku);
		}
	}
	
	public JewelryEntity setUPC4Jewelry(String prodID){
		JewelryEntity jew = this.amazonJewelryDao.get(prodID);
		if(jew!=null && !"parent".equals(jew.getParentChild())){
			setUPC4Jewelry(jew);
		}else{
			this.log.warn(" product is not foud by ID ="+prodID);
		}
		return jew;
	}
	
	public boolean cleanUPC4Jewelry(String prodID){
		JewelryEntity jew = this.amazonJewelryDao.get(prodID);
		if(jew!=null){
			return this.cleanUPC4Jewelry(jew);
		}else{
			this.log.warn(" product is not foud by ID ="+prodID);
			return false;
		}
	}
	
	public void setUPC4Jewelry(JewelryEntity jew){
		if(jew==null){
			return;
		}
		if(!StringUtils.isEmpty(jew.getExternalProductId())){
			this.cleanUPC4Jewelry(jew);
		}
		
		//1.get a "NEW" UPC
		UPC upc = this.upcDao.getNewUPC();
		if(upc==null){
			this.log.error(" No more UPC is found ! ");
			return;
		}
		
		//2.set upc 4 jew and update DB
		jew.setExternalProductId(upc.getCode());
		amazonJewelryDao.update(jew);
		
		//3. update UPC table record status to "USED" 
		upc.setStatus("USED");
		this.upcDao.update(upc);
	}
	
	public boolean cleanUPC4Jewelry(JewelryEntity jew){
		if(jew==null){
			return false;
		}
		//1. update status to "NEW" in UPC table
		String upcCode = jew.getExternalProductId();
		UPC upc = this.upcDao.get(upcCode);
		if(upc == null){
			this.log.error(" upc code ='"+upcCode+"' is not found !");
			return false ;
		}else{
			upc.setStatus("NEW");
		}
		
		//2. clean upc 4 jew and update in DB
		jew.setExternalProductId(null);
		this.amazonJewelryDao.update(jew);
		return true;
		
	}
}
