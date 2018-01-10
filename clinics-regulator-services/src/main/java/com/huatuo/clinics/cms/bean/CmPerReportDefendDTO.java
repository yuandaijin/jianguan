package com.huatuo.clinics.cms.bean;

public class CmPerReportDefendDTO {
	
    private String id;//主键id
    private String type;//报表类型
    private String orgId;//为null是默认模板，有值为机构模板
    private String print;//是否套打(0：选中，1：未选中)
    private String printName;//套打报表号
    private String notPrint;//是否非套打(0：选中，1：未选中)
    private String notPrintName;//非套打报表号
    private String memo;//报表描述
    private String validFlg;//有效标示（0：无效，1：有效）
    private String defaultType;//默认套打还是非套打
    private String actualName;//实际报表号
    
    
    
    
	public String getActualName() {
		return actualName;
	}
	public void setActualName(String actualName) {
		this.actualName = actualName;
	}
	public String getDefaultType() {
		return defaultType;
	}
	public void setDefaultType(String defaultType) {
		this.defaultType = defaultType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}
	public String getPrintName() {
		return printName;
	}
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	public String getNotPrint() {
		return notPrint;
	}
	public void setNotPrint(String notPrint) {
		this.notPrint = notPrint;
	}
	public String getNotPrintName() {
		return notPrintName;
	}
	public void setNotPrintName(String notPrintName) {
		this.notPrintName = notPrintName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getValidFlg() {
		return validFlg;
	}
	public void setValidFlg(String validFlg) {
		this.validFlg = validFlg;
	}
    
    
    
    
}