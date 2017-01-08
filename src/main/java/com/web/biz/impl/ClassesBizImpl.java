package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.ClassesBiz;
import com.web.dao.ClassesDao;
import com.web.entity.Classes;
import com.web.entity.Teacher;
/**
 * 班级业务逻辑实现
 * @author cheng
 *
 */
@Service
public class ClassesBizImpl extends CommonBizPageImpl<Classes> implements ClassesBiz{

	
	private ClassesDao classesdao =null;
	@Resource(name="classesDaoImpl")
	public void setClassesdao(ClassesDao classesdao) {
		this.classesdao = classesdao;
		
		super.setCommonDaoPage(classesdao);
	}
	
	/**
	 * @author johen.deng
	 */
	@Override
	public List<Classes> findAll() {
		return this.classesdao.findAll();
	}

	@Override
	public List<Classes> findByTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return this.classesdao.findByTeacher(teacher);
	}
	
	
	
}
