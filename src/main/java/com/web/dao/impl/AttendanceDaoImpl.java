package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.AttendanceDao;
import com.web.entity.Attendance;
import com.web.entity.Classes;
import com.web.util.PageUtil;

@Repository  
public class AttendanceDaoImpl extends CommonDaoPageImpl<Attendance> implements
		AttendanceDao {

	@Override
	public List<Attendance> searchPaging(Map<String, Object> params,
			PageUtil<Attendance> paging) {
		StringBuffer hql = new StringBuffer("from Attendance");
		if(params.containsKey("classes")){
			hql.append("  where classes=:classes");
		}
		if(params.containsKey("createYear")){
			hql.append(" and createYear=:createYear ");
		}
		if(params.containsKey("createMonth")){
			hql.append(" and createMonth=:createMonth");
		}
		
		
		return super.commonSearch.searchHql(hql.toString(), params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql = "select count(*) from Attendance";

		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public List<Attendance> isExist(Integer year,Integer Month,Classes classes) {
		String hql = "from Attendance where classes=:classes  and createYear=:createYear and createMonth=:createMonth";
		Map<String,Object>params=new HashMap<String, Object>();
		
		params.put("classes",classes);
		params.put("createYear",year);
		params.put("createMonth",Month);
		return super.commonSearch.searchHql(hql, params);
	}


}
