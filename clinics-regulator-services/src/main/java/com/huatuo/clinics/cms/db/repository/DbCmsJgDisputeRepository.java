package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.bean.AssembleDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgDispute;
import com.huatuo.clinics.cms.db.bean.CmsJgMonitor;
import com.huatuo.clinics.cms.db.bean.DisputeRepost;
import com.huatuo.clinics.cms.db.bean.DynamicDetection;
import com.huatuo.clinics.cms.db.bean.MonitoringEvents;

/**
 * 医疗争议登记
 * @author 河南华佗在线
 *
 */
public interface DbCmsJgDisputeRepository {
	/**
	 * 新增医疗争议登记
	 * @param disputeDTO
	 */
	int saveDispute(CmsJgDispute dispute);
	/**
	 * 修改医疗争议登记
	 * @param disputeDTO
	 */
	int updateDispute(CmsJgDispute dispute);
	/**
	 * 查询医疗争议登记
	 * @param key
	 * @param beginDate
	 * @param endDate
	 * @param countyCode
	 * @return
	 */
	List<CmsJgDispute> queryDisputes(String key, String beginDate,
			String endDate, String countyCode);
	
	/**
	 * 医疗争议数量统计表
	 * @param reportType
	 * @param year
	 * @param year2
	 * @param month
	 * @param month2
	 * @param day
	 * @param address
	 * @param status 
	 * @return
	 */
	List<DisputeRepost> queryMedicalDisputeNumber(String reportType,
			String year, String year2, String month, String month2, String day,
			String address, String status);
	
	/**
	 * 医疗争议详情
	 * @param reportType
	 * @param year
	 * @param year2
	 * @param month
	 * @param month2
	 * @param day
	 * @param address
	 * @return
	 */
	List<CmsJgDispute> queryDisputesByReport(String reportType, String year,
			String year2, String month, String month2, String day,
			String address);
	
	/**
	 * 查询发生的医疗争议
	 * @param date
	 * @param endDate
	 * @param orgId 
	 * @return
	 */
	List<AssembleDTO> queryDisputeNo(String date, String endDate, List<String> orgId);
	
	/**
	 * 查询上报的传染病
	 * @param beginDate
	 * @param endDate
	 * @param orgId 
	 * @return
	 */
	List<AssembleDTO> queryInfectionNo(String beginDate, String endDate, List<String> orgId);
	
	/**
	 * 监测事件具体情况
	 * @param strId
	 * @return
	 */
	List<MonitoringEvents> queryType(String strId);
	
	/**
	 * 根据id查询医疗争议登记
	 * @param id
	 * @return
	 */
	CmsJgDispute queryDisputes(String id);
	
	/**
	 * 保存事件详情
	 * @param dto
	 * @return
	 */
	int insertMonitor(CmsJgMonitor dto);
	
	/**
	 *  监测事件详情查询
	 * @param str
	 * @return
	 */
	 List<CmsJgMonitor> queryTypeMonitor(String str);
	 
	 /**
	  * 查询抗生素违规
	  * @param beginDate
	  * @param endDate
	  * @param orgId
	  * @param antibioticId
	  * @return
	  */
	List<AssembleDTO> queryAntibiotic(String beginDate, String endDate,
			List<String> orgId, String antibioticId);
	
	/**
	 *  历史信息查询
	 * @param date
	 * @param type
	 * @param name
	 * @param addressId
	 * @return
	 */
	List<CmsJgMonitor> queryMonitorDtl(String date, String type, String name,
			String addressId);

}
