package com.huatuo.clinics.cms.bean;

public class CmsJgRoleinfoDTO {
 
    private Long id;//id

    private String rolName;//角色名称

    private String describe;//角色描述

    private Long isDel;//用户标识

    


	public Long getIsDel() {
		return isDel;
	}


	public void setIsDel(Long isDel) {
		this.isDel = isDel;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRolName() {
		return rolName;
	}


	public void setRolName(String rolName) {
		this.rolName = rolName;
	}


	public String getDescribe() {
		return describe;
	}


	public void setDescribe(String describe) {
		this.describe = describe;
	}

   
 
}