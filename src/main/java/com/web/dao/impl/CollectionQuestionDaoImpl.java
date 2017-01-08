package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.CollectionQuestionDao;
import com.web.entity.CollectionQuestion;
import com.web.entity.Question;
import com.web.entity.User;
import com.web.util.PageUtil;

@Repository
public class CollectionQuestionDaoImpl extends CommonDaoPageImpl<CollectionQuestion> implements
		CollectionQuestionDao {

	@Override
	public List<CollectionQuestion> searchPaging(Map<String, Object> params,
			PageUtil<CollectionQuestion> paging) {
		String hql=" from CollectionQuestion";
		return super.commonSearch.searchHql(hql, params,paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql="select count(*) from CollectionQuestion";
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public List<CollectionQuestion> findByUser(User user) {
		String hql=" from CollectionQuestion where user=:user";
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("user", user);
		return super.commonSearch.searchHql(hql, params);
	}

	@Override
	public boolean isExist(User user, Question question) {
		String hql="from CollectionQuestion where user=:user and question=:question";
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("user", user);
		params.put("question", question);
		List<CollectionQuestion> list=super.commonSearch.searchHql(hql, params);
		return list.size()>0;
	}




}
