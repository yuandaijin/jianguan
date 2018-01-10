package com.huatuo.clinics.cms.exception;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huatuo.clinics.cms.comm.MessageUtil;
import com.huatuo.clinics.cms.response.BaseResponse;
@ControllerAdvice 
public class GlobalExceptionController   {   
	private static Logger errorLog = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleException(Exception ex, HttpServletRequest request,
			HttpServletResponse response) {
		BaseResponse cp = new BaseResponse();
		cp.setCode(MessageUtil.CODE_FAILURE);
		cp.setMessage(MessageUtil.FAILURE);
		writeLog(ex, request);
		return cp;
	}
	private void writeLog(Exception ex, HttpServletRequest request) {
		String url = MessageFormat.format("Exception :{0}?{1}", request.getRequestURL(),
				request.getQueryString());
		errorLog.info(url+ex.getMessage(),ex);
	}
}  
