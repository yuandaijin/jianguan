package com.huatuo.clinics.cms.services.regulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.OmDicCliChargeItemDTO;
import com.huatuo.clinics.cms.db.repository.OmDicCliChargeItemRepository;
import com.huatuo.common.Utils;

@Service
public class OmDicCliChargeItemServiceImpl implements OmDicCliChargeItemService {
	
	@Autowired
	private OmDicCliChargeItemRepository OmDicCliChargeItemRepository;


	@Override
	public OmDicCliChargeItemDTO getOneBeanById(String cliChargeItemId) {
		return Utils.exchangeObject(OmDicCliChargeItemRepository.getOneBeanById(cliChargeItemId), OmDicCliChargeItemDTO.class);
	}
}
