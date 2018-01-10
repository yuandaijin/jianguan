package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.common.BaseResponse;

public class AdressResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private List<CmsJgDistrictDTO> list;
	
	private CmsJgOrgDTO dto;
	
	

	public CmsJgOrgDTO getDto() {
		return dto;
	}

	public void setDto(CmsJgOrgDTO dto) {
		this.dto = dto;
	}

	public List<CmsJgDistrictDTO> getList() {
		return list;
	}

	public void setList(List<CmsJgDistrictDTO> list) {
		this.list = list;
	}


		
}
