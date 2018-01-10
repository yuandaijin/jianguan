package com.huatuo.clinics.cms.services.report;

import java.util.List;

import com.huatuo.clinics.cms.bean.ReportResultDTO;


public interface ClinicResidentsService {
	
	/**
	 * 查询排名前十病种
	 * @param year
	 * @param parentAreaCode
	 * @param month 
	 * @param reportType 
	 * @return
	 */
	List<ReportResultDTO> queryReIncidence(String year, String parentAreaCode, String reportType, String month);
	
	/**
	 * 特定病种分析
	 * @param year
	 * @param parentAreaCode
	 * @param code
	 * @param month 
	 * @param code2 
	 * @return
	 */
	List<ReportResultDTO> queryReGiven(String year, String parentAreaCode,
			String reportType, String month, String code);
	
	/**
	 * 查询所有病种的总数量
	 * @param year
	 * @param parentAreaCode
	 * @param month 
	 * @param reportType 
	 * @return
	 */
	String queryReIncidenceSum(String year, String parentAreaCode, String reportType, String month);
	
	/**
	 * 查询所有病种的总数量(年季月)
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @param reportType2 
	 * @return
	 */
	String queryDiseasesSum(String year, String parentAreaCode,
			String startMonth, String endMonth, String day, String reportType);
	
	/**
	 * 查询排名前十病种(年季月)
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @return
	 */
	List<ReportResultDTO> queryDiseases(String year, String parentAreaCode,
			String startMonth, String endMonth, String day,String reportType);
	

	/**
	 * 多发病特征(就诊前十病种)
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @return
	 */
	List<ReportResultDTO> queryFirstTen(String year, String parentAreaCode,
			String startMonth, String endMonth,String day, String reportType);
	
	/**
	 * 就诊疾病前十病种高发地区分布
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @return
	 */
	List<ReportResultDTO> queryFirstTenType(String year, String parentAreaCode,
			String startMonth, String endMonth, String day,String reportType);

	/**
	 * 就诊疾病前十病种高发地区分布
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @return
	 */
	List<ReportResultDTO> queryFirstTenTypes(String year,
			String parentAreaCode, String startMonth, String endMonth,String day,
			String reportType);
	
	/**
	 * 各种病种总和查询
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @return
	 */
	List<ReportResultDTO> queryFirstTenSum(String year, String parentAreaCode,
			String startMonth, String endMonth,String day, String reportType);
	
	

}
