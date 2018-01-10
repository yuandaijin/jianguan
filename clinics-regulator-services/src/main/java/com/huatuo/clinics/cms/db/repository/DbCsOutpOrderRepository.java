package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.CsOutpOrder;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtl;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtlExample;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderExample;

/**
 * 医嘱、处方服务接口
 */
public interface DbCsOutpOrderRepository {
	/**
	 * 根据就诊号查询诊所下的处方
	 * @param orderId 
	 * @param vistiNo
	 * @return
	 */
	List<CsOutpOrder> query(List<String> strList,String startDate,String endDate,String orderType,String userId);
	
	/**
	 * 根据诊所id拿到单个处方信息
	 * @param orderId
	 * @return
	 */
	CsOutpOrder getByVisitNo(String orderId);

	/**
	 * 根据查询条件得到处方对象
	 */
	List<CsOutpOrder> getByExample(CsOutpOrderExample example);
	
	/**
	 * 根据处方id获得处方列表
	 * @param example
	 * @return
	 */
	List<CsOutpOrderDtl> getOrderDtList(CsOutpOrderDtlExample example);
	
}
