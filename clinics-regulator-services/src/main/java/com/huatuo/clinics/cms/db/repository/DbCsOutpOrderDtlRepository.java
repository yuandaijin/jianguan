package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtl;

/**
 * 处方详情操作
 * @author hy
 *
 */
public interface DbCsOutpOrderDtlRepository {
	
	/**
	 * 根据医嘱单ID和处方类型拿到详情
	 * @param ordId
	 * @param type
	 * @return
	 */
	List<CsOutpOrderDtl> query(String ordId, String type);
	
	/**
	 * 通过主键拿到处方详情
	 * @param ordDtlId
	 * @return
	 */
	CsOutpOrderDtl getDelById(String ordDtlId);

	/**
	 * 保存详情
	 * @return
	 */
	int addOrderDtl(List<CsOutpOrderDtl> list);
	
	/**
	 * 删除详情
	 * @return
	 */
	int deleteOrderDtl(String orderId);
	
	/**
	 * 修改处方详情表
	 * @param csOutpOrderDtlDTO
	 * @return
	 */
	Boolean update(CsOutpOrderDtl orderDtl);
	
	/**
	 * 统计未收费处方详情
	 * @return
	 */
	int countOrderDtl(String ordId);
	/**
	 * 更新退回数量
	 * @param dtl2
	 * @return
	 */
	Boolean updateSelect(CsOutpOrderDtl dtl2);
}
