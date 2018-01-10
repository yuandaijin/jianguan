package com.huatuo.clinics.cms.db.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huatuo.clinics.cms.db.bean.CmsJgOrg;
import com.huatuo.clinics.cms.db.bean.CmsJgOrgExample;
import com.huatuo.clinics.cms.db.bean.TaPhaDictDrugAntibioticLevelPolity;
import com.huatuo.clinics.cms.db.bean.TaPhaDictDrugAntibioticLevelPolityExample;
import com.huatuo.clinics.cms.db.mapper.CmsJgOrgMapper;
import com.huatuo.clinics.cms.db.mapper.TaPhaDictDrugAntibioticLevelPolityMapper;
import com.huatuo.clinics.cms.db.repository.DbCmsJgOrgRepository;

@Repository
public class DbCmsJgOrgRepositoryImpl implements DbCmsJgOrgRepository {

	@Autowired
	private CmsJgOrgMapper cmsJgOrgMapper;
	@Autowired
	private TaPhaDictDrugAntibioticLevelPolityMapper taPhaDictDrugAntibioticLevelPolityMapper;
	
	@Override
	public Integer save(CmsJgOrg cmsJgOrg) {
		return cmsJgOrgMapper.insert(cmsJgOrg);
	}
	
	@Override
	public List<CmsJgOrg> queryOrgByName(String orgName) {
		CmsJgOrgExample example =new CmsJgOrgExample();
		example.createCriteria().andOrgNameEqualTo(orgName);
		return cmsJgOrgMapper.selectByExample(example);
	}

	@Override
	public int countOrg(String province,String city,String county) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(null != province){
			map.put("province", province);
		}
		if(null != city){
			map.put("city", city);
		}
		if(null != county){
			map.put("county", county);
		}
		return cmsJgOrgMapper.countOrg(map);
	}

	@Override
	public List<CmsJgOrg> queryOrgPage(int start, Integer pageSize,
			String province,String city,String county) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(null != province){
			map.put("province", province);
		}
		if(null != city){
			map.put("city", city);
		}
		if(null != county){
			map.put("county", county);
		}
		map.put("start", start);
		map.put("pageSize", pageSize);
		return cmsJgOrgMapper.queryOrgPage(map);
	}

	@Override
	public CmsJgOrg queryOrgById(String  id) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(null != id){
			map.put("id", id);
		}
		return cmsJgOrgMapper.queryOrgById(map);
	}

	@Override
	public int deleteOrg(String id) {
		CmsJgOrgExample example=new CmsJgOrgExample();
		example.createCriteria().andIdEqualTo(id);
		return cmsJgOrgMapper.deleteByExample(example);
	}

	@Override
	public int updateOrg(CmsJgOrg dto) {
		CmsJgOrgExample example=new CmsJgOrgExample();
		example.createCriteria().andIdEqualTo(dto.getId());
		return cmsJgOrgMapper.updateByExampleSelective(dto, example);
	}

	@Override
	public List<CmsJgOrg> qeryOrg() {
		CmsJgOrgExample example=new CmsJgOrgExample();
		return cmsJgOrgMapper.selectByExample(example);
	}

	@Override
	public List<CmsJgOrg> qeryOrgByNotId(String orgName, String id) {
		CmsJgOrgExample example=new CmsJgOrgExample();
		example.createCriteria().andOrgNameEqualTo(orgName).andIdNotEqualTo(id);
		return cmsJgOrgMapper.selectByExample(example);
	}

	@Override
	public List<TaPhaDictDrugAntibioticLevelPolity> queryAntibiotic() {
		TaPhaDictDrugAntibioticLevelPolityExample example =new TaPhaDictDrugAntibioticLevelPolityExample();
		example.createCriteria().andDelFlagEqualTo(0);
		return taPhaDictDrugAntibioticLevelPolityMapper.selectByExample(example);
	}

}
