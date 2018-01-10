package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.CsOutpOrder;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtl;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtlExample;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderExample;
import com.huatuo.clinics.cms.db.mapper.CsOutpOrderDtlMapper;
import com.huatuo.clinics.cms.db.mapper.CsOutpOrderMapper;
import com.huatuo.clinics.cms.db.repository.DbCsOutpOrderRepository;

@Repository
public class DbCsOutpOrderRepositoryImpl implements DbCsOutpOrderRepository {
	
	@Autowired
	private CsOutpOrderMapper csOutpOrderMapper;
	@Autowired
	private CsOutpOrderDtlMapper csOutpOrderDtlMapper;
	
	@Override
	public List<CsOutpOrder> query(List<String> strList,String startDate,String endDate,String orderType,String userId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("strList", strList);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orderType", orderType);
		map.put("userId", userId);
		map.put("validFlg", 1);
		return csOutpOrderMapper.queryOrdId(map);
//		return csOutpOrderMapper.selectByExample(example);
		
	}


	@Override
	public CsOutpOrder getByVisitNo(String orderId) {
		return csOutpOrderMapper.selectByPrimaryKey(orderId);
	}
	
	@Override
	public List<CsOutpOrder> getByExample(CsOutpOrderExample example) {
		return csOutpOrderMapper.selectByExample(example);
	}
	@Override
	public List<CsOutpOrderDtl> getOrderDtList(CsOutpOrderDtlExample example) {
		return csOutpOrderDtlMapper.selectByExampleWithBLOBs(example);
	}
}
