package com.huatuo.clinics.cms.services.regulator;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgDisputeDTO;
import com.huatuo.clinics.cms.bean.DisputeRepostDTO;

/**
 * 医疗争议登记服务
 * @author 河南华佗在线
 *
 */
public interface CmsJgDisputeService {
	/**
	 * 新增医疗争议登记
	 * @param disputeDTO
	 */
	int saveDispute(CmsJgDisputeDTO disputeDTO);
	/**
	 * 修改医疗争议登记
	 * @param disputeDTO
	 */
	int updateDispute(CmsJgDisputeDTO disputeDTO);
	/**
	 *  查询医疗争议登记
	 * @param key
	 * @param beginDate
	 * @param endDate
	 * @param countyCode
	 * @return
	 */
	List<CmsJgDisputeDTO> queryDisputes(String key, String beginDate,
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
	List<DisputeRepostDTO> queryMedicalDisputeNumber(String reportType,
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
	List<CmsJgDisputeDTO> queryDisputesByReport(String reportType, String year,
			String year2, String month, String month2, String day,
			String address);
	
	/**
	 * 根据id查询医疗争议登记
	 * @param id
	 * @return
	 */
	CmsJgDisputeDTO queryDisputes(String id);
	
}
