package com.huatuo.clinics.cms.bean;

public class CmsJgOrdercheckDtlDTO {
    private String id;//明细id
    private String orderId;//外键id
    private String vistId;//处方id
    private String orderDate;//处方开具日期
    private String checkParts;//抽查地区
    private String orgId;//抽查诊所id
    private String orgName;//抽查诊所名称
    private String doctorId;//医生id
    private String doctorName;//医生名称
    private Integer checkResult;//是否合格,0：合格,1：不合格
    private String unqualifiedReason;//不合格原因
    private String remarks;//备注
    
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getVistId() {
		return vistId;
	}
	public void setVistId(String vistId) {
		this.vistId = vistId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getCheckParts() {
		return checkParts;
	}
	public void setCheckParts(String checkParts) {
		this.checkParts = checkParts;
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
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Integer getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}
	public String getUnqualifiedReason() {
		return unqualifiedReason;
	}
	public void setUnqualifiedReason(String unqualifiedReason) {
		this.unqualifiedReason = unqualifiedReason;
	}
   
    
    
    
    
}