package com.web.dao;

import java.util.List;

import com.web.entity.Major;
/**
 * 专业数据访问
 * @author cheng
 *
 */
public interface MajorDao extends CommonDaoPage<Major>{

	
	public List<Major> findAll();
}
