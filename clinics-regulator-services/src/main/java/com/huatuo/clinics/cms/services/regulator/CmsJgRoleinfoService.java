package com.huatuo.clinics.cms.services.regulator;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgRoleinfoDTO;

/**
 *cms角色操作
 * @author ys
 *
 */
public interface CmsJgRoleinfoService {

	/**
	 * 删除角色
	 * @param roleId	角色id
	 * @return
	 */
	public int deleteRole(Long roleId);
	
	/**
	 * 根据id取得role信息
	 * @param roleId	角色id
	 * @return
	 */
	public CmsJgRoleinfoDTO getCmsRoleinfoBeanById(Long roleId);
	
	/**
	 * 获取角色列表
	 * limit start, pageSize
	 * @param start		开始
	 * @param pageSize	数量
	 * @param integer 
	 * @return
	 */
	public List<CmsJgRoleinfoDTO> getRoles(String roleName, Integer start, Integer pageSize, Integer integer);

	/**
	 * 给某一个用户批量增加多个角色
	 * @param userId	用户id
	 * @param roleId	角色id集合
	 */
	public int addUserRoles(Long userId, Integer roleId);
	
	/**
	 * 给一个角色id删除对应的目录id
	 * @param roleId	角色id
	 * @param menuId	目录id集合
	 */
	public void deleteRoleMenus(Long roleId, List<Long> menuId);
	
	/**
	 * 给一个角色id增加对应的目录id
	 * @param roleId	角色id
	 * @param menuId	目录id集合
	 */
	public void addRoleMenus(Long roleId, List<Long> menuId);
	
	/**
	 * 通过名字和描述增加角色信息
	 * @param name		角色名
	 * @param describe	角色描述
	 * @param isDel 
	 * @return
	 */
	public CmsJgRoleinfoDTO addRole(String name, String describe, Integer isDel);
	
	/**
	 * 统计一个角色使用的用户数量
	 * @param roleId	角色id
	 * @return
	 */
	public int countUserRoles(Long roleId);

	/**
	 * 获取给定角色id可以访问的目录 结果返回目录id以','分割的字符串
	 * @param roleId	角色id
	 * @return
	 */
	public String getMenuIds(Long roleId);
	
	/**
	 * 修改角色
	 * @param roleId	角色id
	 * @param roleName	角色名
	 * @param describe	角色描述
	 * @return
	 */
	public int updateCmsRoleinfo(Long roleId, String roleName, String describe);

	public List<CmsJgRoleinfoDTO> getRole(String roleName, Integer pageNo,
			Integer pageSize, Integer isDel);
	
	/**
	 * 查询除了本身以外是否有相同的角色名称
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	public List<CmsJgRoleinfoDTO> queryRole(Long roleId, String roleName);
	
	/**
	 * 修改用户返回不同类型的角色
	 * @param isDel
	 * @return
	 */
	public List<CmsJgRoleinfoDTO> queryRoleByid(Integer isDel);

//	/**
//	 * 获取给定用户id具备的角色id集合
//	 * @param uid
//	 * @return
//	 */
//	public List<String> getRoleIds(Long uid);
//	
//	/**
//	 * 根据用户id删除其对应的角色
//	 * @param userId
//	 * @return
//	 */
//	public int deleteUserRole(Long userId);
	
}