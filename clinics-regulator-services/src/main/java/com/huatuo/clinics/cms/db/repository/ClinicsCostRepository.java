package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.ReportResultDB;


public interface ClinicsCostRepository {
	
	/**
	 * 查询当年人均就诊费用
	 * @param year
	 * @param parentAreaCode
	 * @param reportType 
	 * @param month 
	 * @return
	 */
	List<ReportResultDB> queryCostQty(String year, String parentAreaCode, String month, String reportType);
	
	/**
	 * 各类型个体诊所人均就诊费用概况分析
	 * @param year
	 * @param parentAreaCode
	 * @param typeCode
	 * @param reportType 
	 * @param month 
	 * @return
	 */
	List<ReportResultDB> queryCostType(String year, String parentAreaCode,
			String typeCode, String month, String reportType);
	
	/**
	 * 服务费用下级区域概括
	 * @param year
	 * @param parentAreaCode
	 * @return
	 */
//	List<reportResultDB> queryLower(String year, String parentAreaCode);
	
	
	


}
