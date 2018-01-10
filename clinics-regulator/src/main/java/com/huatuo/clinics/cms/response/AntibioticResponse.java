package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.TaPhaDictDrugAntibioticLevelPolityDTO;
import com.huatuo.common.BaseResponse;

public class AntibioticResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private List<TaPhaDictDrugAntibioticLevelPolityDTO> list;

	public List<TaPhaDictDrugAntibioticLevelPolityDTO> getList() {
		return list;
	}

	public void setList(List<TaPhaDictDrugAntibioticLevelPolityDTO> list) {
		this.list = list;
	}
	
	
	


		
}
