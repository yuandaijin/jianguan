package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.common.BaseResponse;

public class ReportResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	private List<ReportResultDTO> list;
	private List<ReportResultDTO> totallist;//门诊总量
	private List<ReportResultDTO> efficiencylist;//服务效率
	private List<ReportResultDTO> costlist;//服务费用
	private List<ReportResultDTO> anlist;//同比
	private List<ReportResultDTO> momlist;//环比
	private List<ReportResultDTO> typelist;//不同类型诊所服务人次
	private List<ReportResultDTO> scalelist;//不同规模诊所服务人次
	private List<ReportResultDTO> avglist;//平均服务效率
	private String year;
	private String month;
	private List<String> str;
	private String sum;//多发病的总数量
	
	
	
	


	public List<ReportResultDTO> getAvglist() {
		return avglist;
	}

	public void setAvglist(List<ReportResultDTO> avglist) {
		this.avglist = avglist;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public List<String> getStr() {
		return str;
	}

	public void setStr(List<String> str) {
		this.str = str;
	}

	public List<ReportResultDTO> getCostlist() {
		return costlist;
	}

	public void setCostlist(List<ReportResultDTO> costlist) {
		this.costlist = costlist;
	}

	public List<ReportResultDTO> getTypelist() {
		return typelist;
	}

	public void setTypelist(List<ReportResultDTO> typelist) {
		this.typelist = typelist;
	}

	public List<ReportResultDTO> getScalelist() {
		return scalelist;
	}

	public void setScalelist(List<ReportResultDTO> scalelist) {
		this.scalelist = scalelist;
	}

	public List<ReportResultDTO> getEfficiencylist() {
		return efficiencylist;
	}

	public void setEfficiencylist(List<ReportResultDTO> efficiencylist) {
		this.efficiencylist = efficiencylist;
	}

	public List<ReportResultDTO> getList() {
		return list;
	}

	public void setList(List<ReportResultDTO> list) {
		this.list = list;
	}

	public List<ReportResultDTO> getTotallist() {
		return totallist;
	}

	public void setTotallist(List<ReportResultDTO> totallist) {
		this.totallist = totallist;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<ReportResultDTO> getAnlist() {
		return anlist;
	}

	public void setAnlist(List<ReportResultDTO> anlist) {
		this.anlist = anlist;
	}

	public List<ReportResultDTO> getMomlist() {
		return momlist;
	}

	public void setMomlist(List<ReportResultDTO> momlist) {
		this.momlist = momlist;
	}

	


		
}
