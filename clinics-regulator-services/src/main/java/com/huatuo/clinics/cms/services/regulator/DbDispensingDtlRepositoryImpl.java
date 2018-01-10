package com.huatuo.clinics.cms.services.regulator;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.mapper.TaPhaDispensingDtlMapper;
@Repository
public class DbDispensingDtlRepositoryImpl implements DbDispensingDtlRepository {

	@Autowired
	private TaPhaDispensingDtlMapper taPhaDispensingDtlMapper;
	


	@Override
	public int getMaxActualQuantity(HashMap<String, Object> map1) {
		Integer i = taPhaDispensingDtlMapper.getMaxActualQuantity(map1);
		if (i == null) {
			return 0;
		}
		return i;
	}

	@Override
	public int getMinActualQuantity(HashMap<String, Object> map1) {
		Integer i = taPhaDispensingDtlMapper.getMinActualQuantity(map1);
		if (i == null) {
			return 0;
		}
		return i;
	}
}
