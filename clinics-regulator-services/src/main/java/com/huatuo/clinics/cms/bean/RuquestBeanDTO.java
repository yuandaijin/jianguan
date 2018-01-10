package com.huatuo.clinics.cms.bean;
/**
 * Ruquest请求参数
 *
 */
public class RuquestBeanDTO implements Cloneable    {
	private String year;
	private String month;
	private String day;
	private String areaFlag;
	private String parentAreaCode;
	private String parentAreaName;
	private String areaCode;
	private String areaNode;
	private String startTime;
	private String endTime;
	private String Type;
	private String reportType;
	private String startYear;
	private String endYear;
	private String startMonth;
	private String endMonth;
	private String startDay;
	private String endDay;
	private String orgId;  // 机构id
    public RuquestBeanDTO clone()   
    {   
        Object o=null;   
        try   
        {   
            o=super.clone();   
        }   
        catch(CloneNotSupportedException e)   
        {   
            System.out.println(e.toString());   
        }   
        return (RuquestBeanDTO)o;   
    }   
    
	public String getAreaFlag() {
		return areaFlag;
	}

	public void setAreaFlag(String areaFlag) {
		this.areaFlag = areaFlag;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getParentAreaCode() {
		return parentAreaCode;
	}

	public void setParentAreaCode(String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}

	public String getParentAreaName() {
		return parentAreaName;
	}

	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaNode() {
		return areaNode;
	}

	public void setAreaNode(String areaNode) {
		this.areaNode = areaNode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
