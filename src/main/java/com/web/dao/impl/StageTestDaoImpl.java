package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.StageTestDao;
import com.web.entity.StageTest;
import com.web.util.PageUtil;
@Repository
public class StageTestDaoImpl extends CommonDaoPageImpl<StageTest> implements StageTestDao{

	@Override
	public List<StageTest> searchPaging(Map<String, Object> params,
			PageUtil<StageTest> paging) {
		String hql=" from StageTest";
		return super.commonSearch.searchHql(hql, null);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql=" select count(*) from StageTest";
		return super.commonSearch.searchCount(hql, null);
	}

}
