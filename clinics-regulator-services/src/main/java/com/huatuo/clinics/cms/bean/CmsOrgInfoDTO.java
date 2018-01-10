package com.huatuo.clinics.cms.bean;

public class CmsOrgInfoDTO {
	private String orgId; // 机构id
	
	private String orgName;// 机构名称

	private String address;// 详细地址

	private String telephone;// 联系电话

	private String orgCredentialsApply; // 资质申请时间

	private String orgCredentialsTime; // 资质到期时间

	private Integer patrolStatus; // 巡查状态 0 未巡查 1 已巡查

	private Integer expireStatus; // 到期状态 0正常 1已到期 2还有30天到期
	
	private String linkMan; // 机构联系人
	
	private String LatelyPatrolTime; //  最近的巡查时间
	
	private String lastPatrolTime; // 上一次巡查时间
	
	private String orgCredentialsNo;//机构资质编号
	
	//private Integer expirationDays; // 距离过期时间还差多少天
	
	/**
	 * 经度
	 */
	private double longitude;
	
	/**
	 * 纬度
	 */
	private double latitude;

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOrgCredentialsApply() {
		return orgCredentialsApply;
	}

	public void setOrgCredentialsApply(String orgCredentialsApply) {
		this.orgCredentialsApply = orgCredentialsApply;
	}

	public String getOrgCredentialsTime() {
		return orgCredentialsTime;
	}

	public void setOrgCredentialsTime(String orgCredentialsTime) {
		this.orgCredentialsTime = orgCredentialsTime;
	}

	public Integer getPatrolStatus() {
		return patrolStatus;
	}

	public void setPatrolStatus(Integer patrolStatus) {
		this.patrolStatus = patrolStatus;
	}

	public Integer getExpireStatus() {
		return expireStatus;
	}

	public void setExpireStatus(Integer expireStatus) {
		this.expireStatus = expireStatus;
	}

	/*public Integer getExpirationDays() {
		return expirationDays;
	}

	public void setExpirationDays(Integer expirationDays) {
		this.expirationDays = expirationDays;
	}*/

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLatelyPatrolTime() {
		return LatelyPatrolTime;
	}

	public void setLatelyPatrolTime(String latelyPatrolTime) {
		LatelyPatrolTime = latelyPatrolTime;
	}

	public String getLastPatrolTime() {
		return lastPatrolTime;
	}

	public void setLastPatrolTime(String lastPatrolTime) {
		this.lastPatrolTime = lastPatrolTime;
	}

	public String getOrgCredentialsNo() {
		return orgCredentialsNo;
	}

	public void setOrgCredentialsNo(String orgCredentialsNo) {
		this.orgCredentialsNo = orgCredentialsNo;
	}

}