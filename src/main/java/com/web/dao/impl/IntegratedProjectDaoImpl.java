package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.IntegratedProjectDao;
import com.web.entity.IntegratedProject;
import com.web.util.PageUtil;
@Repository
public class IntegratedProjectDaoImpl extends CommonDaoPageImpl<IntegratedProject> implements IntegratedProjectDao{

	@Override
	public List<IntegratedProject> searchPaging(Map<String, Object> params,
			PageUtil<IntegratedProject> paging) {
		String hql=" from IntegratedProject";
		return super.commonSearch.searchHql(hql, null);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql=" select count(*) from IntegratedProject";
		return super.commonSearch.searchCount(hql, null);
	}

}
