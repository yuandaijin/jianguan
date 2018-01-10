package com.huatuo.clinics.cms.db.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.TaPhaDrugInfo;
import com.huatuo.clinics.cms.db.mapper.TaPhaDrugInfoMapper;
import com.huatuo.clinics.cms.db.repository.DbMedicineInfoManageRepository;
@Repository
public class DbMedicineInfoManageRepositoryImpl implements DbMedicineInfoManageRepository {
	@Autowired
	private TaPhaDrugInfoMapper taPhaDrugInfoMapper;
	



	@Override
	public TaPhaDrugInfo getOneDTO(String phaDrugId) {
		return taPhaDrugInfoMapper.selectByPrimaryKey(phaDrugId);
	}

	
}
