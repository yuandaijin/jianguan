package com.huatuo.clinics.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.response.ObjectResponse;
import com.huatuo.clinics.cms.services.report.ClinicsInfoService;

/**
 * 医疗服务规范
 * @author HT-develop
 */
@RequestMapping(value="MedicalServices")
@Controller
public class MedicalServicesController {
	@Resource
	private ClinicsInfoService clinicsInfoService;
		/**
		 *医疗服务规范- 辖区总体概况-电子病历
		 */
		@RequestMapping(value="MedicalServices")
		@ResponseBody
		public Object AreaGeneralSituation(RuquestBeanDTO ruquestBeanDTO){
			ObjectResponse response=new ObjectResponse();
			response.setData(clinicsInfoService.AreaGeneralSituation(ruquestBeanDTO));
			return response;
		}
		/**
		 *医疗服务规范- 下级区划概况
		 * @throws Exception 
		 */
		@RequestMapping(value="lowerRegion")
		@ResponseBody
		public Object  lowerRegion(RuquestBeanDTO ruquestBeanDTO) throws Exception{
			ObjectResponse response=new ObjectResponse();
			response.setData(clinicsInfoService.lowerRegion(ruquestBeanDTO));
			return response;
		}
		/**
		 * 医疗服务规范-信息总表
		 */
		@RequestMapping(value="SummaryTable")
		@ResponseBody
		public Object SummaryTable(RuquestBeanDTO ruquestBeanDTO){
			ObjectResponse response=new ObjectResponse();
			response.setData(clinicsInfoService.SummaryTable(ruquestBeanDTO));
			return response;
		}
}
