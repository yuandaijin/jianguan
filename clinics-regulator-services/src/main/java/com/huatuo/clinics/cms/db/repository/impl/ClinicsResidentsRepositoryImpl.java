package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.mapper.SmCliAreaCoverMapper;
import com.huatuo.clinics.cms.db.repository.ClinicsResidentsRepository;


@Repository
public class ClinicsResidentsRepositoryImpl implements ClinicsResidentsRepository {
	
	
	@Autowired
	private SmCliAreaCoverMapper smCliAreaCoverMapper;
	
	
	@Override
	public List<ReportResultDB> queryReIncidence(String year, String parentAreaCode, String reportType, String month) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("reportType", reportType);
		map.put("month", month);
		return smCliAreaCoverMapper.queryReIncidence(map);
	}


	@Override
	public List<ReportResultDB> queryReGiven(String year, String parentAreaCode,
			String reportType, String month, String code) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("code", code);
		map.put("reportType", reportType);
		map.put("month", month);
		return smCliAreaCoverMapper.queryReGiven(map);
	}


	@Override
	public String queryReIncidenceSum(String year, String parentAreaCode, String reportType, String month) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("reportType", reportType);
		map.put("month", month);
		return smCliAreaCoverMapper.queryReIncidenceSum(map);
	}


	@Override
	public String queryDiseasesSum(String year, String parentAreaCode, String startMonth,
			String endMonth, String day, String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("day", day);
		return smCliAreaCoverMapper.queryDiseasesSum(map);
	}


	@Override
	public List<ReportResultDB> queryDiseases(String year,
			String parentAreaCode, String startMonth, String endMonth,
			String day,String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("day", day);
		return smCliAreaCoverMapper.queryDiseases(map);
	}




	@Override
	public List<ReportResultDB> queryFirstTentype(String year,String parentAreaCode,String startMonth, String endMonth, String day,String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("day", day);
		return smCliAreaCoverMapper.queryFirstTentype(map);
	}


	@Override
	public List<ReportResultDB> queryFirstTenTown(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("day", day);
		return smCliAreaCoverMapper.queryFirstTenTown(map);
	}

	@Override
	public List<ReportResultDB> queryFirstTentypes(String year,String parentAreaCode,String startMonth, String endMonth, String day,String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("day", day);
		return smCliAreaCoverMapper.queryFirstTentypes(map);
	}


	@Override
	public List<ReportResultDB> queryFirstTenSum(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("day", day);
		return smCliAreaCoverMapper.queryFirstTenSum(map);
	}


	


}
