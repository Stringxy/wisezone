package com.web.biz.impl;

import java.util.Map;





import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.biz.StageTestBiz;
import com.web.dao.StageTestDao;
import com.web.entity.StageTest;

@Service
public class StageTestBizImpl extends CommonBizPageImpl<StageTest>  implements StageTestBiz{

	private StageTestDao stageTestDao= null;
	@Resource(name="stageTestDaoImpl")
	public void setStageTestDao(StageTestDao stageTestDao) {
		this.stageTestDao = stageTestDao;
		super.setCommonDaoPage(stageTestDao);
	}


}
