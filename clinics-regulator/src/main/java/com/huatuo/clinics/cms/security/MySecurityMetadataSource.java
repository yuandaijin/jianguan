package com.huatuo.clinics.cms.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;
import com.huatuo.clinics.cms.db.mapper.CmsJgMenuinfoMapper;

/**
 * 加载资源与权限的对应关系
 * */
@Service
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private CmsJgMenuinfoMapper cmsJgMenuinfoMapper ;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	/**
	 * 加载所有资源与权限的关系
	 */
	@PostConstruct
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<CmsJgMenuInfoDTO> menus = cmsJgMenuinfoMapper.selectCmsMenuInfos();
			for (CmsJgMenuInfoDTO m : menus) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// TODO:ZZQ 通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
				ConfigAttribute configAttribute = new SecurityConfig(m.getAuthority());
				configAttributes.add(configAttribute);
				resourceMap.put(m.getMenuUrl(), configAttributes);
			}
		}
	}
	//返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if(resourceMap == null) {
			loadResourceDefine();
		}
		if(requestUrl.indexOf("?")>-1){
			requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		}
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
		return configAttributes;
	}
}