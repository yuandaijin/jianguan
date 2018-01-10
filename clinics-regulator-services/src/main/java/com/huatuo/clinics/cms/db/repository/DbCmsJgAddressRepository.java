package com.huatuo.clinics.cms.db.repository;

import com.huatuo.clinics.cms.db.bean.CmsJgAddress;

public interface DbCmsJgAddressRepository {

	Integer save(CmsJgAddress csmAddress);
	
	/**
	 * 根据id删除地址信息
	 * @param addressid
	 * @return
	 */
	int deleteAddress(String addressid);
	
	/**
	 * 根据id修改地址信息
	 * @param dto
	 * @return
	 */
	int updateAddress(CmsJgAddress dto);

}
