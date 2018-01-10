package com.huatuo.clinics.cms.services.regulator;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.RsEmpiPatientAllergicDrug;

/**
 * 
 * 过敏接口服务
 *
 */
public interface RsEmpiPatientAllergicDrugService {


	/**
	 * 通过患者id拿到该患者的过敏史 --返回拼装字符串
	 * @param patientId
	 * @return String
	 */
	String queryStringByPatientId(String patientId);
	
	/**
	 * 通过患者id拿到该患者的过敏史
	 * @param patientId
	 * @return
	 */
	List<RsEmpiPatientAllergicDrug> listByPatientId(String patientId);
	
}
