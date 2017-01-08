package com.web.dao;

import java.util.List;

import com.web.entity.Check;
import com.web.entity.Major;

public interface CheckDao extends CommonDaoPage<Check> {

	public List<Check> findByMajor(Major major);
	public List<Check> findAll();
}
