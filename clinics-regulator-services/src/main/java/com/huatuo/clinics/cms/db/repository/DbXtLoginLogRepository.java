package com.huatuo.clinics.cms.db.repository;

import java.util.Date;
import java.util.List;

import com.huatuo.clinics.cms.db.bean.XtLoginLog;

public interface DbXtLoginLogRepository {

	/**
	 * 根据机构id获得该机构登录日志列表
	 * @param orgId
	 * @return
	 */
	List<XtLoginLog> getListByOrgId(String orgId,Date beginDate,Date endDate,Integer currentPage,Integer pageSize);

	/**
	 * 根据机构id获得该机构本段时间的登录总次数
	 * @param orgId
	 * @param parse
	 * @param parse2
	 * @return
	 */
	Integer getTotalByOrgId(String orgId, Date beginDate, Date endDate);

}
