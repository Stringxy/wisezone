package com.web.biz;

import java.util.List;

import com.web.entity.ExamForm;

public interface ExamFormBiz extends CommonBiz<ExamForm> {
	public List<ExamForm> findByTypeId(Integer id);
}
