package com.huatuo.clinics.cms.services.regulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.OpmDicDepartmentDTO;
import com.huatuo.clinics.cms.db.repository.DbOpmDicDepartmentRepository;
import com.huatuo.common.Utils;

@Service
public class OpmDicDepartmentServiceImpl implements OpmDicDepartmentService {
	
	@Autowired
	private DbOpmDicDepartmentRepository dbOpmDicDepartmentRepository;

	@Override
	public OpmDicDepartmentDTO getById(String id) {
		return Utils.exchangeObject(dbOpmDicDepartmentRepository.selectDepartmentById(id), OpmDicDepartmentDTO.class);
	}

}
