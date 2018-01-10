package com.huatuo.clinics.cms.adapter;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.comm.SessionUtils.SessionUtils;
import com.huatuo.clinics.cms.services.regulator.CmsJgUserinfoService;

/**
 * session失效监听
 * @author ydj
 * 
 */
@SuppressWarnings("rawtypes")
public class SessionListener implements HttpSessionListener {
	// 参数
	ServletContext sc;
	ArrayList list = new ArrayList();

	// 新建一个session时触发此操作
	public void sessionCreated(HttpSessionEvent se) {
		sc = se.getSession().getServletContext();
		System.out.println("新建一个session");
	}

	// 销毁一个session时触发此操作
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session=se.getSession();
		CmsJgUserinfoDTO  user= SessionUtils.getUserInfo(session);
		if(null != user){
			//记录用户退出的时间
			Date date=new Date();
			user.setEndDate(date);
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
	        if (context != null) {
	        	//获取user服务
	        	CmsJgUserinfoService service = (CmsJgUserinfoService) context.getBean(CmsJgUserinfoService.class);
	        	int i=service.updateUserDate(user);
	    		if(i > 0){
	    			System.out.println("session已经失效，用户退出时间保存成功");
	    		}
	        }else{
	        	System.out.println("session销毁成功");
	        }
		}
	}

	
	
	
}  