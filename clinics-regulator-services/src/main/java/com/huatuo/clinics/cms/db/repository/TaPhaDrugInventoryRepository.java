package com.huatuo.clinics.cms.db.repository;

import java.util.HashMap;
import java.util.List;

import com.huatuo.clinics.cms.db.bean.TaPhaDrugInventory;

/**
 * 药品库存数据库操作类
 * 
 * @author william.zhang
 * 
 */
public interface TaPhaDrugInventoryRepository {

	/**
	 * 查询一种药品的所有库存
	 * @param map1
	 * @return
	 */
	List<TaPhaDrugInventory> getListPrice(HashMap<String, Object> map1);
	
	/**
	 * 拿取最近几次的最高收费包装价格
	 * @param map
	 * @return
	 */
	float getMaxActualPrice(HashMap<String, Object> map);
	
	/**
	 * 拿取最近几次的最高收费包装价格
	 * @param map
	 * @return
	 */
	float getMinActualPrice(HashMap<String, Object> map);
	
}