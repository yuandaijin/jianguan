package com.huatuo.clinics.cms.db.bean;

import java.util.ArrayList;
import java.util.List;

public class CmsJgMenuInfoDTO {
    private Long mId;
    
    private String menuName;
    
    private String menuUrl;
    
    private Long parentId;
    
    private String authority;
    
    private List<CmsJgMenuInfoDTO> children =new ArrayList<CmsJgMenuInfoDTO>();//一级菜单下面的二级菜单
    

	public List<CmsJgMenuInfoDTO> getChildren() {
		return children;
	}

	public void setChildren(List<CmsJgMenuInfoDTO> children) {
		this.children = children;
	}

	public Long getmId() {
		return mId;
	}

	public void setmId(Long mId) {
		this.mId = mId;
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
    
}