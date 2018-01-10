package com.huatuo.clinics.cms.services.regulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.db.repository.OmDicClinicalItemMapRepository;

@Service
public class OmDicClinicalItemMapKeyServiceImpl implements
		OmDicClinicalItemMapKeyService {
	
	@Autowired
	private OmDicClinicalItemMapRepository omDicClinicalItemMapRepository;




	@Override
	public String getChargeIdByClinicalId(String clinicalItemId) {
		return omDicClinicalItemMapRepository.getChargeIdByClinicalId(clinicalItemId);
	}

}
