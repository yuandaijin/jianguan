package com.huatuo.clinics.cms.db.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huatuo.clinics.cms.db.bean.CsOutpOrder;
import com.huatuo.clinics.cms.db.bean.CsOutpOrderExample;

public interface CsOutpOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    int countByExample(CsOutpOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    int deleteByExample(CsOutpOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    int deleteByPrimaryKey(String ordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    int insert(CsOutpOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    int insertSelective(CsOutpOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    List<CsOutpOrder> selectByExample(CsOutpOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    CsOutpOrder selectByPrimaryKey(String ordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    int updateByExampleSelective(@Param("record") CsOutpOrder record, @Param("example") CsOutpOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    int updateByExample(@Param("record") CsOutpOrder record, @Param("example") CsOutpOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    int updateByPrimaryKeySelective(CsOutpOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cs_outp_order
     *
     * @mbggenerated Wed Apr 13 15:32:39 CST 2016
     */
    int updateByPrimaryKey(CsOutpOrder record);
    
    /**
     * 根据就诊号查询诊所下的多个处方
     * @param vistiNo
     * @return
     */
	List<CsOutpOrder> query(Map map);

	List<CsOutpOrder> queryAll(Map<String, String> map);

	/**
	 * 处方数量
	 * @param map
	 * @return
	 */
	int countAntibiotic(Map<String, Object> map);
	
	/**
	 * 查询处方id
	 * @param map
	 * @return
	 */
	List<CsOutpOrder> queryOrdId(Map<String, Object> map);
}