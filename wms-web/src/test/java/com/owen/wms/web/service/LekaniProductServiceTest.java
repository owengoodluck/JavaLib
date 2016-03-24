package com.owen.wms.web.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.owen.wms.lekani.entity.ProductModel;
import com.owen.wms.lekani.service.LKNService;
import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.entity.JewelryEntity;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class LekaniProductServiceTest {

	
	@Autowired
	@Qualifier("amazonJewelryDao")
	private AmazonJewelryDao amazonJewelryDao;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private Map<Integer,String> categoryMap = new HashMap<Integer,String>();
	private Map<Integer,String> brandMap = new HashMap<Integer,String>();
	{
//		brandMap.put(29,"潘多拉系列");
//		brandMap.put(37,"钛钢系列");
//		brandMap.put(27,"婚饰系列");
//		brandMap.put(13,"古玛雅");
//		brandMap.put(17,"香芭拉");
//		brandMap.put(21,"时尚K金产品");
//		brandMap.put(12,"珍姿美");
//		brandMap.put(28,"民族风系列");
//		brandMap.put(25,"夸张大牌产品");
//		brandMap.put(11,"依娜丽饰");
//		brandMap.put(32,"威妮华");
//		brandMap.put(38,"包包系列");
//		brandMap.put(33,"天然石系列");
//		brandMap.put(18,"品牌A");
//		brandMap.put(10,"又一银");
//		brandMap.put(20,"K金锆石类");
//		brandMap.put(31,"爆款品类");
//		brandMap.put(30,"韩风");
//		brandMap.put(14,"法伯丽");
//		brandMap.put(22,"时尚银饰产品");
//		brandMap.put(19,"品牌B");
//		brandMap.put(99,"其它");
		
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
//		categoryMap.put(54,"包装&展件");
//		categoryMap.put(249,"双肩背包");
//		categoryMap.put(225,"钥匙扣");
//		categoryMap.put(226,"钱夹");
//		categoryMap.put(245,"太阳镜");
//		categoryMap.put(247,"阅读镜");
//		categoryMap.put(248,"眼镜盒");
//		categoryMap.put(243,"手提/单肩/斜跨包");
//		categoryMap.put(242,"钱包");
//		categoryMap.put(241,"时尚包包");
//		categoryMap.put(221,"领带夹&袖扣");
	}
	@Autowired
	@Qualifier("lekaniProductService")
	private LekaniProductService lekaniProductService;
	
	@Test
	public void testLoadProd() throws Exception {
		int categoryID = 216;
		int brandID = 10;
		lekaniProductService.loadProdByCategoryAndBrand(categoryID, brandID);
		
//		boolean resp = LKNService.getIsOnSale( sku);
	}
	
	@Test
	public void loadAll(){
		Set<Integer> categoryIDset = this.categoryMap.keySet();
		Set<Integer> brandIDSet = this.brandMap.keySet();
		for(int categroyID : categoryIDset){
			for(int brandID:brandIDSet){
				lekaniProductService.loadProdByCategoryAndBrand(categroyID, brandID);
			}
		}
	}
	@Test
	public void loadByID(){
		Integer id = 116767;
		this.lekaniProductService.loadById(id);
	}

	
	@Test
	public void testConvet(){
		String prodID = "100700";
		ProductModel pm = lekaniProductService.getProductModelByID(prodID.trim());
		JewelryEntity jew = this.lekaniProductService.convert2AmazonJewelry(pm);
		System.out.println(jew.getItemName());
		System.out.println(jew.getBulletPoint4());
		System.out.println(jew.getBulletPoint5());
		System.out.println(jew.getStandardPrice());
		System.out.println(jew.getListPrice());
		
		this.amazonJewelryDao.saveOrUpdate(jew);
	}
	
	@Test
	public void updateStock(){
		List<ProductModel> list = this.lekaniProductService.listAll();
		this.lekaniProductService.update2LatestStock(list);
	}
}
