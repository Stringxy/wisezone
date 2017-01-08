package com.web.dao;

import java.util.List;

import com.web.biz.CommonBizPage;
import com.web.entity.CollectionQuestion;
import com.web.entity.Question;
import com.web.entity.User;

public interface CollectionQuestionDao extends CommonDaoPage<CollectionQuestion> {

	public List<CollectionQuestion> findByUser(User user);
	
	public boolean isExist(User user,Question question);
}
