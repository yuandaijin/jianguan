package com.huatuo.clinics.cms.db.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.CmsJgDistrict;
import com.huatuo.clinics.cms.db.bean.CmsJgDistrictExample;
import com.huatuo.clinics.cms.db.mapper.CmsJgDistrictMapper;
import com.huatuo.clinics.cms.db.repository.DbCmsJgDistrictRepository;

@Repository
public class DbCmsJgDistrictRepositoryImpl implements DbCmsJgDistrictRepository {
	
	@Autowired
	private CmsJgDistrictMapper cmsJgDistrictMapper;

	@Override
	public List<CmsJgDistrict> getDistrictByParent(Integer parentId) {
		CmsJgDistrictExample example = new CmsJgDistrictExample();
		if(parentId!=null){
			Double id = Double.parseDouble(parentId+"");
			example.createCriteria().andParentIdEqualTo(id).andDelFlagEqualTo(0D);
		}else{
			example.createCriteria().andParentIdEqualTo(-1D).andDelFlagEqualTo(0D);
		}
		return cmsJgDistrictMapper.selectByExample(example );
	}

}
