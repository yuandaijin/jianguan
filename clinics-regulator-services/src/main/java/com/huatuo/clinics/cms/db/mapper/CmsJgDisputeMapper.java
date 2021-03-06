package com.huatuo.clinics.cms.db.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huatuo.clinics.cms.bean.AssembleDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgDispute;
import com.huatuo.clinics.cms.db.bean.CmsJgDisputeExample;
import com.huatuo.clinics.cms.db.bean.DisputeRepost;
import com.huatuo.clinics.cms.db.bean.MonitoringEvents;

public interface CmsJgDisputeMapper {
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    int countByExample(CmsJgDisputeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    int deleteByExample(CmsJgDisputeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    int insert(CmsJgDispute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    int insertSelective(CmsJgDispute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    List<CmsJgDispute> selectByExample(CmsJgDisputeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    CmsJgDispute selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    int updateByExampleSelective(@Param("record") CmsJgDispute record, @Param("example") CmsJgDisputeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    int updateByExample(@Param("record") CmsJgDispute record, @Param("example") CmsJgDisputeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    int updateByPrimaryKeySelective(CmsJgDispute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_dispute
     *
     * @mbggenerated Wed Nov 16 15:07:48 CST 2016
     */
    int updateByPrimaryKey(CmsJgDispute record);
     /**
     * 查询相应的医疗争议登记
     * @param map
     * @return
     */
	List<CmsJgDispute> queryDisputes(HashMap<String, Object> map);
	
	/**
	 * 医疗争议数量统计表
	 * @param map
	 * @return
	 */
	List<DisputeRepost> queryMedicalDisputeNumber(HashMap<String, Object> map);
	
	/**
	 * 医疗争议详情
	 * @param map
	 * @return
	 */
	List<CmsJgDispute> queryDisputesByReport(HashMap<String, Object> map);
	
	/**
	 * 查询发生的医疗争议
	 * @param map
	 * @return
	 */
	List<AssembleDTO> queryDisputeNo(HashMap<String, Object> map);
	
	/**
	 * 查询上报的传染病
	 * @param map
	 * @return
	 */
	List<AssembleDTO> queryInfectionNo(HashMap<String, Object> map);
	
	/**
	 * 监测事件具体情况
	 * @param map
	 * @return
	 */
	List<MonitoringEvents> queryType(HashMap<String, Object> map);
	
	/**
	 * 查询抗生素违规
	 * @param map
	 * @return
	 */
	List<AssembleDTO> queryAntibiotic(HashMap<String, Object> map);
}