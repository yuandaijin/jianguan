package com.huatuo.clinics.cms.services.regulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.CsOutpEmrDTO;
import com.huatuo.clinics.cms.db.repository.CsOutpEmrRepository;
import com.huatuo.common.Utils;
@Service
public class CsOutpEmrServiceImpl implements CsOutpEmrService {

	@Autowired
	private CsOutpEmrRepository csOutpEmrRepository;
	
	@Override
	public CsOutpEmrDTO getByVisitId(String visitId) {
		return Utils.exchangeObject(csOutpEmrRepository.getEmr(visitId), CsOutpEmrDTO.class);
	}

}
