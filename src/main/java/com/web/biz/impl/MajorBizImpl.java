package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.MajorBiz;
import com.web.dao.MajorDao;
import com.web.entity.Major;
/**
 * 专业业务逻辑实现
 * @author cheng
 *
 */
@Service
public class MajorBizImpl extends CommonBizPageImpl<Major> implements MajorBiz{

	
	private MajorDao majordao= null;
	@Resource(name="majorDaoImpl")
	public void setMajordao(MajorDao majordao) {
		this.majordao = majordao;
		
		super.setCommonDaoPage(majordao);
	}
	@Override
	public List<Major> findAll() {
		// TODO Auto-generated method stub
		return this.majordao.findAll();
	}
	
	
	
}
