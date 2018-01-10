package com.huatuo.clinics.cms.bean;

import java.util.Date;

import com.huatuo.clinics.cms.services.translate.Translatable;
import com.huatuo.clinics.cms.services.translate.TranslateField;

public class CsOutpEmrDTO implements Translatable{
    private String id;
    private String visitId;
    private String patientId;
    private String vistiNo;
    private Integer times;
    private String name;
    private String age;
    @TranslateField(fullQualifiedName = "00001", targetFieldName = "sexName")
    private String sex;
    private String sexName;
    private String marry;
    private String birthPlace;
    private Long addressId;
    private String occupationId;
    private String relateTel;
    private Integer socialIdType;
    private String socialId;
    private Date createTime;
    private String createDoctor;
    private String chiefComplaint;
    private String allergicHistory;
    private String presentIllness;
    private String personalIllness;
    private String pastIllness;
    private String suggestion;
    private Long auditDoctor;
    private String auditTime;
    private Integer status;
    private String bodyExam;
    private String diagnoses;
    private Float height;
    private Float weight;
    private String familyIllness;
    private String assistExam;
    private String treatSuggetion;
    
    private String addr;
    
    public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	//出生日期
    private Date birthDay;
    
    private String emrname;
    private String emrtype;
    
	public String getEmrname() {
		return emrname;
	}
	public void setEmrname(String emrname) {
		this.emrname = emrname;
	}
	public String getEmrtype() {
		return emrtype;
	}
	public void setEmrtype(String emrtype) {
		this.emrtype = emrtype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMarry() {
		return marry;
	}
	public void setMarry(String marry) {
		this.marry = marry;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}
	public String getRelateTel() {
		return relateTel;
	}
	public void setRelateTel(String relateTel) {
		this.relateTel = relateTel;
	}
	public Integer getSocialIdType() {
		return socialIdType;
	}
	public void setSocialIdType(Integer socialIdType) {
		this.socialIdType = socialIdType;
	}
	public String getSocialId() {
		return socialId;
	}
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateDoctor() {
		return createDoctor;
	}
	public void setCreateDoctor(String createDoctor) {
		this.createDoctor = createDoctor;
	}
	public String getChiefComplaint() {
		return chiefComplaint;
	}
	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}
	public String getAllergicHistory() {
		return allergicHistory;
	}
	public void setAllergicHistory(String allergicHistory) {
		this.allergicHistory = allergicHistory;
	}
	public String getPresentIllness() {
		return presentIllness;
	}
	public void setPresentIllness(String presentIllness) {
		this.presentIllness = presentIllness;
	}
	public String getPastIllness() {
		return pastIllness;
	}
	public void setPastIllness(String pastIllness) {
		this.pastIllness = pastIllness;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public Long getAuditDoctor() {
		return auditDoctor;
	}
	public void setAuditDoctor(Long auditDoctor) {
		this.auditDoctor = auditDoctor;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBodyExam() {
		return bodyExam;
	}
	public void setBodyExam(String bodyExam) {
		this.bodyExam = bodyExam;
	}
	public String getDiagnoses() {
		return diagnoses;
	}
	public void setDiagnoses(String diagnoses) {
		this.diagnoses = diagnoses;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public String getFamilyIllness() {
		return familyIllness;
	}
	public void setFamilyIllness(String familyIllness) {
		this.familyIllness = familyIllness;
	}
	public String getAssistExam() {
		return assistExam;
	}
	public void setAssistExam(String assistExam) {
		this.assistExam = assistExam;
	}
	public String getTreatSuggetion() {
		return treatSuggetion;
	}
	public void setTreatSuggetion(String treatSuggetion) {
		this.treatSuggetion = treatSuggetion;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getVistiNo() {
		return vistiNo;
	}
	public void setVistiNo(String vistiNo) {
		this.vistiNo = vistiNo;
	}
	public String getPersonalIllness() {
		return personalIllness;
	}
	public void setPersonalIllness(String personalIllness) {
		this.personalIllness = personalIllness;
	}
    
}