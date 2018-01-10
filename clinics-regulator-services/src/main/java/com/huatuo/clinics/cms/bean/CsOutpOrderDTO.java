package com.huatuo.clinics.cms.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.huatuo.clinics.cms.services.translate.Translatable;
import com.huatuo.clinics.cms.services.translate.TranslateField;

public class CsOutpOrderDTO implements Translatable {
	private String ordId;// 医嘱单ID
	private String vistiId;// 就诊id
	private String ordNo;// 医嘱号
	private String orgId;// 机构id
	private Integer vistiNo;// 就诊编号
	private Date visitDate;// 就诊日期
	private String ordDepId;// 开单科室id
	private String doctorId;// 医生
	@TranslateField(fullQualifiedName = "20111", targetFieldName = "orderTypeName")
	private String orderType;// 处方类型：普通处方、急诊处方、儿科处方
	private String orderTypeName;// 处方类型中文名
	@TranslateField(fullQualifiedName = "20107", targetFieldName = "drugTypeName")
	private String drugType;//药品类型：西药/中成药、中草药、诊疗项
	private String drugTypeName;//药品类型中文名
	private String orderMemo;// 嘱托
	private Float amout;// sum(qty*retailPrice)
	private String validFlg;// 有效标示
	private String isCharge;// 是否收费（0-未收费，1-已收费）

	/* -------------------* */
	private Integer itemNo;
	private String ordDtlId;
	private String groupNo;
	private String ordType;
	private String ordItemId;
	private String clinicalItemSpec;
	private Integer sortNo;
	private Float dosage;
	private String dosageUnit;
	private Integer buyQty;
	private String buyUnit;
	private String route;
	private Integer days;
	private String freq;
	private Integer repetition;
	private Integer backQty;//退回数量
	private String patientId;//患者id
	//多余的数据，用于接收前台参数
//	private String jishu;
//	private String jianfufa;
//	public String getJishu() {
//		return jishu;
//	}
//	public void setJishu(String jishu) {
//		this.jishu = jishu;
//	}
//	public String getJianfufa() {
//		return jianfufa;
//	}
//	public void setJianfufa(String jianfufa) {
//		this.jianfufa = jianfufa;
//	}
	private Float defDoseQty;//剂量
    @TranslateField(fullQualifiedName="32103,32104",targetFieldName="defDoseUnitName")
    private String defDoseUnit;//剂量单位
    private String defDoseUnitName;//剂量单位名称
	// 多个处方详情
	@SuppressWarnings("rawtypes")
	private List<CsOutpOrderDtlDTO> orderDtlDTOs;

	
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public String getOrdDtlId() {
		return ordDtlId;
	}

	public void setOrdDtlId(String ordDtlId) {
		this.ordDtlId = ordDtlId;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getOrdType() {
		return ordType;
	}

	public void setOrdType(String ordType) {
		this.ordType = ordType;
	}

	public String getOrdItemId() {
		return ordItemId;
	}

	public void setOrdItemId(String ordItemId) {
		this.ordItemId = ordItemId;
	}

	public String getClinicalItemSpec() {
		return clinicalItemSpec;
	}

	public void setClinicalItemSpec(String clinicalItemSpec) {
		this.clinicalItemSpec = clinicalItemSpec;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Float getDosage() {
		return dosage;
	}

	public void setDosage(Float dosage) {
		this.dosage = dosage;
	}

	public String getDosageUnit() {
		return dosageUnit;
	}

	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}

	public Integer getBuyQty() {
		return buyQty;
	}

	public void setBuyQty(Integer buyQty) {
		this.buyQty = buyQty;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public String getBuyUnit() {
		return buyUnit;
	}

	public void setBuyUnit(String buyUnit) {
		this.buyUnit = buyUnit;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public Integer getRepetition() {
		return repetition;
	}

	public void setRepetition(Integer repetition) {
		this.repetition = repetition;
	}

	public List<CsOutpOrderDtlDTO> getOrderDtlDTOs() {
		return orderDtlDTOs;
	}

	public void setOrderDtlDTOs(List<CsOutpOrderDtlDTO> orderDtlDTOs) {
		this.orderDtlDTOs = orderDtlDTOs;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getVistiId() {
		return vistiId;
	}

	public void setVistiId(String vistiId) {
		this.vistiId = vistiId;
	}

	public String getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public Integer getVistiNo() {
		return vistiNo;
	}

	public void setVistiNo(Integer vistiNo) {
		this.vistiNo = vistiNo;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Float getAmout() {
		return amout;
	}

	public void setAmout(Float amout) {
		this.amout = amout;
	}

	public String getValidFlg() {
		return validFlg;
	}

	public void setValidFlg(String validFlg) {
		this.validFlg = validFlg;
	}

	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getDrugTypeName() {
		return drugTypeName;
	}

	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}

	public Integer getBackQty() {
		return backQty;
	}

	public void setBackQty(Integer backQty) {
		this.backQty = backQty;
	}

	public Float getDefDoseQty() {
		return defDoseQty;
	}

	public void setDefDoseQty(Float defDoseQty) {
		this.defDoseQty = defDoseQty;
	}

	public String getDefDoseUnit() {
		return defDoseUnit;
	}

	public void setDefDoseUnit(String defDoseUnit) {
		this.defDoseUnit = defDoseUnit;
	}

	public String getDefDoseUnitName() {
		return defDoseUnitName;
	}

	public void setDefDoseUnitName(String defDoseUnitName) {
		this.defDoseUnitName = defDoseUnitName;
	}
}
