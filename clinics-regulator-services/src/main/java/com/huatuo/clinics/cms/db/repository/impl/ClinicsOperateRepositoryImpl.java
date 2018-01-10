package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.OperateInfo;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.mapper.SmCliAreaCoverMapper;
import com.huatuo.clinics.cms.db.repository.ClinicsOperateRepository;


@Repository
public class ClinicsOperateRepositoryImpl implements ClinicsOperateRepository {
	
	@Autowired
	private SmCliAreaCoverMapper smCliAreaCoverMapper;

//	@Override
//	public List<reportResultDB> queryOperate(String year, String parentAreaCode,String sourceCode) {
//		Map<String,Object> map=new HashMap<String,Object>(); 
//		map.put("year", year);
//		map.put("parentAreaCode", parentAreaCode);
//		map.put("sourceCode", sourceCode);
//		return smCliAreaCoverMapper.queryOperateOutline(map);
//	}

	@Override
	public List<ReportResultDB> queryVisit(String year, String month,
			String reportType, String parentAreaCode, String sourceCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		map.put("sourceCode", sourceCode);
		return smCliAreaCoverMapper.queryVisit(map);
	}

	@Override
	public List<ReportResultDB> querySubordinate(String year, String month,
			String reportType, String parentAreaCode, String sourceCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		map.put("sourceCode", sourceCode);
		return smCliAreaCoverMapper.querySubordinate(map);
	}

	@Override
	public List<ReportResultDB> queryAveOutpatient(String year, String month,
			String reportType, String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.queryAveOutpatient(map);
	}


	@Override
	public List<ReportResultDB> queryTimeTrend(String startYear,
			String endYear, String startMonth, String endMonth,
			String reportType, String parentAreaCode, String sourceCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("startYear", startYear);
		map.put("endYear", endYear);
		map.put("startMonth", startMonth);
		map.put("endMonth", endMonth);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		map.put("sourceCode", sourceCode);
		return smCliAreaCoverMapper.queryTimeTrend(map);
	}

	@Override
	public List<ReportResultDB> querySum(String year, String month,
			String reportType, String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.querySum(map);
	}

	@Override
	public List<ReportResultDB> queryFirstVisit(String year, String month,
			String reportType, String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.queryFirstVisit(map);
	}

	@Override
	public List<ReportResultDB> queryRepeatVisit(String year, String month,
			String reportType, String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.queryRepeatVisit(map);
	}

	@Override
	public List<ReportResultDB> queryWestVisit(String year, String month,
			String reportType, String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.queryWestVisit(map);
	}

	@Override
	public List<ReportResultDB> queryInVisit(String year, String month,
			String reportType, String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.queryInVisit(map);
	}

	@Override
	public List<ReportResultDB> queryClQty(String year, String month,
			String reportType, String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("reportType", reportType);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.queryClQty(map);
	}

	
	


}
