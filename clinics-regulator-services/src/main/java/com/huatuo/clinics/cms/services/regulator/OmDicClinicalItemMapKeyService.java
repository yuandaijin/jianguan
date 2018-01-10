package com.huatuo.clinics.cms.services.regulator;

public interface OmDicClinicalItemMapKeyService {

	/**
	 * 通过疗诊项id获得收费项id
	 * @param clinicalItemId
	 * @return
	 */
	public String getChargeIdByClinicalId(String clinicalItemId);
}
