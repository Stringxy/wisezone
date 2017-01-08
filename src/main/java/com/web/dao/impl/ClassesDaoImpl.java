package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.ClassesDao;
import com.web.entity.Classes;
import com.web.entity.Teacher;
import com.web.util.PageUtil;
/**
 * 班级数据访问实现
 * @author cheng
 *
 */
@Repository
public class ClassesDaoImpl extends CommonDaoPageImpl<Classes> implements ClassesDao{

	@Override
	public List<Classes> searchPaging(Map<String, Object> params,
			PageUtil<Classes> paging) {
		String hql=" from Classes";
		if(params!=null&&params.containsKey("teacher")){
			hql="select a.classes from TeacherClasses a where a.teacher=:teacher";
		}
		return super.commonSearch.searchHql(hql, params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql= " select count(*) from Classes";
		if(params!=null&&params.containsKey("teacher")){
			hql="select count(*) from TeacherClasses a where a.teacher=:teacher";
		}
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public List<Classes> findAll() {
		String hql="from Classes";
		return this.commonSearch.searchHql(hql, null);
	}

	@Override
	public List<Classes> findByTeacher(Teacher teacher) {
		String hql="select a.classes from TeacherClasses a where a.teacher=:teacher";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("teacher", teacher);
		return super.commonSearch.searchHql(hql, params);
	}

}
