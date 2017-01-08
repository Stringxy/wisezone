package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.MenuRoleDao;
import com.web.entity.MenuRole;
import com.web.util.PageUtil;
@Repository
public class MenuRoleDaoImpl extends CommonDaoPageImpl<MenuRole> implements MenuRoleDao{

	@Override
	public List<MenuRole> searchPaging(Map<String, Object> params,
			PageUtil<MenuRole> paging) {
		String hql=" from MenuRole";
		return super.commonSearch.searchHql(hql, null);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

}
