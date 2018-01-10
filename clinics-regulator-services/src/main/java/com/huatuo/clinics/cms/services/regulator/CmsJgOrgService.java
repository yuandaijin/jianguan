package com.huatuo.clinics.cms.services.regulator;

import java.util.List;

import com.huatuo.clinics.cms.bean.CmsJgAddressAndOrgDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.clinics.cms.bean.TaPhaDictDrugAntibioticLevelPolityDTO;
import com.huatuo.clinics.cms.util.Page;

public interface CmsJgOrgService {

	Integer save(CmsJgOrgDTO cmsJgOrg);
	/**
	 * 新增机构
	 * @param dto
	 */
	int insertOrg(CmsJgAddressAndOrgDTO dto);
	/**
	 * 通过机构名称查询机构信息
	 * @param orgName
	 * @return
	 */
	List<CmsJgOrgDTO> queryOrgByName(String orgName);
	
	/**
	 * 机构信息查询
	 * @param request
	 * @param model
	 * @param provinceCode
	 * @param cityCode
	 * @param countyCode
	 * @return
	 */
	Page<CmsJgOrgDTO> queryAllOrg(Integer pageNo, Integer pageSize,
			 String province,String city,String county);
	/**
	 * 删除机构
	 * @param id
	 * @return
	 */
	int deleteOrg(String id);
	
	/**
	 * 通过机构id查询机构信息
	 * @param id
	 * @return
	 */
	CmsJgOrgDTO queryOrgById(String  id);
	
	/**
	 * 修改机构
	 * @param dto
	 * @return
	 */
	int updateOrg(CmsJgAddressAndOrgDTO dto);
	
	/**
	 * 根据id修改机构信息
	 * @param dto
	 * @return
	 */
	 int updateOrg(CmsJgOrgDTO cmsJgOrg);
	 
	 /**
	  * 查询机构信息
	  * @return
	  */
	List<CmsJgOrgDTO> queryOrg();
	
	/**
	 * 判断机构名除了本身之外是否重复
	 * @param orgName
	 * @param id
	 * @return
	 */
	List<CmsJgOrgDTO> queryOrg(String orgName, String id);
	
	/**
	 * 查询所有的抗生素规则
	 * @return
	 */
	List<TaPhaDictDrugAntibioticLevelPolityDTO> queryAntibiotic();
}
