package com.owen.wms.web.service;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.owen.wms.common.util.DateUtil;
import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.entity.JewelryEntity;

@Service
public class BackupService {

	private  Logger log = Logger.getLogger(BackupService.class);
	
	@Autowired
	@Qualifier("amazonJewelryDao")
	private AmazonJewelryDao amazonJewelryDao;
	
	@Value("${db.backup.folder}")
	private String backFileFolder;
	
	@Value("${db.backup.sku.prefix}")
	private String skuPrefix;
	
	@Value("${db.backup.sku.transform.index}")
	private int skuTransformIndex;
	
	@Value("${db.backup.is.load.into.db}")
	private boolean isLoadIntoDB;
	
	@Value("${amz.manufacturer}")
	private String manufacturer;
	
	public void writeJewelryProd2File(){
		//check and create target file
		File folder = new File(backFileFolder);
		if(!folder.exists()){
			try {
				folder.mkdir();
			} catch (Exception e) {
				log.error(e.getMessage());
				return ;
			}
		}
		File file = new File(folder.getAbsolutePath()+"/jew_bak_"+DateUtil.getDateString(new Date(), "MMdd")+".dat");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			log.error(e1.getMessage());
			return ;
		}
		
		//2. get obj list
		List<JewelryEntity> list = this.amazonJewelryDao.list();
		
		//3. write to file
		ObjectOutputStream out = null;  
		try {
			out= new ObjectOutputStream(new FileOutputStream(file));
			for(JewelryEntity e : list){
				if(e==null){
					continue;
				}
				out.writeObject(e);
				out.flush();
			}
			out.writeObject(null);//TODO there is a problem if not write a null
			log.info(" Write "+list.size()+" JewelryEntity into "+file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try { out.close();  } catch (IOException e) { }
			}
		}
	}
	
	public void readJewelryProdFromFile(File file){
		if(!file.exists()){
			return;
		}
		ObjectInputStream in = null;
		int index=0;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			JewelryEntity ent =(JewelryEntity) in.readObject();
			while(ent!=null){
				log.info(ent);
				ent = (JewelryEntity) in.readObject();
				if(this.isLoadIntoDB){
					if(this.transferAndLoadIntoDB(ent)){
						index++;
					}
				}
			}
		} catch(EOFException e1){
			log.info("file end !");
		}catch (Exception e) {
			log.error(index+" object read exception: "+e.getMessage(),e);
		} finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		log.info(index +" rows inserted into DB !");
	}
	
	private boolean transferAndLoadIntoDB(JewelryEntity input){
		if(input == null){
			return false;
		}
		
		String newSku = this.getNewSKU(input.getItemSku(), skuTransformIndex, skuPrefix);
		String newParentSku = this.getNewSKU(input.getParentSku(), skuTransformIndex, skuPrefix);
		if(this.amazonJewelryDao.get(newSku)!=null){
			log.info(newSku +" (new = " +input.getItemSku()+") exists in local DB .");
			return false;
		}else{
			input.setItemSku(newSku);
			input.setParentSku(newParentSku);
			input.setManufacturer(manufacturer);
			input.setExternalProductId(null);
			this.amazonJewelryDao.save(input);
			return true;
		}
	}
	
	private String getNewSKU(String str,int index,String prefix) {
		if(str==null || str.trim().length()<1){
			return null;
		}
		StringBuffer rs = new StringBuffer(prefix);
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				int x = Integer.valueOf(str.charAt(i)+"") + index;
				if (x >= 10) {
					rs.append(x%10);
				} else {
					rs.append(x);
				}
			} else {
				rs.append(str.charAt(i));
			}
		}
		return rs.toString();
	}
}
