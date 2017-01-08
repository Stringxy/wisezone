package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.DetailDao;
import com.web.entity.Check;
import com.web.entity.Detail;
import com.web.util.PageUtil;

@Repository
public class DetailDaoImpl extends CommonDaoPageImpl<Detail> implements DetailDao {

	@Override
	public List<Detail> findByCheck(Check check) {
		String hql="from Detail where check=:check";
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("check", check);
		return super.commonSearch.searchHql(hql, params);
	}

	@Override
	public List<Detail> searchPaging(Map<String, Object> params,
			PageUtil<Detail> paging) {
		StringBuffer hql=new StringBuffer("from Detail");
		if(params.containsKey("check")){
			hql.append(" where check=:check ");
			
		}
		return super.commonSearch.searchHql(hql.toString(), params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		StringBuffer hql=new StringBuffer("select count(*) from Detail");
	if(params.containsKey("check")){
		hql.append(" where check=:check ");
		
	}
		return super.commonSearch.searchCount(hql.toString(), params);
	}

	@Override
	public Detail findByName(String name) {
		String hql="from Detail where checkName=:checkName";
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("checkName", name);
		return (Detail)super.commonSearch.searchUnique(hql, params);
	}

	@Override
	public List<Detail> findAll() {
		String hql="from Detail";
		return super.commonSearch.searchHql(hql, null);
	}



}
