package com.huatuo.clinics.cms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.response.ObjectResponse;
import com.huatuo.clinics.cms.services.report.ClinicsInfoService;

/**
 * 诊所资源
 *
 */
@Controller
@RequestMapping(value="ClinicResources")
public class ClinicResourcesController {
	
	@Resource
	private ClinicsInfoService clinicsInfoService;
	/**
	 * 覆盖情况
	 */
	@RequestMapping(value="CoverageJson")
	@ResponseBody
	public Object CoverageJson(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.CoverageJson(ruquestBeanDTO));
		return response;
	}
	/**
	 * 诊所数量
	 */
	@RequestMapping(value="ClinicQty")
	@ResponseBody
	public Object ClinicQtyJson(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.set(Integer.parseInt(ruquestBeanDTO.getYear()), ruquestBeanDTO.getMonth()==null?12:Integer.parseInt(ruquestBeanDTO.getMonth()), 0);
		ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.ClinicQtyJson(ruquestBeanDTO));
		return response;
	}
	/**
	 * 诊所类型
	 */
	@RequestMapping(value="ClinicType")
	@ResponseBody
	public Object ClinicTypeJson(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.set(Integer.parseInt(ruquestBeanDTO.getYear()), ruquestBeanDTO.getMonth()==null?12:Integer.parseInt(ruquestBeanDTO.getMonth()), 0);
		ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.ClinicTypeJson(ruquestBeanDTO));
		return response;
	}
	/**
	 * 行政区划
	 */
	@RequestMapping(value="administrative")
	@ResponseBody
	public Object administrative(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.set(Integer.parseInt(ruquestBeanDTO.getYear()), ruquestBeanDTO.getMonth()==null?1:Integer.parseInt(ruquestBeanDTO.getMonth()), 0);
		ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
		ruquestBeanDTO.setYear(null);
		ruquestBeanDTO.setMonth(null);
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.administrativeJson(ruquestBeanDTO));
		return response;
	}
	/** 
	 * 信息总表
	 */
	@RequestMapping(value="Information")
	@ResponseBody
	public Object Information(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.set(Integer.parseInt(ruquestBeanDTO.getYear()), ruquestBeanDTO.getMonth()==null?12:Integer.parseInt(ruquestBeanDTO.getMonth()), 0);
		ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.Information(ruquestBeanDTO));
		return response;
	}
	/**
	 * 时间趋势
	 */
	@RequestMapping(value=" timeseries")
	@ResponseBody
	public Object timeseries(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.timeseries(ruquestBeanDTO));
		return response;
	}
	/**
	 *   辖区总体概况-个体诊所人员岗位分布
	 * @throws ParseException 
	 */
	@RequestMapping(value=" areaPopulationStation")
	@ResponseBody
	public Object areaPopulationStation(@ModelAttribute RuquestBeanDTO ruquestBeanDTO) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.set(Integer.parseInt(ruquestBeanDTO.getYear()), ruquestBeanDTO.getMonth()==null?12:Integer.parseInt(ruquestBeanDTO.getMonth()), 0);
		ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.areaPopulationStation(ruquestBeanDTO));
		return response;
	}
	/**
	 *   辖区总体概况-个体诊所医生职称分布
	 */
	@RequestMapping(value=" areaDoctorTitle")
	@ResponseBody
	public Object areaDoctorTitle (@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.set(Integer.parseInt(ruquestBeanDTO.getYear()), ruquestBeanDTO.getMonth()==null?1:Integer.parseInt(ruquestBeanDTO.getMonth()), 0);
		ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.areaDoctorTitle(ruquestBeanDTO));
		return response;
	}
	/**
	 * 下级区域规划-人员岗位分布
	 */
	@RequestMapping(value="PositionDistribution")
	@ResponseBody
	public Object PositionDistribution (@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.PositionDistribution(ruquestBeanDTO));
		return response;
	}
	/**
	 * 下级区域规划-医生职称分布
	 */
	@RequestMapping(value="DoctorTitle")
	@ResponseBody
	public Object DoctorTitle (@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.DoctorTitle(ruquestBeanDTO));
		return response;
	}
	/**
	 * 医疗服务能力分布概况-人员数量的平均分布
	 */
	@RequestMapping(value="headcountAvg")
	@ResponseBody
	public Object headcountAvg (@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.headcountAvg(ruquestBeanDTO));
		return response;
	}
	
	/**
	 * 医疗服务能力分布概况-医生职称的平均分布
	 */
	@RequestMapping(value="doctorAbility")
	@ResponseBody
	public Object doctorAbility (@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.doctorAbility(ruquestBeanDTO));
		return response;
	}
	
	/**
	 * 医疗服务能力分布概况-个体诊所人员构成信息总表
	 */
	@RequestMapping(value="PersonnelForm")
	@ResponseBody
	public Object PersonnelForm (@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.PersonnelForm(ruquestBeanDTO));
		return response;
	}
	/**
	 * 时间趋势-个体诊所人员构成概况分析
	 */
	@RequestMapping(value="timeseriesPerson")
	@ResponseBody
	public Object timeseriesPerson (@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.timeseriesPerson(ruquestBeanDTO));
		return response;
	}
	
}
