package com.huatuo.clinics.cms.bean;

public class CsOutpVisitDxDTO {
	private String dxId; // 诊断id
	private String vistiId;// 就诊id
	private int vistiNo;// 就诊编号
	private String dxType;// 主要诊断（1）、次要诊断（21,22...）
	private String icdCode;// 诊断ICD编码
	private String icdName;// icd字典名称
	private String icdNameNew;// 修订后的icd名称
	private String additionCode;// 西医附加码，中医症候码
	private String additionName;// 西医附加名、中医症候名
	private String additionNameNew;// 修订后的西医附加名、中医症候名
	private String medicalSystem;// 0西医，1中医，2其他名族医

	private int icdQty;	//此种病的数量
	public String getDxId() {
		return dxId;
	}

	public void setDxId(String dxId) {
		this.dxId = dxId;
	}

	public String getVistiId() {
		return vistiId;
	}

	public void setVistiId(String vistiId) {
		this.vistiId = vistiId;
	}

	public int getVistiNo() {
		return vistiNo;
	}

	public void setVistiNo(int vistiNo) {
		this.vistiNo = vistiNo;
	}

	public String getDxType() {
		return dxType;
	}

	public void setDxType(String dxType) {
		this.dxType = dxType;
	}

	public String getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}

	public String getIcdName() {
		return icdName;
	}

	public void setIcdName(String icdName) {
		this.icdName = icdName;
	}

	public String getIcdNameNew() {
		return icdNameNew;
	}

	public void setIcdNameNew(String icdNameNew) {
		this.icdNameNew = icdNameNew;
	}

	public String getAdditionCode() {
		return additionCode;
	}

	public void setAdditionCode(String additionCode) {
		this.additionCode = additionCode;
	}

	public String getAdditionName() {
		return additionName;
	}

	public void setAdditionName(String additionName) {
		this.additionName = additionName;
	}

	public String getAdditionNameNew() {
		return additionNameNew;
	}

	public void setAdditionNameNew(String additionNameNew) {
		this.additionNameNew = additionNameNew;
	}

	public String getMedicalSystem() {
		return medicalSystem;
	}

	public void setMedicalSystem(String medicalSystem) {
		this.medicalSystem = medicalSystem;
	}

	public int getIcdQty() {
		return icdQty;
	}

	public void setIcdQty(int icdQty) {
		this.icdQty = icdQty;
	}
}