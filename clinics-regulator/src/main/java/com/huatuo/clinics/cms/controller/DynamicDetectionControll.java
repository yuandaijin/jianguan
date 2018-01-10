package com.huatuo.clinics.cms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.CmsJgDisputeDTO;
import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.clinics.cms.bean.CmsJgPatrolDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.bean.CmsOrgInfoDTO;
import com.huatuo.clinics.cms.bean.CsOutpEmrDTO;
import com.huatuo.clinics.cms.bean.CsOutpOrderDTO;
import com.huatuo.clinics.cms.bean.CsOutpOrderDtlDTO;
import com.huatuo.clinics.cms.bean.CsOutpVisitDTO;
import com.huatuo.clinics.cms.bean.DynamicDetectionDTO;
import com.huatuo.clinics.cms.bean.MonitoringEventsDTO;
import com.huatuo.clinics.cms.bean.OpmDicDepartmentDTO;
import com.huatuo.clinics.cms.bean.RsEmpiPatientInfoDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.comm.SessionUtils.SessionUtils;
import com.huatuo.clinics.cms.db.bean.CmsJgMonitor;
import com.huatuo.clinics.cms.db.bean.CsOutpVisit;
import com.huatuo.clinics.cms.db.bean.CsOutpVisitDx;
import com.huatuo.clinics.cms.response.AdressResponse;
import com.huatuo.clinics.cms.response.CmsClinicsResponse;
import com.huatuo.clinics.cms.response.CmsOrgResponse;
import com.huatuo.clinics.cms.response.DisputeResponse;
import com.huatuo.clinics.cms.response.DynamicDetectionResponse;
import com.huatuo.clinics.cms.response.MonitoringEventsResponse;
import com.huatuo.clinics.cms.response.OrderCheckResponse;
import com.huatuo.clinics.cms.services.regulator.CmsJgDisputeService;
import com.huatuo.clinics.cms.services.regulator.CmsJgDistrictService;
import com.huatuo.clinics.cms.services.regulator.CmsJgOrgService;
import com.huatuo.clinics.cms.services.regulator.CsOutpEmrService;
import com.huatuo.clinics.cms.services.regulator.CsOutpVisitService;
import com.huatuo.clinics.cms.services.regulator.OpmDicDepartmentService;
import com.huatuo.clinics.cms.services.regulator.PrescriptionService;
import com.huatuo.clinics.cms.services.regulator.RsEmpiPatientInfoService;
import com.huatuo.clinics.cms.services.report.ClinicsDetectionService;
import com.huatuo.clinics.cms.services.translate.DefaultTranslatorManager;
import com.huatuo.clinics.cms.util.Constant;
import com.huatuo.common.BaseResponse;
import com.huatuo.common.DateUtils;
import com.huatuo.common.MessageUtil;
import com.huatuo.common.Utils;
import com.huatuo.db.bean.XtZdOrg;
import com.huatuo.pms.bo.BODoctorInfo;
import com.huatuo.pms.bo.BOZdOrg;
import com.huatuo.pms.services.DoctorService;
import com.huatuo.pms.services.ZdOrgService;

/**
 * 动态监测
 * @author ydj
 *
 */
@Controller
@RequestMapping(value="/dynamic")
public class DynamicDetectionControll {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(DynamicDetectionControll.class);
	
	@Resource
	private ClinicsDetectionService  clinicsDetectionService;
	@Resource
	private CmsJgDisputeService disputeService;
	@Autowired
	private CsOutpVisitService csOutpVisitService;//就诊服务
	@Autowired 
	private RsEmpiPatientInfoService  rsEmpiPatientInfoService;//患者信息服务
	@Autowired
	private PrescriptionService prescriptionService;//医嘱、处方、组套、药房药品综合服务接口
	@Resource
	private DoctorService doctorService;//医生信息服务
	@Resource
	private CsOutpEmrService csOutpEmrService;
	@Resource
	private ZdOrgService zdOrgService;
	@Resource
	private OpmDicDepartmentService opmDicDepartmentService;
	@Autowired
	private DefaultTranslatorManager dtm;
	@Autowired
	private CmsJgOrgService msJgOrgService;
	@Resource
	private CmsJgDistrictService cmsJgDistrictService;
	
	
	/**
	 * 违规行为查询(包括不在线期间的弹出框)
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/query",method = RequestMethod.GET)
	public @ResponseBody DynamicDetectionResponse query(HttpServletRequest request,
			@RequestParam(value="addressCode",required=true)String addressCode,
			Model model){
		DynamicDetectionResponse rep=new DynamicDetectionResponse();
		List<List<DynamicDetectionDTO>> list=new ArrayList<List<DynamicDetectionDTO>>();//用于存放当天数据
		List<List<DynamicDetectionDTO>> list2=new ArrayList<List<DynamicDetectionDTO>>();//用于存放用户退出系统期间的数据
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fmt1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
		try {
			//拿出用户的抗生素规则
			String antibioticId=null;
			if(null != userInfo){
				String org=userInfo.getCompany();
				CmsJgOrgDTO orgDto= msJgOrgService.queryOrgById(org);
				antibioticId=orgDto.getAntibioticLevelPolityId();//抗生素规则id
			}
			//查询区域下面的所有诊所
			List<BOZdOrg> orglist=zdOrgService.queryOrg(addressCode,false);
			List<String> orgId=new ArrayList<String>();
			if(null != orglist){
				for (BOZdOrg boZdOrg : orglist) {
					orgId.add(String.valueOf(boZdOrg.getId()));
				}
			}
			Date startDate=userInfo.getEndDate();
			String beginDate=fmt1.format(startDate);//用户上次退出系统的时间,如果为null则是第一次登陆
			Date date=new Date();
			String endDate=fmt1.format(date);//当前时间
			String strDate=null;
			//判断是不是第一次登陆，获取不同的开始日期
			if(null !=startDate){
				//用户上次退出系统期间发生的违规行为
				if(orgId.size() > 0){
					List<DynamicDetectionDTO> dto=clinicsDetectionService.queryDisputeNo(beginDate,endDate,orgId);//医疗争议
					List<DynamicDetectionDTO> dto1=clinicsDetectionService.queryInfectionNo(beginDate,endDate,orgId);//传染病
					List<DynamicDetectionDTO> dto2=clinicsDetectionService.queryAntibiotic(beginDate,endDate,orgId,antibioticId);//抗生素
					if(null != dto){
						list2.add(dto);
					}
					if(null != dto1){
						list2.add(dto1);
					}
					if(null != dto2){
						list2.add(dto2);
					}
				}
				rep.setStartDate(beginDate);
				rep.setEndDate(endDate);
			}
			strDate=fmt.format(date)+" 00:00:00";
			//查询当天发生的违规行为
			if(orgId.size() > 0){
				List<DynamicDetectionDTO> dto=clinicsDetectionService.queryDisputeNo(strDate,endDate,orgId);//医疗争议
				List<DynamicDetectionDTO> dto1=clinicsDetectionService.queryInfectionNo(strDate,endDate,orgId);//传染病
				List<DynamicDetectionDTO> dto2=clinicsDetectionService.queryAntibiotic(strDate,endDate,orgId,antibioticId);//抗生素
				
				list.add(dto);
				list.add(dto1);
				list.add(dto2);
				rep.setList(list);
				rep.setList2(list2);
			}
			String dates=fmt.format(date);
			rep.setDate(dates);
			if(null == startDate){
				rep.setFlag(1);//如果startDate等于null。页面弹出不在线期间数据
			}
			//取出集合中所有id，查询出详情数据并保存
			List<String> idIist=new ArrayList<String>();
			if(list2.size() > 0){
					for (int i = 0; i < list2.size(); i++) {
							List<DynamicDetectionDTO> dyList=list2.get(i);
							for (int j = 0; j < dyList.size(); j++) {
								DynamicDetectionDTO dyDto=dyList.get(j);
								idIist.add(dyDto.getId());
							}
					}
			}
			List<MonitoringEventsDTO> monList=clinicsDetectionService.queryType(idIist);
			if(null != monList){
				int i=clinicsDetectionService.insertMonitor(monList);//保存事件详情
			}
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("DynamicDetectionControll query 上一次退出时间到当前的卫生行为统计"+e.getMessage());
			rep.setCode(MessageUtil.CODE_FAILURE);
			rep.setMessage(MessageUtil.FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 监测事件具体情况
	 * @param request
	 * @param idIist  ID集合
	 * @return
	 */
	@RequestMapping(value="/queryTypeMonitor",method = RequestMethod.POST)
	public @ResponseBody MonitoringEventsResponse queryTypeMonitor(HttpServletRequest request,
			@RequestBody List<String> idIist){
		MonitoringEventsResponse rep=new MonitoringEventsResponse();
		try {
			List<CmsJgMonitor> list=clinicsDetectionService.queryTypeMonitor(idIist);
			rep.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("DynamicDetectionControll queryTypeMonitor 监测事件具体情况查询"+e.getMessage());
			rep.setCode(MessageUtil.CODE_FAILURE);
			rep.setMessage(MessageUtil.FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 根据地址id查询机构列表信息
	 * @param request
	 * @param addressCode
	 * @return
	 */
	@RequestMapping(value = "/queryOrgByCode",method = RequestMethod.GET)
	public @ResponseBody CmsOrgResponse queryOrgByCode(HttpServletRequest request,
			@RequestParam(value="addressCode",required=true)String addressCode) {
		CmsOrgResponse rep=new CmsOrgResponse();
		try {
			List<BOZdOrg> orglist=zdOrgService.queryOrg(addressCode,false);
			rep.setOrgList(orglist);
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
	 * 实时事故监测(10分钟执行一次)
	 * @param startDate  开始时间
	 * @param  endDate   结束时间
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/dynamicEvent",method = RequestMethod.GET)
	public @ResponseBody DynamicDetectionResponse queryDynamicEvent(HttpServletRequest request,
			@RequestParam(value="startDate",required=true)String startDate,
			@RequestParam(value="endDate",required=true)String endDate,
			@RequestParam(value="addressCode",required=true)String addressCode,
			Model model){
		DynamicDetectionResponse rep=new DynamicDetectionResponse();
		List<List<DynamicDetectionDTO>> list=new ArrayList<List<DynamicDetectionDTO>>();
		try {
			CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
			//拿出用户的抗生素规则
			String antibioticId=null;
			if(null != userInfo){
				String org=userInfo.getCompany();
				CmsJgOrgDTO orgDto= msJgOrgService.queryOrgById(org);
				antibioticId=orgDto.getAntibioticLevelPolityId();//抗生素规则id
			}
			//查询区域下面的所有诊所
			List<BOZdOrg> orglist=zdOrgService.queryOrg(addressCode,false);
			List<String> orgId=new ArrayList<String>();
			if(orglist.size() > 0){
				for (BOZdOrg boZdOrg : orglist) {
					orgId.add(String.valueOf(boZdOrg.getId()));
				}
			}
			if(orgId.size() > 0){
				List<DynamicDetectionDTO> dto=clinicsDetectionService.queryDisputeNo(startDate,endDate,orgId);//医疗争议
				List<DynamicDetectionDTO> dto1=clinicsDetectionService.queryInfectionNo(startDate,endDate,orgId);//传染病
				List<DynamicDetectionDTO> dto2=clinicsDetectionService.queryAntibiotic(startDate,endDate,orgId,antibioticId);//抗生素
				if(null != dto){
					list.add(dto);
				}
				if(null != dto1){
					list.add(dto1);			
				}
				if(null != dto2){
					list.add(dto2);
				}
			}
			List<String> idIist=new ArrayList<String>();
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
						List<DynamicDetectionDTO> dyList=list.get(i);
						for (int j = 0; j < dyList.size(); j++) {
							DynamicDetectionDTO dyDto=dyList.get(j);
							idIist.add(dyDto.getId());
						}
				}
			}
		List<MonitoringEventsDTO> monList=clinicsDetectionService.queryType(idIist);
		if(null != monList){
			int i=clinicsDetectionService.insertMonitor(monList);//保存事件详情
		}
			rep.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("DynamicDetectionControll queryDynamicEvent 实时事故监测(10分钟执行一次)"+e.getMessage());
			rep.setCode(MessageUtil.CODE_FAILURE);
			rep.setMessage(MessageUtil.FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 根据id查询医疗争议登记
	 * @param countyCode 镇级id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryDisputes", method = RequestMethod.GET)
	public @ResponseBody DisputeResponse queryDisputes(
			@RequestParam(value="id",required=true)String id,
			HttpServletRequest request) {
		DisputeResponse rep = new DisputeResponse();
		try {
			CmsJgDisputeDTO dto = disputeService.queryDisputes(id);
			rep.setDto(dto);
		} catch (Exception e) {
			logger.error("DynamicDetectionControll queryDisputes"+ e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	
	/**
	 * 根据处方id查询处方信息(抗生素)
	 * @param id   id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryOrder", method = RequestMethod.GET)
	public @ResponseBody OrderCheckResponse queryOrder(
			@RequestParam(value="orderId",required=true)String orderId,
			HttpServletRequest request) {
		OrderCheckResponse rep = new OrderCheckResponse();
		try {
			// 根据诊所id拿到单个处方信息
			CsOutpOrderDTO orderDto = prescriptionService.getByVisitNo(orderId);
			// 通过就诊id拿到患者id
			String visitId = orderDto.getVistiId();
			CsOutpVisitDTO dto = csOutpVisitService.queryPatientId(visitId);
			orderDto.setPatientId(dto.getPatientId());// 把患者id set到处方信息里
			// 当属于中药的时候
			if (Constant.ORDER_TYPE_CY.equals(orderDto.getDrugType())) {
				if (orderDto.getRepetition() != null
						&& orderDto.getRepetition() > 1) {
					// 修改单剂购买量
					orderDto.setBuyQty(orderDto.getBuyQty()/ orderDto.getRepetition());
				}
			}
			//就诊信息
			CsOutpVisitDTO csOutpVisitDTO =new CsOutpVisitDTO();
			csOutpVisitDTO.setOrgId(orderDto.getOrgId());
			csOutpVisitDTO.setVistiId(orderDto.getVistiId());
			csOutpVisitDTO.setPatientId(orderDto.getPatientId());
			XtZdOrg xtOrg=zdOrgService.getXtZdOrgBeanById(Integer.valueOf(orderDto.getOrgId()));//查询诊所信息
			csOutpVisitDTO.setOrgName(xtOrg.getName());//诊所名称
			csOutpVisitDTO.setOrgPhone(xtOrg.getTelephone());//诊所电话
			csOutpVisitDTO.setAddress(xtOrg.getAddress());//诊所地址
			//过敏史和诊断
			csOutpVisitDTO=csOutpVisitService.selectAllergicAndDxType(csOutpVisitDTO);
			CsOutpVisit csOutpVisit = csOutpVisitService.getById(visitId);
			csOutpVisitDTO.setVisitNo(orderDto.getVistiNo());
			//患者个人信息
			RsEmpiPatientInfoDTO rsEmpiPatientInfoDTO = rsEmpiPatientInfoService.getById(csOutpVisitDTO.getPatientId());
			Utils.copyProperties(rsEmpiPatientInfoDTO, csOutpVisitDTO);
			Utils.copyProperties(csOutpVisit, csOutpVisitDTO);
			//电子病历
			CsOutpEmrDTO csOutpEmrDTO = csOutpEmrService.getByVisitId(visitId);
			csOutpVisitDTO.setCsOutpEmrDTO(csOutpEmrDTO); 
			//查询科室
			OpmDicDepartmentDTO opmDicDepartmentDTO = opmDicDepartmentService.getById(csOutpVisitDTO.getOrdDepId());
			if(null != opmDicDepartmentDTO){
				csOutpVisitDTO.setOrdDepName(opmDicDepartmentDTO.getDepName());
			}
			//查询单个处方明细
			csOutpVisitDTO=prescriptionService.queryListDl(orderDto, csOutpVisitDTO,0);
			//获取医生信息
			BODoctorInfo doctor =doctorService.getDoctorById(csOutpVisitDTO.getDoctorId());
			if(null != doctor){
				csOutpVisitDTO.setDoctorName(doctor.getName());//医生名称
				csOutpVisitDTO.setTitlesClinical(String.valueOf(doctor.getTitlesClinical()));//医生职称
				csOutpVisitDTO.setDoctorPhone(doctor.getMobile());//医生电话
			}
			rep.setDto(csOutpVisitDTO);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error(
					"DynamicDetectionControll queryOrder......根据处方id查询处方信息失败"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	
	
	/**
	 * 根据诊断id查询处方信息(传染病)
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value = "/newQueryVisitDtlList", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse queryVisitDtlList(@RequestParam(value="dxId",required=true) String dxId,
			HttpServletRequest request) {
		OrderCheckResponse response =new OrderCheckResponse(); 
		CsOutpVisitDTO visitDTO = new CsOutpVisitDTO();
		try {
			//根据诊断明细id查询诊断信息,拿到就诊id
			CsOutpVisitDx csDxDto= csOutpVisitService.queryCsVisitDx(dxId);
			String visitId=null;
			if(null != csDxDto){
				visitId=csDxDto.getVistiId();
			}
			//就诊信息
			CsOutpVisit csOutpVisit = csOutpVisitService.getById(visitId);
			visitDTO.setVistiId(csOutpVisit.getVistiId());
			if(csOutpVisit==null){
				response.setCode(MessageUtil.CODE_FAILURE);
				response.setMessage(MessageUtil.FAILURE);//库存不足
				System.err.print("就诊id不对");
				return response;
			}
			//患者信息
			String patientId = csOutpVisit.getPatientId();
			RsEmpiPatientInfoDTO rsEmpiPatientInfoDTO = rsEmpiPatientInfoService.getById(patientId);
			visitDTO.setPatientId(rsEmpiPatientInfoDTO.getPatientId());
			if(rsEmpiPatientInfoDTO == null){
				response.setCode(MessageUtil.CODE_FAILURE);
				response.setMessage(MessageUtil.FAILURE);//库存不足
				System.err.print("患者信息出错");
				return response;
			}
			// 处方单及明细
			List<CsOutpOrderDTO> orderDTOs = prescriptionService.listByVisitId(visitId);
			if(orderDTOs.size()>0){
				dtm.translate(orderDTOs, "-1");
				for (int i = 0; i < orderDTOs.size(); i++) {
					List<CsOutpOrderDtlDTO> orderDtList = prescriptionService.getOrderDtList(orderDTOs.get(i).getOrdId());
					if(orderDtList.size()>0){
						orderDTOs.get(i).setRepetition(orderDtList.get(0).getRepetition());
						dtm.translate(orderDtList, "-1");
						orderDTOs.get(i).setOrderDtlDTOs(orderDtList);
					}
				}
			}
			//过敏史和诊断
			CsOutpVisitDTO  csDto=csOutpVisitService.selectAllergicAndDxType(visitDTO);
			Utils.copyProperties(rsEmpiPatientInfoDTO, visitDTO);
			Utils.copyProperties(csOutpVisit, visitDTO);
			visitDTO.setOrderDTOs(orderDTOs);
			visitDTO.setAllergicDrugString(csDto.getAllergicDrugString());
			visitDTO.setDxDTOs(csDto.getDxDTOs());
			//获取医生信息
			BODoctorInfo doctor =doctorService.getDoctorById(visitDTO.getDoctorId());
			if(null != doctor){
				visitDTO.setDoctorName(doctor.getName());//医生名称
				visitDTO.setTitlesClinical(String.valueOf(doctor.getTitlesClinical()));//医生职称
				visitDTO.setDoctorPhone(doctor.getMobile());//医生电话
			}
			XtZdOrg xtOrg=zdOrgService.getXtZdOrgBeanById(Integer.valueOf(visitDTO.getOrgId()));//查询诊所信息
			visitDTO.setOrgName(xtOrg.getName());//诊所名称
			visitDTO.setOrgPhone(xtOrg.getTelephone());//诊所电话
			visitDTO.setAddress(xtOrg.getAddress());//诊所地址
			
			response.setCode(MessageUtil.CODE_SUCCESS);
			response.setMessage(MessageUtil.SUCCESS);//操作成功
			response.setDto(visitDTO);
		} catch (Exception e) {
			response.setCode(MessageUtil.CODE_ERROR);
			response.setMessage(MessageUtil.FAILURE);//操作失败
			e.printStackTrace();
		}
		return response;
	}
	

	
	
	/**
	 * 历史记录用户机构地址查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAdress", method = RequestMethod.GET)
	public @ResponseBody AdressResponse queryAdress(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Cache-Control,Pragma,Content-Type,Token");
	    response.setHeader("Access-Control-Allow-Credentials","true");
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
				logger.error("DynamicDetectionControll queryAdress......用户机构地址查询失败"+ e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
	
	
	/**
	 * 历史信息查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryHistory", method = RequestMethod.GET)
	public @ResponseBody MonitoringEventsResponse queryHistory(HttpServletRequest request,
			@RequestParam(value="date",required=false) String date,
			@RequestParam(value="type",required=true) String type,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="addressId",required=true) String addressId
			){
			MonitoringEventsResponse rep=new MonitoringEventsResponse();
			try {
				List<CmsJgMonitor> list=clinicsDetectionService.queryMonitorDtl(date,type,name,addressId);
				rep.setList(list);
				rep.setCode(MessageUtil.CODE_SUCCESS);
				rep.setMessage(MessageUtil.SUCCESS);
			} catch (Exception e) {
				logger.error("DynamicDetectionControll queryHistory......历史信息查询"+ e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
	/**
	 * 获取诊所的基本信息
	 * @param request
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/queryClinicsInfo", method = RequestMethod.GET)
	public @ResponseBody CmsClinicsResponse queryClinicsInfo(HttpServletRequest request,RuquestBeanDTO beanDTO) {
		CmsClinicsResponse rep = new CmsClinicsResponse();
		try {
			CmsOrgInfoDTO cmsOrgInfoDTO = clinicsDetectionService.queryClinicsInfo(beanDTO);
			rep.setCmsOrgInfoDTO(cmsOrgInfoDTO);
		} catch (Exception e) {
			logger.error("DynamicDetectionControll queryClinicsInfo......获取诊所的基本信息"+ e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 查询（蒙自市下所有乡镇下的所有诊所信息）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryClinicsInMap", method = RequestMethod.GET) 
	public @ResponseBody CmsOrgResponse queryCLinicsInMap(HttpServletRequest request,RuquestBeanDTO beanDTO) {
		CmsOrgResponse rep=new CmsOrgResponse();
		try {
			CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
			List<CmsOrgInfoDTO> list = clinicsDetectionService.queryCLinicsInMap(person,beanDTO);
			rep.setCurrYearFirst(DateUtils.getCurrYearFirst());
			rep.setNowDate(DateUtils.getNowDate());
			rep.setCmsOrgInfoDTOs(list);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("DynamicDetectionControll queryCLinicsInMap......查询（蒙自市下所有乡镇下的所有诊所信息）"+ e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 添加巡查信息
	 * @param patrolDTO
	 * @return
	 */
	@RequestMapping(value = "/addPatrolInfo", method = RequestMethod.POST)
	public @ResponseBody BaseResponse addPatrolInfo(CmsJgPatrolDTO patrolDTO) {
		BaseResponse rep = new BaseResponse();
		try {
			clinicsDetectionService.addPatrolInfo(patrolDTO);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("DynamicDetectionControll addPatrolInfo......添加巡查信息"+ e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
}
