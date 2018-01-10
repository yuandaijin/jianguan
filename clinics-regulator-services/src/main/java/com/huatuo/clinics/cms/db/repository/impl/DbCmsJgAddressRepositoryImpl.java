package com.huatuo.clinics.cms.db.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.CmsJgAddress;
import com.huatuo.clinics.cms.db.bean.CmsJgAddressExample;
import com.huatuo.clinics.cms.db.mapper.CmsJgAddressMapper;
import com.huatuo.clinics.cms.db.repository.DbCmsJgAddressRepository;
@Repository
public class DbCmsJgAddressRepositoryImpl implements DbCmsJgAddressRepository {
	
	@Autowired
	private CmsJgAddressMapper cmsJgAddressMapper;

	@Override
	public Integer save(CmsJgAddress csmAddress) {
		Integer i=cmsJgAddressMapper.insert(csmAddress);
		return i;
	}

	@Override
	public int deleteAddress(String addressid) {
		CmsJgAddressExample example=new CmsJgAddressExample();
		example.createCriteria().andAddressIdEqualTo(addressid);
		return cmsJgAddressMapper.deleteByExample( example);
	}

	@Override
	public int updateAddress(CmsJgAddress dto) {
		CmsJgAddressExample example=new CmsJgAddressExample();
		example.createCriteria().andAddressIdEqualTo(dto.getAddressId());
		return cmsJgAddressMapper.updateByExampleSelective(dto,example);
	}

}
