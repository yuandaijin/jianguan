package com.huatuo.clinics.cms.db.repository;


import com.huatuo.clinics.cms.db.bean.OpmOrgExtendInfo;

/**
 * 对诊所信息进行操作的Repository
 * @author hy
 *
 */
public interface DbOpmOrgExtendInfoRepository {
	/**
	 * 查询诊所扩展信息
	 * @param orgId
	 * @return
	 */
	public OpmOrgExtendInfo getDtoByOrgId(String orgId);
}
