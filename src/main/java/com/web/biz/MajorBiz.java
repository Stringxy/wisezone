package com.web.biz;

import java.util.List;

import com.web.entity.Major;
/**
 * 专业业务逻辑
 * @author cheng
 *
 */
public interface MajorBiz extends CommonBizPage<Major>{

	public List<Major> findAll();
}
