package com.huatuo.clinics.cms.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huatuo.clinics.cms.db.bean.OpmOrgExtendInfo;
import com.huatuo.clinics.cms.db.bean.OpmOrgExtendInfoExample;


public interface OpmOrgExtendInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    int countByExample(OpmOrgExtendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    int deleteByExample(OpmOrgExtendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    int deleteByPrimaryKey(String orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    int insert(OpmOrgExtendInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    int insertSelective(OpmOrgExtendInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    List<OpmOrgExtendInfo> selectByExample(OpmOrgExtendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    OpmOrgExtendInfo selectByPrimaryKey(String orgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    int updateByExampleSelective(@Param("record") OpmOrgExtendInfo record, @Param("example") OpmOrgExtendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    int updateByExample(@Param("record") OpmOrgExtendInfo record, @Param("example") OpmOrgExtendInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    int updateByPrimaryKeySelective(OpmOrgExtendInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opm_org_extend_info
     *
     * @mbggenerated Wed Nov 09 15:06:23 CST 2016
     */
    int updateByPrimaryKey(OpmOrgExtendInfo record);
}