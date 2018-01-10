package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDtlDTO;
import com.huatuo.common.BaseResponse;

public class OrderCheckDtlResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private List<CmsJgOrdercheckDtlDTO> list;
	

	public List<CmsJgOrdercheckDtlDTO> getList() {
		return list;
	}

	public void setList(List<CmsJgOrdercheckDtlDTO> list) {
		this.list = list;
	}



		
}
