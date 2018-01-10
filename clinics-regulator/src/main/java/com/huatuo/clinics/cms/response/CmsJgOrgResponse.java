package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.common.BaseResponse;

public class CmsJgOrgResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private List<CmsJgOrgDTO> list;

	public List<CmsJgOrgDTO> getList() {
		return list;
	}

	public void setList(List<CmsJgOrgDTO> list) {
		this.list = list;
	}


		
}
