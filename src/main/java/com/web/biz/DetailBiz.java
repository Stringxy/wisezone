package com.web.biz;

import java.util.List;

import com.web.entity.Check;
import com.web.entity.Detail;

public interface DetailBiz extends CommonBizPage<Detail> {
	public List<Detail> findByCheck(Check check);
	public Detail findByName(String name);
	public boolean isVaild(String name);
	public List<Detail> findAll();
}
