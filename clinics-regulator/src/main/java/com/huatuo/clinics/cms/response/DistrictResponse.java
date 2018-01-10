package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.common.BaseResponse;

public class DistrictResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private List<CmsJgDistrictDTO> list;

	public List<CmsJgDistrictDTO> getList() {
		return list;
	}

	public void setList(List<CmsJgDistrictDTO> list) {
		this.list = list;
	}


		
}
