package com.huatuo.clinics.cms.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huatuo.clinics.cms.services.translate.Translatable;
import com.huatuo.clinics.cms.services.translate.TranslateField;

public class RsEmpiPatientInfoDTO implements Translatable{
	@TranslateField(fullQualifiedName="00001",targetFieldName="sexName")
	private String sex;
	private String sexName;
    private String patientId;
    private String patientName;
    private Date birthday;
    private String nativePlace;
    private String countryCode;
    private String nationalityCode;
    private String cardType;
    private String cardId;
    private String occupationCode;
    private String addr;
    private String postCode;
    private String teleNum;
    private String teleHomeNum;
    private String teleName;
    private String teleRelation;
    private String education;
     // 过敏史id串
    private String allergicNames;
    private Float height;
    private Float weight;
    private String marital;
    private String age;
    
    public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPatientId() {
        return patientId;
    }
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getAllergicNames() {
		return allergicNames;
	}
	public void setAllergicNames(String allergicNames) {
		this.allergicNames = allergicNames;
	}
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getNativePlace() {
        return nativePlace;
    }
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getNationalityCode() {
        return nationalityCode;
    }
    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public String getOccupationCode() {
        return occupationCode;
    }
    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    public String getTeleNum() {
        return teleNum;
    }
    public void setTeleNum(String teleNum) {
        this.teleNum = teleNum;
    }
    public String getTeleHomeNum() {
        return teleHomeNum;
    }
    public void setTeleHomeNum(String teleHomeNum) {
        this.teleHomeNum = teleHomeNum;
    }
    public String getTeleName() {
        return teleName;
    }
    public void setTeleName(String teleName) {
        this.teleName = teleName;
    }
    public String getTeleRelation() {
        return teleRelation;
    }
    public void setTeleRelation(String teleRelation) {
        this.teleRelation = teleRelation;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getMarital() {
		return marital;
	}
	public void setMarital(String marital) {
		this.marital = marital;
	}
}