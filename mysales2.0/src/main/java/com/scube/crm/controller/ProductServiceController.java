package com.scube.crm.controller;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.security.ControllerUtils;
import com.scube.crm.service.ProductService;
import com.scube.crm.utils.UserRoles;

@Controller
@Scope("session")
@SessionAttributes("/admin")
public class ProductServiceController  extends ControllerUtils implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(AdminController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private MessageSource messageSource;


	@RequestMapping(value="/create-productservice",method=RequestMethod.GET )
	public String getCreateService(Model model,HttpServletRequest request,HttpSession session) throws Exception{

		ProductServiceBO productServiceBO=new ProductServiceBO();
		 long loginId=getUserSecurity().getLoginId();		
			String userType = getUserSecurity().getRole();
			AdminLoginBO adminLoginBO=new AdminLoginBO();
			adminLoginBO.setUserType(userType);
			adminLoginBO.setId(loginId);
			adminLoginBO.setFirstName(getUserSecurity().getName());
			model.addAttribute("userType", adminLoginBO);
			if (0 < loginId && userType.equalsIgnoreCase("ROLE_ADMIN")) {
				adminLoginBO.setId(loginId);
				productServiceBO.setAdminLoginBO(adminLoginBO);
			}
		model.addAttribute("productServiceBO",productServiceBO);
		return "create-productservice";

	}
	
	@RequestMapping(value="/create-productservice" , method=RequestMethod.POST)
	public String postCreateServices(@Valid @ModelAttribute("productServiceBO")ProductServiceBO productServiceBO,BindingResult bindingResult,
			Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws FileNotFoundException{
		LOGGER.entry();
		long loginId = getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole();
		if (0 == loginId) {
			return "redirect:/admin-sign-in";
		}
		if (0 < loginId && userType.equalsIgnoreCase("ROLE_ADMIN")) {
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			adminLoginBO.setId(loginId);
			productServiceBO.setAdminLoginBO(adminLoginBO);
			}
		productServiceBO.setCreatedBy(loginId);
		if(null!=productServiceBO){
			ProductServiceBO productBO=productService.createServices(productServiceBO);
			if(0!=productBO.getServiceId()){
				model.addAttribute("successMessage",messageSource.getMessage("Product.Creation",null,null));
				return "redirect:/view-productservice";
			}
		}
		return "create-productservice";
	}
	
	@RequestMapping(value ="view-productservice", method = RequestMethod.GET)
	public String viewService(Model model, HttpServletRequest request,HttpSession session, String paging) throws FileNotFoundException{
		
		ProductServiceBO serviceBO=new ProductServiceBO();
		List<ProductServiceBO> serviceList=new ArrayList<ProductServiceBO>();
		long loginId=getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();
		AdminLoginBO adminLoginBO=new AdminLoginBO();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(loginId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			serviceBO.setAdminLoginBO(adminLoginBO);
		}
		 serviceList = productService.listservice(serviceBO);

		if (null != serviceList) {
			model.addAttribute("serviceList", serviceList);
			model.addAttribute("searchProduct",new ProductServiceBO());
		}
		if(null!=request.getParameter("successMessage")) {
			model.addAttribute("successMessage", request.getParameter("successMessage"));
		}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}	

		//model.addAttribute("serviceBO", serviceBO);
		return "view-productservice";
	}

	@RequestMapping(value = "/search-productservice", method = RequestMethod.POST)
	public String searchProduct( @ModelAttribute("searchProduct") ProductServiceBO productServiceBO,
			HttpServletRequest request,HttpSession session, Model model) throws MyClientsException, SerialException, SQLException {
		long adminId=getUserSecurity().getLoginId();	
		String userType = getUserSecurity().getRole();
		AdminLoginBO adminLoginBO=new AdminLoginBO();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(adminId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		productServiceBO.setId(adminId); 
		ProductServiceBO productServiceBo=new ProductServiceBO();
		productServiceBo.setServiceName(productServiceBO.getServiceName());
		List<ProductServiceBO> serviceList=new ArrayList<ProductServiceBO>();
		serviceList = productService.listservice(productServiceBO);
		if (null != serviceList && 0 < serviceList.size()) {
			model.addAttribute("serviceList", serviceList);
			model.addAttribute("searchProduct",productServiceBo);
			return "view-productservice";
		}else {
			model.addAttribute("errorMessage","No Records Found");
			return "view-productservice";
		}
	}
	
	@RequestMapping(value = "edit-productservice", method = RequestMethod.GET)
	public String editProduct(Model model, HttpServletRequest request,@RequestParam("serviceId") Integer serviceId)
			throws FileNotFoundException, MyClientsException {
		LOGGER.entry();
		ProductServiceBO productServiceBO=new ProductServiceBO();
		long adminId=getUserSecurity().getLoginId();	
		String userType = getUserSecurity().getRole();
		AdminLoginBO adminLoginBO=new AdminLoginBO();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(adminId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		productServiceBO.setId(adminId); 
		productServiceBO.setServiceId(serviceId);
		if(0<productServiceBO.getServiceId()){
			productServiceBO=productService.getServiceObject(productServiceBO);
		}
		if (null != productServiceBO) {
			model.addAttribute("productBO", productServiceBO);
		}
		return "edit-productservice";
	}
	
	@RequestMapping(value = "edit-productservice", method = RequestMethod.POST)
	public String editProduct(@Valid @ModelAttribute("productBO") ProductServiceBO productServiceBO, BindingResult bindingResult,
			Model model, HttpServletRequest request, HttpSession session) throws FileNotFoundException {
		LOGGER.entry();
		long loginId=getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();

		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			adminLoginBO.setId(loginId);
			productServiceBO.setAdminLoginBO(adminLoginBO);
		} 
		boolean status = productService.serviceUpdate(productServiceBO);
		if (status) {
			model.addAttribute("successMessage",messageSource.getMessage("Product.Update",null,null));
			return "redirect:/view-productservice";
		}
		else {
			model.addAttribute("errorMessage", "Doesnot Exists");
		}
		model.addAttribute("productBO", productServiceBO);
		return "edit-productservice";
	}
	
	@RequestMapping(value = "/delete-productservice", method = RequestMethod.GET)
	public String deleteProduct(Model model, HttpServletRequest request, HttpSession session,@RequestParam("serviceId") Integer serviceId)
			throws FileNotFoundException {
		LOGGER.entry();
		ProductServiceBO productServiceBO=new ProductServiceBO();
		productServiceBO.setServiceId(serviceId);
		try {
			/* productServiceBO =productService.getLeads(serviceId); */
			if (null != productServiceBO) {
				Boolean status = productService.deleteService(productServiceBO);
				if (status) {
					model.addAttribute("successMessage", messageSource.getMessage("Product.Delete",null,null));
					return "redirect:/view-productservice";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/view-productservice";
	}	
}
