package com.huatuo.clinics.cms.services.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.OperateInfoDTO;
import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.db.bean.OperateInfo;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;
import com.huatuo.clinics.cms.db.repository.ClinicsOperateRepository;
import com.huatuo.clinics.cms.util.Utils;

@Service
public class ClinicsOperateServiceImpl implements ClinicsOperateService {
	
	@Autowired
	private ClinicsOperateRepository clinicsOperateRepository;

//	@Override
//	public List<reportResultDTO> queryOperate(String year, String parentAreaCode,String sourceCode) {
//		List<reportResultDTO> resultList=new ArrayList<reportResultDTO>();
//		List<reportResultDB> list=clinicsOperateRepository.queryOperate(year,parentAreaCode,sourceCode);
//		for (reportResultDB reportResultDB : list) {
//			reportResultDTO dto = Utils.exchangeObject(reportResultDB, reportResultDTO.class);
//			resultList.add(dto);
//		}
//		if(resultList.size() > 0){
//			return resultList;
//		}
//		return null;
//	}

	@Override
	public List<ReportResultDTO> queryVisit(String year, String month, String reportType, String parentAreaCode, String sourceCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.queryVisit(year, month,reportType,parentAreaCode,sourceCode);
		String name=null;//定义图标x轴的年份
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			if(reportType.equals("year")){
				name=year+"年";
			}else{
				name=year+"年"+month+"月";
			}
			dto.setName(name);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List<ReportResultDTO> querySubordinate(String year, String month,
			String reportType, String parentAreaCode, String sourceCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.querySubordinate(year, month,reportType,parentAreaCode,sourceCode);
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
	public List<ReportResultDTO> queryAveOutpatient(String year, String month,
			String reportType, String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.queryAveOutpatient(year, month,reportType,parentAreaCode);
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
	public List<ReportResultDTO> queryTimeTrend(String startYear,
			String endYear, String startMonth, String endMonth,
			String reportType, String parentAreaCode, String sourceCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.queryTimeTrend(startYear,endYear,startMonth,endMonth,reportType,parentAreaCode,sourceCode);
		//给返回的时间加上'年'或者'月'
		String name = null;
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			if(reportType.equals("year")){
				name=dto.getName()+"年";
			}else{
				name=dto.getName()+"月";
			}
			dto.setName(name);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List<ReportResultDTO> querySum(String year, String month,
			String reportType, String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.querySum(year, month,reportType,parentAreaCode);
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
	public List<ReportResultDTO> queryFirstVisit(String year, String month,
			String reportType, String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.queryFirstVisit(year, month,reportType,parentAreaCode);
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
	public List<ReportResultDTO> queryRepeatVisit(String year, String month,
			String reportType, String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.queryRepeatVisit(year, month,reportType,parentAreaCode);
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
	public List<ReportResultDTO> queryWestVisit(String year, String month,
			String reportType, String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.queryWestVisit(year, month,reportType,parentAreaCode);
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
	public List<ReportResultDTO> queryInVisit(String year, String month,
			String reportType, String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.queryInVisit(year, month,reportType,parentAreaCode);
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
	public List<ReportResultDTO> queryClQty(String year, String month,
			String reportType, String parentAreaCode) {
		List<ReportResultDTO> resultList=new ArrayList<ReportResultDTO>();
		List<ReportResultDB> list=clinicsOperateRepository.queryClQty(year, month,reportType,parentAreaCode);
		for (ReportResultDB reportResultDB : list) {
			ReportResultDTO dto = Utils.exchangeObject(reportResultDB, ReportResultDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}
	
	/**
	 * 个体诊所门诊量信息总表所有信息
	 */
	@Override
	public List<List<ReportResultDTO>> queryTableInfo(String year, String month,
			String reportType, String parentAreaCode) {
		List<List<ReportResultDTO>> resultList=new ArrayList<List<ReportResultDTO>>();
		//查询诊所数量
		List<ReportResultDTO> clqtylist=this.queryClQty(year,month,reportType,parentAreaCode);
		//查询门诊总量
		List<ReportResultDTO> sumlist=this.querySum(year,month,reportType,parentAreaCode);
		//查询初诊
		List<ReportResultDTO> firstlist=this.queryFirstVisit(year,month,reportType,parentAreaCode);
		//查询复诊
		List<ReportResultDTO> repeatlist=this.queryRepeatVisit(year,month,reportType,parentAreaCode);
		//查询西医门诊
		List<ReportResultDTO> west=this.queryWestVisit(year,month,reportType,parentAreaCode);
		//查询中医门诊
		List<ReportResultDTO> inlist=this.queryInVisit(year,month,reportType,parentAreaCode);
		resultList.add(clqtylist);
		resultList.add(sumlist);
		resultList.add(firstlist);
		resultList.add(repeatlist);
		resultList.add(west);
		resultList.add(inlist);
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	


}
