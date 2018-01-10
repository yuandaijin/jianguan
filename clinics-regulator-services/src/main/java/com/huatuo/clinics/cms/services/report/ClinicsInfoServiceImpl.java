package com.huatuo.clinics.cms.services.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.huatuo.bean.XtAddressBean;
import com.huatuo.clinics.cms.bean.AeraTiltleDTO;
import com.huatuo.clinics.cms.bean.ClinicResourcesInfoDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.bean.mrcDTO;
import com.huatuo.clinics.cms.bean.ReportResult2DTO;
import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.db.bean.SmCliAreaCover;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.repository.ClinicsInfoRepository;
import com.huatuo.clinics.cms.util.Utils;
import com.huatuo.pms.bo.BOZdOrg;
import com.huatuo.pms.services.DistrictService;
import com.huatuo.pms.services.ZdOrgService;

@Service
public class ClinicsInfoServiceImpl implements ClinicsInfoService {
	
	@Autowired
	private ClinicsInfoRepository clinicsInfoRepository;
	@Resource
	private ZdOrgService ZdOrgService;
	@Resource
	private DistrictService districtService;
	@Override
	public List<ReportResultDTO> queryNameAndQty(String year, String month,
			String day, String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsInfoRepository.queryNameAndQty(year,month,day,parentAreaCode);
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List<ReportResultDTO> queryIncidence(String year, String month,
			String day, String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsInfoRepository.queryIncidence(year,month,day,parentAreaCode);
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List<ReportResultDTO> queryGrade(String year, String month,String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsInfoRepository.queryGrade(year,month,parentAreaCode);
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List<ReportResultDTO> queryOperate(String year, String month,
			String day, String parentAreaCode,String sourceCode,boolean flag) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsInfoRepository.queryOperate(year,month,day,parentAreaCode,sourceCode,flag);
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List CoverageJson(RuquestBeanDTO ruquestBeanDTO) {
		List<HashMap<String, Object>> result=new ArrayList<HashMap<String, Object>>();
		List<SmCliAreaCover> smCliAreaCovers=clinicsInfoRepository.CoverageJson(ruquestBeanDTO);
		for (int i = 0; i < smCliAreaCovers.size(); i++) {
			SmCliAreaCover smCliAreaCover = smCliAreaCovers.get(i);
			HashMap<String, Object> resul=new HashMap<String, Object>();
			resul.put("areaName", smCliAreaCover.getAreaName());
			resul.put("doctorQty", smCliAreaCover.getDoctorQty());
			resul.put("nurseQty", smCliAreaCover.getNurseQty());
			resul.put("cliqty", smCliAreaCover.getClinicQty());
			resul.put("doctorQty", smCliAreaCover.getDoctorQty());
			resul.put("mcr",smCliAreaCover.getDoctorQty()+":"+smCliAreaCover.getNurseQty());
//			NumberFormat formatter = new DecimalFormat("0.0");
//			if(smCliAreaCover.getDoctorQty().intValue()!=0&&smCliAreaCover.getNurseQty().intValue()!=0){
//				Float x=new Float(smCliAreaCover.getDoctorQty()/smCliAreaCover.getNurseQty());
//				//医护比
//				resul.put("DNratio", formatter.format(x)+":1");
//			}else {
//				resul.put("DNratio", "0:0");
//			}
			result.add(resul);
		}
		return result;
	}

@Override
public List ClinicQtyJson(RuquestBeanDTO ruquestBeanDTO) {
	List<HashMap<String, Object>> result=new ArrayList<HashMap<String, Object>>();
	List<SmCliAreaCover> smCliAreaCovers=clinicsInfoRepository.selectTotal(ruquestBeanDTO);
	//个体诊所数量总计
	HashMap<String, Object> CliCount=new HashMap<String, Object>();
	Integer ClinicQty =0;;
	if(smCliAreaCovers.size()==0){
		CliCount.put("now", 0);
		CliCount.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	}else {
		for(SmCliAreaCover smCliAreaCover:smCliAreaCovers){
			ClinicQty=ClinicQty+smCliAreaCover.getClinicQty();
		}
		CliCount.put("now", ClinicQty);
		CliCount.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	}
	result.add(CliCount);
	//个体诊所数量环比
	HashMap<String, Object> CliYearRatioResult=new HashMap<String, Object>();
	HashMap<String, Object> CliYearRatio=new HashMap<String, Object>();
	RuquestBeanDTO ruquestBeanDTObak=yearRatioTime(ruquestBeanDTO.clone());
	List<SmCliAreaCover> smCliAreaCovers2=clinicsInfoRepository.selectTotal(ruquestBeanDTObak);
	Integer YearRatioClinicQty =0;
	if(smCliAreaCovers2.size()==0){
		CliYearRatio.put("lastMonth", 0);
	}else {
		for(SmCliAreaCover smCliAreaCover:smCliAreaCovers2){
			YearRatioClinicQty=YearRatioClinicQty+smCliAreaCover.getClinicQty();
		}
		CliYearRatio.put("lastMonth", YearRatioClinicQty);
	}
	CliYearRatio.put("now", ClinicQty);
	CliYearRatio.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	CliYearRatio.put("lastMonthTime", ruquestBeanDTObak.getYear()+"年"+ruquestBeanDTObak.getMonth()+"月");
	CliYearRatioResult.put("CliYearRatioResult", CliYearRatio);
	result.add(CliYearRatioResult);
	//个体诊所数量同比
	HashMap<String, Object> CliYearBasisResult=new HashMap<String, Object>();
	HashMap<String, Object> CliYearBasis=new HashMap<String, Object>();
	Integer YearBasisClinicQty =0;;
	RuquestBeanDTO ruquestBeanDTObak2=yearBasisTime(ruquestBeanDTO.clone());
	List<SmCliAreaCover> smCliAreaCoversYearBasis=clinicsInfoRepository.selectTotal(ruquestBeanDTObak2);
	if(smCliAreaCoversYearBasis.size()==0){
		CliYearBasis.put("lastYear",0);
	}else {
		for(SmCliAreaCover smCliAreaCover:smCliAreaCoversYearBasis){
			YearBasisClinicQty=YearBasisClinicQty+smCliAreaCover.getClinicQty();
		}
		CliYearBasis.put("lastYear", YearBasisClinicQty);
	}
	CliYearBasis.put("lastYearTime", ruquestBeanDTObak2.getYear()+"年"+ruquestBeanDTObak2.getMonth()+"月");
	CliYearBasis.put("now", ClinicQty);
	CliYearBasis.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	CliYearBasisResult.put("CliYearBasisResult", CliYearBasis);
	result.add(CliYearBasisResult);
	//个体诊所数量环比、同比概况
//	HashMap<String, Object> probability=new HashMap<String, Object>();
//	HashMap<String, Object> probabilityTemp=new HashMap<String, Object>();
//	probabilityTemp.put("count", ClinicQty);
//	if(ClinicQty!=0&&YearRatioClinicQty!=0){
//	probabilityTemp.put("yearRatio", ((ClinicQty-YearRatioClinicQty)/YearRatioClinicQty));
//	}else {
//		probabilityTemp.put("yearRatio", "0%");
//	}
//	if(ClinicQty!=0&&YearBasisClinicQty!=0){
//	probabilityTemp.put("yearBasis", (ClinicQty-YearBasisClinicQty)/YearBasisClinicQty);
//	}else {
//		probabilityTemp.put("yearBasis", "0%");
//	}
//	probability.put("probability", probabilityTemp);
	//result.add(probability);
	return result;
}
/**
 * 同比
 */
private RuquestBeanDTO yearBasisTime(RuquestBeanDTO ruquestBeanDTO){
		ruquestBeanDTO.setYear(Integer.parseInt(ruquestBeanDTO.getYear())-1+"");
		if(ruquestBeanDTO.getStartTime()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar=Calendar.getInstance();
			calendar.set(Integer.parseInt(ruquestBeanDTO.getStartTime().split("-")[0]), Integer.parseInt(ruquestBeanDTO.getStartTime().split("-")[1]),Integer.parseInt(ruquestBeanDTO.getStartTime().split("-")[2]));
			calendar.add(Calendar.MONTH, -13);
			ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
		}
	return ruquestBeanDTO;
}
/**
 * 环比
 */
private RuquestBeanDTO yearRatioTime(RuquestBeanDTO ruquestBeanDTO){
	//年度环比(上一年)
	if(ruquestBeanDTO.getReportType()!=null&&ruquestBeanDTO.getReportType().equals("year")){
		ruquestBeanDTO.setYear(Integer.parseInt(ruquestBeanDTO.getYear())-1+"");
		if(ruquestBeanDTO.getStartTime()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar=Calendar.getInstance();
			calendar.set(Integer.parseInt(ruquestBeanDTO.getStartTime().split("-")[0]), Integer.parseInt(ruquestBeanDTO.getStartTime().split("-")[1]),31);
			calendar.add(Calendar.MONTH, -13);
			ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
		}
		return ruquestBeanDTO;
	}
	if(ruquestBeanDTO.getStartTime()!=null){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.set(Integer.parseInt(ruquestBeanDTO.getStartTime().split("-")[0]), Integer.parseInt(ruquestBeanDTO.getStartTime().split("-")[1]),31);
		calendar.add(Calendar.MONTH, -2);
		ruquestBeanDTO.setStartTime(sdf.format(calendar.getTime()));
	}
	if(ruquestBeanDTO.getMonth()!=null){
	if(ruquestBeanDTO.getMonth().equals("1")){
		ruquestBeanDTO.setYear(Integer.parseInt(ruquestBeanDTO.getYear())-1+"");
		ruquestBeanDTO.setMonth("12");
	}else {
		ruquestBeanDTO.setMonth(Integer.parseInt(ruquestBeanDTO.getMonth())-1+"");
	}}
	return ruquestBeanDTO;
}

@Override
public Map<String, Object> ClinicTypeJson(RuquestBeanDTO ruquestBeanDTO) {
	HashMap<String, Object> resultMap=new HashMap<String, Object>();
	ruquestBeanDTO.setType("00040");
	List<ReportResultDTO> units=clinicsInfoRepository.queryTypeTotal(ruquestBeanDTO);
	resultMap.put("units", units);
	ruquestBeanDTO.setType("00041");
	List<ReportResultDTO> types=clinicsInfoRepository.queryTypeTotal(ruquestBeanDTO);
	resultMap.put("types", types);
	ruquestBeanDTO.setType("00042");
	List<ReportResultDTO> levels=clinicsInfoRepository.queryTypeTotal(ruquestBeanDTO);
	resultMap.put("levels", levels);
	ruquestBeanDTO.setType("00043");
	List<ReportResultDTO> scales=clinicsInfoRepository.queryTypeTotal(ruquestBeanDTO);
	resultMap.put("scales", scales);
	return resultMap;
}
private List<ReportResultDTO> queryType(RuquestBeanDTO ruquestBeanDTO){
	return clinicsInfoRepository.queryType(ruquestBeanDTO);
}

@Override
public List<ReportResultDTO> administrativeJson(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResultDTO> smCliAreaCoverS =clinicsInfoRepository.administrativeJson(ruquestBeanDTO);
	 return smCliAreaCoverS;
}

@Override
public Object Information(RuquestBeanDTO ruquestBeanDTO) {
	List<ClinicResourcesInfoDTO> result=clinicsInfoRepository.Information(ruquestBeanDTO);
	return result;
}

@Override
public Object timeseries(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResult2DTO> resultMap=new ArrayList<ReportResult2DTO>();
	if(ruquestBeanDTO.getReportType().equals("year")){
		ruquestBeanDTO.setMonth(null);
		Integer count=Integer.parseInt(ruquestBeanDTO.getEndYear())-Integer.parseInt(ruquestBeanDTO.getStartYear());
		for(int i=0;i<=count;i++){
			ruquestBeanDTO.setYear(Integer.parseInt(ruquestBeanDTO.getStartYear())+i+"");
			ReportResult2DTO temp =clinicsInfoRepository.timeseries(ruquestBeanDTO);
			temp.setDate(Integer.parseInt(ruquestBeanDTO.getStartYear())+i+"");
			resultMap.add(temp);
		}
	}else {
			int month =1;
				month=Integer.parseInt(ruquestBeanDTO.getStartMonth());
			for(;month<13;month++){
				if(month>Integer.parseInt(ruquestBeanDTO.getEndMonth())){
					break;
				}
				ruquestBeanDTO.setMonth(month+"");
				ruquestBeanDTO.setYear(Integer.parseInt(ruquestBeanDTO.getStartYear())+"");
				ReportResult2DTO  temp =clinicsInfoRepository.timeseries(ruquestBeanDTO);
				temp.setDate(Integer.parseInt(ruquestBeanDTO.getStartYear())+"-"+month);
				resultMap.add(temp);
			}
			ruquestBeanDTO.setYear(Integer.parseInt(ruquestBeanDTO.getStartYear())+"");
		}
	return resultMap;
	}

@Override
public String queryQuality(String year, String month, String day,
		String parentAreaCode, String code, String type) {
	String qty =clinicsInfoRepository.queryQuality(year,month,day,parentAreaCode,code,type);
	if(null != qty){
		return qty;
	}  
	return null;
}

@Override
public Object areaPopulationStation(RuquestBeanDTO ruquestBeanDTO) {
	Map<String, Object> resultList=new HashMap<String, Object>();
	Map<String, Object> result=new HashMap<String, Object>();
	mrcDTO MrcDTO=clinicsInfoRepository.areaPopulationStation(ruquestBeanDTO);
	Map<String, Object> yearBasisMrc=new HashMap<String, Object>();
	Map<String, Object> yearRatioMrc=new HashMap<String, Object>();
	RuquestBeanDTO yearBasisRuquest=yearBasisTime(ruquestBeanDTO.clone());
	RuquestBeanDTO yearRatioRuquest=yearRatioTime(ruquestBeanDTO.clone());
	result.put("now", MrcDTO);
	result.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	mrcDTO yearBasisMrcDTO =clinicsInfoRepository.areaPopulationStation(yearBasisRuquest);
	if(yearBasisMrcDTO==null){
		yearBasisMrcDTO=new mrcDTO();
	}
	mrcDTO yearRatioMrcDTO=clinicsInfoRepository.areaPopulationStation(yearRatioRuquest);
	if(yearRatioMrcDTO==null){
		yearRatioMrcDTO=new mrcDTO();
	}
	yearBasisMrc.put("lastYear", yearBasisMrcDTO);
	yearBasisMrc.put("lastYearTime", yearBasisRuquest.getYear()+"年"+yearBasisRuquest.getMonth()+"月");
	yearBasisMrc.put("now", MrcDTO);
	yearRatioMrc.put("lastMonth", yearRatioMrcDTO);
	yearRatioMrc.put("lastMonthTime", yearRatioRuquest.getYear()+"年"+yearRatioRuquest.getMonth()+"月");
	yearRatioMrc.put("now", MrcDTO);
	resultList.put("nowmrc", result);
	resultList.put("yearBasisMrc",yearBasisMrc);
	resultList.put("yearRatioMrc",yearRatioMrc);
	return resultList;
}

@Override
public Object areaDoctorTitle(RuquestBeanDTO ruquestBeanDTO) {
	Map<String, Object> resultList=new HashMap<String, Object>();
	Map<String, Object> result=new HashMap<String, Object>();
	List<ReportResultDTO> reportResultList=clinicsInfoRepository.areaDoctorTitle(ruquestBeanDTO);
	Map<String, Object> yearBasisMap=new HashMap<String, Object>();
	Map<String, Object> yearRatioMap=new HashMap<String, Object>();
	RuquestBeanDTO yearBasisRuquest=yearBasisTime(ruquestBeanDTO.clone());
	RuquestBeanDTO yearRatioRuquest=yearRatioTime(ruquestBeanDTO.clone());
	result.put("now", reportResultList);
	result.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	List<ReportResultDTO> yearBasisResultList =clinicsInfoRepository.areaDoctorTitle(yearBasisRuquest);
	List<ReportResultDTO> yearRatioResultList=clinicsInfoRepository.areaDoctorTitle(yearRatioRuquest);
	yearBasisMap.put("lastYear", yearBasisResultList);
	yearBasisMap.put("lastYearTime", yearBasisRuquest.getYear()+"年"+yearBasisRuquest.getMonth()+"月");
	yearBasisMap.put("now", reportResultList);
	yearBasisMap.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	yearRatioMap.put("lastMonth", yearRatioResultList);
	yearRatioMap.put("lastMonthTime", yearRatioRuquest.getYear()+"年"+yearRatioRuquest.getMonth()+"月");
	yearRatioMap.put("now", reportResultList);
	yearRatioMap.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	resultList.put("now", result);
	resultList.put("yearBasisMap",yearBasisMap);
	resultList.put("yearRatioMap",yearRatioMap);
	return resultList;
}

@Override
public List<Map<String, Object>> PositionDistribution(RuquestBeanDTO ruquestBeanDTO) {
	return clinicsInfoRepository.PositionDistribution(ruquestBeanDTO);
}

@Override //AeraTiltle
public Object DoctorTitle(RuquestBeanDTO ruquestBeanDTO) {
	Map<String,AeraTiltleDTO> results=new HashMap<String,AeraTiltleDTO>();
	List<Map<String, String>> result=clinicsInfoRepository.DoctorTitle(ruquestBeanDTO);
	for(Map hashMap:result){
		AeraTiltleDTO aeraTiltleDTO=results.get((String) hashMap.get("area_name"));
		if(aeraTiltleDTO==null){
			AeraTiltleDTO aeraTiltleDTOTemp=new AeraTiltleDTO();
			results.put((String) hashMap.get("area_name"),aeraTiltleDTOTemp);
			aeraTiltleDTO=aeraTiltleDTOTemp;
		}
		aeraTiltleDTO.setAreaName((String) hashMap.get("area_name"));
		aeraTiltleDTO.setAreaCode((String) hashMap.get("area_code"));
		if(((String)hashMap.get("type")).equals("高级")){
			aeraTiltleDTO.setHigh((String) hashMap.get("qty").toString());
		}
		if(((String)hashMap.get("type")).equals("中级")){
			aeraTiltleDTO.setMedium((String) hashMap.get("qty").toString());
		}
		if(((String)hashMap.get("type")).equals("初级")){
			aeraTiltleDTO.setLow((String) hashMap.get("qty").toString());
		}
	}
    List listValue = new ArrayList();
    Iterator it = results.keySet().iterator();
    while (it.hasNext()) {
        String key = it.next().toString();
        listValue.add(results.get(key));
    }  
	return listValue;
}

@Override
public Object headcountAvg(RuquestBeanDTO ruquestBeanDTO) {
	List<Map<String, String>> result=clinicsInfoRepository.headcountAvg(ruquestBeanDTO);
	return result;
}

@Override
public Object doctorAbility(RuquestBeanDTO ruquestBeanDTO) {
	List<Map<String, String>> result=clinicsInfoRepository.doctorAbility(ruquestBeanDTO);
	return result;
	}

@Override
public String queryIncidenceSum(String year, String month, String day,String parentAreaCode) {
	String sum =clinicsInfoRepository.queryIncidenceSum(year,month,day,parentAreaCode);
	if(null != sum){
		return sum;
	}
	return null;
 }

@Override
public Object PersonnelForm(RuquestBeanDTO ruquestBeanDTO) {
	List<Map<String, String>> result=clinicsInfoRepository.PersonnelForm(ruquestBeanDTO);
	return result;
}

@Override
public Object timeseriesPerson(RuquestBeanDTO ruquestBeanDTO) {
	List<Object> resultMap=new ArrayList<Object>();
	if(ruquestBeanDTO.getReportType().equals("year")){
		ruquestBeanDTO.setMonth(null);
		Integer count=Integer.parseInt(ruquestBeanDTO.getEndYear())-Integer.parseInt(ruquestBeanDTO.getStartYear());
		for(int i=0;i<=count;i++){
			ruquestBeanDTO.setYear(Integer.parseInt(ruquestBeanDTO.getStartYear())+i+"");
			Map<String, String> resultMapTemp=clinicsInfoRepository.timeseriesPerson(ruquestBeanDTO);
			resultMapTemp.put("date", Integer.parseInt(ruquestBeanDTO.getStartYear())+i+"");
			resultMap.add(resultMapTemp);
		}
	}else {
			int month =1;
				month=Integer.parseInt(ruquestBeanDTO.getStartMonth());
			for(;month<13;month++){
				if(month>Integer.parseInt(ruquestBeanDTO.getEndMonth())){
					break;
				}
				ruquestBeanDTO.setMonth(month+"");
				ruquestBeanDTO.setYear(Integer.parseInt(ruquestBeanDTO.getStartYear())+"");
				Map<String, String> resultMapTemp=clinicsInfoRepository.timeseriesPerson(ruquestBeanDTO);
				if(ruquestBeanDTO.getReportType().equals("year")){
					resultMapTemp.put("date",Integer.parseInt(ruquestBeanDTO.getStartYear())+"年");
				}else {
					resultMapTemp.put("date",Integer.parseInt(ruquestBeanDTO.getStartYear())+"年"+month+"月");
				}
				resultMap.add(resultMapTemp);
			}
			ruquestBeanDTO.setYear(Integer.parseInt(ruquestBeanDTO.getStartYear())+"");
	}
	return resultMap;
}

@Override
public Object antibioticUse(RuquestBeanDTO ruquestBeanDTO) {
	Map<String, Object> resultList=new HashMap<String, Object>();
	Map<String, Object> result=new HashMap<String, Object>();
	List<ReportResultDTO> reportResultList=clinicsInfoRepository.antibioticUse(ruquestBeanDTO);
	Map<String, Object> yearBasisMap=new HashMap<String, Object>();
	Map<String, Object> yearRatioMap=new HashMap<String, Object>();
	RuquestBeanDTO yearBasisRuquest=yearBasisTime(ruquestBeanDTO.clone());
	RuquestBeanDTO yearRatioRuquest=yearRatioTime(ruquestBeanDTO.clone());
	//result.put("nowUse", reportResultList);
	List<ReportResultDTO> yearBasisResultList =clinicsInfoRepository.antibioticUse(yearBasisRuquest);
	List<ReportResultDTO> yearRatioResultList=clinicsInfoRepository.antibioticUse(yearRatioRuquest);
	//暂未用
//	yearBasisMap.put(yearBasisRuquest.getYear()+"年"+yearBasisRuquest.getMonth()+"月", yearBasisResultList);
//	yearBasisMap.put(ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月", reportResultList);
//	yearRatioMap.put(yearRatioRuquest.getYear()+"年"+yearRatioRuquest.getMonth()+"月", yearRatioResultList);
//	yearRatioMap.put(ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月", reportResultList);
	resultList.put("now", reportResultList);
	resultList.put("lastYear",yearBasisResultList);
	resultList.put("lastMonth",yearRatioResultList);
	return resultList;
}

@Override
public Object transfusion(RuquestBeanDTO ruquestBeanDTO) {
	Map<String, Object> resultList=new HashMap<String, Object>();
	Map<String, Object> result=new HashMap<String, Object>();
	List<ReportResult2DTO> reportResultList=clinicsInfoRepository.transfusion(ruquestBeanDTO);
//	Map<String, Object> yearBasisMap=new HashMap<String, Object>();
//	Map<String, Object> yearRatioMap=new HashMap<String, Object>();
	RuquestBeanDTO yearBasisRuquest=yearBasisTime(ruquestBeanDTO.clone());
	RuquestBeanDTO yearRatioRuquest=yearRatioTime(ruquestBeanDTO.clone());
	//result.put("now", reportResultList);
	List<ReportResult2DTO> yearBasisResultList =clinicsInfoRepository.transfusion(yearBasisRuquest);
	if(yearBasisResultList.size()==0){
		for(ReportResult2DTO reportResultDTO:reportResultList){
			yearBasisResultList.add(reportResultDTO.clone());
		}
		for(ReportResult2DTO reportResultDTO:yearBasisResultList){
			reportResultDTO.setQty("0");
			if(ruquestBeanDTO.getAreaFlag().equals("2")){
				reportResultDTO.setDate(yearBasisRuquest.getYear()+"年");
			}
			if(ruquestBeanDTO.getAreaFlag().equals("1")){
				reportResultDTO.setDate(yearBasisRuquest.getYear()+"年"+yearBasisRuquest.getMonth()+"月");
			}
			if(ruquestBeanDTO.getAreaFlag().equals("0")){
				reportResultDTO.setDate(yearBasisRuquest.getYear()+"年"+yearBasisRuquest.getMonth()+"月"+yearBasisRuquest.getDay()+"天");
			}
		}
	}
	List<ReportResult2DTO> yearRatioResultList=clinicsInfoRepository.transfusion(yearRatioRuquest);
	if(yearRatioResultList.size()==0){
		for(ReportResult2DTO reportResultDTO:reportResultList){
			yearRatioResultList.add(reportResultDTO.clone());
		}
		for(ReportResult2DTO reportResultDTO:yearRatioResultList){
			reportResultDTO.setQty("0");
			if(ruquestBeanDTO.getAreaFlag().equals("2")){
				reportResultDTO.setDate(yearRatioRuquest.getYear()+"年");
			}
			if(ruquestBeanDTO.getAreaFlag().equals("1")){
				reportResultDTO.setDate(yearRatioRuquest.getYear()+"年"+yearRatioRuquest.getMonth()+"月");
			}
			if(ruquestBeanDTO.getAreaFlag().equals("0")){
				reportResultDTO.setDate(yearRatioRuquest.getYear()+"年"+yearRatioRuquest.getMonth()+"月"+yearRatioRuquest.getDay()+"天");
			}
		}
	}
//	yearBasisMap.put(yearBasisRuquest.getYear()+"年"+yearBasisRuquest.getMonth()+"月", yearBasisResultList);
//	yearBasisMap.put(ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月", reportResultList);
//	yearRatioMap.put(yearRatioRuquest.getYear()+"年"+yearRatioRuquest.getMonth()+"月", yearRatioResultList);
//	yearRatioMap.put(ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月", reportResultList);
	resultList.put("now", reportResultList);
	resultList.put("lastYear",yearBasisResultList);
	resultList.put("lastMonth",yearRatioResultList);
	return resultList;
}

@Override
public Object countTransfusion(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResult2DTO> resultList=clinicsInfoRepository.transfusion(ruquestBeanDTO);
	return resultList;
}

@Override
public Object antibioticTransfusion(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResultDB> resultList=clinicsInfoRepository.antibioticTransfusion(ruquestBeanDTO);
	return resultList;
}

@Override
public Object roportionAntibioticUse(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResultDB> resultList=clinicsInfoRepository.roportionAntibioticUse(ruquestBeanDTO);
	return resultList;
}

@Override
public Object transfusionPrescriptions(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResultDB> resultList=clinicsInfoRepository.transfusionPrescriptions(ruquestBeanDTO);
	return resultList;
}

@Override
public Object antibioticTransfusionUse(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResultDB> resultList=clinicsInfoRepository.antibioticTransfusionUse(ruquestBeanDTO);
	return resultList;
}

@Override
public Object TransfusionUseCg(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResultDB> resultList=clinicsInfoRepository.TransfusionUseCg(ruquestBeanDTO);
	return resultList;
}

@Override
public Object RationalForm(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResultDB> resultList=clinicsInfoRepository.RationalForm(ruquestBeanDTO);
	return resultList;
}

@Override
public Object AreaGeneralSituation(RuquestBeanDTO ruquestBeanDTO) {
	Map<String, Object> returnList=new HashMap<String, Object>();
	//病历
	Map<String, Object> resultList=new HashMap<String, Object>();
	Map<String, Object> result=new HashMap<String, Object>();
	List<ReportResultDTO> reportResultList=clinicsInfoRepository.AreaGeneralSituation(ruquestBeanDTO);
	Map<String, Object> yearBasisMap=new HashMap<String, Object>();
	Map<String, Object> yearRatioMap=new HashMap<String, Object>();
	RuquestBeanDTO yearBasisRuquest=yearBasisTime(ruquestBeanDTO.clone());
	RuquestBeanDTO yearRatioRuquest=yearRatioTime(ruquestBeanDTO.clone());
	result.put("now", reportResultList);
	if(ruquestBeanDTO.getReportType()!=null&&ruquestBeanDTO.getReportType().equals("year")){
		result.put("nowTime", ruquestBeanDTO.getYear()+"年");
	}else {
		result.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	}
	List<ReportResultDTO> yearBasisResultList =clinicsInfoRepository.AreaGeneralSituation(yearBasisRuquest);
	List<ReportResultDTO> yearRatioResultList=clinicsInfoRepository.AreaGeneralSituation(yearRatioRuquest);
	yearBasisMap.put("lastYear", yearBasisResultList);
	if(ruquestBeanDTO.getReportType()!=null&&ruquestBeanDTO.getReportType().equals("year")){
		yearBasisMap.put("lastYearTime", yearBasisRuquest.getYear()+"年");
	}else {
		yearBasisMap.put("lastYearTime", yearBasisRuquest.getYear()+"年"+yearBasisRuquest.getMonth()+"月");
	}
	yearBasisMap.put("now", reportResultList);
	//yearBasisMap.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	yearRatioMap.put("lastMonth", yearRatioResultList);
	if(ruquestBeanDTO.getReportType()!=null&&ruquestBeanDTO.getReportType().equals("year")){
		yearRatioMap.put("lastMonthTime", yearRatioRuquest.getYear()+"年");
	}else {
		yearRatioMap.put("lastMonthTime", yearRatioRuquest.getYear()+"年"+yearRatioRuquest.getMonth()+"月");
	}
	yearRatioMap.put("now", reportResultList);
	//yearRatioMap.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	resultList.put("now", result);
	resultList.put("yearBasis",yearBasisMap);
	resultList.put("yearRatio",yearRatioMap);
	returnList.put("cases",resultList);
	//处方默认100%
	Map<String, Object> resultList2=new HashMap<String, Object>();
	ReportResultDTO reportResult=new ReportResultDTO();
	reportResult.setQty("100%");
	reportResult.setName("电子处方");
	Map<String, Object> result2=new HashMap<String, Object>();
	Map<String, Object> yearBasisMap2=new HashMap<String, Object>();
	Map<String, Object> yearRatioMap2=new HashMap<String, Object>();
	result2.put("now", reportResult);
	if(ruquestBeanDTO.getReportType()!=null&&ruquestBeanDTO.getReportType().equals("year")){
		result2.put("nowTime", ruquestBeanDTO.getYear()+"年");
	}else {
		result2.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	}
	yearBasisMap2.put("lastYear", reportResult);
	if(ruquestBeanDTO.getReportType()!=null&&ruquestBeanDTO.getReportType().equals("year")){
		yearBasisMap2.put("lastYearTime", yearBasisRuquest.getYear()+"年");
	}else {
		yearBasisMap2.put("lastYearTime", yearBasisRuquest.getYear()+"年"+yearBasisRuquest.getMonth()+"月");
	}
	yearBasisMap2.put("now", reportResult);
	//yearBasisMap2.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	yearRatioMap2.put("lastMonth", reportResult);
	if(ruquestBeanDTO.getReportType()!=null&&ruquestBeanDTO.getReportType().equals("year")){
		yearRatioMap2.put("lastMonthTime", yearRatioRuquest.getYear()+"年");
	}else {
		yearRatioMap2.put("lastMonthTime", yearRatioRuquest.getYear()+"年"+yearRatioRuquest.getMonth()+"月");
	}
	yearRatioMap2.put("now", reportResult);
	//yearRatioMap2.put("nowTime", ruquestBeanDTO.getYear()+"年"+ruquestBeanDTO.getMonth()+"月");
	resultList2.put("now", result2);
	resultList2.put("yearBasis",yearBasisMap2);
	resultList2.put("yearRatio",yearRatioMap2);
	returnList.put("recipe",resultList2);
	return returnList;
}

@Override
public Object lowerRegion(RuquestBeanDTO ruquestBeanDTO) {
	Map<String, Object> returnList=new HashMap<String, Object>();
	List<ReportResultDB> reportResultList=clinicsInfoRepository.lowerRegion(ruquestBeanDTO);
	//处方默认100%
	List<ReportResultDB> recipeResultList=new ArrayList<ReportResultDB>();
	for(ReportResultDB reportResult:reportResultList){
		ReportResultDB reportResultDtemp=new ReportResultDB();
		reportResultDtemp.setName(reportResult.getName());
		reportResultDtemp.setQty("100%");
		recipeResultList.add(reportResultDtemp);
	}
	returnList.put("recipe", recipeResultList);
	returnList.put("cases", reportResultList);
	return returnList;
}

@Override
public Object SummaryTable(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResultDB> reportResultList=clinicsInfoRepository.lowerRegion(ruquestBeanDTO);
	for(ReportResultDB reportResult:reportResultList){
		//type类型暂时为处方合格率比例,因为没指标,默认100%
		reportResult.setType("100%");
	}
	return reportResultList;
}

@Override
public Object expire(RuquestBeanDTO ruquestBeanDTO) {
	ReportResultDB reportResult=clinicsInfoRepository.expire(ruquestBeanDTO);
	return reportResult;
}

@Override
public Object listTimeClinics(RuquestBeanDTO ruquestBeanDTO) {
	ReportResultDB reportResult=clinicsInfoRepository.expire(ruquestBeanDTO);
//	if(reportResult!=null&&Integer.parseInt(reportResult.getQty())>0){
//		List<String> orgId=clinicsInfoRepository.listTimeClinics(ruquestBeanDTO);
//	}
	List<Map<String, String>> orgIds=clinicsInfoRepository.listTimeClinics(ruquestBeanDTO);
	//需要remove的对象
	List<Map<String, String>>  removeIds=clinicsInfoRepository.listTimeClinics(ruquestBeanDTO);
	for(Map<String, String> temp:orgIds){
		BOZdOrg BOZdOrgs=ZdOrgService.findById(Integer.parseInt(temp.get("org_id")));
		
		if(0 != BOZdOrgs.getAddressId()){
			//根据获取的addressid获取具体的地址信息
			XtAddressBean dto=districtService.getXtAddress(BOZdOrgs.getAddressId());
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
			BOZdOrgs.setAddress(addressName);
		}
		
		List<Integer> orgsByAddress = ZdOrgService.getOrgsByAddress(null, null, null, Integer.parseInt(ruquestBeanDTO.getParentAreaCode()), null, null, null);
		if(BOZdOrgs==null ||orgsByAddress.indexOf(BOZdOrgs.getId())==-1){
			removeIds.add(temp);
		}else{
			temp.put("name", BOZdOrgs.getName());
			temp.put("area", BOZdOrgs.getCounty());
			if(BOZdOrgs.getCounty()==null){
				temp.put("area", "");
			}
			temp.put("address", BOZdOrgs.getAddress());
			if(BOZdOrgs.getAddress()==null){
				temp.put("address", "");
			}
			temp.put("Telephone", BOZdOrgs.getTelephone());
			temp.put("notice", "已通知");
			temp.put("createTime",BOZdOrgs.getCreateTime());
			temp.put("legalPerson", "法人");
		}
	}
	orgIds.removeAll(removeIds);
	return orgIds;
}

@Override
public Object OverviewClinicMedicalQuality(RuquestBeanDTO ruquestBeanDTO) {
	List<ReportResultDB> list=clinicsInfoRepository.OverviewClinicMedicalQuality(ruquestBeanDTO);
	return list;
}
@Override
public Object ListOverviewClinicMedicalQuality(RuquestBeanDTO ruquestBeanDTO) {
	List<Map<String, String>> list=clinicsInfoRepository.ListOverviewClinicMedicalQuality(ruquestBeanDTO);
	return list;
}


@Override
public List<ReportResultDTO> queryOperates(String year, String month,
		String parentAreaCode, String totalCode,String reportType) {
	List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
	List<ReportResultDB> list=clinicsInfoRepository.queryOperates(year,month,parentAreaCode,totalCode,reportType);
	for (ReportResultDB reportResultDB : list) {
		ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
		resultList.add(dto);
	}
	if(resultList.size() > 0){
		return resultList;
	}
	return null;
}


}
