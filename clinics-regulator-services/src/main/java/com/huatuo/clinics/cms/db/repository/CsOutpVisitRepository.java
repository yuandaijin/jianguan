package com.huatuo.clinics.cms.db.repository;

import com.huatuo.clinics.cms.db.bean.CsOutpVisit;


public interface CsOutpVisitRepository {
	
	/**
	 *  通过就诊id拿到患者id
	 * @param visitId
	 * @return
	 */
	CsOutpVisit queryPatientId(String visitId);
	
	
	public CsOutpVisit getById(String vistiId);

}
