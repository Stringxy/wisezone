package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.AttendanceBiz;
import com.web.dao.AttendanceDao;
import com.web.entity.Attendance;
import com.web.entity.Classes;


@Service
public class AttendanceBizImpl extends CommonBizPageImpl<Attendance> implements
		AttendanceBiz {

	private AttendanceDao dao=null;
	
	@Resource(name="attendanceDaoImpl")
	public void setAttendanceDao(AttendanceDao dao){
		this.dao=dao;
		super.setCommonDaoPage(dao);
	}

	@Override
	public boolean isExist(Integer year,Integer Month,Classes classes) {
		// TODO Auto-generated method stub
		List<Attendance>arr=dao.isExist(year, Month, classes);
		return arr!=null&&arr.size()>0;
	}

	@Override
	public List<Attendance> findByParams(Integer year, Integer Month,
			Classes classes) {
		// TODO Auto-generated method stub
		return dao.isExist(year, Month, classes);
	}

	
}
