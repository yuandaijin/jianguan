package com.huatuo.clinics.cms.services.regulator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.RsEmpiPatientAllergicDrug;
import com.huatuo.clinics.cms.db.bean.RsEmpiPatientAllergicDrugExample;
import com.huatuo.clinics.cms.db.mapper.RsEmpiPatientAllergicDrugMapper;
import com.huatuo.clinics.cms.db.repository.RsEmpiPatientAllergicDrugRepository;

@Repository
public class RsEmpiPatientAllergicDrugRepositoryImpl implements
		RsEmpiPatientAllergicDrugRepository {

	@Autowired
	private RsEmpiPatientAllergicDrugMapper rsEmpiPatientAllergicDrugMapper;
	

	


	@Override
	public List<RsEmpiPatientAllergicDrug> listByPatientId(
			RsEmpiPatientAllergicDrugExample example) {
		return rsEmpiPatientAllergicDrugMapper.selectByExample(example);
	}

}
