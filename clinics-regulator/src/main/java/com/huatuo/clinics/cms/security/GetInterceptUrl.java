package com.huatuo.clinics.cms.security;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 读取xml文件 security-config.xml 读取配置的拦截url,封装给过滤器处理
 * 单例模式实现
 * @author ys
 *
 */
public class GetInterceptUrl {

	private static GetInterceptUrl getInterceptUrl;
	
	private List<Map<String,String>> list = new ArrayList<Map<String, String>>();
	
	public List<Map<String, String>> getList() {
		return list;
	}

	private GetInterceptUrl(HttpServletRequest request) {
		getList(request);
	}
	
	private void getList(HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("/");
		filePath += "/WEB-INF/spring/security-config.xml";
		if("\\".equals(File.separator)){   
			filePath = filePath.replace("/", "\\");
		}
	    //linux下
		if("/".equals(File.separator)){   
			filePath = filePath.replace("\\", "/");
		}
		
		File file = new File(filePath);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(file);
			Element root = doc.getRootElement();
			Element httpele = root.element("http");
			@SuppressWarnings("unchecked")
			List<Element> nodes = httpele.elements("intercept-url");
			Map<String, String> map = null;
			for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {      
			      Element elm = it.next();
			      map = new HashMap<String, String>();
			      map.put("pattern", elm.attributeValue("pattern"));
			      map.put("access", elm.attributeValue("access"));
			      list.add(map);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	
	}

	public static GetInterceptUrl getInstance(HttpServletRequest request) {
		if(getInterceptUrl == null){
			  getInterceptUrl = new GetInterceptUrl(request);
		}
		return getInterceptUrl;
	}

}