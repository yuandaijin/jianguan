package com.huatuo.clinics.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.CmsJgDisputeDTO;
import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.clinics.cms.bean.DisputeRepostDTO;
import com.huatuo.clinics.cms.response.DisputeResponse;
import com.huatuo.clinics.cms.response.MedicalDisputeResponse;
import com.huatuo.clinics.cms.services.regulator.CmsJgDisputeService;
import com.huatuo.clinics.cms.services.regulator.CmsJgDistrictService;
import com.huatuo.common.MessageUtil;
/**
 * 医疗争议报表统计
 * @author ydj
 *
 */
@Controller
@RequestMapping("/disputeReport")
public class MedicalDisputeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClinicsDisputeController.class);

	@Resource
	private CmsJgDisputeService disputeService;
	
	@Resource
	private CmsJgDistrictService cmsJgDistrictService;
	
	/**
	 * 医疗争议数量统计表
	 * @param reportType  报表类型
	 * @param year   开始年份
	 * @param year2      结束年份
	 * @param month  开始月份
	 * @param month2   结束月份
	 * @param day    日
	 * @param address 地区
	 * @return
	 */
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public @ResponseBody MedicalDisputeResponse query(
		   @RequestParam(value="reportType",required=true)String reportType,
		   @RequestParam(value="year",required=true)String year,
		   @RequestParam(value="year2",required=true)String year2, 
		   @RequestParam(value="month",required=true)String month,
		   @RequestParam(value="month2",required=true)String month2,
		   @RequestParam(value="day",required=true)String day,
		   @RequestParam(value="address",required=true)String address,
			HttpServletRequest request, Model model){
		MedicalDisputeResponse rep= new MedicalDisputeResponse();
		try {
			//返回区域下面的所有的镇
			List<CmsJgDistrictDTO> addList = cmsJgDistrictService.getDistrictByParent(Integer.valueOf(address));
			rep.setAddList(addList);
			//统计数据查询
			String status="1";//查询已鉴定
			List<DisputeRepostDTO> certified = disputeService.queryMedicalDisputeNumber(
					reportType, year, year2, month, month2, day, address,status);
			status="0";//查询未鉴定
			List<DisputeRepostDTO> unidentified = disputeService.queryMedicalDisputeNumber(
					reportType, year, year2, month, month2, day, address,status);
			if(null != certified ){
				rep.setCertified(certified);
			}
			if(null != unidentified ){
				rep.setUnidentified(unidentified);
			}
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("MedicalDisputeController query"+ e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	
	/**
	 * 医疗争议详情
	 * @param reportType  报表类型
	 * @param year   开始年份
	 * @param year2      结束年份
	 * @param month  开始月份
	 * @param month2   结束月份
	 * @param day    日
	 * @param address 地区
	 * @return
	 */
	@RequestMapping(value = "/queryDisputesByReport", method = RequestMethod.GET)
	public @ResponseBody DisputeResponse queryDisputesByReport(
			@RequestParam(value="reportType",required=true)String reportType,
			   @RequestParam(value="year",required=true)String year,
			   @RequestParam(value="year2",required=true)String year2, 
			   @RequestParam(value="month",required=true)String month,
			   @RequestParam(value="month2",required=true)String month2,
			   @RequestParam(value="day",required=true)String day,
			   @RequestParam(value="address",required=true)String address,
			HttpServletRequest request) {
		DisputeResponse rep = new DisputeResponse();
		try {
			List<CmsJgDisputeDTO> list = disputeService.queryDisputesByReport(
					reportType, year, year2, month, month2, day, address);
			rep.setList(list);
		} catch (Exception e) {
			logger.error("MedicalDisputeController queryDisputesByReport"+ e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
}