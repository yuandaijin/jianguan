package com.huatuo.clinics.cms.db.repository;

import java.util.List;
import java.util.Map;

import com.huatuo.clinics.cms.bean.CmsJgPatrolDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;



/**
 * 对诊所巡查信息进行操作的Repository
 * @author dengyajie
 *
 */
public interface DbCmsJgPatrolRepository {
	/**
	 * 查询诊所是否已巡查
	 * @param beanDTO
	 * @return
	 */
	public int isPatrol(RuquestBeanDTO beanDTO);
	
	/**
	 * 添加巡查信息
	 * @param patrolDTO
	 */
	public void addPatrolInfo(CmsJgPatrolDTO patrolDTO);
	
	/**
	 * 根据机构id获取用户id
	 * @param orgId
	 * @param status
	 * @return
	 */
	public String getUserIdByOrgId(String orgId,String status);
	
	/**
	 * 获取机构的巡查时间
	 * @param beanDTO
	 * @return
	 */
	List<Map<String, Object>> getPatrolTime(RuquestBeanDTO beanDTO);
}
