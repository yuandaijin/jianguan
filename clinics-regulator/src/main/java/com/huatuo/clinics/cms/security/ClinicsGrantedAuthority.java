package com.huatuo.clinics.cms.security;

import org.springframework.security.core.GrantedAuthority;

import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;

public class ClinicsGrantedAuthority implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	private String authority;

	@SuppressWarnings("unused")
	private static ClinicsGrantedAuthority AUTHORITY_PERSON = new ClinicsGrantedAuthority("ROLE_PERSON");
	@SuppressWarnings("unused")
	private static ClinicsGrantedAuthority AUTHORITY_ORG = new ClinicsGrantedAuthority("ROLE_ORG");
	@SuppressWarnings("unused")
	private static ClinicsGrantedAuthority AUTHORITY_DOCTOR = new ClinicsGrantedAuthority("ROLE_DOCTOR");

	public ClinicsGrantedAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public static ClinicsGrantedAuthority getAuthority(CmsJgUserinfoDTO userInfo) {
//		int userType = userInfo.getUserType();
//		if (userType == Config.FIELD_USER_TYPE_PERSON) {
//			return AUTHORITY_PERSON;
//		}
//		if (userType == Config.FIELD_USER_TYPE_ORG) {
//			return AUTHORITY_ORG;
//		}
//		if (userType == Config.FIELD_USER_TYPE_DOCTOR) {
//			return AUTHORITY_DOCTOR;
//		}
		return null;
	}
}
