package com.web.dao;

import com.web.entity.Classes;
import com.web.entity.Teacher;
import com.web.entity.TeacherClasses;

public interface TeacherClassesDao extends CommonDaoPage<TeacherClasses>{
	
	public TeacherClasses findByClasses(Classes classes,Teacher teacher);
}
