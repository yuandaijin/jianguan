package com.huatuo.clinics.cms.services.regulator;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.CmsJgRoleinfoDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgRoleinfo;
import com.huatuo.clinics.cms.db.repository.DbCmsJgRoleinfoRepository;
import com.huatuo.clinics.cms.db.repository.DbCmsJgUserinfoRepository;
import com.huatuo.common.Utils;

@Service
public class CmsJgRoleinfoServiceImpl implements CmsJgRoleinfoService {

	@Autowired
	private DbCmsJgRoleinfoRepository dbCmsJgRoleinfoRepository;
	
	@Autowired
	private DbCmsJgUserinfoRepository dbCmsJgUserinfoRepository;
	
	@Override
	public int deleteRole(Long roleId) {
		if(dbCmsJgRoleinfoRepository.countUserRoles(roleId) > 0){
			return -1;
		}
		return dbCmsJgRoleinfoRepository.deleteCmsRoleinfo(roleId);
	}

	@Override
	public CmsJgRoleinfoDTO getCmsRoleinfoBeanById(Long roleId){
		return Utils.exchangeObject(dbCmsJgRoleinfoRepository.getCmsRoleinfoById(roleId), CmsJgRoleinfoDTO.class) ;
	}
	
	@Override
	public List<CmsJgRoleinfoDTO> getRoles(String roleName, Integer start, Integer pageSize, Integer isDel) {
		List<CmsJgRoleinfo> list = dbCmsJgRoleinfoRepository.getCmsRoleinfos(roleName, start, pageSize,isDel);
		if(list != null && list.size() > 0){
			List<CmsJgRoleinfoDTO> listBean = new ArrayList<CmsJgRoleinfoDTO>();
			for(CmsJgRoleinfo cmsRoleinfo : list){
				CmsJgRoleinfoDTO teagrt = Utils.exchangeObject(cmsRoleinfo, CmsJgRoleinfoDTO.class);
				listBean.add(teagrt);
			}
			return listBean;
		}
		return null;
	}

	
	@Override
	public List<CmsJgRoleinfoDTO> getRole(String roleName, Integer start, Integer pageSize, Integer isDel) {
		List<CmsJgRoleinfo> list = dbCmsJgRoleinfoRepository.getCmsRoleinfo(roleName, start, pageSize,isDel);
		if(list != null && list.size() > 0){
			List<CmsJgRoleinfoDTO> listBean = new ArrayList<CmsJgRoleinfoDTO>();
			for(CmsJgRoleinfo cmsRoleinfo : list){
				CmsJgRoleinfoDTO teagrt = Utils.exchangeObject(cmsRoleinfo, CmsJgRoleinfoDTO.class);
				listBean.add(teagrt);
			}
			return listBean;
		}
		return null;
	}
	@Override
	public int addUserRoles(Long userId, Integer roleId) {
		return dbCmsJgUserinfoRepository.addUserRoles(userId, roleId);
	}

	@Override
	public void deleteRoleMenus(Long roleId, List<Long> menuId) {
		dbCmsJgUserinfoRepository.deleteRoleMenus(roleId, menuId);
	}

	@Override
	public void addRoleMenus(Long roleId, List<Long> menuId){
		dbCmsJgUserinfoRepository.addRoleMenus(roleId, menuId);
	}
	
	@Override
	public CmsJgRoleinfoDTO addRole(String name, String describe, Integer isDel) {
		if(dbCmsJgRoleinfoRepository.getCmsRoleinfo(name) != null){
			return null;
		}
		return Utils.exchangeObject(dbCmsJgRoleinfoRepository.addCmsRoleinfo(name, describe,isDel), CmsJgRoleinfoDTO.class);
	}

	@Override
	public int countUserRoles(Long roleId){
		return dbCmsJgRoleinfoRepository.countUserRoles(roleId);
	}

	@Override
	public String getMenuIds(Long roleId) {
		return Utils.listToString(dbCmsJgRoleinfoRepository.getMenuIds(roleId));
	}

	@Override
	public int updateCmsRoleinfo(Long roleId, String roleName, String describe) {
		return dbCmsJgRoleinfoRepository.updateCmsRoleinfo(roleId, roleName, describe);
	}

	@Override
	public List<CmsJgRoleinfoDTO> queryRole(Long roleId, String roleName) {
		List<CmsJgRoleinfoDTO> resultList=new ArrayList<CmsJgRoleinfoDTO>();
		List<CmsJgRoleinfo> list=dbCmsJgRoleinfoRepository.queryRole(roleId,roleName);
		for (CmsJgRoleinfo cmsJgRoleinfo : list) {
			CmsJgRoleinfoDTO dto=Utils.exchangeObject(cmsJgRoleinfo, CmsJgRoleinfoDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}else{
			return null;
		}
	}

	@Override
	public List<CmsJgRoleinfoDTO> queryRoleByid(Integer isDel) {
		List<CmsJgRoleinfo> list = dbCmsJgRoleinfoRepository.queryRoleByid(isDel);
		if(list != null && list.size() > 0){
			List<CmsJgRoleinfoDTO> listBean = new ArrayList<CmsJgRoleinfoDTO>();
			for(CmsJgRoleinfo cmsRoleinfo : list){
				CmsJgRoleinfoDTO teagrt = Utils.exchangeObject(cmsRoleinfo, CmsJgRoleinfoDTO.class);
				listBean.add(teagrt);
			}
			return listBean;
		}
		return null;
	}

//	@Override
//	public List<String> getRoleIds(Long uid) {
//		return cmsRoleinfoRepository.getRoleIds(uid);
//	}
//
//	@Override
//	public int deleteUserRole(Long userId) {
//		return cmsRoleinfoRepository.deleteUserRole(userId);
//	}
}