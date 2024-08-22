package com.scube.techboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.scube.techboot.security.TechBootUser;

@Component
public abstract class ControllerUtils {
	
	protected TechBootUser getUserSecurity() {
		TechBootUser user=null;
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(null!=auth.getPrincipal() && !auth.getPrincipal().toString().equalsIgnoreCase("anonymousUser")){
		user=(TechBootUser) auth.getPrincipal();
		}
	return user;
	}
	
	
	protected String getUserRole() {
		String userRole=null;
		TechBootUser user=getUserSecurity();
		List<String> userType=new ArrayList<String>();
		if(null!=user){
		for(GrantedAuthority roles:user.getAuthorities()){
			userType.add(roles.getAuthority());
		}
		userRole=userType.get(0);
		}
		return userRole;
	}
}
