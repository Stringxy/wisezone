package com.web.biz;

import com.web.entity.Classes;
import com.web.entity.Teacher;
import com.web.entity.TeacherClasses;

public interface TeacherClassesBiz extends CommonBizPage<TeacherClasses>{

	public TeacherClasses findByClasses(Classes classes, Teacher teacher);
}
