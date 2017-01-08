package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.ClassTestBiz;
import com.web.dao.ClassTestDao;
import com.web.entity.ClassTest;
@Service
public class ClassTestBizImpl extends CommonBizPageImpl<ClassTest> implements ClassTestBiz{

	private ClassTestDao ctd= null;
	@Resource(name="classTestDaoImpl")
	public void setCtd(ClassTestDao ctd) {
		this.ctd = ctd;
		super.setCommonDaoPage(ctd);
	}
	
	
	
	
	
}
