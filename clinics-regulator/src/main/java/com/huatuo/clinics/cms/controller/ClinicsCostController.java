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
import com.huatuo.clinics.cms.response.ReportResponse;
import com.huatuo.clinics.cms.services.report.ClinicsCostService;
import com.huatuo.common.MessageUtil;
/**
 * 服务费用
 * @author ydj
 *
 */
@Controller
@RequestMapping("/cost")
public class ClinicsCostController {
	
	@Resource
	private ClinicsCostService ClinicsCostService;
	private static final Logger logger = LoggerFactory.getLogger(ClinicsCostController.class);
	
	
	/**
	 * 个体诊所服务费用概况分析
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryCostQty", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryCostQty(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			//查询当年人均就诊费用
			List<ReportResultDTO>  list=ClinicsCostService.queryCostQty(year,parentAreaCode,month,reportType);
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
			//人均就诊费用环比
			List<ReportResultDTO>  monList=ClinicsCostService.queryCostQty(momYear,parentAreaCode,momMonth,reportType);
			rep.setList(list);
			rep.setMomlist(monList);
		} catch (Exception e) {
			logger.error("ClinicsCostController queryCostQty"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 各类型个体诊所人均就诊费用概况分析
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryCostType", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryCostType(
			@RequestParam(value = "parentAreaCode") String parentAreaCode,
			@RequestParam(value = "reportType") String reportType,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			String typeCode="00066";
			String scaleCode="00067";
			//查询不同类型诊所就诊费用分布
			List<ReportResultDTO>  typelist=ClinicsCostService.queryCostType(year,parentAreaCode,typeCode,month,reportType);
//			//不同规模诊所人均就诊费用分布
			List<ReportResultDTO>  scaleList=ClinicsCostService.queryCostType(year,parentAreaCode,scaleCode,month,reportType);
			rep.setTypelist(typelist);
			rep.setScalelist(scaleList);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsCostController queryCostType"+e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	/**
	 * 服务费用下级区域概括(暂不用)
	 * @param parentAreaCode 区域id
	 * @param year 年份
	 * @param code 指标code
	 * @return
	 * 
	 */
//	@RequestMapping(value = "/queryLower", method = RequestMethod.GET)
//	public @ResponseBody
//	ReportResponse queryLower(
//			@RequestParam(value = "parentAreaCode") String parentAreaCode,
//			@RequestParam(value = "year") String year,
//			HttpServletRequest request) {
//		ReportResponse rep = new ReportResponse();
//		try {
//			//服务费用下级区域概括
//			List<reportResultDTO>  list=ClinicsCostService.queryLower(year,parentAreaCode);
//			rep.setList(list);
//			rep.setCode(MessageUtil.CODE_SUCCESS);
//			rep.setMessage(MessageUtil.SUCCESS);
//		} catch (Exception e) {
//			logger.error("ClinicsCostController queryLower"+e.getMessage());
//			rep.setMessage(MessageUtil.FAILURE);
//			rep.setCode(MessageUtil.CODE_FAILURE);
//		}
//		return rep;
//	}
	
}
