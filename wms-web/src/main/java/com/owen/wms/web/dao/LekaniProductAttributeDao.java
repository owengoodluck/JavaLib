package com.owen.wms.web.dao;

import org.springframework.stereotype.Repository;

import com.owen.wms.lekani.entity.AttributeModel;

@Repository("lekaniProductAttributeDao")
public class LekaniProductAttributeDao extends BaseHibernateDao<AttributeModel,String> {

}
