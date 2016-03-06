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
import com.owen.wms.web.dao.LekaniProductDao;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.entity.JewelryEntity;;

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
	
	public ProductModel getProductModelByID(String prodID){
		ProductModel prod = this.lekaniProductDao.get(prodID);
		return prod;
	}
	public void loadProdByCategoryAndBrand(int categoryID,int brandID){
		int pageIndex = 1 ;
		GetProductModelListPackage resp = LKNService.getProductList(pageIndex , categoryID, brandID);
		if(resp!=null){
			saveOrUpdateProdList(resp.getProductList());
		}
	}
	
	public void saveOrUpdateProdList(List<ProductModel> list){
		if(list == null || list.isEmpty()){
			return;
		}
		int pageIndex = 1 ;
		int i=0;
		for(ProductModel p :list){
			String pID = p.getProductID();
			List<AttributeModel> attList = p.getAttributes();
			for(AttributeModel a : attList){
				a.setAttributeModelID(pID+"_"+a.getAttrID());
				a.setProduct(p);
			}
			
			//get if on sale or not
			boolean isOnSale = LKNService.getIsOnSale( p.getSKU() );
			p.setOnSale(isOnSale);
			
			try{
				this.lekaniProductDao.saveOrUpdate(p);
			}catch(Exception e){
				log.error(e.getMessage());
				this.lekaniProductDao.merge(p);
			}
			i++;
			log.info( i+"----"+pID );
		}
	}
	public Page pageListByCriteria(int currentPage,int pageSize,ProductModel entity){
		return this.lekaniProductDao.pageListByCriteria(currentPage, pageSize, entity);
	}
}
