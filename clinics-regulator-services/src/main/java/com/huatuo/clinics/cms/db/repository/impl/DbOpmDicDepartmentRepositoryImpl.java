package com.huatuo.clinics.cms.db.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.OpmDicDepartment;
import com.huatuo.clinics.cms.db.mapper.OpmDicDepartmentMapper;
import com.huatuo.clinics.cms.db.repository.DbOpmDicDepartmentRepository;

@Repository
public class DbOpmDicDepartmentRepositoryImpl implements
		DbOpmDicDepartmentRepository {
	
	@Autowired
	private OpmDicDepartmentMapper opmDicDepartmentMapper;
	
	
	@Override
	public OpmDicDepartment selectDepartmentById(String depId) {
		return opmDicDepartmentMapper.selectByPrimaryKey(depId);
	}

	

}
