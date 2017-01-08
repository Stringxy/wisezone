package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.ExamTypeBiz;
import com.web.dao.ExamTypeDao;
import com.web.entity.ExamType;
@Service
public class ExamTypeBizImpl extends CommonBizImpl<ExamType> implements ExamTypeBiz {
	private ExamTypeDao examTypeDao=null;
	@Resource(name="examTypeDaoImpl")
	public void setExamTypeDao(ExamTypeDao examTypeDao) {
		this.examTypeDao = examTypeDao;
		super.setCommonDao(examTypeDao);
	}
	
	
	
}
