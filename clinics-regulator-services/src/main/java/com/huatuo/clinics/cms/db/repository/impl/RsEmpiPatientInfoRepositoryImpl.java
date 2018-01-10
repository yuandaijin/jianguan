package com.huatuo.clinics.cms.db.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.RsEmpiPatientInfo;
import com.huatuo.clinics.cms.db.mapper.RsEmpiPatientInfoMapper;
import com.huatuo.clinics.cms.db.repository.RsEmpiPatientInfoRepository;

@Repository
public class RsEmpiPatientInfoRepositoryImpl implements
		RsEmpiPatientInfoRepository {

	@Autowired
	private RsEmpiPatientInfoMapper rsEmpiPatientInfoMapper;
	
	
	@Override
	public RsEmpiPatientInfo getById(String patientId) {
		return rsEmpiPatientInfoMapper.selectByPrimaryKey(patientId);
	}
}
