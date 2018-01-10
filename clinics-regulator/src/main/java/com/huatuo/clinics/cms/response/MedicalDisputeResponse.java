package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.clinics.cms.bean.DisputeRepostDTO;
import com.huatuo.common.BaseResponse;

public class MedicalDisputeResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088283L;
	
	private List<DisputeRepostDTO> certified;//已鉴定
	private List<DisputeRepostDTO> unidentified;//未鉴定
	private List<CmsJgDistrictDTO> addList;



	public List<DisputeRepostDTO> getCertified() {
		return certified;
	}

	public void setCertified(List<DisputeRepostDTO> certified) {
		this.certified = certified;
	}

	public List<DisputeRepostDTO> getUnidentified() {
		return unidentified;
	}

	public void setUnidentified(List<DisputeRepostDTO> unidentified) {
		this.unidentified = unidentified;
	}

	public List<CmsJgDistrictDTO> getAddList() {
		return addList;
	}

	public void setAddList(List<CmsJgDistrictDTO> addList) {
		this.addList = addList;
	}
		
	
}
