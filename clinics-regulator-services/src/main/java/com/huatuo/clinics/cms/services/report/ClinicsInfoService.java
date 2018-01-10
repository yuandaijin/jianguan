package com.huatuo.clinics.cms.services.report;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.huatuo.clinics.cms.bean.AeraTiltleDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.bean.ReportResultDTO;


public interface ClinicsInfoService {
	
	/**
	 * 查询截止当前时间区域下的各个镇的诊所数量
	 * @param year
	 * @param month
	 * @param day
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDTO> queryNameAndQty(String year, String month,
			String day, String parentAreaCode);
	
	/**
	 * 查询截止当前时间区域下前十的病种
	 * @param year
	 * @param month
	 * @param day
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDTO> queryIncidence(String year, String month, String day,
			String parentAreaCode);
	
	/**
	 * 查询截止当前时间区域下诊所评级情况
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	List<ReportResultDTO> queryGrade(String year, String month, String day);

	/**
	 * 查询截止当前时间区域下各个镇的门诊总量
	 * @param year
	 * @param month
	 * @param day
	 * @param parentAreaCode
	 * @param b 
	 * @return
	 */
	List<ReportResultDTO> queryOperate(String year, String month, String day,
			String parentAreaCode,String sourceCode,boolean flag);
	/**
	 * 覆盖情况
	 */
	List CoverageJson(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 诊所数量
	 * @param ruquestBeanDTO
	 * @return
	 */
	List ClinicQtyJson(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 诊所类型
	 * @param ruquestBeanDTO
	 * @return
	 */
	Map<String, Object> ClinicTypeJson(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 行政区划
	 */
	List<ReportResultDTO> administrativeJson(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 信息总表
	 */
	Object Information(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 诊所时间趋势
	 */
	Object timeseries(RuquestBeanDTO ruquestBeanDTO);
	/**
	 *   辖区总体概况-个体诊所人员岗位分布
	 * @throws ParseException 
	 */
	Object areaPopulationStation(RuquestBeanDTO ruquestBeanDTO) throws ParseException;
	/**
	 *  辖区总体概况-个体诊所医生职称分布
	 */
	Object areaDoctorTitle(RuquestBeanDTO ruquestBeanDTO);

	List<Map<String, Object>> PositionDistribution(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 各下级行政区划个体诊所医生职称分布
	 */
	Object DoctorTitle(RuquestBeanDTO ruquestBeanDTO);
	
	/**
	 * 资质监管许可证到期数量查询
	 */
	String queryQuality(String year, String month, String day,
			String parentAreaCode, String code, String type);
	/**
	 * 医疗服务能力分布概况-人员数量的平均分布
	 */
	Object headcountAvg(RuquestBeanDTO ruquestBeanDTO);
	
	/**
	 * 医疗服务能力分布概况-医生职称的平均分布
	 */
	Object doctorAbility(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 医疗服务能力分布概况-个体诊所人员构成信息总表
	 */
	Object PersonnelForm(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 时间趋势-个体诊所人员构成概况分析
	 */
	Object timeseriesPerson(RuquestBeanDTO ruquestBeanDTO);
	
	/**
	 * 查询所有病种的总数量
	 * @param year
	 * @param month
	 * @param day
	 * @param parentAreaCode
	 * @return
	 */
	String queryIncidenceSum(String year, String month, String day,
			String parentAreaCode);
	/**
	 * 合理用药-抗生素使用情况分析
	 */
	Object antibioticUse(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-输液处方占比
	 */
	Object transfusion(RuquestBeanDTO ruquestBeanDTO);
	
	/**
	 * 合理用药-处方总量输液占比
	 */
	Object countTransfusion(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-输液处方抗生素占比
	 */
	Object antibioticTransfusion(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-下级-各下级行政区划个体诊所抗生素使用占比
	 */
	Object roportionAntibioticUse(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-下级-个体诊所输液处方占比
	 */
	Object transfusionPrescriptions(RuquestBeanDTO ruquestBeanDTO);
	/**
	 *  合理用药-时间趋势-抗生素使用占比变化情况
	 */
	Object antibioticTransfusionUse(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-时间趋势-输液处方占比变化情况
	 */
	Object TransfusionUseCg(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-情况信息总表
	 */
	Object RationalForm(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 医疗服务规范- 辖区总体概况
	 */
	Object AreaGeneralSituation(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 医疗服务规范- 下级区划概况
	 */
	Object lowerRegion(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 医疗服务规范- 信息总表
	 */
	Object SummaryTable(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 资质监管
	 */
	Object expire(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 资质监管-到期数量
	 */
	Object listTimeClinics(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 诊所医疗质量情况概览
	 */
	Object OverviewClinicMedicalQuality(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 
	 *个体诊所医疗质量情况概览表单
	 */
	Object ListOverviewClinicMedicalQuality(RuquestBeanDTO ruquestBeanDTO);
	
	
	/**
	 * 门诊总量
	 * @param year
	 * @param month
	 * @param parentAreaCode
	 * @param totalCode
	 * @return
	 */
	List<ReportResultDTO> queryOperates(String year, String month,
			String parentAreaCode, String totalCode,String reportType);
	
}
