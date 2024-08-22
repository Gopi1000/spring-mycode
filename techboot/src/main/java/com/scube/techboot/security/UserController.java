package com.scube.techboot.security;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@Controller
@Scope("session")
public class UserController {	

	static final Logger LOGGER = Logger.getLogger(UserController.class);


	@RequestMapping(value = "/home-dashBoard", method = RequestMethod.GET)
	public String postLogin(Model model, HttpServletRequest request) throws Exception {		
		String userRole=null;		
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		validatePrinciple(authentication.getPrincipal());
		TechBootUser loggedInUser = (TechBootUser) authentication.getPrincipal();
		HttpSession session=request.getSession();       

		if(null!=loggedInUser){
			List<String> userType=new ArrayList<String>();
			for(GrantedAuthority roles:loggedInUser.getAuthorities()){
				userType.add(roles.getAuthority());
			}
			userRole=userType.get(0);	      
		}
		if(userRole.equalsIgnoreCase("admin")){
			session.setAttribute("userType", userRole);
			session.setAttribute("adminId", loggedInUser.getLoginId());
			return "redirect:/admin-home";
		}
		if(userRole.equalsIgnoreCase("company")){
			session.setAttribute("userType", userRole);
			session.setAttribute("companyId", loggedInUser.getCompany().getCompanyId());
			session.setAttribute("companyType",loggedInUser.getCompany().getCompanyType());
			return "redirect:/company-home";
		}
		if(userRole.equalsIgnoreCase("student")){
			session.setAttribute("userType", userRole);
			session.setAttribute("studentId", loggedInUser.getStudentId());
			session.setAttribute("studentLoginId", loggedInUser.getLoginId());
			return "redirect:/student-home";

		}

		return "sign-in";

	}
	private void validatePrinciple(Object principal) {
		if (!(principal instanceof UserDetails)) {
			throw new  IllegalArgumentException("Principal can not be null!");
		}
	}
}