package com.scube.techboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {
		
	@ModelAttribute
	public Model getBackNavigation(Model model,HttpServletRequest request){		
		model.addAttribute("companyType",request.getSession().getAttribute("companyType"));		
		return model;
		
	}

}