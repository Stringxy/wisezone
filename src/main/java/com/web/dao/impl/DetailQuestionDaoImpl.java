package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.DetailQuestionDao;
import com.web.entity.Detail;
import com.web.entity.DetailQuestion;
import com.web.entity.Question;
import com.web.util.PageUtil;

@Repository
public class DetailQuestionDaoImpl extends CommonDaoImpl<DetailQuestion> implements
		DetailQuestionDao {

	@Override
	public List<DetailQuestion> searchPaging(Map<String, Object> params,
			PageUtil<DetailQuestion> paging) {
		String hql="from DetailQuestion";
		return super.commonSearch.searchHql(hql, params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
String hql="select count(*) from DetailQuestion";
		
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public DetailQuestion findByQuestion(Question question,Detail detail) {
		String hql="from DetailQuestion where question=:question and detail=:detail";
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("question", question);
		params.put("detail", detail);
		return (DetailQuestion) super.commonSearch.searchUnique(hql, params);
	}





}
