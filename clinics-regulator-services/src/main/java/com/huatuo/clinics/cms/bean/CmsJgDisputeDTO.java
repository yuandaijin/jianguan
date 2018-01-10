package com.huatuo.clinics.cms.bean;

import java.util.Date;

import com.huatuo.clinics.cms.services.translate.Translatable;
import com.huatuo.clinics.cms.services.translate.TranslateField;

/**
 * 医疗争议记录主表
 * @author 河南华佗在线
 *
 */
public class CmsJgDisputeDTO implements Translatable{
    private String id;//主键id
    private String disId;//外键（暂留）
    private String orgId;//诊所id
    private String orgName;//诊所名称
    private String cityCode;//父级id
    private String city;//父级名称
    private String countyCode;//区/县id
    private String county;//区/县名称
    private Integer identifyStatus;//鉴定状态
    @TranslateField(fullQualifiedName="00090",targetFieldName="identifyTypeName")
    private String identifyType;//鉴定类型
    private String identifyTypeName;//鉴定类型名称
    @TranslateField(fullQualifiedName="00091",targetFieldName="identifyGradeName")
    private String identifyGrade;//鉴定等级
    private String identifyGradeName;//鉴定等级名称
    private Date aroseDate;//发生日期
    private Date bookedDate;//登记日期
    private String dispute;//争议详情
    private String chargeName;//责任人姓名
    private Integer chargeTitle;//责任人职称
    private String chargeMobile;//责任人联系方式
    private String visitName;//患者名称
    private String visitStatus;//患者状态
    private String visitMobile;//患者联系方式
    private String kindredMobile;//亲属联系方式
    private String userId;//上报人id
    private String userName;//上报人名称
    private String userMobile;//上报人电话
    private Integer mercyStatus;//处置状态
    private Integer justiceStatus;//司法机构是否介入
    private String mercyData;//处置时间
    private String mercyUser;//处置负责人
    private String mercyMethod;//处置办法
    private Date CreateDate;//最新修改时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisId() {
		return disId;
	}
	public void setDisId(String disId) {
		this.disId = disId;
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
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public Integer getIdentifyStatus() {
		return identifyStatus;
	}
	public void setIdentifyStatus(Integer identifyStatus) {
		this.identifyStatus = identifyStatus;
	}

	public String getIdentifyType() {
		return identifyType;
	}
	public void setIdentifyType(String identifyType) {
		this.identifyType = identifyType;
	}
	public String getIdentifyGrade() {
		return identifyGrade;
	}
	public void setIdentifyGrade(String identifyGrade) {
		this.identifyGrade = identifyGrade;
	}
	public Date getAroseDate() {
		return aroseDate;
	}
	public void setAroseDate(Date aroseDate) {
		this.aroseDate = aroseDate;
	}
	
	public Date getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}
	public String getDispute() {
		return dispute;
	}
	public void setDispute(String dispute) {
		this.dispute = dispute;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public Integer getChargeTitle() {
		return chargeTitle;
	}
	public void setChargeTitle(Integer chargeTitle) {
		this.chargeTitle = chargeTitle;
	}
	public String getChargeMobile() {
		return chargeMobile;
	}
	public void setChargeMobile(String chargeMobile) {
		this.chargeMobile = chargeMobile;
	}
	public String getVisitName() {
		return visitName;
	}
	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}
	public String getVisitStatus() {
		return visitStatus;
	}
	public void setVisitStatus(String visitStatus) {
		this.visitStatus = visitStatus;
	}
	public String getVisitMobile() {
		return visitMobile;
	}
	public void setVisitMobile(String visitMobile) {
		this.visitMobile = visitMobile;
	}
	public String getKindredMobile() {
		return kindredMobile;
	}
	public void setKindredMobile(String kindredMobile) {
		this.kindredMobile = kindredMobile;
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
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public Integer getMercyStatus() {
		return mercyStatus;
	}
	public void setMercyStatus(Integer mercyStatus) {
		this.mercyStatus = mercyStatus;
	}
	public Integer getJusticeStatus() {
		return justiceStatus;
	}
	public void setJusticeStatus(Integer justiceStatus) {
		this.justiceStatus = justiceStatus;
	}
	public String getMercyData() {
		return mercyData;
	}
	public void setMercyData(String mercyData) {
		this.mercyData = mercyData;
	}
	public String getMercyUser() {
		return mercyUser;
	}
	public void setMercyUser(String mercyUser) {
		this.mercyUser = mercyUser;
	}
	public String getMercyMethod() {
		return mercyMethod;
	}
	public void setMercyMethod(String mercyMethod) {
		this.mercyMethod = mercyMethod;
	}
	public String getIdentifyGradeName() {
		return identifyGradeName;
	}
	public void setIdentifyGradeName(String identifyGradeName) {
		this.identifyGradeName = identifyGradeName;
	}
	public String getIdentifyTypeName() {
		return identifyTypeName;
	}
	public void setIdentifyTypeName(String identifyTypeName) {
		this.identifyTypeName = identifyTypeName;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	
	
}