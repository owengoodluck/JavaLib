package com.owen.wms.web.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.mws.entity.yanwen.Receiver;
import com.amazonaws.mws.entity.yanwen.resp.CreateExpressResponseType;
import com.owen.wms.common.constant.AppConstant;
import com.owen.wms.common.util.DateUtil;
import com.owen.wms.web.constants.WMSConstants;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.entity.AmazonOrder;
import com.owen.wms.web.entity.AmazonOrderItem;
import com.owen.wms.web.entity.JewelryEntity;
import com.owen.wms.web.entity.YanWenExpressEntity;
import com.owen.wms.web.form.ExpressQueryForm;
import com.owen.wms.web.form.ExpressScanForm;
import com.owen.wms.web.form.ExpressStatisticForm;
import com.owen.wms.web.form.OrderSynchronizeForm;
import com.owen.wms.web.form.YanwenExpress;
import com.owen.wms.web.music.NumberPlayer;
import com.owen.wms.web.service.AmazonOrderService;
import com.owen.wms.web.service.YanwenExpressService;
import com.owen.wms.web.utils.DigitalFormatUtil;

@Controller
@RequestMapping("/express")
public class YanwenExpressController {
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int defaultPageSize = 20;
	
	@Autowired
	private YanwenExpressService service;
	@Autowired
	@Qualifier("amazonOrderService")
	private AmazonOrderService amazonOrderService ;

	@RequestMapping(value = "/expressStatic", method = RequestMethod.GET)
	public String preSynchronize(Model model) throws Exception{
		ExpressStatisticForm expressStaticForm = new ExpressStatisticForm();
		model.addAttribute("expressStaticForm",expressStaticForm);
		model.addAttribute("currentMenu", "express");
		return "express/expressStatic";
	}
	
	@RequestMapping(value = "/expressStatic", method = RequestMethod.POST)
	public String synchronize(@ModelAttribute("expressStaticForm") ExpressStatisticForm expressStaticForm,Model model) throws Exception{
		//1.synchronize orders
		Date startDate =null;
		Date endDate = null;
		
		if (!StringUtils.isBlank(expressStaticForm.getStartDateStr())) {  
			startDate = DateUtil.parse(expressStaticForm.getStartDateStr(), DateUtil.MONTH_FORMAT);
	    }  
		
		if (!StringUtils.isBlank(expressStaticForm.getEndDateStr())) {  
			endDate = DateUtil.parse(expressStaticForm.getEndDateStr(), DateUtil.MONTH_FORMAT);
	    }  
	
		Map<String, Object> statisResult= this.service.statisticExpress(startDate, endDate);
		model.addAttribute("statisResult", statisResult);
	
		return "express/expressStatic";
	}
	
	
	
	@RequestMapping(value="/loadBillGet", method = RequestMethod.GET)
	public String loadBillGet(HttpServletRequest request, ModelMap model) throws Exception {
		return "express/loadBill";
	}
	
	@RequestMapping(value="/loadBill", method = RequestMethod.POST)
	public String loadBill(@RequestParam(value = "billFile", required = false) MultipartFile billFile, HttpServletRequest request, ModelMap model) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("tmp");  
        String fileName = billFile.getOriginalFilename();  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {
        	billFile.transferTo(targetFile);  
        	List<String[]> list = this.service.loadBill(targetFile);
        	model.addAttribute("list", list);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return "express/loadBill";
	}
	
	@RequestMapping(value="/expressDetail", method = RequestMethod.GET)
	public String getExpressDetailByOrderID(Model model,HttpServletRequest request) throws Exception {
		String orderID =request.getParameter("orderID");
		YanWenExpressEntity expressEntity = this.service.getByOrderID(orderID);
		model.addAttribute("expressEntity", expressEntity);
		model.addAttribute("currentMenu", "express");
		return "express/expressDetail";
	}
	
	@RequestMapping(value="/updateWeight", method = RequestMethod.GET)
	public @ResponseBody String updateWeight(Model model,HttpServletRequest request) throws Exception {
		String weight =request.getParameter("weight");
		String expressNumber = request.getParameter("expressNumber");
		YanWenExpressEntity express = this.service.getByID(expressNumber.trim());
		if(express!=null){
			express.setWeight(Double.valueOf(weight).intValue());
			this.service.update(express);
		}else{
			return "Can't find express with number: "+expressNumber;
		}
		
		return "OK";
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
			if(expressScanForm.getExpressNumber().length()>9){
				play.playNumber(expressScanForm.getExpressNumber().substring(9));
			}else{
				play.playNumber(expressScanForm.getExpressNumber());
			}
			expressScanForm.setOrder(null);
			expressScanForm.setOrderItemSet(null);
			this.service.scanExpressToConfirmDeliver(expressScanForm);
			if(expressScanForm.getOrder()!=null){
				model.addAttribute("message", expressScanForm.getExpressNumber()+"扫描发货成功！");
			}else{
				model.addAttribute("message", expressScanForm.getExpressNumber()+"新添加快递单成功！");
			}
			expressScanForm.setExpressNumber(null);
		}
		model.addAttribute("currentMenu", "express");
		model.addAttribute("scanDone", "ok");
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
		YanwenExpress express = this.service.convertToExpress(amazonOrderID);
		model.addAttribute("express", express);
		model.addAttribute("currentMenu", "express");
		
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