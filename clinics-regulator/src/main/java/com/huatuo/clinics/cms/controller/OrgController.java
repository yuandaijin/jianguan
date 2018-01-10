package com.huatuo.clinics.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.bean.CmsJgAddressAndOrgDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.clinics.cms.bean.TaPhaDictDrugAntibioticLevelPolityDTO;
import com.huatuo.clinics.cms.response.AntibioticResponse;
import com.huatuo.clinics.cms.response.BaseResponse;
import com.huatuo.clinics.cms.services.regulator.CmsJgOrgService;
import com.huatuo.clinics.cms.services.regulator.CmsJgUserinfoService;
import com.huatuo.clinics.cms.util.Page;
import com.huatuo.common.MessageUtil;

@Controller
@RequestMapping("/org")
public class OrgController {


//	@Autowired
//	private CmsJgAddressService cmsJgAddressService;
	
	@Autowired
	private CmsJgOrgService msJgOrgService;
	
	@Resource
	private CmsJgUserinfoService userinfoService;
	
	/**
	 * 增加机构
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addorg", method = RequestMethod.GET)
	public String goUrl(Model model){
		return "org/addOrg";
	}
	

	/**
	 * 机构维护
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/orgupdate", method = RequestMethod.GET)
	public String delteUsers(Model model){
		return "org/queryOrg";
	}
	
	
	/**
	 * 增加机构
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addOneOrg", method = RequestMethod.POST)
	public @ResponseBody BaseResponse addOrd(@RequestBody CmsJgAddressAndOrgDTO dto,HttpServletRequest request, Model model){
		BaseResponse rep = new BaseResponse();
		try {
			
			int  i=0;
			if("" ==dto.getId()){
				//判断机构名是否重复
				List<CmsJgOrgDTO> bean=msJgOrgService.queryOrgByName(dto.getOrgName());
				if(null != bean){
					rep.setCode(MessageUtil.CODE_ERROR);
					rep.setMessage(MessageUtil.USERNAME_EXISTS);
					return rep;
				}
				i=msJgOrgService.insertOrg(dto);//新增机构
			}else{
				//判断机构名除了本身之外是否重复
					List<CmsJgOrgDTO> list=msJgOrgService.queryOrg(dto.getOrgName(),dto.getId());
					if(null != list){
						rep.setCode(MessageUtil.CODE_ERROR);
						rep.setMessage(MessageUtil.USERNAME_EXISTS);
						return rep;
					}
				i=msJgOrgService.updateOrg(dto);//修改机构
			}
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

	

	
	/**
	 * 机构信息查询
	 * @param request
	 * @param model
	 * @param provinceCode
	 * @param cityCode
	 * @param countyCode
	 * @return
	 */
	@RequestMapping(value="/queryAllOrg", method = RequestMethod.POST)
	public String queryAllOrg(
			@RequestParam(value="pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value="pageSize",defaultValue = "1000") Integer pageSize,
		   @RequestParam(value="province") String province,
		   @RequestParam(value="city") String city,
		   @RequestParam(value="county") String county,
		   Model model,HttpServletRequest request) {
		Page<CmsJgOrgDTO> page = msJgOrgService.queryAllOrg( pageNo, pageSize,province,city,county);
		model.addAttribute("orgList", page.getList());
		model.addAttribute("current", page.getCurrentPage());
		model.addAttribute("total", page.getTotalPage());
		return "org/orgList";
	}
	
	
	
	/**
	 * 删除机构
	 */
	@RequestMapping(value="/deleteOrg", method = RequestMethod.GET)
	public @ResponseBody BaseResponse updateOrgDelFlag(
		   @RequestParam(value="id") String id,
		   HttpServletRequest request,Model model){
		BaseResponse rep = new BaseResponse();
		try {
			int i=msJgOrgService.deleteOrg(id);
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
	
	@RequestMapping(value="/updateOneOrg", method = RequestMethod.POST)
	public  String updateOneOrg(
		   @RequestParam(value="id") String id,HttpServletRequest request,
		   Model model) {
		CmsJgOrgDTO dto= msJgOrgService.queryOrgById(id);
		model.addAttribute("dto", dto);
		return "org/addOrg";
	}
	
	
	
	/**
	 * 查询所有的抗生素规则
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAntibiotic", method = RequestMethod.GET)
	public @ResponseBody
	AntibioticResponse resultAddress(
			HttpServletRequest request) {
		AntibioticResponse rep = new AntibioticResponse();
		try {
			List<TaPhaDictDrugAntibioticLevelPolityDTO> list=msJgOrgService.queryAntibiotic();
			rep.setList(list);
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}

}