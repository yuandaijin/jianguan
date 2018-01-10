package com.huatuo.clinics.cms.db.repository;

import com.huatuo.clinics.cms.db.bean.OmDicCliChargeItem;




public interface OmDicCliChargeItemRepository {
	
	/**
	 * 通过id查询诊疗项目收费对象
	 * @param cliChargeItemId
	 * @return
	 */
	public OmDicCliChargeItem getOneBeanById(String cliChargeItemId);
}
