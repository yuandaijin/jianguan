package com.huatuo.clinics.cms.bean;
/**
 *监测事件具体情况bean 
 * @author ydj
 *
 */
public class MonitoringEventsDTO {
   
	private String id;//事件id
	private String date;//发生时间
	private String regionId;//发生地区
	private String region;//发生地区
	private String type;//医疗卫生行为
	private String orgId;//机构id
	private String orgName;//机构名称
	private String address;//详细地址
	private String name ;//事件详情需要的信息
	private String eventDtl;//事实详情
	private String doctorId;//医生id
	
	
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEventDtl() {
		return eventDtl;
	}
	public void setEventDtl(String eventDtl) {
		this.eventDtl = eventDtl;
	}
	
	
	



}