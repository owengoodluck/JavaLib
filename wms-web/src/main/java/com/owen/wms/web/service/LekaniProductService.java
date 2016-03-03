package com.owen.wms.web.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.owen.wms.lekani.entity.AttributeModel;
import com.owen.wms.lekani.entity.GetProductModelListPackage;
import com.owen.wms.lekani.entity.ProductModel;
import com.owen.wms.lekani.service.LKNService;
import com.owen.wms.web.dao.LekaniProductAttributeDao;
import com.owen.wms.web.dao.LekaniProductDao;;

@Service("lekaniProductService")
@Transactional
public class LekaniProductService {
	private Logger log = Logger.getLogger(this.getClass());

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Autowired
	@Qualifier("lekaniProductAttributeDao")
	private LekaniProductAttributeDao lekaniProductAttributeDao;

	@Autowired
	@Qualifier("lekaniProductDao")
	private LekaniProductDao lekaniProductDao;
	
	public void loadProdList(int categoryID,int brandID){
		int pageIndex = 1 ;
		GetProductModelListPackage resp = LKNService.getProductList(pageIndex , categoryID, brandID);
		if(resp!=null){
			List<ProductModel> list = resp.getProductList();
			int i=0;
			for(ProductModel p :list){
				String pID = p.getProductID();
				List<AttributeModel> attList = p.getAttributes();
				for(AttributeModel a : attList){
					a.setAttributeModelID(pID+"_"+a.getAttrID());
				}
				this.lekaniProductDao.saveOrUpdate(p);
				i++;
				if(i>10){
					break;
				}
			}
		}
	}
}
