package com.web.biz;

import java.util.List;

import com.web.entity.Classes;
import com.web.entity.Teacher;
/**
 * 班级业务逻辑
 * @author cheng
 *
 */
public interface ClassesBiz extends CommonBizPage<Classes>{
	
	/**
	 *@author johen.deng 
	 */
	public List<Classes> findAll();
	
	public List<Classes> findByTeacher(Teacher teacher);
}
