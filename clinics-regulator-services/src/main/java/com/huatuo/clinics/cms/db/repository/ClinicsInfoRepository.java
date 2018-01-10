package com.huatuo.clinics.cms.db.repository;

import java.util.List;
import java.util.Map;

import com.huatuo.clinics.cms.bean.ClinicResourcesInfoDTO;
import com.huatuo.clinics.cms.bean.RuquestBeanDTO;
import com.huatuo.clinics.cms.bean.mrcDTO;
import com.huatuo.clinics.cms.bean.ReportResult2DTO;
import com.huatuo.clinics.cms.bean.ReportResultDTO;
import com.huatuo.clinics.cms.db.bean.SmCliAreaCover;
import com.huatuo.clinics.cms.db.bean.ReportResultDB;


public interface ClinicsInfoRepository {
	
	/**
	 * 查询截止当前时间区域下的各个镇的诊所数量
	 * @param year
	 * @param month
	 * @param day
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryNameAndQty(String year, String month, String day,
			String parentAreaCode);
	
	/**
	 * 查询截止当前时间区域下前十的病种
	 * @param year
	 * @param month
	 * @param day
	 * @param parentAreaCode
	 * @return
	 */
	List<ReportResultDB> queryIncidence(String year, String month, String day,
			String parentAreaCode);
	
	/**
	 * 查询截止当前时间区域下诊所评级情况
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	List<ReportResultDB> queryGrade(String year, String month, String day);
	
	/**
	 * 查询截止当前时间区域下各个镇的门诊总量
	 * @param year
	 * @param month
	 * @param day
	 * @param parentAreaCode
	 * @param flag 
	 * @return
	 */
	List<ReportResultDB> queryOperate(String year, String month, String day,
			String parentAreaCode,String sourceCode, boolean flag);
	/**
	 * 查询
	 * @param ruquestBeanDTO
	 * @return 
	 */
	List<SmCliAreaCover> select(RuquestBeanDTO ruquestBeanDTO);

	List<ReportResultDTO> queryType(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 诊所资源信息总表
	 * @param ruquestBeanDTO
	 * @return
	 */
	List<ClinicResourcesInfoDTO> Information(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 诊所资源行政区划
	 * @param ruquestBeanDTO
	 * @return
	 */
	List<ReportResultDTO> administrativeJson(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 诊所资源时间趋势
	 * @param ruquestBeanDTO
	 * @return
	 */
	ReportResult2DTO timeseries(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 	辖区总体概况-个体诊所人员岗位分布
	 */
	mrcDTO areaPopulationStation(RuquestBeanDTO ruquestBeanDTO);

	List<ReportResultDTO> areaDoctorTitle(RuquestBeanDTO ruquestBeanDTO);
	/**
	 *  资质监管许可证到期数量查询
	 * @param year
	 * @param month
	 * @param day
	 * @param parentAreaCode
	 * @param code
	 * @param type
	 * @return
	 */
	String queryQuality(String year, String month, String day,
			String parentAreaCode, String code, String type);
	
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

	List<Map<String,Object>> PositionDistribution(RuquestBeanDTO ruquestBeanDTO);

	List<Map<String, String>> DoctorTitle(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 查询累计
	 */
	List<SmCliAreaCover> selectTotal(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 根据类型查询总和
	 */
	List<ReportResultDTO> queryTypeTotal(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 医疗服务能力分布概况-人员数量的平均分布
	 */
	List<Map<String, String>> headcountAvg(RuquestBeanDTO ruquestBeanDTO);
	
	/**
	 * 医疗服务能力分布概况-医生职称的平均分布
	 */
	List<Map<String, String>> doctorAbility(RuquestBeanDTO ruquestBeanDTO);
	/**
	 *  医疗服务能力分布概况-个体诊所人员构成信息总表
	 */
	List<Map<String, String>> PersonnelForm(RuquestBeanDTO ruquestBeanDTO);
	/**
	 *时间趋势-个体诊所人员构成概况分析
	 */
	Map<String, String> timeseriesPerson(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-抗生素使用情况分析
	 */
	List<ReportResultDTO> antibioticUse(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-输液情况
	 */
	List<ReportResult2DTO> transfusion(RuquestBeanDTO ruquestBeanDTO);

	/**
	 * 合理用药-输液处方抗生素占比
	 */
	List<ReportResultDB> antibioticTransfusion(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-下级-各下级行政区划个体诊所抗生素使用占比
	 */
	List<ReportResultDB> roportionAntibioticUse(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-下级-个体诊所输液处方占比
	 */
	List<ReportResultDB> transfusionPrescriptions(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-信息总表-抗生素使用占比变化情况
	 */
	List<ReportResultDB> antibioticTransfusionUse(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-信息总表-输液处方占比变化情况
	 */
	List<ReportResultDB> TransfusionUseCg(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 合理用药-情况信息总表
	 */
	List<ReportResultDB> RationalForm(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 医疗服务规范- 辖区总体概况
	 */
	List<ReportResultDTO> AreaGeneralSituation(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 医疗服务规范- 下级区划概况
	 */
	List<ReportResultDB> lowerRegion(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 资质监管
	 */
	ReportResultDB expire(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 资质监管-到期数量
	 * @return 过期的orgid总和
	 */
	List<Map<String, String>> listTimeClinics(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 诊所医疗质量情况概览
	 */
	List<ReportResultDB> OverviewClinicMedicalQuality(RuquestBeanDTO ruquestBeanDTO);
	/**
	 * 个体诊所医疗质量情况概览表单
	 */
	List<Map<String, String>> ListOverviewClinicMedicalQuality(RuquestBeanDTO ruquestBeanDTO);
	/**
	 *2诊所资源-覆盖情况- 诊所医疗能力覆盖情况
	 */
	List<SmCliAreaCover> CoverageJson(RuquestBeanDTO ruquestBeanDTO);
	
	
	/**
	 * 门诊总量
	 * @param year
	 * @param month
	 * @param parentAreaCode
	 * @param totalCode
	 * @param reportType
	 * @return
	 */
	List<ReportResultDB> queryOperates(String year, String month,
			String parentAreaCode, String totalCode, String reportType);
	

}
