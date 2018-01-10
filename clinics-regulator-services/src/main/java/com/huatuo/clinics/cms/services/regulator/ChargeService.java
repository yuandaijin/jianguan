package com.huatuo.clinics.cms.services.regulator;


/**
 * 费用相关接口，包括收费，退费
 * @author duanzongxiang
 *
 */
public interface ChargeService {

	
	/**
	 * 分批次查询收费价格
	 * @param buyQty
	 * @param phaDrugId
	 * @param orgId
	 * @param buyUnit
	 * @return
	 */
	public float getActualPrice(Integer convQty,Integer buyQty, String phaDrugId, String orgId,
			Integer sign);
	
}
