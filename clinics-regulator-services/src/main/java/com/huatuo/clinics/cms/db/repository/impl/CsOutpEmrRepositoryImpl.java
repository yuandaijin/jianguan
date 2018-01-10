package com.huatuo.clinics.cms.db.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.CsOutpEmr;
import com.huatuo.clinics.cms.db.bean.CsOutpEmrExample;
import com.huatuo.clinics.cms.db.mapper.CsOutpEmrMapper;
import com.huatuo.clinics.cms.db.repository.CsOutpEmrRepository;

@Repository
public class CsOutpEmrRepositoryImpl implements CsOutpEmrRepository {
	
	@Autowired
	private CsOutpEmrMapper csOutpEmrMapper;

	

	@Override
	public CsOutpEmr getEmr(String visitId) {
		CsOutpEmr csOutpEmr = null;
		
		CsOutpEmrExample example = new CsOutpEmrExample();
		example.createCriteria().andVisitIdEqualTo(visitId);
		List<CsOutpEmr> list = csOutpEmrMapper.selectByExample(example );
		if(list.size()>=1){
			csOutpEmr = list.get(0);
		}
		
		return csOutpEmr;
	}

	

}
