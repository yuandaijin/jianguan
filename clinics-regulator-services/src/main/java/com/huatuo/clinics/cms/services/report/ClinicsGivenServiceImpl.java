package com.huatuo.clinics.cms.services.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.repository.ClinicsGivenRepository;
import com.huatuo.clinics.cms.util.Utils;

@Service
public class ClinicsGivenServiceImpl implements ClinicGivenService {
	
	@Autowired
	private ClinicsGivenRepository clinicsGivenRepository;

	@Override
	public List<ReportResultDTO> queryGivenSummary(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType, String code) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsGivenRepository.queryGivenSummary(year,parentAreaCode,startMonth,endMonth,day,reportType,code);
		String name= null;
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			if(reportType.equals("year")){
				name=year+"年";
			}else if(reportType.equals("month")){
				name=year+"年"+startMonth+"月";
			}else{
				name=year+"年"+startMonth+"月-"+endMonth+"月";
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
	public List<ReportResultDTO> queryGivenFeatures(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType, String code) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsGivenRepository.queryGivenFeatures(year,parentAreaCode,startMonth,endMonth,day,reportType,code);
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
	public List<ReportResultDTO> queryGivenSexOrAge(String year,
			String parentAreaCode, String startMonth, String endMonth,
			String reportType, String code,String day) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsGivenRepository.queryGivenSexOrAge(year,parentAreaCode,startMonth,endMonth,reportType,code,day);
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
	public List<ReportResultDTO> queryGivenTimeTrend(String startYear,String endYear,
			String parentAreaCode, String startMonth, String endMonth,String startDay,String endDay,
			String reportType, String code) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsGivenRepository.queryGivenTimeTrend(startYear,endYear,parentAreaCode,startMonth,endMonth,startDay,endDay,reportType,code);
		String name=null;
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			if(reportType.equals("year")){
				name=dto.getName()+"年";
			}else if(reportType.equals("month")){
				name=dto.getName()+"月";
			}else{
				name=dto.getName()+"日";
			}
			dto.setName(name);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}
	
	
	
	
	
	

	


}
