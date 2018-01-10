package com.huatuo.clinics.cms.response;


import com.huatuo.clinics.cms.bean.CmsOrgInfoDTO;
import com.huatuo.common.BaseResponse;

/**
 * 诊所基本信息
 * @author dengyajie
 *
 */
public class CmsClinicsResponse extends BaseResponse {
	
	private static final long serialVersionUID = -3885139222370088285L;
	
	private CmsOrgInfoDTO cmsOrgInfoDTO;

	public CmsOrgInfoDTO getCmsOrgInfoDTO() {
		return cmsOrgInfoDTO;
	}

	public void setCmsOrgInfoDTO(CmsOrgInfoDTO cmsOrgInfoDTO) {
		this.cmsOrgInfoDTO = cmsOrgInfoDTO;
	}
	


		
}
