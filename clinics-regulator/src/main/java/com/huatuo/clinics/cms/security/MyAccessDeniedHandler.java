package com.huatuo.clinics.cms.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		boolean isAjax = isAjaxRequest(request);
		if (!isAjax) {
			request.setAttribute("isAjaxRequest", isAjax);
			request.setAttribute("message", accessDeniedException.getMessage());
			response.sendRedirect(request.getContextPath()
					+ "/index.jsp");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			String v = "{\"code\":\"200000\",\"message\":\"权限不足\"}";
			response.getWriter().write(v);
			response.getWriter().close();
		}

	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
}
