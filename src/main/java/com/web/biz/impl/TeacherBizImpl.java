package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.TeacherBiz;
import com.web.dao.TeacherDao;
import com.web.entity.Teacher;

/**
 * 
 * @author Xy
 *
 */
@Service //教师业务逻辑层注解
public class TeacherBizImpl extends CommonBizPageImpl<Teacher> implements TeacherBiz {

	private TeacherDao teacherDao=null;
	
	
	@Resource(name="teacherDaoImpl")
	public void setTeacherDao(TeacherDao teacherDao){
		this.teacherDao=teacherDao;
		super.setCommonDaoPage(teacherDao);
	}
	

	@Override
	public Teacher login(String loginName, String loginPwd) {
		// TODO Auto-generated method stub
		return this.teacherDao.login(loginName, loginPwd);
	}


	@Override
	public Teacher findByName(String loginName) {
		// TODO Auto-generated method stub
		return this.teacherDao.findByName(loginName);
	}

//johen添加教师全查
	@Override
	public List<Teacher> findAll() {
		// TODO Auto-generated method stub
		return this.teacherDao.findAll();
	}


	@Override
	public boolean isVaild(String loginName) {
		// TODO Auto-generated method stub
		return this.teacherDao.findByName(loginName)==null;
	}

}
