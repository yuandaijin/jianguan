package com.huatuo.clinics.cms.services.regulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.RsEmpiPatientInfoDTO;
import com.huatuo.clinics.cms.db.repository.RsEmpiPatientInfoRepository;
import com.huatuo.common.Utils;

@Service
public class RsEmpiPatientInfoServiceImpl implements RsEmpiPatientInfoService {

	@Autowired
	private RsEmpiPatientInfoRepository rsEmpiPatientInfoRepository;
	
	
	

	@Override
	public RsEmpiPatientInfoDTO getById(String patientId) {
		return Utils.exchangeObject(rsEmpiPatientInfoRepository.getById(patientId), RsEmpiPatientInfoDTO.class);
	}
}
