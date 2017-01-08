package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.ExamFormBiz;
import com.web.dao.ExamFormDao;
import com.web.entity.ExamForm;

@Service
public class ExamFormBizImpl extends CommonBizImpl<ExamForm> implements ExamFormBiz {
	
	private ExamFormDao examFormDao=null;
	@Resource(name="examFormDaoImpl")
	public void setExamFormDao(ExamFormDao examFormDao) {
		this.examFormDao = examFormDao;
		super.setCommonDao(examFormDao);
	}
	
	@Override
	public List<ExamForm> findByTypeId(Integer id) {
		return this.examFormDao.findByTypeId(id) ;
	}
	

}
