package com.huatuo.clinics.cms.db.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huatuo.clinics.cms.db.bean.TaPhaDrugInfo;
import com.huatuo.clinics.cms.db.bean.TaPhaDrugInfoExample;

public interface TaPhaDrugInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    int countByExample(TaPhaDrugInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    int deleteByExample(TaPhaDrugInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    int deleteByPrimaryKey(String phaDrugId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    int insert(TaPhaDrugInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    int insertSelective(TaPhaDrugInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    List<TaPhaDrugInfo> selectByExample(TaPhaDrugInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    TaPhaDrugInfo selectByPrimaryKey(String phaDrugId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    int updateByExampleSelective(@Param("record") TaPhaDrugInfo record, @Param("example") TaPhaDrugInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    int updateByExample(@Param("record") TaPhaDrugInfo record, @Param("example") TaPhaDrugInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    int updateByPrimaryKeySelective(TaPhaDrugInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_pha_drug_info
     *
     * @mbggenerated Fri Aug 12 20:03:22 CST 2016
     */
    int updateByPrimaryKey(TaPhaDrugInfo record);
    
    //自定义方法
	/**
	 * 统计查询条数
	 * @param map
	 * @return
	 */
	int countList(Map<String, Object> map);
	
	
	/**
	 * 查询药品总记录数
	 * @param map
	 * @return
	 */
	int queryCount(Map<String, Object> map);
	
	/**
	 * 条件查询出所有的数据集合
	 * @param map
	 * @return
	 */
	List<TaPhaDrugInfo> pageList(Map<String, Object> map);
	
	/**
	 * 更新批发价
	 * @param tap
	 * @return 
	 */
	int updateWp(TaPhaDrugInfo tap);
	/**
	 * 模糊查询当前诊所的所有药品数量(不带批次)
	 * @param map
	 * @return
	 */
	int countInfo(Map<String, Object> map);
	/**
	 *  模糊查询当前诊所的药品(不带批次)
	 * @param map
	 * @return
	 */
	List<TaPhaDrugInfo> pageListInfo(Map<String, Object> map);
	/**
	 * 判断该对象是否存在（所有字段比较）
	 * @param record
	 * @return
	 */
	TaPhaDrugInfo queryIsExist(TaPhaDrugInfo record);
}