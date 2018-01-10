package com.huatuo.clinics.cms.db.repository;

import java.util.List;

import com.huatuo.clinics.cms.db.bean.CmsJgOrg;
import com.huatuo.clinics.cms.db.bean.TaPhaDictDrugAntibioticLevelPolity;

public interface DbCmsJgOrgRepository {

	Integer save(CmsJgOrg dto);
	
	/**
	 * 通过机构名称查询机构信息
	 * @param orgName
	 * @return
	 */
	List<CmsJgOrg> queryOrgByName(String orgName);
	
	/**
	 * 根据条件查询条数
	 * @param provinceCode
	 * @param cityCode
	 * @param countyCode
	 * @return
	 */
	int countOrg(String province,String city,String county);
	
	/**
	 * 分页查询机构信息
	 * @param start
	 * @param pageSize
	 * @param provinceCode
	 * @param cityCode
	 * @param countyCode
	 * @return
	 */
	List<CmsJgOrg> queryOrgPage(int start, Integer pageSize,
			String province,String city,String county);
	
	/**
	 * 通过id查询机构信息
	 * @param id
	 * @return
	 */
	CmsJgOrg queryOrgById(String  id);
	
	/**
	 * 根据id删除机构信息
	 * @param valueOf
	 * @return
	 */
	int deleteOrg(String valueOf);
	
	/**
	 * 根据id删除机构信息
	 * @param dto
	 * @return
	 */
	int updateOrg(CmsJgOrg dto);
	
	/**
	 * 查询机构信息
	 * @param dto
	 * @return
	 */
	List<CmsJgOrg> qeryOrg();
	
	/**
	 * 判断机构名除了本身之外是否重复
	 * @param orgName
	 * @param id
	 * @return
	 */
	List<CmsJgOrg> qeryOrgByNotId(String orgName, String id);
	
	/**
	 * 查询所有的抗生素规则
	 * @return
	 */
	List<TaPhaDictDrugAntibioticLevelPolity> queryAntibiotic();

}
