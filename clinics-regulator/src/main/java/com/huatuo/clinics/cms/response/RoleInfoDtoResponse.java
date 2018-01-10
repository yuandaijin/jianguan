package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgRoleinfoDTO;
import com.huatuo.common.BaseResponse;

public class RoleInfoDtoResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private List<CmsJgRoleinfoDTO> list;

	public List<CmsJgRoleinfoDTO> getList() {
		return list;
	}

	public void setList(List<CmsJgRoleinfoDTO> list) {
		this.list = list;
	}


		
}
