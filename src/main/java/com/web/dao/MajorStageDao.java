package com.web.dao;

import java.util.List;

import com.web.entity.MajorStage;

public interface MajorStageDao extends CommonDaoPage<MajorStage>{

	public List<MajorStage> findAll();
}
