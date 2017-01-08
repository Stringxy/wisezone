package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.TeacherClassesDao;
import com.web.entity.Classes;
import com.web.entity.Teacher;
import com.web.entity.TeacherClasses;
import com.web.util.PageUtil;
@Repository
public class TeacherClassesDaoImpl extends CommonDaoPageImpl<TeacherClasses> implements TeacherClassesDao{

	@Override
	public List<TeacherClasses> searchPaging(Map<String, Object> params,
			PageUtil<TeacherClasses> paging) {
		String hql="from TeacherClasses";
		return super.commonSearch.searchHql(hql, params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql=" select count(*) from TeacherClasses";
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public TeacherClasses findByClasses(Classes classes, Teacher teacher) {
		String hql="from TeacherClasses where classes=:classes and teacher=:teacher";
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("classes", classes);
		params.put("teacher", teacher);
		return (TeacherClasses)super.commonSearch.searchUnique(hql, params);
	}

}
