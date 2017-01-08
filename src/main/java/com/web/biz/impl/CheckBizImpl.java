package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.CheckBiz;
import com.web.dao.CheckDao;
import com.web.entity.Check;
import com.web.entity.Major;

@Service
public class CheckBizImpl extends CommonBizPageImpl<Check> implements CheckBiz {

	private CheckDao checkDao=null;
	
	@Resource(name="checkDaoImpl")
	public void setCheckDao(CheckDao checkDao){
		this.checkDao=checkDao;
		super.setCommonDaoPage(checkDao);
	}
	@Override
	public List<Check> findByMajor(Major major) {
		// TODO Auto-generated method stub
		return this.checkDao.findByMajor(major);
	}
	@Override
	public List<Check> findAll() {
		
		return this.checkDao.findAll();
	}

}
