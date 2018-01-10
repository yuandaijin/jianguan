package com.huatuo.clinics.cms.services.regulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.CmsJgAddressDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgAddress;
import com.huatuo.clinics.cms.db.repository.DbCmsJgAddressRepository;
import com.huatuo.clinics.cms.util.Utils;

@Service
public class CmsJgAddressServiceImpl implements CmsJgAddressService {
	@Autowired
	private DbCmsJgAddressRepository dbCmsJgAddressRepository;

	@Override
	public Integer save(CmsJgAddressDTO csmAddress) {
		CmsJgAddress dto = Utils.exchangeObject(csmAddress, CmsJgAddress.class);
		return dbCmsJgAddressRepository.save(dto);
	}

	@Override
	public int deleteAddress(String addressid) {
		return dbCmsJgAddressRepository.deleteAddress(addressid);
	}

	@Override
	public int updateAddress(CmsJgAddressDTO cmsJgAddress) {
		CmsJgAddress dto = Utils.exchangeObject(cmsJgAddress, CmsJgAddress.class);
		return dbCmsJgAddressRepository.updateAddress(dto);
	}

}
