package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.InterviewDao;
import com.web.entity.Interview;
import com.web.util.PageUtil;
@Repository
public class InterviewDaoImpl extends CommonDaoPageImpl<Interview> implements InterviewDao{

	@Override
	public List<Interview> searchPaging(Map<String, Object> params,
			PageUtil<Interview> paging) {
		String hql=" from Interview";
		return super.commonSearch.searchHql(hql, null);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql="  select count(*) from Interview";
		return super.commonSearch.searchCount(hql, null);
	}

}
