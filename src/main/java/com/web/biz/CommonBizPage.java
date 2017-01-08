package com.web.biz;

import java.util.Map;

import com.web.util.PageUtil;

public interface CommonBizPage<T> extends CommonBiz<T> {
	 /**
     * 分页查询
     * @param params
     * @param paging
     */
    public void searchPaging(Map<String,Object> params,PageUtil<T> paging);
}
