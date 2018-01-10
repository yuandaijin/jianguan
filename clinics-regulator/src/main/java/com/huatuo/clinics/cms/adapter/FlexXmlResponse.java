package com.huatuo.clinics.cms.adapter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * FLEX配置文件
 * @author caohu
 *
 */
@XmlRootElement(name="config")
public class FlexXmlResponse {

	private String host;
	private String application;
	private String applicationStream;
	private String rootJSPURL;
	private String rootURL;
	private String rootContentURL;
	private String printURL;
	private String performance;
	private String resourcePath;
	private String authorizeURL;
	
	public String getAuthorizeURL() {
		return authorizeURL;
	}
	public void setAuthorizeURL(String authorizeURL) {
		this.authorizeURL = authorizeURL;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getApplicationStream() {
		return applicationStream;
	}
	public void setApplicationStream(String applicationStream) {
		this.applicationStream = applicationStream;
	}
	public String getRootJSPURL() {
		return rootJSPURL;
	}
	public void setRootJSPURL(String rootJSPURL) {
		this.rootJSPURL = rootJSPURL;
	}
	public String getRootURL() {
		return rootURL;
	}
	public void setRootURL(String rootURL) {
		this.rootURL = rootURL;
	}
	public String getRootContentURL() {
		return rootContentURL;
	}
	public void setRootContentURL(String rootContentURL) {
		this.rootContentURL = rootContentURL;
	}
	public String getPrintURL() {
		return printURL;
	}
	public void setPrintURL(String printURL) {
		this.printURL = printURL;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
}
