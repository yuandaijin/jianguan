package com.huatuo.clinics.cms.db.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huatuo.clinics.cms.db.bean.CsOutpEmr;
import com.huatuo.clinics.cms.db.bean.CsOutpEmrExample;

public interface CsOutpEmrMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    int countByExample(CsOutpEmrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    int deleteByExample(CsOutpEmrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    int insert(CsOutpEmr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    int insertSelective(CsOutpEmr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    List<CsOutpEmr> selectByExample(CsOutpEmrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    CsOutpEmr selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    int updateByExampleSelective(@Param("record") CsOutpEmr record, @Param("example") CsOutpEmrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    int updateByExample(@Param("record") CsOutpEmr record, @Param("example") CsOutpEmrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    int updateByPrimaryKeySelective(CsOutpEmr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_emr
     *
     * @mbggenerated Mon Jul 11 17:57:07 CST 2016
     */
    int updateByPrimaryKey(CsOutpEmr record);

    /**
     * 合格的电子病历数
     * @param map
     * @return
     */
	int countQualified(Map<String, Object> map);
}