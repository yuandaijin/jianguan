package com.huatuo.clinics.cms.services.regulator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.CsOutpVisitDTO;
import com.huatuo.clinics.cms.bean.CsOutpVisitDxDTO;
import com.huatuo.clinics.cms.db.bean.CsOutpVisit;
import com.huatuo.clinics.cms.db.bean.CsOutpVisitDx;
import com.huatuo.clinics.cms.db.repository.CsOutpVisitRepository;
import com.huatuo.clinics.cms.db.repository.DbCsOutpVisitDxRepository;
import com.huatuo.common.Utils;

@Service
public class CsOutpVisitServiceImpl implements CsOutpVisitService {
	@Autowired
	private CsOutpVisitRepository csOutpVisitRepository;
	
	@Autowired
	private RsEmpiPatientAllergicDrugService rsEmpiPatientAllergicDrugService;
	
	@Autowired
	private DbCsOutpVisitDxRepository dbCsOutpVisitDxRepository;
	
	
	@Override
	public CsOutpVisitDTO queryPatientId(String visitId) {
		CsOutpVisit dto=csOutpVisitRepository.queryPatientId(visitId);
		return Utils.exchangeObject(dto, CsOutpVisitDTO.class);
	}
	

	@Override
	public CsOutpVisitDTO selectAllergicAndDxType(CsOutpVisitDTO reDto) {
		//过敏史
		reDto.setAllergicDrugString(rsEmpiPatientAllergicDrugService.queryStringByPatientId(reDto.getPatientId()));
		//诊断对象列表
		List<CsOutpVisitDx> dxList = dbCsOutpVisitDxRepository.getDxListByVisitId(reDto.getVistiId());
		ArrayList<CsOutpVisitDxDTO> dxDTOs = new ArrayList<>();
		for (CsOutpVisitDx csOutpVisitDx : dxList) {
			dxDTOs.add(Utils.exchangeObject(csOutpVisitDx, CsOutpVisitDxDTO.class));
		}
		reDto.setDxDTOs(dxDTOs);
		return reDto;
	}
	
	@Override
	public CsOutpVisit getById(String vistiId){
		return csOutpVisitRepository.getById(vistiId);
	}


	@Override
	public CsOutpVisitDx queryCsVisitDx(String dxId) {
		return dbCsOutpVisitDxRepository.queryCsVisitDx(dxId);
	}
	
}
