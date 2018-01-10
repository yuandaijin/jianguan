package com.huatuo.clinics.cms.services.regulator;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;

/**
 * 监管地址服务
 * @author Administrator
 *
 */
public interface CmsJgDistrictService {

	/**
	 * 根据父级id取得list
	 * @param parentId
	 * @return
	 */
	List<CmsJgDistrictDTO> getDistrictByParent(Integer parentId);

}
