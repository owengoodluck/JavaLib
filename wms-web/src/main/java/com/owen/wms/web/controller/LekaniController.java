package com.owen.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.owen.wms.lekani.entity.GetProductModelListPackage;
import com.owen.wms.lekani.entity.ProductModel;
import com.owen.wms.lekani.service.LKNService;
import com.owen.wms.web.dao.Page;
import com.owen.wms.web.form.LekaniProdImportForm;
import com.owen.wms.web.form.LekaniProdQueryForm;
import com.owen.wms.web.service.LekaniProductService;

@Controller
@RequestMapping("/lekani")
public class LekaniController {
	
	private Logger log = Logger.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int defaultPageSize = 20;
	
	private LKNService lknService = new LKNService();
	@Autowired
	@Qualifier("lekaniProductService")
	private LekaniProductService lekaniProductService;
	
	
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
		log.info("getCategoryID="+queryForm.getCategoryID()+"--getBrandID="+queryForm.getBrandID());
		Page page = null;
		if(pack == null){
			page = new Page(1,100,0,null);
		}else{
			page = new Page(queryForm.getCurrentPage(),pack.getPageSize(),pack.getRecordTotal(),pack.getProductList());
			
			//load into local DB
			this.lekaniProductService.saveOrUpdateProdList(pack.getProductList());
		}
		model.addAttribute("page", page);
		model.addAttribute("queryForm", queryForm);
		model.addAttribute("currentMenu", "lekani");
		return "lekani/prodList";
	}
	
	@RequestMapping(value="/pageQueryLocal", method = RequestMethod.GET)
	public String pageQueryLocal(Model model,HttpServletRequest request) throws Exception {
		int currentPage =1;
		int pageSize = 20; 
		ProductModel entity = new ProductModel() ;
		entity.setStatus("selected,null");
		Page page = this.lekaniProductService.pageListByCriteria(currentPage, pageSize, entity);
		model.addAttribute("page", page);
		
		LekaniProdQueryForm queryForm = new LekaniProdQueryForm();
		queryForm.setCurrentPage(currentPage);
		queryForm.setPageSize(pageSize);
		queryForm.setStatus("selected,null");
		model.addAttribute("queryForm", queryForm);
		model.addAttribute("currentMenu", "lekani");
		return "lekani/pageQueryLocal";
	}

	@RequestMapping(value="/pageQueryLocal", method = RequestMethod.POST)
	public String pageQueryLocal(Model model,@ModelAttribute("queryForm") LekaniProdQueryForm queryForm ) throws Exception {
		ProductModel entity = new ProductModel() ;
		if(queryForm.getProdID() != null &&queryForm.getProdID().trim().length()>0){
			entity.setProductID(queryForm.getProdID());
		}
		if(queryForm.getCategoryID() != 0 ){
			entity.setCatID(queryForm.getCategoryID()+"");
		}
		if(queryForm.getBrandID() != 0 ){
			entity.setBrandID(queryForm.getBrandID()+"");
		}
		if(queryForm.getStatus() != "all" ){
			entity.setStatus(queryForm.getStatus());
		}
		if(queryForm.getStock() != null ){
			entity.setStock(queryForm.getStock());
		}
		Page page = this.lekaniProductService.pageListByCriteria(queryForm.getCurrentPage(), queryForm.getPageSize(), entity);
		model.addAttribute("page", page);
		model.addAttribute("queryForm", queryForm);
		model.addAttribute("currentMenu", "lekani");
		return "lekani/pageQueryLocal";
	}
	
	@RequestMapping(value="/prodDetail/{prodID}", method = RequestMethod.GET)
	public String prodDetail(Model model,@PathVariable("prodID") String prodID) {
		ProductModel result = this.lekaniProductService.getProductModelByID(prodID);
		if(result.getImages()!=null){
			String[] images = result.getImages().split(",");
			model.addAttribute("images", images);
		}
		model.addAttribute("prod", result);
		model.addAttribute("currentMenu", "lekani");
		return "lekani/prodDetail";
	}
	
	@RequestMapping(value="/getBrandListByCategoryID", method = RequestMethod.GET)  
    public @ResponseBody Map<String,String> getBrandListByCategoryID() {
		String categoryID = null;
		Map<String,String> map = this.lekaniProductService.getBrandListByCategoryID(categoryID );
		return map;
	}

	
	@RequestMapping(value="/importByID", method = RequestMethod.GET)  
    public String importByID(Model model) {
		LekaniProdImportForm importForm = new LekaniProdImportForm();
		model.addAttribute("importForm",importForm);
		return "lekani/importByID";
	}

	@RequestMapping(value="/importByID", method = RequestMethod.POST)  
    public String importByIDPost(Model model,@ModelAttribute("importForm") LekaniProdImportForm importForm) {
		if(importForm.getProdID()==null){
			importForm.setProdID(0);
		}
		ProductModel prod = this.lekaniProductService.loadById(importForm.getProdID(),importForm.getSku());
		if(prod!=null){
			return this.prodDetail(model, prod.getProductID());
		}
		return "lekani/importByID";
	}
	
	@RequestMapping(value="/updateStatus", method = RequestMethod.GET)  
    public void updateStatus(HttpServletRequest request) {
		String prodID = request.getParameter("prodID");
		String status = request.getParameter("status");
		this.lekaniProductService.upateStatus(prodID, status);
	}
	
	@RequestMapping(value = "/batchProcess", method = RequestMethod.POST)
	public String batchProcess(Model model,@ModelAttribute("queryForm") LekaniProdQueryForm queryForm,HttpServletRequest request) throws Exception{
		String[] prodIDs = request.getParameterValues("prodIDs");
		String processMethod = request.getParameter("processMethod");
		if("discard".equals(processMethod) || "selected".equals(processMethod)){
			this.lekaniProductService.bathUpdateStatus(prodIDs, processMethod);
		}else if("converted".equals(processMethod)){
			this.lekaniProductService.saveAmazonJewelryFromLekaniProds(prodIDs);
		}else if("getLatesStock".equals(processMethod)){
			this.lekaniProductService.getLatestStockForLekaniProds(prodIDs);
		}
		return this.pageQueryLocal(model, queryForm) ;
	}
	
}