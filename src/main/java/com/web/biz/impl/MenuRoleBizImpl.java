package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.MenuRoleBiz;
import com.web.dao.MenuRoleDao;
import com.web.entity.MenuRole;
@Service
public class MenuRoleBizImpl extends CommonBizPageImpl<MenuRole> implements MenuRoleBiz{

	
	private MenuRoleDao menuRoleDao= null;
	@Resource(name="menuRoleDaoImpl")
	public void setMenuRoleDao(MenuRoleDao menuRoleDao) {
		this.menuRoleDao = menuRoleDao;
		super.setCommonDaoPage(menuRoleDao);
	}
	
	
}
