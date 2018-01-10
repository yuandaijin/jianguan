package com.huatuo.clinics.cms.comm.SessionUtils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.security.ClinicsUserDetails;

public class SessionUtils{
	
	// private static final Logger logger = LoggerFactory.getLogger(SessionUtils.class);
	
	public static void updateUserInfo(HttpServletRequest request, CmsJgUserinfoDTO userInfo) {
		CmsJgUserinfoDTO origUserInfo = getUserInfo(request);
		if (origUserInfo == null) {
			return;
		}
		if (origUserInfo.getId() == userInfo.getId()) {
			SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute(
					HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
			if (securityContext == null) {
				return;
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userInfo.getUserName(), userInfo);
			securityContext.setAuthentication(authentication);
		}
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 */
	public static CmsJgUserinfoDTO getUserInfo(HttpServletRequest request) {
		return getUserInfo(request.getSession());
	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 */
	public static CmsJgUserinfoDTO getUserInfo(HttpSession session) {
		SecurityContext securityContext = (SecurityContext) session
				.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if (securityContext == null) {
			return null;
		}
		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object credentials = authentication.getCredentials();
		if (credentials != null && credentials instanceof CmsJgUserinfoDTO) {
			return (CmsJgUserinfoDTO) credentials;
		}
		Object principal = authentication.getPrincipal();
		if (principal == null || !(principal instanceof ClinicsUserDetails)) {
			return null;
		}
		return ((ClinicsUserDetails) principal).getUserInfo();
	}
	
	public static void cleanSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<?> enums = session.getAttributeNames();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			session.removeAttribute(key);
		}
	}
	
}
