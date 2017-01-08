package com.web.biz;

import java.util.List;

import com.web.entity.Teacher;

public interface TeacherBiz extends CommonBizPage<Teacher> {

	public  Teacher login(String loginName,String loginPwd);
	public Teacher findByName(String loginName);
	/**
	 * 教师全查询业务逻辑层
	 * @return
	 */
	public List<Teacher> findAll();
	
	public boolean isVaild(String loginName);
}
