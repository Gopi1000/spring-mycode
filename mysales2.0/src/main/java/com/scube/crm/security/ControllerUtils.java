package com.scube.crm.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public abstract class ControllerUtils {
	
	protected MySalesUser getUserSecurity() {
		MySalesUser user=null;
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(null!=auth.getPrincipal() && !auth.getPrincipal().toString().equalsIgnoreCase("anonymousUser")){
		user=(MySalesUser) auth.getPrincipal();
		}
	return user;
	}
}
