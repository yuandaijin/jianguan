package com.huatuo.clinics.cms.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huatuo.clinics.cms.db.bean.OmDicCliChargeItem;
import com.huatuo.clinics.cms.db.bean.OmDicCliChargeItemExample;

public interface OmDicCliChargeItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    int countByExample(OmDicCliChargeItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    int deleteByExample(OmDicCliChargeItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    int deleteByPrimaryKey(String cliChargeItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    int insert(OmDicCliChargeItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    int insertSelective(OmDicCliChargeItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    List<OmDicCliChargeItem> selectByExample(OmDicCliChargeItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    OmDicCliChargeItem selectByPrimaryKey(String cliChargeItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    int updateByExampleSelective(@Param("record") OmDicCliChargeItem record, @Param("example") OmDicCliChargeItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    int updateByExample(@Param("record") OmDicCliChargeItem record, @Param("example") OmDicCliChargeItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    int updateByPrimaryKeySelective(OmDicCliChargeItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table om_dic_cli_charge_item
     *
     * @mbggenerated Sun Apr 17 10:26:52 CST 2016
     */
    int updateByPrimaryKey(OmDicCliChargeItem record);
	
	
	/**
     * ����������Ŀ
     * @param orgId
     * @return
     */
	int insertCli(String orgId);
}