package com.huatuo.clinics.cms.services.regulator;

import com.huatuo.clinics.cms.bean.CsOutpEmrDTO;

public interface CsOutpEmrService {

	/**
	 * 通过就诊id获得电子病历
	 * @param vistiId
	 */
	CsOutpEmrDTO getByVisitId(String visitId);

}
