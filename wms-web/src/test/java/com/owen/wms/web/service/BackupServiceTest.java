package com.owen.wms.web.service;

import java.io.File;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class BackupServiceTest {

	@Autowired
	private BackupService backupService;

	@Test
	public void testSave(){
		this.backupService.writeJewelryProd2File();
	}
	@Test
	public void testRead(){
		File f = new File("C:/Users/owen/Desktop/tmp/jew_bak_0319.dat");
		this.backupService.readJewelryProdFromFile(f);
	}
}
