package com.huatuo.clinics.cms.services.regulator;

import java.util.Date;
import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDtlDTO;


/**
 * 处方抽查服务
 * @author ydj
 *
 */
public interface OrderCheckService {
	
	/**
	 * 处方抽查汇总以及明细信息保存
	 * @param dto
	 * @return
	 */
	String insertOrder(CmsJgOrdercheckDTO dto);
	
	/**
	 * 处方抽查记录查询
	 * @param startDate
	 * @param endDate
	 * @param checkArea
	 * @param orgName
	 * @return
	 */
	List<CmsJgOrdercheckDTO> queryOrderRecord(String startDate, String endDate,
			String checkArea, String orgName);
	
	/**
	 * 合格或不合格处方记录查询
	 * @param orderId
	 * @param flag
	 * @return
	 */
	List<CmsJgOrdercheckDtlDTO> queryOrderQualified(String orderId, Integer flag);

	/**
	 * 查询审核后的处方审核结果
	 * @param orderId
	 * @return
	 */
	List<CmsJgOrdercheckDtlDTO> queryOrder(String orderId);
	
	/**
	 * 查询处方头信息
	 * @param checkId
	 * @return
	 */
	CmsJgOrdercheckDTO queryOrderHands(String checkId);

	
	
	
}
