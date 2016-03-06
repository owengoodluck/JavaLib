package com.owen.wms.web.dao;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.owen.wms.lekani.entity.ProductModel;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("classpath:test_config.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class LekaniProductDaoTest {
	@Autowired
	@Qualifier("lekaniProductDao")
	private LekaniProductDao lekaniProductDao;
	
	@Test
	public void testByProductID(){
		ProductModel p = this.lekaniProductDao.get("119544");
		System.out.println(p.getAttributes().size());
	}
}
