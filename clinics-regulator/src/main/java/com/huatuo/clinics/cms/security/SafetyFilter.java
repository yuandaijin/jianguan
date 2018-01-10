package com.huatuo.clinics.cms.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huatuo.common.Utils;


/**
 * @author 作者叶林森
 * @version 创建时间：2015-8-03 上午09:56:06 类说明
 */

public class SafetyFilter implements Filter {

	private static List<String> unsafeChars = new ArrayList<String>();
	private static final Logger logger = LoggerFactory
			.getLogger(SafetyFilter.class);

	public void init(FilterConfig conf) throws ServletException {
		unsafeChars = Arrays.asList(conf.getInitParameter("unsafeChars").split(","));
	}

	public void destroy() {
		logger.info("SafetyFilter destory.");
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		Map<String, String[]> params = request.getParameterMap();
		Set<String> keys = params.keySet();
		for (String key : keys) {
			String value = request.getParameter(key);
			for (String word : unsafeChars) {
				if (word.equalsIgnoreCase(key) || word.equalsIgnoreCase(value) || 
						value.toLowerCase().contains(word.toLowerCase())) {
					doWarn(request, response, key, value);
					return;
				}
			}
		}
		fc.doFilter(req, res);
	}

	private void doWarn(HttpServletRequest request, HttpServletResponse response, String key, String value)
			throws IOException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = df.format(new Date());
		logger.info("WARNING:attack found!|ip:" + Utils.getIpAddr(request) + "|time:" + dateString + "|key:" + key
				+ "|value:" + value);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String warning = "<html><body><div style='text-align:left;'><br><b>UNLEGAL REQUEST!</b><br><br>Your IP address has been recorded!</div></body></html>";
		pw.write(warning);
		pw.flush();
		pw.close();
	}

}
