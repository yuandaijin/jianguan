package com.huatuo.clinics.cms.services.regulator;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.db.bean.TaPhaDrugInventory;
import com.huatuo.clinics.cms.db.repository.TaPhaDrugInventoryRepository;
@Service
public class ChargeServiceImpl implements ChargeService  {
	@Autowired
	private TaPhaDrugInventoryRepository inventoryRepository;//库存
	@Autowired
	private DbDispensingDtlRepository dispensingDtlRepository;//发药


	@Override
	public float getActualPrice(Integer convQty,Integer buyQty, String phaDrugId, String orgId,
			Integer sign) {
		//根据时间排序查询库存数量，取满足数量的数据条数中的最大价格作为药品价格
		//查询库存用时间进行排序
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("phaDrugId", phaDrugId);
		map1.put("orgId", orgId);
		map1.put("sign", sign);
		List<TaPhaDrugInventory> list = inventoryRepository.getListPrice(map1);
		//排除处方未发药的药品数量
		//拿取大单位数量
		int maxQuantity = dispensingDtlRepository.getMaxActualQuantity(map1);
		//拿取小单位数量
		int minQuantity = dispensingDtlRepository.getMinActualQuantity(map1);
		int minNumber = 0;
		if (sign == 0) {//包装单位
			minNumber = maxQuantity * convQty + minQuantity;
		}
		if (list.size()>0) {
			Integer sum = 0;
			int x = 0;
			for (int i = 0; i < list.size(); i++){
				sum += (list.get(i).getPackQty()*convQty + list.get(i).getMinQty());
				if ((sum - minNumber) >= buyQty*convQty) {
					x = i;
					break;
				}
			}
			if (x == 0) {//直接拿第一批次库存
				if (sign == 0) {//拿包装价
					return list.get(0).getRetailPrice();
				}else {//拿小单位价
					return list.get(0).getRetailMinPrice();
				}
			}else {
				HashMap<String, Object> map = new HashMap<>();
				map.put("phaDrugId", phaDrugId);
				map.put("orgId", orgId);
				map.put("number", x+1);
				if (sign == 0) {//拿包装价
					return inventoryRepository.getMaxActualPrice(map);
				}else {//拿小单位价
					return inventoryRepository.getMinActualPrice(map);
				}
			}
		}else {
			return 0.0f;
		}
	}

	
}

