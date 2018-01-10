package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.TaPhaDrugInventory;
import com.huatuo.clinics.cms.db.mapper.TaPhaDrugInventoryMapper;
import com.huatuo.clinics.cms.db.repository.TaPhaDrugInventoryRepository;

@Repository
public class TaPhaDrugInventoryReposityImpl implements
		TaPhaDrugInventoryRepository {
	@Resource
	private TaPhaDrugInventoryMapper inventoryMapper;



	@Override
	public List<TaPhaDrugInventory> getListPrice(HashMap<String, Object> map1) {
		return inventoryMapper.getListPrice(map1);
	}

	@Override
	public float getMaxActualPrice(HashMap<String, Object> map) {
		Float price = inventoryMapper.getMaxActualPrice(map);
		if (price == null) {
			return 0.0f;
		}
		return price;
	}
	
	@Override
	public float getMinActualPrice(HashMap<String, Object> map) {
		Float price = inventoryMapper.getMinActualPrice(map);
		if (price == null) {
			return 0.0f;
		}
		return price;
	}

}
