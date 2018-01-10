package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.OperateInfoDTO;
import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.common.BaseResponse;

public class OperateTableResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private List<OperateInfoDTO> list;
	private List<List<ReportResultDTO>> resultList;
	
	
	





	public List<List<ReportResultDTO>> getResultList() {
		return resultList;
	}

	public void setResultList(List<List<ReportResultDTO>> resultList) {
		this.resultList = resultList;
	}

	public List<OperateInfoDTO> getList() {
		return list;
	}

	public void setList(List<OperateInfoDTO> list) {
		this.list = list;
	}
	
	
	


		
}
