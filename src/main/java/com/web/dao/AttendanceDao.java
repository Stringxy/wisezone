package com.web.dao;

import java.util.List;

import com.web.entity.Attendance;
import com.web.entity.Classes;

public interface AttendanceDao extends CommonDaoPage<Attendance>{

	
	public List<Attendance> isExist(Integer year,Integer Month,Classes classes);
}
