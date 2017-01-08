package com.web.biz;

import java.util.List;

import com.web.entity.Check;
import com.web.entity.Major;

public interface CheckBiz extends CommonBizPage<Check> {

	public List<Check> findByMajor(Major major);
	public List<Check> findAll();
}
