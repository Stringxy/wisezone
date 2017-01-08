package com.web.biz;

import java.util.List;

import com.web.entity.Detail;
import com.web.entity.Question;

public interface QuestionBiz extends CommonBizPage<Question> {
	public List<Question> findByDetail(Detail detail);
	public List<Question> findAll();
}
