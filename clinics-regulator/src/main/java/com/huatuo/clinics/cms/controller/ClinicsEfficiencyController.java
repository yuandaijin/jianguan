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
import com.huatuo.clinics.cms.comm.DateUtil;
import com.huatuo.clinics.cms.response.ReportResponse;
import com.huatuo.clinics.cms.services.report.ClinicsEfficiencyService;
import com.huatuo.clinics.cms.services.report.ClinicsInfoService;
import com.huatuo.common.MessageUtil;
import com.huatuo.common.Utils;
/**
 * 服务效率
 * @author ydj
 *
 */
@Controller
@RequestMapping("/efficiency")
public class ClinicsEfficiencyController {
	
	@Resource
	private ClinicsEfficiencyService clinicsEfficiencyService;
	@Resource
	private ClinicsInfoService clinicsInfoService;
	private static final Logger logger = LoggerFactory.getLogger(ClinicsEfficiencyController.class);
	
	
	/**
	 * 个体诊所服务效率概况分析(辖区总体概括)
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryServiceQty", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryServiceQty(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			List<String> str=DateUtil.getNextDay();
			String day=str.get(2);
			if(Utils.isBlank(month)){
				month=str.get(1);
			}
			//查询当月服务人次
			List<ReportResultDTO>  list=clinicsEfficiencyService.queryServiceQty(year,parentAreaCode,reportType,month,day);
			//对环比参数做限制
			//对环比参数做限制
			String momYear=null;
			String momMonth = null;
			if(reportType.equals("year")){
				momYear=String.valueOf(Integer.parseInt(year)-1);
			}else{
				if(month.equals("1")){
					momYear=String.valueOf(Integer.parseInt(year)-1);
					momMonth="12";
				}else{
					momYear=year;
					momMonth=String.valueOf(Integer.parseInt(month)-1);
				}
			}
			//环比人次查询
			List<ReportResultDTO>  monList=clinicsEfficiencyService.queryServiceQty(momYear,parentAreaCode,reportType,momMonth,day);
			rep.setList(list);
			rep.setMomlist(monList);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsEfficiencyController queryServiceQty"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 各类型个体诊所服务效率概况分析(辖区总体概括)
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @return
	 */
	@RequestMapping(value = "/queryServiceEff", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryServiceEff(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			String typeCode="00064";
			String scaleCode="00065";
			//查询不同类型诊所日人均服务人次分布
			List<ReportResultDTO>  typelist=clinicsEfficiencyService.queryServiceEff(year,parentAreaCode,typeCode,reportType,month);
			//不同规模诊所日人均服务人次分布
			List<ReportResultDTO>  scaleList=clinicsEfficiencyService.queryServiceEff(year,parentAreaCode,scaleCode,reportType,month);
			rep.setTypelist(typelist);
			rep.setScalelist(scaleList);
		} catch (Exception e) {
			logger.error("ClinicsEfficiencyController queryServiceEff"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	
	/**
	 * 下级区域概括(服务人次)
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @param code 指标code
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryJuniorOne", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryJuniorOne(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			String code="00049-0002";
			//服务效率
			List<ReportResultDTO>  costlist=clinicsInfoService.queryOperates(year,month,parentAreaCode,code,reportType);
			rep.setList(costlist);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsEfficiencyController queryJuniorOne"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 下级区域概括(不同规模和不同类型)
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @param code 指标code
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryJunior", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryJunior(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			//下级区域概括
			List<ReportResultDTO>  list=clinicsEfficiencyService.queryJunior(year,parentAreaCode,code,month,reportType);
			rep.setList(list);
		} catch (Exception e) {
			logger.error("ClinicsEfficiencyController queryJunior"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
}
