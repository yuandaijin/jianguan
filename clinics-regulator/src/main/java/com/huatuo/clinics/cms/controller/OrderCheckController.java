package com.huatuo.clinics.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDtlDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.bean.CsOutpEmrDTO;
import com.huatuo.clinics.cms.bean.CsOutpOrderDTO;
import com.huatuo.clinics.cms.bean.CsOutpVisitDTO;
import com.huatuo.clinics.cms.bean.OpmDicDepartmentDTO;
import com.huatuo.clinics.cms.bean.RsEmpiPatientInfoDTO;
import com.huatuo.clinics.cms.bean.SsDicItemDTO;
import com.huatuo.clinics.cms.comm.SessionUtils.SessionUtils;
import com.huatuo.clinics.cms.db.bean.CsOutpVisit;
import com.huatuo.clinics.cms.response.AdressResponse;
import com.huatuo.clinics.cms.response.OrderCheckDtlResponse;
import com.huatuo.clinics.cms.response.OrderCheckRecordResponse;
import com.huatuo.clinics.cms.response.OrderCheckResponse;
import com.huatuo.clinics.cms.response.OrderTypeItemResponse;
import com.huatuo.clinics.cms.services.regulator.CmsJgDistrictService;
import com.huatuo.clinics.cms.services.regulator.CmsJgOrgService;
import com.huatuo.clinics.cms.services.regulator.CsOutpEmrService;
import com.huatuo.clinics.cms.services.regulator.CsOutpVisitService;
import com.huatuo.clinics.cms.services.regulator.DictionaryService;
import com.huatuo.clinics.cms.services.regulator.OpmDicDepartmentService;
import com.huatuo.clinics.cms.services.regulator.OrderCheckService;
import com.huatuo.clinics.cms.services.regulator.PrescriptionService;
import com.huatuo.clinics.cms.services.regulator.RsEmpiPatientInfoService;
import com.huatuo.clinics.cms.util.Constant;
import com.huatuo.clinics.cms.util.Random;
import com.huatuo.common.MessageUtil;
import com.huatuo.common.Utils;
import com.huatuo.pms.bo.BODoctorInfo;
import com.huatuo.pms.bo.BOZdOrg;
import com.huatuo.pms.services.DoctorService;
import com.huatuo.pms.services.ZdOrgService;

@Controller
@RequestMapping("/orderCheck")
public class OrderCheckController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private CmsJgOrgService msJgOrgService;
	@Resource
	private CmsJgDistrictService cmsJgDistrictService;
	@Resource
	private ZdOrgService zdOrgService;
	@Resource
	private PrescriptionService prescriptionService;
	@Resource
	private CsOutpVisitService csOutpVisitService;//就诊服务
	@Resource
	private RsEmpiPatientInfoService rsEmpiPatientInfoService;
	@Resource
	private CsOutpEmrService csOutpEmrService;
	@Resource
	private OpmDicDepartmentService opmDicDepartmentService;
	@Resource
	private DoctorService doctorService;//医生信息服务
	@Resource
	private OrderCheckService orderCheckService;//处方抽查服务
	
	
	/**
	 * 处方类型字典查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryDict", method = RequestMethod.GET)
	public @ResponseBody OrderTypeItemResponse queryDictionaries(
			@RequestParam (value="code",required=true)String code,
			HttpServletRequest request){
		OrderTypeItemResponse rep=new OrderTypeItemResponse();
			try {
				//code='20107'
				//查询处方类型
				List<SsDicItemDTO>  list = dictionaryService.queryBillingCodes(code);
				CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
				String userId=String.valueOf(userInfo.getId());
				String userName=userInfo.getUserName();
				rep.setUserId(userId);//用户id
				rep.setUserName(userName);//用户名称
				rep.setList(list);
				rep.setCode(MessageUtil.CODE_SUCCESS);
				rep.setMessage(MessageUtil.SUCCESS);
			} catch (Exception e) {
				logger.error("OrderCheckController queryDictionaries......处方类型字典查询失败"+ e.getMessage());
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
				logger.error("OrderCheckController queryAdress......用户机构地址查询失败"+ e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
	
	/**
	 * 查询待抽查处方id
	 * @param roadCode 需要抽查的镇
	 * @param orgName 诊所名称
	 * @param startDate 查询处方开始日期
	 * @param endDate   查询处方截止日期
	 * @param orderType 处方类型
	 * @param checkNo   抽查数量
	 * @return
	 */
	@RequestMapping(value = "/queryVisitIdList", method = RequestMethod.GET)
	public @ResponseBody OrderCheckResponse queryVisitIdList(HttpServletRequest request,
			@RequestParam(value="roadCode",required=true)String roadCode,
			@RequestParam(value="orgName",required=false)String orgName,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="orderType",required=false)String orderType,
			@RequestParam(value="checkNo",required=false)Integer checkNo){
		OrderCheckResponse rep=new OrderCheckResponse();
			try {
				//根据地址和诊所名称查询所有的诊所
				if(orgName==""){
					orgName=null;
				}
				CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
				String userId=String.valueOf(userInfo.getId());
				List<String> strList=zdOrgService.queryOrgdId(orgName,roadCode);
				if(strList.size() >0){
					//根据诊所id、处方开始和结束日期、处方类型查询处方对象
					List<CsOutpOrderDTO> orderDTOs  = prescriptionService.getByVisitNo(strList,startDate,endDate,orderType,userId);
					if(orderDTOs.size()<checkNo){
						rep.setMessage("抽查数量不能大于处方数量,当前查询处方"+orderDTOs.size()+"条");
						rep.setCode(MessageUtil.CODE_ERROR);
						return rep;
					}
					//从查询出的所有处方中随机抽取N条处方数据
					List<CsOutpOrderDTO> list=Random.randomNumber(orderDTOs, checkNo);
					//转换成处方id返回前台
					List<String> str=new ArrayList<String>();
					for (int i = 0; i < list.size(); i++) {
						str.add(list.get(i).getOrdId());
					}
					rep.setStr(str);
					BOZdOrg bozDto=zdOrgService.getXtZdOrg(Integer.valueOf(strList.get(0)));//查询诊所信息
					rep.setOrgName(bozDto.getName());//诊所名称
					rep.setOrgId(strList.get(0));//诊所id
				}
				rep.setCode(MessageUtil.CODE_SUCCESS);
				rep.setMessage(MessageUtil.SUCCESS);
			} catch (Exception e) {
				logger.error("OrderCheckController queryVisitIdList......待抽查处方id查询失败"+ e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
	
	/**
	 * 根据处方id查询处方信息
	 * @param orderId 处方id
	 * @param id 抽查保存后的处方id
	 * @param flag 判断是查询审核前的处方信息还是审核后的
	 * @return
	 */
	@RequestMapping(value = "/queryOrderDtl", method = RequestMethod.GET)
	public @ResponseBody
	OrderCheckResponse queryOrderDtl(HttpServletRequest request,
			@RequestParam(value = "orderId", required = true) String orderId,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "flag", required = true) Integer flag) {
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
			BOZdOrg bozDto=zdOrgService.getXtZdOrg(Integer.valueOf(orderDto.getOrgId()));//查询诊所信息
			csOutpVisitDTO.setOrgName(bozDto.getName());//诊所名称
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
			}
			if(flag==1){//flag=1表示查询审核后的处方审核结果
				List<CmsJgOrdercheckDtlDTO> list=orderCheckService.queryOrder(id);
				rep.setList(list);
			}
			rep.setDto(csOutpVisitDTO);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error(
					"OrderCheckController queryOrderDtl......根据处方id查询处方信息失败"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	

	
	/**
	 * 处方抽查信息保存
	 * @param CmsJgOrdercheckDTO 抽查处方汇总信息和明细信息
	 * @return
	 */
	@RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
	public @ResponseBody OrderCheckResponse insertOrder(HttpServletRequest request,
			@RequestBody CmsJgOrdercheckDTO dto,Model model){
		OrderCheckResponse rep=new OrderCheckResponse();
			try {
				//保存
				String checkId=orderCheckService.insertOrder(dto);
				//查询处方头信息
				CmsJgOrdercheckDTO resultDto=null;
				if(null != checkId){
					resultDto=orderCheckService.queryOrderHands(checkId);
				}
				if(null != resultDto){
					rep.setCheckDto(resultDto);
					rep.setCode(MessageUtil.CODE_SUCCESS);
					rep.setMessage(MessageUtil.SUCCESS);
				}
			} catch (Exception e) {
				logger.error("OrderCheckController insertOrder......处方抽查信息保存失败"+ e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
	
	/**
	 * 处方抽查记录查询
	 * @param startDate 起始日期
	 * @param endDate 结束日期
	 * @param checkArea 抽查地区
	 * @param orgName 诊所名称
	 * @return
	 */
	@RequestMapping(value = "/queryOrderRecord", method = RequestMethod.GET)
	public @ResponseBody OrderCheckRecordResponse queryOrderRecord(HttpServletRequest request,
			@RequestParam(value="startDate",required=true) String startDate,
			@RequestParam(value="endDate",required=true) String endDate,
			@RequestParam(value="checkArea",required=true) String checkArea,
			@RequestParam(value="orgName",required=false) String orgName,
			Model model){
		OrderCheckRecordResponse rep=new OrderCheckRecordResponse();
			try {
				List<CmsJgOrdercheckDTO> list=orderCheckService.queryOrderRecord(startDate,endDate,checkArea,orgName);
				rep.setList(list);
				rep.setCode(MessageUtil.CODE_SUCCESS);
				rep.setMessage(MessageUtil.SUCCESS);
			} catch (Exception e) {
				logger.error("OrderCheckController queryOrderRecord......处方抽查记录查询失败"+e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
	/**
	 * 合格或不合格处方记录查询
	 * @param orderId 处方id
	 * @param flag 区分查询的是合格的处方还是不合格的，0为合格；1为不合格
	 * @return
	 */
	@RequestMapping(value = "/queryOrderQualified", method = RequestMethod.GET)
	public @ResponseBody OrderCheckDtlResponse queryOrderQualified(HttpServletRequest request,
			@RequestParam(value="checkId",required=true) String checkId,
			@RequestParam(value="flag",required=true) Integer flag,
			Model model){
		OrderCheckDtlResponse rep=new OrderCheckDtlResponse();
			try {
				List<CmsJgOrdercheckDtlDTO> list=orderCheckService.queryOrderQualified(checkId,flag);
				rep.setList(list);
				rep.setCode(MessageUtil.CODE_SUCCESS);
				rep.setMessage(MessageUtil.SUCCESS);
			} catch (Exception e) {
				logger.error("OrderCheckController queryOrderQualified......合格或不合格处方记录查询"+e.getMessage());
				rep.setMessage(MessageUtil.FAILURE);
				rep.setCode(MessageUtil.CODE_FAILURE);
			}
		return rep;
	}
	
	
}
