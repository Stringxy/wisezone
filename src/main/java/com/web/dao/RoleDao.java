package com.web.dao;

import java.util.List;

import com.web.entity.Role;

public interface RoleDao extends CommonDaoPage<Role>{

	
	public  List<Role> findAll();
}
