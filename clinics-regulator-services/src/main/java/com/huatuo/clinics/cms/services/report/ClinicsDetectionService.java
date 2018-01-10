package com.huatuo.clinics.cms.services.report;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgPatrolDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.bean.CmsOrgInfoDTO;
import com.huatuo.clinics.cms.bean.DynamicDetectionDTO;
import com.huatuo.clinics.cms.bean.MonitoringEventsDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgMonitor;




public interface ClinicsDetectionService {
	
	/**
	 * 查询发生的医疗争议
	 * @param date
	 * @param endDate
	 * @param orgId 
	 */
	List<DynamicDetectionDTO> queryDisputeNo(String beginDate, String endDate, List<String> orgId);
	
	/**
	 *  查询上报的传染病
	 * @param beginDate
	 * @param endDate
	 * @param orgId 
	 * @return
	 */
	List<DynamicDetectionDTO> queryInfectionNo(String beginDate, String endDate, List<String> orgId);
	
	/**
	 * 监测事件具体情况
	 * @param idIist
	 * @return
	 */
	List<MonitoringEventsDTO> queryType(List<String> idIist);
	
	/**
	 * 保存事件详情
	 * @param monList
	 * @return
	 */
	int insertMonitor(List<MonitoringEventsDTO> monList);
	
	/**
	 * 监测事件详情查询
	 * @param idIist
	 * @return
	 */
	List<CmsJgMonitor> queryTypeMonitor(List<String> idIist);
	
	/**
	 *  查询抗生素违规
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @param antibioticId 
	 * @return
	 */
	List<DynamicDetectionDTO> queryAntibiotic(String beginDate, String endDate,
			List<String> orgId, String antibioticId);
	
	/**
	 * 历史信息查询
	 * @param date
	 * @param type
	 * @param name
	 * @param addressId
	 * @return
	 */
	List<CmsJgMonitor> queryMonitorDtl(String date, String type, String name,
			String addressId);
	
	/**
	 * 根据机构id查询诊所基本信息
	 * @param orgId
	 * @return
	 */
	CmsOrgInfoDTO queryClinicsInfo(RuquestBeanDTO beanDTO);
	
	/**
	 * 获取诊所基本信息（地图显示）
	 * @return
	 */
	List<CmsOrgInfoDTO> queryCLinicsInMap(CmsJgUserinfoDTO person,RuquestBeanDTO beanDTO);
	
	/**
	 * 添加巡查信息
	 * @param patrolDTO
	 * 
	 */
	void addPatrolInfo(CmsJgPatrolDTO patrolDTO);
}
