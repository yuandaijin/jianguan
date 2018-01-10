package com.huatuo.clinics.cms.db.repository;

import com.huatuo.clinics.cms.db.bean.OpmDicDepartment;


public interface DbOpmDicDepartmentRepository {

 
    /**
     *通过主键id查询单条
     */
    OpmDicDepartment selectDepartmentById(String depId);

    
	
}
