package com.huatuo.clinics.cms.services.report;

import java.util.List;

import com.huatuo.clinics.cms.bean.OperateInfoDTO;
import com.huatuo.clinics.cms.bean.ReportResultDTO;



public interface ClinicsOperateService {
	
	/**
	 * 个体诊所运营情况概览
	 * @param year
	 * @param parentAreaCode
	 * @return
	 */
//	List<reportResultDTO> queryOperate(String year, String parentAreaCode,String sourceCode);
	
	/**
	 * 个体诊所初复诊门诊量统计(包含同比和环比)
	 * @param year
	 * @param parentAreaCode
	 * @param parentAreaCode 
	 * @param reportType 
	 * @param sourceCode 
	 * @return
	 */
	List<ReportResultDTO> queryVisit(String year, String month, String reportType, String parentAreaCode, String sourceCode);
	
	/**
	 * 服务总量下级区域概括
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @param sourceCode
	 * @return
	 */
	List<ReportResultDTO> querySubordinate(String year, String month,
			String reportType, String parentAreaCode, String sourceCode);
	
	/**
	 * 平均门诊量概括
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDTO> queryAveOutpatient(String year, String month,
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
	List<ReportResultDTO> queryTimeTrend(String startYear, String endYear,
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
	List<ReportResultDTO> querySum(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 查询初诊
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDTO> queryFirstVisit(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 查询复诊
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDTO> queryRepeatVisit(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 查询西医门诊
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDTO> queryWestVisit(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 查询中医门诊
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDTO> queryInVisit(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 查询诊所数量
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDTO> queryClQty(String year, String month,
			String reportType, String parentAreaCode);
	
	/**
	 * 个体诊所门诊量信息总表所有信息
	 * @param year
	 * @param month
	 * @param reportType
	 * @param parentAreaCode
	 * @return
	 */
	List<List<ReportResultDTO>> queryTableInfo(String year, String month, String reportType,
			String parentAreaCode);
	
	
	

	

}
