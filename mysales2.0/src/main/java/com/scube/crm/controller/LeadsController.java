package com.scube.crm.controller;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.security.ControllerUtils;
import com.scube.crm.service.CampaignService;
import com.scube.crm.service.CustomerService;
import com.scube.crm.service.LeadsService;
import com.scube.crm.utils.UserRoles;
import com.scube.crm.vo.Customer;
import com.scube.crm.vo.User;
import com.scube.crm.exception.MyClientsException;


@Controller
@Scope("session")
@SessionAttributes("/admin")
public class LeadsController extends ControllerUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5857977996611066292L;

	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(LeadsController.class);

	@Autowired
	private LeadsService leadsService;

	@Autowired
	CustomerService customerService;

	@Autowired
	CampaignService campaignService;

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


	@RequestMapping(value = "create-leads", method = RequestMethod.GET)
	public String createLeads(Model model, HttpServletRequest request)
			throws FileNotFoundException, MyClientsException {
		LOGGER.entry();
		LeadsBO leadsBO = new LeadsBO();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long loginId=getUserSecurity().getLoginId();		
		String userType = getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		if (0 < loginId && userType.equalsIgnoreCase("ROLE_ADMIN")) {
			adminLoginBO.setId(loginId);
			leadsBO.setAdminLoginBO(adminLoginBO);
			List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
			userBOList = customerService.retrieveUser();
			if (null != userBOList && 0 != userBOList.size()) {
				model.addAttribute("userBOList", userBOList);
			}
		}
		if (0 < loginId && !userType.equalsIgnoreCase("ROLE_ADMIN")) {
			adminLoginBO.setId(loginId);
			leadsBO.setAdminLoginBO(adminLoginBO);
		}
		if (0 < loginId &&!userType.equalsIgnoreCase("ROLE_ADMIN")) {
			List<AdminUserBO> user=new ArrayList<>();
			AdminUserBO userBOList = campaignService.retrieveParticularUser(loginId);
			user.add(userBOList);
			model.addAttribute("userBOList", user);}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}else {
			model.addAttribute("leadsBO", leadsBO);
		}
		// Drop Down
		CampaignBO campaignBO = new CampaignBO();
		if(0 < loginId &&!userType.equalsIgnoreCase("ROLE_ADMIN")) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			campaignBO.setAdminLoginBO(adminLoginBO);
		List<CampaignBO> pagecampaignlist = leadsService.listOfCampaign(campaignBO);
		if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
			model.addAttribute("listcampaign", pagecampaignlist);
		}}
		if(0 < loginId &&userType.equalsIgnoreCase("ROLE_ADMIN")) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			campaignBO.setAdminLoginBO(adminLoginBO);
			List<CampaignBO> pagecampaignlist = leadsService.listOfCampaign(campaignBO);
			if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
				model.addAttribute("listcampaign", pagecampaignlist);
			}}
		/*
		 * List<AdminUserBO> userBOList = leadsService.retrieveUser(); if (null !=
		 * userBOList && 0 != userBOList.size()) { model.addAttribute("userBOList",
		 * userBOList); }
		 */
		return "create-leads";

	}

	@RequestMapping(value = "create-leads", method = RequestMethod.POST)
	public String createLeads(@Valid @ModelAttribute("leadsBO") LeadsBO leadsBO, BindingResult result,
			Model model, HttpServletRequest request) throws FileNotFoundException {

		LOGGER.entry();
		CampaignBO campaignBO = new CampaignBO();
		long loginId=getUserSecurity().getLoginId();		
		String userType = getUserSecurity().getRole();
		if (0 < loginId && userType.equalsIgnoreCase("ROLE_ADMIN")) {
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			adminLoginBO.setId(loginId);
			leadsBO.setAdminLoginBO(adminLoginBO);
		}
		if (0 < loginId && !userType.equalsIgnoreCase("ROLE_ADMIN")) {
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			adminLoginBO.setId(loginId);
			leadsBO.setAdminLoginBO(adminLoginBO);
		}
		if (null!=leadsBO.getEmailAddress() && !leadsBO.getEmailAddress().isEmpty()  && leadsService.findByEmailLeads(leadsBO.getEmailAddress())) {
			List<CampaignBO> pagecampaignlist = leadsService.listOfCampaign(campaignBO);
			if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
				model.addAttribute("listcampaign", pagecampaignlist);
			}
			model.addAttribute("user", leadsBO);
			model.addAttribute("errorMessage", messageSource.getMessage("error.email",null,null));
			return "create-leads";
		} 

		if (null!=leadsBO.getMobileNo() && !leadsBO.getMobileNo().isEmpty() && leadsService.findByMobilenoLeads(leadsBO.getMobileNo())) {
			model.addAttribute("errorMessage", messageSource.getMessage("error.mobile",null,null));
			List<CampaignBO> pagecampaignlist = leadsService.listOfCampaign(campaignBO);
			if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
				model.addAttribute("listcampaign", pagecampaignlist);
			}
			return "create-leads";
		}
		else{
			long leadsID = leadsService.saveLeads(leadsBO);
			if (0 != leadsID) {
				model.addAttribute("successMessage",messageSource.getMessage("Lead.Creation",null,null));
				return "redirect:/view-leads";
			}
		}
		return "create-leads";
	}

	@RequestMapping(value = "view-leads", method = RequestMethod.GET)
	public String viewLeads(Model model, HttpServletRequest request, HttpSession session) throws FileNotFoundException, MyClientsException {
		LOGGER.entry();
		LeadsBO leadsBO = new LeadsBO();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		List<LeadsBO> pageLeads=new ArrayList<LeadsBO>();
		long loginId=getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		leadsBO.setUserId(loginId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			leadsBO.setAdminLoginBO(adminLoginBO);
			pageLeads = leadsService.getListLeads(leadsBO);
		}
		if (0 < loginId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			leadsBO.setAdminLoginBO(adminLoginBO);
			pageLeads = leadsService.getListLeads(leadsBO);
		}
		if (null != pageLeads) {
			model.addAttribute("listLeads", pageLeads);
			model.addAttribute("searchLeads",new LeadsBO());
		}
		if(null!=request.getParameter("successMessage")) {
			model.addAttribute("successMessage", request.getParameter("successMessage"));
		}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}	

		model.addAttribute("leadsBO", leadsBO);
		//campaign search dd
		CampaignBO campaignBO = new CampaignBO();
		if(0 < loginId &&!userType.equalsIgnoreCase("ROLE_ADMIN")) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			campaignBO.setAdminLoginBO(adminLoginBO);
		List<CampaignBO> pagecampaignlist = leadsService.listOfCampaign(campaignBO);
		if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
			model.addAttribute("listcampaign", pagecampaignlist);
		}}
		if(0 < loginId &&userType.equalsIgnoreCase("ROLE_ADMIN")) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			campaignBO.setAdminLoginBO(adminLoginBO);
			List<CampaignBO> pagecampaignlist = leadsService.listOfCampaign(campaignBO);
			if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
				model.addAttribute("listcampaign", pagecampaignlist);
			}}
		
		return "view-leads";

	}

	@RequestMapping(value = "/convert-customer", method = RequestMethod.GET)
	public String convertCustomer(Model model, HttpServletRequest request,@RequestParam("id") Integer leadsId)
			throws FileNotFoundException, MyClientsException {
		LOGGER.entry();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long userId =getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(userId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);

		LeadsBO leadsBO = null;
		leadsBO = leadsService.getLeads(leadsId);
		if(null!=leadsBO) {
			Customer customer=new Customer();
			customer.setFirstName(leadsBO.getFirstName());
			customer.setLastName(leadsBO.getLastName());
			customer.setCompanyName(leadsBO.getCompanyName());
			customer.setContactNumber(leadsBO.getContactNo());
			customer.setMobileNumber(leadsBO.getMobileNo());
			customer.setEmailAddress(leadsBO.getEmailAddress());
			customer.setAddress(leadsBO.getAddress());
			customer.setWebSite(leadsBO.getWebsite());
			customer.setIndustryType(leadsBO.getIndustryType());
			customer.setAssigned(getUserSecurity().getLoginId());
			customer.setCreated(new Date());
			customer.setCreatedBy(getUserSecurity().getLoginId());
			customer.setModified(new Date());
			customer.setModifiedBy(getUserSecurity().getLoginId());
			customer.setIsActive(true);
			customer.setIsDelete(false);
			customer.setStatus("opened");
			if(null!=leadsBO.getAdminLoginBO()&&0!=leadsBO.getAdminLoginBO().getId()) {
				User user=new User();
				user.setId(leadsBO.getAdminLoginBO().getId());
				customer.setLoginVO(user);
			}
			customerService.createCustomer(customer);
			leadsBO.setConvertedCustomer(true);
			leadsBO.setIsActive(false);
			leadsBO.setDelete(true);
			leadsService.updateLeads(leadsBO);
		}
		if (null != leadsBO) {
			model.addAttribute("leadsBO", leadsBO);
			model.addAttribute("edit", "leadsEdit");
		}
		return "redirect:/view-leads";

	}




	@RequestMapping(value = "edit-leads", method = RequestMethod.GET)
	public String editLeads(Model model, HttpServletRequest request,@RequestParam("leadsId") Integer leadsId)
			throws FileNotFoundException, MyClientsException {
		LOGGER.entry();
		LeadsBO leadsBO = new LeadsBO();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long loginId=getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		
		if (0 < loginId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
		leadsBO = leadsService.getLeads(leadsId);
		}
		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			List<AdminUserBO> userBOList = customerService.retrieveUser();
						leadsBO = leadsService.getLeads(leadsId);
						model.addAttribute("userBOList", userBOList);
						if(0!=leadsBO.getAdminLoginBO().getId()&&null!=leadsBO.getAdminLoginBO().getName()) {
						model.addAttribute("usrId", leadsBO.getAdminLoginBO().getId());
						model.addAttribute("userName", leadsBO.getAdminLoginBO().getName());
		}}
		CampaignBO campaignBO = new CampaignBO();
		List<CampaignBO> pagecampaignlist = new ArrayList<CampaignBO>();
		if (0 < loginId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			campaignBO.setAdminLoginBO(adminLoginBO);
		pagecampaignlist = leadsService.listOfCampaign(campaignBO);
		}
		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			campaignBO.setAdminLoginBO(adminLoginBO);
			pagecampaignlist = leadsService.listOfCampaign(campaignBO);
			}
		if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
			model.addAttribute("listcampaign", pagecampaignlist);			
		}

		if (null != leadsBO) {
			model.addAttribute("leadsBO", leadsBO);
			if(null!=leadsBO.getAdminLoginBO().getName()) {
				model.addAttribute("userName",leadsBO.getAdminLoginBO().getName());
			}
			if(null!=leadsBO.getCampaignBO() 
					&& null!=leadsBO.getCampaignBO().getCampaignName()) {
			model.addAttribute("campaignName",leadsBO.getCampaignBO().getCampaignName());
			model.addAttribute("edit", "leadsEdit");
		}
		}
		return "edit-leads";

	}

	@RequestMapping(value = "edit-leads", method = RequestMethod.POST)
	public String editLeads(@Valid @ModelAttribute("leadsBO") LeadsBO leadsBO, BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpSession session) throws MyClientsException, FileNotFoundException {
		LOGGER.entry();
		long loginId= getUserSecurity().getLoginId();		
		String userType = getUserSecurity().getRole();

		if (0 < loginId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			adminLoginBO.setId(loginId);
			leadsBO.setAdminLoginBO(adminLoginBO);

		} else {
			if (null != leadsBO && null != leadsBO.getAdminLoginBO()
					&& null != leadsBO.getAdminLoginBO().getFirstName()) {
				String adminId = leadsBO.getAdminLoginBO().getFirstName();
				long userId = 0;
				if (null != adminId) {
					userId = Long.parseLong(adminId);
				}

				AdminLoginBO adminLoginBO = new AdminLoginBO();
				adminLoginBO.setId(userId);
				leadsBO.setAdminLoginBO(adminLoginBO);
			}
		}
		boolean status = leadsService.updateLeads(leadsBO);
		if (status) {
			model.addAttribute("successMessage",messageSource.getMessage("Lead.Update",null,null));
			return "redirect:/view-leads";
		}
		else {
			model.addAttribute("errorMessage", "Doesnot Exists");
		}

		model.addAttribute("leadsBO", leadsBO);
		return "edit-leads";
	}

	@RequestMapping(value = "/delete-leads", method = RequestMethod.GET)
	public String deleteLeads(Model model, HttpServletRequest request, HttpSession session,@RequestParam("leadsId") Integer leadsId)
			throws FileNotFoundException {
		LOGGER.entry();
		LeadsBO leadsBO;
		try {
			leadsBO = leadsService.getLeads(leadsId);

			if (null != leadsBO) {
				boolean status = leadsService.deleteLeads(leadsId);
				if (status) {
					model.addAttribute("successMessage", messageSource.getMessage("Lead.Delete",null,null));
					return "redirect:/view-leads";
				}

			}
		} catch (MyClientsException e) {

			e.printStackTrace();
		}
		return "redirect:/view-leads";
	}		


	@RequestMapping(value = "/search-leads", method = RequestMethod.POST)
	public String searchLeads( @ModelAttribute("searchLeads") LeadsBO leadsBO,
			HttpServletRequest request,HttpSession session, Model model) throws MyClientsException, SerialException, SQLException {
		LOGGER.entry();
		try {
			model.addAttribute("searchLeads",leadsBO);
		long adminId=getUserSecurity().getLoginId();	
		String userType = getUserSecurity().getRole();
		leadsBO.setUserId(adminId); 
		CampaignBO campaignBO = new CampaignBO();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		adminLoginBO.setId(adminId);
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		campaignBO.setAdminLoginBO(adminLoginBO);
		leadsBO.setAdminLoginBO(adminLoginBO);
		CampaignBO campaignBo = new CampaignBO();
		campaignBo.setCampaignName(leadsBO.getCampaignBO().getCampaignName());
		if(null!=leadsBO.getCampaignBO()&& null!=leadsBO.getCampaignBO().getCampaignName()
				&& !leadsBO.getCampaignBO().getCampaignName().isEmpty()) {
		Integer campaignId=Integer.parseInt(leadsBO.getCampaignBO().getCampaignName());
		campaignBo.setCampaignId(campaignId);
		leadsBO.setCampaignBO(campaignBo);
		
		}
		List<LeadsBO> leadsList=new ArrayList<LeadsBO>();
		if (0 < adminId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			leadsList = leadsService.getListLeads(leadsBO);
		}
		if (0 < adminId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			leadsList = leadsService.getListLeads(leadsBO);
		}
		
		if (null != leadsList && 0 < leadsList.size()) {
			model.addAttribute("listLeads", leadsList);
		}else {
			model.addAttribute("errorMessage","No Records Found");
		}
			if(0 < adminId &&!userType.equalsIgnoreCase("ROLE_ADMIN")) {
						List<CampaignBO> pagecampaignlist = leadsService.listOfCampaign(campaignBO);
			if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
				model.addAttribute("listcampaign", pagecampaignlist);
			}}
			if(0 < adminId &&userType.equalsIgnoreCase("ROLE_ADMIN")) {
				List<CampaignBO> pagecampaignlist = leadsService.listOfCampaign(campaignBO);
				if (null != pagecampaignlist && 0 < pagecampaignlist.size()) {
					model.addAttribute("listcampaign", pagecampaignlist);
				}}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view-leads";
	}	

	@RequestMapping(value = "/leads-tracking-status", method = RequestMethod.GET)
	public String viewEmployerStatus(Model model, HttpServletRequest request)
			throws MyClientsException, SerialException, SQLException {
		try {
			int leadsId = 0;
			LeadsBO leadsBO =null;
			AdminLoginBO adminLoginBO = new AdminLoginBO();
			long userId =getUserSecurity().getLoginId();
			String userType = getUserSecurity().getRole();
			adminLoginBO.setUserType(userType);
			adminLoginBO.setId(userId);
			adminLoginBO.setFirstName(getUserSecurity().getName());
			model.addAttribute("userType", adminLoginBO);

			if (null != request.getParameter("reports")) {
				model.addAttribute("reports", request.getParameter("reports"));
			}

			if (null != request.getParameter("leadsId")) {
				final String id = request.getParameter("leadsId");
				leadsId = Integer.valueOf(id);
			} 

			leadsBO = leadsService.getLeads(leadsId);
			if (null != leadsBO) {
				//leadsBOList = leadsBO.getLeadsList();
				model.addAttribute("viewleads", leadsBO);
				//model.addAttribute("leadsBOList", leadsBO.getLeadsUpdateVOList());
				model.addAttribute("leadsBOList", leadsBO.getLeadsUpdateVOList());
			}else {
				model.addAttribute("viewleads",new LeadsBO());
				model.addAttribute("leadsBOList", null);
			}

		} catch (MyClientsException e) {
			e.printStackTrace();
		}
		return "leads-tracking-status";
	}

	@RequestMapping(value = { "/leads-tracking-status" }, method = RequestMethod.POST)
	public  String registerBO(@RequestParam String description, String date, String nextAppointmentDate,long leadsId, Model model,
			HttpServletRequest request) {	
		LeadsBO BO = new LeadsBO();
		long loginId = getUserSecurity().getLoginId();
		String status = "Inprocess";
		BO.setLeadsId(leadsId);
		BO.setDescription(description);
		BO.setDate(date);
		BO.setNextAppointmentDate(nextAppointmentDate);
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		adminLoginBO.setId(loginId);
		BO.setAdminLoginBO(adminLoginBO);
		BO.setStatus(status);
		BO = leadsService.saveTracking(BO);		
		return "redirect:/leads-tracking-status?leadsId="+leadsId;
	}
}