package com.web.dao;

import java.util.List;
import java.util.Map;

import com.web.util.PageUtil;

public interface CommonDaoPage<T> extends CommonDao<T>{

	
	/**
	 * 分页查询显示数据
	 * @param params
	 * @param paging
	 * @return
	 */
	public List<T> searchPaging(Map<String,Object> params,PageUtil<T> paging);
	
	/**
	 * 查询总的条数
	 * @param params
	 * @return
	 */
	public int getTotalCount(Map<String,Object> params);
}
