package com.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.MajorStageDao;
import com.web.entity.MajorStage;
import com.web.util.PageUtil;
@Repository
public class MajorStageDaoImpl extends CommonDaoPageImpl<MajorStage> implements MajorStageDao{

	@Override
	public List<MajorStage> searchPaging(Map<String, Object> params,
			PageUtil<MajorStage> paging) {
		String hql=" from MajorStage";
		if(params!=null&&params.containsKey("major")){
			hql=" from MajorStage where major=:major";
		}
		return super.commonSearch.searchHql(hql, params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql=" select count(*) from MajorStage";
		if(params!=null&&params.containsKey("major")){
			hql="select count(*) from MajorStage where major=:major";
		}
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public List<MajorStage> findAll() {
		String hql=" from MajorStage";
		return super.commonSearch.searchHql(hql, null);
	}

}
