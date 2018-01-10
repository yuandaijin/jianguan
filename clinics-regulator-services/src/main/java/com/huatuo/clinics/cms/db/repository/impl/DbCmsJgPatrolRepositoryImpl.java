package com.huatuo.clinics.cms.db.repository.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.bean.CmsJgPatrolDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgPatrol;
import com.huatuo.clinics.cms.db.mapper.CmsJgPatrolMapper;
import com.huatuo.clinics.cms.db.repository.DbCmsJgPatrolRepository;
import com.huatuo.clinics.cms.util.Utils;


@Repository
public class DbCmsJgPatrolRepositoryImpl implements DbCmsJgPatrolRepository{
	@Autowired
	private CmsJgPatrolMapper patrolMapper;

	@Override
	public int isPatrol(RuquestBeanDTO beanDTO) {
		return patrolMapper.isPatrol(beanDTO);
	}

	@Override
	public void addPatrolInfo(CmsJgPatrolDTO patrolDTO) {
		CmsJgPatrol jgPatrol = Utils.exchangeObject(patrolDTO, CmsJgPatrol.class);
		patrolMapper.insert(jgPatrol);
	}
	
	@Override
	public String getUserIdByOrgId(String orgId, String status) {
		return patrolMapper.getUserIdByOrgId(orgId, status);
	}

	@Override
	public List<Map<String, Object>> getPatrolTime(RuquestBeanDTO beanDTO) {
		return patrolMapper.getPatrolTime(beanDTO);
	}
	

}
