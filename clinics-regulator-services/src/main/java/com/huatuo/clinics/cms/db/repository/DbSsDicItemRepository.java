package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.SsDicItem;



public interface DbSsDicItemRepository {

	/**
	 * 根据code查询字典
	 * @param code
	 * @return
	 */
	public List<SsDicItem> queryBillingCodes(String code);
	
	/**
	 * 查询字典数据
	 * @param orgId
	 * @param temParent
	 * @param itemValue
	 * @return
	 * @throws Exception
	 */
	public List<SsDicItem> getDicByCode(String orgId,String itemParent, String itemValue);
}
