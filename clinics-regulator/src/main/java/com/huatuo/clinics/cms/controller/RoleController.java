package com.huatuo.clinics.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.CmsJgRoleinfoDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.comm.MessageUtil;
import com.huatuo.clinics.cms.comm.TreeUtil;
import com.huatuo.clinics.cms.comm.SessionUtils.SessionUtils;
import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;
import com.huatuo.clinics.cms.response.RoleInfoResponse;
import com.huatuo.clinics.cms.services.regulator.CmsJgRoleinfoService;
import com.huatuo.clinics.cms.services.regulator.CmsJgUserinfoService;
import com.huatuo.common.BaseResponse;
import com.huatuo.common.Utils;

@Controller
@RequestMapping("/role")
public class RoleController {

	
	@Resource
	private CmsJgUserinfoService cmsJgUserinfoService;
	
	@Resource
	private CmsJgRoleinfoService cmsJgRoleinfoService;
	
	/**
	 * 增加角色
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addrole", method = RequestMethod.GET)
	public String addRole(Model model){
		return "role/saveOrUpdateOneRole";
	}
	
	/**
	 * 角色维护
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryrole", method = RequestMethod.GET)
	public String deleterole(Model model){
		return "role/queryRole";
	}
	
	/**
	 * 增加权限中的权限查询
	 * @param parentId
	 * @param level
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listmenu", method = RequestMethod.GET)
	@ResponseBody
	public RoleInfoResponse getRoleMenu(
			@RequestParam(value="m_parentId", defaultValue = "0") Long parentId,
			@RequestParam(value="level") Integer level,
			HttpServletRequest request, Model model){
		RoleInfoResponse rep= new RoleInfoResponse();
		try {
			CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
			List<CmsJgMenuInfoDTO> list = cmsJgUserinfoService.queryMenu( parentId, person.getIsDel());
			List<CmsJgMenuInfoDTO> listChild = TreeUtil.getChildResourcess(list,
					String.valueOf(0));
//			model.addAttribute("roleList", listChild);
//			model.addAttribute("level", level);
			rep.setList(listChild);
			rep.setMessage(MessageUtil.SUCCESS);
			rep.setCode(MessageUtil.CODE_SUCCESS);
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			 rep.setCode(MessageUtil.CODE_FAILURE);
			 return rep;
		}
		return rep;
	}
	
	/**
	 * 角色保存
	 * @param roleName
	 * @param describe
	 * @param menuId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	public @ResponseBody BaseResponse addRole(
		   @RequestParam(value="roleName") String roleName,
		   @RequestParam(value="describe") String describe,
		   @RequestParam(value="menuId") String menuId,
		   HttpServletRequest request, Model model){
		BaseResponse rep=new BaseResponse();
		try {
			List<Long> list = Utils.getList(menuId);
			CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
			CmsJgRoleinfoDTO role = cmsJgRoleinfoService.addRole(roleName, describe,person.getIsDel());
			if(role == null){
				 rep.setCode(MessageUtil.CODE_ERROR);
				 rep.setMessage(MessageUtil.FAILURE);
				 return rep;
			}
			cmsJgUserinfoService.batchInsertRoleMenu(role.getId(), list);
			rep.setCode(MessageUtil.CODE_SUCCESS);
		} catch (Exception e) {
			 rep.setCode(MessageUtil.CODE_FAILURE);
			 return rep;
		}
		return rep;
	}

	
	/**
	 * 角色查询(进入后可进行删除,修改操作)
	 * @param pageNo
	 * @param pageSize
	 * @param roleName
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String queryRoleMenus(
		   @RequestParam(value="pageNo", defaultValue = "0") Integer pageNo,
		   @RequestParam(value="pageSize",defaultValue = "1000") Integer pageSize,
		   @RequestParam(value="roleName",required=false ) String roleName,
		   HttpServletRequest request, Model model){
		CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
		List<CmsJgRoleinfoDTO> list = cmsJgRoleinfoService.getRole(roleName, pageNo, pageSize,person.getIsDel());
		model.addAttribute("roleList", list);
		return "menu/queryRoleList";
	}
	
	/**
	 * 删除角色
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteonerole", method = RequestMethod.GET)
	public @ResponseBody BaseResponse delteUser(
		   @RequestParam(value="roleId") Long roleId, Model model){
		if(cmsJgRoleinfoService.countUserRoles(roleId) > 0){
			return BaseResponse.refailure();
		}
		cmsJgRoleinfoService.deleteRole(roleId);
		return BaseResponse.reSuccess();
		//return BaseResponse.refailure();
	}

	
	/**
	 * 修改角色
	 * @param roleId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateonerole", method = RequestMethod.POST)
	public String updateonerole(@RequestParam(value="roleId") Long roleId,
			HttpServletRequest request, Model model){
		CmsJgRoleinfoDTO rolebean = cmsJgRoleinfoService.getCmsRoleinfoBeanById(roleId);
		String menus = cmsJgRoleinfoService.getMenuIds(roleId);
		model.addAttribute("rolebean", rolebean);
		model.addAttribute("menus", menus);
		return "role/saveOrUpdateOneRole";
	}
	
	
	@RequestMapping(value = "/updatetosaveRole", method = RequestMethod.POST)
	public @ResponseBody BaseResponse updateRole(
			@RequestParam(value="roleId") Long roleId,
			@RequestParam(value="roleName") String roleName,
			@RequestParam(value="describe") String describe,
			@RequestParam(value="delMenus") String delMenus,
			@RequestParam(value="addMenus") String addMenus,
			HttpServletRequest request, Model model){
		BaseResponse rep=new BaseResponse();
		try {
			List<Long> dellist = Utils.getList(delMenus);
			List<Long> addlist = Utils.getList(addMenus);
			//查询除了本身以外是否有相同的角色名称
			List<CmsJgRoleinfoDTO> list=cmsJgRoleinfoService.queryRole(roleId,roleName);
			if(null != list){
				rep.setMessage(MessageUtil.USERNAME_EXISTS);
				rep.setCode(MessageUtil.CODE_ERROR);
				return rep;
			}
			cmsJgRoleinfoService.deleteRoleMenus(roleId, dellist);
			cmsJgRoleinfoService.addRoleMenus(roleId, addlist);
			if(cmsJgRoleinfoService.updateCmsRoleinfo(roleId, roleName, describe) <= 0){
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_ERROR);
				return rep;
			}
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
			return rep;
		}
		rep.setMessage(MessageUtil.SUCCESS);
		rep.setCode(MessageUtil.CODE_SUCCESS);
		return rep;
	}

}