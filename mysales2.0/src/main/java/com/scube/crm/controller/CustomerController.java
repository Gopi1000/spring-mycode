package com.scube.crm.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
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
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.ContactBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.security.ControllerUtils;
import com.scube.crm.service.CampaignService;
import com.scube.crm.service.CustomerService;
import com.scube.crm.utils.DateHelper;
import com.scube.crm.utils.UserRoles;








@Controller
@Scope("session")
@SessionAttributes("/admin")
public class CustomerController extends ControllerUtils implements Serializable {

	ClientBO clientSearch;
	private static final long serialVersionUID = -5857977996611066292L;
	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CampaignService campaignService;
	@Autowired
	ServletContext servletContext;
	ClientBO clientBO;
	List<ClientBO> profileList;
	List<AdminUserBO> adminEmployeeList;
	List<ClientBO> recordList;




	@RequestMapping(value = "/create-customers", method = RequestMethod.GET)
	public String createEmployer(HttpServletRequest request, Model model) throws
	SerialException, MyClientsException, SQLException, NumberFormatException,
	FileNotFoundException {
		ClientBO clientBO = new ClientBO();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long loginId = getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		if (0 < loginId && !userType.equalsIgnoreCase("ROLE_ADMIN")) {
			adminLoginBO.setId(loginId);
			clientBO.setLoginBO(adminLoginBO);
		}
		if (0 < loginId && !userType.equalsIgnoreCase("ROLE_ADMIN")) {
			List<AdminUserBO> user = new ArrayList<>();
			AdminUserBO userBOList = campaignService.retrieveParticularUser(loginId);
			user.add(userBOList);
			model.addAttribute("userBOList", user);
		}
		if (0 < loginId && userType.equalsIgnoreCase("ROLE_ADMIN")) {
			List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
			userBOList = customerService.retrieveUser();

			if (null != userBOList && 0 != userBOList.size()) {
				model.addAttribute("userBOList", userBOList);
			}
		}
		model.addAttribute("type", "add");
		model.addAttribute("updateProfile", new ClientBO());
		model.addAttribute("createProfile", new ClientBO());
		model.addAttribute("searchjobseeker", new ClientBO());
		return "create-customers";
	}
	@RequestMapping(value = "/view-customers", method = RequestMethod.GET)
	public String viewCustomers(Model model, HttpServletRequest request, HttpSession session)
			throws FileNotFoundException, MyClientsException, SerialException, SQLException {
		LOGGER.entry();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long loginId = getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		clientBO=new ClientBO();
		if(null!=userType && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.getRole())) {
			clientBO.setAssignedTo(loginId);
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			clientBO.setLoginBO(adminLoginBO);
		}
		clientBO=customerService.retriveCustomer(clientBO);			
		if(null!=clientBO) {
			model.addAttribute("listClients", clientBO.getCustomersList());
		}
		else {
			model.addAttribute("errorMessage","No Records Found");
		}
		if(null!=request.getParameter("successMessage")) {
			model.addAttribute("successMessage", request.getParameter("successMessage"));
			model.addAttribute("successMessage", messageSource.getMessage("Customer.Creation",null,null));
		}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}


		model.addAttribute("searchjobseeker", new ClientBO());
		return "view-customers";

	}

	@RequestMapping(value = "/create-customers", method = RequestMethod.POST)
	public String createEmployer(@Valid @ModelAttribute("createProfile") ClientBO profileBO, BindingResult result,
			HttpServletRequest request, Model model) throws MyClientsException {
		try {

			boolean isValidation = false;

			AdminUserBO userBO = new AdminUserBO();
			long userLoginId=getUserSecurity().getLoginId();
			profileBO.setEmployerId(userLoginId);
			profileBO.setCreatedBy(userLoginId);
			profileBO.setModifiedBy(userLoginId);
			// uservalidation
			if (null != profileBO.getUserName()) {
				long adminId = Long.parseLong(profileBO.getUserName());
				profileBO.setAdminId(adminId);
			}
			profileBO.setDelete(false);
			profileBO.setActive(true);
			profileBO.setCreated(DateHelper.beginningOfDay(new Date()));
			profileBO.setModified(DateHelper.beginningOfDay(new Date()));

			// uservalidation
			String userId = profileBO.getName();
			userBO.setName(userId);
			profileBO.setAdminUserBO(userBO);

			if (customerService.findEmployerEmail(profileBO.getEmailAddress())) {
				result.rejectValue("emailAddress", "Validate.EmailAddress");
			}

			// check mobile no

			if (customerService.mobileNoVerification(profileBO.getMobileNo())) {
				result.rejectValue("mobileNo", "Validate.AvailableMobileNo");
			}

			if (result.hasErrors() || isValidation) {
				ClientBO employerRegister = new ClientBO();
				//paginations(employerRegister, request, model);
				model.addAttribute("type", "add");
				model.addAttribute("updateProfile", new ClientBO());
				model.addAttribute("searchjobseeker", employerRegister);
				// model.addAttribute("createProfile", profileBO);
				return "create-customers";
			}
			if(0!=profileBO.getAdminId()) {

				profileBO = customerService.createCustomer(profileBO);
			}
			if (profileBO.getId() > 0) {

				model.addAttribute("successMessage", messageSource.getMessage("Customer.Creation",null,null));
			} else {
				model.addAttribute("errorMessage",messageSource.getMessage("Customer.Creation",null,null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("searchjobseeker", new ClientBO());
		return "redirect:/view-customers";
	}


	
	
	@RequestMapping(value = "/contact-details", method = RequestMethod.GET)
	public String getCreateService(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		ContactBO contactbo = new ContactBO();

		ClientBO clientBO = new ClientBO();

		final String id = request.getParameter("id");
		final String firstName = request.getParameter("firstName");
		long customerId = 0;
		if (null != id) {
			customerId = Long.parseLong(id);

			clientBO.setId(customerId);
			clientBO.setFirstName(firstName);

			contactbo.setClientbo(clientBO);

		}
		model.addAttribute("createProfile", contactbo);

		return "contact-details";

	}
	
	
	
	
	

	@RequestMapping(value = "/contact-details", method = RequestMethod.POST)
	public String postCreateServices(@ModelAttribute("createProfile") ContactBO contactbo, Model model) {

		if (null != contactbo) {

			contactbo = customerService.insertCustomers(contactbo);

		}
		model.addAttribute("successMessage", messageSource.getMessage("Contact.Creation", null, null));

		return "redirect:/view-contact";

	}
	
	
	

	@RequestMapping(value = "/view-contact", method = RequestMethod.GET)
	public String viewcontact(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		List<ContactBO> contactbo1 = new ArrayList<ContactBO>();

		if (null != contactbo1) {
			contactbo1 = customerService.retriveContact(contactbo1);
			
			if(null!=request.getParameter("successMessage")) {
			   model.addAttribute("successMessage", request.getParameter("successMessage"));
			}

			model.addAttribute("contactlist", contactbo1);
		}

		return "view-contact";
	}
	
	
	
	
	
	@RequestMapping(value = "/edit-contact", method = RequestMethod.GET)
	public String editcontact(Model model, HttpServletRequest request, HttpSession session) throws Exception {

		String id = request.getParameter("contactid");
		if (null != id) {
			long editId = Long.parseLong(id);

			ContactBO bo = customerService.getParticularContact(editId);

			if (null != bo) {
				model.addAttribute("updatecontact", bo);

			}
		}
		return "edit-contact";

	}
	
	
	
	
	 @RequestMapping(value="/edit-contact", method = RequestMethod.POST)
     public String changeProductDetails(@ModelAttribute ContactBO contactbo,Model model,HttpServletRequest request) throws IOException, ServletException {
		
		String response=null;
		String id = request.getParameter("contactid");
		
		if (null != id) {
			long editId = Long.parseLong(id);

		if (null != contactbo) {
			contactbo.setContactid(editId);
			
			response = customerService.updateContact(contactbo);
			model.addAttribute("successMessage", messageSource.getMessage("Contact.Update", null, null));
		}
		}
		
		return "redirect:/view-contact";

	}
	
	
	 
	
	@RequestMapping(value = "/delete-contact", method = RequestMethod.GET)
	public String deletecontact(@ModelAttribute ContactBO contactbo, Model model, HttpServletRequest request)
			throws IOException, ServletException {

		String response = null;

		String id = request.getParameter("contactid");
		if (null != id) {
			long deleteId = Long.parseLong(id);

			if (null != contactbo) {

				contactbo.setContactid(deleteId);
				response = customerService.deleteContact(deleteId);
				model.addAttribute("successMessage", messageSource.getMessage("Contact.Delete", null, null));
			}

		}

		return "redirect:/view-contact";
	}
	
  
		
		
		
		
		
		
	

	@RequestMapping(value = "/edit-customers", method = RequestMethod.GET)
	public String editClient(HttpServletRequest request, Model model)
			throws SerialException, MyClientsException, SQLException, NumberFormatException, FileNotFoundException {

		HttpSession session = request.getSession();
		ClientBO registerBO = new ClientBO();
		final String id = request.getParameter("id");
		long employerId = 0;
		if (null != id) {
			employerId = Long.parseLong(id);
			registerBO.setId(employerId);
		}
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		String userType=getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		session.setAttribute("jobprofileId", employerId);
		registerBO = this.customerService.retriveCustomerById(registerBO);

		model.addAttribute("userId", registerBO.getUserId());
		model.addAttribute("userName", registerBO.getUserName());
		model.addAttribute("id", registerBO.getLoginBO().getId());
		model.addAttribute("updateProfile", registerBO);
		model.addAttribute("update", "update");
		ClientBO employer = new ClientBO();
		model.addAttribute("searchjobseeker", employer);
		clientBO = this.customerService.retriveCustomer(clientBO);
		model.addAttribute("listClients", clientBO.getCustomersList());

		List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
		userBOList = customerService.retrieveUser();
		if(null!=userType && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.getRole())) {
			if (null != userBOList && 0 != userBOList.size()) {
				model.addAttribute("userBOList", userBOList);
			}}


		return "edit-customers";
	}

	@RequestMapping(value = "/edit-customers", method = RequestMethod.POST)
	public String jobseekerProfileView(@Valid @ModelAttribute("updateProfile") ClientBO registerBO,
			BindingResult result, HttpServletRequest request, Model model) throws MyClientsException {

		try {
			HttpSession session = request.getSession();

			long userId = 0;
			userId=getUserSecurity().getLoginId();
			registerBO.setEmployerId(userId);

			if (null == registerBO.getMobileNo()) {
				registerBO.setMobileNo(null);
			}

			if (null == request.getParameter("search") && null == request.getParameter("report")) {
				AdminLoginBO loginBO = new AdminLoginBO();
				long id = 0;
				if (null != registerBO && null != registerBO.getUserName()) {
					id = Long.parseLong(registerBO.getUserName());
					loginBO.setId(id);
					id=getUserSecurity().getLoginId();
					registerBO.setModifiedBy(id);

				} else {
					id=getUserSecurity().getLoginId();
					loginBO.setId(id);
					registerBO.setModifiedBy(id);
				}

				registerBO.setLoginBO(loginBO);

				registerBO.setEmployerId(id);
				registerBO = this.customerService.updateEmployer(registerBO);
				if (null != registerBO.getResponse() && !registerBO.getResponse().isEmpty()) {
					model.addAttribute("successMessage", messageSource.getMessage("Customer.Update",null,null));
					registerBO = new ClientBO();
				} else {
					model.addAttribute("infoMessage", "Updated Failed");
				}
			}

			else {
				if (null != registerBO.getFirstName() && !registerBO.getFirstName().isEmpty()) {
					model.addAttribute("firstName", registerBO.getFirstName());
				}

				if (null != registerBO.getEmailAddress() && !registerBO.getEmailAddress().isEmpty()) {
					model.addAttribute("emailAddress", registerBO.getEmailAddress());
				}
				if (null != registerBO.getMobileNo() && !registerBO.getMobileNo().isEmpty()) {
					model.addAttribute("mobileNo", registerBO.getMobileNo());
				}
				if (null != registerBO.getCompanyName() && !registerBO.getCompanyName().isEmpty()) {
					model.addAttribute("companyName", registerBO.getCompanyName());
				}

				if (null != registerBO.getIndustryType() && !registerBO.getIndustryType().isEmpty()) {
					model.addAttribute("industryType", registerBO.getCompanyName());
				}
			}

			if (null != request.getParameter("report") || null != request.getParameter("updateReport")) {
				long reportId = 0;
				if (null != registerBO.getUserName() && !registerBO.getUserName().isEmpty()) {
					String userInfo[] = registerBO.getUserName().split("-");
					reportId = Long.parseLong(userInfo[1]);
					registerBO.setAdminId(reportId);
				}
				if (null != registerBO.getProcess() && registerBO.getProcess().equalsIgnoreCase("create")
						&& !registerBO.getProcess().isEmpty()) {
					registerBO.setCreatedBy(reportId);
				}
				if (null != registerBO.getProcess() && registerBO.getProcess().equalsIgnoreCase("edit")
						&& !registerBO.getProcess().isEmpty()) {
					registerBO.setModifiedBy(reportId);
				}
				session.setAttribute("reportSearchBO", registerBO);

				List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
				userBOList = customerService.retrieveUser();
				List<String> userList = new ArrayList<String>();
				if (null != userBOList && 0 != userBOList.size()) {
					for (AdminUserBO userlist : userBOList) {
						userList.add(userlist.getName() + "-" + userlist.getId());
					}

					model.addAttribute("userBOList", userList);
				}

				model.addAttribute("reportSearch", "report");
				model.addAttribute("updateProfile", registerBO);

			} else {
				model.addAttribute("updateProfile", new ClientBO());
			}

			//long totalJobseekerProfileCount = customerService.employerCount(registerBO);
			ClientBO profile = new ClientBO();
			model.addAttribute("search", profile);
			int page = 1;
			int maxRecord = Integer.parseInt(messageSource.getMessage("record.page.limit",null,null));

			if (null != request.getParameter("paging")) {
				String paging = request.getParameter("paging");
				page = Integer.valueOf(paging);
			}
			registerBO.setMaxRecord(maxRecord);
			registerBO.setPagination("pagination");
			if (null == registerBO.getMobileNo()) {
				registerBO.setMobileNo(null);
			}
			if (registerBO.getAdminId() == 0) {
				registerBO.setAdminId(userId);

			}
			//registerBO = this.customerService.retriveCustomer(registerBO);
			if (null != registerBO.getCustomersList() && 0 != registerBO.getCustomersList().size()) {
				profileList = registerBO.getCustomersList();

				//model.addAttribute("searchJobseeker", PaginationClass.paginationLimitedRecords(page, profileList,	maxRecord, totalJobseekerProfileCount));
			} else {
				model.addAttribute("infoMessage", "No Record Found");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		// model.addAttribute("updateProfile", new ClientBO());
		model.addAttribute("searchjobseeker", new ClientBO());
		return "redirect:/view-customers";
	}


	@RequestMapping(value = "/delete-customers", method = RequestMethod.GET)
	public String jobseekerDeleteProfile(Model model, HttpServletRequest request)
			throws MyClientsException, FileNotFoundException, NumberFormatException, SerialException, SQLException {

		CustomerController.LOGGER.entry();
		// String id = null;
		long deletedId = 0;
		// String id=request.getParameter("id");
		if (null != request.getParameter("id")) {
			String id = request.getParameter("id");
			deletedId = Long.parseLong(id);
		}

		ClientBO deleteProfile = new ClientBO();
		deleteProfile.setId(deletedId);
		deleteProfile.setDelete(true);
		deleteProfile = customerService.deleteProfile(deleteProfile);
		if(null!=deleteProfile.getResponse()) {
			if(null!=request.getParameter("successMessage")) {
				model.addAttribute("successMessage", request.getParameter("successMessage"));
				model.addAttribute("successMessage", messageSource.getMessage("Customer.Delete",null,null));
			}
			if(null!=request.getParameter("errorMessage")) {
				model.addAttribute("errorMessage", request.getParameter("errorMessage"));
			}

		}
		return "redirect:/view-customers";
	}

	@RequestMapping(value = "/search-client", method = RequestMethod.POST)
	public String searchClient(@Valid @ModelAttribute("searchjobseeker") ClientBO registerBO, BindingResult result,
			HttpServletRequest request, Model model) throws MyClientsException, SerialException, SQLException {
		LOGGER.entry();
		model.addAttribute("searchjobseeker",registerBO);
		//ClientBO clientBO=new ClientBO();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long userId =getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(userId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		/*
		 * clientBO.setCompanyName(registerBO.getCompanyName());
		 * clientBO.setEmailAddress(registerBO.getEmailAddress());
		 * clientBO.setMobileNo(registerBO.getMobileNo());
		 */
		clientSearch = this.customerService.retriveCustomer(registerBO);
		if (null != clientSearch && null != clientSearch.getCustomersList()) {
			model.addAttribute("listClients", clientSearch.getCustomersList());
			//model.addAttribute("searchjobseeker",clientBO);

		}
		return "view-customers";
	}




	@RequestMapping(value = { "/customer-tracking-status" }, method = RequestMethod.POST)
	public  String registerBO(@RequestParam String description, String date,String nextAppointmentDate,long id, Model model,
			HttpServletRequest request) {	

		ClientBO BO = new ClientBO();

		long loginId = getUserSecurity().getLoginId();
		String status = "Inprocess";
		BO.setId(id);
		BO.setDescription(description);
		BO.setDate(date);
		BO.setNextAppointmentDate(nextAppointmentDate);
		BO.setAdminId(loginId);
		BO.setStatus(status);
		BO = customerService.saveTracking(BO);		
		return "redirect:/customer-tracking-status?id="+id;
	}


	@RequestMapping(value = "/customer-tracking-status", method = RequestMethod.GET)
	public String viewEmployerStatus(Model model, HttpServletRequest request)
			throws MyClientsException, SerialException, SQLException {
		try {
   
			AdminLoginBO adminLoginBO = new AdminLoginBO();
		    long loginId=getUserSecurity().getLoginId();
			String userType =getUserSecurity().getRole();
			adminLoginBO.setUserType(userType);
			adminLoginBO.setId(loginId);
			adminLoginBO.setFirstName(getUserSecurity().getName());
			model.addAttribute("userType", adminLoginBO);
			long customerId = 0;
			long userId = 0;
			List<ClientBO> registerBOList = new ArrayList<ClientBO>();
			ClientBO BO = new ClientBO();
			//ContactBO co=new ContactBO();

			if (null != getUserSecurity().getLoginId()) {
				userId = (long) getUserSecurity().getLoginId();
				BO.setEmployerId(userId);
			}
			ClientBO registerBO = new ClientBO();
			//ContactBO ao=new ContactBO();
			
			if (null != request.getParameter("reports")) {
				model.addAttribute("reports", request.getParameter("reports"));
			}

			if (null == BO.getMobileNo()) {
				BO.setMobileNo(null);
			}

			if (null != request.getParameter("id")) {
				final String id = request.getParameter("id");
				customerId = Long.parseLong(id);
				registerBO.setId(customerId);
				BO.setId(customerId);
			} 

			BO = customerService.retriveCustomer(BO);

			if (null != BO.getCustomersList() && 0 != BO.getCustomersList().size()) {
				registerBOList = BO.getCustomersList();
				registerBO=customerService.retriveCustomerById(registerBO);
				model.addAttribute("viewemployer",registerBO);
				model.addAttribute("clientBOList", BO.getCustomerUpdateVOList());
				
				//model.addAttribute("clientBOList", co.getCustomerUpdateVOList());
				//model.addAttribute("viewemployer",ao);

			}

		} catch (MyClientsException e) {
			e.printStackTrace();
		}
		return "customer-tracking-status";
	}
	
	/*@RequestMapping(value = "/customer-tracking-status", method = RequestMethod.GET)
	public String viewEmployer(Model model, HttpServletRequest request)
			throws MyClientsException, SerialException, SQLException {

		ContactBO contactBO = new ContactBO();
		long loginId = getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(loginId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		if (0 < loginId && userType.equalsIgnoreCase("ROLE_ADMIN")) {
			adminLoginBO.setId(loginId);

		}

		model.addAttribute("createProfile", contactBO);
		return "contact-details";

	}*/



}