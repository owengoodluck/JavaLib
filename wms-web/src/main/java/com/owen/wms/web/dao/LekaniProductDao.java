package com.owen.wms.web.dao;

import org.springframework.stereotype.Repository;

import com.owen.wms.lekani.entity.ProductModel;

@Repository("lekaniProductDao")
public class LekaniProductDao extends BaseHibernateDao<ProductModel,String> {

}
