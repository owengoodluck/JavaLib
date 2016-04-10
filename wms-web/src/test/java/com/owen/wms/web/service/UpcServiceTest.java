package com.owen.wms.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.owen.wms.web.entity.UPC;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class UpcServiceTest {

	@Autowired
	private UpcService upcService;
	
	@Test
	public void testLoad(){
		String excelPath = "C:/Users/owen/Desktop/Amazon/UPC/2016-04-04_192813-neoo_ok-GS1授权UPC码500个/2016-04-04_192813-neoo_ok-GS1授权UPC码500个/UPC-A (USA & Canada)/UPC - Spreadsheet -1 Excel.xls";
		this.upcService.loadUPCFromExcel(excelPath );
	}
	
	@Test
	public void getCountOfNewUPC(){
		List<UPC> list = this.upcService.getCountOfNewUPC(3 );
		System.out.println(list.size());
		Assert.assertTrue(list.size() == 3);
	}
}
