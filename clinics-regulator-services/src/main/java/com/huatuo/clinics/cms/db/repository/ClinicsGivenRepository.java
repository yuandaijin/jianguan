package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.ReportResultDB;




public interface ClinicsGivenRepository {
	
	/**
	 * 个体诊所选择病种发病总量
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @param code
	 * @return
	 */
	List<ReportResultDB> queryGivenSummary(String year, String parentAreaCode,
			String startMonth, String endMonth, String day,String reportType, String code);
	
	/**
	 * 特定病种高发地区分布
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @param code
	 * @return
	 */
	List<ReportResultDB> queryGivenFeatures(String year, String parentAreaCode,
			String startMonth, String endMonth,String day, String reportType, String code);
	
	/**
	 * 个体诊所A病种发病性别或年龄分布
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @param code
	 * @return
	 */
	List<ReportResultDB> queryGivenSexOrAge(String year, String parentAreaCode,
			String startMonth, String endMonth, String reportType, String code,String day);
	
	/**
	 * 时间趋势
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @param code
	 * @return
	 */
	List<ReportResultDB> queryGivenTimeTrend(String startYear,String endYear,
			String parentAreaCode, String startMonth, String endMonth,String startDay,String endDay,
			String reportType, String code);
	
	
	


}
