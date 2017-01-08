package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.RecordHistoryDetailBiz;
import com.web.dao.RecordHistoryDetailDao;
import com.web.entity.RecordHistory;
import com.web.entity.RecordHistoryDetail;


@Service
public class RecordHistoryDetailBizImpl extends
		CommonBizImpl<RecordHistoryDetail> implements RecordHistoryDetailBiz {
	private RecordHistoryDetailDao recordHistoryDetailDao;
	
	@Resource(name="recordHistoryDetailDaoImpl")
	public void setRecordHistoryDetailDao(RecordHistoryDetailDao recordHistoryDetailDao){
		this.recordHistoryDetailDao=recordHistoryDetailDao;
		super.setCommonDao(recordHistoryDetailDao);
	}

	@Override
	public List<RecordHistoryDetail> findByRecord(RecordHistory rh) {
		// TODO Auto-generated method stub
		return this.recordHistoryDetailDao.findByRecord(rh);
	}
}
