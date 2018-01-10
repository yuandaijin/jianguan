package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.RsEmpiPatientAllergicDrug;
import com.huatuo.clinics.cms.db.bean.RsEmpiPatientAllergicDrugExample;

public interface RsEmpiPatientAllergicDrugRepository {


	/**
	 * 通过患者id获得对应的过敏列表
	 * @param example
	 * @return
	 */
	List<RsEmpiPatientAllergicDrug> listByPatientId(
			RsEmpiPatientAllergicDrugExample example);

	
	
	
}
