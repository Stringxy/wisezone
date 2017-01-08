package com.web.biz.impl;

import java.util.Map;

import com.web.dao.CommonDao;
import com.web.dao.CommonDaoPage;
import com.web.util.PageUtil;

public class CommonBizPageImpl<T> extends CommonBizImpl<T> {

	private CommonDaoPage<T> commonDaoPage = null;
	
	public void setCommonDaoPage(CommonDaoPage<T> commonDaoPage) {
		this.commonDaoPage = commonDaoPage;
		super.setCommonDao(commonDaoPage);
	}



	/**
     * 分页查询
     * @param params
     * @param paging
     */
    public void searchPaging(Map<String,Object> params,PageUtil<T> paging){
    
    	paging.setData(commonDaoPage.searchPaging(params, paging));//设置分页的数据
    	paging.setTotalCount(commonDaoPage.getTotalCount(params));//设置分页总的条数
    }
}
