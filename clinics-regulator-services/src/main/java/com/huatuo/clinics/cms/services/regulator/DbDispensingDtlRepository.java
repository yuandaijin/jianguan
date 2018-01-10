package com.huatuo.clinics.cms.services.regulator;

import java.util.HashMap;

public interface DbDispensingDtlRepository {

	
	/**
	 * 拿取一个药品未发药的大规格总数量
	 * @param map1
	 * @return
	 */
	int getMaxActualQuantity(HashMap<String, Object> map1);
	/**
	 * 拿取一个药品未发药的大规格总数量
	 * @param map1
	 * @return
	 */
	int getMinActualQuantity(HashMap<String, Object> map1);

}
