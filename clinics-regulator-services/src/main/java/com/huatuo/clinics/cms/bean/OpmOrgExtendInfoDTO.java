package com.huatuo.clinics.cms.bean;

import java.util.Date;

/**
 * 诊所扩展信息模型
 * @author hy
 *
 */
public class OpmOrgExtendInfoDTO {
	private String orgId;//机构id
	private String fundSource;//主办单位
	private String orgSubType;//机构子类型
	private Integer orgDoctorCount;//机构医生数量
	private Integer orgNurseCount;//机构护士数量
	private Integer orgOtherCount;//机构其它人员数量
	private Integer orgLevel;//机构评价级别
	private Date orgCredentialsApply;//机构资质签发日期
	private String orgCredentialsApplyStr;//机构资质签发日期
	private Date orgCredentialsTime;//机构资质到期日期
	private String orgCredentialsTimeStr;//机构资质到期日期
    private String orgCredentialsNo;//机构资质编号
    private String orgCredentialsPerson;//机构法人
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getFundSource() {
		return fundSource;
	}
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	public String getOrgSubType() {
		return orgSubType;
	}
	public void setOrgSubType(String orgSubType) {
		this.orgSubType = orgSubType;
	}
	public Integer getOrgDoctorCount() {
		return orgDoctorCount;
	}
	public void setOrgDoctorCount(Integer orgDoctorCount) {
		this.orgDoctorCount = orgDoctorCount;
	}
	public Integer getOrgNurseCount() {
		return orgNurseCount;
	}
	public void setOrgNurseCount(Integer orgNurseCount) {
		this.orgNurseCount = orgNurseCount;
	}
	public Integer getOrgOtherCount() {
		return orgOtherCount;
	}
	public void setOrgOtherCount(Integer orgOtherCount) {
		this.orgOtherCount = orgOtherCount;
	}
	public Integer getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}
	public Date getOrgCredentialsTime() {
		return orgCredentialsTime;
	}
	public void setOrgCredentialsTime(Date orgCredentialsTime) {
		this.orgCredentialsTime = orgCredentialsTime;
	}
	public String getOrgCredentialsNo() {
		return orgCredentialsNo;
	}
	public void setOrgCredentialsNo(String orgCredentialsNo) {
		this.orgCredentialsNo = orgCredentialsNo;
	}
	public Date getOrgCredentialsApply() {
		return orgCredentialsApply;
	}
	public void setOrgCredentialsApply(Date orgCredentialsApply) {
		this.orgCredentialsApply = orgCredentialsApply;
	}
	public String getOrgCredentialsPerson() {
		return orgCredentialsPerson;
	}
	public void setOrgCredentialsPerson(String orgCredentialsPerson) {
		this.orgCredentialsPerson = orgCredentialsPerson;
	}
	public String getOrgCredentialsApplyStr() {
		return orgCredentialsApplyStr;
	}
	public void setOrgCredentialsApplyStr(String orgCredentialsApplyStr) {
		this.orgCredentialsApplyStr = orgCredentialsApplyStr;
	}
	public String getOrgCredentialsTimeStr() {
		return orgCredentialsTimeStr;
	}
	public void setOrgCredentialsTimeStr(String orgCredentialsTimeStr) {
		this.orgCredentialsTimeStr = orgCredentialsTimeStr;
	}
}
