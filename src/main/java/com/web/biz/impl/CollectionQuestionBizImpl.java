package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.CollectionQuestionBiz;
import com.web.dao.CollectionQuestionDao;
import com.web.entity.CollectionQuestion;
import com.web.entity.Question;
import com.web.entity.User;

@Service
public class CollectionQuestionBizImpl extends CommonBizPageImpl<CollectionQuestion> implements
		CollectionQuestionBiz {

	private CollectionQuestionDao collectionQuestionDao=null;
	
	@Resource(name="collectionQuestionDaoImpl")
	public void setCollectionQuestionDao(CollectionQuestionDao collectionQuestionDao){
		this.collectionQuestionDao=collectionQuestionDao;
		super.setCommonDaoPage(collectionQuestionDao);
	}

	@Override
	public List<CollectionQuestion> findByUser(User user) {
		// TODO Auto-generated method stub
		return this.collectionQuestionDao.findByUser(user);
	}

	@Override
	public boolean isExist(User user, Question question) {
		// TODO Auto-generated method stub
		return this.collectionQuestionDao.isExist(user, question);
	}

}
