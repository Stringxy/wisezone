package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.web.biz.EmployeeInfoBiz;
import com.web.dao.EmployeeInfoDao;
import com.web.entity.EmployeeInfo;
@Service

public class EmployeeInfoBizImpl extends CommonBizPageImpl<EmployeeInfo> implements EmployeeInfoBiz{

	
	private EmployeeInfoDao empDao= null;
	@Resource(name="employeeInfoDaoImpl")
	public void setEmpDao(EmployeeInfoDao empDao) {
		this.empDao = empDao;
		super.setCommonDaoPage(empDao);
	}
	
}
