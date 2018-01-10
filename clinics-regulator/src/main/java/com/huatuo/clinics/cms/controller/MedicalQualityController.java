package com.huatuo.clinics.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.response.ObjectResponse;
import com.huatuo.clinics.cms.services.report.ClinicsInfoService;


@Controller
@RequestMapping("/medicalQuality")
public class MedicalQualityController {
	@Resource
	private ClinicsInfoService clinicsInfoService;
	/**
	 * 合理用药-抗生素使用情况分析
	 * @throws Exception 
	 */
	@RequestMapping(value="antibioticUse")
	@ResponseBody
	public Object antibioticUse(@ModelAttribute RuquestBeanDTO ruquestBeanDTO) throws Exception{
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.antibioticUse(ruquestBeanDTO));
		return response;
	}
	/**
	 * 合理用药-输液占比
	 */
	@RequestMapping(value="transfusion")
	@ResponseBody
	public Object transfusion(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.transfusion(ruquestBeanDTO));
		return response;
	}
	/**
	 * 合理用药-处方总量输液占比
	 */
	@RequestMapping(value="countTransfusion")
	@ResponseBody
	Object countTransfusion(RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.countTransfusion(ruquestBeanDTO));
		return response;
	}
	/**
	 * 合理用药-输液处方抗生素占比
	 */
	@RequestMapping(value="antibioticTransfusion")
	@ResponseBody
	Object antibioticTransfusion(RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.antibioticTransfusion(ruquestBeanDTO));
		return response;
	}
	/**
	 * 合理用药-下级-各下级行政区划个体诊所抗生素使用占比
	 */
	@RequestMapping(value="roportionAntibioticUse")
	@ResponseBody
	Object roportionAntibioticUse(RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.roportionAntibioticUse(ruquestBeanDTO));
		return response;
	}
	/**
	 * 合理用药-下级-个体诊所输液处方占比
	 */
	@RequestMapping(value="transfusionPrescriptions")
	@ResponseBody
	Object transfusionPrescriptions(RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.transfusionPrescriptions(ruquestBeanDTO));
		return response;
	}
	/**
	 *  合理用药-时间趋势-抗生素使用占比变化情况
	 */
	@RequestMapping(value="antibioticTransfusionUse")
	@ResponseBody
	Object antibioticTransfusionUse(RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.antibioticTransfusionUse(ruquestBeanDTO));
		return response;
	}
	/**
	 * 合理用药-时间趋势-输液处方占比变化情况
	 */
	@RequestMapping(value="TransfusionUseCg")
	@ResponseBody
	Object TransfusionUseCg(RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.TransfusionUseCg(ruquestBeanDTO));
		return response;
	}
	/**
	 * 合理用药-情况信息总表
	 */
	@RequestMapping(value="RationalForm")
	@ResponseBody
	public Object RationalForm(@ModelAttribute RuquestBeanDTO ruquestBeanDTO){
		ObjectResponse response=new ObjectResponse();
		response.setData(clinicsInfoService.RationalForm(ruquestBeanDTO));
		return response;
	}

	
}
