package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.RoleDao;
import com.web.entity.Role;
import com.web.util.PageUtil;
@Repository
public class RoleDaoImpl extends CommonDaoPageImpl<Role> implements RoleDao {

	@Override
	public List<Role> searchPaging(Map<String, Object> params,
			PageUtil<Role> paging) {
		String hql=" from Role";
		return super.commonSearch.searchHql(hql, params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql=" select count(*) from Role";
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public List<Role> findAll() {
		String hql=" from Role";
		return super.commonSearch.searchHql(hql, null);
	}

}
