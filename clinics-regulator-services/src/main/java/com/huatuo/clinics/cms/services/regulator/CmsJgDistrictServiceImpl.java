package com.huatuo.clinics.cms.services.regulator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgDistrict;
import com.huatuo.clinics.cms.db.repository.DbCmsJgDistrictRepository;
import com.huatuo.common.Utils;

@Service
public class CmsJgDistrictServiceImpl implements CmsJgDistrictService {
	
	@Autowired
	private DbCmsJgDistrictRepository cmsJgDistrictRepository;

	@Override
	public List<CmsJgDistrictDTO> getDistrictByParent(Integer parentId) {
		List<CmsJgDistrictDTO> list = new ArrayList<>();
		List<CmsJgDistrict> list2 = cmsJgDistrictRepository.getDistrictByParent(parentId);
		for (CmsJgDistrict cmsJgDistrict : list2) {
			list.add(Utils.exchangeObject(cmsJgDistrict, CmsJgDistrictDTO.class));
		}
		return list;
	}

}
