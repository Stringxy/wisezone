package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.TeacherDao;
import com.web.entity.Teacher;
import com.web.util.PageUtil;

/**
 * 
 * @author Xy
 *
 */

@Repository  //数据访问层的注解 teacher
public class TeacherDaoImpl extends CommonDaoPageImpl<Teacher> implements TeacherDao {

	

	@Override
	public List<Teacher> searchPaging(Map<String, Object> params,
			PageUtil<Teacher> paging) {
		String hql="from Teacher";
		return super.commonSearch.searchHql(hql, params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		String hql="select count(*) from Teacher";
		return super.commonSearch.searchCount(hql, params);
	}

	@Override
	public Teacher login(String loginName, String loginPwd) {
		String hql="from Teacher where loginName=:loginName and loginPwd=:loginPwd";
		Map<String,Object>params=new HashMap<String, Object>();
		System.out.println("登录的用户名："+loginName);
		System.out.println("登录的密码："+loginPwd);
		params.put("loginName", loginName);
		params.put("loginPwd", loginPwd);
		
		return (Teacher)super.commonSearch.searchUnique(hql, params);
	}

	@Override
	public Teacher findByName(String loginName) {
		String hql="from Teacher where loginName=:loginName";
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("loginName", loginName);
		return (Teacher)super.commonSearch.searchUnique(hql, params);
	}
/**
 * 教师全查询实现
 */
	@Override
	public List<Teacher> findAll() {
		String hql="from Teacher";
		return super.commonSearch.searchHql(hql, null);
	}

}

