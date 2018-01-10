package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsOrgInfoDTO;
import com.huatuo.common.BaseResponse;
import com.huatuo.pms.bo.BOZdOrg;

/**
 * 机构信息
 * 
 * @author dengyajie
 * 
 */
public class CmsOrgResponse extends BaseResponse {
	private static final long serialVersionUID = 1L;
	
	private List<BOZdOrg> orgList;// 机构信息
	
	List<CmsOrgInfoDTO> cmsOrgInfoDTOs; // 地图返回信息
	
	private String currYearFirst; // 获取当年的第一天
	
	private String nowDate; // 获取当天日期

	public List<CmsOrgInfoDTO> getCmsOrgInfoDTOs() {
		return cmsOrgInfoDTOs;
	}

	public void setCmsOrgInfoDTOs(List<CmsOrgInfoDTO> cmsOrgInfoDTOs) {
		this.cmsOrgInfoDTOs = cmsOrgInfoDTOs;
	}

	public List<BOZdOrg> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<BOZdOrg> orgList) {
		this.orgList = orgList;
	}

	public String getCurrYearFirst() {
		return currYearFirst;
	}

	public void setCurrYearFirst(String currYearFirst) {
		this.currYearFirst = currYearFirst;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

}
