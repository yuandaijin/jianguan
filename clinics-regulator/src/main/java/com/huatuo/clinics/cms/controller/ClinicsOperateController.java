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
import com.huatuo.clinics.cms.response.OperateTableResponse;
import com.huatuo.clinics.cms.response.ReportResponse;
import com.huatuo.clinics.cms.services.report.ClinicsInfoService;
import com.huatuo.clinics.cms.services.report.ClinicsOperateService;
import com.huatuo.common.MessageUtil;

@Controller
@RequestMapping("/operate")
public class ClinicsOperateController {
	
	@Resource
	private ClinicsOperateService clinicsOperateService;
	@Resource
	private ClinicsInfoService clinicsInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClinicsOperateController.class);
	
	/**
	 * 个体诊所运营情况概览
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryOperate", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryOperate(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			String totalCode="00049-0001";
			String efficiencyCode="00049-0002";
			//门诊总量
			List<ReportResultDTO>  dtoList=clinicsInfoService.queryOperates(year,month,parentAreaCode,totalCode,reportType);
			//服务效率
			List<ReportResultDTO>  list=clinicsInfoService.queryOperates(year,month,parentAreaCode,efficiencyCode,reportType);
			rep.setTotallist(dtoList);
			rep.setEfficiencylist(list);
		} catch (Exception e) {
			logger.error("ClinicsOperateController queryOperate"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 个体诊所运营情况概览table
	 */
	@RequestMapping(value = "/queryOperatetable", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryOperatetable(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			String totalCode="00049-0001";
			String efficiencyCode="00049-0002";
			String costCode="00049-0003";
			//门诊总量
			List<ReportResultDTO>  dtoList=clinicsInfoService.queryOperates(year,month,parentAreaCode,totalCode,reportType);
			//服务效率
			List<ReportResultDTO>  efficiencylist=clinicsInfoService.queryOperates(year,month,parentAreaCode,efficiencyCode,reportType);
			//服务费用
			List<ReportResultDTO>  costlist=clinicsInfoService.queryOperates(year,month,parentAreaCode,costCode,reportType);
			rep.setTotallist(dtoList);
			rep.setEfficiencylist(efficiencylist);
			rep.setCostlist(costlist);
		} catch (Exception e) {
			logger.error("ClinicsOperateController queryOperatetable"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	
	/**
	 * 服务总量辖区总体概括(包含同比和环比)
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @param month 月份
	 * @param reportType 报表类型
	 * @param sourceCode 查询指标
	 * @return
	 */
	@RequestMapping(value = "/queryVisit", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryVisit(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "sourceCode") String sourceCode,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			//个体诊所初复诊门诊量统计
			List<ReportResultDTO>  dtoList=clinicsOperateService.queryVisit(year,month,reportType,parentAreaCode,sourceCode);//本月数据
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
			List<ReportResultDTO>  momList=clinicsOperateService.queryVisit(momYear,momMonth,reportType,parentAreaCode,sourceCode);//环比
			//对同比参数做限制
			String anYear=null;
			String anMonth = null;
			if(reportType.equals("year")){
				anYear=String.valueOf(Integer.parseInt(year)-1);
			}else{
				anYear=String.valueOf(Integer.parseInt(year)-1);
				anMonth=month;
			}
			List<ReportResultDTO>  anList=clinicsOperateService.queryVisit(anYear,anMonth,reportType,parentAreaCode,sourceCode);//同比
			rep.setList(dtoList);
			rep.setMomlist(momList);
			rep.setAnlist(anList);
		} catch (Exception e) {
			logger.error("ClinicsOperateController queryVisit"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 服务总量下级区域概括
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @param month 月份
	 * @param reportType 报表类型
	 * @param sourceCode 查询指标
	 * @return
	 */
	@RequestMapping(value = "/querySubordinate", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse querySubordinate(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "sourceCode") String sourceCode
			,HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			//服务总量下级区域概括
			List<ReportResultDTO>  dtoList=clinicsOperateService.querySubordinate(year,month,reportType,parentAreaCode,sourceCode);
			rep.setList(dtoList);
		} catch (Exception e) {
			logger.error("ClinicsOperateController querySubordinate"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 平均门诊量概括
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @param month 月份
	 * @param reportType 报表类型
	 * @return
	 */
	@RequestMapping(value = "/queryAveOutpatient", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryAveOutpatient(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			@RequestParam(value = "reportType") String reportType
			,HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			//平均门诊量概括
			List<ReportResultDTO>  dtoList=clinicsOperateService.queryAveOutpatient(year,month,reportType,parentAreaCode);
			rep.setList(dtoList);
		} catch (Exception e) {
			logger.error("ClinicsOperateController queryAveOutpatient"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 个体诊所门诊量信息总表
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @param month 月份
	 * @param reportType 报表类型
	 * @return
	 */
	@RequestMapping(value = "/queryTableInfo", method = RequestMethod.GET)
	public @ResponseBody
	OperateTableResponse queryTableInfo(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			@RequestParam(value = "reportType") String reportType
			,HttpServletRequest request) {
		OperateTableResponse rep = new OperateTableResponse();
		try {
			//个体诊所门诊量信息总表所有信息
			List<List<ReportResultDTO>> dtolist=clinicsOperateService.queryTableInfo(year,month,reportType,parentAreaCode);
			rep.setResultList(dtolist);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsOperateController queryTableInfo"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	
	/**
	 * 服务总量时间趋势
	 * @param parentAreaCode 区域id
	 * @param startYear 开始年份
	 * @param endYear 结束年份
	 * @param startMonth 开始月份
	 * @param endMonth 结束月份 
	 * @param reportType 报表类型
	 * @param sourceCode 查询指标
	 * @return
	 */
	@RequestMapping(value = "/queryTimeTrend", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryTimeTrend(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "startYear") String startYear,
			@RequestParam(value = "endYear") String endYear,
			@RequestParam(value = "startMonth") String startMonth,
			@RequestParam(value = "endMonth") String endMonth,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "sourceCode") String sourceCode,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			//服务总量时间趋势
			List<ReportResultDTO>  dtoList=clinicsOperateService.queryTimeTrend(startYear,endYear,startMonth,endMonth,reportType,parentAreaCode,sourceCode);
			rep.setList(dtoList);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsOperateController queryTimeTrend"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
}
