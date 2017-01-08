package com.web.biz;

import java.util.List;

import com.web.entity.MajorStage;

public interface MajorStageBiz extends CommonBizPage<MajorStage>{

	public List<MajorStage> findAll();
}
