package com.web.dao;

import java.util.List;

import com.web.entity.Teacher;

public interface TeacherDao extends CommonDaoPage<Teacher> {

	public Teacher login(String loginName,String loginPwd);
	
	public Teacher findByName(String loginName);
	/**教师全查
	 * @author johen.deng
	 * @return
	 */
	public List<Teacher> findAll();

}
