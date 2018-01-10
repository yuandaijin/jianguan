package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDTO;
import com.huatuo.common.BaseResponse;

public class OrderCheckRecordResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private List<CmsJgOrdercheckDTO> list;
	

	public List<CmsJgOrdercheckDTO> getList() {
		return list;
	}

	public void setList(List<CmsJgOrdercheckDTO> list) {
		this.list = list;
	}



		
}
