package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.bean.AssembleDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgDispute;
import com.huatuo.clinics.cms.db.bean.CmsJgMonitor;
import com.huatuo.clinics.cms.db.bean.CmsJgMonitorExample;
import com.huatuo.clinics.cms.db.bean.DisputeRepost;
import com.huatuo.clinics.cms.db.bean.MonitoringEvents;
import com.huatuo.clinics.cms.db.mapper.CmsJgDisputeMapper;
import com.huatuo.clinics.cms.db.mapper.CmsJgMonitorMapper;
import com.huatuo.clinics.cms.db.repository.DbCmsJgDisputeRepository;
import com.huatuo.common.Utils;
@Repository
public class DbCmsJgDisputeRepositoryImpl implements DbCmsJgDisputeRepository {
	@Autowired
	private CmsJgDisputeMapper cmsJgDisputeMapper;
	
	@Autowired
	private CmsJgMonitorMapper cmsJgMonitorMapper;

	@Override
	public int saveDispute(CmsJgDispute dispute) {
		return cmsJgDisputeMapper.insert(dispute);
	}

	@Override
	public int updateDispute(CmsJgDispute dispute) {
		return cmsJgDisputeMapper.updateByPrimaryKeySelective(dispute);
	}

	@Override
	public List<CmsJgDispute> queryDisputes(String key, String beginDate,
			String endDate, String countyCode) {
		HashMap<String, Object> map = new HashMap<>();
		if (!Utils.isBlank(key)) {
			map.put("key", key);
		}
		if (!Utils.isBlank(beginDate)) {
			map.put("beginDate", beginDate);
		}
		if (!Utils.isBlank(endDate)) {
			map.put("endDate", endDate);
		}
		if (!Utils.isBlank(countyCode)) {
			map.put("countyCode", countyCode);
		}
		return cmsJgDisputeMapper.queryDisputes(map);
	}

	@Override
	public List<DisputeRepost> queryMedicalDisputeNumber(String reportType,
			String year, String year2, String month, String month2, String day,
			String address, String status) {
		HashMap<String, Object> map = new HashMap<>();
		if (!Utils.isBlank(reportType)) {
			map.put("reportType", reportType);
		}
		if (!Utils.isBlank(year)) {
			map.put("year", year);
		}
		if (!Utils.isBlank(year2)) {
			map.put("year2", year2);
		}
		if (!Utils.isBlank(month)) {
			map.put("month", month);
		}
		if (!Utils.isBlank(month2)) {
			map.put("month2", month2);
		}
		if (!Utils.isBlank(day)) {
			map.put("day", day);
		}
		if (!Utils.isBlank(address)) {
			map.put("address", address);
		}
		if (!Utils.isBlank(status)) {
			map.put("status", status);
		}
		return cmsJgDisputeMapper.queryMedicalDisputeNumber(map);
	}

	@Override
	public List<CmsJgDispute> queryDisputesByReport(String reportType,
			String year, String year2, String month, String month2, String day,
			String address) {
		HashMap<String, Object> map = new HashMap<>();
		if (!Utils.isBlank(reportType)) {
			map.put("reportType", reportType);
		}
		if (!Utils.isBlank(year)) {
			map.put("year", year);
		}
		if (!Utils.isBlank(year2)) {
			map.put("year2", year2);
		}
		if (!Utils.isBlank(month)) {
			map.put("month", month);
		}
		if (!Utils.isBlank(month2)) {
			map.put("month2", month2);
		}
		if (!Utils.isBlank(day)) {
			map.put("day", day);
		}
		if (!Utils.isBlank(address)) {
			map.put("address", address);
		}
		return cmsJgDisputeMapper.queryDisputesByReport(map);
	}

	@Override
	public List<AssembleDTO> queryDisputeNo(String date, String endDate, List<String> orgId) {
		HashMap<String, Object> map = new HashMap<>();
		if (!Utils.isBlank(date)) {
			map.put("beginDate", date);
		}
		if (!Utils.isBlank(endDate)) {
			map.put("endDate", endDate);
		}
		if (orgId.size() > 0) {
			map.put("list", orgId);
		}
		return cmsJgDisputeMapper.queryDisputeNo(map);
	}

	@Override
	public List<AssembleDTO> queryInfectionNo(String beginDate, String endDate, List<String> orgId) {
		HashMap<String, Object> map = new HashMap<>();
		if (!Utils.isBlank(beginDate)) {
			map.put("beginDate", beginDate);
		}
		if (!Utils.isBlank(endDate)) {
			map.put("endDate", endDate);
		}
		if (orgId.size() > 0) {
			map.put("list", orgId);
		}
		return cmsJgDisputeMapper.queryInfectionNo(map);
	}

	@Override
	public List<MonitoringEvents> queryType(String strId) {
		HashMap<String, Object> map = new HashMap<>();
		if (!Utils.isBlank(strId)) {
			map.put("strId", strId);
		}
		return cmsJgDisputeMapper.queryType(map);
	}

	@Override
	public CmsJgDispute queryDisputes(String id) {
		return  cmsJgDisputeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertMonitor(CmsJgMonitor dto) {
		return cmsJgMonitorMapper.insertSelective(dto);
	}

	@Override
	public  List<CmsJgMonitor> queryTypeMonitor(String str) {
		CmsJgMonitorExample example=new CmsJgMonitorExample();
		example.createCriteria().andTypeIdEqualTo(str);
		return cmsJgMonitorMapper.selectByExample(example);
	}

	@Override
	public List<AssembleDTO> queryAntibiotic(String beginDate, String endDate,
			List<String> orgId, String antibioticId) {
		HashMap<String, Object> map = new HashMap<>();
		if (!Utils.isBlank(beginDate)) {
			map.put("beginDate", beginDate);
		}
		if (!Utils.isBlank(endDate)) {
			map.put("endDate", endDate);
		}
		if (orgId.size() > 0) {
			map.put("list", orgId);
		}
		if (orgId.size() > 0) {
			map.put("antibioticId", antibioticId);
		}
		return cmsJgDisputeMapper.queryAntibiotic(map);
	}

	@Override
	public List<CmsJgMonitor> queryMonitorDtl(String date, String type,
			String name, String addressId) {
		HashMap<String, Object> map = new HashMap<>();
			map.put("date", date);
			map.put("type", type);
			map.put("name", name);
			map.put("addressId", addressId);
		return cmsJgMonitorMapper.queryMonitorDtl(map);
	}
	
}
