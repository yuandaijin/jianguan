package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.mapper.SmCliAreaCoverMapper;
import com.huatuo.clinics.cms.db.repository.ClinicsGivenRepository;


@Repository
public class ClinicsGivenRepositoryImpl implements ClinicsGivenRepository {
	
	
	
	@Autowired
	private SmCliAreaCoverMapper smCliAreaCoverMapper;
	
	
	@Override
	public List<ReportResultDB> queryGivenSummary(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType, String code) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		map.put("code", code);
		map.put("day", day);
		return smCliAreaCoverMapper.queryGivenSummary(map);
	}


	@Override
	public List<ReportResultDB> queryGivenFeatures(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType, String code) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		map.put("code", code);
		map.put("day", day);
		return smCliAreaCoverMapper.queryGivenFeatures(map);
	}


	@Override
	public List<ReportResultDB> queryGivenSexOrAge(String year,
			String parentAreaCode, String startMonth, String endMonth,
			String reportType, String code,String day) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		map.put("code", code);
		map.put("day", day);
		return smCliAreaCoverMapper.queryGivenSexOrAge(map);
	}


	@Override
	public List<ReportResultDB> queryGivenTimeTrend(String startYear,String endYear,
			String parentAreaCode, String startMonth, String endMonth,String startDay,String endDay,
			String reportType, String code) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("startYear", startYear);
		map.put("endYear", endYear);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		map.put("code", code);
		map.put("startDay", startDay);
		map.put("endDay", endDay);
		return smCliAreaCoverMapper.queryGivenTimeTrend(map);
	}

	
	
	


}
