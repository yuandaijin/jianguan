package com.huatuo.clinics.cms.services.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.repository.ClinicsCostRepository;
import com.huatuo.clinics.cms.util.Utils;

@Service
public class ClinicsCostServiceImpl implements ClinicsCostService {
	
	@Autowired
	private ClinicsCostRepository clinicsCostRepository;

	@Override
	public List<ReportResultDTO> queryCostQty(String year, String parentAreaCode, String month, String reportType) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsCostRepository.queryCostQty(year,parentAreaCode,month,reportType);
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
	public List<ReportResultDTO> queryCostType(String year, String parentAreaCode,
			String typeCode, String month, String reportType) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsCostRepository.queryCostType(year,parentAreaCode,typeCode,month,reportType);
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

//	@Override
//	public List<reportResultDTO> queryLower(String year, String parentAreaCode) {
//		List<reportResultDTO> resultList=new ArrayList<reportResultDTO>();
//		List<reportResultDB> list=clinicsCostRepository.queryLower(year,parentAreaCode);
//		for (reportResultDB reportResultDB : list) {
//			reportResultDTO dto = Utils.exchangeObject(reportResultDB, reportResultDTO.class);
//			resultList.add(dto);
//		}
//		if(resultList.size() > 0){
//			return resultList;
//		}
//		return null;
//	}

	

	


}
