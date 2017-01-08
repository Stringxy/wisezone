package com.web.dao;

import java.util.List;

import com.web.entity.Classes;
import com.web.entity.Teacher;
/**
 * 班级数据访问
 * @author cheng
 *
 */
public interface ClassesDao extends CommonDaoPage<Classes>{
	/**
	 * @author johen.deng
	 * @return list<>
	 */
	public List<Classes> findAll();
	
	public  List<Classes> findByTeacher(Teacher teacher);
}
