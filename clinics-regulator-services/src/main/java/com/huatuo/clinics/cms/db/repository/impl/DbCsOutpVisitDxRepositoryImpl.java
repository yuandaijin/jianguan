package com.huatuo.clinics.cms.db.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.huatuo.clinics.cms.db.bean.CsOutpVisitDx;
import com.huatuo.clinics.cms.db.bean.CsOutpVisitDxExample;
import com.huatuo.clinics.cms.db.bean.CsOutpVisitDxExample.Criteria;
import com.huatuo.clinics.cms.db.mapper.CsOutpVisitDxMapper;
import com.huatuo.clinics.cms.db.repository.DbCsOutpVisitDxRepository;

@Repository
public class DbCsOutpVisitDxRepositoryImpl implements DbCsOutpVisitDxRepository {

	@Autowired
	private CsOutpVisitDxMapper csOutpVisitDxMapper;
	

	
	@Override
	public List<CsOutpVisitDx> getDxListByVisitId(String visitId) {
		CsOutpVisitDxExample example = new CsOutpVisitDxExample();
		example.setOrderByClause("dx_type");
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isBlank(visitId)){
			criteria.andVistiIdEqualTo(visitId);
		}else{
			criteria.andVistiIdEqualTo("没有");
		}
		return csOutpVisitDxMapper.selectByExample(example);
	}



	@Override
	public CsOutpVisitDx queryCsVisitDx(String dxId) {
		 CsOutpVisitDx dto=csOutpVisitDxMapper.selectByPrimaryKey(dxId);
		 if(null != dto){
			 return dto;
		 }
		 return null;
	}
	
	
}
