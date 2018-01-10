package com.huatuo.clinics.cms.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.LoginLogPage;
import com.huatuo.clinics.cms.comm.MessageUtil;
import com.huatuo.clinics.cms.response.LoginLogResponse;
import com.huatuo.clinics.cms.services.log.LoginLogService;

/**
 * 登录日志
 * @author Administrator
 *
 */
@RequestMapping(value="/loginLog")
@Controller
public class LoginLogController {
	private static int pageSize = 10;
	@Autowired
	private LoginLogService logService;
	
	@RequestMapping(value="/loginLogList")
	public @ResponseBody LoginLogResponse loginLogList(HttpServletRequest request,String orgId,String beginDate,String endDate,Integer currentPage){
		LoginLogResponse resp = new LoginLogResponse();
		
		LoginLogPage page = new LoginLogPage();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setOrgId(orgId);
		page.setBeginDate(beginDate);
		page.setEndDate(endDate);
		try {
			page = logService.getListByOrgId(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setLogs(page.getLogs());
		resp.setResul(page.getTotal()+"");
		resp.setCode(MessageUtil.CODE_SUCCESS);
		resp.setMessage(MessageUtil.SUCCESS);
		
		return resp;
	}
}
