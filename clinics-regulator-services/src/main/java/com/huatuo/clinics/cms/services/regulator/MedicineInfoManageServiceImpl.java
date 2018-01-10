package com.huatuo.clinics.cms.services.regulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.TaPhaDrugInfoDTO;
import com.huatuo.clinics.cms.db.bean.TaPhaDrugInfo;
import com.huatuo.clinics.cms.db.repository.DbMedicineInfoManageRepository;
import com.huatuo.common.Utils;

@Service
public class MedicineInfoManageServiceImpl implements MedicineInfoManageService {
	
	@Autowired
	private DbMedicineInfoManageRepository repository;
	
	

	@Override
	public TaPhaDrugInfoDTO getOneDTO(String phaDrugId) {
//		TaPhaDrugInfoDTO dto = (TaPhaDrugInfoDTO) redisSupport.hGet(REDIS_TA_PHA_DRUGINFOMAP, phaDrugId);
//		if(dto==null){
		  TaPhaDrugInfo taPhaDrugInfo = repository.getOneDTO(phaDrugId);
		  TaPhaDrugInfoDTO 	dto = Utils.exchangeObject(taPhaDrugInfo, TaPhaDrugInfoDTO.class);
//			redisSupport.hSet(REDIS_TA_PHA_DRUGINFOMAP, phaDrugId, dto);
//		}
//		dto = dtm.translate(dto, "");
		return dto;
	}

	
}
