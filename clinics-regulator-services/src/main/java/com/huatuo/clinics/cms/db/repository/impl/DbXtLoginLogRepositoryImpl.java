package com.huatuo.clinics.cms.db.repository.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.XtLoginLog;
import com.huatuo.clinics.cms.db.bean.XtLoginLogExample;
import com.huatuo.clinics.cms.db.mapper.XtClinicsLoginLogMapper;
import com.huatuo.clinics.cms.db.repository.DbXtLoginLogRepository;

@Repository
public class DbXtLoginLogRepositoryImpl implements DbXtLoginLogRepository {
	@Autowired
	private XtClinicsLoginLogMapper xtClinicsLoginLogMapper;

	@Override
	public List<XtLoginLog> getListByOrgId(String orgId,Date beginDate,
			Date endDate,Integer currentPage, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("currentPage", (currentPage-1)*pageSize);
		map.put("pageSize", pageSize);
		return xtClinicsLoginLogMapper.getListByOrgId(map);
	}


	@Override
	public Integer getTotalByOrgId(String orgId, Date beginDate, Date endDate) {
		XtLoginLogExample example = new XtLoginLogExample();
		example.createCriteria().andLoginTimeBetween(beginDate, endDate).andLoginOrgIdEqualTo(orgId);
		return xtClinicsLoginLogMapper.countByExample(example );
	}

}
