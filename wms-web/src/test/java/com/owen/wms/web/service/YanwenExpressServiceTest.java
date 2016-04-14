package com.owen.wms.web.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.owen.wms.common.constant.AppConstant;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.form.ExpressScanForm;
import com.owen.wms.web.form.YanwenExpress;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@Transactional
public class YanwenExpressServiceTest {

	@Autowired
	private YanwenExpressService service;
	
	@Test
	public void test() throws Exception{
		YanwenExpress express  = new YanwenExpress();
		express.setAmazonOrderID("111-2651851-0898603");
		CreateExpressResponseType result = this.service.createExpress( express  );
		this.service.downloadPrintExpress(result, AppConstant.defaultPdfDownloadPath, express.getAmazonOrderID());
	}
	
	@Test
	public void testScan(){
		ExpressScanForm expressForm = new ExpressScanForm();
		expressForm.setExpressNumber("LS917674814CN");
		this.service.scanByExpressNumber(expressForm );
		Set<AmazonOrderItem> orderItemList = expressForm.getOrderItemSet();
		if(orderItemList!=null){
			for(AmazonOrderItem item: orderItemList){
				JewelryEntity prod = item.getSellerSKU();
				System.out.println(prod.getLocalImagePath());
			}
		}
	}
}
