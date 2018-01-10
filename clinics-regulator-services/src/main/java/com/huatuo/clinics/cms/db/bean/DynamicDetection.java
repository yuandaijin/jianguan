package com.huatuo.clinics.cms.db.bean;

public class DynamicDetection {
	
	private String id;//主键id
	private Integer number;//发生数量
	private String  name;//

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
