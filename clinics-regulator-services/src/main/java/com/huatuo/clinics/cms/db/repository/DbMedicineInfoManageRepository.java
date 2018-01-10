package com.huatuo.clinics.cms.db.repository;

import com.huatuo.clinics.cms.db.bean.TaPhaDrugInfo;

/**
 * 诊所药品信息相关操作
 * @author hy
 *
 */
public interface DbMedicineInfoManageRepository {

	/**
	 * 根据id查询单个对象
	 * @param phaDrugId
	 * @return
	 */
	public TaPhaDrugInfo getOneDTO(String phaDrugId);

}
