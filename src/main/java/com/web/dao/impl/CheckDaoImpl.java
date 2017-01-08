package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.CheckDao;
import com.web.entity.Check;
import com.web.entity.Major;
import com.web.util.PageUtil;

@Repository
public class CheckDaoImpl extends CommonDaoImpl<Check> implements CheckDao {


	@Override
	public List<Check> findByMajor(Major major) {
		String hql="from Check where major=:major";
		Map<String,Object>params =new HashMap<String,Object>();
		params.put("major", major);
		return super.commonSearch.searchHql(hql, params);
	}

	@Override
	public List<Check> searchPaging(Map<String, Object> params,
			PageUtil<Check> paging) {
		String hql="from Check";
		return super.commonSearch.searchHql(hql, params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql="select count(*) from Check";
		
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public List<Check> findAll() {
		String hql="from Check";
		return super.commonSearch.searchHql(hql, null);
	}

}
