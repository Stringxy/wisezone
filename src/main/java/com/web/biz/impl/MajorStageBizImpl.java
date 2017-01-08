package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.MajorStageBiz;
import com.web.dao.MajorStageDao;
import com.web.entity.MajorStage;
@Service 
public class MajorStageBizImpl extends CommonBizPageImpl<MajorStage> implements MajorStageBiz{

	
	private MajorStageDao majorstagedao=null;
	@Resource(name="majorStageDaoImpl")
	public void setMajorstagedao(MajorStageDao majorstagedao) {
		this.majorstagedao = majorstagedao;
		super.setCommonDao(majorstagedao);
		super.setCommonDaoPage(majorstagedao);
	}
	@Override
	public List<MajorStage> findAll() {
		// TODO Auto-generated method stub
		return this.majorstagedao.findAll();
	}
	
}
