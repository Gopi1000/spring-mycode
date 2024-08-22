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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.scube.crm.bo.PrivilegeBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.bo.RoleBO;
import com.scube.crm.service.PrivilegeService;

import com.scube.crm.service.RoleService;
import com.scube.crm.utils.UserRoles;

@Controller

public class RoleController {

	@Autowired
	RoleService roleservice;
	
	@Autowired
	PrivilegeService privilegeservice;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/create-role", method = RequestMethod.GET)
	public String createrole(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		RoleBO rolebo = new RoleBO();

		model.addAttribute("createrole", rolebo);

		return "create-role";
	}

	@RequestMapping(value = "/create-role", method = RequestMethod.POST)
	public String Createrole(@ModelAttribute("createrole") RoleBO rolebo, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "product-create";
		}

		if (null != rolebo) {

			rolebo = roleservice.insertrole(rolebo);
			
			model.addAttribute("successMessage",messageSource.getMessage("Role.Creation",null,null));
			}
		
		    return "redirect:/view-role";

	}

	@RequestMapping(value = "/view-role", method = RequestMethod.GET)
	public String viewrole(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		List<RoleBO> rolebo = new ArrayList<RoleBO>();

		if (null != rolebo) {

			rolebo = roleservice.retrieverole(rolebo);

		if(null!=request.getParameter("successMessage")) {
	     model.addAttribute("successMessage", request.getParameter("successMessage"));
			}
			model.addAttribute("viewlist", rolebo);
			model.addAttribute("searchRole", new RoleBO());
		}
		return "view-role";
	}

	@RequestMapping(value = "/edit-role", method = RequestMethod.GET)
	public String editrole(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		String id = request.getParameter("roleid");
		if (null != id) {
			long editId = Long.parseLong(id);

			RoleBO bo = roleservice.getParticularrole(editId);

			if (null != bo) {
				model.addAttribute("updaterole", bo);

			}
		}
		return "edit-role";

	}

	@RequestMapping(value = "/edit-role", method = RequestMethod.POST)
	public String changeProductDetails(@ModelAttribute RoleBO rolebo, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		String id = request.getParameter("roleid");

		if (null != id) {
			long editId = Long.parseLong(id);

			if (null != rolebo) {
				rolebo.setRoleid(editId);

				roleservice.updateRole(rolebo);
				model.addAttribute("successMessage",messageSource.getMessage("Role.Update",null,null));
			}
		}

		return "redirect:/view-role";

	}

	@RequestMapping(value = "/delete-role", method = RequestMethod.GET)
	public String deleterole(@ModelAttribute RoleBO rolebo, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		 String response=null;
		String id = request.getParameter("roleid");
		if (null != id) {
			long deleteId = Long.parseLong(id);

			if (null != rolebo) {

				rolebo.setRoleid(deleteId);
				response= roleservice.deleteRole(deleteId);
				model.addAttribute("successMessage",messageSource.getMessage("Role.Delete",null,null));
			}

		}

		return "redirect:/view-role";
	}

	@RequestMapping(value = "/search-role", method = RequestMethod.POST)
	public String searchRole(@ModelAttribute("searchRole") RoleBO rolebo1, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		List<RoleBO> rolebo = new ArrayList<RoleBO>();
		rolebo = roleservice.listrole(rolebo1);

		if (null != rolebo && 0 < rolebo.size()) {
			model.addAttribute("viewlist", rolebo);
			model.addAttribute("searchRole", rolebo1);

			return "view-role";
		} else {
			 model.addAttribute("errorMessage", "No Records Found");
			return "view-role";
		}
	}
	
	
	
	@RequestMapping(value = "/create-role-privilege", method = RequestMethod.GET)
	public String createroleprivilege(Model model, HttpServletRequest request, HttpSession session) throws Exception {
		
		RoleBO rpbo=new RoleBO();
		
		
		
		if (null!=rpbo) {
			RoleBO bo=new RoleBO();
			List<RoleBO> list=roleservice.listRole(bo);
			model.addAttribute("createroleprivilege", rpbo);
			model.addAttribute("roleList",list);
		}
		
		if(null!=rpbo){
			PrivilegeBO bo1=new PrivilegeBO();
			List<PrivilegeBO> list1=roleservice.listPrivilege(bo1);
			model.addAttribute("privilegelist",list1);
		}
		return "create-role-privilege";
		
	}
	
	@RequestMapping(value = "/create-role-privilege", method = RequestMethod.POST)
	public String Createroleprivilege(@ModelAttribute("createroleprivilege") RoleBO rpbo, BindingResult result,
			HttpServletRequest request, Model model) {

		RoleBO borole = new RoleBO();
		
		borole.setRoleid(Long.valueOf(rpbo.getRolename()));
		List<PrivilegeBO> privilegebolist = new ArrayList<PrivilegeBO>();
		
		if (null != rpbo.getPrivilegenames() && rpbo.getPrivilegenames().length > 0) {
			
				for (String privilegeid : rpbo.getPrivilegenames()) {
				PrivilegeBO bo =privilegeservice.getParticularprivilege(Long.valueOf(privilegeid));
				privilegebolist.add(bo);
			}
		}
		
	    borole=roleservice.getParticularrole(borole.getRoleid());
		borole.setPrivilegelist(privilegebolist);
		borole=roleservice.insertroleprivilege(borole);
		
        List<RoleBO> rolebo = new ArrayList<RoleBO>();
		
		if (null != rolebo) {

			rolebo = roleservice.retrieveroleprivilege(rolebo);
			model.addAttribute("viewroleprivilegelist", rolebo);
		}
		
		
	    return "redirect:/view-role-privilege";

	}
	
	
	@RequestMapping(value = "/view-role-privilege", method = RequestMethod.GET)
	public String viewroleprivilege(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		List<RoleBO> rolebo = new ArrayList<RoleBO>();
		
		if (null != rolebo) {

			rolebo = roleservice.retrieveroleprivilege(rolebo);
			model.addAttribute("viewroleprivilegelist", rolebo);
		}
		

		return "view-role-privilege";
	}
	
	
	@RequestMapping(value = "/edit-role-privilege", method = RequestMethod.GET)
	public String editroleprivilege(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		String id = request.getParameter("roleid");
		if (null != id) {
			long editId = Long.parseLong(id);

			RoleBO bo = roleservice.getParticularroleprivilege(editId);
			List<RoleBO> list=roleservice.listRole(bo);
			
			PrivilegeBO bo1=new PrivilegeBO();
			List<PrivilegeBO> list1=roleservice.listPrivilege(bo1);
			

			if (null != bo) {
				model.addAttribute("updateroleprivilege", bo);
				model.addAttribute("roleList",list);
				model.addAttribute("privilegelist",list1);

			}
		}
		
		return "edit-role-privilege";

	}
	
	/*@RequestMapping(value = "/edit-role-privilege", method = RequestMethod.POST)
	public String editRoleprivilege(@ModelAttribute("editRoleprivilege") RoleBO rolebo1, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		List<RoleBO> rolebo = new ArrayList<RoleBO>();
		rolebo = roleservice.listofrole(rolebo1);
		
		if (null != rolebo && 0 < rolebo.size()) {
			model.addAttribute("viewlist", rolebo);
			model.addAttribute("searchRole", rolebo1);
		}
		
		return "edit-role-privilege";
	}*/
	
	
	
	
	@RequestMapping(value = "/edit-role-privilege", method = RequestMethod.POST)
	public String updateroleprivilege(@ModelAttribute RoleBO rolebo, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		/*String id = request.getParameter("roleid");

		if (null != id) {
			long editId = Long.parseLong(id);

			if (null != rolebo) {
				rolebo.setRoleid(editId);

				roleservice.updateRole(rolebo);
				//model.addAttribute("successMessage",messageSource.getMessage("Role.Update",null,null));
			}
		}*/

		return "redirect:/view-role-privilege";

	}
	
	
	@RequestMapping(value = "/delete-role-privilege", method = RequestMethod.GET)
	public String deleteroleprivilege(@ModelAttribute RoleBO rolebo, Model model, HttpServletRequest request)
			throws IOException, ServletException {
		
		 String response=null;
		String id = request.getParameter("roleid");
		if (null != id) {
			long deleteId = Long.parseLong(id);

			if (null != rolebo) {

				rolebo.setRoleid(deleteId);
				response=roleservice.deleteRoleprivilege(deleteId);
				/*model.addAttribute("successMessage",messageSource.getMessage("Role.Delete",null,null));*/
			}

		}

		return "redirect:/view-role-privilege";
	}
	

}
