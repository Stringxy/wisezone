package com.web.biz.impl;

import java.util.Map;

import com.web.biz.CommonBiz;
import com.web.dao.CommonDao;
import com.web.util.PageUtil;


/**
 * 公共的业务逻辑层访问实现类
 * @author Administrator
 *
 */
public class CommonBizImpl<T> implements CommonBiz<T> {

	private CommonDao<T> commonDao = null;
	public void setCommonDao(CommonDao<T> commonDao) {
		this.commonDao = commonDao;
	}

	/**
	 * 添加
	 * @param obj
	 */
	public void insert(T obj){
		commonDao.insert(obj);
	}
	
	/**
	 * 修改
	 * @param obj
	 */
	public void update(T obj){
		commonDao.update(obj);
	}
	
	/**
	 * 删除
	 * @param obj
	 */
	public void delete(T obj){
		commonDao.delete(obj);
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(int id){
		commonDao.delete(id);
	}
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public T findById(int id){
		return commonDao.findById(id);
	}
	
	
}
