package com.huatuo.clinics.cms.services.regulator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.CmPerReportDefendDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserExtendDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserPartsDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserRoleDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.db.bean.CmPerReportDefend;
import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgUserParts;
import com.huatuo.clinics.cms.db.bean.CmsJgUserRole;
import com.huatuo.clinics.cms.db.bean.CmsJgUserextend;
import com.huatuo.clinics.cms.db.bean.CmsJgUserinfo;
import com.huatuo.clinics.cms.db.repository.DbCmsJgUserinfoRepository;
import com.huatuo.clinics.cms.util.Page;
import com.huatuo.common.MD5Util;
import com.huatuo.common.Utils;

@Service
public class CmsJgUserinfoServiceImpl implements CmsJgUserinfoService {

	@Autowired
	private DbCmsJgUserinfoRepository CmsUserinfoRepository;
	
	@Autowired
	private CmsJgRoleinfoService cmsJgRoleinfoService;
	
	@Override
	public CmsJgUserinfoDTO insertCmsUser(CmsJgUserinfoDTO dto) {
		CmsJgUserinfo userbean = Utils.exchangeObject(dto, CmsJgUserinfo.class);
		CmsUserinfoRepository.insertCmsUser(userbean);
		return this.selectRepeatUserName(dto.getUserName());
	}

	@Override
	public CmsJgUserinfoDTO loginIn(String name, String pwd) {
		if(name == null || name.length() == 0 || pwd == null || pwd.length() == 0){
			return null;
		}
		CmsJgUserinfo userInfo = CmsUserinfoRepository.getUser(name,pwd);
		return Utils.exchangeObject(userInfo, CmsJgUserinfoDTO.class);
	}

	@Override
	public int deleteCmsUser(Long uId) {
			return CmsUserinfoRepository.deleteCmsUser(uId);
	}

//	@Override
//	public int updatePwd(Long id, String pwd) {
//		if(id != null && getCmsUserinfoBean(id) != null && pwd !=null){
//			pwd = MD5Util.MD5(pwd);
//			return CmsUserinfoRepository.updatePwd(id, pwd);
//		}
//		return 0;
//	}

//	private CmsUserinfoBean getCmsUserinfoBean(Long id){
//		return Utils.exchangeObject(CmsUserinfoRepository.getUserById(id), CmsUserinfoBean.class);
//	}

	@Override
	public Page<CmsJgUserPartsDTO> getUsers(Integer pageNo, Integer pageSize,String orgId, String userName, Long isDel,Integer id) {
		int total = CmsUserinfoRepository.countUser(orgId, isDel,userName,id);
		Page<CmsJgUserPartsDTO> pageBack = new Page<CmsJgUserPartsDTO>(total, pageNo, pageSize);
		List<CmsJgUserPartsDTO> answerBeanList = new ArrayList<CmsJgUserPartsDTO>();
		if(total > 0){
			Page<CmsJgUserParts> page = new Page<CmsJgUserParts>(total, pageNo, pageSize);
			List<CmsJgUserParts> userinfoList= CmsUserinfoRepository.getCmsUserinfo(page.getStart(), pageSize, orgId,userName, isDel,id);
			for(CmsJgUserParts cmsUserinfo : userinfoList){
				CmsJgUserPartsDTO cmsUserinfoBean = Utils.exchangeObject(cmsUserinfo, CmsJgUserPartsDTO.class);
				answerBeanList.add(cmsUserinfoBean);
			}
		}
		pageBack.setList(answerBeanList);
		return pageBack;
	}

	@Override
	public List<CmsJgMenuInfoDTO> getMenus(Integer userId, Integer parentId, Boolean isShow) {
		List<CmsJgMenuInfoDTO> listMenuInfoBean = new ArrayList<CmsJgMenuInfoDTO>(); 
		List<CmsJgMenuInfoDTO> list = CmsUserinfoRepository.selectMenuList(userId, parentId,isShow);
		if(list != null && list.size() > 0){
			for(CmsJgMenuInfoDTO menu : list){
				CmsJgMenuInfoDTO teget = Utils.exchangeObject(menu, CmsJgMenuInfoDTO.class);
				listMenuInfoBean.add(teget);
			}
		}
		return listMenuInfoBean;
	}

	@Override
	public List<String> getAuthority(Integer userId) {
		return CmsUserinfoRepository.getAuthority(userId);
	}

//	@Override
//	public String getOperations(Long userId, Long menuId) {
//		List<String> list = CmsUserinfoRepository.getOperations(userId, menuId);
//		if(list != null && list.size() > 0){
//			Set<String> set = new HashSet<String>();
//			tocheck : for(String s : list){
//				if(s != null && !"".equals(s.trim())){
//					String[] array = s.split(",");
//					for(String out : array){
//						set.add(out);
//					}
//					if(set.size() == 4){
//						break tocheck;
//					}
//				}
//			}
//			return Arrays.toString(set.toArray());
//		}
//		 return null;
//	}

	@Override
	public void batchInsertRoleMenu(long roleId, List<Long> list) {
		CmsUserinfoRepository.addRoleMenus(roleId, list);
	}

	@Override
	public List<CmsJgMenuInfoDTO> queryMenu(Long parentId, Integer isDel) {
		List<CmsJgMenuInfoDTO> listMenuInfoBean = new ArrayList<CmsJgMenuInfoDTO>(); 
		List<CmsJgMenuInfoDTO> list = CmsUserinfoRepository.selectMenu(parentId,isDel);
		if(list != null && list.size() > 0){
			for(CmsJgMenuInfoDTO menu : list){
				CmsJgMenuInfoDTO teget = Utils.exchangeObject(menu, CmsJgMenuInfoDTO.class);
				listMenuInfoBean.add(teget);
			}
		}
		return listMenuInfoBean;
	}

//	@Override
//	public void updateCompany(Long userid, Long CompanyId) {
//		CmsUserinfoRepository.updateCompany(userid, CompanyId);
//	}

	@Override
	public CmsJgUserinfoDTO selectRepeatUserName(String name) {
		CmsJgUserinfo user= CmsUserinfoRepository.getUser(name);
		if(null != user){
			CmsJgUserinfoDTO teget = Utils.exchangeObject(user, CmsJgUserinfoDTO.class);
			return teget;
		}
		return null;
	}

	@Override
	public void insertUserExtend(CmsJgUserExtendDTO dtoex) {
		CmsJgUserextend teget = Utils.exchangeObject(dtoex, CmsJgUserextend.class);
		 CmsUserinfoRepository.insertUserExtend(teget);
	}

	@Override
	public int addUserAll(String userName, String password, String companyId,
			String userNames, String mobile, String userType, Integer roleId, Integer id, Integer isDel) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String strDate=sdf.format(date); 
		//组装user DTO
		CmsJgUserinfoDTO dto= new CmsJgUserinfoDTO();
		if(isDel==0){
			dto.setIsDel(1);
		}else{
			dto.setIsDel(2);
		}
		dto.setUserName(userName);
		dto.setPwd(MD5Util.MD5(password));
		dto.setCid(id);
		dto.setCompany(companyId);
		dto.setCreateTime(strDate);
		//新增用户返回主键
		CmsJgUserinfoDTO userid=this.insertCmsUser(dto);
		//组装user扩展信息DTO
		CmsJgUserExtendDTO dtoex=new CmsJgUserExtendDTO();
		dtoex.setId(UUID.randomUUID().toString());
		dtoex.setData(date);
		dtoex.setUserId(String.valueOf(userid.getId()));
		dtoex.setUserName(userNames);
		dtoex.setMobile(mobile);
		dtoex.setUserType(userType);
		dtoex.setOrg(companyId);
		dtoex.setIsDel(1);
		//新增用户扩展信息
		 this.insertUserExtend(dtoex);
		//用户关联角色
		int i =cmsJgRoleinfoService.addUserRoles(Long.valueOf(userid.getId()), roleId);
		if(i > 0){
			return i;
		}
		return 0;
	}

	@Override
	public CmsJgUserPartsDTO getUserParts(Long uid, Integer isDel) {
		CmsJgUserParts user= CmsUserinfoRepository.getCmsUserinfo(uid,isDel);
		CmsJgUserPartsDTO cmsUserinfoBean = Utils.exchangeObject(user, CmsJgUserPartsDTO.class);
		if(null != cmsUserinfoBean){
			return cmsUserinfoBean;
		}
		return null;
	}

	@Override
	public int updateUserAll(String flagId, String userName, String password,
			String companyId, String userNames, String mobile, String userType,
			Integer roleId, Integer id, Integer isDel) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String strDate=sdf.format(date); 
		//组装user DTO
		CmsJgUserinfoDTO dto= new CmsJgUserinfoDTO();
		dto.setUserName(userName);
		dto.setCid(id);
		dto.setCompany(companyId);
		dto.setCreateTime(strDate);
		dto.setId(Integer.valueOf(flagId));
		//修改user信息
		CmsJgUserinfo user = Utils.exchangeObject(dto, CmsJgUserinfo.class);
		CmsUserinfoRepository.updateUser(user);
		//组装user扩展信息DTO
		CmsJgUserExtendDTO dtoex=new CmsJgUserExtendDTO();
		dtoex.setUserId(String.valueOf(user.getId()));
		dtoex.setData(date);
		dtoex.setUserName(userNames);
		dtoex.setMobile(mobile);
		dtoex.setUserType(userType);
		dtoex.setOrg(companyId);
		//修改用户扩展信息
		 this.updateUserEx(dtoex);
		 //修改用户的权限
		 CmsJgUserRoleDTO  role=new CmsJgUserRoleDTO();
		 role.setUserId(Integer.valueOf(flagId));
		 role.setRoleId(roleId);
		 CmsJgUserRole roledb = Utils.exchangeObject(role, CmsJgUserRole.class);
		 int i=CmsUserinfoRepository.updateRole(roledb);
		if(i > 0){
			return i;
		}
		return 0;
	}
	
	@Override
	public int updateUserEx(CmsJgUserExtendDTO dtoex) {
		CmsJgUserextend dto = Utils.exchangeObject(dtoex, CmsJgUserextend.class);
		return CmsUserinfoRepository.updateUserEx(dto);
	}

	@Override
	public List<CmsJgUserinfoDTO> selectRepeatUser(String userName, Integer id) {
		List<CmsJgUserinfoDTO> list=new ArrayList<CmsJgUserinfoDTO>();
		CmsJgUserinfoDTO resultDto=new CmsJgUserinfoDTO();
		List<CmsJgUserinfo> dto=CmsUserinfoRepository.selectRepeatUser(userName,id);
		for (CmsJgUserinfo cmsJgUserinfo : dto) {
			resultDto = Utils.exchangeObject(cmsJgUserinfo, CmsJgUserinfoDTO.class);
			list.add(resultDto);
		}
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}

	@Override
	public int updatePwd(String password, Integer id) {
		return CmsUserinfoRepository.updatePwd(password,id);
	}

	@Override
	public int updateUserDate(CmsJgUserinfoDTO userInfo) {
		return CmsUserinfoRepository.updateUserDate(Utils.exchangeObject(userInfo, CmsJgUserinfo.class));
		
	}

	@Override
	public List<CmPerReportDefendDTO> getReportAll() {
		List<CmPerReportDefendDTO> list=new ArrayList<CmPerReportDefendDTO>();
		List<CmPerReportDefend> dto=CmsUserinfoRepository.getReportAll();
		for (CmPerReportDefend cmPerReportDefend : dto) {
			CmPerReportDefendDTO result=Utils.exchangeObject(cmPerReportDefend, CmPerReportDefendDTO.class);
			list.add(result);
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public int deleteReport(String id) {
		return  CmsUserinfoRepository.deleteReport(id);
		
	}

	@Override
	public int saveReport(CmPerReportDefendDTO dto) {
		CmPerReportDefend inDto=Utils.exchangeObject(dto, CmPerReportDefend.class);
		inDto.setValidFlg("1");
		return CmsUserinfoRepository.saveReport(inDto);
	
	}

//	@Override
//	public CmsUserinfo getUserById(Long id) {
//		return CmsUserinfoRepository.getUserById(id);
//	}
	
}