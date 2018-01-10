package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.CmsJgMonitor;
import com.huatuo.common.BaseResponse;
/**
 * 监测事件具体情况
 * @author ydj
 *
 */
public class MonitoringEventsResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	
	private List<CmsJgMonitor>  list;

	public List<CmsJgMonitor> getList() {
		return list;
	}

	public void setList(List<CmsJgMonitor> list) {
		this.list = list;
	}
	
	
	
	
	
	
}
