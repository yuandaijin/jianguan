package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.common.BaseResponse;

public class ResidentsResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	private List<ReportResultDTO> list;
	private List<ReportResultDTO> aidslsit;//艾滋病
	private List<ReportResultDTO> hbvlist;//乙肝
	private List<ReportResultDTO> hfmdlist;//手足口
	private String sum;//所有病种的总数量
	private List<ReportResultDTO> sumlist;//各种病种的总数量
	
	
	


	public List<ReportResultDTO> getSumlist() {
		return sumlist;
	}

	public void setSumlist(List<ReportResultDTO> sumlist) {
		this.sumlist = sumlist;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public List<ReportResultDTO> getAidslsit() {
		return aidslsit;
	}

	public void setAidslsit(List<ReportResultDTO> aidslsit) {
		this.aidslsit = aidslsit;
	}

	public List<ReportResultDTO> getHbvlist() {
		return hbvlist;
	}

	public void setHbvlist(List<ReportResultDTO> hbvlist) {
		this.hbvlist = hbvlist;
	}

	public List<ReportResultDTO> getHfmdlist() {
		return hfmdlist;
	}

	public void setHfmdlist(List<ReportResultDTO> hfmdlist) {
		this.hfmdlist = hfmdlist;
	}

	public List<ReportResultDTO> getList() {
		return list;
	}

	public void setList(List<ReportResultDTO> list) {
		this.list = list;
	}



		
}
