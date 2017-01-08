package com.web.dao;

import java.util.List;

import com.web.entity.City;

public interface CityDao extends CommonDaoPage<City>{

	
	public int addCityNameExists(String cityName);
	
	public List<City> findAll();
	
}
