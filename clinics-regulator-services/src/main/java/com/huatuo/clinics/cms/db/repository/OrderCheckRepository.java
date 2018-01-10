package com.huatuo.clinics.cms.db.repository;

import java.util.Date;
import java.util.List;

import com.huatuo.clinics.cms.db.bean.CmsJgOrdercheck;
import com.huatuo.clinics.cms.db.bean.CmsJgOrdercheckDtl;



public interface OrderCheckRepository {
	
	/**
	 * 保存处方汇总信息
	 * @param exchangeObject
	 * @return
	 */
	int insertOrderSummary(CmsJgOrdercheck exchangeObject);
	
	/**
	 * 保存处方明细
	 * @param exchangeObject
	 * @return
	 */
	int insertOrderDtl(CmsJgOrdercheckDtl exchangeObject);
	
	/**
	 * 处方抽查记录查询
	 * @param startDate
	 * @param endDate
	 * @param checkArea
	 * @param orgName
	 * @return
	 */
	List<CmsJgOrdercheck> queryOrderRecord(String startDate, String endDate,
			String checkArea, String orgName);
	
	/**
	 * 合格或不合格处方记录查询
	 * @param orderId
	 * @param flag
	 * @return
	 */
	List<CmsJgOrdercheckDtl> queryOrderQualified(String orderId, Integer flag);
	
	/**
	 * 查询审核后的处方审核结果
	 * @param orderId
	 * @return
	 */
	List<CmsJgOrdercheckDtl> queryOrder(String orderId);
	
	/**
	 * 查询处方头信息
	 * @param checkId
	 * @return
	 */
	CmsJgOrdercheck queryOrderHands(String checkId);
	
	


}
