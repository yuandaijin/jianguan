package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.CsOutpVisitDx;

public interface DbCsOutpVisitDxRepository {

	
	/**
	 * 通过就诊id查询多个就诊对象
	 * @param vistiNo
	 * @return
	 */
	List<CsOutpVisitDx> getDxListByVisitId(String visitId);
	
	/**
	 * 通过诊断明细Id查询诊断信息
	 */
	CsOutpVisitDx queryCsVisitDx(String dxId);
	
}
