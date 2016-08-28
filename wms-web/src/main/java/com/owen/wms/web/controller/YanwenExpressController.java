package com.owen.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazonaws.mws.entity.yanwen.Receiver;
import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.owen.wms.common.constant.AppConstant;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.entity.YanWenExpressEntity;
import com.owen.wms.web.form.ExpressQueryForm;
import com.owen.wms.web.form.ExpressScanForm;
import com.owen.wms.web.form.YanwenExpress;
import com.owen.wms.web.music.NumberPlayer;
import com.owen.wms.web.service.AmazonOrderService;
import com.owen.wms.web.service.YanwenExpressService;
import com.owen.wms.web.utils.DigitalFormatUtil;

@Controller
@RequestMapping("/yanwen")
public class YanwenExpressController {
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int defaultPageSize = 20;
	
	@Autowired
	private YanwenExpressService service;
	@Autowired
	@Qualifier("amazonOrderService")
	private AmazonOrderService amazonOrderService ;

	@RequestMapping(value="/expressDetail", method = RequestMethod.GET)
	public String getExpressDetailByOrderID(Model model,HttpServletRequest request) throws Exception {
		String orderID =request.getParameter("orderID");
		YanWenExpressEntity expressEntity = this.service.getByOrderID(orderID);
		model.addAttribute("expressEntity", expressEntity);
		model.addAttribute("currentMenu", "express");
		return "express/expressDetail";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listAll(Model model,HttpServletRequest request) throws Exception {
		ExpressQueryForm expressQueryForm = new ExpressQueryForm();
		expressQueryForm.setCurrentPage(1);
		expressQueryForm.setPageSize(defaultPageSize);
		
		
		Page page = this.service.pageQuery(expressQueryForm);
		model.addAttribute("page", page);
		model.addAttribute("expressQueryForm", expressQueryForm);
		model.addAttribute("currentMenu", "express");
		return "express/expressList";
	}
	
	//扫描快递信息
	@RequestMapping(value="/scan", method = RequestMethod.GET)
	public String scan(Model model,HttpServletRequest request) throws Exception {
		ExpressScanForm expressScanForm = new ExpressScanForm();
		model.addAttribute("expressScanForm", expressScanForm);
		model.addAttribute("currentMenu", "express");
		return "express/scan";
	}
	
	@RequestMapping(value="/scan", method = RequestMethod.POST)
	public String scanPost(Model model,HttpServletRequest request,@ModelAttribute("expressScanForm") ExpressScanForm expressScanForm) throws Exception {
		if(expressScanForm.getExpressNumber()!=null && expressScanForm.getExpressNumber().trim().length()>0){
			expressScanForm.setPreviousExpressNumber(expressScanForm.getExpressNumber());
			NumberPlayer play = new NumberPlayer();
			if(expressScanForm.getExpressNumber().length()>9){
				play.playNumber(expressScanForm.getExpressNumber().substring(9));
			}else{
				play.playNumber(expressScanForm.getExpressNumber());
			}
			expressScanForm.setOrder(null);
			expressScanForm.setOrderItemSet(null);
			this.service.scanByExpressNumber(expressScanForm);
			expressScanForm.setExpressNumber(null);
		}
		model.addAttribute("currentMenu", "express");
		return "express/scan";
	}
	
	//扫描确认发货
	@RequestMapping(value="/scanConfirmDeliver", method = RequestMethod.GET)
	public String scanDeliver(Model model,HttpServletRequest request) throws Exception {
		ExpressScanForm expressScanForm = new ExpressScanForm();
		model.addAttribute("expressScanForm", expressScanForm);
		model.addAttribute("currentMenu", "express");
		return "express/scanConfirmDeliver";
	}
	
	@RequestMapping(value="/scanConfirmDeliver", method = RequestMethod.POST)
	public String scanDeliverPost(Model model,HttpServletRequest request,@ModelAttribute("expressScanForm") ExpressScanForm expressScanForm) throws Exception {
		if(expressScanForm.getExpressNumber()!=null && expressScanForm.getExpressNumber().trim().length()>0){
			expressScanForm.setPreviousExpressNumber(expressScanForm.getExpressNumber());
			NumberPlayer play = new NumberPlayer();
			if(expressScanForm.getExpressNumber().length()>8){
				play.playNumber(expressScanForm.getExpressNumber().substring(8));
			}else{
				play.playNumber(expressScanForm.getExpressNumber());
			}
			expressScanForm.setOrder(null);
			expressScanForm.setOrderItemSet(null);
			this.service.scanExpressToConfirmDeliver(expressScanForm);
			if(expressScanForm.getOrder()!=null){
				model.addAttribute("message", expressScanForm.getExpressNumber()+"扫描发货成功！");
			}
			expressScanForm.setExpressNumber(null);
		}
		model.addAttribute("currentMenu", "express");
		return "express/scanConfirmDeliver";
	}

	@RequestMapping(value="/pageQuery", method = RequestMethod.POST)
	public String pageQuery(Model model,@ModelAttribute("expressQueryForm") ExpressQueryForm expressQueryForm) throws Exception {
		Page page = this.service.pageQuery(expressQueryForm);
		model.addAttribute("page", page);
		model.addAttribute("expressQueryForm", expressQueryForm);
		model.addAttribute("currentMenu", "express");
		return "express/expressList";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String preCreateExpress(Model model,HttpServletRequest request) {
		String amazonOrderID = request.getParameter("amazonOrderID");
		YanwenExpress express = new YanwenExpress();
		express.setAmazonOrderID(amazonOrderID);
		express.setDeclaredCurrency("USD");
		express.setQuantity(1);
		express.setDeclaredValue(9);
		express.setWeight(50);
		express.setSendDate(sdf.format(new Date()));
		express.setDownloadPath(AppConstant.defaultPdfDownloadPath);
		express.setCountry("美国");
		
		AmazonOrder order = this.amazonOrderService.getByOrderIDWithoutExpress(amazonOrderID);
		if(order!=null){
			Set<AmazonOrderItem> orderItems = order.getOrderItemList();
			if(orderItems.size()>0){
				express.setQuantity(orderItems.size());
				int i =0;
				AmazonOrderItem[] orderItemArray = orderItems.toArray(new AmazonOrderItem[]{});
				AmazonOrderItem firstOrderItem = orderItemArray[0];
				while( firstOrderItem.getQuantityOrdered() < 1 && i<orderItems.size()-1 ){
					i++;
					firstOrderItem = orderItemArray[i];
				}
				JewelryEntity prod = firstOrderItem.getSellerSKU();
				if(prod!=null){
					String itemType = prod.getItemType();
					if(itemType!=null){
						itemType = itemType.toLowerCase();
						switch (itemType){
							case "pendant-necklaces":{
								express.setNameChinese("时尚简约饰品-项链吊坠");
								express.setNameEnglish("Fashion Jewelry Accessories-Necklace Pendant");
								break;
							}
							case "link-bracelets":{
								express.setNameChinese("时尚简约饰品-手链手环");
								express.setNameEnglish("Fashion Jewelry Accessories-Bracelets");
								break;
							}
							case "rings":{
								express.setNameChinese("时尚简约饰品-指环戒指");
								express.setNameEnglish("Fashion Jewelry Accessories-Rings");
								break;
							}
							case "earrings":{
								express.setNameChinese("时尚简约饰品-耳环耳坠");
								express.setNameEnglish("Fashion Jewelry Accessories-Earrings");
								break;
							}
							case "anklets":{
								express.setNameChinese("时尚简约饰品-脚踝链");
								express.setNameEnglish("Fashion Jewelry Accessories-Anklets");
								break;
							}
							case "dresses":{
								express.setNameChinese("时尚服饰-连衣裙");
								express.setNameEnglish("Fashion Clothes Dress");
								break;
							}
							case "shorts":{
								express.setNameChinese("时尚服饰-短裤");
								express.setNameEnglish("Fashion Clothes Shorts");
								break;
							}
							case "music-fan-t-shirts":{
								express.setNameChinese("时尚服饰-T恤");
								express.setNameEnglish("Fashion Clothes T Shirts");
								break;
							}
							case "sunglasses":{
								express.setNameChinese("太阳镜");
								express.setNameEnglish("sunglasses");
								break;
							}
							case "wallets":{
								express.setNameChinese("女士零钱包");
								express.setNameEnglish("Fashion Lady Purse");
								break;
							}
							case "fashion-hoodies":{
								express.setNameChinese("时尚卫衣");
								express.setNameEnglish("ashion Hoodies");
								break;
							}
							default:{
								express.setNameChinese(null);
								express.setNameEnglish(itemType);
								break;
							}
						}
					}
				}
			}
		}
		
		Receiver rc = new Receiver();
		express.setReceiver(rc );
		rc.setUserid(AppConstant.yanwenUserId);
		rc.setName(order.getShippingAddressName());
		rc.setPhone(order.getShippingAddressPhone());
		rc.setCountry(express.getCountry()); 
		rc.setState(order.getShippingAddressStateOrRegion());
		rc.setCity(order.getShippingAddressCity());
		rc.setAddress1(order.getShippingAddressAddressLine1());
		rc.setAddress2(order.getShippingAddressAddressLine2());
		rc.setPostcode(order.getShippingAddressPostalCode());
		model.addAttribute("express", express);
		model.addAttribute("currentMenu", "express");
		
		Set<AmazonOrderItem> list = order.getOrderItemList();
		double sum=0;
		for(AmazonOrderItem i:list){
			if(i.getItemPriceAmount()!=null){
				sum += i.getItemPriceAmount();
			}
		}
		if(sum==0){
			express.setDeclaredValue(9);
		}else if(sum>10 && sum <=20 ){
			express.setDeclaredValue(DigitalFormatUtil.getFormatedDouble(sum/2, 2));
		}else if(sum>20 && sum <=50 ){
			express.setDeclaredValue(DigitalFormatUtil.getFormatedDouble(sum/3, 2));
		}else if(sum>50 && sum <=100 ){
			express.setDeclaredValue(DigitalFormatUtil.getFormatedDouble(sum/3, 2));
		}else{
			express.setDeclaredValue(DigitalFormatUtil.getFormatedDouble(2*sum/3, 2));
		}
		express.setQuantity(list.size());
		return "createYanwenExpress";
	}

	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createExpress(@ModelAttribute("express") YanwenExpress express,Model model) throws Exception {
		CreateExpressResponseType result = null;
		if("remoteAmz".equals(express.getMethodToGetOrder())){
			result = this.service.createExpressFromAmazonOrderWMS(express);
		}else{
			result = this.service.createExpress( express);
			this.service.downloadPrintExpress(result, express.getDownloadPath(), express.getAmazonOrderID());
		}
		String orderId = express.getAmazonOrderID();
		if(result!=null){
			if(result.isCallSuccess()){
				express.setAmazonOrderID(null);
//				express.setChannel(null);
//				express.setNameChinese(null);
				model.addAttribute("createSuccessIndicator","订单["+ orderId+"] 快递单创建成功！快递单号 ：" +result.getResp().getEpcode());
			}else{
				model.addAttribute("createSuccessIndicator", "快递单创建失败： "+result.getResp().getReasonMessage());
			}
		}else{
			model.addAttribute("createSuccessIndicator", "快递单创建失败：无法获取亚马逊订单号【"+express.getAmazonOrderID()+"】信息");
		}
		model.addAttribute("currentMenu", "express");
		return "createYanwenExpress";
	}
}