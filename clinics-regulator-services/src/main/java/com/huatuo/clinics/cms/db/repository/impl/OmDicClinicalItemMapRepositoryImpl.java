package com.huatuo.clinics.cms.db.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.mapper.OmDicClinicalItemMapMapper;
import com.huatuo.clinics.cms.db.repository.OmDicClinicalItemMapRepository;

@Repository
public class OmDicClinicalItemMapRepositoryImpl implements
		OmDicClinicalItemMapRepository {
	@Autowired
	private OmDicClinicalItemMapMapper omDicClinicalItemMapMapper;


	@Override
	public String getChargeIdByClinicalId(String clinicalItemId) {
		return omDicClinicalItemMapMapper.getChargeIdByClinicalId(clinicalItemId);
	}

}
