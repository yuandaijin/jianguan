package com.huatuo.clinics.cms.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.comm.DateUtil;
import com.huatuo.clinics.cms.response.ObjectResponse;
import com.huatuo.clinics.cms.response.ReportResponse;
import com.huatuo.clinics.cms.services.report.ClinicsInfoService;
import com.huatuo.common.MessageUtil;

@Controller
@RequestMapping("/overview")
public class ClinicsInfoController {
	

	@Resource
	private ClinicsInfoService clinicsInfoService;
	private static final Logger logger = LoggerFactory.getLogger(ClinicsInfoController.class);
	/**
	 * 诊所医疗能力覆盖情况
	 * @param parentAreaCode 区域id
	 * @return
	 * 
	 */
	@RequestMapping(value = "/clinicsQty", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse clinicsQty(
			@RequestParam(value = "parentAreaCode") String parentAreaCode) {
		ReportResponse rep = new ReportResponse();
		try {
			List<String> str=DateUtil.getNextDay();
			String year=str.get(0);
			String month=str.get(1);
			String day=str.get(2);
			//查询截止当前时间区域下的各个镇的诊所数量
			List<ReportResultDTO>  dtoList=clinicsInfoService.queryNameAndQty(year,month,day,parentAreaCode);
			rep.setList(dtoList);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsInfoController clinicsQty", e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 居民多发疾病综合情况
	 * @param parentAreaCode 区域id
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryIncidence", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryIncidence(
			@RequestParam(value = "parentAreaCode") String parentAreaCode
			,HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			List<String> str=DateUtil.getNextDay();
			String year=str.get(0);
			String month=str.get(1);
			String day=str.get(2);
			//查询截止当前时间区域下前十的病种
			List<ReportResultDTO>  dtoList=clinicsInfoService.queryIncidence(year,month,day,parentAreaCode);
			//查询所有病种的总数量
			String sum=clinicsInfoService.queryIncidenceSum(year,month,day,parentAreaCode);
			rep.setSum(sum);
			rep.setList(dtoList);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsInfoController queryIncidence", e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 诊所评级综合情况
	 * @param parentAreaCode 区域id
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryGrade", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryGrade(
			@RequestParam(value = "parentAreaCode") String parentAreaCode
			,HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			List<String> str=DateUtil.getNextDay();
			String year=str.get(0);
			String month=str.get(1);
//			String day=str.get(2);
			//查询截止当前时间区域下诊所评级情况
			List<ReportResultDTO>  dtoList=clinicsInfoService.queryGrade(year,month,parentAreaCode);//当月的上月数据
			rep.setMonth(month);
			rep.setYear(year);
			rep.setList(dtoList);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsInfoController queryGrade", e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 诊所运营综合情况
	 * @param parentAreaCode 区域id
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryOperate", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryOperate(
			@RequestParam(value = "parentAreaCode") String parentAreaCode
			,HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			List<String> str=DateUtil.getNextDay();
			String year=str.get(0);
			String month=str.get(1);
			String day=str.get(2);
			String totalCode="00049-0001";
			String efficiencyCode="00049-0002";
			//查询截止当前时间区域下各个镇的门诊总量
			List<ReportResultDTO>  dtoList=clinicsInfoService.queryOperate(year,month,day,parentAreaCode,totalCode,true);
			//查询截止当前时间区域下各个镇的服务效率
			List<ReportResultDTO>  list=clinicsInfoService.queryOperate(year,month,day,parentAreaCode,efficiencyCode,true);
			rep.setTotallist(dtoList);
			rep.setEfficiencylist(list);
		} catch (Exception e) {
			logger.error("ClinicsInfoController queryOperate", e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	
	
	/**
	 * 诊所医疗质量情况(暂无数据)
	 * @param parentAreaCode 区域id
	 * @return
	 * 
	 */
	@RequestMapping(value = "/queryQuality", method = RequestMethod.GET)
	public @ResponseBody
	ReportResponse queryQuality(
			@RequestParam(value = "parentAreaCode") String parentAreaCode
			,HttpServletRequest request) {
		ReportResponse rep = new ReportResponse();
		try {
			List<String> str=DateUtil.getNextDay();
			String year=str.get(0);
			String month=str.get(1);
			String day=str.get(2);
			String code="00054";
			String type="00054-0001";
			//资质监管许可证到期数量查询
			String  daoqi=clinicsInfoService.queryQuality(year,month,day,parentAreaCode,code,type);
			//电子处方合格率
			code="00055";
			type="00055-0001";
			String  fuwu=clinicsInfoService.queryQuality(year,month,day,parentAreaCode,code,type);
			//电子病历合格率
			code="00055";
			type="00055-0002";
			String  heli=clinicsInfoService.queryQuality(year,month,day,parentAreaCode,code,type);
			//抗生素使用占比
			code="00046";
			type="00046-0001";
			String  kss=clinicsInfoService.queryQuality(year,month,day,parentAreaCode,code,type);
			//输液处方占比
			code="00046";
			type="00046-0002";
			String  shuye=clinicsInfoService.queryQuality(year,month,day,parentAreaCode,code,type);
			//医疗事故统计
			String shigu="100";
			//服务满意度
			String manyi="100";
			List<String> list=new ArrayList<String>();
			list.add(daoqi);
			list.add(fuwu);
			list.add(heli);
			list.add(kss);
			list.add(shuye);
			list.add(shigu);
			list.add(manyi);
			rep.setStr(list);
			rep.setCode(MessageUtil.CODE_SUCCESS);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			logger.error("ClinicsInfoController queryQuality", e.getMessage());
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	/**
	 * 诊所医疗质量情况概览
	 */
	@RequestMapping(value=" OverviewClinicMedicalQuality")
	@ResponseBody
	public Object OverviewClinicMedicalQuality(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.OverviewClinicMedicalQuality(ruquestBeanDTO));
		return response;
	}
	/**
	 * 诊所医疗质量情况概览
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value=" OverviewClinicMedicalQualityIndex")
	@ResponseBody
	public Object OverviewClinicMedicalQualityIndex(@ModelAttribute RuquestBeanDTO ruquestBeanDTO ){
		Date date=new Date();
		ruquestBeanDTO.setYear(Calendar.getInstance().get(Calendar.YEAR)+"");
		ruquestBeanDTO.setMonth(date.getMonth()+1+"");
		ruquestBeanDTO.setAreaFlag("1");
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.OverviewClinicMedicalQuality(ruquestBeanDTO));
		return response;
	}
	/**
	 * 诊所医疗质量情况概览 表单
	 */
	@RequestMapping(value=" ListOverviewClinicMedicalQuality")
	@ResponseBody
	public Object ListOverviewClinicMedicalQuality(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.ListOverviewClinicMedicalQuality(ruquestBeanDTO));
		return response;
	}
}
