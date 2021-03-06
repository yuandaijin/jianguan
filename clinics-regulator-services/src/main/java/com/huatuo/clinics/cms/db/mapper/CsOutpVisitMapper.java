package com.huatuo.clinics.cms.db.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huatuo.clinics.cms.bean.CsOutpVisitDTO;
import com.huatuo.clinics.cms.db.bean.CsOutpVisit;
import com.huatuo.clinics.cms.db.bean.CsOutpVisitExample;

public interface CsOutpVisitMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    int countByExample(CsOutpVisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    int deleteByExample(CsOutpVisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    int deleteByPrimaryKey(String vistiId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    int insert(CsOutpVisit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    int insertSelective(CsOutpVisit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    List<CsOutpVisit> selectByExample(CsOutpVisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    CsOutpVisit selectByPrimaryKey(String vistiId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") CsOutpVisit record, @Param("example") CsOutpVisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    int updateByExample(@Param("record") CsOutpVisit record, @Param("example") CsOutpVisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    int updateByPrimaryKeySelective(CsOutpVisit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_visit
     *
     * @mbggenerated Fri Aug 12 20:30:53 CST 2016
     */
    int updateByPrimaryKey(CsOutpVisit record);
    
    /**
     * 根据查询条件返回挂号信息对象
     * @param map
     * @return
     */
	CsOutpVisit query(Map<String,Object> map);
	
	/**
     * 查询诊所下面最近的一条挂号数据
     * @param orgId
     * @return
     */
	CsOutpVisit getLastData(String orgId);
	
	/**
	 * 历史处方查询
	 * @param dto
	 * @return
	 */
	List<CsOutpVisit> selectRescription(CsOutpVisitDTO dto);
	/**
	 * 查询已收费的左侧菜单
	 * @param map
	 * @return
	 */
	List<CsOutpVisit> queryVisits(Map<String, Object> map);

	List<CsOutpVisit> queryAll(Map<String, Object> map);

	/**
	 * 门诊总量(类型)
	 * @param map	机构id集合和时间
	 * @return
	 */
	int countVisit(Map<String, Object> map);


	CsOutpVisit getByVisitDateAndVisitNo(Map<String, Object> map);

	/**
	 * 查询出就诊id集合
	 * @param map
	 * @return
	 */
	List<String> listVisitId(Map<String, Object> map);

	/**
	 * 存入前十病种性别性别分布
	 * @param map
	 * @return
	 */
	int countIcdBySexOrAge(Map<String, Object> map);
}