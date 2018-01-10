package com.huatuo.clinics.cms.bean;

import java.util.Date;
//用户组合dto
public class CmsJgUserPartsDTO {
	private Integer id; //用户ID
    private String userName;//账号名称
    private String userNames;//用户名称
    private String pwd;//密码
    private String mobile;//电话
    private String userType;//职位
    private String orgName;//所属机构
    private Date data;//创建时间
    private String orgId;//机构id
    private Integer roleId;//角色id
    private String rolName;//角色名称
    
    
    
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRolName() {
		return rolName;
	}
	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNames() {
		return userNames;
	}
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
    
    
}