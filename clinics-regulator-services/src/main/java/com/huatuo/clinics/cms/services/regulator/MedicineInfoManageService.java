package com.huatuo.clinics.cms.services.regulator;

import com.huatuo.clinics.cms.bean.TaPhaDrugInfoDTO;


/**
 * 
 * 药房药品相关服务ta_pha_drug_info
 *
 */
public interface MedicineInfoManageService {
	/**
	 * 通过id查询单个对象
	 * @param phaDrugId
	 */
	public TaPhaDrugInfoDTO getOneDTO(String phaDrugId);
	
	
}
