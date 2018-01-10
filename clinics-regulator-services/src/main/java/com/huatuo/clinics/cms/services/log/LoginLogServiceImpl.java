package com.huatuo.clinics.cms.services.log;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.huatuo.clinics.cms.bean.LoginLogPage;
import com.huatuo.clinics.cms.db.bean.XtLoginLog;
import com.huatuo.clinics.cms.db.repository.DbXtLoginLogRepository;
import com.huatuo.common.ConfigProperites;

@Service
public class LoginLogServiceImpl implements LoginLogService {
	@Autowired
	private DbXtLoginLogRepository loginRepository;

	@Override
	public LoginLogPage getListByOrgId(LoginLogPage page) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<XtLoginLog> logs = loginRepository.getListByOrgId(page.getOrgId(),
				dateFormat.parse(page.getBeginDate()),dateFormat.parse(page.getEndDate()),
				page.getCurrentPage(),page.getPageSize());
		Integer total = loginRepository.getTotalByOrgId(page.getOrgId(),dateFormat.parse(page.getBeginDate()),dateFormat.parse(page.getEndDate()));
		for (int i = 0; i < logs.size(); i++) {
			if(!StringUtils.isBlank(logs.get(i).getLoginPhotoUrl())){
				logs.get(i).setLoginPhotoUrl(ConfigProperites.getImageUrl()+logs.get(i).getLoginPhotoUrl());
			}
		}
		page.setLogs(logs);
		page.setTotal(total);
		return page;
	}


}
