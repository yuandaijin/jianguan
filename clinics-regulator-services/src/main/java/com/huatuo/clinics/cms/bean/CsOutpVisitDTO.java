package com.huatuo.clinics.cms.bean;

import java.util.Date;
import java.util.List;

import com.huatuo.clinics.cms.services.translate.Translatable;
import com.huatuo.clinics.cms.services.translate.TranslateField;

public class CsOutpVisitDTO implements Translatable {
	private String vistiId;// 就诊id
	private String patientId;// 患者id
	private Integer vistiNo;// 就诊编号
	private Integer visitNo;// 就诊编号
	private Date visitDate;// 就诊日期
	private String ordDepId;// 开单科室id
	private String ordDepName;//开单科室名称
	private String doctorId;// 医生id
	private String doctorName;// 医生名称
	private String titlesClinical;//医生职称
	private String doctorPhone;// 医生联系方式
	private String age;// 年龄
	private String visitStatus;// 就诊状态
	private String visitType;// 访问类型
	private String orgId;// 机构id
	private String orgName;// 机构名称
	private String orgPhone;// 诊所联系方式
	private String address;//诊所地址
	private Integer ledgerSn;//  收费次数发生器,默认为0.每次产生新收费发票记录将ledger_sn加1,新收费发票记录中的ledger_sn应用此字段中新值
    private Integer chargeType;//费别，自费、医保。默认0
    private Integer responseType;//身份，双流县医保、成都市医保.默认0

	// 自定义字段
	private String patientName;// 姓名：关联患者表patient_id
	@TranslateField(fullQualifiedName = "00001", targetFieldName = "sexName")
	private String sex;// 性别
	private String sexName;// 性别
	private String birthday;//出生日期-前台展示用
	private String addr;// 住址
	private String teleNum;// 电话
	private String cardId;// 身份证号
	private float amount;// 总收费
//	private String dxTypeString;// 诊断（拼接成字符串）：诊断详情中cs_outp_visit_dx
//	private String dxTypeName;// 诊断(前端使用)
//	private String icdNameNew;// 修改后的诊断
	private String allergicDrugString;// 过敏名称（拼接成字符串）rs_empi_patient_allergic_drug
	private String beginDate;// 就诊查询开始时间
	private String endDate;// 就诊查询结束时间
	private Float weight;//体重

	// 处方单
	private List<CsOutpOrderDTO> orderDTOs;
	// 门诊诊断记录
	private List<CsOutpVisitDxDTO> dxDTOs;
	
	//电子病历
	private CsOutpEmrDTO csOutpEmrDTO;

	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrgPhone() {
		return orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public String getTitlesClinical() {
		return titlesClinical;
	}

	public void setTitlesClinical(String titlesClinical) {
		this.titlesClinical = titlesClinical;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSexName() {
		return sexName;
	}

	public String getOrdDepName() {
		return ordDepName;
	}

	public void setOrdDepName(String ordDepName) {
		this.ordDepName = ordDepName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getVistiId() {
		return vistiId;
	}

	public void setVistiId(String vistiId) {
		this.vistiId = vistiId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Integer getVistiNo() {
		if (vistiNo != null) {
			return vistiNo;
		}else {
			return visitNo;
		}
	}

	public void setVistiNo(Integer vistiNo) {
		if (vistiNo != null) {
			this.vistiNo = vistiNo;
		}else {
			this.vistiNo = getVisitNo();
		}
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getOrdDepId() {
		return ordDepId;
	}

	public void setOrdDepId(String ordDepId) {
		this.ordDepId = ordDepId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getVisitStatus() {
		return visitStatus;
	}

	public void setVisitStatus(String visitStatus) {
		this.visitStatus = visitStatus;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTeleNum() {
		return teleNum;
	}

	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getAllergicDrugString() {
		return allergicDrugString;
	}

	public void setAllergicDrugString(String allergicDrugString) {
		this.allergicDrugString = allergicDrugString;
	}

	public List<CsOutpOrderDTO> getOrderDTOs() {
		return orderDTOs;
	}

	public void setOrderDTOs(List<CsOutpOrderDTO> orderDTOs) {
		this.orderDTOs = orderDTOs;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public CsOutpEmrDTO getCsOutpEmrDTO() {
		return csOutpEmrDTO;
	}

	public void setCsOutpEmrDTO(CsOutpEmrDTO csOutpEmrDTO) {
		this.csOutpEmrDTO = csOutpEmrDTO;
	}

	public Integer getLedgerSn() {
		return ledgerSn;
	}

	public void setLedgerSn(Integer ledgerSn) {
		this.ledgerSn = ledgerSn;
	}

	public Integer getChargeType() {
		return chargeType;
	}

	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}

	public Integer getResponseType() {
		return responseType;
	}

	public void setResponseType(Integer responseType) {
		this.responseType = responseType;
	}
	public Integer getVisitNo() {
		if (vistiNo != null) {
			return vistiNo;
		}else {
			return visitNo;
		}
	}
	public void setVisitNo(Integer visitNo) {
		if (visitNo != null) {
			this.visitNo = visitNo;
		}else {
			this.visitNo = getVistiNo();
		}
	}

	public List<CsOutpVisitDxDTO> getDxDTOs() {
		return dxDTOs;
	}

	public void setDxDTOs(List<CsOutpVisitDxDTO> dxDTOs) {
		this.dxDTOs = dxDTOs;
	}
//
//	public String getDxTypeString() {
//		return dxTypeString;
//	}
//
//	public void setDxTypeString(String dxTypeString) {
//		this.dxTypeString = dxTypeString;
//	}
}