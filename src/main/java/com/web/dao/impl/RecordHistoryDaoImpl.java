package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.RecordHistoryDao;
import com.web.entity.RecordHistory;
import com.web.util.PageUtil;


@Repository
public class RecordHistoryDaoImpl extends CommonDaoPageImpl<RecordHistory> implements
		RecordHistoryDao {

	@Override
	public List<RecordHistory> searchPaging(Map<String, Object> params,
			PageUtil<RecordHistory> paging) {
		String hql=" from RecordHistory";
		return super.commonSearch.searchHql(hql, params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql=" select count(*) from RecordHistory";
		return super.commonSearch.searchCount(hql, params);
	}



}
