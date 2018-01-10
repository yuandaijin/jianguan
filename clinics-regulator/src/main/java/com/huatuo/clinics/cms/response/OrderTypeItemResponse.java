package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.clinics.cms.bean.SsDicItemDTO;
import com.huatuo.common.BaseResponse;

public class OrderTypeItemResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	private String userName;
	private String userId;
	private List<SsDicItemDTO> list;
	private List<SsDicItemDTO> list2;
	
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

	public List<SsDicItemDTO> getList() {
		return list;
	}

	public void setList(List<SsDicItemDTO> list) {
		this.list = list;
	}

	public List<SsDicItemDTO> getList2() {
		return list2;
	}

	public void setList2(List<SsDicItemDTO> list2) {
		this.list2 = list2;
	}


		
}
