package com.huatuo.clinics.cms.bean;
/**
 * 个体诊所基本信息总表
 */
public class ClinicResourcesInfoDTO {
	private String areaName;
	private String cliQty;
	/**
	 * 社会办
	 */
	private String social;
	/**
	 * 个人办
	 */
	private String personal;
	/**
	 * 主办单位-其它
	 */
	private String foundOther;
	/**
	 *普通
	 */
	private String ordinary;
	/**
	 *中医
	 */
	private String chinaDoctor;
	/**
	 * 中西医
	 */
	private String medicine;
	/**
	 * 口腔
	 */
	private String mouth;
	/**
	 * 诊所类别-其它
	 */
	private String otherType;
	/**
	 * 五星级
	 */
	private String fiveStars;
	/**
	 * 四星级
	 */
	private String fourStars;
	/**
	 * 三星级
	 */
	private String thereStars;
	/**
	 * 未评级
	 */
	private String rateD;
	/**
	 * 小型
	 */
	private String small;
	/**
	 * 中型
	 */
	private String midsize;
	/**
	 * 大型
	 */
	private String big;
	/**
	 * code
	 */
	private String areaCode;
	private String twoStar;
	private String oneStar;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCliQty() {
		return cliQty;
	}
	public void setCliQty(String cliQty) {
		this.cliQty = cliQty;
	}
	public String getSocial() {
		return social;
	}
	public void setSocial(String social) {
		this.social = social;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public String getFoundOther() {
		return foundOther;
	}
	public void setFoundOther(String foundOther) {
		this.foundOther = foundOther;
	}
	public String getOrdinary() {
		return ordinary;
	}
	public void setOrdinary(String ordinary) {
		this.ordinary = ordinary;
	}
	public String getChinaDoctor() {
		return chinaDoctor;
	}
	public void setChinaDoctor(String chinaDoctor) {
		this.chinaDoctor = chinaDoctor;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getMouth() {
		return mouth;
	}
	public void setMouth(String mouth) {
		this.mouth = mouth;
	}
	public String getOtherType() {
		return otherType;
	}
	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}
	public String getFiveStars() {
		return fiveStars;
	}
	public void setFiveStars(String fiveStars) {
		this.fiveStars = fiveStars;
	}
	public String getFourStars() {
		return fourStars;
	}
	public void setFourStars(String fourStars) {
		this.fourStars = fourStars;
	}
	public String getThereStars() {
		return thereStars;
	}
	public void setThereStars(String thereStars) {
		this.thereStars = thereStars;
	}
	public String getRateD() {
		return rateD;
	}
	public void setRateD(String rateD) {
		this.rateD = rateD;
	}
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public String getMidsize() {
		return midsize;
	}
	public void setMidsize(String midsize) {
		this.midsize = midsize;
	}
	public String getBig() {
		return big;
	}
	public void setBig(String big) {
		this.big = big;
	}
	public String getTwoStar() {
		return twoStar;
	}
	public void setTwoStar(String twoStar) {
		this.twoStar = twoStar;
	}
	public String getOneStar() {
		return oneStar;
	}
	public void setOneStar(String oneStar) {
		this.oneStar = oneStar;
	}
	public void rateD(){
//		int total=getInt(fiveStars)+getInt(fourStars)+getInt(thereStars)+getInt(twoStar)+getInt(oneStar);
//		int fft=getInt(fiveStars)+getInt(fourStars)+getInt(thereStars);
		this.rateD=(getInt(fiveStars)+getInt(fourStars)+getInt(thereStars)+getInt(twoStar)+getInt(oneStar))-(getInt(fiveStars)+getInt(fourStars)+getInt(thereStars))+"";
	}
	private int getInt(String str){
		if(str!=null){
			return Integer.parseInt(str);
		}else {
			return 0;
		}
	}
}
