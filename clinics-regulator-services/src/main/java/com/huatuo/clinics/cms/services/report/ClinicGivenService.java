package com.huatuo.clinics.cms.services.report;

import java.util.List;

import com.huatuo.clinics.cms.bean.ReportResultDTO;



public interface ClinicGivenService {
	
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
	List<ReportResultDTO> queryGivenSummary(String year, String parentAreaCode,
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
	List<ReportResultDTO> queryGivenFeatures(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType, String code);
	
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
	List<ReportResultDTO> queryGivenSexOrAge(String year,
			String parentAreaCode, String startMonth, String endMonth,
			String reportType, String code1,String day);
	
	/**
	 * 时间趋势
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @param code
	 * @param code 
	 * @return
	 */
	List<ReportResultDTO> queryGivenTimeTrend(String startYear,String endYear,
			String parentAreaCode, String startMonth, String endMonth,String startDay,String endDay,
			String reportType, String code);

	
	
}
