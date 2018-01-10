package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.DynamicDetectionDTO;
import com.huatuo.common.BaseResponse;
/**
 * 实时监测
 * @author ydj
 *
 */
public class DynamicDetectionResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	
	private List<List<DynamicDetectionDTO>>   list;//用于存放当天数据
	private List<List<DynamicDetectionDTO>>   list2;//用于存放用户退出系统期间的数据
	private String  startDate;
	private String  endDate;
	private String date;
	private Integer flag=0;
	
	

	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<List<DynamicDetectionDTO>> getList() {
		return list;
	}
	public void setList(List<List<DynamicDetectionDTO>> list) {
		this.list = list;
	}
	public List<List<DynamicDetectionDTO>> getList2() {
		return list2;
	}
	public void setList2(List<List<DynamicDetectionDTO>> list2) {
		this.list2 = list2;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
