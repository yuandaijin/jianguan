package com.huatuo.clinics.cms.db.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huatuo.clinics.cms.db.bean.TaPhaDispensingDtl;
import com.huatuo.clinics.cms.db.bean.TaPhaDispensingDtlExample;

public interface TaPhaDispensingDtlMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    int countByExample(TaPhaDispensingDtlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    int deleteByExample(TaPhaDispensingDtlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    int deleteByPrimaryKey(String dispenseItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    int insert(TaPhaDispensingDtl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    int insertSelective(TaPhaDispensingDtl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    List<TaPhaDispensingDtl> selectByExample(TaPhaDispensingDtlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    TaPhaDispensingDtl selectByPrimaryKey(String dispenseItemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    int updateByExampleSelective(@Param("record") TaPhaDispensingDtl record, @Param("example") TaPhaDispensingDtlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    int updateByExample(@Param("record") TaPhaDispensingDtl record, @Param("example") TaPhaDispensingDtlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    int updateByPrimaryKeySelective(TaPhaDispensingDtl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_dispensing_dtl
     *
     * @mbggenerated Fri Aug 12 19:39:17 CST 2016
     */
    int updateByPrimaryKey(TaPhaDispensingDtl record);

	List<TaPhaDispensingDtl> getDtlListByPId(String dispenseId);
	/**
	 * 拿取一个药品未发药的大规格总数量
	 * @param map1
	 * @return
	 */
	Integer getMaxActualQuantity(HashMap<String, Object> map1);
	/**
	 * 拿取一个药品未发药的大规格总数量
	 * @param map1
	 * @return
	 */
	Integer getMinActualQuantity(HashMap<String, Object> map1);
	/**
	 * 根据发药单id获得退药发药明细
	 * @param dispenseId
	 * @return
	 */
	List<TaPhaDispensingDtl> getDtlListByPIdForBack(String dispenseId);
}