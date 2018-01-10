package com.huatuo.clinics.cms.services.report;

import com.huatuo.clinics.cms.bean.OpmOrgExtendInfoDTO;


/**
 * 诊所扩展信息服务接口
 * @author dengyajie
 *
 */
public interface OpmOrgExtendInfoService {
	/**
	 * 查询诊所扩展信息
	 * @param orgId
	 * @return
	 */
	public OpmOrgExtendInfoDTO getDtoByOrgId(String orgId);

}
