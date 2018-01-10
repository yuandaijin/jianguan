package com.huatuo.clinics.cms.services.regulator;

import java.util.List;

import com.huatuo.clinics.cms.bean.SsDicItemDTO;

/**
 * 字典相关服务
 *
 */
public interface DictionaryService {

	
	/**
	 * 根据code查询字典
	 * @return
	 */
	public List<SsDicItemDTO> queryBillingCodes(String code);
	
	/**
	 * 查找固定编码对应的字典项，主要用于查找给定字典项的name
	 * @param orgId 与业务相关的机构ID
	 * @param parent 字典类型编码
	 * @param value  字典对应的value
	 * @return
	 */
	public SsDicItemDTO getDicByCode(String orgId, String dictType, String itemValue) throws Exception; 
}
