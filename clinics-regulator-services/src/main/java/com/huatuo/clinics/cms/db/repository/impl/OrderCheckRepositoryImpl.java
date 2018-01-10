package com.huatuo.clinics.cms.db.repository.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.CmsJgOrdercheck;
import com.huatuo.clinics.cms.db.bean.CmsJgOrdercheckDtl;
import com.huatuo.clinics.cms.db.bean.CmsJgOrdercheckDtlExample;
import com.huatuo.clinics.cms.db.bean.CmsJgOrdercheckExample;
import com.huatuo.clinics.cms.db.bean.CmsJgOrdercheckExample.Criteria;
import com.huatuo.clinics.cms.db.mapper.CmsJgOrdercheckDtlMapper;
import com.huatuo.clinics.cms.db.mapper.CmsJgOrdercheckMapper;
import com.huatuo.clinics.cms.db.repository.OrderCheckRepository;
import com.huatuo.common.Utils;


@Repository
public class OrderCheckRepositoryImpl implements OrderCheckRepository {
	@Autowired
	private CmsJgOrdercheckMapper cmsJgOrdercheckMapper;
	@Autowired
	private CmsJgOrdercheckDtlMapper cmsJgOrdercheckDtlMapper;
	
	@Override
	public int insertOrderSummary(CmsJgOrdercheck cmsJgOrdercheck) {
		return cmsJgOrdercheckMapper.insertSelective(cmsJgOrdercheck);
	}

	@Override
	public int insertOrderDtl(CmsJgOrdercheckDtl cmsJgOrdercheckDtl) {
		return cmsJgOrdercheckDtlMapper.insertSelective(cmsJgOrdercheckDtl);
	}

	
	@Override
	public List<CmsJgOrdercheck> queryOrderRecord(String startDate, String endDate,
			String checkArea, String orgName) {
		CmsJgOrdercheckExample example =new CmsJgOrdercheckExample();
		Criteria criteria=example.createCriteria();
		if(null != startDate && null != endDate){
			criteria.andCheckTimeBetween(startDate, endDate);
		}
		if(!Utils.isBlank(checkArea)){
			criteria.andCheckAreaEqualTo(checkArea);
		}
		if(!Utils.isBlank(orgName)){
			criteria.andOrgNameEqualTo(orgName);
		}
		example.setOrderByClause("check_time");
		return cmsJgOrdercheckMapper.selectByExample(example);
	}

	@Override
	public List<CmsJgOrdercheckDtl> queryOrderQualified(String orderId,
			Integer flag) {
		CmsJgOrdercheckDtlExample example =new CmsJgOrdercheckDtlExample();
		example.createCriteria().andCheckResultEqualTo(flag).andOrderIdEqualTo(orderId);
		return cmsJgOrdercheckDtlMapper.selectByExample(example);
	}

	@Override
	public List<CmsJgOrdercheckDtl> queryOrder(String orderId) {
		CmsJgOrdercheckDtlExample example =new CmsJgOrdercheckDtlExample();
		example.createCriteria().andIdEqualTo(orderId);
		return cmsJgOrdercheckDtlMapper.selectByExample(example);
	}

	@Override
	public CmsJgOrdercheck queryOrderHands(String checkId) {
		return cmsJgOrdercheckMapper.selectByPrimaryKey(checkId);
	}



}
