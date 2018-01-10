package com.huatuo.clinics.cms.bean;

public class ReportResult2DTO extends ReportResultDTO implements Cloneable{
	private String Date;
    public ReportResult2DTO clone()   
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
        return (ReportResult2DTO)o;   
    }   
    
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
	
}
