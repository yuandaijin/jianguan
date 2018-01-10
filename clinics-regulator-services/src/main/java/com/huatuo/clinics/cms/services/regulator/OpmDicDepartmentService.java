package com.huatuo.clinics.cms.services.regulator;

import com.huatuo.clinics.cms.bean.OpmDicDepartmentDTO;

/**
 * 科室服务
 * @author Administrator
 *
 */
public interface OpmDicDepartmentService {

	/**
	 * 根据id取对象
	 * @param string
	 * @return
	 */
	OpmDicDepartmentDTO getById(String id);

}
