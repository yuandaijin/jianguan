package com.huatuo.clinics.cms.db.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtl;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtlExample;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtlExample.Criteria;
import com.huatuo.clinics.cms.db.mapper.CsOutpOrderDtlMapper;
import com.huatuo.clinics.cms.db.repository.DbCsOutpOrderDtlRepository;

@Repository
public class DbCsOutpOrderDtlRepositoryImpl implements DbCsOutpOrderDtlRepository {
	
	@Autowired
	private CsOutpOrderDtlMapper csOutpOrderDtlMapper;
	
	@Override
	public List<CsOutpOrderDtl> query(String ordId, String type) {
		CsOutpOrderDtlExample example = new CsOutpOrderDtlExample();
		Criteria criteria = example.createCriteria();
		if (ordId!=null && !"".equals(ordId)) {
			criteria.andOrdIdEqualTo(ordId);
		}
		if (type!=null && !"".equals(type)) {
			criteria.andOrdTypeEqualTo(type);
		}
		List<CsOutpOrderDtl> list = csOutpOrderDtlMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public CsOutpOrderDtl getDelById(String ordDtlId) {
		return csOutpOrderDtlMapper.getDelById(ordDtlId);
	}
	
	@Override
	public int addOrderDtl(List<CsOutpOrderDtl> list) {
		int i = 0;
		for (CsOutpOrderDtl csOutpOrderDtl : list) {
			int j = csOutpOrderDtlMapper.insertSelective(csOutpOrderDtl);
			i += j;
		}
		return i;
	}

	@Override
	public Boolean update(CsOutpOrderDtl orderDtl) {
		CsOutpOrderDtlExample dtlExample = new CsOutpOrderDtlExample();
		Criteria criteria = dtlExample.createCriteria();
		criteria.andOrdDtlIdEqualTo(orderDtl.getOrdDtlId());
		int i = csOutpOrderDtlMapper.updateByExample(orderDtl, dtlExample);
		if (i>0) {
			return true;
		}
		return false;
	}

	@Override
	public int deleteOrderDtl(String ordId) {
		CsOutpOrderDtlExample example = new CsOutpOrderDtlExample();
		Criteria criteria = example.createCriteria();
		if (ordId!=null && !"".equals(ordId)) {
			criteria.andOrdIdEqualTo(ordId);
		}
		return csOutpOrderDtlMapper.deleteByExample(example);
	}

	@Override
	public int countOrderDtl(String ordId) {
		return csOutpOrderDtlMapper.countOrderDtl(ordId);
	}

	@Override
	public Boolean updateSelect(CsOutpOrderDtl dtl2) {
		int i = csOutpOrderDtlMapper.updateSelect(dtl2);
		if (i != 0) {
			return true;
		}
		return Boolean.FALSE;
	}
}
