package com.web.dao;

import java.util.List;

import com.web.entity.ExamForm;

public interface ExamFormDao extends CommonDao<ExamForm> {
	public List<ExamForm> findByTypeId(Integer id);
}
