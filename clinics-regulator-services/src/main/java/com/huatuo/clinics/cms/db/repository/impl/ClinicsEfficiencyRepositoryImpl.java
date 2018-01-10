package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.mapper.SmCliAreaCoverMapper;
import com.huatuo.clinics.cms.db.repository.ClinicsEfficiencyRepository;


@Repository
public class ClinicsEfficiencyRepositoryImpl implements ClinicsEfficiencyRepository {
	
	@Autowired
	private SmCliAreaCoverMapper smCliAreaCoverMapper;

	@Override
	public List<ReportResultDB> queryServiceQty(String year, String parentAreaCode, String reportType, String month, String day) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("reportType", reportType);
		map.put("month", month);
		map.put("day", day);
		return smCliAreaCoverMapper.queryServiceQty(map);
	}

	@Override
	public List<ReportResultDB> queryServiceEff(String year, String parentAreaCode,
			String code, String reportType, String month) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("code", code);
		map.put("reportType", reportType);
		map.put("month", month);
		return smCliAreaCoverMapper.queryServiceEff(map);
	}

	@Override
	public List<ReportResultDB> queryJunior(String year, String parentAreaCode,
			String code, String month, String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("code", code);
		map.put("month", month);
		map.put("reportType", reportType);
		return smCliAreaCoverMapper.queryJunior(map);
	}

	


}
