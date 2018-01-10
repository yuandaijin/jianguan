package com.huatuo.clinics.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.comm.SessionUtils.SessionUtils;
import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;
import com.huatuo.clinics.cms.services.regulator.CmsJgUserinfoService;

@Controller
@RequestMapping("/web/person")
public class PersionController {

	@Resource
	private CmsJgUserinfoService userinfoService;
	
	/**
	 * 登陆初始页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model){
		CmsJgUserinfoDTO person = (CmsJgUserinfoDTO) SessionUtils.getUserInfo(request);
		List<CmsJgMenuInfoDTO> list = userinfoService.getMenus(person.getId(), 0, true);
		model.addAttribute("person", person);
		model.addAttribute("list", list);
		return "person/operate";
	}
	
}