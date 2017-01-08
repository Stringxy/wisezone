package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.UserBiz;
import com.web.dao.UserDao;
import com.web.entity.Classes;
import com.web.entity.Major;
import com.web.entity.User;


@Service
public class UserBizImpl extends CommonBizPageImpl<User> implements UserBiz {

	private UserDao userDao;
	
	
	@Resource(name="userDaoImpl")
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
		super.setCommonDaoPage(userDao);
	}
	
	@Override
	public User login(String stuName, String password) {
		// TODO Auto-generated method stub
		return this.userDao.login(stuName, password);
	}

	@Override
	public User findByname(String stuName) {
		// TODO Auto-generated method stub
		return this.userDao.findByname(stuName);
	}

	@Override
	public boolean isVaild(String stuName) {
		// TODO Auto-generated method stub
		return this.userDao.findByname(stuName)==null;
	}

	@Override
	public List<User> findByClasses(Classes classes) {
		// TODO Auto-generated method stub
		return this.userDao.findByClasses(classes);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.userDao.findAll();
	}

	

}
