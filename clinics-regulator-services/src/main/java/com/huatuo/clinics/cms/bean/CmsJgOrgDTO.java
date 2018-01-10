package com.huatuo.clinics.cms.bean;

public class CmsJgOrgDTO {
    private String id;//主键

    private String org;//暂时不用

    private String orgName;//机构名称

    private String address;//详细地址

    private String userName;//机构用户名称

    private String mobile;//联系电话

    private String addressid;//机构地址id
    private String addressName;//机构管辖区域
    private String taName;//抗生素规则名称
    private String antibioticLevelPolityId;//抗生素规则id

    private Integer provinceCode;
    private String province;
    private Integer cityCode;
    private String city;
    private Integer countyCode;
    private String county;
    
    
   	public String getTaName() {
		return taName;
	}

	public void setTaName(String taName) {
		this.taName = taName;
	}

	public String getAntibioticLevelPolityId() {
		return antibioticLevelPolityId;
	}

	public void setAntibioticLevelPolityId(String antibioticLevelPolityId) {
		this.antibioticLevelPolityId = antibioticLevelPolityId;
	}

	public Integer getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(Integer countyCode) {
		this.countyCode = countyCode;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
    
	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}


    
}