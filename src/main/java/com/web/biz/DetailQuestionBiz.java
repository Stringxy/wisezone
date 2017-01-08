package com.web.biz;

import com.web.entity.Detail;
import com.web.entity.DetailQuestion;
import com.web.entity.Question;

public interface DetailQuestionBiz extends CommonBizPage<DetailQuestion> {

	public DetailQuestion findByQuestion(Question question,Detail detail);
}
