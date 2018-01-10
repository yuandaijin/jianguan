package com.huatuo.clinics.cms.bean;

public class ZdyDrugInfoAndInventory {
    private String phaDrugId;//药房药品id
    private String drugTradeId;//商品ID  ta_pha_dict_drug_info商品表
    private String orgId;//机构id  ta_pha_define默认
    private String pharmacyId;//'药房id  ta_pha_define
    
    private String tradeName;//商品名
    private String drugSpec;//药品规格
    private String commName;//通用名
    private String doseForm;//剂型
    private String spellCode;//拼音码
    private String wbCode;//五笔码
    private String tSpellCode;//商品名拼音码
    private String tWbCode;//商品名五笔码
    private String aliasName;//别名
    private String component;//药品成分
    private String drugType;//药品种类
    private String drugSubType;//药品分类
    private String suit;//功能主治
    private String phaEffect;//药理作用
    private String toxClass;//毒理分类
    private String physicsTrait;//理化特性
    private String otcType;//用药类型
    private String medType;//医保类别
    private Object volume;//体积
    private String volUnit;//体积单位
    private Object weight;//重量
    private String weightUnit;//重量单位
    private Object concentration;//浓度
    private String defFreq;//默认频率
    private Object defDoseQty;//默认频率
    private String defDoseUnit;//默认剂量单位
    private String defRoute;//默认给药方法
    private String barCode;//药品条形码
    private String manufacturer;//生产厂家
    private String ncmcFlg;//新农合标志
    private String approval;//批准文号
    private String baseDrugFlg;//国家基药标志
    private String kickBack;//不良反应
    private String tabu;//禁忌
    private String matters;//注意事项
    private String validFlg;//有效标示
    private String minUnit;//最小单位
    private String packUnit;//包装单位
    private int convQty;//换算量
    private float packRetailPrice;//市场零售价
    private float minRetailPrice;//小单位市场零售价
    private String stockMax; //库存上限
    private String stockMin;//库存下限
    private float wholesalePrice;//批发价
    
    private String drugInvId;//库存明细id
	private int packQty;//包装数量
	private int minQty;//小规格数量
    private Integer minQtyTotal;//药品库存总量
	private Integer packQtyTotal;//药品库存总量
	private String instockBatch;//库存批次
	public float getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(float wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public String getStockMax() {
		return stockMax;
	}

	public void setStockMax(String stockMax) {
		this.stockMax = stockMax;
	}

	public String getStockMin() {
		return stockMin;
	}

	public void setStockMin(String stockMin) {
		this.stockMin = stockMin;
	}
    
    
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
	public Object getVolume() {
		return volume;
	}
	public void setVolume(Object volume) {
		this.volume = volume;
	}
	public String getVolUnit() {
		return volUnit;
	}
	public void setVolUnit(String volUnit) {
		this.volUnit = volUnit;
	}
	public Object getWeight() {
		return weight;
	}
	public void setWeight(Object weight) {
		this.weight = weight;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public Object getConcentration() {
		return concentration;
	}
	public void setConcentration(Object concentration) {
		this.concentration = concentration;
	}
	public String getDefFreq() {
		return defFreq;
	}
	public void setDefFreq(String defFreq) {
		this.defFreq = defFreq;
	}
	public Object getDefDoseQty() {
		return defDoseQty;
	}
	public void setDefDoseQty(Object defDoseQty) {
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
	public String getPackUnit() {
		return packUnit;
	}
	public void setPackUnit(String packUnit) {
		this.packUnit = packUnit;
	}
	public int getConvQty() {
		return convQty;
	}
	public void setConvQty(int convQty) {
		this.convQty = convQty;
	}

	public float getPackRetailPrice() {
		return packRetailPrice;
	}

	public void setPackRetailPrice(float packRetailPrice) {
		this.packRetailPrice = packRetailPrice;
	}

	public float getMinRetailPrice() {
		return minRetailPrice;
	}

	public void setMinRetailPrice(float minRetailPrice) {
		this.minRetailPrice = minRetailPrice;
	}

	public String getDrugInvId() {
		return drugInvId;
	}

	public void setDrugInvId(String drugInvId) {
		this.drugInvId = drugInvId;
	}

	public int getPackQty() {
		return packQty;
	}

	public void setPackQty(int packQty) {
		this.packQty = packQty;
	}

	public int getMinQty() {
		return minQty;
	}

	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}

	public Integer getMinQtyTotal() {
		return minQtyTotal;
	}

	public void setMinQtyTotal(Integer minQtyTotal) {
		this.minQtyTotal = minQtyTotal;
	}

	public Integer getPackQtyTotal() {
		return packQtyTotal;
	}

	public void setPackQtyTotal(Integer packQtyTotal) {
		this.packQtyTotal = packQtyTotal;
	}

	public String getInstockBatch() {
		return instockBatch;
	}

	public void setInstockBatch(String instockBatch) {
		this.instockBatch = instockBatch;
	}
	
}