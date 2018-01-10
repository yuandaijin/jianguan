package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.huatuo.clinics.cms.db.bean.SsDicItem;
import com.huatuo.clinics.cms.db.bean.SsDicItemExample;
import com.huatuo.clinics.cms.db.mapper.SsDicItemMapper;
import com.huatuo.clinics.cms.db.repository.DbSsDicItemRepository;

@Repository
public class DbSsDicItemRepositoryImpl implements DbSsDicItemRepository{

	@Autowired
	private SsDicItemMapper ssDicItemMapper;
	
	
	

	@Override
	public List<SsDicItem> queryBillingCodes(String code) {
		SsDicItemExample example=new SsDicItemExample();
		example.createCriteria().andDictItemParentEqualTo(code);
		return  ssDicItemMapper.selectByExample(example);
	}
	
	@Override
	public List<SsDicItem> getDicByCode(String orgId, String itemParent,
			String itemValue) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(!StringUtils.isEmpty(orgId) && itemParent.startsWith("9")){
			map.put("orgId", orgId);
		}
		map.put("itemParent", itemParent);
		map.put("itemValue", itemValue);
		return  ssDicItemMapper.getDicByCode(map);
	}

}
