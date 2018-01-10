package com.huatuo.clinics.cms.services.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.repository.ClinicsEfficiencyRepository;
import com.huatuo.clinics.cms.util.Utils;

@Service
public class ClinicsEfficiencyServiceImpl implements ClinicsEfficiencyService {
	
	@Autowired
	private ClinicsEfficiencyRepository clinicsEfficiencyRepository;

	@Override
	public List<ReportResultDTO> queryServiceQty(String year, String parentAreaCode, String reportType, String month, String day) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsEfficiencyRepository.queryServiceQty(year,parentAreaCode,reportType,month,day);
		String name= null;
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			if(reportType.equals("year")){
				name=dto.getName()+"年";
			}else{
				name=dto.getName()+"月";
			}
			dto.setName(name);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List<ReportResultDTO> queryServiceEff(String year, String parentAreaCode,
			String code, String reportType, String month) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsEfficiencyRepository.queryServiceEff(year,parentAreaCode,code,reportType,month);
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List<ReportResultDTO> queryJunior(String year, String parentAreaCode,
			String code, String month, String reportType) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsEfficiencyRepository.queryJunior(year,parentAreaCode,code,month,reportType);
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	


}
