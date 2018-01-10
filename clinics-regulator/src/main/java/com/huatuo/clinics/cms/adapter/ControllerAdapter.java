package com.huatuo.clinics.cms.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.comm.SessionUtils.SessionUtils;

/**
 * controller adapter
 */
public class ControllerAdapter extends HandlerInterceptorAdapter {

	//private static final Logger logger = LoggerFactory.getLogger(ControllerAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String path = request.getServletPath();
		String contextPath = request.getContextPath();
		CmsJgUserinfoDTO userInfo = SessionUtils.getUserInfo(request);
		if(userInfo==null){
			if(!"/user".equals(path) && !"/user/login".equals(path) && !"/user/login/out".equals(path)){
				String loginPath = "/user";
				response.sendRedirect(contextPath+loginPath);
				return false;
			}
		}else{
			if(("/".equals(path) || "/index.jsp".equals(path) 
				||  "/user".equals(path))){
				response.sendRedirect(contextPath + "/web/person");
			}
		}
		return true;
	}
}
