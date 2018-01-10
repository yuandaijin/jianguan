package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.ReportResultDB;



public interface ClinicsResidentsRepository {
	
	/**
	 * 查询排名前十病种
	 * @param year
	 * @param parentAreaCode
	 * @param month 
	 * @param reportType 
	 * @return
	 */
	List<ReportResultDB> queryReIncidence(String year, String parentAreaCode, String reportType, String month);
	
	/**
	 * 特定病种分析
	 * @param year
	 * @param parentAreaCode
	 * @param code 
	 * @param code2 
	 * @param month 
	 * @return
	 */
	List<ReportResultDB> queryReGiven(String year, String parentAreaCode, String reportType, String month, String code);
	
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
	 * @param reportType 
	 * @param endMonth 
	 * @param startMonth 
	 * @param reportType 
	 * @return
	 */
	String queryDiseasesSum(String year, String parentAreaCode, String startMonth, String endMonth, String day, String reportType);
	
	/**
	 * 查询排名前十病种(年季月)
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @return
	 */
	List<ReportResultDB> queryDiseases(String year, String parentAreaCode,
			String startMonth, String endMonth,String day, String reportType);
	
	
	/**
	 * 根据病种名称查询镇名称以及数量
	 * @param name
	 * @param reportType 
	 * @param endMonth 
	 * @param startMonth 
	 * @param parentAreaCode 
	 * @return
	 */
	List<ReportResultDB> queryFirstTenTown(String name, String parentAreaCode, String startMonth, String endMonth, String day,String reportType);
	
	/**
	 * 根据病种id查询该病种男女占比
	 * @param name
	 * @param reportType 
	 * @param endMonth 
	 * @param startMonth 
	 * @return
	 */
	List<ReportResultDB> queryFirstTentype(String year,String parentAreaCode,String startMonth, String endMonth,String day, String reportType);
	
	/**
	 * 根据病种id查询该病种年龄占比
	 * @param name
	 * @param reportType 
	 * @param endMonth 
	 * @param startMonth 
	 * @return
	 */
	List<ReportResultDB> queryFirstTentypes(String year, String parentAreaCode,
			String startMonth, String endMonth,String day,String reportType);
	
	/**
	 * 各种病种总和查询
	 * @param year
	 * @param parentAreaCode
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @return
	 */
	List<ReportResultDB> queryFirstTenSum(String year, String parentAreaCode,
			String startMonth, String endMonth, String day,String reportType);
	
	
	


}
