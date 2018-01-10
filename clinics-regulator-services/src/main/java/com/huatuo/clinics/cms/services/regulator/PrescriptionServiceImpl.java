package com.huatuo.clinics.cms.services.regulator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huatuo.clinics.cms.bean.CsOutpOrderDTO;
import com.huatuo.clinics.cms.bean.CsOutpOrderDtlDTO;
import com.huatuo.clinics.cms.bean.CsOutpVisitDTO;
import com.huatuo.clinics.cms.bean.OmDicCliChargeItemDTO;
import com.huatuo.clinics.cms.bean.TaPhaDrugInfoDTO;
import com.huatuo.clinics.cms.db.bean.CsOutpOrder;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtl;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtlExample;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderDtlExample.Criteria;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderExample;
import com.huatuo.clinics.cms.db.repository.DbCsOutpOrderDtlRepository;
import com.huatuo.clinics.cms.db.repository.DbCsOutpOrderRepository;
import com.huatuo.clinics.cms.services.translate.DefaultTranslatorManager;
import com.huatuo.clinics.cms.util.Constant;
import com.huatuo.clinics.cms.util.ImportException;
import com.huatuo.common.Utils;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
	@Autowired
	private DbCsOutpOrderRepository dbCsOutpOrderRepository;
	@Autowired
	private DbCsOutpOrderDtlRepository dbCsOutpOrderDtlRepository;
	@Autowired
	private DefaultTranslatorManager dtm;
	@Autowired
	private OmDicClinicalItemMapKeyService omDicClinicalItemMapKeyService;
	@Autowired
	private OmDicCliChargeItemService omDicCliChargeItemService;
	@Autowired
	private MedicineInfoManageService medicineInfoManageService;
	@Autowired
	private ChargeService chargeService;
	
	
	@Override
	public List<CsOutpOrderDTO> getByVisitNo(List<String> strList,String startDate,String endDate,String orderType,String userId) throws Exception {
		List<CsOutpOrderDTO> list=new ArrayList<CsOutpOrderDTO>();
		List<CsOutpOrder> csOutpOrders = dbCsOutpOrderRepository.query(strList,startDate,endDate,orderType,userId);
		for (CsOutpOrder csOutpOrder : csOutpOrders) {
			list.add(Utils.exchangeObject(csOutpOrder, CsOutpOrderDTO.class));
		}
		return list;
	}

	@Override
	public CsOutpOrderDTO getByVisitNo(String orderId) {
		CsOutpOrder dto = dbCsOutpOrderRepository.getByVisitNo(orderId);
		if(null != dto){
			return Utils.exchangeObject(dto, CsOutpOrderDTO.class);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<CsOutpOrderDtlDTO> query(String ordId, String type) {
		List<CsOutpOrderDtlDTO> listDto = new ArrayList<CsOutpOrderDtlDTO>();
		List<CsOutpOrderDtl> list = dbCsOutpOrderDtlRepository.query(ordId, type);
		//对象转换
		for (CsOutpOrderDtl csOutpOrderDtl : list) {
			CsOutpOrderDtlDTO dto = Utils.exchangeObject(csOutpOrderDtl, CsOutpOrderDtlDTO.class);
			listDto.add(dto);
		}
		return listDto;
	}
	
	
	/**
	 * 查询处方明细 公共方法
	 * @param orderDTOs
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public CsOutpVisitDTO queryListDl(CsOutpOrderDTO dto,CsOutpVisitDTO csOutpVisitDTO,int sign) throws Exception{
		float amount = 0;//总收费
		List<CsOutpOrderDTO> ords = new ArrayList<CsOutpOrderDTO>();
		if (null != dto) {
				double orderAmount = 0;//每张处方单的总价
				//通过医嘱单ID和处方类型查出处方详情集合
				List<CsOutpOrderDtlDTO> dtlDTOs = this.query(dto.getOrdId(), dto.getDrugType());
				List<CsOutpOrderDtlDTO> dtls = new ArrayList<>();
				//得到处方详情集合以后遍历：再通过判断处方类型，去关联相应的表（药品表或者诊疗收费表）一对一
				if (dtlDTOs.size()>0) {
					for (CsOutpOrderDtlDTO csOutpOrderDtlDTO : dtlDTOs) {     //处方单详情
						if (Constant.ORDER_TYPE_DIAGNOSE.equals(dto.getDrugType())) {
							//通过ord_item_id关联诊疗项表进行查询价格和项目
							String cliChargeItemId = omDicClinicalItemMapKeyService.getChargeIdByClinicalId(csOutpOrderDtlDTO.getOrdItemId());//获得收费项id
							OmDicCliChargeItemDTO oneBeanById = omDicCliChargeItemService.getOneBeanById(cliChargeItemId);//拿到收费诊疗项
							if (oneBeanById != null) {
								csOutpOrderDtlDTO.setTradeName(oneBeanById.getItemName());//诊疗项名称
								csOutpOrderDtlDTO.setPackRetailPrice(oneBeanById.getPrice());//诊疗项价格
							}
						}else {
							//通过ord_item_id和购买单位buy_unit查询相应的药品名和单价
							TaPhaDrugInfoDTO oneDTO = medicineInfoManageService.getOneDTO(csOutpOrderDtlDTO.getOrdItemId());
							if (oneDTO != null) {
								//包装单位
								csOutpOrderDtlDTO.setMinUnit(oneDTO.getMinUnit());
								csOutpOrderDtlDTO.setPackUnit(oneDTO.getPackUnit());
								//换算量
								csOutpOrderDtlDTO.setConvQty(oneDTO.getConvQty());
								csOutpOrderDtlDTO.setTradeName(oneDTO.getTradeName());//药品名称
								//西药剂量
								Float volume = null;
								Float weight = null;
								if(oneDTO.getVolume()!=null){
									volume = oneDTO.getVolume().floatValue();//体积
								}
								if(oneDTO.getWeight()!=null){
									weight = oneDTO.getWeight().floatValue();//重量
								}
								if(weight!=null){
									csOutpOrderDtlDTO.setWeight(weight);
								}else if(volume!=null){
									csOutpOrderDtlDTO.setVolume(volume);
								}
								float price = 0.0f;
								//购买单位为包装单位：单价取pack_retail_price市场零售价
								if (oneDTO.getPackUnit().equals(csOutpOrderDtlDTO.getBuyUnit())) {
									//查询大单位零售价
									price = chargeService.getActualPrice(oneDTO.getConvQty(),csOutpOrderDtlDTO.getBuyQty(),oneDTO.getPhaDrugId(),oneDTO.getOrgId(),0); 
								}else if (oneDTO.getMinUnit().equals(csOutpOrderDtlDTO.getBuyUnit())) {
									////查询小单位零售价
									price = chargeService.getActualPrice(oneDTO.getConvQty(),csOutpOrderDtlDTO.getBuyQty(),oneDTO.getPhaDrugId(),oneDTO.getOrgId(),1); 
								}
								if (price != 0.0) {
									csOutpOrderDtlDTO.setPackRetailPrice(price);
								}else {
									if (price == 0.0) {
										throw new ImportException(oneDTO.getTradeName()+"药品的价格不能为0");
									}
									if (sign == -1) {
										throw new ImportException("请先将药品"+oneDTO.getTradeName()+"入库");
									}
								}
							}
						}
						
						if (csOutpOrderDtlDTO.getBuyQty()!=null) {
							float subtotal = 0f;
							if (csOutpOrderDtlDTO.getRepetition()!=null && Constant.ORDER_TYPE_CY.equals(csOutpOrderDtlDTO.getOrdType())) {//中草药小计 = 购买数量*单价*付数
								if ( csOutpOrderDtlDTO.getPackRetailPrice()!=null) {
									subtotal = csOutpOrderDtlDTO.getBuyQty()*csOutpOrderDtlDTO.getPackRetailPrice();
								}
								csOutpOrderDtlDTO.setBuyQty(csOutpOrderDtlDTO.getBuyQty()/csOutpOrderDtlDTO.getRepetition());
							}else {//西药小计 = 购买数量*单价
								if ( csOutpOrderDtlDTO.getPackRetailPrice()!=null) {
									subtotal = csOutpOrderDtlDTO.getBuyQty()*csOutpOrderDtlDTO.getPackRetailPrice();
								}
							}
							csOutpOrderDtlDTO.setSubtotal(subtotal);//小计
							orderAmount += subtotal;
							csOutpOrderDtlDTO = dtm.translate(csOutpOrderDtlDTO, csOutpVisitDTO.getOrgId());
							csOutpOrderDtlDTO.setDefDoseUnit(csOutpOrderDtlDTO.getDefDoseUnitName());
							dtls.add(csOutpOrderDtlDTO);
						}
					}
				}
				amount += orderAmount;//总价
				dto.setOrderDtlDTOs(dtls);
				dto = dtm.translate(dto, csOutpVisitDTO.getOrgId());
				if(Constant.ORDER_TYPE_CY.equals(dto.getDrugType())){
					if(dto.getOrderDtlDTOs().size()>0){
						dto.setRepetition(dto.getOrderDtlDTOs().get(0).getRepetition());
					}
				}
				ords.add(dto);
		}
		csOutpVisitDTO.setOrderDTOs(ords);
		csOutpVisitDTO.setAmount(amount);
		csOutpVisitDTO = dtm.translate(csOutpVisitDTO, csOutpVisitDTO.getOrgId());
		return csOutpVisitDTO;
	}
	
	@Override
	public List<CsOutpOrderDTO> listByVisitId(String visitId) {
		List<CsOutpOrderDTO> list = new ArrayList<CsOutpOrderDTO>();
		
		CsOutpOrderExample example = new CsOutpOrderExample();
		example.createCriteria().andVistiIdEqualTo(visitId)
		.andValidFlgEqualTo("1");
		List<CsOutpOrder> list2 = dbCsOutpOrderRepository.getByExample(example);
		for (CsOutpOrder csOutpOrder2 : list2) {
			list.add(Utils.exchangeObject(csOutpOrder2, CsOutpOrderDTO.class));
		}
		
		return list;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override 
	public List<CsOutpOrderDtlDTO> getOrderDtList(String ordDepId) throws Exception {
		List<CsOutpOrderDtlDTO> orderDtList = new ArrayList<>();
		//封装查询条件
		CsOutpOrderDtlExample example = new CsOutpOrderDtlExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(ordDepId)){
			criteria.andOrdIdEqualTo(ordDepId);
		}else{
			throw new Exception("没有这个处方");
		}
		
		List<CsOutpOrderDtl> list = dbCsOutpOrderRepository.getOrderDtList(example);
		for (CsOutpOrderDtl csOutpOrderDtl : list) {
			CsOutpOrderDtlDTO csOutpOrderDtlDTO = Utils.exchangeObject(csOutpOrderDtl, CsOutpOrderDtlDTO.class);
		     //处方单详情
			if (!Constant.ORDER_TYPE_DIAGNOSE.equals(csOutpOrderDtlDTO.getOrdType())) { 
				//通过ord_item_id关联诊疗项表进行查询价格和项目
				TaPhaDrugInfoDTO oneDTO = medicineInfoManageService.getOneDTO(csOutpOrderDtlDTO.getOrdItemId());
				if (oneDTO != null) {
					//包装单位
					csOutpOrderDtlDTO.setMinUnit(oneDTO.getMinUnit());
					csOutpOrderDtlDTO.setPackUnit(oneDTO.getPackUnit());
					//换算量
					csOutpOrderDtlDTO.setConvQty(oneDTO.getConvQty());
					csOutpOrderDtlDTO.setTradeName(oneDTO.getTradeName());//药品名称
					csOutpOrderDtlDTO.setMinRetailPrice(oneDTO.getMinRetailPrice());
					csOutpOrderDtlDTO.setPackRetailPrice(oneDTO.getPackRetailPrice());
					//西药剂量
					if(oneDTO.getVolume()!=null){
						csOutpOrderDtlDTO.setWeight(oneDTO.getVolume().floatValue());//体积
					}
					if(oneDTO.getWeight()!=null){
						csOutpOrderDtlDTO.setVolume(oneDTO.getWeight().floatValue());//重量
					}
					//中药数量
					if(Constant.ORDER_TYPE_CY.equals(csOutpOrderDtlDTO.getOrdType())){
						if(csOutpOrderDtlDTO.getBackQty()!=null&&csOutpOrderDtlDTO.getRepetition()!=null){
							csOutpOrderDtlDTO.setBuyQty(csOutpOrderDtlDTO.getBuyQty()/csOutpOrderDtlDTO.getRepetition());
						}
					}
				}
			}else {
				if (!Utils.isBlank(csOutpOrderDtlDTO.getTradeName()) && csOutpOrderDtlDTO.getItemPrice() != null) {
					csOutpOrderDtlDTO.setPackRetailPrice(csOutpOrderDtlDTO.getItemPrice());//诊疗项价格
				}else {
					String cliChargeItemId = omDicClinicalItemMapKeyService.getChargeIdByClinicalId(csOutpOrderDtlDTO.getOrdItemId());//获得收费项id
					OmDicCliChargeItemDTO oneBeanById = omDicCliChargeItemService.getOneBeanById(cliChargeItemId);//拿到收费诊疗项
					if (oneBeanById != null) {
						csOutpOrderDtlDTO.setTradeName(oneBeanById.getItemName());//诊疗项名称
						csOutpOrderDtlDTO.setPackRetailPrice(oneBeanById.getPrice());//诊疗项价格
					}
				}
			}
			orderDtList.add(csOutpOrderDtlDTO);
		}
		return orderDtList;
	}
	
}
