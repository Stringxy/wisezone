package com.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.RoleBiz;
import com.web.dao.RoleDao;
import com.web.entity.Role;
@Service
public class RoleBizImpl extends CommonBizPageImpl<Role> implements RoleBiz{

	
	private RoleDao roledao= null;
	@Resource(name="roleDaoImpl")
	public void setRoledao(RoleDao roledao) {
		this.roledao = roledao;

		super.setCommonDaoPage(roledao);
	}
	@Override
	public List<Role> findAll() {
		
		return this.roledao.findAll();
	}
		
}
