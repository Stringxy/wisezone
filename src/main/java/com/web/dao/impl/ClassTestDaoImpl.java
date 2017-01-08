package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.ClassTestDao;
import com.web.entity.ClassTest;
import com.web.util.PageUtil;
@Repository
public class ClassTestDaoImpl extends CommonDaoPageImpl<ClassTest> implements ClassTestDao{

	@Override
	public List<ClassTest> searchPaging(Map<String, Object> params,
			PageUtil<ClassTest> paging) {
		String hql=" from ClassTest";
		return super.commonSearch.searchHql(hql, null);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql=" select count(*) from ClassTest";
		return super.commonSearch.searchCount(hql, null);
	}

}
