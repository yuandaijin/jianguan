package com.huatuo.clinics.cms.db.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.bean.ClinicResourcesInfoDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.bean.mrcDTO;
import com.huatuo.clinics.cms.bean.ReportResult2DTO;
import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.db.bean.SmCliAreaCover;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.mapper.SmCliAreaCoverMapper;
import com.huatuo.clinics.cms.db.repository.ClinicsInfoRepository;
import com.huatuo.common.Utils;


@Repository
public class ClinicsInfoRepositoryImpl implements ClinicsInfoRepository {
	
	@Autowired
	private SmCliAreaCoverMapper smCliAreaCoverMapper;
	
	@Override
	public List<ReportResultDB> queryNameAndQty(String year, String month,
			String day, String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		if(!Utils.isBlank(year)){
			map.put("year", year);
		}
		if(!Utils.isBlank(month)){
			map.put("month", month);
		}
		if(!Utils.isBlank(day)){
			map.put("day", day);
		}
		if(!Utils.isBlank(parentAreaCode)){
			map.put("parentAreaCode", parentAreaCode);
		}
		return smCliAreaCoverMapper.queryNameAndQty(map);
	}

	@Override
	public List<ReportResultDB> queryIncidence(String year, String month,
			String day, String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.queryIncidence(map);
	}

	@Override
	public List<ReportResultDB> queryGrade(String year, String month,String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.queryGrade(map);
	}

	@Override
	public List<ReportResultDB> queryOperate(String year, String month,
			String day, String parentAreaCode,String sourceCode, boolean flag) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		if(null !=year){
			map.put("year", year);
		}
		if(null !=month){
			map.put("month", month);
		}
		if(null !=day){
			map.put("day", day);
		}
		map.put("parentAreaCode", parentAreaCode);
		map.put("sourceCode", sourceCode);
		map.put("flag", flag ? 0 : 1);
		return smCliAreaCoverMapper.queryOperate(map);
	}

	@Override
	public List<SmCliAreaCover> select(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.select(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDTO> queryType(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.queryType(ruquestBeanDTO);
	}

	@Override
	public List<ClinicResourcesInfoDTO> Information(RuquestBeanDTO ruquestBeanDTO) {
		Map<String,ClinicResourcesInfoDTO> results=new HashMap<String,ClinicResourcesInfoDTO>();
		List<Map<String, String>> result=smCliAreaCoverMapper.Information(ruquestBeanDTO);
		for(Map hashMap:result){
			if(hashMap.get("source")==null){
				continue;
			}
			ClinicResourcesInfoDTO clinicResourcesInfoDTO=results.get((String) hashMap.get("area_code"));
			if(clinicResourcesInfoDTO==null){
				ClinicResourcesInfoDTO clinicResourcesInfoDTOTemp=new ClinicResourcesInfoDTO();
				results.put((String) hashMap.get("area_code"),clinicResourcesInfoDTOTemp);
				clinicResourcesInfoDTO=clinicResourcesInfoDTOTemp;
			}
			clinicResourcesInfoDTO.setAreaName((String)hashMap.get("area_name"));
			clinicResourcesInfoDTO.setAreaCode((String)hashMap.get("area_code"));
			clinicResourcesInfoDTO.setCliQty(hashMap.get("clinic_qty").toString());
			if(((String)hashMap.get("source")).equals("00040")){
				if(((String)hashMap.get("type")).equals("00040-0001")){
					clinicResourcesInfoDTO.setPersonal(hashMap.get("qty").toString().toString());
				}
				if(((String)hashMap.get("type")).equals("00040-0002")){
					clinicResourcesInfoDTO.setSocial((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00040-0003")){
					clinicResourcesInfoDTO.setFoundOther((String) hashMap.get("qty").toString());
				}
			}
			if(((String)hashMap.get("source")).equals("00041")){
				if(((String)hashMap.get("type")).equals("00041-0001")){
					clinicResourcesInfoDTO.setOrdinary((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00041-0002")){
					clinicResourcesInfoDTO.setChinaDoctor((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00041-0003")){
					clinicResourcesInfoDTO.setMedicine((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00041-0004")){
					clinicResourcesInfoDTO.setMouth((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00041-0005")){
					clinicResourcesInfoDTO.setOtherType((String) hashMap.get("qty").toString());
				}
			}
			if(((String)hashMap.get("source")).equals("00042")){
				if(((String)hashMap.get("type")).equals("00042-0001")){
					clinicResourcesInfoDTO.setOneStar((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00042-0002")){
					clinicResourcesInfoDTO.setTwoStar((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00042-0003")){
					clinicResourcesInfoDTO.setThereStars((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00042-0004")){
					clinicResourcesInfoDTO.setFourStars((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00042-0005")){
					clinicResourcesInfoDTO.setFiveStars((String) hashMap.get("qty").toString());
				}
			}
			if(((String)hashMap.get("source")).equals("00043")){
				if(((String)hashMap.get("type")).equals("00043-0001")){
					clinicResourcesInfoDTO.setSmall((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00043-0002")){
					clinicResourcesInfoDTO.setMidsize((String) hashMap.get("qty").toString());
				}
				if(((String)hashMap.get("type")).equals("00043-0003")){
					clinicResourcesInfoDTO.setBig((String) hashMap.get("qty").toString());
				}
			}
		}
		ArrayList<ClinicResourcesInfoDTO> list = new ArrayList<ClinicResourcesInfoDTO>();
		for(String key : results.keySet()){
			//未评级
			ClinicResourcesInfoDTO clinicResourcesInfoDTO=results.get(key);
			clinicResourcesInfoDTO.rateD();
		list.add(results.get(key));
		}
		return list;
	}

	@Override
	public List<ReportResultDTO> administrativeJson(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.administrativeJson(ruquestBeanDTO);
	}
	
	@Override
	public String queryQuality(String year, String month, String day,
			String parentAreaCode, String code, String type) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		map.put("parentAreaCode", parentAreaCode);
		map.put("code", code);
		map.put("type", type);
		return smCliAreaCoverMapper.queryQuality(map);
	}

	@Override
	public ReportResult2DTO timeseries(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.timeseries(ruquestBeanDTO);
	}

	@Override
	public mrcDTO areaPopulationStation(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.areaPopulationStation(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDTO> areaDoctorTitle(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.areaDoctorTitle(ruquestBeanDTO);
	}

	@Override
	public List<Map<String, Object>> PositionDistribution(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.PositionDistribution(ruquestBeanDTO);
	}

	@Override
	public List<Map<String, String>> DoctorTitle(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.DoctorTitle(ruquestBeanDTO);
	}

	@Override
	public List<SmCliAreaCover> selectTotal(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.selectTotal(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDTO> queryTypeTotal(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.queryTypeTotal(ruquestBeanDTO);
	}
	
	@Override
	public String queryIncidenceSum(String year, String month, String day,
			String parentAreaCode) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		map.put("parentAreaCode", parentAreaCode);
		return smCliAreaCoverMapper.queryIncidenceSum(map);
	}

	@Override
	public List<Map<String, String>> headcountAvg(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.headcountAvg(ruquestBeanDTO);
	}

	@Override
	public List<Map<String, String>> doctorAbility(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.doctorAbility(ruquestBeanDTO);
	}

	@Override
	public List<Map<String, String>> PersonnelForm(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.PersonnelForm(ruquestBeanDTO);
	}

	@Override
	public Map<String, String> timeseriesPerson(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.timeseriesPerson(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDTO> antibioticUse(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.antibioticUse(ruquestBeanDTO);
	}

	@Override
	public List<ReportResult2DTO> transfusion(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.transfusion(ruquestBeanDTO);
	}


	@Override
	public List<ReportResultDB> antibioticTransfusion(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.antibioticTransfusion(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDB> roportionAntibioticUse(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.roportionAntibioticUse(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDB> transfusionPrescriptions(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.transfusionPrescriptions(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDB> antibioticTransfusionUse(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.antibioticTransfusionUse(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDB> TransfusionUseCg(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.TransfusionUseCg(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDB> RationalForm(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.RationalForm(ruquestBeanDTO);
	}

	@Override 
	public List<ReportResultDTO> AreaGeneralSituation(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.AreaGeneralSituation(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDB> lowerRegion(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.lowerRegion(ruquestBeanDTO);
	}

	@Override
	public ReportResultDB expire(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.expire(ruquestBeanDTO);
	}

	@Override
	public List<Map<String, String>> listTimeClinics(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.listTimeClinics(ruquestBeanDTO);
	}

	@Override
	public List<ReportResultDB> OverviewClinicMedicalQuality(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.OverviewClinicMedicalQuality(ruquestBeanDTO);
	}
	@Override
	public List<Map<String, String>> ListOverviewClinicMedicalQuality(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.ListOverviewClinicMedicalQuality(ruquestBeanDTO);
	}

	@Override
	public List<SmCliAreaCover> CoverageJson(RuquestBeanDTO ruquestBeanDTO) {
		return smCliAreaCoverMapper.CoverageJson(ruquestBeanDTO);
	}


	@Override
	public List<ReportResultDB> queryOperates(String year, String month,
			String parentAreaCode, String totalCode, String reportType) {
		Map<String,Object> map=new HashMap<String,Object>(); 
		if(null !=year){
			map.put("year", year);
		}
		if(null !=month){
			map.put("month", month);
		}
		map.put("parentAreaCode", parentAreaCode);
		map.put("reportType", reportType);
		map.put("sourceCode", totalCode);
		return smCliAreaCoverMapper.queryOperates(map);
	}

}
