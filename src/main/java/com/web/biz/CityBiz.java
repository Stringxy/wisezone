package com.web.biz;

import java.util.List;

import com.web.entity.City;

public interface CityBiz extends CommonBizPage<City> {

	public boolean addCityNameExists(String cityName);
	public List<City> findAll();
}
