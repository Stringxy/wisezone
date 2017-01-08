package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web.dao.UserDao;
import com.web.entity.Classes;
import com.web.entity.Major;
import com.web.entity.User;
import com.web.util.PageUtil;

@Repository
public class UserDaoImpl extends CommonDaoPageImpl<User> implements UserDao {

	@Override
	public List<User> searchPaging(Map<String, Object> params,
			PageUtil<User> paging) {
		StringBuffer hql=new StringBuffer("from User");
		if(params!=null&&params.containsKey("classes")){
			hql.append("  where classes=:classes");
			
		}
		return super.commonSearch.searchHql(hql.toString(), params, paging);
	}

	@Override
	public int getTotalCount(Map<String, Object> params) {
		StringBuffer hql=new StringBuffer("select count(*) from User");
		if(params!=null&&params.containsKey("classes")){
			hql.append("  where classes=:classes");
			
		}
		return super.commonSearch.searchCount(hql.toString(), params);
	}

	@Override
	public User login(String stuName, String password) {
		String hql="from User where stuName=:stuName and password=:password";
		Map<String,Object>params=new HashMap<String, Object>();
		System.out.println("登录的用户名："+stuName);
		System.out.println("登录的密码："+password);
		params.put("stuName", stuName);
		params.put("password", password);
		return (User)super.commonSearch.searchUnique(hql, params);
	}

	@Override
	public User findByname(String stuName) {
		String hql="from User where stuName=:stuName";
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("stuName", stuName);
		return (User)super.commonSearch.searchUnique(hql, params);
	}

	@Override
	public List<User> findByClasses(Classes classes) {
		String hql="from User where classes=:classes";
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("classes", classes);
		return super.commonSearch.searchHql(hql, params);
	}


	@Override
	public List<User> findAll() {
		String hql=" from User";
		return super.commonSearch.searchHql(hql, null);
	}


}
