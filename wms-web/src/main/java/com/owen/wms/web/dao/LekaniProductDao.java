package com.owen.wms.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.owen.wms.lekani.entity.ProductModel;

@Repository("lekaniProductDao")
public class LekaniProductDao extends BaseHibernateDao<ProductModel,String> {

	public List<ProductModel> listAllParent(){
		Query query = this.getSession().createQuery("from ProductModel e where 1=1 order by catID,brandID desc");
		List<ProductModel> result = query.list();
		return result;
	}
	
	public List<String> getBrandListByCategoryID(String categoryID){
		String sql = null;
		if(categoryID!=null && categoryID.trim().length()>0){
			sql  = "select distinct brandID from ProductModel e where 1=1 and catID = '"+categoryID+"'";
		}else{
			sql =  "select distinct brandID from ProductModel e where 1=1 ";
		}
		Query query = this.getSession().createQuery(sql);
		List<String> result = query.list();
		return result;
	}
	
	public Page pageListByCriteria(int currentPage,int pageSize,ProductModel entity){
		Map<String,Object> criteriaMap = new HashMap();
		StringBuffer hql = new StringBuffer(" from ProductModel where 1 = 1 ");
		if(entity!=null){
			if(entity.getCatID()!=null &&  entity.getCatID().trim().length()>0){
				hql.append(" and  catID =:catID");
				criteriaMap.put("catID", entity.getCatID().trim());
			}
			if(entity.getBrandID()!=null &&  entity.getBrandID().trim().length()>0){
				hql.append(" and  brandID =:brandID");
				criteriaMap.put("brandID", entity.getBrandID().trim());
			}
		}
		List<ProductModel> list = this.findPageByQuery(currentPage, pageSize, hql.append(" order by catID,brandID desc").toString(), criteriaMap);
		int totalCount = this.getTotalCount("select count(*) "+hql.toString(), criteriaMap);
		return new Page(currentPage,pageSize,totalCount,list);
	}
}
