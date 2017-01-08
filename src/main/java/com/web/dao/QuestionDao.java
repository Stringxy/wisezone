package com.web.dao;

import java.util.List;

import com.web.entity.Detail;
import com.web.entity.Question;

public interface QuestionDao extends CommonDaoPage<Question> {

	public List<Question> findByDetail(Detail detail);
	public List<Question> findAll();
}
