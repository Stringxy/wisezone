package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.web.dao.CommonDaoPage;
import com.web.dao.CommonSearchDao;
import com.web.util.PageUtil;

public abstract class CommonDaoPageImpl<T> extends CommonDaoImpl<T> implements CommonDaoPage<T>{

	@Resource(name = "commonSearchDaoImpl")
	public CommonSearchDao<T> commonSearch;

	@Override
	public abstract List<T> searchPaging(Map<String, Object> params, PageUtil<T> paging);

	@Override
	public abstract int getTotalCount(Map<String, Object> params);

}
