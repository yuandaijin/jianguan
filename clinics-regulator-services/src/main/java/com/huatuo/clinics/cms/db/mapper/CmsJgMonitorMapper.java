package com.huatuo.clinics.cms.db.mapper;

import com.huatuo.clinics.cms.db.bean.CmsJgMonitor;
import com.huatuo.clinics.cms.db.bean.CmsJgMonitorExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsJgMonitorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_monitor
     *
     * @mbggenerated Wed Nov 16 16:05:54 CST 2016
     */
    int countByExample(CmsJgMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_monitor
     *
     * @mbggenerated Wed Nov 16 16:05:54 CST 2016
     */
    int deleteByExample(CmsJgMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_monitor
     *
     * @mbggenerated Wed Nov 16 16:05:54 CST 2016
     */
    int insert(CmsJgMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_monitor
     *
     * @mbggenerated Wed Nov 16 16:05:54 CST 2016
     */
    int insertSelective(CmsJgMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_monitor
     *
     * @mbggenerated Wed Nov 16 16:05:54 CST 2016
     */
    List<CmsJgMonitor> selectByExample(CmsJgMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_monitor
     *
     * @mbggenerated Wed Nov 16 16:05:54 CST 2016
     */
    int updateByExampleSelective(@Param("record") CmsJgMonitor record, @Param("example") CmsJgMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_jg_monitor
     *
     * @mbggenerated Wed Nov 16 16:05:54 CST 2016
     */
    int updateByExample(@Param("record") CmsJgMonitor record, @Param("example") CmsJgMonitorExample example);
    
    /**
     * 历史信息查询
     * @param map
     * @return
     */
	List<CmsJgMonitor> queryMonitorDtl(HashMap<String, Object> map);
}