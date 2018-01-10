package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.mapper.SmCliAreaCoverMapper;
import com.huatuo.clinics.cms.db.repository.ClinicsCostRepository;


@Repository
public class ClinicsCostRepositoryImpl implements ClinicsCostRepository {
	
	@Autowired
	private SmCliAreaCoverMapper smCliAreaCoverMapper;

	@Override
	public List<ReportResultDB> queryCostQty(String year, String parentAreaCode, String month, String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("month", month);
		map.put("reportType", reportType);
		return smCliAreaCoverMapper.queryCostQty(map);
	}

	@Override
	public List<ReportResultDB> queryCostType(String year, String parentAreaCode,
			String typeCode, String month, String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("parentAreaCode", parentAreaCode);
		map.put("code", typeCode);
		map.put("month", month);
		map.put("reportType", reportType);
		return smCliAreaCoverMapper.queryCostType(map);
	}

//	@Override
//	public List<reportResultDB> queryLower(String year, String parentAreaCode) {
//		Map<String,Object> map=new HashMap<String,Object>(); 
//		map.put("year", year);
//		map.put("parentAreaCode", parentAreaCode);
//		return smCliAreaCoverMapper.queryLower(map);
//	}


	


}
