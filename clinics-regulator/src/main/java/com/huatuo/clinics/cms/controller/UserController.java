package com.huatuo.clinics.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.clinics.cms.bean.CmsJgRoleinfoDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserPartsDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.comm.MD5Util;
import com.huatuo.clinics.cms.comm.SessionUtils.SessionUtils;
import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;
import com.huatuo.clinics.cms.response.CmsJgOrgResponse;
import com.huatuo.clinics.cms.response.RoleInfoDtoResponse;
import com.huatuo.clinics.cms.services.regulator.CmsJgOrgService;
import com.huatuo.clinics.cms.services.regulator.CmsJgRoleinfoService;
import com.huatuo.clinics.cms.services.regulator.CmsJgUserinfoService;
import com.huatuo.clinics.cms.util.Page;
import com.huatuo.common.BaseResponse;
import com.huatuo.common.MessageUtil;

@Controller
@RequestMapping("/user")
public class UserController {

//	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
	@Resource
	private CmsJgUserinfoService userinfoService;
	
	@Resource
	private CmsJgRoleinfoService cmsJgRoleinfoService;
	
	@Resource
	private CmsJgOrgService cmsJgOrgService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody BaseResponse login(@RequestParam("name") String name, 
		   @RequestParam("pwd") String pwd,
		   HttpServletRequest request, HttpServletResponse response,Model model) {
		BaseResponse resp = new BaseResponse();
		CmsJgUserinfoDTO userinfoBean = userinfoService.loginIn(name, pwd);
		if(userinfoBean==null){
			resp.setCode(MessageUtil.CODE_FAILURE);
			resp.setMessage(MessageUtil.ERROR_LOG);
			return resp;
		}
		userinfoBean.setAuthorityList(userinfoService.getAuthority(userinfoBean.getId()));
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userinfoBean.getUserName(), userinfoBean);
		securityContext.setAuthentication(authentication);
		return resp;
	}
	
	/**
	 * 登出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login/out", method = RequestMethod.GET)
	public @ResponseBody BaseResponse Loginout(HttpServletRequest request) {
		BaseResponse resp = new BaseResponse();
		CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
		if (userInfo == null) {
			return resp;
		}
		//记录用户退出的时间
		Date date=new Date();
		userInfo.setEndDate(date);
		userinfoService.updateUserDate(userInfo);
		SessionUtils.cleanSession(request);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(null);
		return resp;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String login(Model model){
		return "login";
	}
	
	/**
	 * 测试使用
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model){
		// userinfoService.insertCmsUser("ys2", "123456");
		/*Page<DrugListBean> page = stdDrugService.getDrugListBeanList("中", 0, 1, 50);
		System.out.println(page.getList().size());*/
		
//		Page<DoctorDetailBean> page = doctorService.pageDoctor(null, 0, 0, 1, 50, true, "0");
//		System.out.println(page.getList().size());
		return "test";
	}
	
	/**
	 * 增加用户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/urladd", method = RequestMethod.GET)
	public String goUrl(Model model){
		return "person/addUser";
	}

	
	/**
	 * 用户维护
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/urlselete", method = RequestMethod.GET)
	public String delteUsers(Model model){
		return "person/queryUser";
	}
	
	/**
	 * 个人信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET)
	public String personInfo(
		   HttpServletRequest request,Model model){
		CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
		CmsJgUserPartsDTO rolebean = userinfoService.getUserParts(Long.valueOf(person.getId()),person.getIsDel());
		model.addAttribute("rolebean", rolebean);
		return "person/personInfo";
	}
	
	/**
	 * 修改密码页面返回
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public String updatePassword(
		   HttpServletRequest request,Model model){
		return "person/password";
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String queryUser(
		   @RequestParam(value="pageNo", defaultValue = "0") Integer pageNo,
		   @RequestParam(value="pageSize",defaultValue = "1000") Integer pageSize,
		   @RequestParam(value="orgId",required=false ) String orgId,
		   @RequestParam(value="userName",required=false ) String userName,
		   HttpServletRequest request, Model model){
		CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
		Page<CmsJgUserPartsDTO> page=null;
		if(null != person){
			page = userinfoService.getUsers(pageNo, pageSize, orgId, userName,Long.valueOf(person.getIsDel()),person.getId());
			model.addAttribute("personList", page.getList());
		}
		return "menu/userList";
	}
	
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public @ResponseBody BaseResponse delteUser(
			 @RequestParam(value="uId") Long uId, Model model){
		BaseResponse rep= new BaseResponse();
		try {
			int i=userinfoService.deleteCmsUser(uId);
			if(i <= 0){
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_ERROR);
				return rep;
			}
			rep.setMessage(MessageUtil.SUCCESS_DEL);
			rep.setCode(MessageUtil.CODE_SUCCESS);
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
			return rep;
		}
		return rep;
	}
	
	@RequestMapping(value="/menu/list", method = RequestMethod.GET)
	public String QueryMenus(@RequestParam(value="m_id") Integer mId,
			@RequestParam(value="level") Integer level,
			HttpServletRequest request, Model model){
		CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
		List<CmsJgMenuInfoDTO> mlist = userinfoService.getMenus(person.getId(), mId, true);
		model.addAttribute("mlist", mlist);
		model.addAttribute("level", level);
		return "menu/menuList";
	}
	
	@RequestMapping(value="/optionList", method = RequestMethod.GET)
	public @ResponseBody RoleInfoDtoResponse queryOptionList(
		   @RequestParam(value="pageNo", defaultValue = "0") Integer pageNo,
		   @RequestParam(value="pageSize",defaultValue = "1000") Integer pageSize,
		   HttpServletRequest request,
		   Model model){
		RoleInfoDtoResponse rep=new RoleInfoDtoResponse();
		try {
			CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
			List<CmsJgRoleinfoDTO> roleList = cmsJgRoleinfoService.getRoles(null, pageNo, pageSize,person.getIsDel());
			rep.setList(roleList);
			rep.setMessage(MessageUtil.SUCCESS);
			rep.setCode(MessageUtil.CODE_SUCCESS);
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_ERROR);
			return rep;
		}
		return rep;
	}
	
	
	@RequestMapping(value="/queryRoleByid", method = RequestMethod.GET)
	public @ResponseBody RoleInfoDtoResponse queryRoleByid(
		   HttpServletRequest request,
		   Model model){
		RoleInfoDtoResponse rep=new RoleInfoDtoResponse();
		try {
			CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
			List<CmsJgRoleinfoDTO> roleList = cmsJgRoleinfoService.queryRoleByid(person.getIsDel());
			rep.setList(roleList);
			rep.setMessage(MessageUtil.SUCCESS);
			rep.setCode(MessageUtil.CODE_SUCCESS);
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_ERROR);
			return rep;
		}
		return rep;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody BaseResponse addUser(
			@RequestParam(value="flagId") String flagId,
		   @RequestParam(value="userName") String userName,
		   @RequestParam(value="password", required=false) String password,
		   @RequestParam(value="companyId") String companyId,
		   @RequestParam(value="userNames") String userNames,
		   @RequestParam(value="mobile") String mobile,
		   @RequestParam(value="userType", required=false) String userType,
		   @RequestParam(value="roleId", required=false)Integer roleId,
		   HttpServletRequest request, Model model){
		BaseResponse rep= new BaseResponse();
		try {
			CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
			
			//新增
			 int i=0;
			if(flagId == ""){
				//判断用户是否存在
				CmsJgUserinfoDTO user=userinfoService.selectRepeatUserName(userName);
				if(user != null){
					rep.setMessage(MessageUtil.USERNAME_EXISTS);
					rep.setCode(MessageUtil.CODE_ERROR);
					return rep;
				}
			    i=userinfoService.addUserAll(userName,password,companyId,userNames,mobile,userType,roleId,person.getId(),person.getIsDel());
			}else{
				//判断除了当前用户其他用户是否同名
					List<CmsJgUserinfoDTO> list=userinfoService.selectRepeatUser(userName,Integer.valueOf(flagId));
					if(null != list){
						rep.setMessage(MessageUtil.USERNAME_EXISTS);
						rep.setCode(MessageUtil.CODE_ERROR);
						return rep;
					}
			//修改	
				i=userinfoService.updateUserAll(flagId,userName,password,companyId,userNames,mobile,userType,roleId,person.getId(),person.getIsDel());
			}
			if(i>0){
				rep.setMessage(MessageUtil.SUCCESS);
				rep.setCode(MessageUtil.CODE_SUCCESS);
			}
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
			return rep;
		}
		return rep;
	}
	
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String updateuser(
		   @RequestParam(value="uid") Long uid,
		   @RequestParam(value="username") String username,
		   HttpServletRequest request,Model model){
		CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
		model.addAttribute("uid", uid);
		model.addAttribute("username", username);
		CmsJgUserPartsDTO rolebean = userinfoService.getUserParts(Long.valueOf(uid),person.getIsDel());
		model.addAttribute("rolebean", rolebean);
		return "person/addUser";
	}
	
	/**
	 * 获取所有机构
	 */
	@RequestMapping(value="findAllManageCompany")
	public@ResponseBody CmsJgOrgResponse findAllManageCompany(Model model,HttpServletRequest request){
		CmsJgOrgResponse rep=new CmsJgOrgResponse();
		try {
			List<CmsJgOrgDTO> orgList=cmsJgOrgService.queryOrg();
			rep.setList(orgList);
			rep.setMessage(MessageUtil.SUCCESS);
			rep.setCode(MessageUtil.CODE_SUCCESS);
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_ERROR);
			return rep;
		}
		 return rep;
	}
	
	/**
	 * 密码修改
	 * @param uid
	 * @param model
	 * @param request 
	 * @return
	 */
	@RequestMapping(value = "/passwordUpdate", method = RequestMethod.GET)
	public @ResponseBody BaseResponse passwordUpdate(
			@RequestParam(value="password") String password,
		   @RequestParam(value="newPassword") String newPassword,
		   HttpServletRequest request,Model model){
		BaseResponse rep=new BaseResponse();
		CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
		try {
			if(null != person){
				String pwd=person.getPwd();
				if(!pwd.equals(MD5Util.MD5(password)) ){
					rep.setMessage(MessageUtil.OLDPASSWORDERROR);
					rep.setCode(MessageUtil.CODE_ERROR);
					return rep;
				}
				 int i=userinfoService.updatePwd(MD5Util.MD5(newPassword),person.getId());
				 if(i > 0){
						rep.setMessage(MessageUtil.SUCCESS);
						rep.setCode(MessageUtil.CODE_SUCCESS); 
				 }
			}
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
			return rep;
		}
		return rep;
	}
	/**
	 * 关闭或刷新记录用户退出时间
	 */
	@RequestMapping(value="/updateUserDate",method = RequestMethod.GET)
	public@ResponseBody CmsJgOrgResponse updateUserDate(Model model,HttpServletRequest request){
		CmsJgOrgResponse rep=new CmsJgOrgResponse();
		try {
			CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
			if (userInfo == null) {
				return rep;
			}
			//记录用户退出的时间
			Date date=new Date();
			userInfo.setEndDate(date);
			int i=userinfoService.updateUserDate(userInfo);
			if(i>0){
				rep.setMessage(MessageUtil.SUCCESS);
				rep.setCode(MessageUtil.CODE_SUCCESS); 
			}
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_ERROR);
			return rep;
		}
		 return rep;
	}
	
}
