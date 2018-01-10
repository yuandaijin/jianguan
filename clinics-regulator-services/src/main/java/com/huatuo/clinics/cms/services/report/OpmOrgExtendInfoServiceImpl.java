package com.huatuo.clinics.cms.services.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.OpmOrgExtendInfoDTO;
import com.huatuo.clinics.cms.db.bean.OpmOrgExtendInfo;
import com.huatuo.clinics.cms.db.repository.DbOpmOrgExtendInfoRepository;
import com.huatuo.common.Utils;

@Service
public class OpmOrgExtendInfoServiceImpl implements OpmOrgExtendInfoService {
	@Autowired
	private DbOpmOrgExtendInfoRepository extendInfoRepository;

	@Override
	public OpmOrgExtendInfoDTO getDtoByOrgId(String orgId) {
		OpmOrgExtendInfo opmOrgExtendInfo = extendInfoRepository.getDtoByOrgId(orgId);
		OpmOrgExtendInfoDTO dto = null;
		if (opmOrgExtendInfo!=null) {
			dto = Utils.exchangeObject(opmOrgExtendInfo, OpmOrgExtendInfoDTO.class);
		}
		return dto;
	}

}
