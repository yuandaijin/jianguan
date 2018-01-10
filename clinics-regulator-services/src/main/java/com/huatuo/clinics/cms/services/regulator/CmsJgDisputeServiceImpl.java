package com.huatuo.clinics.cms.services.regulator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.CmsJgDisputeDTO;
import com.huatuo.clinics.cms.bean.DisputeRepostDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgDispute;
import com.huatuo.clinics.cms.db.bean.DisputeRepost;
import com.huatuo.clinics.cms.db.repository.DbCmsJgDisputeRepository;
import com.huatuo.clinics.cms.services.translate.DefaultTranslatorManager;
import com.huatuo.common.Utils;

@Service
public class CmsJgDisputeServiceImpl implements CmsJgDisputeService{
	@Autowired
	private DbCmsJgDisputeRepository disputeRepository;
	
	@Autowired
	private DefaultTranslatorManager dtm;

	@Override
	public int saveDispute(CmsJgDisputeDTO disputeDTO) {
		CmsJgDispute dispute = Utils.exchangeObject(disputeDTO, CmsJgDispute.class);
		return disputeRepository.saveDispute(dispute);
	}

	@Override
	public int updateDispute(CmsJgDisputeDTO disputeDTO) {
		CmsJgDispute dispute = Utils.exchangeObject(disputeDTO, CmsJgDispute.class);
		return disputeRepository.updateDispute(dispute);
	}

	@Override
	public List<CmsJgDisputeDTO> queryDisputes(String key, String beginDate,
			String endDate, String countyCode) {
		List<CmsJgDisputeDTO> dtos = new ArrayList<>();
		List<CmsJgDispute> disputes = disputeRepository.queryDisputes(key, beginDate,endDate, countyCode);
		for (CmsJgDispute cmsJgDispute : disputes) {
			CmsJgDisputeDTO dto=Utils.exchangeObject(cmsJgDispute, CmsJgDisputeDTO.class);
			dto = dtm.translate(dto, "-1");
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<DisputeRepostDTO> queryMedicalDisputeNumber(String reportType,
			String year, String year2, String month, String month2, String day,
			String address, String status) {
		List<DisputeRepostDTO> reList=new ArrayList<DisputeRepostDTO>();
		List<DisputeRepost> list=disputeRepository.queryMedicalDisputeNumber(reportType, year, year2, month, month2, day, address,status);
		if(list.size() > 0){
			for (DisputeRepost disputeRepost : list) {
				reList.add(Utils.exchangeObject(disputeRepost, DisputeRepostDTO.class));
			}
			return reList;
		}
		return null;
	}

	@Override
	public List<CmsJgDisputeDTO> queryDisputesByReport(String reportType,
			String year, String year2, String month, String month2, String day,
			String address) {
		List<CmsJgDisputeDTO> dtos = new ArrayList<>();
		List<CmsJgDispute> disputes = disputeRepository.queryDisputesByReport(reportType, year, year2, month, month2, day, address);
		if(disputes.size() > 0){
			for (CmsJgDispute cmsJgDispute : disputes) {
				CmsJgDisputeDTO dto=Utils.exchangeObject(cmsJgDispute, CmsJgDisputeDTO.class);
				dto = dtm.translate(dto, "-1");
				dtos.add(dto);
			}
			return dtos;
		}
		return dtos;
	}

	@Override
	public CmsJgDisputeDTO queryDisputes(String id) {
		CmsJgDispute dto=disputeRepository.queryDisputes(id);
		if(null != dto){
				CmsJgDisputeDTO dto1=Utils.exchangeObject(dto, CmsJgDisputeDTO.class);
				dto1 = dtm.translate(dto1, "-1");
			return dto1;
		}
		return null;
	}
}
