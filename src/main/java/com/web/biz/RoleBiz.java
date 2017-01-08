package com.web.biz;

import java.util.List;

import com.web.entity.Role;

public interface RoleBiz extends CommonBizPage<Role>{

	
	public List<Role> findAll();
}
