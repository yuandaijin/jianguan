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
import com.huatuo.clinics.cms.comm.MessageUtil;
import com.huatuo.clinics.cms.response.ReportGivenResponse;
import com.huatuo.clinics.cms.services.report.ClinicGivenService;

@Controller
@RequestMapping("/given")
public class ClinicsGivenController {
	
	@Resource
	private ClinicGivenService clinicGivenService;
	private static final Logger logger = LoggerFactory.getLogger(ClinicsGivenController.class);
	
	/**
	 * 特定病种概括
	 * @param reportType 报表类型
	 * @param year 年
	 * @param startMonth 开始月
	 * @param endMonth 结束月
	 * @param parentAreaCode 区域id
	 * @param code 病种指标(A病种 00057   B病种 00058  C病种 00059)
	 * 
	 */
	@RequestMapping(value = "/queryGivenSummary", method = RequestMethod.GET)
	public @ResponseBody
	ReportGivenResponse queryGivenSummary(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "startMonth") String startMonth,
			@RequestParam(value = "endMonth") String endMonth,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "day") String day,
			@RequestParam(value = "code") String code) {
		ReportGivenResponse rep = new ReportGivenResponse();
		try {
			//个体诊所选择病种发病总量
			List<ReportResultDTO> list=clinicGivenService.queryGivenSummary(year,parentAreaCode,startMonth,endMonth,day,reportType,code);
			//对环比参数做限制
			String momYear=null;
			String momSmonth=null;
			String momEmonth=null;
			if(reportType.equals("year")){//年
				momYear=String.valueOf(Integer.parseInt(year)-1);
			}else if(reportType.equals("month")){//月
				if(startMonth.equals("1")){
					year=String.valueOf(Integer.parseInt(year)-1);
					momSmonth="12";
				}else{
					momYear=year;
					momSmonth=String.valueOf(Integer.parseInt(startMonth)-1);
				}
			}else{//季度
				//4个季度
				if(startMonth.equals("1")){
					momYear=String.valueOf(Integer.parseInt(year)-1);
					momSmonth="10";
					momEmonth="12";
				}else if(startMonth.equals("4")){
					momYear=year;
					momSmonth="1";
					momEmonth="3";
				}else if(startMonth.equals("7")){
					momYear=year;
					momSmonth="4";
					momEmonth="6";
				}else{
					momYear=year;
					momSmonth="7";
					momEmonth="9";
				}
		}
			List<ReportResultDTO>  momList=clinicGivenService.queryGivenSummary(momYear,parentAreaCode,momSmonth,momEmonth,day,reportType,code);
			//对同比参数做限制
			String anYear=null;
			String anSmonth=null;
			String anEmonth=null;
			if(reportType.equals("year")){//年
				anYear=String.valueOf(Integer.parseInt(year)-1);
			}else if(reportType.equals("month")){//月
				anYear=String.valueOf(Integer.parseInt(year)-1);
					anSmonth=startMonth;
			}else{//季度
				anYear=String.valueOf(Integer.parseInt(year)-1);
				anSmonth=startMonth;
				anEmonth=endMonth;
			}
			List<ReportResultDTO>  anList=clinicGivenService.queryGivenSummary(anYear,parentAreaCode,anSmonth,anEmonth,day,reportType,code);
			rep.setList(list);
			rep.setAnList(anList);
			rep.setMomList(momList);
		} catch (Exception e) {
			logger.error("ClinicsGivenController queryGivenSummary"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	
	/**
	 * 特定病种特征(特定病种高发地区分布)
	 * @param reportType 报表类型
	 * @param year 年
	 * @param startMonth 开始月
	 * @param endMonth 结束月
	 * @param parentAreaCode 区域id
	 * @param code 病种指标(A病种 00057   B病种 00058  C病种 00059)
	 * 
	 */
	@RequestMapping(value = "/queryGivenFeatures", method = RequestMethod.GET)
	public @ResponseBody
	ReportGivenResponse queryGivenFeatures(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "startMonth") String startMonth,
			@RequestParam(value = "endMonth") String endMonth,
			@RequestParam(value = "day") String day,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "code") String code,HttpServletRequest request) {
		ReportGivenResponse rep = new ReportGivenResponse();
		try {
			//特定病种高发地区分布
			List<ReportResultDTO> list=clinicGivenService.queryGivenFeatures(year,parentAreaCode,startMonth,endMonth,day,reportType,code);
			rep.setList(list);
		} catch (Exception e) {
			logger.error("ClinicsGivenController queryGivenFeatures"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 特定病种特征(特定病种性别和年龄分布)
	 * @param reportType 报表类型
	 * @param year 年
	 * @param startMonth 开始月
	 * @param endMonth 结束月
	 * @param parentAreaCode 区域id
	 * @param code1 男女指标
	 * @param code2 年龄指标
	 * 
	 */
	@RequestMapping(value = "/queryGivenSexOrAge", method = RequestMethod.GET)
	public @ResponseBody
	ReportGivenResponse queryGivenSexOrAge(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "startMonth") String startMonth,
			@RequestParam(value = "endMonth") String endMonth,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "code1") String code1,
			@RequestParam(value = "code2") String code2,
			@RequestParam(value = "day") String day,
			HttpServletRequest request) {
		ReportGivenResponse rep = new ReportGivenResponse();
		try {
			//个体诊所A病种发病性别分布
			List<ReportResultDTO> sexlist=clinicGivenService.queryGivenSexOrAge(year,parentAreaCode,startMonth,endMonth,reportType,code1,day);
			//体诊所A病种发病年龄分布
			List<ReportResultDTO> agelist=clinicGivenService.queryGivenSexOrAge(year,parentAreaCode,startMonth,endMonth,reportType,code2,day);
			rep.setSexList(sexlist);
			rep.setAgeList(agelist);
		} catch (Exception e) {
			logger.error("ClinicsGivenController queryGivenSexOrAge"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 时间趋势
	 * @param reportType 报表类型
	 * @param startYear 开始年
	 * @param endYear 结束年
	 * @param startMonth 开始月
	 * @param endMonth 结束月
	 * @param parentAreaCode 区域id
	 * @param code  病种指标(A病种 00057   B病种 00058  C病种 00059)
	 * 
	 */
	@RequestMapping(value = "/queryGivenTimeTrend", method = RequestMethod.GET)
	public @ResponseBody
	ReportGivenResponse queryGivenTimeTrend(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "startYear") String startYear,
			@RequestParam(value = "endYear") String endYear,
			@RequestParam(value = "startMonth") String startMonth,
			@RequestParam(value = "endMonth") String endMonth,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "startDay") String startDay,
			@RequestParam(value = "endDay") String endDay,
			HttpServletRequest request) {
		ReportGivenResponse rep = new ReportGivenResponse();
		try {
			List<ReportResultDTO> list=clinicGivenService.queryGivenTimeTrend(startYear,endYear,parentAreaCode,startMonth,endMonth,startDay,endDay,reportType,code);
			rep.setList(list);
		} catch (Exception e) {
			logger.error("ClinicsGivenController queryGivenTimeTrend"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
}
