package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.MajorDao;
import com.web.entity.Major;
import com.web.util.PageUtil;
/**
 * 专业数据访问实现
 * @author cheng
 *
 */
@Repository
public class MajorDaoImpl extends CommonDaoPageImpl<Major> implements MajorDao{

	@Override
	public List<Major> searchPaging(Map<String, Object> params,
			PageUtil<Major> paging) {
		String hql=" from Major";
		
		return super.commonSearch.searchHql(hql, params,paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql="select count(*) from Major";
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public List<Major> findAll() {
		String hql=" from Major";
		return super.commonSearch.searchHql(hql, null);
	}

}
