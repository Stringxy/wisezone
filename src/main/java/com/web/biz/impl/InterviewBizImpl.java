package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.InterviewBiz;
import com.web.dao.InterviewDao;
import com.web.entity.Interview;
@Service
public class InterviewBizImpl extends CommonBizPageImpl<Interview> implements InterviewBiz{

	private InterviewDao intervDao= null;
	@Resource(name="interviewDaoImpl")
	public void setIntervDao(InterviewDao intervDao) {
		this.intervDao = intervDao;
		super.setCommonDaoPage(intervDao);
	}
	
}
