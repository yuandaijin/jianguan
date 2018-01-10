package com.huatuo.clinics.cms.services.regulator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.clinics.cms.bean.CmsJgAddressAndOrgDTO;
import com.huatuo.clinics.cms.bean.CmsJgAddressDTO;
import com.huatuo.clinics.cms.bean.CmsJgOrgDTO;
import com.huatuo.clinics.cms.bean.TaPhaDictDrugAntibioticLevelPolityDTO;
import com.huatuo.clinics.cms.db.bean.CmsJgOrg;
import com.huatuo.clinics.cms.db.bean.TaPhaDictDrugAntibioticLevelPolity;
import com.huatuo.clinics.cms.db.repository.DbCmsJgOrgRepository;
import com.huatuo.clinics.cms.util.Page;
import com.huatuo.clinics.cms.util.Utils;

@Service
public class CmsJgOrgServiceImpl implements CmsJgOrgService {
	
	@Autowired
	private DbCmsJgOrgRepository dbCmsJgOrgRepository;
	
	@Autowired
	private CmsJgAddressService cmsJgAddressService;

	@Override
	public Integer save(CmsJgOrgDTO cmsJgOrg) {
		CmsJgOrg dto = Utils.exchangeObject(cmsJgOrg, CmsJgOrg.class);
		return dbCmsJgOrgRepository.save(dto);
	}

	@Override
	public int insertOrg(CmsJgAddressAndOrgDTO dto) {
		CmsJgAddressDTO cmsJgAddress = Utils.exchangeObject(dto, CmsJgAddressDTO.class);
		cmsJgAddress.setAddressId(UUID.randomUUID().toString());
		//新增机构的地址
		cmsJgAddressService.save(cmsJgAddress);
		CmsJgOrgDTO cmsJgOrg = Utils.exchangeObject(dto, CmsJgOrgDTO.class);
		//拿到新增地址的id去新增机构信息
		cmsJgOrg.setAddressid(cmsJgAddress.getAddressId());
		cmsJgOrg.setId(UUID.randomUUID().toString());
		cmsJgOrg.setAddress(dto.getOrgAddress());
		int i=this.save(cmsJgOrg);
		if(i >0){
			return i;
		}
		return 0;
	}

	@Override
	public List<CmsJgOrgDTO> queryOrgByName(String orgName) {
		List<CmsJgOrgDTO> list=new ArrayList<CmsJgOrgDTO>();
		List<CmsJgOrg> dto=dbCmsJgOrgRepository.queryOrgByName(orgName);
		for (CmsJgOrg cmsJgOrg : dto) {
			CmsJgOrgDTO cmsOrg = Utils.exchangeObject(cmsJgOrg, CmsJgOrgDTO.class);
			list.add(cmsOrg);
		}
		if(list.size() >0){
			return list;
		}
		return null;
	}

	@Override
	public Page<CmsJgOrgDTO> queryAllOrg(Integer pageNo, Integer pageSize,
			 String province,String city,String county) {
		int total = dbCmsJgOrgRepository.countOrg(province, city,county);
		Page<CmsJgOrgDTO> pageBack = new Page<CmsJgOrgDTO>(total, pageNo, pageSize);
		List<CmsJgOrgDTO> answerBeanList = new ArrayList<CmsJgOrgDTO>();
		if(total > 0){
			Page<CmsJgOrg> page = new Page<CmsJgOrg>(total, pageNo, pageSize);
			List<CmsJgOrg> userinfoList= dbCmsJgOrgRepository.queryOrgPage(page.getStart(), pageSize,province,city,county);
			for(CmsJgOrg cmsJgOrg : userinfoList){
				CmsJgOrgDTO dto = Utils.exchangeObject(cmsJgOrg, CmsJgOrgDTO.class);
				answerBeanList.add(dto);
			}
		}
		pageBack.setList(answerBeanList);
		return pageBack;
	}

	@Override
	public int deleteOrg(String  id) {
		CmsJgOrgDTO dto=this.queryOrgById(id);
		//删除机构关联的地址信息
		cmsJgAddressService.deleteAddress(dto.getAddressid());
//		//删除机构
		int i=dbCmsJgOrgRepository.deleteOrg(id);
		if(i > 0){
			return i;
		}
		return 0;
	}

	@Override
	public CmsJgOrgDTO queryOrgById(String  id) {
		CmsJgOrg dto=dbCmsJgOrgRepository.queryOrgById(id);
		CmsJgOrgDTO result = Utils.exchangeObject(dto, CmsJgOrgDTO.class);
		if(null != result){
			return result;
		}
		return null;
	}

	@Override
	public int updateOrg(CmsJgAddressAndOrgDTO dto) {
		//根据id查询机构地址信息
		CmsJgOrg addressDto=dbCmsJgOrgRepository.queryOrgById(dto.getId());
		CmsJgAddressDTO cmsJgAddress = Utils.exchangeObject(dto, CmsJgAddressDTO.class);
		cmsJgAddress.setAddressId(addressDto.getAddressid());
		//修改机构的地址
		cmsJgAddressService.updateAddress(cmsJgAddress);
		CmsJgOrgDTO cmsJgOrg = Utils.exchangeObject(dto, CmsJgOrgDTO.class);
		//拿到新增地址的id去新增机构信息
		cmsJgOrg.setAddressid(cmsJgAddress.getAddressId());
		cmsJgOrg.setAddress(dto.getOrgAddress());
		int i=this.updateOrg(cmsJgOrg);
		if(i >0){
			return i;
		}
		return 0;
	}
	
	@Override
	public int updateOrg(CmsJgOrgDTO cmsJgOrg) {
		CmsJgOrg dto = Utils.exchangeObject(cmsJgOrg, CmsJgOrg.class);
		return dbCmsJgOrgRepository.updateOrg(dto);
	}

	@Override
	public List<CmsJgOrgDTO> queryOrg() {
		List<CmsJgOrgDTO> resultList= new ArrayList<CmsJgOrgDTO>();
		List<CmsJgOrg> list=dbCmsJgOrgRepository.qeryOrg();
		for (CmsJgOrg cmsJgOrg : list) {
			CmsJgOrgDTO dto = Utils.exchangeObject(cmsJgOrg, CmsJgOrgDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List<CmsJgOrgDTO> queryOrg(String orgName, String id) {
		List<CmsJgOrgDTO> resultList= new ArrayList<CmsJgOrgDTO>();
		List<CmsJgOrg> list=dbCmsJgOrgRepository.qeryOrgByNotId(orgName,id);
			for (CmsJgOrg cmsJgOrg : list) {
				CmsJgOrgDTO dto = Utils.exchangeObject(cmsJgOrg, CmsJgOrgDTO.class);
				resultList.add(dto);
			}
			if(resultList.size() > 0){
				return resultList;
			}
			return null;
	}

	@Override
	public List<TaPhaDictDrugAntibioticLevelPolityDTO> queryAntibiotic() {
		List<TaPhaDictDrugAntibioticLevelPolityDTO> resultList= new ArrayList<TaPhaDictDrugAntibioticLevelPolityDTO>();
		List<TaPhaDictDrugAntibioticLevelPolity> list=dbCmsJgOrgRepository.queryAntibiotic();
		for (TaPhaDictDrugAntibioticLevelPolity taPhaDictDrugAntibioticLevelPolity : list) {
			TaPhaDictDrugAntibioticLevelPolityDTO dto = Utils.exchangeObject(taPhaDictDrugAntibioticLevelPolity, TaPhaDictDrugAntibioticLevelPolityDTO.class);
			resultList.add(dto);
		}
		if(resultList.size() > 0){
			return resultList;
		}
		return null;
	}

}
