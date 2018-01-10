package com.huatuo.clinics.cms.services.regulator;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huatuo.clinics.cms.db.bean.RsEmpiPatientAllergicDrug;
import com.huatuo.clinics.cms.db.bean.RsEmpiPatientAllergicDrugExample;
import com.huatuo.clinics.cms.db.bean.RsEmpiPatientAllergicDrugExample.Criteria;
import com.huatuo.clinics.cms.db.repository.RsEmpiPatientAllergicDrugRepository;

/**
 * 过敏史
 *
 */
@Service
public class RsEmpiPatientAllergicDrugServiceImpl implements
		RsEmpiPatientAllergicDrugService {
	
	@Autowired
	private RsEmpiPatientAllergicDrugRepository rsEmpiPatientAllergicDrugRepository;


	@Override
	public String queryStringByPatientId(String patientId) {
		if(patientId==null){
			return null;
		}
		List<RsEmpiPatientAllergicDrug> list = this.listByPatientId(patientId);
		if(list==null || list.size()==0){
			return null;
		}
		String allergics = "";
		for (RsEmpiPatientAllergicDrug rsEmpiPatientAllergicDrug : list) {
			allergics += rsEmpiPatientAllergicDrug.getAllergicDrug();
		}
		return allergics;
	}
	
	@Override
	public List<RsEmpiPatientAllergicDrug> listByPatientId(String patientId) {
		//封装条件，查询总数
		RsEmpiPatientAllergicDrugExample example = new RsEmpiPatientAllergicDrugExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(patientId)){
			criteria.andPatientIdEqualTo(patientId);
		}
		return rsEmpiPatientAllergicDrugRepository.listByPatientId(example);
	}
}
