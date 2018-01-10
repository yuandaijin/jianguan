package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.CmsJgDistrict;

/**
 * 监管地址
 * @author Administrator
 *
 */
public interface DbCmsJgDistrictRepository {

	List<CmsJgDistrict> getDistrictByParent(Integer parentId);

}
