package com.huatuo.clinics.cms.db.bean;

import java.util.Date;

public class ZdyTaPhaDrugInventory {
    private String drugInvId;
    private String phaDrugId;
    private String tradeName;
    private String drugSpec;
    private String orgId;
    private Date instockDate;
    private String instockBatch;
    private String manufacturer;
    private String packUnit;
    private String minUnit;
    private Integer packQty;
    private Integer minQty;
    private Integer convQty;
    private Integer minQtyTotal;
    private Integer packQtyTotal;
    private Float retailMinPrice;//小规格零售价
    private Float retailPrice;//零售价
	public String getDrugInvId() {
		return drugInvId;
	}
	public void setDrugInvId(String drugInvId) {
		this.drugInvId = drugInvId;
	}
	public String getPhaDrugId() {
		return phaDrugId;
	}
	public void setPhaDrugId(String phaDrugId) {
		this.phaDrugId = phaDrugId;
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
	public Date getInstockDate() {
		return instockDate;
	}
	public void setInstockDate(Date instockDate) {
		this.instockDate = instockDate;
	}
	public String getInstockBatch() {
		return instockBatch;
	}
	public void setInstockBatch(String instockBatch) {
		this.instockBatch = instockBatch;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getPackUnit() {
		return packUnit;
	}
	public void setPackUnit(String packUnit) {
		this.packUnit = packUnit;
	}
	public String getMinUnit() {
		return minUnit;
	}
	public void setMinUnit(String minUnit) {
		this.minUnit = minUnit;
	}
	public Integer getPackQty() {
		return packQty;
	}
	public void setPackQty(Integer packQty) {
		this.packQty = packQty;
	}
	public Integer getMinQty() {
		return minQty;
	}
	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}
	public Integer getConvQty() {
		return convQty;
	}
	public void setConvQty(Integer convQty) {
		this.convQty = convQty;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Float getRetailMinPrice() {
		return retailMinPrice;
	}
	public void setRetailMinPrice(Float retailMinPrice) {
		this.retailMinPrice = retailMinPrice;
	}
	public Float getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Float retailPrice) {
		this.retailPrice = retailPrice;
	}
}