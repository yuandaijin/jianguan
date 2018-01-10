package com.huatuo.clinics.cms.services.regulator;

import java.util.List;

import com.huatuo.clinics.cms.bean.CsOutpOrderDTO;
import com.huatuo.clinics.cms.bean.CsOutpOrderDtlDTO;
import com.huatuo.clinics.cms.bean.CsOutpVisitDTO;

/**
 * 医嘱、处方、药房药品综合服务接口
 * 
 * @author duanzongxiang
 * 
 */
public interface PrescriptionService {

	/**
	 * 通过就诊号获得诊所下当天的处方对象
	 * @param orderId 
	 * 
	 * @param visitNo
	 * @return
	 * @throws Exception
	 */
	public List<CsOutpOrderDTO> getByVisitNo(List<String> strList,String startDate,String endDate,String orderType,String userId) throws Exception;
	
	/**
	 * 根据诊所id拿到单个处方信息
	 * @param orderId
	 * @return
	 */
	public CsOutpOrderDTO getByVisitNo(String orderId);
	
	/**
	 * 查询处方明细 公共方法
	 * @param orderDTOs
	 * @return
	 */
	public CsOutpVisitDTO queryListDl(CsOutpOrderDTO dto,CsOutpVisitDTO csOutpVisitDTO,int sign)throws Exception;
	/**
	 * 根据医嘱单ID和处方类型查询药品列表
	 * 
	 * @param ordId
	 * @param type
	 * @return
	 */
	public List<CsOutpOrderDtlDTO> query(String ordId, String type);
	
	/**
	 * 获得就诊id下的所有处方
	 * @return
	 */
	public List<CsOutpOrderDTO> listByVisitId(String visitId);
	
	/**
	 * 通过处方id获得处方明细
	 * 
	 * @param ordDepId
	 * @return
	 * @throws Exception
	 */
	public List<CsOutpOrderDtlDTO> getOrderDtList(String ordDepId)
			throws Exception;
}
