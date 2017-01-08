package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.TeacherClassesBiz;
import com.web.dao.TeacherClassesDao;
import com.web.entity.Classes;
import com.web.entity.Teacher;
import com.web.entity.TeacherClasses;
@Service
public class TeacherClassesBizImpl extends CommonBizPageImpl<TeacherClasses> implements TeacherClassesBiz{

	
	private TeacherClassesDao tedao= null;
	@Resource(name="teacherClassesDaoImpl")
	public void setTedao(TeacherClassesDao tedao) {
		this.tedao = tedao;
		
		super.setCommonDaoPage(tedao);
	}
	@Override
	public TeacherClasses findByClasses(Classes classes, Teacher teacher) {
		// TODO Auto-generated method stub
		return this.tedao.findByClasses(classes, teacher);
	}
	
	
}
