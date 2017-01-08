package com.web.dao;

import java.util.List;

import com.web.entity.Check;
import com.web.entity.Detail;

public interface DetailDao extends CommonDaoPage<Detail> {

	public List<Detail> findByCheck(Check check);
	public Detail findByName(String name);
	public List<Detail> findAll();
}
