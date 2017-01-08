package com.web.biz;

import java.util.List;

import com.web.entity.Classes;
import com.web.entity.Major;
import com.web.entity.User;

public interface UserBiz extends CommonBizPage<User> {
	public User login(String stuName, String password);
	public User findByname(String stuName);
	public boolean isVaild(String stuName);
	public List<User> findByClasses(Classes classes);
	public List<User> findAll();}
