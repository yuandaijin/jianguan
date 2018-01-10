package com.huatuo.clinics.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.response.ReportIncidenceResponse;
import com.huatuo.clinics.cms.response.ResidentsResponse;
import com.huatuo.clinics.cms.services.report.ClinicResidentsService;
import com.huatuo.common.MessageUtil;
/**
 * 居民健康
 * @author ydj
 *
 */
@Controller
@RequestMapping("/residents")
public class ClinicsResidentsController {
	
	@Resource
	private ClinicResidentsService clinicResidentsService;
	private static final Logger logger = LoggerFactory.getLogger(ClinicsResidentsController.class);
	
	
	/**
	 * 区域居民健康(多发病分析)
	 * @param parentAreaCode 区域id
	 * @param year 年
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryReIncidence", method = RequestMethod.GET)
	public @ResponseBody
	ResidentsResponse queryReIncidence(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ResidentsResponse rep = new ResidentsResponse();
		try {
			//查询所有病种的总数量
			String sum=clinicResidentsService.queryReIncidenceSum(year,parentAreaCode,reportType,month);
			//查询排名前十病种
			List<ReportResultDTO>  dtoList=clinicResidentsService.queryReIncidence(year,parentAreaCode,reportType,month);
			rep.setList(dtoList);
			rep.setSum(sum);//总数量
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsResidentsController queryReIncidence"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 区域居民健康(特定病种分析)
	 * @param parentAreaCode 区域id
	 * @param year 年
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryReGiven", method = RequestMethod.GET)
	public @ResponseBody
	ResidentsResponse queryReGiven(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ResidentsResponse rep = new ResidentsResponse();
		try {
			//就诊A病种人数查询
			String  code="00057";
			List<ReportResultDTO>  aidslsit=clinicResidentsService.queryReGiven(year,parentAreaCode,reportType,month,code);
			//就诊B病种人数查询
			code="00058";
			List<ReportResultDTO>  hbvlist=clinicResidentsService.queryReGiven(year,parentAreaCode,reportType,month,code);
			//就诊C病种人数查询
			code="00059";
			List<ReportResultDTO>  hfmdlist=clinicResidentsService.queryReGiven(year,parentAreaCode,reportType,month,code);
			rep.setAidslsit(aidslsit);
			rep.setHbvlist(hbvlist);
			rep.setHfmdlist(hfmdlist);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsResidentsController queryReGiven"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 多发病分析(年季月)
	 * @param reportType 报表类型
	 * @param year 年
	 * @param startMonth 开始月
	 * @param endMonth 结束月
	 * @param parentAreaCode 区域id
	 * 
	 */
	@RequestMapping(value = "/queryDiseases", method = RequestMethod.GET)
	public @ResponseBody
	ResidentsResponse queryDiseases(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "startMonth") String startMonth,
			@RequestParam(value = "endMonth") String endMonth,
			@RequestParam(value = "day") String day,
			@RequestParam(value = "reportType") String reportType
			,HttpServletRequest request) {
		ResidentsResponse rep = new ResidentsResponse();
		try {
			//查询所有病种的总数量(年季月)
			String sum=clinicResidentsService.queryDiseasesSum(year,parentAreaCode,startMonth,endMonth,day,reportType);
			//查询排名前十病种(年季月)
			List<ReportResultDTO>  dtoList=clinicResidentsService.queryDiseases(year,parentAreaCode,startMonth,endMonth,day,reportType);
			rep.setList(dtoList);
			rep.setSum(sum);//总数量
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsResidentsController queryDiseases"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 多发病特征(就诊前十病种)
	 * @param reportType 报表类型
	 * @param year 年
	 * @param startMonth 开始月
	 * @param endMonth 结束月
	 * @param parentAreaCode 区域id
	 * 
	 */
	@RequestMapping(value = "/queryFirstTen", method = RequestMethod.GET)
	public @ResponseBody
	ResidentsResponse queryFirstTen(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "startMonth") String startMonth,
			@RequestParam(value = "endMonth") String endMonth,
			@RequestParam(value = "day") String day,
			@RequestParam(value = "reportType") String reportType
			,HttpServletRequest request) {
		ResidentsResponse rep = new ResidentsResponse();
		try {
			List<ReportResultDTO>  dtoList=clinicResidentsService.queryFirstTen(year,parentAreaCode,startMonth,endMonth,day,reportType);
			List<ReportResultDTO>  sumist=clinicResidentsService.queryFirstTenSum(year,parentAreaCode,startMonth,endMonth,day,reportType);
			rep.setList(dtoList);
			rep.setSumlist(sumist);
		} catch (Exception e) {
			logger.error("ClinicsResidentsController queryFirstTen"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 就诊疾病前十病种高发地区分布
	 * @param reportType 报表类型
	 * @param year 年
	 * @param startMonth 开始月
	 * @param endMonth 结束月
	 * @param parentAreaCode 区域id
	 * 
	 */
	@RequestMapping(value = "/queryFirstTenType", method = RequestMethod.GET)
	public @ResponseBody
	ReportIncidenceResponse queryFirstTenType(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "startMonth") String startMonth,
			@RequestParam(value = "endMonth" ,required=false) String endMonth,
			@RequestParam(value = "day") String day,
			@RequestParam(value = "reportType") String reportType,
			HttpServletRequest request) {
		ReportIncidenceResponse rep = new ReportIncidenceResponse();
		try {
			//查询性别
			List<ReportResultDTO>  sexList=clinicResidentsService.queryFirstTenType(year,parentAreaCode,startMonth,endMonth,day,reportType);
			//查询年龄
			List<ReportResultDTO>  ageList=clinicResidentsService.queryFirstTenTypes(year,parentAreaCode,startMonth,endMonth,day,reportType);
			rep.setSexList(sexList);
			rep.setAgeList(ageList);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsResidentsController queryFirstTenType"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
}
