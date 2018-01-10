package com.huatuo.clinics.cms.bean;


public class CmsJgPatrolDTO {

	private String id; // 主键

	private String orgId; // 机构id

	private String address;// 机构地址

	private String orgCredentialsPerson;// 机构负责人

	private String patrolMan;// 巡查人

	private String patrolOfficer;// 巡查负责人

	private Integer finishStatus;// 巡查完成标志(0未完成 1已完成)

	private String patrolTime; // 巡查时间

	private String planTime; // 计划下次巡查时间

	private String patrolResult; // 巡查结果

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrgCredentialsPerson() {
		return orgCredentialsPerson;
	}

	public void setOrgCredentialsPerson(String orgCredentialsPerson) {
		this.orgCredentialsPerson = orgCredentialsPerson;
	}

	public String getPatrolMan() {
		return patrolMan;
	}

	public void setPatrolMan(String patrolMan) {
		this.patrolMan = patrolMan;
	}

	public String getPatrolOfficer() {
		return patrolOfficer;
	}

	public void setPatrolOfficer(String patrolOfficer) {
		this.patrolOfficer = patrolOfficer;
	}

	public Integer getFinishStatus() {
		return finishStatus;
	}

	public void setFinishStatus(Integer finishStatus) {
		this.finishStatus = finishStatus;
	}

	public String getPatrolTime() {
		return patrolTime;
	}

	public void setPatrolTime(String patrolTime) {
		this.patrolTime = patrolTime;
	}

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getPatrolResult() {
		return patrolResult;
	}

	public void setPatrolResult(String patrolResult) {
		this.patrolResult = patrolResult;
	}

}