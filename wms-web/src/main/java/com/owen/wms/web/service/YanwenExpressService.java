package com.owen.wms.web.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.mws.entity.yanwen.ExpressType;
import com.amazonaws.mws.entity.yanwen.GoodsName;
import com.amazonaws.mws.entity.yanwen.Receiver;
import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.amazonaws.mws.service.YanwenService;
import com.amazonaws.mws.util.JaxbUtil;
import com.amazonservices.mws.orders._2013_09_01.model.Address;
import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResponse;
import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResult;
import com.amazonservices.mws.orders._2013_09_01.model.Order;
import com.amazonservices.mws.orders._2013_09_01.service.GetOrderService;
import com.owen.wms.common.constant.AppConstant;
import com.owen.wms.web.constants.AmazonOrderStatus;
import com.owen.wms.web.constants.WMSConstants;
import com.owen.wms.web.dao.AmazonJewelryDao;
import com.owen.wms.web.dao.AmazonOrderDao;
import com.owen.wms.web.dao.AmazonOrderItemDao;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.dao.YanWenExpressDao;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.entity.YanWenExpressEntity;
import com.owen.wms.web.form.ExpressQueryForm;
import com.owen.wms.web.form.ExpressScanForm;
import com.owen.wms.web.form.YanwenExpress;
import com.owen.wms.web.utils.DigitalFormatUtil;
import com.owen.wms.web.utils.ExcelUtil;
import com.owen.wms.web.utils.PdfPrintThread;
import com.owen.wms.web.utils.PdfPrintUtil;

@Service
@Transactional
public class YanwenExpressService {

	private Logger expressLogger = Logger.getLogger(YanwenExpressService.class);
	
	@Value("${yanwen.express.pdf.is.print}")
	private boolean print;
	@Value("${express.number.log.folder}")
	private String expressNumberLogFolder;
	private Logger log = Logger.getLogger(this.getClass());
	private YanwenService yanwenService = new YanwenService();
	private GetOrderService getOrderService = new GetOrderService();
	private SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private SimpleDateFormat sdfYYYYMMDD  =new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	@Qualifier("amazonOrderDao")
	private AmazonOrderDao amazonOrderDao;
	
	@Autowired
	@Qualifier("amazonOrderItemDao")
	private AmazonOrderItemDao orderItemDao;	
	
	@Autowired
	@Qualifier("amazonJewelryDao")
	private AmazonJewelryDao amazonJewelryDao;

	@Autowired
	@Qualifier("yanWenExpressDao")
	private YanWenExpressDao yanWenExpressDao;
	
	public Map<String, Object> statisticExpress(Date start ,Date end){
		return this.yanWenExpressDao.statisticExpress(start, end);
	}
	
	public YanWenExpressEntity getByOrderID(String orderID){
		YanWenExpressEntity ent =this.yanWenExpressDao.getByAmazonOrderId(orderID);
		return ent;
	}
	
	public YanWenExpressEntity getByID(String expressNumber){
		YanWenExpressEntity ent =this.yanWenExpressDao.get(expressNumber);
		return ent;
	}
	
	public void update(YanWenExpressEntity entity ){
		this.yanWenExpressDao.update(entity);
	}
	/**
	 * page query
	 * @param queryForm
	 * @return
	 * @throws Exception
	 */
	public Page pageQuery(ExpressQueryForm queryForm) throws Exception{
		return this.yanWenExpressDao.pageListByCriteria(queryForm);
	}
	
	/**
	 * load bill excel file
	 * @param billExcelFile
	 */
	public List<String[]> loadBill(File billExcelFile){
		if(billExcelFile == null){
			throw new RuntimeException("input file is null");
		}
		List<String[]> list = ExcelUtil.readExcel(billExcelFile,0,16,0);
		 Map<String,Integer> headerMap = this.parseHeader(list.get(0));
		if(list!=null && list.size()>0){
			for(int i=1;i<list.size()-1;i++){//start from line 1
				String[] row = list.get(i);
				String expressNumber = row[headerMap.get("快递单号")];
				String orderNumber = row[headerMap.get("订单号")];
				String country = row[headerMap.get("目的地")];
				String weight = row[headerMap.get("重量")];
				String sendDate = row[headerMap.get("快递单日期")];
				String channel = null;
				if(headerMap.containsKey("产品名称")){
					channel = row[headerMap.get("产品名称")];
				}else if(headerMap.containsKey("渠道名称")){
					channel = row[headerMap.get("渠道名称")];
				}
				String feeTotal = row[headerMap.get("汇总金额")];
				
				if(expressNumber!=null && expressNumber.trim().length()>0){
					YanWenExpressEntity express = this.yanWenExpressDao.get(expressNumber);
					if(express==null){
						express = new YanWenExpressEntity();
						express.setEpcode(expressNumber);
					}
					try {
						express.setSendDate(sdfYYYYMMDD.parse(sendDate));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					//set value
					if(weight!=null){
						express.setWeightByYanwen(Integer.valueOf(weight));
					}
					if(feeTotal!=null){
						express.setFeeTotal(Double.valueOf(feeTotal));
					}

					if(channel!=null){
						express.setChannel(channel);
					}
					if(country!=null){
						express.setCountry(country);
					}
					if(orderNumber!=null){
						express.setUserOrderNumber(orderNumber);
					}
					//save or update
					this.yanWenExpressDao.saveOrUpdate(express);
				}else{
					break;
				}
			}
		}
		return list;
	}
	
	//0			1		2
	//快递单日期	快递单号	订单号	转单号	参考号	目的地	重量	产品名称	资费	折后资费	附加费	汇总金额
	//2016-10-15	LT287515886CN	105-0619207-7864203	LT287515886CN	LT287515886CN	美国	40	中邮北京线下E邮宝	13.0000	11.7000	0.0000	11.7000
	private Map<String,Integer> parseHeader(String[] header){
		Map<String,Integer> map = new HashMap();
		if(header == null || header.length<1){
			return map;
		}
		for(int i =0;i<header.length;i++){
			String value = header[i].trim();
			if("快递单日期".equals(value) || "快递单号".equals(value) || "转单号".equals(value)|| "参考号".equals(value)
					|| "订单号".equals(value)|| "目的地".equals(value) || "渠道名称".equals(value)
					|| "重量".equals(value)|| "产品名称".equals(value)|| "资费".equals(value)|| "折后资费".equals(value)
					|| "附加费".equals(value)
					|| "汇总金额".equals(value)){
				map.put(value, i);
			}
		}
		
		return map;
	}
	
	
	public void loadYanwenBill(File folder){
		if(folder ==null){
			this.log.warn("Folder is null");
		}else if(folder.isFile()){
			this.log.info(folder.getAbsolutePath()+" is not a directory !");
		}else{
			File[] files = folder.listFiles();
			for(File f : files){
				if(f.isFile() && f.getName().endsWith(".xls")){
					this.loadBill(f);
				}else{
					this.log.warn(f.getAbsolutePath() +" is not bill file");
				}
			}
		}
	}
	
	public void scanByExpressNumber(ExpressScanForm expressScanForm){
		YanWenExpressEntity express = this.yanWenExpressDao.getByID(expressScanForm.getExpressNumber().trim());
		if(express != null){
			expressScanForm.setExpress(express);
			AmazonOrder order = this.amazonOrderDao.getByOrderID(express.getUserOrderNumber());
			if(order!=null){
				expressScanForm.setOrder(order);
				Set<AmazonOrderItem> orderItemList = order.getOrderItemList();
				if(orderItemList!=null){
					expressScanForm.setOrderItemSet(orderItemList);
					for(AmazonOrderItem item: orderItemList){
						JewelryEntity prod = item.getSellerSKU();
					}
				}
			}
		}
	}
	
	public void scanExpressToConfirmDeliver(ExpressScanForm expressScanForm){
		String expressNumber = expressScanForm.getExpressNumber().trim();
		
		YanWenExpressEntity express = this.yanWenExpressDao.getByID(expressNumber);
		if(express != null){
			expressScanForm.setExpress(express);
			AmazonOrder order = this.amazonOrderDao.getByOrderID(express.getUserOrderNumber());
			if(order!=null){
				expressScanForm.setOrder(order);
				Set<AmazonOrderItem> orderItemList = order.getOrderItemList();
				if(orderItemList!=null){
					expressScanForm.setOrderItemSet(orderItemList);
					for(AmazonOrderItem item: orderItemList){
						JewelryEntity prod = item.getSellerSKU();
					}
				}
			}
			express.setScanedConfirmedDeliver(true);
			this.yanWenExpressDao.update(express);
		}else if(expressNumber.length()==13 || expressNumber.length() ==11 ){//LS459138813CN // LT587794923CN //11738272055
			YanWenExpressEntity expressNew = new YanWenExpressEntity();
			expressNew.setEpcode(expressNumber);
			expressNew.setScanedConfirmedDeliver(true);
			expressNew.setName("HaiFang");
			expressNew.setSendDate(new Date());
			this.yanWenExpressDao.save(expressNew);
			expressScanForm.setExpress(expressNew);
		}else{
			//record express number
			this.expressLogger.info(expressScanForm.getExpressNumber().trim());
		}
			
	}
	/**
	 * list all
	 * @return
	 */
	public List<YanWenExpressEntity> listAll(){
		return this.yanWenExpressDao.listAll();
	}
	
	/**
	 * 
	 * @param express
	 * @return
	 * @throws Exception
	 */
	public CreateExpressResponseType createExpress(YanwenExpress express) throws Exception {
		//1. get Amazon order info
		AmazonOrder orderEntity = this.amazonOrderDao.get(express.getAmazonOrderID().trim());
		CreateExpressResponseType result = null;
		
		ExpressType et = this.convert(express);
		//2.create Yanwen express
		result = this.yanwenService.createExpress(et);
		
		if (orderEntity!=null) {
			express.setAmazonOrder(orderEntity);
			
			if(result.isCallSuccess()){
				String epCode=result.getCreatedExpress().getEpcode();
				et.setEpcode(epCode);
				
				//1. save to DB
				YanWenExpressEntity entity = this.convert2Entity(et);
				this.yanWenExpressDao.saveOrUpdate(entity );
				
				//2 . update order print status
				orderEntity.setIsPrinted(true);
				this.amazonOrderDao.update(orderEntity);
				
				//3. update product stock
				if(express.getSequenceNo()==null || express.getSequenceNo().trim().length()<1){//update only once
					this.updateStock(orderEntity);
				}
			}else{
				this.log.error("Create Express fail with error :"+result.getResp().getReasonMessage());
			}
		}else{
			this.log.error("Not related order:"+express.getAmazonOrderID().trim());
		}
		return result;
	}
	
	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public void downloadPrintExpress(CreateExpressResponseType result,String pdfDownloadPath,String amzOrderID) throws Exception {
		if(result !=null && result.isCallSuccess()){
			String epCode=result.getCreatedExpress().getEpcode();
			//down load pdf to local
			String pdfFilePath = this.yanwenService.downloadLabel(epCode, pdfDownloadPath,amzOrderID);
			//print pdf label
			if(print){
				PdfPrintThread pdfPrintThread = new PdfPrintThread(pdfFilePath);
				pdfPrintThread.start();
			}
			
		}else{
			this.log.error("Fail to create Yanwen express:"+result.getResp().getReason()+result.getResp().getReasonMessage());
		}
	}
	
	private void updateStock(AmazonOrder orderEntity){
		if(orderEntity ==null 
				|| orderEntity.getOrderStatus() == AmazonOrderStatus.Canceled.toString() 
				|| orderEntity.getOrderStatus() == AmazonOrderStatus.Pending.toString()){
			return ;
		}
		Set<AmazonOrderItem> orderItems = orderEntity.getOrderItemList();
		if(orderItems != null && !orderItems.isEmpty()){
			for(AmazonOrderItem item:orderItems){
				JewelryEntity jewelryEntity = this.amazonJewelryDao.load(item.getSellerSKU().getItemSku());
				int quantity = item.getQuantityOrdered() == 0 ? item.getQuantityShipped() : item.getQuantityOrdered();//TODO pay attention 
				if(jewelryEntity.getStockQuantity() == null){
					jewelryEntity.setStockQuantity( 0 - quantity );
				}else{
					jewelryEntity.setStockQuantity( jewelryEntity.getStockQuantity() - quantity );
				}
				this.amazonJewelryDao.update(jewelryEntity);
			}
		}
	}
	
	private ExpressType convert(YanwenExpress form) {
		ExpressType et = new ExpressType();
		et.setUserid(AppConstant.yanwenUserId);
		et.setSendDate(form.getSendDate()+"T00:00:00");// 2015-07-09T00:00:00
		et.setQuantity(form.getQuantity());
		et.setChannel(form.getChannel());//中文 ， 中邮北京平邮小包
		String userOrderNumber = form.getAmazonOrderID();
		if(form.getSequenceNo()!=null && form.getSequenceNo().trim().length()>0){
			userOrderNumber+="_"+form.getSequenceNo();
		}
		et.setUserOrderNumber(userOrderNumber);

		Receiver rc = form.getReceiver();
		rc.setUserid(AppConstant.yanwenUserId);
		et.setReceiver(rc);

		GoodsName gn = new GoodsName();
		et.setGoodsName(gn);
		gn.setUserid(AppConstant.yanwenUserId);
		gn.setNameCh(form.getNameChinese());
		gn.setNameEn(form.getNameEnglish());
		gn.setDeclaredValue(form.getDeclaredValue());
		gn.setDeclaredCurrency(form.getDeclaredCurrency());
		gn.setWeight(form.getWeight());

		this.log.info(JaxbUtil.toXml(et));
		return et;
	}
	private ExpressType convert(AmazonOrder orderEntity, YanwenExpress form) {
		ExpressType et = new ExpressType();
		et.setUserid(AppConstant.yanwenUserId);
		et.setSendDate(form.getSendDate()+"T00:00:00");// 2015-07-09T00:00:00
		et.setQuantity(form.getQuantity());
		et.setChannel(form.getChannel());//中文 ， 中邮北京平邮小包
		String userOrderNumber = orderEntity.getAmazonOrderId();
		if(form.getSequenceNo()!=null && form.getSequenceNo().trim().length()>0){
			userOrderNumber+="_"+form.getSequenceNo();
		}
		et.setUserOrderNumber(userOrderNumber);

		Receiver rc = new Receiver();
		et.setReceiver(rc);
		rc.setUserid(AppConstant.yanwenUserId);
		rc.setName(orderEntity.getShippingAddressName());
		rc.setPhone(orderEntity.getShippingAddressPhone());
		rc.setCountry(form.getCountry()); 
		rc.setState(orderEntity.getShippingAddressStateOrRegion());
		rc.setCity(orderEntity.getShippingAddressCity());
		rc.setAddress1(orderEntity.getShippingAddressAddressLine1());
		rc.setAddress2(orderEntity.getShippingAddressAddressLine2());
		rc.setPostcode(orderEntity.getShippingAddressPostalCode());

		GoodsName gn = new GoodsName();
		et.setGoodsName(gn);
		gn.setUserid(AppConstant.yanwenUserId);
		gn.setNameCh(form.getNameChinese());
		gn.setNameEn(form.getNameEnglish());
		
		Set<AmazonOrderItem> list = orderEntity.getOrderItemList();
		double sum=0;
		for(AmazonOrderItem i:list){
			sum+=i.getItemPriceAmount();
		}
		if(sum==0){
			gn.setDeclaredValue(form.getDeclaredValue());
		}else{
			gn.setDeclaredValue(sum);
		}
		et.setQuantity(list.size());//form.getQuantity()
		
		gn.setDeclaredCurrency(form.getDeclaredCurrency());
		gn.setWeight(form.getWeight());

		this.log.info(JaxbUtil.toXml(et));
		return et;
	}

	@Deprecated
	public CreateExpressResponseType createExpressFromAmazonOrderWMS(YanwenExpress form) throws Exception {
		//1. get Amazon order info
		GetOrderResponse order = getOrderService.getOrderByID(form.getAmazonOrderID().trim());
		CreateExpressResponseType result = null;
		if (order != null 
			&& order.getGetOrderResult()!=null 
			&& order.getGetOrderResult().getOrders()!=null 
			&&!order.getGetOrderResult().getOrders().isEmpty()) {
			
			ExpressType et = this.convert(order,form);
			
			//2.create Yanwen express
			result = this.yanwenService.createExpress(et);
			if(result.isCallSuccess()){
				String epCode=result.getCreatedExpress().getEpcode();
				et.setEpcode(epCode);
				
				//3. down load pdf to local
				String pdfFilePath = this.yanwenService.downloadLabel(epCode, form.getDownloadPath(),form.getAmazonOrderID().trim());
				
				//4. save to DB
				YanWenExpressEntity entity = this.convert2Entity(et);
				this.yanWenExpressDao.saveOrUpdate(entity );
				
				//5. print pdf label
				PdfPrintUtil.printViaCommandLine(pdfFilePath);
			}else{
				this.log.error("Fail to create Yanwen express:"+result.getResp().getReason()+result.getResp().getReasonMessage());
			}
		}
		
		return result;
	}

	private ExpressType convert(GetOrderResponse od,YanwenExpress form) {
		GetOrderResult orderResult = od.getGetOrderResult();
		Order order = orderResult.getOrders().get(0);//TODO TBC
		
		ExpressType et = new ExpressType();
		et.setUserid(AppConstant.yanwenUserId);
		et.setSendDate(form.getSendDate()+"T00:00:00");// 2015-07-09T00:00:00
		et.setQuantity(form.getQuantity());
		et.setChannel(form.getChannel());//中文 ， 中邮北京平邮小包
		String userOrderNumber = order.getAmazonOrderId();
		if(form.getSequenceNo()!=null && form.getSequenceNo().trim().length()>0){
			userOrderNumber+="_"+form.getSequenceNo();
		}
		et.setUserOrderNumber(userOrderNumber);

		Address address = order.getShippingAddress();
		Receiver rc = new Receiver();
		et.setReceiver(rc);
		rc.setUserid(AppConstant.yanwenUserId);
		rc.setName(address.getName());
		rc.setPhone(address.getPhone());
		rc.setCountry(form.getCountry()); 
		rc.setState(address.getStateOrRegion());
		rc.setCity(address.getCity());
		rc.setAddress1(address.getAddressLine1());
		rc.setAddress2(address.getAddressLine2());
		rc.setPostcode(address.getPostalCode());

		GoodsName gn = new GoodsName();
		et.setGoodsName(gn);
		gn.setUserid(AppConstant.yanwenUserId);
		gn.setNameCh(form.getNameChinese());
		gn.setNameEn(form.getNameEnglish());
		gn.setDeclaredValue(form.getDeclaredValue());
		gn.setDeclaredCurrency(form.getDeclaredCurrency());
		gn.setWeight(form.getWeight());

		this.log.info(JaxbUtil.toXml(et));
		return et;
	}
	
	private YanWenExpressEntity convert2Entity(ExpressType i) throws Exception{
		YanWenExpressEntity e = new YanWenExpressEntity();
		e.setEpcode(i.getEpcode());
		e.setUserid(i.getUserid());
		e.setChannel(i.getChannel());
		e.setUserOrderNumber(i.getUserOrderNumber());
		e.setSendDate(this.sdf.parse(i.getSendDate()));
		e.setMemo(i.getMemo());
		
		if(i.getGoodsName()!=null){
			GoodsName g = i.getGoodsName();
			e.setNameCh(g.getNameCh());
			e.setNameEn(g.getNameEn());
			e.setWeight(g.getWeight());
			e.setDeclaredValue(g.getDeclaredValue());
			e.setDeclaredCurrency(g.getDeclaredCurrency());
		}
		
		if(i.getReceiver()!=null){
			Receiver r = i.getReceiver();
			e.setName(r.getName());
			e.setPhone(r.getPhone());
			e.setMobile(r.getMobile());
			e.setEmail(r.getEmail());
			e.setCompany(r.getCompany());
			e.setCountry(r.getCountry());
			e.setPostcode(r.getPostcode());
			e.setState(r.getState());
			e.setCity(r.getCity());
			e.setAddress1(r.getAddress1());
			e.setAddress2(r.getAddress2());
		}
		return e;
	}
	
	public YanwenExpress convertToExpress(String amazonOrderID){
		YanwenExpress express = new YanwenExpress();
		express.setAmazonOrderID(amazonOrderID);
		express.setDeclaredCurrency("USD");
		express.setQuantity(1);
		express.setDeclaredValue(9);
		express.setWeight(50);
		express.setSendDate(sdf.format(new Date()));
		express.setDownloadPath(AppConstant.defaultPdfDownloadPath);
		express.setCountry("美国");//default
		
		AmazonOrder order = this.amazonOrderDao.getByOrderID(amazonOrderID);
		if(order!=null){
			express.setAmazonOrder(order);
			if(WMSConstants.marketPlaceIDCA.equals(order.getMarketplaceId())){
				express.setCountry("加拿大");
			}else if(WMSConstants.marketPlaceIDUS.equals(order.getMarketplaceId())){
				express.setCountry("美国");
			}else{
				throw new RuntimeException ("Unknow market place ID ");
			}
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
		if(order!=null){
			rc.setName(order.getShippingAddressName());
			rc.setPhone(order.getShippingAddressPhone());
			rc.setState(order.getShippingAddressStateOrRegion());
			rc.setCity(order.getShippingAddressCity());
			rc.setAddress1(order.getShippingAddressAddressLine1());
			rc.setAddress2(order.getShippingAddressAddressLine2());
			rc.setPostcode(order.getShippingAddressPostalCode());
			
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
		}
		rc.setCountry(express.getCountry()); 
		
		return express;
	}
	
}
