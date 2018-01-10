package com.huatuo.clinics.cms.db.mapper;

import com.huatuo.clinics.cms.db.bean.CmPerReportDefend;
import com.huatuo.clinics.cms.db.bean.CmPerReportDefendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmPerReportDefendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    int countByExample(CmPerReportDefendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    int deleteByExample(CmPerReportDefendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    int insert(CmPerReportDefend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    int insertSelective(CmPerReportDefend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    List<CmPerReportDefend> selectByExample(CmPerReportDefendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    CmPerReportDefend selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    int updateByExampleSelective(@Param("record") CmPerReportDefend record, @Param("example") CmPerReportDefendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    int updateByExample(@Param("record") CmPerReportDefend record, @Param("example") CmPerReportDefendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    int updateByPrimaryKeySelective(CmPerReportDefend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_per_report_defend
     *
     * @mbggenerated Sat Oct 29 10:31:19 CST 2016
     */
    int updateByPrimaryKey(CmPerReportDefend record);
}