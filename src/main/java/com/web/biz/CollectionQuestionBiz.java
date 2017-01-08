package com.web.biz;

import java.util.List;

import com.web.entity.CollectionQuestion;
import com.web.entity.Question;
import com.web.entity.User;

public interface CollectionQuestionBiz extends CommonBizPage<CollectionQuestion> {

	public List<CollectionQuestion> findByUser(User user);
	
	public boolean isExist(User user, Question question);
}
