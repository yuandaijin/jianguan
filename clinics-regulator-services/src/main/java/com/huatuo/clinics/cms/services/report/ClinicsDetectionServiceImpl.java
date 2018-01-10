package com.huatuo.clinics.cms.services.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huatuo.bean.XtAddressBean;
import com.huatuo.clinics.cms.bean.AssembleDTO;
import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.clinics.cms.bean.CmsJgPatrolDTO;
import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;
import com.huatuo.clinics.cms.bean.CmsOrgInfoDTO;
import com.huatuo.clinics.cms.bean.DynamicDetectionDTO;
import com.huatuo.clinics.cms.bean.MonitoringEventsDTO;
import com.huatuo.clinics.cms.bean.OpmOrgExtendInfoDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgMonitor;
import com.huatuo.clinics.cms.db.bean.MonitoringEvents;
import com.huatuo.clinics.cms.db.repository.DbCmsJgDisputeRepository;
import com.huatuo.clinics.cms.db.repository.DbCmsJgPatrolRepository;
import com.huatuo.clinics.cms.services.regulator.CmsJgDistrictService;
import com.huatuo.clinics.cms.services.regulator.CmsJgOrgService;
import com.huatuo.clinics.cms.util.Utils;
import com.huatuo.common.DateUtils;
import com.huatuo.db.bean.XtZdOrg;
import com.huatuo.pms.bo.BODoctorInfo;
import com.huatuo.pms.bo.BOUser;
import com.huatuo.pms.bo.BOZdOrg;
import com.huatuo.pms.services.DistrictService;
import com.huatuo.pms.services.DoctorService;
import com.huatuo.pms.services.UserService;
import com.huatuo.pms.services.ZdOrgService;

@Service
public class ClinicsDetectionServiceImpl implements ClinicsDetectionService {
	
	@Autowired
	private DbCmsJgDisputeRepository dbCmsJgDisputeRepository;
	
	@Autowired
	private DbCmsJgPatrolRepository dbCmsJgPatrolRepository;
	
	@Resource
	private ZdOrgService zdOrgService;
	
	@Resource
	private DoctorService doctorService;
	
	@Resource
	private DistrictService districtService;
	
	@Resource
	private OpmOrgExtendInfoService extendInfoService;
	
	@Autowired
	private CmsJgOrgService msJgOrgService;
	
	@Resource
	private CmsJgDistrictService cmsJgDistrictService;
	@Resource
	private UserService userService;
	
	
	
	
	@Override
	public List<DynamicDetectionDTO> queryDisputeNo(String beginDate, String endDate, List<String> orgId) {
		List<DynamicDetectionDTO> list=new ArrayList<DynamicDetectionDTO>();
		List<AssembleDTO> lists=dbCmsJgDisputeRepository.queryDisputeNo(beginDate,endDate,orgId);
		for (AssembleDTO assembleDTO : lists) {
			if(null != assembleDTO){
				XtZdOrg xtOrg=zdOrgService.getXtZdOrgBeanById(Integer.valueOf(assembleDTO.getOrgId()));
				String county=xtOrg.getRoad();//发生地区
				DynamicDetectionDTO dtos=new DynamicDetectionDTO();
				dtos.setId(assembleDTO.getId());
				dtos.setName("医疗争议");
				dtos.setAddress(county);
				list.add(dtos);
			}
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}


	@Override
	public List<DynamicDetectionDTO> queryInfectionNo(String beginDate, String endDate, List<String> orgId) {
		List<DynamicDetectionDTO> list=new ArrayList<DynamicDetectionDTO>();
		List<AssembleDTO> lists=dbCmsJgDisputeRepository.queryInfectionNo(beginDate,endDate,orgId);
		for (AssembleDTO assembleDTO : lists) {
			if(null != assembleDTO){
				XtZdOrg xtOrg=zdOrgService.getXtZdOrgBeanById(Integer.valueOf(assembleDTO.getOrgId()));
				String county=xtOrg.getRoad();//发生地区
				DynamicDetectionDTO dtos=new DynamicDetectionDTO();
				dtos.setId(assembleDTO.getId());
				dtos.setName("传染病");
				dtos.setAddress(county);
				list.add(dtos);
			}
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}


	@Override
	public List<MonitoringEventsDTO> queryType(List<String> idIist) {
		List<MonitoringEventsDTO> list=new ArrayList<MonitoringEventsDTO>();
		//获取id集合中的单个id
		for (String strId : idIist) {
			List<MonitoringEvents> dtoLsit=dbCmsJgDisputeRepository.queryType(strId);
			for (MonitoringEvents monitoringEvents : dtoLsit) {
				MonitoringEventsDTO reDto = Utils.exchangeObject(monitoringEvents, MonitoringEventsDTO.class);
				 XtZdOrg xtOrg=zdOrgService.getXtZdOrgBeanById(Integer.valueOf(monitoringEvents.getOrgId()));
				 String county=xtOrg.getRoad();//发生地区
				 String address =xtOrg.getAddress();//详细地址
				 String countyCode=String.valueOf(xtOrg.getRoadCode());//发生地区Id
				 String orgName=xtOrg.getName();//诊所名称
				//组装事件详情
				 String eventDtl="";
				if(reDto.getType().equals("医疗争议")){
					eventDtl=orgName+"因诊断失误发生一起医疗争议";
				}else if(reDto.getType().equals("传染病")){
					eventDtl=orgName+"诊断上报一起"+reDto.getName()+"病例";
				}else if(reDto.getType().equals("抗生素")){
					//获取医生信息
					BODoctorInfo doctor =doctorService.getDoctorById(reDto.getDoctorId());
					eventDtl=doctor.getName()+"医生不具备"+reDto.getName()+"抗生素处方权，违规开具该药品";
				}
				reDto.setEventDtl(eventDtl);
				reDto.setRegion(county);
				reDto.setOrgName(orgName);
				reDto.setAddress(address);
				reDto.setRegionId(countyCode);
				reDto.setId(strId);
				list.add(reDto);
			}
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}


	@Override
	public int insertMonitor(List<MonitoringEventsDTO> monList) {
		int j=0;
		for (int i = 0; i < monList.size(); i++) {
			MonitoringEventsDTO moDto=monList.get(i);
			CmsJgMonitor dto=new CmsJgMonitor();
			dto.setId(UUID.randomUUID().toString());
			dto.setOrgId(moDto.getOrgId());
			dto.setOrgName(moDto.getOrgName());
			dto.setType(moDto.getType());
			dto.setTypeId(moDto.getId());
			dto.setDate(moDto.getDate());
			dto.setAddressid(moDto.getRegionId());
			dto.setAddress(moDto.getRegion());
			dto.setAddressDtl(moDto.getAddress());
			dto.setEbentDtl(moDto.getEventDtl());
			//先根据事件id查询是否已经存在，如果存在，则不保存
			List<CmsJgMonitor> lists=dbCmsJgDisputeRepository.queryTypeMonitor(moDto.getId());
			if(lists.size() <= 0){
				j+=dbCmsJgDisputeRepository.insertMonitor(dto);
			}
			
		}
		if(j > 0){
			return j;
		}
		return 0;
	}


	@Override
	public List<CmsJgMonitor> queryTypeMonitor(List<String> idIist) {
		List<CmsJgMonitor> list=new ArrayList<CmsJgMonitor>();
		for (String str : idIist) {
			 List<CmsJgMonitor> dto=dbCmsJgDisputeRepository.queryTypeMonitor(str);
			 for (CmsJgMonitor cmsJgMonitor : dto) {
				 list.add(cmsJgMonitor);
			}
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}


	@Override
	public List<DynamicDetectionDTO> queryAntibiotic(String beginDate,
			String endDate, List<String> orgId, String antibioticId) {
		List<DynamicDetectionDTO> list=new ArrayList<DynamicDetectionDTO>();
		List<AssembleDTO> lists=dbCmsJgDisputeRepository.queryAntibiotic(beginDate,endDate,orgId,antibioticId);
		for (AssembleDTO assembleDTO : lists) {
			if(null != assembleDTO){
				boolean flag=false;//标识，true为抗生素违规，false为未违规
				XtZdOrg xtOrg=zdOrgService.getXtZdOrgBeanById(Integer.valueOf(assembleDTO.getOrgId()));
				String county=xtOrg.getRoad();//发生地区
				//查询医生信息,获取医生职称，判断是否违规开具抗生素药品
				BODoctorInfo doctor =doctorService.getDoctorById(assembleDTO.getDoctorId());//获取医生信息
				String  titlesClinical=String.valueOf(doctor.getTitlesClinical());//医生职称
				String  level=assembleDTO.getLevel();//抗生素级别  (初级对应0级药品、中级对应0,1级药品、高级对应0,1,2,级药品)
				if(titlesClinical.equals("5") && !level.equals("0")){
					flag=true;
				}else if(titlesClinical.equals("6") && level.equals("2")){
					flag=true;
				}else if(titlesClinical.equals("7")){
					flag=true;
				}
				if(flag){
					DynamicDetectionDTO dtos=new DynamicDetectionDTO();
					dtos.setId(assembleDTO.getId());
					dtos.setName("抗生素");
					dtos.setAddress(county);
					list.add(dtos);
				}
			}
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}


	@Override
	public List<CmsJgMonitor> queryMonitorDtl(String date, String type,
			String name, String addressId) {
		List<CmsJgMonitor> lists=dbCmsJgDisputeRepository.queryMonitorDtl(date,type,name,addressId);
		if(lists.size() > 0){
			return lists;
		}
		return null;
	}


	@Override
	public CmsOrgInfoDTO queryClinicsInfo(RuquestBeanDTO beanDTO) {
		String orgId = beanDTO.getOrgId();
		String startTime = null;
		String endTime = null;
		
		if("".equals(beanDTO.getStartTime()) || beanDTO.getStartTime() == null) {
			startTime = DateUtils.getCurrYearFirst() +" 00:00:00";
		} else {
			startTime = beanDTO.getStartTime()+" 00:00:00";
		}
		
		if("".equals(beanDTO.getEndTime()) || beanDTO.getEndTime() == null) {
			endTime = DateUtils.getNowDate() +" 23:59:59";
		} else {
			endTime = beanDTO.getEndTime()+" 23:59:59";
		}
		beanDTO.setStartTime(startTime);
		beanDTO.setEndTime(endTime);
		
		CmsOrgInfoDTO cmsOrgInfoDTO = new CmsOrgInfoDTO();
		BOZdOrg zdOrg=zdOrgService.findById(Integer.parseInt(orgId));  // 获取机构基本信息
		OpmOrgExtendInfoDTO extendInfoDTO = extendInfoService.getDtoByOrgId(orgId);   // 获取机构扩展信息
		String userId = dbCmsJgPatrolRepository.getUserIdByOrgId(orgId, "01001-0002");  // 获取机构负责人userId(最先入职的员工就是机构负责人)
		
		// 通过userid获取用户信息
		if(userId != null) {
			BOUser bouser = userService.getUserById(Long.parseLong(userId)); 
			if(!StringUtils.isEmpty(bouser))  {
				cmsOrgInfoDTO.setLinkMan(bouser.getName()); // 机构联系人
			}
		}
		
		
		if(0 != zdOrg.getAddressId()){
			//根据获取的addressid获取具体的地址信息
			XtAddressBean dto=districtService.getXtAddress(zdOrg.getAddressId());
			//拼接地址信息
			String addressName="";
			if(null == dto.getRoad()){
				addressName=dto.getProvince()+dto.getCity()+dto.getCounty()+dto.getAddress();
			}else{
				addressName=dto.getProvince()+dto.getCity()+dto.getCounty()+dto.getRoad()+dto.getAddress();
			}
			//单独判断是全国的地址
			if(dto.getProvince().equals("全国")){
				addressName=dto.getProvince()+dto.getAddress();
			}
			cmsOrgInfoDTO.setAddress(addressName);
		}
		cmsOrgInfoDTO.setOrgName(zdOrg.getName()); // 机构名称
		cmsOrgInfoDTO.setTelephone(zdOrg.getTelephone()); // 联系电话
		cmsOrgInfoDTO.setLongitude(zdOrg.getLongitude()); // 经度
		cmsOrgInfoDTO.setLatitude(zdOrg.getLatitude()); // 纬度
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		if(!StringUtils.isEmpty(extendInfoDTO)) {
			cmsOrgInfoDTO.setOrgCredentialsNo(extendInfoDTO.getOrgCredentialsNo());//诊所资质编号
			Date credentialsTime = extendInfoDTO.getOrgCredentialsTime();
			Date credentialsApply = extendInfoDTO.getOrgCredentialsApply();
			
			if(credentialsApply != null) {
				cmsOrgInfoDTO.setOrgCredentialsApply(sdf.format(credentialsApply));  // 资质到期时间
			}
			
			// 判断资质到期时间 和当前时间的大小(到期状态 0正常 1已到期 2即将到期（提前三个月通知）)
			if(credentialsTime != null) {
				cmsOrgInfoDTO.setOrgCredentialsTime(sdf.format(credentialsTime));  
				
				long threeMonsthTime = DateUtils.getBeforeMonthDay(-3);  // 获取今天三个月后的日期时间戳
				long nowTime = new Date().getTime(); // 获取当前时间的时间戳
				long expireTime = credentialsTime.getTime(); // 获取过期时间的时间戳
				
				
				if(expireTime > threeMonsthTime) {
					// 过期时间>三个月之后的时候（即将过期）（正常）
					cmsOrgInfoDTO.setExpireStatus(0);
				}  else if(expireTime < nowTime) {
					// 过期时间<当前时间（已过期）
					cmsOrgInfoDTO.setExpireStatus(1);
				} else{
					// 当前时间<过期时间<三个月之后的时候（即将过期）
					cmsOrgInfoDTO.setExpireStatus(2);
				}
			} else {
				cmsOrgInfoDTO.setExpireStatus(1);
			}
		} else {
			cmsOrgInfoDTO.setExpireStatus(0);
		}
		
		beanDTO.setOrgId(orgId);
		//int isPatrol = dbCmsJgPatrolRepository.isPatrol(beanDTO);  // 判断该诊所是否已巡查
		
		List<Map<String, Object>> results = dbCmsJgPatrolRepository.getPatrolTime(beanDTO); // 获取最近两次巡查时间
		if(results.size() > 0) {
			cmsOrgInfoDTO.setPatrolStatus(1); // 巡查
			String latelyPartolTime = results.get(0).get("patrol_time").toString(); // 最近的巡查时间
			if(results.size() == 1) {
				cmsOrgInfoDTO.setLatelyPatrolTime(latelyPartolTime); // 上一次巡查时间
				cmsOrgInfoDTO.setLastPatrolTime(latelyPartolTime); // 最近巡查时间
			} else {
				cmsOrgInfoDTO.setLatelyPatrolTime(latelyPartolTime); // 上一次巡查时间
				cmsOrgInfoDTO.setLastPatrolTime(results.get(1).get("patrol_time").toString()); // 最近巡查时间
			}
		} else {
			cmsOrgInfoDTO.setPatrolStatus(0); // 未巡查
		}
		
		return cmsOrgInfoDTO;
	}


	@Override
	public List<CmsOrgInfoDTO> queryCLinicsInMap(CmsJgUserinfoDTO person,RuquestBeanDTO beanDTO) {
		String startTime = null;
		String endTime = null;
		
		if("".equals(beanDTO.getStartTime()) || beanDTO.getStartTime() == null) {
			startTime = DateUtils.getCurrYearFirst() +" 00:00:00";
		} else {
			startTime = beanDTO.getStartTime()+" 00:00:00";
		}
		
		if("".equals(beanDTO.getEndTime()) || beanDTO.getEndTime() == null) {
			endTime = DateUtils.getNowDate() +" 23:59:59";
		} else {
			endTime = beanDTO.getEndTime()+" 23:59:59";
		}
		beanDTO.setStartTime(startTime);
		beanDTO.setEndTime(endTime);
		
		List<CmsOrgInfoDTO> cmsOrgInfoDTOs = new ArrayList<>();
		
		
		// 获取单独某个诊所的信息
		if(!StringUtils.isEmpty(beanDTO.getOrgId())) {
			String orgId = beanDTO.getOrgId();
			BOZdOrg zdOrg=zdOrgService.findById(Integer.parseInt(orgId));
			patrolInfoCommonMethod(orgId, zdOrg, beanDTO, cmsOrgInfoDTOs);
			
		} else {
			// 获取所有诊所信息
			//根据当前用户查询机构地址
			CmsJgOrgDTO dto= msJgOrgService.queryOrgById(person.getCompany());
			//查询区下面所有的镇
			List<CmsJgDistrictDTO> list = cmsJgDistrictService.getDistrictByParent(dto.getCountyCode());  // 针对蒙自是用countrycode
			//List<CmsJgDistrictDTO> list = cmsJgDistrictService.getDistrictByParent(dto.getCityCode());  // 针对成都是用citycode(用于测试)
			if(list!=null && !list.isEmpty()) {
				for (CmsJgDistrictDTO district : list) {
					List<BOZdOrg> orgList=zdOrgService.queryOrg(district.getId().toString(),true);
					if(orgList!=null && !orgList.isEmpty()) {
						for (BOZdOrg org : orgList) {
							String orgId = org.getId()+"";
							patrolInfoCommonMethod(orgId, org, beanDTO, cmsOrgInfoDTOs);
						}
						
					}
					
				}
			}
		}
		
		return cmsOrgInfoDTOs;
	}
	
	
	/**
	 * 获取诊所扩展信息及巡查信息的公共方法
	 * @param orgId
	 * @param org
	 * @param beanDTO
	 * @param cmsOrgInfoDTOs
	 */
	public void patrolInfoCommonMethod(String orgId,BOZdOrg org,RuquestBeanDTO beanDTO,List<CmsOrgInfoDTO> cmsOrgInfoDTOs) {
		CmsOrgInfoDTO cmsOrgInfoDTO = new CmsOrgInfoDTO();
		// 诊所基本信息
		cmsOrgInfoDTO.setOrgId(orgId);  // 机构id
		cmsOrgInfoDTO.setOrgName(org.getName()); // 机构名称
		cmsOrgInfoDTO.setLongitude(org.getLongitude()); // 经度
		cmsOrgInfoDTO.setLatitude(org.getLatitude()); // 纬度
		
		OpmOrgExtendInfoDTO extendInfoDTO = extendInfoService.getDtoByOrgId(orgId);  // 获取机构扩展信息
		
		if(!StringUtils.isEmpty(extendInfoDTO)) {
			Date credentialsTime = extendInfoDTO.getOrgCredentialsTime();
			// 判断资质到期时间 和当前时间的大小(到期状态 0正常 1已到期 2即将到期（提前三个月通知）)
			if(credentialsTime != null) {
				long threeMonsthTime = DateUtils.getBeforeMonthDay(-3);  // 获取今天三个月后的日期时间戳
				long nowTime = new Date().getTime(); // 获取当前时间的时间戳
				long expireTime = credentialsTime.getTime(); // 获取过期时间的时间戳
				
				
				if(expireTime > threeMonsthTime) {
					// 过期时间>三个月之后的时候（即将过期）（正常）
					cmsOrgInfoDTO.setExpireStatus(0);
				}  else if(expireTime < nowTime) {
					// 过期时间<当前时间（已过期）
					cmsOrgInfoDTO.setExpireStatus(1);
				} else{
					// 当前时间<过期时间<三个月之后的时候（即将过期）
					cmsOrgInfoDTO.setExpireStatus(2);
				}
			} else {
				cmsOrgInfoDTO.setExpireStatus(0);
			}
		} else {
			cmsOrgInfoDTO.setExpireStatus(0);
		}
		beanDTO.setOrgId(orgId);
		int isPatrol = dbCmsJgPatrolRepository.isPatrol(beanDTO);  // 判断该诊所是否已巡查
		if(isPatrol > 0) {
			cmsOrgInfoDTO.setPatrolStatus(1); // 已巡查
		} else {
			cmsOrgInfoDTO.setPatrolStatus(0); // 未巡查
		}
		cmsOrgInfoDTOs.add(cmsOrgInfoDTO);
	}


	@Override
	public void addPatrolInfo(CmsJgPatrolDTO patrolDTO) {
		patrolDTO.setFinishStatus(1);
		dbCmsJgPatrolRepository.addPatrolInfo(patrolDTO);
	}

}
