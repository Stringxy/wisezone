package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.QuestionBiz;
import com.web.dao.QuestionDao;
import com.web.entity.Detail;
import com.web.entity.Question;

@Service
public class QuestionBizImpl extends CommonBizPageImpl<Question> implements QuestionBiz {

	private QuestionDao questionDao=null;
	
	@Resource(name="questionDaoImpl")
	public void setQuestionDao(QuestionDao questionDao){
		this.questionDao=questionDao;
		super.setCommonDaoPage(questionDao);
	}

	@Override
	public List<Question> findByDetail(Detail detail) {
		// TODO Auto-generated method stub
		return this.questionDao.findByDetail(detail);
	}

	@Override
	public List<Question> findAll() {
		// TODO Auto-generated method stub
		return this.questionDao.findAll();
	}

}
