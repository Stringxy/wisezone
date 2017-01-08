package com.web.dao;

import java.util.List;

import com.web.entity.Detail;
import com.web.entity.DetailQuestion;
import com.web.entity.Question;

public interface DetailQuestionDao extends CommonDaoPage<DetailQuestion> {

	public DetailQuestion findByQuestion(Question question,Detail detail);
}
