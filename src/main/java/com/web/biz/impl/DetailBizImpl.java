package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.DetailBiz;
import com.web.dao.DetailDao;
import com.web.entity.Check;
import com.web.entity.Detail;

@Service
public class DetailBizImpl extends CommonBizPageImpl<Detail> implements DetailBiz {
	
	private DetailDao detailDao;
	
	@Resource(name="detailDaoImpl")
	public void setDetailDao(DetailDao detailDao){
		this.detailDao=detailDao;
		super.setCommonDaoPage(detailDao);
	}

	@Override
	public List<Detail> findByCheck(Check check) {
		// TODO Auto-generated method stub
		return this.detailDao.findByCheck(check);
	}

	@Override
	public Detail findByName(String name) {
		// TODO Auto-generated method stub
		return this.detailDao.findByName(name);
	}

	@Override
	public boolean isVaild(String name) {
		// TODO Auto-generated method stub
		return this.detailDao.findByName(name)==null;
	}

	@Override
	public List<Detail> findAll() {
		// TODO Auto-generated method stub
		return this.detailDao.findAll();
	}

}
