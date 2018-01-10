package com.huatuo.clinics.cms.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.CmsJgDisputeDTO;
import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.bean.SsDicItemDTO;
import com.huatuo.clinics.cms.comm.SessionUtils.SessionUtils;
import com.huatuo.clinics.cms.response.AdressResponse;
import com.huatuo.clinics.cms.response.DisputeResponse;
import com.huatuo.clinics.cms.response.OrderTypeItemResponse;
import com.huatuo.clinics.cms.services.regulator.CmsJgDisputeService;
import com.huatuo.clinics.cms.services.regulator.CmsJgDistrictService;
import com.huatuo.clinics.cms.services.regulator.CmsJgOrgService;
import com.huatuo.clinics.cms.services.regulator.DictionaryService;
import com.huatuo.common.MessageUtil;
import com.huatuo.pms.bo.BOZdOrg;
import com.huatuo.pms.services.ZdOrgService;
/**
 * 医疗争议登记
 * @author 河南华佗在线
 *
 */
@Controller
@RequestMapping("/dispute")
public class ClinicsDisputeController {
	
	@Resource
	private CmsJgDistrictService cmsJgDistrictService;
	@Resource
	private CmsJgOrgService msJgOrgService;
	@Resource
	private CmsJgDisputeService disputeService;
	
	@Resource
	private DictionaryService dictionaryService;
	
	@Resource
	private ZdOrgService zdOrgService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClinicsDisputeController.class);
	
	
	/**
	 * 根据地址查询所有的诊所
	 * @param request
	 * @param roadCode 镇id
	 * @return
	 */
	@RequestMapping(value = "/queryOrgName", method = RequestMethod.GET)
	public @ResponseBody DisputeResponse queryOrgName(
			@RequestParam(value="roadCode",required=true)String roadCode,
			HttpServletRequest request){
		DisputeResponse rep=new DisputeResponse();
			try {
				List<BOZdOrg> list=zdOrgService.queryOrg(roadCode,true);
				rep.setOrgList(list);
			} catch (Exception e) {
				logger.error("ClinicsDisputeController queryOrgName......所有的诊所查询失败"+ e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
	
	
	
	/**
	 * 字典查询(分类鉴定，等级鉴定)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryDict", method = RequestMethod.GET)
	public @ResponseBody OrderTypeItemResponse queryDictionaries(
			HttpServletRequest request){
		OrderTypeItemResponse rep=new OrderTypeItemResponse();
			try {
				String code="00090";
				String code1="00091";
				//查询处方类型
				List<SsDicItemDTO>  list = dictionaryService.queryBillingCodes(code);
				List<SsDicItemDTO>  lists = dictionaryService.queryBillingCodes(code1);
				CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
				String userId=String.valueOf(userInfo.getId());
				String userName=userInfo.getUserName();
				rep.setUserId(userId);//用户id
				rep.setUserName(userName);//用户名称
				rep.setList(list);
				rep.setList2(lists);
				rep.setCode(MessageUtil.CODE_SUCCESS);
				rep.setMessage(MessageUtil.SUCCESS);
			} catch (Exception e) {
				logger.error("ClinicsDisputeController queryDictionaries......处方类型字典查询失败"+ e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
	
	/**
	 * 查询医疗争议登记
	 * @param key 关键字
	 * @param beginDate
	 * @param endDate
	 * @param countyCode 镇级id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryDisputes", method = RequestMethod.GET)
	public @ResponseBody DisputeResponse queryDisputes(
			@RequestParam(value="key",required=false)String key,
			@RequestParam(value="beginDate",required=true)String beginDate,
			@RequestParam(value="endDate",required=true)String endDate,
			@RequestParam(value="countyCode",required=true)String countyCode,
			HttpServletRequest request) {
		DisputeResponse rep = new DisputeResponse();
		try {
			
			//获得用户登录对象信息
			CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
			List<CmsJgDisputeDTO> list = disputeService.queryDisputes(key,beginDate,endDate,countyCode);
			rep.setUserId(String.valueOf(userInfo.getId()));//用户信息,用于判断是否允许修改
			rep.setList(list);
			rep.setMessage(MessageUtil.SUCCESS);
			rep.setCode(MessageUtil.CODE_SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsDisputeController queryDisputes"+ e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	/**
	 * 保存医疗争议登记
	 * @param disputeDTO
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDispute", method = RequestMethod.POST)
	public @ResponseBody DisputeResponse saveDispute(@RequestBody CmsJgDisputeDTO disputeDTO,
			HttpServletRequest request) {
		DisputeResponse rep = new DisputeResponse();
		//获得用户登录对象信息
		CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
		try {
			if (userInfo != null) {
				disputeDTO.setUserId(userInfo.getId()+"");
				disputeDTO.setBookedDate(new Date());
			}
			disputeDTO.setId(UUID.randomUUID().toString());
			//保存
			int i = disputeService.saveDispute(disputeDTO);
			if (i <= 0) {
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		} catch (Exception e) {
			logger.error("ClinicsDisputeController saveDispute"+ e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 修改医疗争议登记
	 * @param disputeDTO
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateDispute", method = RequestMethod.POST)
	public @ResponseBody DisputeResponse updateDispute(@RequestBody CmsJgDisputeDTO disputeDTO,
			HttpServletRequest request) {
		DisputeResponse rep = new DisputeResponse();
//		//获得用户登录对象信息
//		CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
		try {
			//曾加最新时间
			Date date=new Date();
			disputeDTO.setCreateDate(date);
			//保存
			int i = disputeService.updateDispute(disputeDTO);
			if (i <= 0) {
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		} catch (Exception e) {
			logger.error("ClinicsDisputeController updateDispute"+ e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	/**
	 * 用户机构地址查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAdress", method = RequestMethod.GET)
	public @ResponseBody AdressResponse queryAdress(HttpServletRequest request){
		AdressResponse rep=new AdressResponse();
			try {
				CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
				//根据当前用户查询机构地址
				CmsJgOrgDTO dto= msJgOrgService.queryOrgById(person.getCompany());
				//查询区下面所有的镇
				List<CmsJgDistrictDTO> list = cmsJgDistrictService.getDistrictByParent(dto.getCountyCode());
				rep.setList(list);
				rep.setDto(dto);
				rep.setCode(MessageUtil.CODE_SUCCESS);
				rep.setMessage(MessageUtil.SUCCESS);
			} catch (Exception e) {
				logger.error("ClinicsDisputeController queryAdress......用户机构地址查询失败"+ e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
}
