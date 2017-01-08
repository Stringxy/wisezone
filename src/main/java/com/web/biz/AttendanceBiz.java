package com.web.biz;

import java.util.List;

import com.web.entity.Attendance;
import com.web.entity.Classes;

public interface AttendanceBiz extends CommonBizPage<Attendance> {

	public boolean isExist(Integer year,Integer Month,Classes classes) ;
	
	public List<Attendance> findByParams(Integer year,Integer Month,Classes classes);
}
