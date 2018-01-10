package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDtlDTO;
import com.huatuo.clinics.cms.bean.CsOutpVisitDTO;
import com.huatuo.common.BaseResponse;

public class OrderCheckResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private CsOutpVisitDTO dto;

	private List<String> str;
	private String orgId;
	private String orgName;
	private List<CmsJgOrdercheckDtlDTO> list;
	private CmsJgOrdercheckDTO checkDto;
	
	
	
	
	
	public CmsJgOrdercheckDTO getCheckDto() {
		return checkDto;
	}

	public void setCheckDto(CmsJgOrdercheckDTO checkDto) {
		this.checkDto = checkDto;
	}

	public List<CmsJgOrdercheckDtlDTO> getList() {
		return list;
	}

	public void setList(List<CmsJgOrdercheckDtlDTO> list) {
		this.list = list;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public CsOutpVisitDTO getDto() {
		return dto;
	}

	public void setDto(CsOutpVisitDTO dto) {
		this.dto = dto;
	}


	public List<String> getStr() {
		return str;
	}

	public void setStr(List<String> str) {
		this.str = str;
	}
	


		
}
