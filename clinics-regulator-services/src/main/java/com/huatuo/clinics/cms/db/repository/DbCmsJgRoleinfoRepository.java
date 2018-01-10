package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgRoleinfoDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgRoleinfo;

/**
 * cms用户信息操作
 * @author ys
 *
 */
public interface DbCmsJgRoleinfoRepository {
	/**
	 * 新增角色
	 * @param name
	 * @param describe
	 * @return
	 */
	public CmsJgRoleinfo addCmsRoleinfo(String name, String describe, Integer isDel);
	
	/**
	 * 根据名字获取角色信息
	 * @param name
	 * @param isDel 
	 * @param pageSize 
	 * @param start 
	 * @return
	 */
	public CmsJgRoleinfo getCmsRoleinfo(String name);
	
	/**
	 * 删除角色
	 * @return
	 */
	public int deleteCmsRoleinfo(Long roleId);
	
//	/**
//	 * 统计角色数量
//	 * @return
//	 */
//	public int countRole();

	/**
	 * 查角色列表
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<CmsJgRoleinfo> getCmsRoleinfo(String roleName, Integer start, Integer pageSize, Integer isDel);
	
	
	/**
	 * 查角色列表
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<CmsJgRoleinfo> getCmsRoleinfos(String roleName, Integer start, Integer pageSize, Integer isDel);
//	/**
//	 * 修改角色信息
//	 * @param roleInfo
//	 * @return
//	 */
//	public int updateCmsRoleinfo(CmsRoleinfo roleInfo);
	
	/**
	 * 统计给定角色用户数量
	 * @param roleId
	 * @return
	 */
	public int countUserRoles(Long roleId);
	
	/**
	 * 根据角色id获取角色信息
	 * @param roleId
	 * @return
	 */
	public CmsJgRoleinfo getCmsRoleinfoById(Long roleId);
	
	/**
	 * 根据角色id获取目录id集合
	 * @param roleId
	 * @return
	 */
	public List<String> getMenuIds(Long roleId);
	
	/**
	 * 修改角色信息
	 * @param roleId
	 * @param roleName
	 * @param describe
	 * @return
	 */
	public int updateCmsRoleinfo(Long roleId, String roleName, String describe);
	
	/**
	 * 查询除了本身以外是否有相同的角色名称
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	public List<CmsJgRoleinfo> queryRole(Long roleId, String roleName);

	/**
	 * 修改用户返回不同类型的角色
	 * @param isDel
	 * @return
	 */
	public List<CmsJgRoleinfo> queryRoleByid(Integer isDel);

//	/**
//	 * 查询给定用户id的角色id集合
//	 * @param uid
//	 * @return
//	 */
//	public List<String> getRoleIds(Long uid);
//
//	/**
//	 * 删除给定用户的全部角色
//	 * @param userId
//	 * @return
//	 */
//	public int deleteUserRole(Long userId);
}