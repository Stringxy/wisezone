package com.web.dao;

import java.util.List;

import com.web.entity.RecordHistory;
import com.web.entity.RecordHistoryDetail;

public interface RecordHistoryDetailDao extends CommonDao<RecordHistoryDetail> {

	public List<RecordHistoryDetail> findByRecord(RecordHistory rh);
}
