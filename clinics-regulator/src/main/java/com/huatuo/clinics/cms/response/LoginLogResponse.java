package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.XtLoginLog;
import com.huatuo.common.BaseResponse;

public class LoginLogResponse extends BaseResponse{
	private List<XtLoginLog> logs;

	public List<XtLoginLog> getLogs() {
		return logs;
	}

	public void setLogs(List<XtLoginLog> logs) {
		this.logs = logs;
	}
	
}
