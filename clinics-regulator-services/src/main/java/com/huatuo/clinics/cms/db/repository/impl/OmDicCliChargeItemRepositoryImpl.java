package com.huatuo.clinics.cms.db.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.OmDicCliChargeItem;
import com.huatuo.clinics.cms.db.mapper.OmDicCliChargeItemMapper;
import com.huatuo.clinics.cms.db.repository.OmDicCliChargeItemRepository;

@Repository
public class OmDicCliChargeItemRepositoryImpl implements OmDicCliChargeItemRepository{
	
	@Autowired
	private OmDicCliChargeItemMapper OmDicCliChargeItemMapper;
	

	@Override
	public OmDicCliChargeItem getOneBeanById(String cliChargeItemId) {
		return OmDicCliChargeItemMapper.selectByPrimaryKey(cliChargeItemId);
	}


}
