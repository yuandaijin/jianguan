package com.huatuo.clinics.cms.db.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.CmPerReportDefend;
import com.huatuo.clinics.cms.db.bean.CmPerReportDefendExample;
import com.huatuo.clinics.cms.db.bean.CmPerReportDefendExample.Criteria;
import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgUserParts;
import com.huatuo.clinics.cms.db.bean.CmsJgUserRole;
import com.huatuo.clinics.cms.db.bean.CmsJgUserRoleExample;
import com.huatuo.clinics.cms.db.bean.CmsJgUserextend;
import com.huatuo.clinics.cms.db.bean.CmsJgUserextendExample;
import com.huatuo.clinics.cms.db.bean.CmsJgUserinfo;
import com.huatuo.clinics.cms.db.bean.CmsJgUserinfoExample;
import com.huatuo.clinics.cms.db.mapper.CmPerReportDefendMapper;
import com.huatuo.clinics.cms.db.mapper.CmsJgUserRoleMapper;
import com.huatuo.clinics.cms.db.mapper.CmsJgUserextendMapper;
import com.huatuo.clinics.cms.db.mapper.CmsJgUserinfoMapper;
import com.huatuo.clinics.cms.db.repository.DbCmsJgUserinfoRepository;

@Repository
public class DbCmsJgUserinfoRepositoryImpl implements DbCmsJgUserinfoRepository {

	@Autowired
	private CmsJgUserinfoMapper cmsJgUserinfoMapper;
	
	@Autowired
	private CmsJgUserextendMapper cmsJgUserextendMapper;
	
	@Autowired
	private CmsJgUserRoleMapper cmsJgUserRoleMapper;
	
	@Autowired
	private CmPerReportDefendMapper cmPerReportDefendMapper;
	
//	@Override
//	public int addCmsUserinfo(CmsUserinfo userinfo) {
//		return cmsUserinfoMapper.insert(userinfo);
//	}

	@Override
	public CmsJgUserinfo getUser(String name, String pwd) {
		CmsJgUserinfoExample example = new CmsJgUserinfoExample();
		CmsJgUserinfoExample.Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(name);
		if (pwd != null) {
			criteria.andPwdEqualTo(pwd);
		}
		List<CmsJgUserinfo> list = cmsJgUserinfoMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int deleteCmsUser(Long uId) {
		CmsJgUserinfoExample example = new CmsJgUserinfoExample();
		example.createCriteria().andIdEqualTo(Integer.valueOf(String.valueOf(uId)));
		CmsJgUserextendExample exp=new CmsJgUserextendExample();
		exp.createCriteria().andUserIdEqualTo(String.valueOf(uId));
		int i=cmsJgUserinfoMapper.deleteByExample(example);//删除用户
		int s=cmsJgUserextendMapper.deleteByExample(exp);//删除用户扩展信息
		if(i>0 && s >0){//确保user和user扩展表都有删除数据
			return 1;
		}
		return 0;
	}

//	@Override
//	public CmsUserinfo getUserById(Long id) {
//		return cmsUserinfoMapper.selectByPrimaryKey(id);
//	}
//
//	@Override
//	public int updatePwd(Long id, String pwd) {
//		CmsUserinfo user = getUserById(id);
//		if (user != null) {
//			user.setPwd(pwd);
//			return cmsUserinfoMapper.updateByPrimaryKey(user);
//		}
//		return 0;
//	}

	@Override
	public int countUser(String orgId, Long isDel, String userName, Integer id) {
		Map<String,Object> map =new HashMap<String,Object>();
		if(null != orgId){
			map.put("orgId", orgId);
		}
		if(null != isDel){
			map.put("isDel", isDel);
		}
		if(null != userName){
			map.put("userName", userName);
		}
		if(null != id){
			map.put("id", id);
		}
		return cmsJgUserinfoMapper.countQuery(map);
	}

	@Override
	public List<CmsJgUserParts> getCmsUserinfo(Integer start,Integer pageSize,String orgId,String userName,Long isDel, Integer id) {
		Map<String,Object> map =new HashMap<String,Object>();
		if(null != orgId){
			map.put("orgId", orgId);
		}
		if(null != isDel){
			map.put("isDel", isDel);
		}
		if(null != userName){
			map.put("userName", userName);
		}
		if(null != id){
			map.put("id", id);
		}
		map.put("start", start);
		map.put("pageSize", pageSize);
		return cmsJgUserinfoMapper.queryAllUser(map);
	}

	@Override
	public List<CmsJgMenuInfoDTO> selectMenuList(Integer userId, Integer parentId, Boolean isShow) {
		Map<String, Object> availableMap = new HashMap<String, Object>();
		if (userId != null) {
			availableMap.put("id", userId);
		}
		if (parentId != null) {
			availableMap.put("parentId", parentId);
		}
		if (isShow != null) {
			availableMap.put("isShow", isShow.booleanValue() == true ? 0 : 1);
		}
		return cmsJgUserinfoMapper.selectByConditions(availableMap);
	}

	@Override
	public List<String> getAuthority(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("uId", userId);
		}
		return cmsJgUserinfoMapper.getAuthority(map);
	}

//	@Override
//	public List<CmsRoleinfo> getUesrRole(Long userId) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (userId != null) {
//			map.put("uId", userId);
//		}
//		return cmsUserinfoMapper.getUesrRole(map);
//	}
//
//	@Override
//	public int countUesrRole(Long userId) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (userId != null) {
//			map.put("uId", userId);
//		}
//		return cmsUserinfoMapper.countUesrRole(map);
//	}
//
//	// @Override
//	// public List<String> getOperations(Long userId, Long menuId) {
//	// Map<String,Object> map = new HashMap<String,Object>();
//	// if(userId != null){
//	// map.put("uId", userId);
//	// }
//	// if(menuId != null){
//	// map.put("menuId", menuId);
//	// }
//	// return cmsUserinfoMapper.getOperations(map);
//	// }

	@Override
	public int addUserRoles(Long userId, Integer roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		return cmsJgUserRoleMapper.insertUserAndRole(map);
	}

	@Override
	public void deleteRoleMenus(Long roleId, List<Long> list) {
		if (roleId != null && list != null && list.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("list", list);
			cmsJgUserinfoMapper.batchDeleteRoleMenus(map);
		}
	}

	@Override
	public void addRoleMenus(Long roleId, List<Long> list) {
		if (roleId != null && list != null && list.size() > 0) {
			List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
			for (Long menuId : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleId", roleId);
				map.put("menuId", menuId);
				listMaps.add(map);
			}
			cmsJgUserinfoMapper.batchInsertRoleMenu(listMaps);
		}
	}

	@Override
	public List<CmsJgMenuInfoDTO> selectMenu(Long parentId, Integer isDel) {
		Map<String, Object> availableMap = new HashMap<String, Object>();
		if (isDel != null) {
			availableMap.put("isDel", isDel);
		}
		if (parentId != null) {
			availableMap.put("parentId", parentId);
		}
		return cmsJgUserinfoMapper.selectByMenus(availableMap);
	}

	@Override
	public CmsJgUserinfo getUser(String name) {
		CmsJgUserinfo user = cmsJgUserinfoMapper.getUser(name);
		if (null != user) {
			return user;
		}
		return null;
	}

	@Override
	public void insertCmsUser(CmsJgUserinfo userbean) {
		cmsJgUserinfoMapper.insert(userbean);
	}

	@Override
	public void insertUserExtend(CmsJgUserextend teget) {
		cmsJgUserextendMapper.insertSelective(teget);
	}

	@Override
	public CmsJgUserParts getCmsUserinfo(Long uid, Integer isDel) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		if(null != uid){
			map.put("uid", uid);
		}
		if(null != isDel){
			map.put("isDel", isDel);
		}
		return cmsJgUserinfoMapper.getCmsUserinfo(map);
	}

	@Override
	public int updateUser(CmsJgUserinfo dto) {
		CmsJgUserinfoExample example = new CmsJgUserinfoExample();
		example.createCriteria().andIdEqualTo(dto.getId());
		int i= cmsJgUserinfoMapper.updateByExampleSelective(dto, example);
		if(i > 0){
			return i;
		}
		return 0;
	}

	@Override
	public int updateUserEx(CmsJgUserextend dto) {
		CmsJgUserextendExample example=new CmsJgUserextendExample();
		example.createCriteria().andUserIdEqualTo(dto.getUserId());
		int i =cmsJgUserextendMapper.updateByExampleSelective(dto, example);
		if(i>0){
			return i;
		}
		return 0;
	}

	@Override
	public int updateRole(CmsJgUserRole role) {
		CmsJgUserRoleExample example =new CmsJgUserRoleExample();
		example.createCriteria().andUserIdEqualTo(role.getUserId());
		return cmsJgUserRoleMapper.updateByExampleSelective(role, example);
	}

	@Override
	public List<CmsJgUserinfo> selectRepeatUser(String userName, Integer id) {
		CmsJgUserinfoExample example= new  CmsJgUserinfoExample();
		example.createCriteria().andUserNameEqualTo(userName).andIdNotEqualTo(id);
		return cmsJgUserinfoMapper.selectByExample(example);
	}

	@Override
	public int updatePwd(String password, Integer id) {
		CmsJgUserinfo dto= new CmsJgUserinfo();
		dto.setPwd(password);
		CmsJgUserinfoExample example=new CmsJgUserinfoExample();
		example.createCriteria().andIdEqualTo(id);
		int i = cmsJgUserinfoMapper.updateByExampleSelective(dto,example);
		if( i > 0){
			return i;
		}
		return 0;
	}

	@Override
	public int updateUserDate(CmsJgUserinfo dto) {
		int i = cmsJgUserinfoMapper.updateByPrimaryKey(dto);
		if( i > 0){
			return i;
		}
		return 0;
	}

	@Override
	public List<CmPerReportDefend> getReportAll() {
		CmPerReportDefendExample example=new CmPerReportDefendExample();
		Criteria criteria=example.createCriteria();
		criteria.andOrgIdIsNull().andValidFlgEqualTo("1");
		return cmPerReportDefendMapper.selectByExample(example);
	}

	@Override
	public int deleteReport(String id) {
		return cmPerReportDefendMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int saveReport(CmPerReportDefend inDto) {
		CmPerReportDefendExample example=new CmPerReportDefendExample();
		Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(inDto.getId());
		return cmPerReportDefendMapper.updateByExampleSelective(inDto, example);
	}

//	@Override
//	public void updateCompany(Long userid, Long companyId) {
//		if (userid != null) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("userid", userid);
//			map.put("companyId", companyId);
//			cmsUserinfoMapper.updateCompany(map);
//		}
//	}

}