package com.scube.crm.controller;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.security.ControllerUtils;
import com.scube.crm.service.CustomerService;
import com.scube.crm.service.LeadsService;
import com.scube.crm.utils.UserRoles;
import com.scube.crm.vo.LeadsFollowup;

@Controller
@Scope("session")
@SessionAttributes("/report")
public class ReportController extends ControllerUtils implements Serializable {


	private static final long serialVersionUID = -5857977996611066292L;

	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(CampaignController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private LeadsService leadsService;

	@Autowired
	ServletContext servletContext;

	ClientBO clientBO;
	List<ClientBO> profileList;
	List<CampaignBO> pagecampaignlist;
	List<LeadsBO> pageLeadsList;
	List<AdminUserBO> adminEmployeeList;
	List<ClientBO> recordList;
	ClientBO employer = new ClientBO();



	@RequestMapping(value = "/view-report", method = RequestMethod.GET)
	public String viewEmployer(HttpServletRequest request, Model model)
			throws SerialException,MyClientsException, SQLException, NumberFormatException, FileNotFoundException {
		LOGGER.entry();
		ClientBO clientBO = new ClientBO();
		AdminLoginBO adminLoginBO = new AdminLoginBO();
		long loginId=getUserSecurity().getLoginId();
		String userType=getUserSecurity().getRole();
		adminLoginBO.setUserType(userType);
		adminLoginBO.setId(loginId);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		clientBO.setUserId(loginId);
		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_SALES.toString())) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			clientBO.setLoginBO(adminLoginBO);
		}
		if (0 < loginId && !userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			clientBO.setLoginBO(adminLoginBO);
		}
		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			adminLoginBO.setId(loginId);
			adminLoginBO.setUserType(userType);
			clientBO.setLoginBO(adminLoginBO);
			List<AdminUserBO> userBOList = new ArrayList<AdminUserBO>();
			userBOList = customerService.retrieveUser();
			if (null != userBOList && 0 != userBOList.size()) {
				model.addAttribute("userBOList", userBOList);
			}else {
				model.addAttribute("errorMessage","No Records Found");
			}
		}
		clientBO = this.customerService.retriveCustomer(clientBO);
		model.addAttribute("listClients", clientBO.getCustomersList());
		model.addAttribute("updateProfile", new ClientBO());
		model.addAttribute("searchReport", new ClientBO());
		return "view-report";
	}

	@RequestMapping(value="/search-report",method=RequestMethod.POST)
	public String searchReport(@ModelAttribute("searchReport")ClientBO registerBO,
			HttpServletRequest request, Model model) throws MyClientsException, SerialException, SQLException {
		LOGGER.entry();
		long userId =getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole();
		registerBO.setUserType(userType);
		registerBO.setId(userId);
		ClientBO clientBO=new ClientBO(); 
		AdminLoginBO adminLoginBO=new AdminLoginBO();
		adminLoginBO.setId(userId);
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		registerBO.setLoginBO(adminLoginBO);
		clientBO.setAssignedTo(registerBO.getAssignedTo());
		clientBO.setStarDate(registerBO.getStarDate());
		clientBO.setEndDate(registerBO.getEndDate());
		if(null!=registerBO.getProcess() && registerBO.getProcess().equalsIgnoreCase("create") && !registerBO.getProcess().isEmpty()){
			registerBO.setCreatedBy(registerBO.getId());
		}
		List<ClientBO> clientList=new ArrayList<ClientBO>();
		if(null!=registerBO.getProcess() && registerBO.getProcess().equalsIgnoreCase("edit") && !registerBO.getProcess().isEmpty()){
			registerBO.setModifiedBy(registerBO.getId());
			clientList= customerService.searchRetrieveTracking(registerBO);
			model.addAttribute("listClients", clientList);
			return "view-report";
		}
		clientBO.setProcess(registerBO.getProcess());
		registerBO = this.customerService.retriveCustomer(registerBO);
		if (null != registerBO && null!=registerBO.getCustomersList()) {
			model.addAttribute("listClients", registerBO.getCustomersList());
			model.addAttribute("searchReport",clientBO);
		}
		else {
			model.addAttribute("errorMessage","No Records Found");
		}
		List<AdminUserBO> userBOList = customerService.retrieveUser();
		if (null != userBOList && 0 != userBOList.size()) {
			model.addAttribute("userBOList", userBOList);
		}else {
			model.addAttribute("errorMessage","No Records Found");
		}
		return "view-report";
	}

	@RequestMapping(value = "/view-leads-reports", method = RequestMethod.GET)
	public String viewLeads(HttpServletRequest request, Model model)
			throws SerialException,MyClientsException, SQLException, NumberFormatException, FileNotFoundException {
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
			model.addAttribute("searchLeadsReport",new LeadsBO());
			model.addAttribute("leadsBO", leadsBO);
		}
		else {
			model.addAttribute("errorMessage","No Records Found");
		}
		if (0 < loginId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
			List<AdminUserBO> userBOList = customerService.retrieveUser();
			if (null != userBOList && 0 != userBOList.size()) {
				model.addAttribute("userBOList", userBOList);
			}
			}
		return "view-leads-reports";
	}
	
	@RequestMapping(value="/search-leads-report",method=RequestMethod.POST)
	public String searchLeadsReport(@ModelAttribute("searchLeadsReport")LeadsBO leadsBO,
			HttpServletRequest request, Model model,HttpSession session) throws MyClientsException, SerialException, SQLException {
		LOGGER.entry();
		long adminId =getUserSecurity().getLoginId();
		String userType = getUserSecurity().getRole();
		//leadsBO.setUserType(userType);
		leadsBO.setId(adminId);
		LeadsBO clientBO=new LeadsBO(); 
		AdminLoginBO adminLoginBO=new AdminLoginBO();
		adminLoginBO.setId(adminId);
		adminLoginBO.setUserType(userType);
		adminLoginBO.setFirstName(getUserSecurity().getName());
		model.addAttribute("userType", adminLoginBO);
		leadsBO.setAdminLoginBO(adminLoginBO);
		leadsBO.setUserId(adminId);
		//clientBO.setAssignedTo(leadsBO.getAssignedTo());
		clientBO.setStartDate(leadsBO.getStartDate());
		clientBO.setEndDate(leadsBO.getEndDate());
		List<LeadsBO> leadsList=new ArrayList<LeadsBO>();
		if(null!=leadsBO.getProcess() && leadsBO.getProcess().equalsIgnoreCase("create") && !leadsBO.getProcess().isEmpty()){
			leadsBO.setCreatedBy(leadsBO.getId());
		}
		if(null!=leadsBO.getProcess() && leadsBO.getProcess().equalsIgnoreCase("edit") && !leadsBO.getProcess().isEmpty()){
			leadsBO.setModifiedBy(leadsBO.getId());
			leadsList= leadsService.searchRetrieveTracking(leadsBO);
			model.addAttribute("listLeads", leadsList);
			return "view-leads-reports";
		}
		clientBO.setProcess(leadsBO.getProcess());
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
		if (0 < adminId && userType.equalsIgnoreCase(UserRoles.ROLE_ADMIN.toString())) {
		List<AdminUserBO> userBOList = customerService.retrieveUser();
		if (null != userBOList && 0 != userBOList.size()) {
			model.addAttribute("userBOList", userBOList);
		}else {
			model.addAttribute("errorMessage","No Records Found");
		}
		}
		return "view-leads-reports";
	}

}