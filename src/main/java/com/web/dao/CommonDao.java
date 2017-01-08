package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.util.PageUtil;

public interface CommonDao<T> {

	/**
	 * 添加
	 * @param obj
	 */
	public void insert(T obj);
	
	/**
	 * 修改
	 * @param obj
	 */
	public void update(T obj);
	
	/**
	 * 删除
	 * @param obj
	 */
	public void delete(T obj);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public T findById(int id);
	
	
}
