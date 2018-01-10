package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.CmPerReportDefend;
import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgUserParts;
import com.huatuo.clinics.cms.db.bean.CmsJgUserRole;
import com.huatuo.clinics.cms.db.bean.CmsJgUserextend;
import com.huatuo.clinics.cms.db.bean.CmsJgUserinfo;

/**
 * cms用户信息操作
 * @author ys
 *
 */
public interface DbCmsJgUserinfoRepository {
	/**
	 * 新增用户
	 * @param userinfo
	 * @return
	 */
//	public int addCmsUserinfo(CmsUserinfo userinfo);

	/**
	 * 通过用户名和密码获取用户信息
	 * @param name		用户名
	 * @param pwd		密码(如果密码为null，则查询指定用户名的用户)
	 * @return
	 */
	public CmsJgUserinfo getUser(String name, String pwd);
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public int deleteCmsUser(Long uId);
//	
//	/**
//	 * 根据id获取用户信息
//	 * @param id
//	 * @return
//	 */
//	public CmsUserinfo getUserById(Long id);
//	
//	/**
//	 * 根据指定用户id，修改其密码
//	 * @param id
//	 * @param pwd
//	 * @return
//	 */
//	public int updatePwd(Long id, String pwd);

	/**
	 * 统计用户数量(删除标志位0)
	 * @param userName 
	 * @param id 
	 * @return
	 */
	public int countUser(String orgId, Long isDel, String userName, Integer id);

	/**
	 * 查询用户列表
	 * @param start
	 * @param pageSize
	 * @param userName 
	 * @param id 
	 * @return
	 */
	public List<CmsJgUserParts> getCmsUserinfo(Integer start,Integer pageSize,String orgId,String userName, Long isDel, Integer id);
	
	/**
	 * 查询目录列表
	 * @param userId
	 * @param parentId
	 * @param isShow
	 * @return
	 */
	public List<CmsJgMenuInfoDTO> selectMenuList(Integer userId, Integer parentId, Boolean isShow);

	/**
	 * 查询指定用户拦截的设置
	 * @param userId
	 * @return
	 */
	public List<String> getAuthority(Integer userId);
	
//	/**
//	 * 查询指定用户具有的角色信息
//	 * @param userId
//	 * @return
//	 */
//	public List<CmsRoleinfo> getUesrRole(Long userId);
//	
//	/**
//	 * 统计指定用户具有的角色数量
//	 * @param userId
//	 * @return
//	 */
//	public int countUesrRole(Long userId);
//
//	//public List<String> getOperations(Long userId, Long menuId);
	
	/**
	 * 增加用户角色
	 * @param userId
	 * @param list
	 */
	public int addUserRoles(Long userId, Integer roleId);

	/**
	 * 删除角色目录
	 * @param roleId
	 * @param list
	 */
	public void deleteRoleMenus(Long roleId, List<Long> list);
	
	/**
	 * 增加角色目录
	 * @param roleId
	 * @param list
	 */
	public void addRoleMenus(Long roleId, List<Long> list);
	
	/**
	 * 增加权限中的权限查询
	 * @param parentId 
	 * @param roleId
	 * @param list
	 */
	public List<CmsJgMenuInfoDTO> selectMenu(Long parentId, Integer isDel);
	
	/**
	 * 查询重复账号
	 * @param name
	 * @return
	 */
	public CmsJgUserinfo getUser(String name);
	
	/**
	 * 新增用户
	 * @param userbean
	 */
	public void insertCmsUser(CmsJgUserinfo userbean);
	
	/**
	 * 新增用户扩展信息
	 * @param teget
	 * @return
	 */
	public void insertUserExtend(CmsJgUserextend teget);
	
	/**
	 * 根据用户id查询用户信息
	 * @param uid
	 * @param isDel 
	 * @return
	 */
	public CmsJgUserParts getCmsUserinfo(Long uid, Integer isDel);
	
	/**
	 * 修改user信息
	 * @param user
	 * @return
	 */
	public int updateUser(CmsJgUserinfo user);
	
	/**
	 * 修改用户扩展信息
	 * @param dto
	 * @return
	 */
	public int updateUserEx(CmsJgUserextend dto);
	
	/**
	 * 修改权限
	 * @param role
	 * @return
	 */
	public int updateRole(CmsJgUserRole role);
	
	/**
	 * 判断除了当前用户其他用户是否同名
	 * @param userName
	 * @param id
	 * @return
	 */
	public List<CmsJgUserinfo> selectRepeatUser(String userName, Integer id);
	
	/**
	 * 密码修改
	 * @param password
	 * @param id
	 * @return
	 */
	public int updatePwd(String password, Integer id);
	
	/**
	 * 记录用户退出的时间
	 * @param exchangeObject
	 * @return
	 */
	public int updateUserDate(CmsJgUserinfo exchangeObject);
	
	/**
	 * 查询默认模板
	 * @param request
	 * @param model
	 * @return
	 */
	public List<CmPerReportDefend> getReportAll();
	
	/**
	 * return 
	 * @param id
	 * @return
	 */
	public int deleteReport(String id);
	
	/**
	 * 保存模板
	 * @param inDto
	 * @return
	 */
	public int saveReport(CmPerReportDefend inDto);

//	/**
//	 * 修改一条公司信息
//	 * @param userid
//	 * @param companyId
//	 */
//	public void updateCompany(Long userid, Long companyId);
	
}