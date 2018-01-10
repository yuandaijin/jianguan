package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;
import com.huatuo.common.BaseResponse;

public class RoleInfoResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private List<CmsJgMenuInfoDTO> list;

	public List<CmsJgMenuInfoDTO> getList() {
		return list;
	}

	public void setList(List<CmsJgMenuInfoDTO> list) {
		this.list = list;
	}


		
}
