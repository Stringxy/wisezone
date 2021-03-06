package com.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.MenuBiz;
import com.web.dao.MenuDao;
import com.web.entity.Menu;

@Service
public class MenuBizImpl extends CommonBizImpl<Menu> implements MenuBiz{

	
	private MenuDao menuDao= null;
	@Resource(name="menuDaoImpl")
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
		super.setCommonDao(menuDao);
	}
	
	
}
