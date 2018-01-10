package com.huatuo.clinics.cms.bean;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.XtLoginLog;

/**
 * 登录分页查询对象
 * @author Administrator
 *
 */
public class LoginLogPage {
	private Integer pageSize;
	private Integer total;
	private Integer currentPage;
	private List<XtLoginLog> logs;
	private String orgId;
	private String beginDate;
	private String endDate;
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public List<XtLoginLog> getLogs() {
		return logs;
	}
	public void setLogs(List<XtLoginLog> logs) {
		this.logs = logs;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
