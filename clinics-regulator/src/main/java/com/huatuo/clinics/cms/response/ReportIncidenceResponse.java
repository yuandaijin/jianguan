package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.common.BaseResponse;

public class ReportIncidenceResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	private List<ReportResultDTO> list;
	private List<ReportResultDTO> sexList;//性别
	private List<ReportResultDTO> ageList;//年龄
	
	
	
	public List<ReportResultDTO> getSexList() {
		return sexList;
	}
	public void setSexList(List<ReportResultDTO> sexList) {
		this.sexList = sexList;
	}
	public List<ReportResultDTO> getAgeList() {
		return ageList;
	}
	public void setAgeList(List<ReportResultDTO> ageList) {
		this.ageList = ageList;
	}
	public List<ReportResultDTO> getList() {
		return list;
	}
	public void setList(List<ReportResultDTO> list) {
		this.list = list;
	}


		
}
