package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.EmployeeInfoDao;
import com.web.entity.EmployeeInfo;
import com.web.util.PageUtil;
@Repository
public class EmployeeInfoDaoImpl extends CommonDaoPageImpl<EmployeeInfo> implements EmployeeInfoDao{

	@Override
	public List<EmployeeInfo> searchPaging(Map<String, Object> params,
			PageUtil<EmployeeInfo> paging) {
		String hql=" from EmployeeInfo";
		return super.commonSearch.searchHql(hql,null);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		
		return 0;
	}

}
