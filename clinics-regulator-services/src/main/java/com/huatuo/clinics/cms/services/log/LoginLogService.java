package com.huatuo.clinics.cms.services.log;


import com.huatuo.clinics.cms.bean.LoginLogPage;


/**
 * 登录日志
 * @author Administrator
 *
 */
public interface LoginLogService {

	/**
	 * 根据登录机构id获得该机构的登录日志列表
	 * @param orgId
	 * @return
	 */
	LoginLogPage getListByOrgId(LoginLogPage page) throws Exception;


}
