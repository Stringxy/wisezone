package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.ExamResultDao;
import com.web.entity.ExamResult;
import com.web.util.PageUtil;
/**
 * 问卷调查结果记录
 * @author johen
 *
 */
@Repository
public class ExamResultDaoImpl extends CommonDaoPageImpl<ExamResult> implements ExamResultDao{

	@Override
	public List<ExamResult> searchPaging(Map<String, Object> params,
			PageUtil<ExamResult> paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

}
