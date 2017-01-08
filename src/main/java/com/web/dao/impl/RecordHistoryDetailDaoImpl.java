package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.RecordHistoryDetailDao;
import com.web.entity.RecordHistory;
import com.web.entity.RecordHistoryDetail;

@Repository
public class RecordHistoryDetailDaoImpl extends CommonDaoImpl<RecordHistoryDetail> implements
		RecordHistoryDetailDao {

	@Override
	public List<RecordHistoryDetail> findByRecord(RecordHistory rh) {
		String hql="from RecordHistoryDetail where record=:record";
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("record", rh);
		return super.commonSearch.searchHql(hql, params);
	}



}
