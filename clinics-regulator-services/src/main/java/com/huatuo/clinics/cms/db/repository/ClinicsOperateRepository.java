package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.OperateInfo;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;



public interface ClinicsOperateRepository {
	
	/**
	 * 个体诊所运营情况概览
	 * @param year
	 * @param parentAreaCode
	 * @return
	 */
//	List<reportResultDB> queryOperate(String year, String parentAreaCode,String sourceCode);
	
	/**
	 * 个体诊所初复诊门诊量统计(包含同比和环比)
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryVisit(String year, String month,
			String reportType, String parentAreaCode, String sourceCode);
	
	/**
	 * 服务总量下级区域概括
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @param sourceCode
	 * @return
	 */
	List<ReportResultDB> querySubordinate(String year, String month,
			String reportType, String parentAreaCode, String sourceCode);
	
	/**
	 * 平均门诊量概括
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryAveOutpatient(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 服务总量时间趋势
	 * @param startYear
	 * @param endYear
	 * @param startMonth
	 * @param endMonth
	 * @param reportType
	 * @param parentAreaCode
	 * @param sourceCode 
	 * @return
	 */
	List<ReportResultDB> queryTimeTrend(String startYear, String endYear,
			String startMonth, String endMonth, String reportType,
			String parentAreaCode, String sourceCode);
	
	/**
	 * 查询门诊总量
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> querySum(String year, String month, String reportType,
			String parentAreaCode);
	
	/**
	 * 查询初诊
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryFirstVisit(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 查询复诊
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryRepeatVisit(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 查询西医门诊
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryWestVisit(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 查询中医门诊
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryInVisit(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 查询诊所数量
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryClQty(String year, String month,
			String reportType, String parentAreaCode);
	



}
