package com.huatuo.clinics.cms.services.regulator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.SsDicItemDTO;
import com.huatuo.clinics.cms.db.bean.SsDicItem;
import com.huatuo.clinics.cms.db.repository.DbSsDicItemRepository;
import com.huatuo.common.Utils;

/**
 * 字典相关服务
 *
 */
@Service
public class DictionaryServiceImpl implements DictionaryService{


//	@Resource    autowired好像不行
	@Autowired
	private DbSsDicItemRepository dbSsDicItemRepository;
	
	
	
	@Override
	public List<SsDicItemDTO> queryBillingCodes(String code) {
		List<SsDicItemDTO> list2=new ArrayList<>();
		List<SsDicItem> list=dbSsDicItemRepository.queryBillingCodes(code);
		for (SsDicItem ssDicItem : list) {
			SsDicItemDTO dicItemBean = Utils.exchangeObject(ssDicItem, SsDicItemDTO.class);
				list2.add(dicItemBean);
		}
		if(list2.size() > 0){
			return list2;
		}
		return null;
	}
	
	
	@Override
	public SsDicItemDTO  getDicByCode(String orgId,String itemParent,String itemValue) throws Exception{
		List<SsDicItem>  list=dbSsDicItemRepository.getDicByCode(orgId, itemParent, itemValue);
		if(list!=null && list.size()>0){
			SsDicItemDTO dto= new SsDicItemDTO();
			dto=Utils.exchangeObject(list.get(0), SsDicItemDTO.class);
			return dto;
		}
		return null;
	}
	
}
