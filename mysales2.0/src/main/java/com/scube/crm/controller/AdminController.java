package com.scube.crm.controller;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.ContactBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.bo.RoleBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.security.ControllerUtils;
import com.scube.crm.service.AdminService;
import com.scube.crm.utils.UserRoles;
import com.scube.crm.vo.LoginStatusVO;

@Controller
@Scope("session")
@SessionAttributes("/admin")
public class AdminController extends ControllerUtils implements Serializable {

	private static final long serialVersionUID = -5857977996611066292L;

	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	ServletContext servletContext;

	ClientBO reteriveprofile;
	List<ClientBO> profileList;
	List<CampaignBO> pagecampaignlist;
	List<LeadsBO> pageLeadsList;
	List<AdminUserBO> adminEmployeeList;
	List<ClientBO> recordList;
	ClientBO employer = new ClientBO();


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) throws FileNotFoundException {
		return "redirect:admin-sign-in";
	}

	@RequestMapping(value = "/admin-sign-in" , method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) throws FileNotFoundException {
		model.addAttribute("adminLogin", new AdminLoginBO());
		return "admin-sign-in";
	}

	/*
	 * @RequestMapping(value = "/admin-sign-in", method = RequestMethod.POST) public
	 * String login(@Valid @ModelAttribute("adminLogin") AdminLoginBO adminLoginBO,
	 * BindingResult result, HttpServletRequest request, Model model) throws
	 * MyClientsException, FileNotFoundException { AdminController.LOGGER.entry();
	 * String pwLength = adminLoginBO.getPassword(); try { adminLoginBO =
	 * this.adminService.authendicate(adminLoginBO);
	 * 
	 * if (!adminLoginBO.isActive()) { if (0 != pwLength.length()) { if
	 * (pwLength.length() >= 4) { model.addAttribute("errormessage",
	 * messageSource.getMessage("Validate.AccountInvalid",null,null)); return
	 * "admin-sign-in"; } } } } catch (final MyClientsException jb) { if
	 * (LOGGER.isDebugEnabled()) { LOGGER.debug("Admin sign in failed:" +
	 * jb.getErrorCode() + jb); } LOGGER.info("Admin sign in failed:" +
	 * jb.getErrorCode() + jb); } AdminController.LOGGER.exit(); return
	 * "redirect:/admin-home.html";
	 * 
	 * }
	 */
	@RequestMapping(value = "/send-mail", method = RequestMethod.GET)
	public String sendMail(Model model, HttpServletRequest request) {
		LOGGER.entry();
		long userLoginId = 0;
		boolean mailStatus = false;
		userLoginId = getUserSecurity().getLoginId();

		if (null != request.getParameter("email")) {
			String jobseekerName = request.getParameter("name");
			String emailAddress = request.getParameter("email");
			String sendId = request.getParameter("id");
			ClientBO employerRegisterBO = new ClientBO();
			employerRegisterBO.setEmailAddress(emailAddress);
			employerRegisterBO.setFirstName(jobseekerName);
			employerRegisterBO.setEmployerId(userLoginId);
			employerRegisterBO.setId(Long.parseLong(sendId));
			mailStatus = adminService.sendClientMail(employerRegisterBO);
			if (mailStatus) {
				model.addAttribute("successmessage", "Mail has been sent Successfully");
			}
		}
		return "redirect:/profile-view";
	}

	@RequestMapping(value = "/create-employees", method = RequestMethod.GET)
	public String usercreate(Model model, HttpServletRequest request) throws MyClientsException, MalformedURLException,
	NumberFormatException, FileNotFoundException, SerialException, SQLException {

		long loginId = getUserSecurity().getLoginId();
		if (0 == loginId) {
			return "redirect:/admin-sign-in";
		}
		if (!getUserSecurity().getRole().equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			return "redirect:/admin-sign-in";
		}
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		String userType = getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		AdminUserBO bo = new AdminUserBO();
		int page = 1;
		final String paging = request.getParameter("page");
		if (null != paging) {
			page = Integer.parseInt(paging);
		}
		/*
		 * userBOList = adminService.retrieveUser(); if (null != userBOList && 0 !=
		 * userBOList.size()) { model.addAttribute("userBOList", userBOList); }
		 */
		if (null != request.getParameter("successMessage")) {
			model.addAttribute("successMessage", request.getParameter("successMessage"));
		}
		if (null != request.getParameter("infoMessage")) {
			model.addAttribute("infoMessage", request.getParameter("infoMessage"));
		}
		if (null != request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}
		if (null != request.getParameter("functionType") && !request.getParameter("functionType").isEmpty()) {
			model.addAttribute("functionType", request.getParameter("functionType"));
		} else {
			model.addAttribute("functionType", "add");
		}

		model.addAttribute("user", bo);
		return "create-employees";

	}

	@RequestMapping(value = "/create-employees", method = RequestMethod.POST)
	public String usercreate(@Valid @ModelAttribute("user") AdminUserBO adminBO, BindingResult result,
			HttpServletRequest request, Model model) throws MyClientsException {
		long userId = 0;
		try {
			long loginId = getUserSecurity().getLoginId();
			// String userType=getUserSecurity().getRole();
			if (0 == loginId) {
				return "redirect:/admin-sign-in";
			}
			if (!getUserSecurity().getRole().equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
				return "redirect:/admin-sign-in";
			}

			model.addAttribute("functionType", "add");
			if (result.hasErrors()) {
				return "create-employees";
			}

			if (!adminBO.getPassword().equals(adminBO.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				if (0 != userId) {
					model.addAttribute("infoMessage", "password-confirmpassword Verify");
				}
			}
			if (adminService.findByEmail(adminBO.getEmailAddress())) {
				model.addAttribute("user", adminBO);
				model.addAttribute("errorMessage", "This account already exist");
			} else {
				adminBO = adminService.createuser(adminBO);
				if (0 != adminBO.getId()) {
					model.addAttribute("successMessage", messageSource.getMessage("Create-Employee", null, null));
				} else {
					model.addAttribute("errorMessage", adminBO.getErrorMessage());
					adminBO.setErrorMessage(null);
				}
			}
			if (adminService.findByMobilenoEmployee(adminBO.getMobileNo())) {
				result.rejectValue("errorMessage", "Validate.AvailableMobileNo");
			}
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add user has failed:" + ex.getMessage());
			}
			LOGGER.info("Add user has failed:" + ex.getMessage());
		}
		model.addAttribute("user", adminBO);
		model.addAttribute("functionType", "add");
		return "redirect:/view-employees";
	}

	@RequestMapping(value = "view-employees", method = RequestMethod.GET)
	public String viewEmployees(Model model, HttpServletRequest request, HttpSession session)
			throws FileNotFoundException, MyClientsException {
		long loginId = getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();
		if (0 == loginId) {
			return "redirect:/admin-sign-in";
		}
		final String paging = request.getParameter("page");
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		if (null != paging) {
		}
		List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
		userBOList = adminService.retrieveUser();
		if (null != userBOList && 0 < userBOList.size()) {
			model.addAttribute("userBOList", userBOList);
			/*
			 * model.addAttribute("successMessage",
			 * MyjobkartResourceBundle.getValue("Create-Employee"));
			 */
			model.addAttribute("successMessage", messageSource.getMessage("Create-Employee", null, null));
		}
		AdminUserBO adminUserBO = new AdminUserBO();
		model.addAttribute("user", adminUserBO);
		model.addAttribute("searchEmployees", new AdminUserBO());
		return "view-employees";
	}

	@RequestMapping(value = "/search-employees", method = RequestMethod.POST)
	public String searchEmployees(@Valid @ModelAttribute("searchEmployees") AdminUserBO adminBO, BindingResult result,
			HttpServletRequest request, Model model, HttpSession session)
					throws MyClientsException, SerialException, SQLException {

		long loginId =getUserSecurity().getLoginId();
		if (0 == loginId) {
			return "redirect:/admin-sign-in";
		}
		AdminUserBO adminBo=new AdminUserBO();
		adminBo.setName(adminBO.getName());
		adminBo.setEmailAddress(adminBO.getEmailAddress());
		adminBo.setUserType(adminBO.getUserType());
		adminBO.setUserId(loginId);
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		String userType = getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		adminEmployeeList = adminService.searchEmployeeList(adminBO);
		if (null != adminEmployeeList && 0 < adminEmployeeList.size()) {
			model.addAttribute("userBOList", adminEmployeeList);
			model.addAttribute("searchEmployees", adminBo);
			return "view-employees";
		} else {
			model.addAttribute("errorMessage", "No Records Found");
			return "view-employees";
		}

	}

	@RequestMapping(value = "/active-deactive-user", method = RequestMethod.GET)
	public String activedeactiveuser(Model model, HttpServletRequest request)
			throws MyClientsException, FileNotFoundException {
		try {
			long loginId = getUserSecurity().getLoginId();
			// String userType=getUserSecurity().getRole();
			if (0 == loginId) {
				return "redirect:/admin-sign-in";
			}
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			String userType = getUserSecurity().getRole();
			adminLoginBO.setUserType(userType);
			adminLoginBO.setFirstName(getUserSecurity().getName());
			model.addAttribute("userType", adminLoginBO);
			String status = request.getParameter("status");
			String statusvalue[] = status.split(",");
			String userstatus = statusvalue[0];
			long userId = Long.valueOf(statusvalue[1]);

			AdminUserBO userBO = new AdminUserBO();
			userBO.setId(userId);
			if (userstatus.equals("Active")) {
				userBO.setActive(false);
			} else {
				userBO.setActive(true);
			}
			boolean useractivestatus = adminService.userStatus(userBO);
			if (useractivestatus) {
				model.addAttribute("successmessage", "Admin user activated Successfully");
			} else {
				model.addAttribute("successmessage", "Admin user de-activated Successfully");
			}
		} catch (final NullPointerException ne) {
			ne.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("user status has failed:" + ne.getMessage() + ne);
			}
			LOGGER.info("user status has failed:" + ne.getMessage() + ne);
		}
		return "redirect:/view-employees";
	}
	
	
	
	/*@RequestMapping(value = "/create-role", method = RequestMethod.GET)
	public String createrole(Model model, HttpServletRequest request, HttpSession session) throws Exception {
		
		RoleBO rolebo=new RoleBO();
		
		model.addAttribute("createrole", rolebo);
		
		return "create-role";
	}
	
	
	@RequestMapping(value = "/create-role", method = RequestMethod.POST)
	public String postCreateServices(@ModelAttribute("createrole") RoleBO rolebo, Model model) {

		if (null != rolebo) {

			rolebo = adminService.insertrole(rolebo);

		}
		model.addAttribute("successMessage", messageSource.getMessage("Contact.Creation", null, null));

		return "redirect:/view-role";

	}
	
	
	@RequestMapping(value = "/view-role", method = RequestMethod.GET)
	public String viewrole(Model model, HttpServletRequest request, HttpSession session) throws Exception {
		
		List<RoleBO> rolebo=new ArrayList<RoleBO>();
		
		if(null!= rolebo){
			
			rolebo=adminService.retrieverole(rolebo);
			
			model.addAttribute("viewlist", rolebo);
		}
		return "view-role";
	}*/
	
	
	
	

	@RequestMapping(value = "/delete-employees", method = RequestMethod.GET)
	public String deleteUser(Model model, HttpServletRequest request) throws MyClientsException, FileNotFoundException {

		AdminController.LOGGER.entry();
		long loginId = getUserSecurity().getLoginId();
		// String userType=getUserSecurity().getRole();
		if (0 == loginId) {
			return "redirect:/admin-sign-in";
		}
		String id = null;
		long deletedId = 0;
		if (request.getParameter("id") != null) {
			id = request.getParameter("id");
			deletedId = Long.parseLong(id);
		}
		AdminUserBO deleteProfile = new AdminUserBO();
		deleteProfile.setId(deletedId);
		deleteProfile.setDelete(true);
		deleteProfile = adminService.deleteProfile(deleteProfile);
		if (null != deleteProfile.getResponse()) {
			model.addAttribute("successmessages", "deleted succesfully");
		} else {
			model.addAttribute("infoMessagemessage", "deleted failed");
		}
		AdminController.LOGGER.exit();
		model.addAttribute("functionType", "add");
		return "redirect:/view-employees";
	}

	@RequestMapping(value = "/edit-employees", method = RequestMethod.GET)
	public String edituser(Model model, HttpServletRequest request, HttpSession session) throws MyClientsException {
		try {
			long loginId = getUserSecurity().getLoginId();
			if (0 == loginId) {
				return "redirect:/admin-sign-in";
			}
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			String userType = getUserSecurity().getRole();
			adminLoginBO.setUserType(userType);
			adminLoginBO.setFirstName(getUserSecurity().getName());
			model.addAttribute("userType", adminLoginBO);
			final String id = request.getParameter("id");
			final long userId = Long.parseLong(id);
			//session.setAttribute("userId", userId);
			AdminUserBO userBO = new AdminUserBO();
			if (0 < loginId) {
				userBO = adminService.retrieveusers(userId);
			}
			List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
			userBOList = adminService.retrieveUser();
			if (null != userBOList && 0 != userBOList.size()) {
				model.addAttribute("userBOList", userBOList);
			}

			if (userBO.getId() == userId) {
				session.setAttribute("updateUser", reteriveprofile);
				model.addAttribute("functionType", "edit");
				model.addAttribute("editUser", userBO);
				return "edit-employees";
			}
		} catch (final NullPointerException ne) {

		}

		return "edit-employees";
	}

	@RequestMapping(value = "/edit-employees", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("updateuser") AdminUserBO adminBO, BindingResult result,
			HttpServletRequest request, Model model) throws MyClientsException {
		//HttpSession session = request.getSession();
		long loginId = getUserSecurity().getLoginId();
		// String userType=getUserSecurity().getRole();
		if (0 == loginId) {
			return "redirect:/admin-sign-in";
		}

		try {
			long userId = 0;

			if (0 != getUserSecurity().getLoginId()) {
				userId = (Long) getUserSecurity().getLoginId();
				adminBO.setUserId(userId);
			}

			model.addAttribute("functionType", "edit");
			if (result.hasErrors()) {
				return "edit-employees";
			}

			adminBO = adminService.updateuser(adminBO);
			if (0 != adminBO.getId()) {
				model.addAttribute("Successmessage", "Admin user updated Successfully");

			} else {
				model.addAttribute("errorMessages", adminBO.getErrorMessage());
				adminBO.setErrorMessage(null);
			}
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Edit adminuser has failed:" + ex.getMessage());
			}
			LOGGER.info("Edit adminuser has failed:" + ex.getMessage());
		}
		model.addAttribute("functionType", "add");
		return "redirect:/view-employees";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) throws MyClientsException, MalformedURLException {
		final HttpSession session = request.getSession();
		String emailId = (String) session.getAttribute("lstatusemailId");
		LoginStatusVO loginStatusVO = new LoginStatusVO();
		loginStatusVO.setUserName(emailId);
		adminService.editLoginStatus(loginStatusVO);
		session.invalidate();
		return "redirect:/admin-sign-in";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) throws MyClientsException, MalformedURLException {

		return "redirect:/admin-sign-in";
	}

	@RequestMapping(value = "admin-home", method = RequestMethod.GET)
	public String adminHome(Model model, HttpServletRequest request, HttpSession session) throws FileNotFoundException {
		long loginId=0;
		String userTypes=null; 
		if(null!=getUserSecurity()) {
			loginId = getUserSecurity().getLoginId();
		    userTypes = getUserSecurity().getRole();
		}
		if (0 == loginId && null==userTypes) {
			return "redirect:/admin-sign-in";
		}
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long userId =getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(userId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);

		if (null != userType && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.getRole())) {
			adminLoginBO.setEmployeeCount(adminService.employeesCount(adminLoginBO));
			adminLoginBO.setCampaignCount(adminService.campaignCount(adminLoginBO));
			adminLoginBO.setProductCount(adminService.productCount(adminLoginBO));
			adminLoginBO.setCustomerCount(adminService.customerCount(adminLoginBO));
			adminLoginBO.setLeadsCount(adminService.leadsCount(adminLoginBO));
		}

		if (null != userType && userType.equalsIgnoreCase(UserRoles.ROLE_CAMPAIGN.getRole())) {
			adminLoginBO.setCampaignCount(adminService.campaignCount(adminLoginBO));
			adminLoginBO.setLeadsCount(adminService.leadsCount(adminLoginBO));
		}

		if (null != userType && userType.equalsIgnoreCase(UserRoles.ROLE_MARKETING.getRole())) {
			adminLoginBO.setCampaignCount(adminService.campaignCount(adminLoginBO));
			adminLoginBO.setCustomerCount(adminService.customerCount(adminLoginBO));
			adminLoginBO.setLeadsCount(adminService.leadsCount(adminLoginBO));
		}

		if (null != userType && userType.equalsIgnoreCase(UserRoles.ROLE_SALES.getRole())) {
			adminLoginBO.setCampaignCount(adminService.campaignCount(adminLoginBO));
			adminLoginBO.setCustomerCount(adminService.customerCount(adminLoginBO));
			adminLoginBO.setLeadsCount(adminService.leadsCount(adminLoginBO));
			//adminLoginBO.setCurrentActionCustomers(adminService.getCurrentActionCustomers(adminLoginBO));
		}

		// }
		if (null != adminLoginBO) {
			model.addAttribute("adminDashboardCount", adminLoginBO);
		}
		return "admin-home";
	}


}