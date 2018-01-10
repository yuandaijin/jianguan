package com.huatuo.clinics.cms.bean;

public class CmsJgAddressAndOrgDTO {
//地址dto
    private String addressId;
    private Double provinceCode;
    private String province;
    private Double cityCode;
    private String city;
    private Double countyCode;
    private String county;
    private Double roadCode;
    private String road;
    private String address;
    private String positionCode;
    
    
    
//机构dto
    private String id;//机构id
    private String org;//暂时不用
    private String orgName;//机构名称
    private String orgAddress;//详细地址
    private String userName;//机构用户名称
    private String mobile;//联系电话
    private String antibioticLevelPolityId;//抗生素规则id
    
    
    
	
	public String getAntibioticLevelPolityId() {
		return antibioticLevelPolityId;
	}
	public void setAntibioticLevelPolityId(String antibioticLevelPolityId) {
		this.antibioticLevelPolityId = antibioticLevelPolityId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public Double getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(Double provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Double getCityCode() {
		return cityCode;
	}
	public void setCityCode(Double cityCode) {
		this.cityCode = cityCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(Double countyCode) {
		this.countyCode = countyCode;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public Double getRoadCode() {
		return roadCode;
	}
	public void setRoadCode(Double roadCode) {
		this.roadCode = roadCode;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

    
    
    
}