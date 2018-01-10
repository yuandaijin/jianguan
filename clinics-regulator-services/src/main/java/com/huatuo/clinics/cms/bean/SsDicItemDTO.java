package com.huatuo.clinics.cms.bean;

public class SsDicItemDTO implements Comparable<SsDicItemDTO>{
	
    private String dictItemId;
    private String dictItemCode;
    private String dictItemParent;
    private String dictItemValue;
    private String dictItemName;
    private String dictItemFp;
    private Integer dictValidFlg = 1;
    private Integer dictReadFlg = 1;
    private Integer defaultLoadFlg = 1;
    private String spellCode;
    private String wbCode;
    private String property1;
    private String property2;
    private String property3;
    private String property4;
    private String property5;
    private String property6;
    private Integer sortNo = 0;
    private String orgId;

	public String getDictItemId() {
		return dictItemId;
	}


	public void setDictItemId(String dictItemId) {
		this.dictItemId = dictItemId;
	}


	public String getDictItemCode() {
		return dictItemCode;
	}


	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}


	public String getDictItemParent() {
		return dictItemParent;
	}


	public void setDictItemParent(String dictItemParent) {
		this.dictItemParent = dictItemParent;
	}


	public String getDictItemValue() {
		return dictItemValue;
	}


	public void setDictItemValue(String dictItemValue) {
		this.dictItemValue = dictItemValue;
	}


	public String getDictItemName() {
		return dictItemName;
	}


	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}


	public String getDictItemFp() {
		return dictItemFp;
	}


	public void setDictItemFp(String dictItemFp) {
		this.dictItemFp = dictItemFp;
	}


	public Integer getDictValidFlg() {
		return dictValidFlg;
	}


	public void setDictValidFlg(Integer dictValidFlg) {
		this.dictValidFlg = dictValidFlg;
	}


	public Integer getDictReadFlg() {
		return dictReadFlg;
	}


	public void setDictReadFlg(Integer dictReadFlg) {
		this.dictReadFlg = dictReadFlg;
	}


	public Integer getDefaultLoadFlg() {
		return defaultLoadFlg;
	}


	public void setDefaultLoadFlg(Integer defaultLoadFlg) {
		this.defaultLoadFlg = defaultLoadFlg;
	}


	public String getSpellCode() {
		return spellCode;
	}


	public void setSpellCode(String spellCode) {
		this.spellCode = spellCode;
	}


	public String getWbCode() {
		return wbCode;
	}


	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}


	public String getProperty1() {
		return property1;
	}


	public void setProperty1(String property1) {
		this.property1 = property1;
	}


	public String getProperty2() {
		return property2;
	}


	public void setProperty2(String property2) {
		this.property2 = property2;
	}


	public String getProperty3() {
		return property3;
	}


	public void setProperty3(String property3) {
		this.property3 = property3;
	}


	public String getProperty4() {
		return property4;
	}


	public void setProperty4(String property4) {
		this.property4 = property4;
	}


	public String getProperty5() {
		return property5;
	}


	public void setProperty5(String property5) {
		this.property5 = property5;
	}


	public String getProperty6() {
		return property6;
	}


	public void setProperty6(String property6) {
		this.property6 = property6;
	}


	public Integer getSortNo() {
		return sortNo;
	}


	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Override
	public String toString() {
		return "SsDicItemDTO [dictItemId=" + dictItemId + ", dictItemCode="
				+ dictItemCode + ", dictItemParent=" + dictItemParent
				+ ", dictItemValue=" + dictItemValue + ", dictItemName="
				+ dictItemName + ", dictItemFp=" + dictItemFp
				+ ", dictValidFlg=" + dictValidFlg + ", dictReadFlg="
				+ dictReadFlg + ", defaultLoadFlg=" + defaultLoadFlg
				+ ", spellCode=" + spellCode + ", wbCode=" + wbCode
				+ ", property1=" + property1 + ", property2=" + property2
				+ ", property3=" + property3 + ", property4=" + property4
				+ ", property5=" + property5 + ", property6=" + property6
				+ ", sortNo=" + sortNo + "]";
	}


	@Override
	public int compareTo(SsDicItemDTO o) {
		return this.sortNo.compareTo(o.getSortNo());
	}
    
}