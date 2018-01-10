package com.huatuo.clinics.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.CmPerReportDefendDTO;
import com.huatuo.clinics.cms.response.BaseResponse;
import com.huatuo.clinics.cms.services.regulator.CmsJgUserinfoService;
import com.huatuo.common.MessageUtil;

@Controller
@RequestMapping("/report")
public class ReportOrgController {

	
	@Resource
	private CmsJgUserinfoService userinfoService;
	
	
	
	/**
	 * 报表维护
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report(Model model){
		return "report/report";
	}
	
	/**
	 * 报表维护
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/interface", method = RequestMethod.GET)
	public String queryInterface(Model model){
		return "report/interfaceReport";
	}
	
	
	/**
	 * 模板维护
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/template", method = RequestMethod.GET)
	public String queryTemplate(Model model){
		return "report/template";
	}
	
	
	/**
	 * 查询默认模板
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryReport", method = RequestMethod.GET)
	public String queryUser(
		   HttpServletRequest request, Model model){
		List<CmPerReportDefendDTO> list = userinfoService.getReportAll();
			model.addAttribute("list", list);
		return "menu/reportList";
	}
	
	
	/**
	 * 删除模板
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteReport", method = RequestMethod.GET)
	public @ResponseBody BaseResponse deleteReport(
		   @RequestParam(value="id") String id,
		   HttpServletRequest request,Model model){
		BaseResponse rep = new BaseResponse();
		try {
			int i=userinfoService.deleteReport(id);
			if(i<=0){
				rep.setCode(MessageUtil.CODE_ERROR);
			}
			rep.setMessage(MessageUtil.SUCCESS);
			rep.setCode(MessageUtil.CODE_SUCCESS);
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
			return rep;
		}
		return rep;
	}
	
	
	/**
	 * 保存模板
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveReport", method = RequestMethod.POST)
	public @ResponseBody BaseResponse saveReport(@RequestBody CmPerReportDefendDTO dto,HttpServletRequest request, Model model){
		BaseResponse rep = new BaseResponse();
		try {
			int i=userinfoService.saveReport(dto);//保存模板
			if(i > 0){
				rep.setMessage(MessageUtil.SUCCESS);
				rep.setCode(MessageUtil.CODE_SUCCESS);
			}
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
			return rep;
		}
		return rep;
	}
	

}