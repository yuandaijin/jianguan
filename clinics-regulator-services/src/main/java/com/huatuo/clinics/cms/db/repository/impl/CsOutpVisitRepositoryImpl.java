package com.huatuo.clinics.cms.db.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.CsOutpVisit;
import com.huatuo.clinics.cms.db.mapper.CsOutpVisitMapper;
import com.huatuo.clinics.cms.db.repository.CsOutpVisitRepository;

@Repository
public class CsOutpVisitRepositoryImpl implements CsOutpVisitRepository {
	
	@Autowired
	private CsOutpVisitMapper csOutpVisitMapper;

	@Override
	public CsOutpVisit queryPatientId(String visitId) {
		CsOutpVisit dto=csOutpVisitMapper.selectByPrimaryKey(visitId);
		if(null != dto){
			return dto;
		}
		return null;
	}

	@Override
	public CsOutpVisit getById(String vistiId) {
		CsOutpVisit dto= csOutpVisitMapper.selectByPrimaryKey(vistiId);
		if(null != dto){
			return dto;
		}
		return null;
	}
}
