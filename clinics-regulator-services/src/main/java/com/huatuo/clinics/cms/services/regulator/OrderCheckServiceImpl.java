package com.huatuo.clinics.cms.services.regulator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrdercheckDtlDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgOrdercheck;
import com.huatuo.clinics.cms.db.bean.CmsJgOrdercheckDtl;
import com.huatuo.clinics.cms.db.repository.OrderCheckRepository;
import com.huatuo.clinics.cms.services.translate.DefaultTranslatorManager;
import com.huatuo.common.Utils;
@Service
public class OrderCheckServiceImpl implements OrderCheckService  {
	@Autowired
	private OrderCheckRepository orderCheckRepository;//处方抽查服务
	@Autowired
	private DefaultTranslatorManager dtm;

	@Override
	public String insertOrder(CmsJgOrdercheckDTO dto) {
		Date date=new Date();
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate=fmt.format(date);
		dto.setCheckTime(newDate);//抽查时间
		//处方明细
		List<CmsJgOrdercheckDtlDTO> list=dto.getList();
		//总处方数量
		Integer sum=list.size();
		//合格处方数量
		Integer qualifiedNo=0;
		for (CmsJgOrdercheckDtlDTO cmsJgOrdercheckDtlDTO : list) {
			if(cmsJgOrdercheckDtlDTO.getCheckResult()==0){
				qualifiedNo++;
			}
		}
		//合格率=合格处方数量/总处方数量*100
		MathContext mc = new MathContext(8, RoundingMode.HALF_DOWN);
		BigDecimal number=new BigDecimal(qualifiedNo).divide(new BigDecimal(sum),mc).multiply(new BigDecimal(100));
		String qualified =number.setScale(2, BigDecimal.ROUND_HALF_UP)+"%";
		//不合格处方数量
		Integer unqualifiedNo=sum-qualifiedNo;
		dto.setQualified(qualified);
		dto.setQualifiedNo(qualifiedNo);
		dto.setUnqualifiedNo(unqualifiedNo);
		dto.setCheckId(UUID.randomUUID().toString());//主键id
		//保存处方汇总信息
		int i=orderCheckRepository.insertOrderSummary(Utils.exchangeObject(dto, CmsJgOrdercheck.class));
		//保存处方明细
		int s=0;
		for (CmsJgOrdercheckDtlDTO cmsJgOrdercheckDtlDTO : list) {
			cmsJgOrdercheckDtlDTO.setId(UUID.randomUUID().toString());//主键
			cmsJgOrdercheckDtlDTO.setOrderId(dto.getCheckId());//外键
			 s=orderCheckRepository.insertOrderDtl(
					Utils.exchangeObject(cmsJgOrdercheckDtlDTO, CmsJgOrdercheckDtl.class));
		}
		if(i > 0){
			return dto.getCheckId();
		}else{
			return null;
		}
	}

	
	@Override
	public List<CmsJgOrdercheckDTO> queryOrderRecord(String startDate,
			String endDate, String checkArea, String orgName) {
		List<CmsJgOrdercheckDTO> list=new ArrayList<CmsJgOrdercheckDTO>();
		List<CmsJgOrdercheck> dto=orderCheckRepository.queryOrderRecord(startDate,endDate,checkArea,orgName);
		if(dto.size() > 0){
			for (CmsJgOrdercheck cmsJgOrdercheck : dto) {
				CmsJgOrdercheckDTO resultDto= Utils.exchangeObject(cmsJgOrdercheck, CmsJgOrdercheckDTO.class);
				resultDto = dtm.translate(resultDto,cmsJgOrdercheck.getOrgId());
				list.add(resultDto);
			}
			return list;
		}
		return null;
	}


	@Override
	public List<CmsJgOrdercheckDtlDTO> queryOrderQualified(String orderId,
			Integer flag) {
		List<CmsJgOrdercheckDtlDTO> list=new ArrayList<CmsJgOrdercheckDtlDTO>();
		List<CmsJgOrdercheckDtl> dto=orderCheckRepository.queryOrderQualified(orderId,flag);
		for (CmsJgOrdercheckDtl cmsJgOrdercheckDtl : dto) {
			CmsJgOrdercheckDtlDTO resultDto= Utils.exchangeObject(cmsJgOrdercheckDtl, CmsJgOrdercheckDtlDTO.class);
			list.add(resultDto);
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}


	@Override
	public List<CmsJgOrdercheckDtlDTO> queryOrder(String orderId) {
		List<CmsJgOrdercheckDtlDTO> list=new ArrayList<CmsJgOrdercheckDtlDTO>();
		List<CmsJgOrdercheckDtl> dto=orderCheckRepository.queryOrder(orderId);
		for (CmsJgOrdercheckDtl cmsJgOrdercheckDtl : dto) {
			CmsJgOrdercheckDtlDTO resultDto= Utils.exchangeObject(cmsJgOrdercheckDtl, CmsJgOrdercheckDtlDTO.class);
			list.add(resultDto);
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}


	@Override
	public CmsJgOrdercheckDTO queryOrderHands(String checkId) {
		CmsJgOrdercheck dto=orderCheckRepository.queryOrderHands(checkId);
		if(null != dto){
			CmsJgOrdercheckDTO resultDto=Utils.exchangeObject(dto, CmsJgOrdercheckDTO.class);
			resultDto = dtm.translate(resultDto,resultDto.getOrgId());
			return resultDto;
		}
		return null;
	}
	
}

