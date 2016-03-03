package com.owen.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.owen.wms.lekani.entity.GetProductModelListPackage;
import com.owen.wms.lekani.service.LKNService;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.form.ExpressQueryForm;
import com.owen.wms.web.form.LekaniProdQueryForm;

@Controller
@RequestMapping("/lekani")
public class LekaniController {
	
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int defaultPageSize = 20;
	
	private LKNService lknService = new LKNService();
	
	
	@RequestMapping(value="/pageQuery", method = RequestMethod.GET)
	public String listProd(Model model,HttpServletRequest request) throws Exception {
		LekaniProdQueryForm queryForm = new LekaniProdQueryForm();
		queryForm.setCurrentPage(1);
		queryForm.setPageSize(100);
		Page page = new Page(1,100,0,null);
		model.addAttribute("page", page);
		model.addAttribute("queryForm", queryForm);
		model.addAttribute("currentMenu", "lekani");
		return "lekani/prodList";
	}

	@RequestMapping(value="/pageQuery", method = RequestMethod.POST)
	public String pageQuery(Model model,@ModelAttribute("queryForm") LekaniProdQueryForm queryForm ) throws Exception {
		GetProductModelListPackage pack = this.lknService.getProductList(queryForm.getCurrentPage(), queryForm.getCategoryID(),queryForm.getBrandID());
		Page page = null;
		if(pack == null){
			page = new Page(1,100,0,null);
		}else{
			page = new Page(queryForm.getCurrentPage(),pack.getPageSize(),pack.getRecordTotal(),pack.getProductList());
		}
		model.addAttribute("page", page);
		model.addAttribute("queryForm", queryForm);
		model.addAttribute("currentMenu", "lekani");
		return "lekani/prodList";
	}
}