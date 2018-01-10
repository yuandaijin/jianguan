package com.huatuo.clinics.cms.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.huatuo.clinics.cms.services.translate.Translatable;
import com.huatuo.clinics.cms.services.translate.TranslateField;

public class TaPhaDrugInfoDTO implements Serializable,Translatable{
	private static final long serialVersionUID = 1L;
	private String phaDrugId;//药房药品id
    private String drugTradeId;//商品ID  ta_pha_dict_drug_info商品表
    private String orgId;//机构id  ta_pha_define默认
    private String pharmacyId;//'药房id  ta_pha_define
    
    private String tradeName;//商品名
    private String drugSpec;//药品规格
    private String commName;//通用名
    @TranslateField(fullQualifiedName="10003",targetFieldName="doseFormName")
	private String doseForm;//药品剂型
	private String doseFormName;//药品剂型名称
    private String spellCode;//拼音码
    private String wbCode;//五笔码
    private String tSpellCode;//商品名拼音码
    private String tWbCode;//商品名五笔码
    private String aliasName;//别名
    private String component;//药品成分
    private String drugType;//药品种类
    @TranslateField(fullQualifiedName="10002",targetFieldName="drugSubTypeName")
    private String drugSubType;//药品分类
    private String drugSubTypeName;//药品分类
    private String suit;//功能主治
    private String phaEffect;//药理作用
    private String toxClass;//毒理分类
    private String physicsTrait;//理化特性
    private String otcType;//用药类型
    private String medType;//医保类别
    private BigDecimal volume;//体积
    private BigDecimal weight;//重量
    @TranslateField(fullQualifiedName="32103",targetFieldName="volUnitName")
    private String volUnit;//体积单位
    private String volUnitName;//体积单位
    @TranslateField(fullQualifiedName="32104",targetFieldName="weightUnitName")
    private String weightUnit;//重量单位
    private String weightUnitName;//重量单位
    private BigDecimal concentration;//浓度
    private String defFreq;//默认频率
    private BigDecimal defDoseQty;//默认剂量
    private String defDoseUnit;//默认剂量单位
    private String defRoute;//默认给药方法
    private String barCode;//药品条形码
    private String manufacturer;//生产厂家
    private String ncmcFlg;//新农合标志
    private String approval;//批准文号
    private String baseDrugFlg;//国家基药标志
    private String medFlg;//医保标志
    private String kickBack;//不良反应
    private String tabu;//禁忌
    private String matters;//注意事项
    private String validFlg;//有效标示
    @TranslateField(fullQualifiedName="32102,32104",targetFieldName="minUnitName")
    private String minUnit;//最小单位
    private String minUnitName;//最小单位名称
    @TranslateField(fullQualifiedName="32102,32104",targetFieldName="packUnitName")
    private String packUnit;//包装单位
    private String packUnitName;//包装单位名称
    private Integer convQty;//换算量
    private Float packRetailPrice;//市场零售价
    private Float minRetailPrice;//小单位市场零售价
    private Integer stockMax; //库存上限
    private Integer stockMin;//库存下限
    private Float wholesalePrice;//批发价
	@TranslateField(fullQualifiedName="10004",targetFieldName="billCodeValue")
	private String billCode;//账单码
	private String billCodeValue;//账单码
	
	private Integer virtualPackQty;//虚拟大库存数量
	private Integer virtualMinQty;//虚拟小库存数量
    //自定义
    private String drugCommId;//药品通用名id
    
    
    
	public String getPhaDrugId() {
		return phaDrugId;
	}
	public void setPhaDrugId(String phaDrugId) {
		this.phaDrugId = phaDrugId;
	}
	public String getDrugTradeId() {
		return drugTradeId;
	}
	public void setDrugTradeId(String drugTradeId) {
		this.drugTradeId = drugTradeId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getDrugSpec() {
		return drugSpec;
	}
	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	public String getCommName() {
		return commName;
	}
	public void setCommName(String commName) {
		this.commName = commName;
	}
	public String getDoseForm() {
		return doseForm;
	}
	public void setDoseForm(String doseForm) {
		this.doseForm = doseForm;
	}
	public String getDoseFormName() {
		return doseFormName;
	}
	public void setDoseFormName(String doseFormName) {
		this.doseFormName = doseFormName;
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
	public String gettSpellCode() {
		return tSpellCode;
	}
	public void settSpellCode(String tSpellCode) {
		this.tSpellCode = tSpellCode;
	}
	public String gettWbCode() {
		return tWbCode;
	}
	public void settWbCode(String tWbCode) {
		this.tWbCode = tWbCode;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getDrugType() {
		return drugType;
	}
	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	public String getDrugSubType() {
		return drugSubType;
	}
	public void setDrugSubType(String drugSubType) {
		this.drugSubType = drugSubType;
	}
	public String getDrugSubTypeName() {
		return drugSubTypeName;
	}
	public void setDrugSubTypeName(String drugSubTypeName) {
		this.drugSubTypeName = drugSubTypeName;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public String getPhaEffect() {
		return phaEffect;
	}
	public void setPhaEffect(String phaEffect) {
		this.phaEffect = phaEffect;
	}
	public String getToxClass() {
		return toxClass;
	}
	public void setToxClass(String toxClass) {
		this.toxClass = toxClass;
	}
	public String getPhysicsTrait() {
		return physicsTrait;
	}
	public void setPhysicsTrait(String physicsTrait) {
		this.physicsTrait = physicsTrait;
	}
	public String getOtcType() {
		return otcType;
	}
	public void setOtcType(String otcType) {
		this.otcType = otcType;
	}
	public String getMedType() {
		return medType;
	}
	public void setMedType(String medType) {
		this.medType = medType;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getVolUnit() {
		return volUnit;
	}
	public void setVolUnit(String volUnit) {
		this.volUnit = volUnit;
	}
	public String getVolUnitName() {
		return volUnitName;
	}
	public void setVolUnitName(String volUnitName) {
		this.volUnitName = volUnitName;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public String getWeightUnitName() {
		return weightUnitName;
	}
	public void setWeightUnitName(String weightUnitName) {
		this.weightUnitName = weightUnitName;
	}
	public BigDecimal getConcentration() {
		return concentration;
	}
	public void setConcentration(BigDecimal concentration) {
		this.concentration = concentration;
	}
	public String getDefFreq() {
		return defFreq;
	}
	public void setDefFreq(String defFreq) {
		this.defFreq = defFreq;
	}
	public BigDecimal getDefDoseQty() {
		return defDoseQty;
	}
	public void setDefDoseQty(BigDecimal defDoseQty) {
		this.defDoseQty = defDoseQty;
	}
	public String getDefDoseUnit() {
		return defDoseUnit;
	}
	public void setDefDoseUnit(String defDoseUnit) {
		this.defDoseUnit = defDoseUnit;
	}
	public String getDefRoute() {
		return defRoute;
	}
	public void setDefRoute(String defRoute) {
		this.defRoute = defRoute;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getNcmcFlg() {
		return ncmcFlg;
	}
	public void setNcmcFlg(String ncmcFlg) {
		this.ncmcFlg = ncmcFlg;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getBaseDrugFlg() {
		return baseDrugFlg;
	}
	public void setBaseDrugFlg(String baseDrugFlg) {
		this.baseDrugFlg = baseDrugFlg;
	}
	public String getMedFlg() {
		return medFlg;
	}
	public void setMedFlg(String medFlg) {
		this.medFlg = medFlg;
	}
	public String getKickBack() {
		return kickBack;
	}
	public void setKickBack(String kickBack) {
		this.kickBack = kickBack;
	}
	public String getTabu() {
		return tabu;
	}
	public void setTabu(String tabu) {
		this.tabu = tabu;
	}
	public String getMatters() {
		return matters;
	}
	public void setMatters(String matters) {
		this.matters = matters;
	}
	public String getValidFlg() {
		return validFlg;
	}
	public void setValidFlg(String validFlg) {
		this.validFlg = validFlg;
	}
	public String getMinUnit() {
		return minUnit;
	}
	public void setMinUnit(String minUnit) {
		this.minUnit = minUnit;
	}
	public String getMinUnitName() {
		return minUnitName;
	}
	public void setMinUnitName(String minUnitName) {
		this.minUnitName = minUnitName;
	}
	public String getPackUnit() {
		return packUnit;
	}
	public void setPackUnit(String packUnit) {
		this.packUnit = packUnit;
	}
	public String getPackUnitName() {
		return packUnitName;
	}
	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
	}
	public Integer getConvQty() {
		return convQty;
	}
	public void setConvQty(Integer convQty) {
		this.convQty = convQty;
	}
	public Float getPackRetailPrice() {
		return packRetailPrice;
	}
	public void setPackRetailPrice(Float packRetailPrice) {
		this.packRetailPrice = packRetailPrice;
	}
	public Float getMinRetailPrice() {
		return minRetailPrice;
	}
	public void setMinRetailPrice(Float minRetailPrice) {
		this.minRetailPrice = minRetailPrice;
	}
	public Integer getStockMax() {
		return stockMax;
	}
	public void setStockMax(Integer stockMax) {
		this.stockMax = stockMax;
	}
	public Integer getStockMin() {
		return stockMin;
	}
	public void setStockMin(Integer stockMin) {
		this.stockMin = stockMin;
	}
	public Float getWholesalePrice() {
		return wholesalePrice;
	}
	public void setWholesalePrice(Float wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getBillCodeValue() {
		return billCodeValue;
	}
	public void setBillCodeValue(String billCodeValue) {
		this.billCodeValue = billCodeValue;
	}
	public String getDrugCommId() {
		return drugCommId;
	}
	public void setDrugCommId(String drugCommId) {
		this.drugCommId = drugCommId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getVirtualPackQty() {
		return virtualPackQty;
	}
	public void setVirtualPackQty(Integer virtualPackQty) {
		this.virtualPackQty = virtualPackQty;
	}
	public Integer getVirtualMinQty() {
		return virtualMinQty;
	}
	public void setVirtualMinQty(Integer virtualMinQty) {
		this.virtualMinQty = virtualMinQty;
	}
}