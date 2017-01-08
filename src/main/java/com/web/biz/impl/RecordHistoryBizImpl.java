package com.web.biz.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.RecordHistoryBiz;
import com.web.dao.RecordHistoryDao;
import com.web.entity.RecordHistory;
import com.web.util.PageUtil;


@Service
public class RecordHistoryBizImpl extends CommonBizPageImpl<RecordHistory> implements
		RecordHistoryBiz {

	private RecordHistoryDao recordHistoryDao=null;
	
	
	@Resource(name="recordHistoryDaoImpl")
	public void setRecordHistoryDao(RecordHistoryDao recordHistoryDao){
		this.recordHistoryDao=recordHistoryDao;
		super.setCommonDaoPage(recordHistoryDao);
	}
	




}
