package com.huatuo.clinics.cms.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.huatuo.clinics.cms.bean.CmsJgUserinfoDTO;

public class ClinicsUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private CmsJgUserinfoDTO userInfo;

	public ClinicsUserDetails(CmsJgUserinfoDTO userInfo) {
		this.userInfo = userInfo;
	}

	public CmsJgUserinfoDTO getUserInfo() {
		return userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<ClinicsGrantedAuthority> collection = new ArrayList<ClinicsGrantedAuthority>();
		List<String>  l=userInfo.getAuthorityList();
		for (String str : l) {
			collection.add(new ClinicsGrantedAuthority(str));
		}
		return collection;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return userInfo.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
