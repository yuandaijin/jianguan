package com.huatuo.clinics.cms.bean;

public class CmsJgMenuinfoDTO {

    private Long id;//id

    private String menuName;//菜单名称

    private String menuUrl;//菜单路径

    private Long parentId;//父级id
   
    private String authority;//拦截名称

    private Integer isShow;//菜单层级：0卫计委，1卫计委普通用户
    
    private Integer isFlag;//是否显示：0：隐藏；1：显示
    
    

	public Integer getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(Integer isFlag) {
		this.isFlag = isFlag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}


   
}