package com.huatuo.clinics.cms.services.regulator;

import com.huatuo.clinics.cms.bean.CmsJgAddressDTO;

/**
 * 监管机构
 * @author Administrator
 *
 */
public interface CmsJgAddressService {

	/**
	 * 保存
	 * @param cmsJgAddress
	 * @return
	 */
	Integer save(CmsJgAddressDTO cmsJgAddress);
	
	/**
	 * 根据Id删除地址信息
	 * @param addressid
	 * @return
	 */
	int deleteAddress(String addressid);
	
	/**
	 * 根据id修改机构地址
	 * @param cmsJgAddress
	 * @return
	 */
	int updateAddress(CmsJgAddressDTO cmsJgAddress);

}
