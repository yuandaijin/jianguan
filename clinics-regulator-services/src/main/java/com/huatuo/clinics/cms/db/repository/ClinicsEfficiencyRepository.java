package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.ReportResultDB;





public interface ClinicsEfficiencyRepository {
	
	/**
	 * 查询当月服务人次
	 * @param year
	 * @param month
	 * @return
	 */
	List<ReportResultDB> queryServiceQty(String year, String parentAreaCode, String reportType, String month, String day);
	
	/**
	 * 查询不同类型诊所日人均服务人次分布
	 * @param year
	 * @param month
	 * @param parentAreaCode
	 * @param month2 
	 * @param reportType 
	 * @return
	 */
	List<ReportResultDB> queryServiceEff(String year, String parentAreaCode,
			String code, String reportType, String month);
	
	/**
	 * 下级区域概括
	 * @param year
	 * @param month
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryJunior(String year, String parentAreaCode,
			String code, String month, String reportType);
	
	


}
