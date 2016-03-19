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
		List<JewelryEntity> list = new ArrayList();
		int index=0;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			JewelryEntity ent =(JewelryEntity) in.readObject();
			while(ent!=null){
				log.info(ent);
				ent = (JewelryEntity) in.readObject();
				list.add(ent);
				index++;
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
		log.info(list.size()+" object is read ");
	}
}
