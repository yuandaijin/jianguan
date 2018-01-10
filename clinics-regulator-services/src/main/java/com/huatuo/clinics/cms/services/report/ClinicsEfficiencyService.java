package com.huatuo.clinics.cms.services.report;

import java.util.List;

import com.huatuo.clinics.cms.bean.ReportResultDTO;

public interface ClinicsEfficiencyService {
	
	/**
	 * 查询当月服务人次
	 * @param year
	 * @param month 
	 * @param reportType 
	 * @param month
	 * @param day 
	 * @return
	 */
	List<ReportResultDTO> queryServiceQty(String year, String parentAreaCode, String reportType, String month, String day);
	
	/**
	 * 查询不同类型诊所日人均服务人次分布
	 * @param year
	 * @param month
	 * @param parentAreaCode
	 * @param month 
	 * @param reportType 
	 * @param Code
	 * @return
	 */
	List<ReportResultDTO> queryServiceEff(String year, String parentAreaCode,
			String typeCode, String reportType, String month);
	
	/**
	 * 下级区域概括
	 * @param year
	 * @param month
	 * @param parentAreaCode
	 * @param reportType 
	 * @param month 
	 * @return
	 */
	List<ReportResultDTO> queryJunior(String year, String parentAreaCode,
			String code, String month, String reportType);
	
	

	

}
