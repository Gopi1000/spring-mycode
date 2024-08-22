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
import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.security.ControllerUtils;
import com.scube.crm.service.GstService;
import com.scube.crm.service.ProductService;
import com.scube.crm.utils.UserRoles;

@Controller
@Scope("session")
@SessionAttributes("/admin")
public class GstController extends ControllerUtils implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(AdminController.class);

	@Autowired
	private GstService gstService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value="/create-gst",method=RequestMethod.GET )
	public String createGst(Model model,HttpServletRequest request,HttpSession session) throws MyClientsException{
		GstBO gstBO=new GstBO();
		 long loginId=getUserSecurity().getLoginId();		
			String userType = getUserSecurity().getRole();
			AdminLoginBO adminLoginBo=new AdminLoginBO();
			adminLoginBo.setUserType(userType);
			adminLoginBo.setId(loginId);
			adminLoginBo.setFirstName(getUserSecurity().getName());
			model.addAttribute("userType", adminLoginBo);
			if (0 < loginId && userType.equalsIgnoreCase("ROLE_ADMIN")) {
				AdminLoginBO adminLoginBO = new AdminLoginBO();
				adminLoginBO.setId(loginId);
				gstBO.setAdminLoginBO(adminLoginBO);
			}
		model.addAttribute("gstBO",gstBO);
		return "create-gst";
	}
	
	@RequestMapping(value="/create-gst" , method=RequestMethod.POST)
	public String createGstValues(@Valid @ModelAttribute("gstBO")GstBO gstBO,BindingResult bindingResult,
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
			gstBO.setAdminLoginBO(adminLoginBO);
			}
		gstBO.setCreatedBy(loginId);
		if(null!=gstBO){
			GstBO productBO=gstService.createGstValues(gstBO);
			if(0!=productBO.getGstId()){
				model.addAttribute("successMessage",messageSource.getMessage("Gst.Creation",null,null));
				return "redirect:/view-gst";
			}
		}
		return "create-gst";
	}
	
	@RequestMapping(value ="view-gst", method = RequestMethod.GET)
	public String viewService(Model model, HttpServletRequest request,HttpSession session, String paging) throws FileNotFoundException{
		
		GstBO gstBO=new GstBO();
		List<GstBO> gstList=new ArrayList<GstBO>();
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
			gstBO.setAdminLoginBO(adminLoginBO);
		}
		gstList = gstService.getListGst(gstBO);

		if (null != gstList) {
			model.addAttribute("gstList", gstList);
			model.addAttribute("searchGst",new GstBO());
		}
		if(null!=request.getParameter("successMessage")) {
			model.addAttribute("successMessage", request.getParameter("successMessage"));
		}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}	
		return "view-gst";
	}
	
	@RequestMapping(value = "/search-gst", method = RequestMethod.POST)
	public String searchGst( @ModelAttribute("searchGst") GstBO gstBO,
			HttpServletRequest request,HttpSession session, Model model) throws MyClientsException, SerialException, SQLException {
		long adminId=getUserSecurity().getLoginId();	
		String userType = getUserSecurity().getRole();
		AdminLoginBO adminLoginBO=new AdminLoginBO();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(adminId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		gstBO.setId(adminId); 
		GstBO gstBo=new GstBO();
		gstBo.setSgst(gstBO.getSgst());
		gstBo.setCgst(gstBO.getCgst());
		gstBo.setStartDate(gstBO.getStartDate());
		List<GstBO> gstList=new ArrayList<GstBO>();
		gstList = gstService.getListGst(gstBO);
		if (null != gstList && 0 < gstList.size()) {
			model.addAttribute("gstList", gstList);
			model.addAttribute("searchGst",gstBo);
			return "view-gst";
		}else {
			model.addAttribute("errorMessage","No Records Found");
			return "view-gst";
		}
	}
	
	
	@RequestMapping(value = "edit-gst", method = RequestMethod.GET)
	public String editGstValues(Model model, HttpServletRequest request,@RequestParam("gstId") Integer gstId)
			throws FileNotFoundException, MyClientsException {
		LOGGER.entry();
		GstBO gstBO=new GstBO();
		long adminId=getUserSecurity().getLoginId();	
		String userType = getUserSecurity().getRole();
		AdminLoginBO adminLoginBO=new AdminLoginBO();
		adminLoginBO.setId(adminId);
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		gstBO.setGstId(gstId);
		if(0<gstBO.getGstId()){
			gstBO=gstService.getGstValues(gstBO);
		}
		if (null != gstBO) {
			model.addAttribute("gstBO", gstBO);
		}
		return "edit-gst";
	}
	
	@RequestMapping(value = "edit-gst", method = RequestMethod.POST)
	public String editGstValues(@Valid @ModelAttribute("gstBO") GstBO gstBO, BindingResult bindingResult,
			Model model, HttpServletRequest request, HttpSession session) throws FileNotFoundException {
		LOGGER.entry();
		long loginId=getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();

		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			adminLoginBO.setId(loginId);
			gstBO.setAdminLoginBO(adminLoginBO);
		} 
		boolean status =gstService.gstValueUpdate(gstBO);
		if (status) {
			model.addAttribute("successMessage",messageSource.getMessage("Gst.Update",null,null));
			return "redirect:/view-gst";
		}
		else {
			model.addAttribute("errorMessage", "Doesnot Exists");
		}
		model.addAttribute("gstBO", gstBO);
		return "edit-gst";
	}
	
	@RequestMapping(value = "/delete-gst", method = RequestMethod.GET)
	public String deleteGstValues(Model model, HttpServletRequest request, HttpSession session,@RequestParam("gstId") Integer gstId)
			throws FileNotFoundException {
		LOGGER.entry();
		GstBO gstBO=new GstBO();
		gstBO.setGstId(gstId);
		try {
			if (null != gstBO) {
				Boolean status =gstService.deleteGstValues(gstBO);
				if (status) {
					model.addAttribute("successMessage", messageSource.getMessage("Gst.Delete",null,null));
					return "redirect:/view-gst";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/view-gsts";
	}	
	
}
