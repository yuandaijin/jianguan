package com.huatuo.clinics.cms.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.response.ObjectResponse;
import com.huatuo.clinics.cms.services.report.ClinicsInfoService;

/**
 * 资质监管
 * @author HT-develop
 *
 */
@RequestMapping(value="QualitySupervision")
@Controller 
public class QualitySupervisionController {
	@Resource
	private ClinicsInfoService clinicsInfoService;
	
	/** 
	 * 到期
	 */
	@RequestMapping(value="expire")
	@ResponseBody
	public Object expire(RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.expire(ruquestBeanDTO));
		return response;
	}
	/** 
	 * 资质监管-到期
	 */
	@RequestMapping(value="listTimeClinics")
	@ResponseBody
	public Object listTimeClinics(RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.set(Integer.parseInt(ruquestBeanDTO.getYear()), ruquestBeanDTO.getMonth()==null?1:Integer.parseInt(ruquestBeanDTO.getMonth())-1, ruquestBeanDTO.getDay()==null?1:Integer.parseInt(ruquestBeanDTO.getDay())-1);
		ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
		calendar.set(Integer.parseInt(ruquestBeanDTO.getYear()), ruquestBeanDTO.getMonth()==null?1:Integer.parseInt(ruquestBeanDTO.getMonth())+5,  ruquestBeanDTO.getDay()==null?1:Integer.parseInt(ruquestBeanDTO.getDay())-1);
		ruquestBeanDTO.setEndTime(sdf.format(calendar.getTime()));
		response.setData(clinicsInfoService.listTimeClinics(ruquestBeanDTO));
		return response;
	}
}
