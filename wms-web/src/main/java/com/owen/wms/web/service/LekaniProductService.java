package com.owen.wms.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.owen.wms.common.constant.AppConstant;
import com.owen.wms.lekani.entity.AttributeModel;
import com.owen.wms.lekani.entity.GetProductModelListPackage;
import com.owen.wms.lekani.entity.ProductModel;
import com.owen.wms.lekani.service.LKNService;
import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.dao.LekaniProductAttributeDao;
import com.owen.wms.web.dao.LekaniProductDao;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.utils.DigitalFormatUtil;
import com.owen.wms.web.utils.JewelryMappingUtil;;

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

	@Autowired
	@Qualifier("amazonJewelryDao")
	private AmazonJewelryDao amazonJewelryDao;
	
	private Map<Integer,String> categoryMap = new HashMap<Integer,String>();
	private Map<Integer,String> brandMap = new HashMap<Integer,String>();
	{
		brandMap.put(29,"潘多拉系列");
		brandMap.put(37,"钛钢系列");
		brandMap.put(27,"婚饰系列");
		brandMap.put(13,"古玛雅");
		brandMap.put(17,"香芭拉");
		brandMap.put(21,"时尚K金产品");
		brandMap.put(12,"珍姿美");
		brandMap.put(28,"民族风系列");
		brandMap.put(25,"夸张大牌产品");
		brandMap.put(11,"依娜丽饰");
		brandMap.put(32,"威妮华");
		brandMap.put(38,"包包系列");
		brandMap.put(33,"天然石系列");
		brandMap.put(18,"品牌A");
		brandMap.put(10,"又一银");
		brandMap.put(20,"K金锆石类");
		brandMap.put(31,"爆款品类");
		brandMap.put(30,"韩风");
		brandMap.put(14,"法伯丽");
		brandMap.put(22,"时尚银饰产品");
		brandMap.put(19,"品牌B");
		brandMap.put(99,"其它");
		
		//-----------------------------
		categoryMap.put(201,"戒指");
		categoryMap.put(210,"耳钉");
		categoryMap.put(216,"项链");
		categoryMap.put(217,"吊坠");
		categoryMap.put(211,"耳圈");
		categoryMap.put(202,"耳饰");
		categoryMap.put(212,"耳钩&耳坠");
		categoryMap.put(218,"长款项链");
		categoryMap.put(205,"项链&吊坠");
		categoryMap.put(207,"手链");
		categoryMap.put(219,"链条配链");
		categoryMap.put(213,"耳夹&耳扣");
		categoryMap.put(208,"手镯");
		categoryMap.put(220,"脚链");
		categoryMap.put(215,"耳饰花托");
		categoryMap.put(204,"饰品套装");
		categoryMap.put(222,"胸针");
		categoryMap.put(244,"时尚眼镜");
		categoryMap.put(224,"钥匙扣&钱夹");
		categoryMap.put(223,"头饰、发饰");
		categoryMap.put(104,"LEKANI 纯银首饰");
		categoryMap.put(54,"包装&展件");
		categoryMap.put(249,"双肩背包");
		categoryMap.put(225,"钥匙扣");
		categoryMap.put(226,"钱夹");
		categoryMap.put(245,"太阳镜");
		categoryMap.put(247,"阅读镜");
		categoryMap.put(248,"眼镜盒");
		categoryMap.put(243,"手提/单肩/斜跨包");
		categoryMap.put(242,"钱包");
		categoryMap.put(241,"时尚包包");
		categoryMap.put(221,"领带夹&袖扣");
	}
	
	public void upateStatus(String prodID,String status){
		if(status!=null && status.trim().length()>0){
			ProductModel prod = this.lekaniProductDao.get(prodID);
			if(prod!=null){
				prod.setStatus(status);
				this.lekaniProductDao.update(prod);
			}
		}
	}
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
	
	public void loadById(Integer id){
		ProductModel prod = LKNService.getProdInfo(id, null);
		if(prod!=null){
			ArrayList<ProductModel> list = new ArrayList();
			list.add(prod);
			saveOrUpdateProdList(list);
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
			}catch(NonUniqueObjectException e){
				log.error(e.getMessage());
				try{
					this.lekaniProductDao.merge(p);
				}catch(Exception e1){
					log.error(e1.getMessage());
				}
			}
			i++;
			log.info( i+"----"+pID );
		}
	}
	public Page pageListByCriteria(int currentPage,int pageSize,ProductModel entity){
		return this.lekaniProductDao.pageListByCriteria(currentPage, pageSize, entity);
	}
	
	public Map<String,String> getBrandListByCategoryID(String categoryID){
		Map<String,String> result= new HashMap<String,String>();
		List<String> list = this.lekaniProductDao.getBrandListByCategoryID(categoryID);
		
		for(String s:list){
			if(brandMap.containsKey(Integer.valueOf(s))){
				result.put(s, brandMap.get(Integer.valueOf(s)));
			}
		}
		return result;
	}
	
	public void saveAmazonJewelryFromLekaniProds(String[] prodIDs){
		if(prodIDs!=null && prodIDs.length>0){
			for(String prodID: prodIDs){
				ProductModel pm = this.getProductModelByID(prodID.trim());
				if(!"converted".equals(pm.getStatus())){
					JewelryEntity jew = this.convert2AmazonJewelry(pm);
					this.amazonJewelryDao.saveOrUpdate(jew);
				}else{
					log.warn(prodID.trim()+" was already converted !");
				}
			}
			this.lekaniProductDao.bathUpdateStatus(prodIDs, "converted");
		}
	}
	
	public void getLatestStockForLekaniProds(String[] prodIDs){
		if(prodIDs!=null && prodIDs.length>0){
			List<ProductModel> list = new ArrayList<ProductModel>();
			for(String prodID: prodIDs){
				ProductModel pm = this.getProductModelByID(prodID.trim());
				if(pm!=null){
					list.add(pm);
				}
			}
			this.update2LatestStock(list);
		}
	}
	
	public JewelryEntity convert2AmazonJewelry(ProductModel pm){
		if(pm==null){
			return null;
		}
		JewelryEntity jew = new JewelryEntity();
		this.setTitle(jew, pm);
		this.mappingByCategory(jew, pm);
		this.setImages(jew, pm);
		this.setAttributes(jew, pm);
		this.setPrice(jew, pm);
		this.setMetalType(jew, pm);
		jew.setDepartmentName("womens");//TODO girls ???
		jew.setUpdateDate(new Date());
		jew.setPurchaseUrl1("http://www.pfhoo.com/p/"+pm.getProductID()+".html");
		
		JewelryMappingUtil.enrichDefaultValue(jew);
		return jew;
	}
	
	private void setMetalType(JewelryEntity e,ProductModel pm){
//		e.setWebsiteShippingWeight(pm.getWeight());
//		e.setWebsiteShippingWeightUnitOfMeasure("g");
		List<AttributeModel> list = pm.getAttributes();
		if(list!=null){
			for(AttributeModel a : list){
				if(a.getAttrName()!=null && a.getAttrName().indexOf("Metals Type")>-1){
					e.setMetalType(a.getAttrValue());
					break;
				}
			}
		}
	}
	
	private void setPrice(JewelryEntity jew,ProductModel pm){
		Double purchagePrice = Double.valueOf(pm.getPrice());
		double totalSalePrice = (purchagePrice + AppConstant.ShippingFeePay + AppConstant.profit ) / AppConstant.USDRate / 0.8;
		double salePrice = totalSalePrice - AppConstant.ShippingFeeEarnPerItem - AppConstant.ShippingFeeEarnPerShip;
		jew.setStandardPrice(DigitalFormatUtil.getFormatedDouble(salePrice, 2));
		jew.setListPrice(DigitalFormatUtil.getFormatedDouble(salePrice/0.2, 2));
		jew.setPurchasePrice(purchagePrice);
	}
	
	private void setTitle(JewelryEntity jew,ProductModel pm){
		String title = pm.getName();
		String token=" ";
		String[] ts = title.split(token);
		if(ts.length<2){
			jew.setItemName(title);
		}else{
			StringBuffer buf = new StringBuffer(pm.getCatName() +token);
			for(int i=1;i<ts.length;i++){
				if(ts[i].trim().length()>0){
					buf.append(ts[i].substring(0, 1).toUpperCase());
					if(ts[i].trim().length()>1){
						buf.append(ts[i].substring(1, ts[i].length()));
					}
					buf.append(token);
				}
			}
			title = buf.toString().trim();
			title = title.replace("Free Shipping", "");
			title = title.replace("18K", "");
			title = title.replace("18k", "");
			jew.setItemName(title);
		}
	}
	
	private void setAttributes(JewelryEntity jew,ProductModel pm){
		jew.setBulletPoint1("Fashion and charming design to make you stand out from others in all kinds of party or gathering");
		jew.setBulletPoint2("Perfect gift for many occasions that will surely make a memorable impression");
		jew.setBulletPoint3("Please refer to the picture detail for jewelry size,and make sure it fit for you size");
		List<AttributeModel> attributes = pm.getAttributes();
		if(attributes!=null){
			int index=4;
			StringBuffer buf = new StringBuffer();
			for(AttributeModel a : attributes){
				buf.append(a.getAttrName()+":");
				buf.append(a.getAttrValue()+";");
				if(buf.length()>=140){
					if(4==index){
						jew.setBulletPoint4(buf.toString());
					}else if(5==index){
						jew.setBulletPoint5(buf.toString());
						break;
					}
					index++;
					buf.delete(0, buf.length());
				}
			}
			
			if(index <=5 && buf.length()<140){
				if(4==index){
					jew.setBulletPoint4(buf.toString());
				}else if(5==index){
					jew.setBulletPoint5(buf.toString());
				}
			}
		}
	}
	
	private void setImages(JewelryEntity jew,ProductModel pm){
		String[] images = pm.getImages().split(",");
		jew.setMainImageUrl(images[0]);
		if(images.length>1){
			jew.setOtherImageUrl1(images[1]);
		}
		if(images.length>2){
			jew.setOtherImageUrl2(images[2]);
		}
		if(images.length>3){
			jew.setOtherImageUrl3(images[3]);
		}
		if(images.length>4){
			jew.setOtherImageUrl4(images[4]);
		}
		if(images.length>5){
			jew.setOtherImageUrl5(images[5]);
		}
		if(images.length>6){
			jew.setOtherImageUrl6(images[6]);
		}
		if(images.length>7){
			jew.setOtherImageUrl7(images[7]);
		}
		if(images.length>8){
			jew.setOtherImageUrl8(images[8]);
		}
	}
	/**
	 * category name list:
		"Dangle Earrings"
		"Stud Earrings"
		"Clip Earrings"
		"Hoop Earrings"

		"Long Necklaces"
		"Necklace&Pendants"
		"Necklaces"
		"Chain Nceklace"
		"Pendants"

		"Bracelet"
		
		"Ring"

		"Anklets"
		
		"Bangle&Cuff"
		
		"Jewelry Set"
	 * @param jew
	 * @param productModel
	 */
	private void mappingByCategory(JewelryEntity jew,ProductModel productModel){
		String itemType = null;//项链/吊坠 手链 耳环/耳坠 戒指
		String sku = null;
		if(productModel.getCatName().indexOf("Earrings")>-1){
			sku ="ER";
			itemType = "earrings";
		}else if(productModel.getCatName().indexOf("Necklace")>-1
				||productModel.getCatName().indexOf("Nceklace")>-1
				||productModel.getCatName().indexOf("Pendant")>-1){
			sku ="NP";
			itemType = "pendant-necklaces";
		}else if(productModel.getCatName().indexOf("Bracelet")>-1){
			sku ="BL";
			itemType = "link-bracelets";
		}else if(productModel.getCatName().indexOf("Ring")>-1){
			sku ="RI";
			itemType = "rings";
		}else if(productModel.getCatName().indexOf("Bangle&Cuff")>-1){
			sku ="BC";
//			itemType = "xxxxxxx";
		}else if(productModel.getCatName().indexOf("Anklets")>-1){
			sku ="AL";
//			itemType = "xxxxxxx";
		}else if(productModel.getCatName().indexOf("Jewelry Set")>-1){
			sku ="JS";
//			itemType = "xxxxxxx";
		}else {
			sku ="OT";
//			itemType = "xxxxxxx";
		}
		sku = sku +"-"+ productModel.getProductID()+"-LKN";
		jew.setItemSku(sku);
		jew.setItemType(itemType);
		jew.setFeedProductType(JewelryMappingUtil.getFeedProductTypeByItemType(itemType));
	}
	
	public void bathUpdateStatus(String[] prodIDs,String status){
		this.lekaniProductDao.bathUpdateStatus(prodIDs, status);
	}
	
	public List<ProductModel> listAll(){
		return this.lekaniProductDao.listAll();
	}
	
	public void update2LatestStock(List<ProductModel> list){
		if(list==null || list.isEmpty()){
			return ;
		}
		
		for(ProductModel p : list){
			Integer latestStock = LKNService.getStock(p.getSKU());
			Integer previousStock = p.getStock();
			if(latestStock!= null && latestStock != previousStock ){
				this.log.warn(" LKN prod id = ["+p.getProductID() +"] latest stock is "+latestStock+", the old value = "+p.getStock());
				p.setStock(latestStock);
				p.setStockPrevious(previousStock);
				this.lekaniProductDao.save(p);
			}
		}
	}
}
