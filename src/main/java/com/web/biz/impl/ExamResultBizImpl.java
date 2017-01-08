package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.ExamResultBiz;
import com.web.dao.ExamResultDao;
import com.web.entity.ExamResult;

@Service
public class ExamResultBizImpl extends CommonBizPageImpl<ExamResult> implements ExamResultBiz {
	private ExamResultDao examResultDao=null;
	@Resource(name="examResultDaoImpl")
	public void setExamResultDao(ExamResultDao examResultDao) {
		this.examResultDao = examResultDao;
		super.setCommonDaoPage(examResultDao);
	}
	
	


}
