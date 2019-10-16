package com.bonheure.domain;

 

import org.springframework.security.core.GrantedAuthority;
 

 

public enum Role implements GrantedAuthority {
//		implements GrantedAuthority {

    SUPERADMIN, CLIENT, PRESTATAIRE;

    

	@Override
	public String getAuthority() {
		 
		return name();
	}

}
