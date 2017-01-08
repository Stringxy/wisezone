package com.web.biz;

import java.util.List;

import com.web.entity.RecordHistory;
import com.web.entity.RecordHistoryDetail;

public interface RecordHistoryDetailBiz extends CommonBiz<RecordHistoryDetail> {

	public List<RecordHistoryDetail> findByRecord(RecordHistory rh);
}
