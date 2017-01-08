package com.web.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.web.dao.CityDao;
import com.web.entity.City;
import com.web.util.PageUtil;


@Repository  //数据访问层的注解  city
public class CityDaoImpl extends CommonDaoPageImpl<City> implements CityDao {


	@Override
	public List<City> searchPaging(Map<String, Object> params,
			PageUtil<City> paging) {
		String hql="from City";
		return super.commonSearch.searchHql(hql, params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql="select count(*) from City";
		
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public int addCityNameExists(String cityName) {
		String hql= "select count(*) from City where cityName=:cityName";
		Map<String , Object > params= new HashMap<String , Object>();
		params.put("cityName", cityName);
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public List<City> findAll() {
		String hql="from City";
		return super.commonSearch.searchHql(hql,null);
	}

}
