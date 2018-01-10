package com.huatuo.clinics.cms.services.regulator;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmPerReportDefendDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserExtendDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserPartsDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;
import com.huatuo.clinics.cms.util.Page;


/**
 *cms用户操作
 * @author ydj
 *
 */
public interface CmsJgUserinfoService {

	/**
	 * 新增cms用户
	 * @param name		用户名
	 * @param pwd		密码 
	 * @param companyId 管理公司id
	 * @return
	 */
	public CmsJgUserinfoDTO insertCmsUser(CmsJgUserinfoDTO dto);
	
	/**
	 * cms用户登陆
	 * @param name		用户名
	 * @param pwd		密码
	 * @return
	 */
	public CmsJgUserinfoDTO loginIn(String name, String pwd);
	
	/**
	 * 批量删除用户
	 * @param list		用户id集合
	 * @return
	 */
	public int deleteCmsUser(Long uId);
	
	/**
	 * 用户修改密码
	 * @param id		用户id
	 * @param pwd		新密码
	 * @return
	 */
//	public int updatePwd(Long id, String pwd);
	
	/**
	 * 分页查询用户信息列表
	 * limit m,n
	 * @param pageNo	开始数量
	 * @param pageSize	显示数量
	 * @param userName 
	 * @param userName	用户名
	 * @param integer 
	 * @param uId		用户ID
	 * @return
	 */
	public Page<CmsJgUserPartsDTO> getUsers(Integer pageNo, Integer pageSize, String orgId, String userName, Long isDel, Integer id);
	
	/**
	 * 获取指定用户给定父目录id的子目录
	 * @param useId		用户id
	 * @param parentId	父目录id
	 * @param isShow	是否展示
	 * @param integer 
	 * @return
	 */
	public List<CmsJgMenuInfoDTO> getMenus(Integer useId, Integer parantId, Boolean isShow);
	
	/**
	 * 获取给定用户所有的拦截地址
	 * @param userId	用户id
	 * @return
	 */
	public List<String> getAuthority(Integer userId);
	
//	/**
//	 *  获取指定目录拥有的增删改查权限
//	 * @param userId	
//	 * @param menuId
//	 * @return
//	 */
//	public String getOperations(Long userId, Long menuId);

	/**
	 * 批量给指定定角色id增加目录
	 * @param id		角色id
	 * @param list		目录id集合
	 */
	public void batchInsertRoleMenu(long id, List<Long> list);
	/**
	 * 增加权限中的权限查询
	 * @param parentId 
	 * @param isDel
	 * @return
	 */
	public List<CmsJgMenuInfoDTO> queryMenu(Long parentId, Integer isDel);
	
	/**
	 * 查询重复的用户名
	 * @param name
	 * @return
	 */
	public CmsJgUserinfoDTO selectRepeatUserName(String name);
	
	/**
	 * 新增用户扩展信息
	 * @param dtoex
	 * @return
	 */
	public void insertUserExtend(CmsJgUserExtendDTO dtoex);

	public int addUserAll(String userName, String password, String companyId,
			String userNames, String mobile, String userType, Integer roleId, Integer id, Integer integer);
	
	/**
	 * 根据id查询用户信息
	 * @param isDel 
	 * @param valueOf
	 * @return
	 */
	public CmsJgUserPartsDTO getUserParts(Long uid, Integer isDel);
	
	/**
	 * 修改用户扩展信息
	 * @param isDel 
	 * @param dtoex
	 * @return
	 */
	public int updateUserAll(String flagId, String userName, String password,
			String companyId, String userNames, String mobile, String userType,
			Integer roleId, Integer id, Integer isDel);
	
	/**
	 * 修改用户扩展信息
	 * @param dtoex
	 * @return
	 */
	public int updateUserEx(CmsJgUserExtendDTO dtoex);
	
	/**
	 * 判断除了当前用户其他用户是否同名
	 * @param userName
	 * @param id
	 * @return
	 */
	public List<CmsJgUserinfoDTO> selectRepeatUser(String userName, Integer id);
	
	/**
	 * 密码修改
	 * @param password
	 * @param id
	 * @return
	 */
	public int updatePwd(String password, Integer id);
	
	/**
	 * 记录用户退出的时间
	 * @param userInfo
	 */
	public int updateUserDate(CmsJgUserinfoDTO userInfo);
	
	/**
	 * 查询默认模板
	 * @param request
	 * @param model
	 * @return
	 */
	public List<CmPerReportDefendDTO> getReportAll();
	
	/**
	 * 删除模板
	 * @param id
	 * @return
	 */
	public int deleteReport(String id);
	
	/**
	 * 保存模板
	 * @param dto
	 * @return
	 */
	public int saveReport(CmPerReportDefendDTO dto);
}