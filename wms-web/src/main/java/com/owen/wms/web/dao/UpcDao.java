package com.owen.wms.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.owen.wms.web.entity.UPC;

@Repository("upcDao")
public class UpcDao extends BaseHibernateDao<UPC,String> {

	public List<UPC> getCountOfNewUPC(int count){
		Criteria criteria = createCriteria();
		criteria.addOrder(Order.desc("code") );
		Criterion criterion = Restrictions.eq("status", "NEW");
		criteria.add(criterion);
		criteria.setMaxResults(count);
		return criteria.list();
	}
	
	public UPC getNewUPC(){
		Criteria criteria = createCriteria();
		criteria.addOrder(Order.desc("code") );
		Criterion criterion = Restrictions.eq("status", "NEW");
		criteria.add(criterion);
		criteria.setMaxResults(1);
		return (UPC) criteria.uniqueResult();
	}
}
