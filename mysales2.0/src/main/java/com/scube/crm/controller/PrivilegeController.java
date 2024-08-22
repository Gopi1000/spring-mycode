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

import com.scube.crm.bo.PrivilegeBO;
import com.scube.crm.service.PrivilegeService;

@Controller
public class PrivilegeController {
	
	@Autowired
	PrivilegeService privilegeservice;
	
	@Autowired
	MessageSource messageSource;

	
	@RequestMapping(value = "/create-privilege", method = RequestMethod.GET)
	public String createprivilege(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		PrivilegeBO privilegebo = new PrivilegeBO();

		model.addAttribute("createprivilege", privilegebo);

		return "create-privilege";
	}
	
	
	
	@RequestMapping(value = "/create-privilege", method = RequestMethod.POST)
	public String postCreateServices(@ModelAttribute("createprivilege") PrivilegeBO privilegebo, Model model) {

		if (null != privilegebo) {

			privilegebo = privilegeservice.insertprivilege(privilegebo);

		model.addAttribute("successMessage", messageSource.getMessage("Privilege.Creation", null, null));
		}
		return "redirect:/view-privilege";

	}
	
	
	@RequestMapping(value = "/view-privilege", method = RequestMethod.GET)
	public String viewprivilege(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		List<PrivilegeBO> privilegebo = new ArrayList<PrivilegeBO>();

		if (null != privilegebo) {

			privilegebo = privilegeservice.retrievePrivilege(privilegebo);
			
			if(null!=request.getParameter("successMessage")) {
			 model.addAttribute("successMessage", request.getParameter("successMessage"));
		     }

			model.addAttribute("viewprivilege", privilegebo);
			model.addAttribute("searchprivilege",new PrivilegeBO());
		}
		return "view-privilege";
	}
	
	@RequestMapping(value = "/search-privilege", method = RequestMethod.POST)
	public String searchprivilege(@ModelAttribute("searchprivilege") PrivilegeBO privilegebo1, Model model, HttpServletRequest request)
			throws IOException, ServletException {
		
		List<PrivilegeBO> privilegebo = new ArrayList<PrivilegeBO>();
		privilegebo = privilegeservice.listprivilege(privilegebo1);

		if (null != privilegebo && 0 < privilegebo.size()) {
			model.addAttribute("viewprivilege", privilegebo);
			model.addAttribute("searchprivilege", privilegebo1);

			return "view-privilege";
		} else {
			model.addAttribute("errorMessage", "No Records Found");
			return "view-privilege";
		}
	}
	
	
	
	@RequestMapping(value = "/edit-privilege", method = RequestMethod.GET)
	public String editprivilege(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		String id = request.getParameter("privilegeid");
		if (null != id) {
			long editId = Long.parseLong(id);

			PrivilegeBO bo = privilegeservice.getParticularprivilege(editId);

			if (null != bo) {
				model.addAttribute("updateprivilege", bo);

			}
		}
		return "edit-privilege";

	}
	
	
	@RequestMapping(value = "/edit-privilege", method = RequestMethod.POST)
	public String changeprivilege(@ModelAttribute PrivilegeBO privilegebo, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		String response = null;
		String id = request.getParameter("privilegeid");

		if (null != id) {
			long editId = Long.parseLong(id);

			if (null != privilegebo) {
				privilegebo.setPrivilegeid(editId);

				response = privilegeservice.updateprivilege(privilegebo);
				model.addAttribute("successMessage", messageSource.getMessage("Privilege.Update", null, null));
			}
		}

		return "redirect:/view-privilege";

	}
	
	@RequestMapping(value = "/delete-privilege", method = RequestMethod.GET)
	public String deleteprivilege(@ModelAttribute PrivilegeBO privilegebo, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		String response = null;

		String id = request.getParameter("privilegeid");
		if (null != id) {
			long deleteId = Long.parseLong(id);

			if (null != privilegebo) {

				privilegebo.setPrivilegeid(deleteId);
				response = privilegeservice.deleteprivilege(deleteId);
				model.addAttribute("successMessage", messageSource.getMessage("Privilege.Delete", null, null));
			}
           }
         return "redirect:/view-privilege";
	}
}
