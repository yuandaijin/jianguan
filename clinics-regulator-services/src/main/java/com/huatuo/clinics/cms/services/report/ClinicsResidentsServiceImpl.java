package com.huatuo.clinics.cms.services.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.repository.ClinicsResidentsRepository;
import com.huatuo.clinics.cms.util.Utils;

@Service
public class ClinicsResidentsServiceImpl implements ClinicResidentsService {
	
	@Autowired
	private ClinicsResidentsRepository clinicsResidentsRepository;
	
	
	@Override
	public List<ReportResultDTO> queryReIncidence(String year, String parentAreaCode, String reportType, String month) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsResidentsRepository.queryReIncidence(year,parentAreaCode,reportType,month);
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
	public List<ReportResultDTO> queryReGiven(String year, String parentAreaCode,
			String reportType, String month, String code) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsResidentsRepository.queryReGiven(year,parentAreaCode,reportType,month,code);
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
	public String queryReIncidenceSum(String year, String parentAreaCode, String reportType, String month) {
		String sum=clinicsResidentsRepository.queryReIncidenceSum(year,parentAreaCode,reportType,month);
		 if(null != sum){
			 return sum;
		 }
		return null;
	}


	@Override
	public String queryDiseasesSum(String year, String parentAreaCode,
			String startMonth, String endMonth, String day, String reportType) {
		String sum=clinicsResidentsRepository.queryDiseasesSum(year,parentAreaCode,startMonth,endMonth,day,reportType);
		 if(null != sum){
			 return sum;
		 }
		return null;
	}


	@Override
	public List<ReportResultDTO> queryDiseases(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsResidentsRepository.queryDiseases(year,parentAreaCode,startMonth,endMonth,day,reportType);
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
	public List<ReportResultDTO> queryFirstTen(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType) {
		List<ReportResultDTO> resultLists = new ArrayList<ReportResultDTO>();
		List<ReportResultDB> resultList = new ArrayList<ReportResultDB>();
		List<ReportResultDB> list = clinicsResidentsRepository
				.queryFirstTenTown(year, parentAreaCode, startMonth, endMonth,day,reportType);
		ReportResultDB redto = new ReportResultDB();
		int len = list.size()-1;
		for (int i = 0; i < len; i++) {
			// 先取出前三条
			if(i==0){
				redto = list.get(i);
				resultList.add(redto);
				redto = list.get(i+1);
				resultList.add(redto);
				redto = list.get(i+2);
				resultList.add(redto);
			}
			if (i < len) {
				// 通过不同的病名称，取出前三条
				if (!list.get(i).getName().equals(list.get(i + 1).getName())) {
					redto = list.get(i + 1);
					resultList.add(redto);
					redto = list.get(i + 2);
					resultList.add(redto);
					redto = list.get(i + 3);
					resultList.add(redto);
				}
			}
		}
		for (ReportResultDB reportResultDB : resultList) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB,
					ReportResultDTO.class);
			resultLists.add(dto);
		}
		if (resultList.size() > 0) {
			return resultLists;
		}
		return null;
	}


	@Override
	public List<ReportResultDTO> queryFirstTenType(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType) {List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
			List<ReportResultDB> list=clinicsResidentsRepository.queryFirstTentype(year,parentAreaCode,startMonth,endMonth,day,reportType);
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
	public List<ReportResultDTO> queryFirstTenTypes(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType) {List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
			List<ReportResultDB> list=clinicsResidentsRepository.queryFirstTentypes(year,parentAreaCode,startMonth,endMonth,day,reportType);
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
	public List<ReportResultDTO> queryFirstTenSum(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsResidentsRepository.queryFirstTenSum(year,parentAreaCode,startMonth,endMonth,day,reportType);
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
