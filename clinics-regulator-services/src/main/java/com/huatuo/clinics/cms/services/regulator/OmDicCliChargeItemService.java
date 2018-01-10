package com.huatuo.clinics.cms.services.regulator;

import com.huatuo.clinics.cms.bean.OmDicCliChargeItemDTO;



/**
 * 诊疗项设置服务接口
 * 
 * @author duanzongxiang
 * 
 */
public interface OmDicCliChargeItemService {

	
	/**
	 * 根据诊所收费项目ID查询诊所收费项目对象
	 * @param cliChargeItemId
	 * @return
	 */
	public OmDicCliChargeItemDTO getOneBeanById(String cliChargeItemId);
	
}
