package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.ExamFormDao;
import com.web.entity.ExamForm;

@Repository
public class ExamFormDaoImpl extends CommonDaoImpl<ExamForm> implements ExamFormDao {
//根据问卷类型查询题目
	@Override
	public List<ExamForm> findByTypeId(Integer id) {
		String hql="from ExamForm where examTypeId=:id";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("id", id);
		return super.commonSearch.searchHql(hql, params);
	}

}
