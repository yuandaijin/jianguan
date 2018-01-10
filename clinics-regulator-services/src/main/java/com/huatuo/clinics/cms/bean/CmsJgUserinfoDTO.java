package com.huatuo.clinics.cms.bean;

import java.util.Date;
import java.util.List;

public class CmsJgUserinfoDTO {

    private Integer id;//主键id

    private String userName;//用户名称

    private String pwd;//密码

    private String company;//管理公司id(暂不用)

    private Integer cid;//创建人id

    private String createTime;//创建时间

    private Integer isDel;//是否删除 0：未删除; 1：删除
    
    private Date endDate; //用户退出系统时间
    
    
    /**
     * 拦截列表
     */
    private List<String> authorityList;

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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public List<String> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<String> authorityList) {
		this.authorityList = authorityList;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}