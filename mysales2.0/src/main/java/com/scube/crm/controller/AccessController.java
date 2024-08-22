package com.scube.crm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scube.crm.bo.AccessBO;
import com.scube.crm.service.AccessService;

@Controller
public class AccessController {
	
	@Autowired
	AccessService accessservice;
	
	@Autowired
	MessageSource messageSource;
	
	
	@RequestMapping(value = "/create-access", method = RequestMethod.GET)
	public String createrole(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		AccessBO bo = new AccessBO();

		model.addAttribute("createAccess", bo);

		return "create-access";
	}
	
	@RequestMapping(value = "/create-access", method = RequestMethod.POST)
	public String postCreateServices(@ModelAttribute("createaccess") AccessBO accessbo, Model model) {

		if (null != accessbo) {

			accessbo =  accessservice.insertAccess(accessbo);

		}
		model.addAttribute("successMessage", messageSource.getMessage("Access.Creation", null, null));

		return "redirect:/view-access";
       }
	
	
	@RequestMapping(value = "/view-access", method = RequestMethod.GET)
	public String viewaccess(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		List<AccessBO> accessbo = new ArrayList<AccessBO>();

		if (null != accessbo) {

			accessbo = accessservice.retrieveaccess(accessbo);
			
			if(null!=request.getParameter("successMessage")) {
		    model.addAttribute("successMessage", request.getParameter("successMessage"));
		    }

			model.addAttribute("viewlist", accessbo);
			model.addAttribute("searchAccess", new AccessBO());
		}
		return "view-access";
	}
	
	
	@RequestMapping(value = "/search-access", method = RequestMethod.POST)
	public String searchaccess(@ModelAttribute("searchAccess") AccessBO bo, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		List<AccessBO> accessbo = new ArrayList<AccessBO>();
		accessbo = accessservice.listaccess(bo);

		if (null != accessbo && 0 < accessbo.size()) {
			model.addAttribute("viewlist", accessbo);
			model.addAttribute("searchAccess",bo);

			return "view-access";
		} else {
			 model.addAttribute("errorMessage", "No Records Found");
			return "view-access";
		}
	}
	
	@RequestMapping(value = "/edit-access", method = RequestMethod.GET)
	public String editaccess(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		String id = request.getParameter("id");
		if (null != id) {
			long editId = Long.parseLong(id);

			AccessBO bo = accessservice.getParticularaccess(editId);

			if (null != bo) {
				model.addAttribute("updateaccess", bo);
            }
		}
		return "edit-access";

	}
	
	@RequestMapping(value = "/edit-access", method = RequestMethod.POST)
	public String changeProductDetails(@ModelAttribute AccessBO accessbo, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		String response = null;
		String id = request.getParameter("id");

		if (null != id) {
			long editId = Long.parseLong(id);

			if (null != accessbo) {
				accessbo.setId(editId);

				response = accessservice.updateaccess(accessbo);
				model.addAttribute("successMessage", messageSource.getMessage("Access.Update", null, null));
			}
		}
          return "redirect:/view-access";

	}
	
	
	@RequestMapping(value = "/delete-access", method = RequestMethod.GET)
	public String deletecontact(@ModelAttribute AccessBO accessbo, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		String response = null;

		String id = request.getParameter("id");
		if (null != id) {
			long deleteId = Long.parseLong(id);

			if (null != accessbo) {

				accessbo.setId(deleteId);
				response = accessservice.deleteaccess(deleteId);
				model.addAttribute("successMessage", messageSource.getMessage("Access.Delete", null, null));
			}

		}

		return "redirect:/view-access";
	}

}
