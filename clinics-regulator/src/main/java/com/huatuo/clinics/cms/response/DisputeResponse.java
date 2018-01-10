package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgDisputeDTO;
import com.huatuo.common.BaseResponse;
import com.huatuo.pms.bo.BOZdOrg;
/**
 * 医疗争议登记返回数据
 * @author 河南华佗在线
 *
 */
public class DisputeResponse extends BaseResponse {
	private static final long serialVersionUID = 1L;
	private List<CmsJgDisputeDTO> list; //医疗争议登记
	private String userId;//当前用户id
	private List<BOZdOrg> orgList;//机构信息
	private CmsJgDisputeDTO dto;
	
	
	
	public CmsJgDisputeDTO getDto() {
		return dto;
	}

	public void setDto(CmsJgDisputeDTO dto) {
		this.dto = dto;
	}

	public List<CmsJgDisputeDTO> getList() {
		return list;
	}

	public void setList(List<CmsJgDisputeDTO> list) {
		this.list = list;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<BOZdOrg> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<BOZdOrg> orgList) {
		this.orgList = orgList;
	}
	
}
