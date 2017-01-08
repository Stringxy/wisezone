package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.CityBiz;
import com.web.dao.CityDao;
import com.web.entity.City;

/**
 * 城市的业务逻辑层实现类
 * @author Xy
 *
 */
@Service //业务逻辑层的注解
public class CityBizImpl extends CommonBizPageImpl<City> implements CityBiz {

	private CityDao cityDao=null;
	
	
	@Resource(name="cityDaoImpl")//名称必须首字母小写，其他的相同
	public void setCityDao(CityDao cityDao){
		this.cityDao=cityDao;
		
		super.setCommonDaoPage(cityDao);
	}


	@Override
	public boolean addCityNameExists(String cityName) {
		int count=cityDao.addCityNameExists(cityName);
		return count==0;
	}


	@Override
	public List<City> findAll() {
		// TODO Auto-generated method stub
		return this.cityDao.findAll();
	}

}
