package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.IntegratedProjectBiz;
import com.web.dao.IntegratedProjectDao;
import com.web.entity.IntegratedProject;

@Service
public class IntegratedProjectBizImpl extends CommonBizPageImpl<IntegratedProject> implements IntegratedProjectBiz{

	
	private  IntegratedProjectDao interDao= null;
	@Resource(name="integratedProjectDaoImpl")
	public void setInterDao(IntegratedProjectDao interDao) {
		this.interDao = interDao;
		super.setCommonDaoPage(interDao);
	}
	
}
