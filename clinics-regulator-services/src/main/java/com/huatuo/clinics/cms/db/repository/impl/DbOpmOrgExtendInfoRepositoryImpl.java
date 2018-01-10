package com.huatuo.clinics.cms.db.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.OpmOrgExtendInfo;
import com.huatuo.clinics.cms.db.mapper.OpmOrgExtendInfoMapper;
import com.huatuo.clinics.cms.db.repository.DbOpmOrgExtendInfoRepository;

@Repository
public class DbOpmOrgExtendInfoRepositoryImpl implements DbOpmOrgExtendInfoRepository{
	@Autowired
	private OpmOrgExtendInfoMapper extendInfoMapper;

	@Override
	public OpmOrgExtendInfo getDtoByOrgId(String orgId) {
		return extendInfoMapper.selectByPrimaryKey(orgId);
	}

}
