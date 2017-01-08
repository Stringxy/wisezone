package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.DetailQuestionBiz;
import com.web.dao.DetailQuestionDao;
import com.web.entity.Detail;
import com.web.entity.DetailQuestion;
import com.web.entity.Question;

@Service
public class DetailQuestionBizImpl extends CommonBizPageImpl<DetailQuestion> implements
		DetailQuestionBiz {

	private DetailQuestionDao detailQuestionDao;
	
	@Resource(name="detailQuestionDaoImpl")
	public void setDetailQuestionDao(DetailQuestionDao detailQuestionDao){
		this.detailQuestionDao=detailQuestionDao;
		super.setCommonDaoPage(detailQuestionDao);
	}

	@Override
	public DetailQuestion findByQuestion(Question question,Detail detail) {
		// TODO Auto-generated method stub
		return this.detailQuestionDao.findByQuestion(question,detail);
	}

}
