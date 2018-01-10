package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.bean.CmsJgRoleinfoDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgRoleinfo;
import com.huatuo.clinics.cms.db.bean.CmsJgRoleinfoExample;
import com.huatuo.clinics.cms.db.mapper.CmsJgRoleinfoMapper;
import com.huatuo.clinics.cms.db.repository.DbCmsJgRoleinfoRepository;

@Repository
public class DbCmsJgRoleinfoRepositoryImpl implements DbCmsJgRoleinfoRepository {

	@Autowired
	private CmsJgRoleinfoMapper cmsJgRoleinfoMapper;

	@Override
	public CmsJgRoleinfo addCmsRoleinfo(String name, String describe, Integer isDel) {
		CmsJgRoleinfo roleInfo = new CmsJgRoleinfo();
		roleInfo.setRolName(name);
		roleInfo.setDescribe(describe);
		roleInfo.setIsDel(isDel);
		cmsJgRoleinfoMapper.insert(roleInfo);
		return getCmsRoleinfo(name);
	}

	@Override
	public CmsJgRoleinfo getCmsRoleinfo(String name) {
		CmsJgRoleinfoExample example = new CmsJgRoleinfoExample();
		CmsJgRoleinfoExample.Criteria criteria = example.createCriteria();
		criteria.andRolNameEqualTo(name);
		List<CmsJgRoleinfo> list = cmsJgRoleinfoMapper.selectByExample(example);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int deleteCmsRoleinfo(Long roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(roleId != null){
			map.put("roleId", roleId);
		}
		int i = cmsJgRoleinfoMapper.DeleteCmsRoleinfo(map);
		cmsJgRoleinfoMapper.DeleteUserRoleinfo(map);
		cmsJgRoleinfoMapper.DeleteRoleinfomenu(map);
		return i;
	}

//	@Override
//	public int countRole() {
//		CmsRoleinfoExample example = new CmsRoleinfoExample();
//		return cmsRoleinfoMapper.countByExample(example);
//	}

	@Override
	public List<CmsJgRoleinfo> getCmsRoleinfo(String roleName, Integer start, Integer pageSize, Integer isDel) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != roleName){
			map.put("roleName", roleName);
		}
		map.put("start", start);
		map.put("pageSize", pageSize);
		map.put("isDel", isDel);
		return cmsJgRoleinfoMapper.queryRole(map);
	}
	
	@Override
	public List<CmsJgRoleinfo> getCmsRoleinfos(String roleName, Integer start, Integer pageSize, Integer isDel) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != roleName){
			map.put("roleName", roleName);
		}
		map.put("start", start);
		map.put("pageSize", pageSize);
		map.put("isDel", isDel);
		return cmsJgRoleinfoMapper.queryRoles(map);
	}

//	@Override
//	public int updateCmsRoleinfo(CmsRoleinfo roleInfo) {
//		return cmsRoleinfoMapper.updateByPrimaryKey(roleInfo);
//	}

	@Override
	public int countUserRoles(Long roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(roleId != null){
			map.put("roleId", roleId);
		}
		return cmsJgRoleinfoMapper.countUserRoles(map);
	}

	@Override
	public CmsJgRoleinfo getCmsRoleinfoById(Long roleId) {
		return cmsJgRoleinfoMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public List<String> getMenuIds(Long roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(roleId != null){
			map.put("roleId", roleId);
		}
		return cmsJgRoleinfoMapper.getMenuIds(map);
	}

	@Override
	public int updateCmsRoleinfo(Long roleId, String roleName, String describe) {
		CmsJgRoleinfoExample example = new CmsJgRoleinfoExample();
		example.createCriteria().andIdNotEqualTo(roleId).andRolNameEqualTo(roleName);
		if(cmsJgRoleinfoMapper.countByExample(example) > 0) {
			return 0;
		}
		CmsJgRoleinfo role = new CmsJgRoleinfo();
		role.setId(roleId);
		role.setRolName(roleName);
		role.setDescribe(describe);
		return cmsJgRoleinfoMapper.updateByPrimaryKey(role);
	}

	@Override
	public List<CmsJgRoleinfo> queryRole(Long roleId, String roleName) {
		CmsJgRoleinfoExample example=new CmsJgRoleinfoExample();
		example.createCriteria().andRolNameEqualTo(roleName).andIdNotEqualTo(roleId);
		return cmsJgRoleinfoMapper.selectByExample(example);
	}

	@Override
	public List<CmsJgRoleinfo> queryRoleByid(Integer isDel) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isDel", isDel);
		return cmsJgRoleinfoMapper.queryRoleByid(map);
	}

//	@Override
//	public List<String> getRoleIds(Long uid) {
//		return cmsRoleinfoMapper.getRoleIds(uid);
//	}
//
//	@Override
//	public int deleteUserRole(Long userId) {
//		return cmsRoleinfoMapper.deleteUserRole(userId);
//	}

}