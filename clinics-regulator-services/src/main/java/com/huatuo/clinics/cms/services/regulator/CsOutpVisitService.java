package com.huatuo.clinics.cms.services.regulator;

import com.huatuo.clinics.cms.bean.CsOutpVisitDTO;
import com.huatuo.clinics.cms.db.bean.CsOutpVisit;
import com.huatuo.clinics.cms.db.bean.CsOutpVisitDx;


/**
 * 就诊记录相关服务
 * @param <K>
 *
 */
public interface CsOutpVisitService{

	
	/**
	 * 通过就诊id拿到患者id
	 * @param visitId
	 * @return
	 */
	CsOutpVisitDTO queryPatientId(String visitId);

	/**
	 * 查询过敏史和诊断
	 * @param dto
	 * @return
	 */
	CsOutpVisitDTO selectAllergicAndDxType(CsOutpVisitDTO dto);
	
	/**
	 * 通过id查询一个挂号对象
	 */
	CsOutpVisit getById(String vistiId);
	
	/**
	 * 通过诊断明细Id查询诊断信息
	 */
	CsOutpVisitDx queryCsVisitDx(String dxId);
}
