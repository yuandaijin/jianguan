package com.huatuo.clinics.cms.bean;

import java.util.Date;
import java.util.List;

import com.huatuo.clinics.cms.services.translate.Translatable;
import com.huatuo.clinics.cms.services.translate.TranslateField;

public class CmsJgOrdercheckDTO implements Translatable {
    private String checkId;//主键id
    private String checkDate;//抽查时段
    private String checkArea;//抽查地区
    private String orgId;//抽查诊所id
    private String orgName;//抽查诊所名称
    private String orderTypeName;//抽查处方类型
    @TranslateField(fullQualifiedName="20107",targetFieldName="orderTypeName")
    private String orderType;//抽查处方类型
    private Integer checkCount;//抽查处方数量
    private String qualified;//合格率
    private Integer qualifiedNo;//合格处方数
    private Integer unqualifiedNo;//不合格处方数
    private String userId;//审核人id
    private String userName;//审核人名称
    private String checkTime;//抽查时间
    
    private List<CmsJgOrdercheckDtlDTO>  list;//明细
    
    
    
	public String getOrderTypeName() {
		return orderTypeName;
	}
	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}
	public List<CmsJgOrdercheckDtlDTO> getList() {
		return list;
	}
	public void setList(List<CmsJgOrdercheckDtlDTO> list) {
		this.list = list;
	}
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckArea() {
		return checkArea;
	}
	public void setCheckArea(String checkArea) {
		this.checkArea = checkArea;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Integer getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
	}
	public String getQualified() {
		return qualified;
	}
	public void setQualified(String qualified) {
		this.qualified = qualified;
	}
	public Integer getQualifiedNo() {
		return qualifiedNo;
	}
	public void setQualifiedNo(Integer qualifiedNo) {
		this.qualifiedNo = qualifiedNo;
	}
	public Integer getUnqualifiedNo() {
		return unqualifiedNo;
	}
	public void setUnqualifiedNo(Integer unqualifiedNo) {
		this.unqualifiedNo = unqualifiedNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

}