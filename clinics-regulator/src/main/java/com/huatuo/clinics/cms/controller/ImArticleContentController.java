package com.huatuo.clinics.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.comm.SessionUtils.SessionUtils;
import com.huatuo.clinics.cms.response.ImArticleContentResponse;
import com.huatuo.clinics.cms.services.article.ImArticleChannelService;
import com.huatuo.clinics.cms.services.article.ImArticleColumnService;
import com.huatuo.clinics.cms.services.article.ImArticleContentService;
import com.huatuo.common.MessageUtil;
import com.huatuo.common.Utils;
import com.huatuo.db.bean.ImArticleChannel;
import com.huatuo.db.bean.ImArticleColumn;
import com.huatuo.db.bean.ImArticleContent;

/**
 * 咨询
 * @author Administrator
 *
 */
@RequestMapping(value="/imArticleContent")
@Controller
public class ImArticleContentController {
	@Autowired
	private ImArticleColumnService columnService;
	@Autowired
	private ImArticleContentService contentService;
	@Autowired
	private ImArticleChannelService channelService;
	
	/**
	 * 获得栏目下面咨询列表
	 * @param request
	 * @param columnId 栏目id
	 * @return
	 */
	@RequestMapping(value="/contentList",method=RequestMethod.GET)
	@ResponseBody
	public Object contentList(HttpServletRequest request,@RequestParam String columnId){
		ImArticleContentResponse resp = new ImArticleContentResponse();
		List<ImArticleContent> list = contentService.selectByColumnId(columnId);
		resp.setContents(list);
		resp.setCode(MessageUtil.CODE_SUCCESS);
		resp.setMessage(MessageUtil.SUCCESS);
		return resp;
	}
	
	/**
	 * 通过id获得咨询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getById",method=RequestMethod.GET)
	@ResponseBody
	public Object getById(HttpServletRequest request,@RequestParam String contentId){
		ImArticleContentResponse resp = new ImArticleContentResponse();
		ImArticleContent content = contentService.selectById(contentId);
		resp.setContent(content);
		resp.setCode(MessageUtil.CODE_SUCCESS);
		resp.setMessage(MessageUtil.SUCCESS);
		return resp;
	}
	
	/**
	 * 新增/修改咨询
	 * @param request
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/saveOrUpdateContent",method=RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdateContent(HttpServletRequest request,@RequestBody ImArticleContent content){
		ImArticleContentResponse resp = new ImArticleContentResponse();
		
		CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
		//用户id
		String createUserId = userInfo.getId()+"";
		int row = 0;
		if(StringUtils.isBlank(content.getContentId())){//新增
			content.setCreateUserId(createUserId);
			content.setDelFlag(1);
			content.setIsRelease(0);
			row = contentService.insert(content);
		}else{//修改
			row = contentService.update(content);
		}
		if(row>0){
			resp.setCode(MessageUtil.CODE_SUCCESS);
			resp.setMessage(MessageUtil.SUCCESS);
		}else{
			resp.setCode(MessageUtil.CODE_FAILURE);
			resp.setMessage(MessageUtil.FAILURE);
		}
		
		return resp;
	}
	
	/**
	 * 发布咨询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/releaseById",method=RequestMethod.GET)
	@ResponseBody
	public Object releaseById(HttpServletRequest request,@RequestParam String contentId){
		ImArticleContentResponse resp = new ImArticleContentResponse();
		
		CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
		//用户id
		String userId = userInfo.getId()+"";
		String orgId = userInfo.getCompany();
//		String orgName = userInfo.getOrgName();
		ImArticleContent content = new ImArticleContent();
		content.setContentId(contentId);
		content.setReleaseUser(userId);
		content.setIsRelease(1);
//		content.setReleaseOrg(orgName);
		content.setReleaseOrgId(orgId);
		content.setReleaseTime(Utils.getNowTime());
		int update = contentService.update(content);
		if(update>0){
			resp.setCode(MessageUtil.CODE_SUCCESS);
			resp.setMessage(MessageUtil.SUCCESS);
		}else{
			resp.setCode(MessageUtil.CODE_FAILURE);
			resp.setMessage(MessageUtil.FAILURE);
		}
		return resp;
	}
	
	/**
	 * 获得cms栏目列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/selectColumn",method=RequestMethod.GET)
	@ResponseBody
	public Object selectColumn(HttpServletRequest request){
		ImArticleContentResponse resp = new ImArticleContentResponse();
		List<ImArticleColumn> columns = columnService.getColumnsByType(1);
		resp.setColumns(columns);
		resp.setCode(MessageUtil.CODE_SUCCESS);
		resp.setMessage(MessageUtil.SUCCESS);
		return resp;
	}
	
	/**
	 * 获得栏目下面频道列表
	 * @param request
	 * @param columnId 栏目id
	 * @return
	 */
	@RequestMapping(value="/selectChannel",method=RequestMethod.GET)
	@ResponseBody
	public Object selectChannel(HttpServletRequest request,@RequestParam String columnId){
		ImArticleContentResponse resp = new ImArticleContentResponse();
		List<ImArticleChannel> channels = channelService.seleteByColumnId(columnId);
		resp.setChannels(channels);
		resp.setCode(MessageUtil.CODE_SUCCESS);
		resp.setMessage(MessageUtil.SUCCESS);
		return resp;
	}
}
