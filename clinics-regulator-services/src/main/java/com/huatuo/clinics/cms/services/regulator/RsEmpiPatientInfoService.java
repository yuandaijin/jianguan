package com.huatuo.clinics.cms.services.regulator;

import com.huatuo.clinics.cms.bean.RsEmpiPatientInfoDTO;

/**
 * 
 *患者信息服务接口
 *
 */
public interface RsEmpiPatientInfoService {



	/**
	 * 通过患者id拿到患者信息
	 * @param patientId
	 * @return
	 */
	RsEmpiPatientInfoDTO getById(String patientId);

}
