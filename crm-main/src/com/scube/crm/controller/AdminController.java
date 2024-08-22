package com.scube.crm.controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.scube.crm.bo.AdminBO;
import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.EmployerRegisterBO;
import com.scube.crm.exception.JLogger;
import com.scube.crm.exception.MyJobKartException;
import com.scube.crm.service.AdminService;
import com.scube.crm.service.EmployerService;
import com.scube.crm.utils.CookiesGenerator;
import com.scube.crm.utils.DateHelper;
import com.scube.crm.utils.MyjobkartResourceBundle;
import com.scube.crm.utils.PaginationClass;
import com.scube.crm.vo.LoginStatusVO;
//import com.scube.springcrm.model.ProductBo;

//import com.scube.crm.bo.RestEmployer;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by :
 * sathishkumar.s Description : Admin Controller is a controller this will calls
 * the appropriate services to do the respective operations.. Reviewed by :
 * 
 */

/**
 * @author Administrator
 * 
 */
@Controller
@Scope("session")
@SessionAttributes("/admin")
public class AdminController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5857977996611066292L;

	private static final JLogger LOGGER = JLogger
			.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;


	@Autowired
	private EmployerService employerService;

	@Autowired
	ServletContext servletContext;

	/*List<EmployerBO> employerNotificationList;*/
	EmployerRegisterBO reteriveprofile;
	List<EmployerRegisterBO> profileList;
	List<EmployerRegisterBO> recordList;
	EmployerRegisterBO employer= new EmployerRegisterBO();

	@RequestMapping(value = "/admin-sign-in", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request)
			throws FileNotFoundException {


		model.addAttribute("adminLogin", new AdminLoginBO());
		return "admin-sign-in";
	}

	/**
	 * This method used to perform the login function (admin)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value = "/admin-sign-in", method = RequestMethod.POST)
	public String login(
			@Valid @ModelAttribute("adminLogin") AdminLoginBO adminLoginBO,
			BindingResult result, HttpServletRequest request, Model model,
			HttpServletResponse httpResponse) throws MyJobKartException,
			FileNotFoundException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		String pwLength = adminLoginBO.getPassword();

		try {
			adminLoginBO = this.adminService.authendicate(adminLoginBO);

			if (adminLoginBO.isActive()) {
				session.setAttribute("adminId", adminLoginBO.getId());
				session.setAttribute("userType", adminLoginBO.getUserType());
				session.setAttribute("lstatusemailId",
						adminLoginBO.getEmailAddress());
			} else {

				if (0 != pwLength.length()) {
					if (pwLength.length() >= 4) {
						model.addAttribute("errormessage",
								MyjobkartResourceBundle
								.getValue("Validate.AccountInvalid"));
						return "admin-sign-in";
					}
				}
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin sign in failed:" + jb.getErrorCode() + jb);
			}
			LOGGER.info("Admin sign in failed:" + jb.getErrorCode() + jb);
		}
		AdminController.LOGGER.exit();
		return "redirect:/profile-view.html";

	}

	/*@RequestMapping(value = "/send-mail", method = RequestMethod.GET)
	public String sendMail(Model model, HttpServletRequest request) { 
		LOGGER.entry();
		long userLoginId = 0;
		boolean mailStatus = false;
		HttpSession session = request.getSession();
		if(null != session.getAttribute("adminId")){
			 userLoginId = (long) session.getAttribute("adminId");
		}
		if(null != request.getParameter("email")){
			String jobseekerName = request.getParameter("name");
			String emailAddress = request.getParameter("email");
			String sendId=request.getParameter("id");
			EmployerRegisterBO employerRegisterBO = new EmployerRegisterBO();
			employerRegisterBO.setEmailAddress(emailAddress);
			employerRegisterBO.setFirstName(jobseekerName);
			employerRegisterBO.setEmployerId(userLoginId);
			employerRegisterBO.setId(Long.parseLong(sendId));
			mailStatus = adminService.sendClientMail(employerRegisterBO);
			if(mailStatus){
				model.addAttribute("successmessage", "Mail has been sent Successfully");
			}
		}
		return "redirect:/profile-view.html";
	}*/
	
	/*@RequestMapping(value = "/migration-sendmail", method = RequestMethod.GET)
	public String migrationsendMail(Model model, HttpServletRequest request) {
		LOGGER.entry();
		boolean mailStatus = false;
		try {
		if(null != request.getParameter("id")){
			String id=request.getParameter("id");
			EmployerRegisterBO employerRegisterBO = new EmployerRegisterBO();
			employerRegisterBO.setId(Long.parseLong(id));
				mailStatus=employerService.migrationstatus(employerRegisterBO);
				if(mailStatus){
					model.addAttribute("successmessage", "Mail Send Successfully");
					
				}
			} 
		}catch (final Exception jb) {
			jb.printStackTrace();	
			}
		finally {
			LOGGER.exit();
			}
		return "redirect:/profile-view.html";
	}*/
	
	private EmployerRegisterBO reterivedEmployer(EmployerRegisterBO employerRegister) throws SerialException, MyJobKartException, SQLException{
		employerRegister=this.employerService
				.retriveJobseeker(employerRegister);
		List<EmployerRegisterBO> employerList = new ArrayList<EmployerRegisterBO>();
		if(null!=employerRegister.getJobseekerProfileList()){
			employerList=employerRegister.getJobseekerProfileList();	
		}

		return employerRegister;
	}



	private void paginations(EmployerRegisterBO reteriveprofile,HttpServletRequest request,Model model) throws NumberFormatException, FileNotFoundException, SerialException, MyJobKartException, SQLException{
		HttpSession session = request.getSession();
		long userId=0;
		/*if (null!= session.getAttribute("adminId")) {

			userId =(long) session.getAttribute("adminId");
			reteriveprofile.setAdminId(userId);

		}*/
		int page = 1;
		final String paging = request.getParameter("page");
		int maxRecord = Integer.parseInt(MyjobkartResourceBundle
				.getValue("record.page.limit"));
		if (null != paging) {
			page = Integer.parseInt(paging);
			model.addAttribute("paging",page);
		}

		//long totalJobseekerProfileCount = jobSeekerService.getJobseekerProfileCount(reteriveprofile);		
		long totalJobseekerProfileCount = employerService.employerCount(reteriveprofile);
		int startingRecordIndex = paginationPageValues(page, maxRecord);
		reteriveprofile.setRecordIndex(startingRecordIndex);
		reteriveprofile.setMaxRecord(maxRecord);
		reteriveprofile.setPagination("pagination");
		reteriveprofile = this.employerService
				.retriveJobseeker(reteriveprofile);
		if (null != reteriveprofile.getJobseekerProfileList()
				&& 0 != reteriveprofile.getJobseekerProfileList()
				.size()) {
			this.profileList = reteriveprofile.getJobseekerProfileList();

			model.addAttribute("searchJobseeker", PaginationClass
					.paginationLimitedRecords(page,profileList, maxRecord,
							totalJobseekerProfileCount));
		}
	}


	@RequestMapping(value = "/create-employer", method = RequestMethod.GET)
	public String createEmployer(HttpServletRequest request,  Model model) throws SerialException, MyJobKartException, SQLException, NumberFormatException, FileNotFoundException{


		EmployerRegisterBO employer =new EmployerRegisterBO();
		//employer=reterivedEmployer(employer);

		paginations(employer, request, model);
		model.addAttribute("type","add");
		model.addAttribute("updateProfile", new EmployerRegisterBO());
		model.addAttribute("createProfile", new EmployerRegisterBO());
		model.addAttribute("searchjobseeker", employer);
		return"profile-view";
	}


	@RequestMapping(value = "/create-employer", method = RequestMethod.POST)
	public String createEmployer(
			@Valid @ModelAttribute("createProfile") EmployerRegisterBO profileBO,
			BindingResult result, HttpServletRequest request, Model model) throws MyJobKartException{
		try{


			boolean isValidation=false;

			HttpSession session = request.getSession();
			if(null != session.getAttribute("adminId")){
				long userLoginId = (long) session.getAttribute("adminId");
				profileBO.setEmployerId(userLoginId);
				profileBO.setCreatedBy(userLoginId);
				profileBO.setModifiedBy(userLoginId);
				profileBO.setAdminId(userLoginId);
				profileBO.setDelete(false);
				profileBO.setActive(true);
				profileBO.setCreated(DateHelper.beginningOfDay(new Date()));
				profileBO.setModified(DateHelper.beginningOfDay(new Date()));
			}
			if(!profileBO.getEmailAddress().equalsIgnoreCase(profileBO.getEmailAddress())){
				isValidation=true;
				result.rejectValue("EmailAddress","Validate.Email");	
			}

			//if(!profileBO.getPassword().equals(profileBO.getConfirmPassword())){
				//result.rejectValue("Password","Validate.Password");
			//}

			if(adminService.findEmployerEmail(profileBO.getEmailAddress())){
				result.rejectValue("emailAddress","Validate.EmailAddress");
			}



			if(result.hasErrors() || isValidation){
				EmployerRegisterBO employerRegister = new EmployerRegisterBO();
				paginations(employerRegister, request, model);
				model.addAttribute("type","add");
				model.addAttribute("updateProfile", new EmployerRegisterBO());
				model.addAttribute("searchjobseeker", employerRegister);
				//	model.addAttribute("createProfile", profileBO);
				return "profile-view";
			}

			profileBO=employerService.createEmployer(profileBO);
			if(profileBO.getId()>0){

				model.addAttribute("successmessage", "Employer Created Successfully");
			}else{
				model.addAttribute("infoMessage", "Employer is not Created");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("searchjobseeker", new EmployerRegisterBO());
		return"redirect:/profile-view";
	}



	/*	@RequestMapping(value = "/searchjobseeker", method = RequestMethod.POST)
	public String search(
			@Valid @ModelAttribute("searchjobseeker") EmployerRegisterBO profileBO,
			BindingResult result, HttpServletRequest request, Model model)
					throws SerialException, SQLException, FileNotFoundException {
		LOGGER.entry();
		try {			
			if (null != request.getParameter("successmessage")) {
				model.addAttribute("successmessage",
						request.getParameter("successmessage"));
			}
			if (null != request.getParameter("infoMessage")) {
				model.addAttribute("infoMessage",
						request.getParameter("infoMessage"));
			}

			if (null != request.getParameter("errormessage")) {
				model.addAttribute("errormessage",
						request.getParameter("errormessage"));
			}
			final List<EmployerRegisterBO> profileLists = new ArrayList<EmployerRegisterBO>();
			profileBO = this.jobSeekerService
					.retriveJobseeker(profileBO);
			//	this.profileList = profileBO.getJobseekerProfileList();
			if (null != this.profileList) {
				model.addAttribute("searchJobseeker", this.profileList);
			}

		} catch (Exception jb) {

		}
		model.addAttribute("updateProfile", new EmployerRegisterBO());
		model.addAttribute("searchjobseeker", new EmployerRegisterBO());
		return "profile-view";
	}

	 */


	@RequestMapping(value = "/profile-view", method = RequestMethod.POST)
	public String jobseekerProfileView(
			@Valid @ModelAttribute("updateProfile") EmployerRegisterBO registerBO,
			BindingResult result, HttpServletRequest request, Model model) throws MyJobKartException {
		List<EmployerRegisterBO> profileList=new ArrayList<EmployerRegisterBO>();
		try{
			HttpSession session = request.getSession();

			long userId=0;
			if (null!= session.getAttribute("adminId")) {
				userId =(long) session.getAttribute("adminId");
				registerBO.setEmployerId(userId);
			}
			int count = 0;
			if(null == registerBO.getMobileNo()){
				registerBO.setMobileNo(null);
			}
			if(null == request.getParameter("search") && null==request.getParameter("report")){
				Long Id = (Long) session.getAttribute("adminId");
				AdminLoginBO loginBO = new AdminLoginBO();
				loginBO.setId(Id);
				registerBO.setLoginBO(loginBO);
				registerBO.setModifiedBy(Id);
				registerBO.setEmployerId(Id);
				registerBO = this.employerService.updateEmployer(registerBO);
				if(null != registerBO.getResponse() && !registerBO.getResponse().isEmpty()){




					// Access Log
					/*String ipAddress = request.getHeader("X-FORWARDED-FOR");
					if (ipAddress == null) {
						String pathInfo = request.getRequestURI();
						if (null != session.getAttribute("adminId")
								&& !pathInfo.contains("logout.html")) {
							count = count + 1;
							ipAddress = request.getRemoteAddr();
							String sessionId = request.getSession().getId();
							session.setAttribute("sessionId", sessionId);
							AccessLogBO logBO = new AccessLogBO();
							logBO.setClientIP(ipAddress);
							logBO.setAccessDate(new Date());
							logBO.setSessionId(sessionId);
							logBO.setAccessId(registerBO.getId());
							adminService.createAccessLog(logBO);
						}
					}*/




					model.addAttribute("successmessage", "Updated Successfully");
					registerBO = new EmployerRegisterBO();
				}else{
					model.addAttribute("infoMessage", "Updated Failed");
				}
			}else{
				if(null !=registerBO.getFirstName() && !registerBO.getFirstName().isEmpty()){
					model.addAttribute("firstName",registerBO.getFirstName());
				}

				if(null !=registerBO.getEmailAddress() && !registerBO.getEmailAddress().isEmpty())
				{
					model.addAttribute("emailAddress",registerBO.getEmailAddress());
				}
				if(null !=registerBO.getMobileNo() && !registerBO.getMobileNo().isEmpty() ){
					model.addAttribute("mobileNo",registerBO.getMobileNo());
				}
				if(null !=registerBO.getCompanyName() && !registerBO.getCompanyName().isEmpty() ){
					model.addAttribute("companyName",registerBO.getCompanyName());
				}

				if(null !=registerBO.getIndustryType() && !registerBO.getIndustryType().isEmpty() ){
					model.addAttribute("industryType",registerBO.getCompanyName());
				}
			}

			if(null!=request.getParameter("report") || null!=request.getParameter("updateReport")){
				long reportId=0;
				if(null!=registerBO.getUserName() && !registerBO.getUserName().isEmpty()){
					String  userInfo[]=  registerBO.getUserName().split("-");
					reportId=  Long.parseLong(userInfo[1]);
					registerBO.setAdminId(reportId);
				}
				if(null!=registerBO.getProcess() && registerBO.getProcess().equalsIgnoreCase("create") && !registerBO.getProcess().isEmpty()){
					registerBO.setCreatedBy(reportId);
				}
				if(null!=registerBO.getProcess() && registerBO.getProcess().equalsIgnoreCase("edit") && !registerBO.getProcess().isEmpty()){
					registerBO.setModifiedBy(reportId);
				}
				session.setAttribute("reportSearchBO", registerBO);

				List<AdminUserBO>userBOList=new ArrayList<AdminUserBO>();
				userBOList = adminService.retrieveUser();
				List<String> userList=new ArrayList<String>();
				if(null != userBOList && 0!= userBOList.size()){
					for(AdminUserBO userlist: userBOList){
						userList.add(userlist.getName()+"-"+userlist.getId());
					}

					model.addAttribute("userBOList", userList);
				}	

				model.addAttribute("reportSearch","report");
				model.addAttribute("updateProfile", registerBO);

			}else{
				model.addAttribute("updateProfile", new EmployerRegisterBO());
			}





			/*if(null!=registerBO.getStarDate()){
				model.addAttribute("reportSearch","report");
			}*/

			long totalJobseekerProfileCount = employerService.employerCount(registerBO);
			EmployerRegisterBO profile = new EmployerRegisterBO();
			model.addAttribute("search", profile);
			int page =1;
			int maxRecord = Integer.parseInt(MyjobkartResourceBundle
					.getValue("record.page.limit"));

			if(null != request.getParameter("paging")){
				String paging = request.getParameter("paging");
				page = Integer.valueOf(paging);
			}
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			registerBO.setRecordIndex(startingRecordIndex);
			registerBO.setMaxRecord(maxRecord);
			registerBO.setPagination("pagination");
			if(null == registerBO.getMobileNo()){
				registerBO.setMobileNo(null);
			}
			if(registerBO.getAdminId()==0){
				registerBO.setAdminId(userId);

			}
			registerBO = this.employerService
					.retriveJobseeker(registerBO);
			if (null != registerBO.getJobseekerProfileList()
					&& 0 != registerBO.getJobseekerProfileList()
					.size()) {
				profileList = registerBO.getJobseekerProfileList();

				model.addAttribute("searchJobseeker", PaginationClass
						.paginationLimitedRecords(page,profileList, maxRecord,
								totalJobseekerProfileCount));
			}else{
				model.addAttribute("infoMessage","No Record Found");
			}
			/*model.addAttribute("searchJobseeker", PaginationClass
					.paginationLimitedRecords(page,profileList, maxRecord,
							totalJobseekerProfileCount));*/

		}catch(Exception e){
			e.printStackTrace();

		}
		//	model.addAttribute("updateProfile", new EmployerRegisterBO());
		model.addAttribute("searchjobseeker", new EmployerRegisterBO());
		return "profile-view";
	}

	private static int paginationPageValues(int pageid, int recordPerPage) {
		int pageRecords = 0;
		if (pageid == 1) {
			pageRecords = 0;
		} else {
			pageRecords = (pageid - 1) * recordPerPage + 1;
			pageRecords = pageRecords - 1;
		}
		return pageRecords;
	}
	/**
	 * This method used to perform the retrieve jobseeker_profile function
	 * (jobseeker)
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 * @throws IOException
	 * @throws SQLException
	 * @throws SerialException
	 */



	@RequestMapping(value = "/create-user", method = RequestMethod.GET)
	public String usercreate(Model model, HttpServletRequest request)
			throws MyJobKartException, MalformedURLException, NumberFormatException, 
			FileNotFoundException, SerialException, SQLException {
		List<AdminUserBO>userBOList=new ArrayList<AdminUserBO>();

		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin-sign-in.html";
		}
		AdminUserBO bo=new AdminUserBO();
		int page = 1;
		final String paging = request.getParameter("page");
		if (null != paging) {
			page = Integer.parseInt(paging);
		}
		userBOList = adminService.retrieveUser();
		if(null != userBOList && 0!= userBOList.size()){
			model.addAttribute("userBOList", userBOList);
		}
		if (null != request.getParameter("successMessage")) {
			model.addAttribute("successMessage",
					request.getParameter("successMessage"));
		}
		if (null != request.getParameter("infoMessage")) {
			model.addAttribute("infoMessage",
					request.getParameter("infoMessage"));
		}
		if (null != request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage",
					request.getParameter("errorMessage"));
		}
		if(null != request.getParameter("functionType") && !request.getParameter("functionType").isEmpty() ){
			model.addAttribute("functionType",request.getParameter("functionType"));
		}else{
			model.addAttribute("functionType", "add");
		}

		model.addAttribute("user",bo);
		return "create-user";

	}
	
	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public String usercreate (
			@Valid @ModelAttribute("user") AdminUserBO adminBO,
			BindingResult result, HttpServletRequest request, Model model) throws MyJobKartException {
		long userId = 0;
		boolean isvalidation = false;
		try{
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin-sign-in.html";
			}
			model.addAttribute("functionType", "add");
			if (result.hasErrors()) {
				return "create-user";
			}

			if (!adminBO.getPassword().equals(adminBO.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				isvalidation = true;
				if (0 != userId) {
					model.addAttribute("infoMessage","password-confirmpassword Verify");			
				}
			}
			if (adminService.findByEmail(adminBO.getEmailAddress())) {
				model.addAttribute("user", adminBO);
				//model.addAttribute("errorMessage","This mailId already exist");
				isvalidation = true;
			}
			
			if (adminService.findByMobileNo(adminBO.getMobileNo())) {
				model.addAttribute("user", adminBO);
				model.addAttribute("errorMessage","This MobileNo already exist");
				isvalidation = true;
			}
			
			else{
				adminBO=adminService.createuser(adminBO);
				if(0 !=adminBO.getId()){
					model.addAttribute("successMessage","Admin user Added Successfully");
				}
				else{
					model.addAttribute("errorMessage",
							adminBO.getErrorMessage());
					adminBO.setErrorMessage(null);
				}
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add user has failed:" + ex.getMessage());
			}
			LOGGER.info("Add user has failed:" + ex.getMessage());
		}
		model.addAttribute("user", adminBO);
		model.addAttribute("functionType", "add");
		return "redirect:/create-user";
	}

	
	
	@RequestMapping(value = "/create-customer", method = RequestMethod.GET)
	public String customercreate(Model model, HttpServletRequest request)
			throws MyJobKartException, MalformedURLException, NumberFormatException, 
			FileNotFoundException, SerialException, SQLException {
		List<AdminBO>userBOList=new ArrayList<AdminBO>();

		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin-sign-in.html";
		}
		AdminBO bo=new AdminBO();
		int page = 1;
		final String paging = request.getParameter("page");
		if (null != paging) {
			page = Integer.parseInt(paging);
		}
		userBOList = adminService.retrieveCustomer();
		if(null != userBOList && 0!= userBOList.size()){
			model.addAttribute("userBOList", userBOList);
		}
		/*if (null != request.getParameter("successMessage")) {
			model.addAttribute("successMessage",
					request.getParameter("successMessage"));
		}
		if (null != request.getParameter("infoMessage")) {
			model.addAttribute("infoMessage",
					request.getParameter("infoMessage"));
		}
		if (null != request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage",
					request.getParameter("errorMessage"));
		}*/
		if(null != request.getParameter("functionType") && !request.getParameter("functionType").isEmpty() ){
			model.addAttribute("functionType",request.getParameter("functionType"));
		}else{
			model.addAttribute("functionType", "add");
		}

		model.addAttribute("customer",bo);
		return "create-customer";

	}
	
	
	
	/*@RequestMapping(value="/create-customer", method = RequestMethod.POST)
	public String createCustomer(@Valid @ModelAttribute("product") AdminBO adminbo,BindingResult result,Model model) throws IOException, ServletException {	
		
		
		HttpSession session = result.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin-sign-in.html";
		}
		
		if (result.hasErrors()) {
			return "product-create";
		}
		String response=null;
	
		if(null!=adminbo) {
		 response =adminService.insertcustomerdetails(adminbo);		
			}	
		
		if(null!=response) {			
		    System.out.println("Register Successful");			
			List<AdminBO> car=new ArrayList<AdminBO>();
			car=adminService.getcustomerDetails();
			
		if(null!=car&& car.size()>0) {	
				System.out.println("product value retrieved");
				model.addAttribute("carlist",car);				
			}
		}                                                                              
			
		return "product-create";
		
	   }*/
	
	
	/*@RequestMapping(value = "/create-customer", method = RequestMethod.POST)
	public String usercreate (
			@Valid @ModelAttribute("customer") AdminBO adminBO,
			BindingResult result, HttpServletRequest request, Model model) throws MyJobKartException {
		long userId = 0;
		boolean isvalidation = false;
		try{
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin-sign-in.html";
			}
			model.addAttribute("functionType", "add");
			if (result.hasErrors()) {
				return "create-user";
			}

			if (!adminBO.getPassword().equals(adminBO.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				isvalidation = true;
				if (0 != userId) {
					model.addAttribute("infoMessage","password-confirmpassword Verify");			
				}
			}
			if (adminService.findByEmail(adminBO.getEmailAddress())) {
				model.addAttribute("user", adminBO);
				//model.addAttribute("errorMessage","This mailId already exist");
				isvalidation = true;
			}
			
			if (adminService.findByMobileNo(adminBO.getMobileNo())) {
				model.addAttribute("user", adminBO);
				model.addAttribute("errorMessage","This MobileNo already exist");
				isvalidation = true;
			}
			
			else{
				adminBO=adminService.createuser(adminBO);
				if(0 !=adminBO.getId()){
					model.addAttribute("successMessage","Admin user Added Successfully");
				}
				else{
					model.addAttribute("errorMessage",
							adminBO.getErrorMessage());
					adminBO.setErrorMessage(null);
				}
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add user has failed:" + ex.getMessage());
			}
			LOGGER.info("Add user has failed:" + ex.getMessage());
		}
		model.addAttribute("user", adminBO);
		model.addAttribute("functionType", "add");
		return "redirect:/create-user";
	}*/

	@RequestMapping(value = "/create-customer", method = RequestMethod.POST)
	public String createcustomer(
			@Valid @ModelAttribute("customer") AdminBO adminBO,
			BindingResult result, HttpServletRequest request, Model model) throws MyJobKartException {
		long userId = 0;
		boolean isvalidation = false;
		try{
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin-sign-in.html";
			}
			//model.addAttribute("functionType", "add");
			if (result.hasErrors()) {
				return "create-customer";
				
			}
               else{
				adminBO=adminService.createcustomer(adminBO);
				
				if(0 !=adminBO.getAdminId()){
					model.addAttribute("successMessage","Customer Added Successfully");
				}
				else{
					model.addAttribute("errorMessage",
							adminBO.getErrorMessage());
					adminBO.setErrorMessage(null);
				}
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add user has failed:" + ex.getMessage());
			}
			LOGGER.info("Add user has failed:" + ex.getMessage());
		}
		model.addAttribute("customer", adminBO);
		model.addAttribute("functionType", "add");
		return "redirect:/create-customer";
		//return "create-view";
	}


	@RequestMapping(value = "/active-deactive-user", method = RequestMethod.GET)
	public String activedeactiveuser(Model model, HttpServletRequest request)
			throws MyJobKartException, FileNotFoundException{
		try{
			String status = request.getParameter("status");
			String statusvalue[] = status.split(",");
			String userstatus= statusvalue[0];
			long userId = Long.valueOf(statusvalue[1]);

			AdminUserBO userBO = new AdminUserBO();
			userBO.setId(userId);
			if (userstatus.equals("Active")) {
				userBO.setActive(false);
			} else {
				userBO.setActive(true);
			}

			boolean useractivestatus =adminService.userStatus(userBO);

			if (useractivestatus) {
				model.addAttribute("successmessage","Admin user activated Successfully");
			} else {
				model.addAttribute("successmessage","Admin user de-activated Successfully");
			}
		} catch (final NullPointerException ne) {
			ne.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("user status has failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("user status has failed:" + ne.getMessage() + ne);
		}

		return "redirect:/create-user.html";
	}

	/*@RequestMapping(value = "/active-deactive-customer", method = RequestMethod.GET)
	public String activedeactivecustomer(Model model, HttpServletRequest request)
			throws MyJobKartException, FileNotFoundException{
		try{
			String status = request.getParameter("status");
			String statusvalue[] = status.split(",");
			String userstatus= statusvalue[0];
			int userId = Integer.valueOf(statusvalue[1]);

			AdminBO userBO = new AdminBO();
			userBO.setAdminId(userId);
			if (userstatus.equals("Active")) {
				userBO.setIsActive(false);
			} else {
				userBO.setIsActive(true);
			}

			boolean useractivestatus =adminService.customerStatus(userBO);

			if (useractivestatus) {
				model.addAttribute("successmessage","Admin user activated Successfully");
			} else {
				model.addAttribute("successmessage","Admin user de-activated Successfully");
			}
		} catch (final NullPointerException ne) {
			ne.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("user status has failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("user status has failed:" + ne.getMessage() + ne);
		}

		return "redirect:/create-customer.html";
	}*/
	
	
	@RequestMapping(value = "/delete-user", method = RequestMethod.GET)
	public String deleteUser(Model model, HttpServletRequest request)
			throws MyJobKartException, FileNotFoundException {
		List<AdminUserBO>userBOList=new ArrayList<AdminUserBO>();
		AdminUserBO bo=new AdminUserBO();
		AdminController.LOGGER.entry();
		String id = null;
		long deletedId = 0;
		if (request.getParameter("id") != null) {
			id = request.getParameter("id");
			deletedId = Long.parseLong(id);
		}
		AdminUserBO deleteProfile = new AdminUserBO();
		deleteProfile.setId(deletedId);
		deleteProfile.setDelete(true);
		deleteProfile = adminService
				.deleteProfile(deleteProfile);
		if (null != deleteProfile.getResponse()) {
			model.addAttribute("successmessages",
					"deleted succesfully");
		} else {
			model.addAttribute("infoMessagemessage","deleted failed");
		}
		AdminController.LOGGER.exit();
		model.addAttribute("functionType", "add");
		return "redirect:/create-user";
	}

	@RequestMapping(value = "/edit-user", method = RequestMethod.GET)
	public String edituser(Model model, HttpServletRequest request)
			throws MyJobKartException {
		try{
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin-sign-in.html";
			}

			if (null != request.getParameter("errorMessages")) {
				model.addAttribute("infoMessage",
						request.getParameter("errorMessages"));
			}
			if(null !=request.getParameter("Successmessage"));{
				model.addAttribute("Successmessage",
						request.getParameter("Successmessage"));
			}
			final String id = request.getParameter("id");

			final long userId = Long.parseLong(id);
			session.setAttribute("userId", userId);

			AdminUserBO userBO = new AdminUserBO();
			userBO = adminService.retrieveusers(userId);

			List<AdminUserBO>userBOList=new ArrayList<AdminUserBO>();
			userBOList = adminService.retrieveUser();
			if(null != userBOList && 0!= userBOList.size()){
				model.addAttribute("userBOList", userBOList);
			}

			if (null != request.getParameter("infoMessage")) {
				model.addAttribute("infoMessage",
						request.getParameter("infoMessage"));
			}
			if (userBO.getId() ==userId ) {
				session.setAttribute("updateUser", reteriveprofile);
				model.addAttribute("functionType", "edit");
				model.addAttribute("editUser",userBO);
				return "create-user";
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("user update get:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("user update get:" + ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "create-user";
	}
	
	@RequestMapping(value = "/edit-customer", method = RequestMethod.GET)
	public String editcustomer(Model model, HttpServletRequest request)
			throws MyJobKartException {
		try{
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin-sign-in.html";
			}

			if (null != request.getParameter("errorMessages")) {
				model.addAttribute("infoMessage",
						request.getParameter("errorMessages"));
			}
			if(null !=request.getParameter("Successmessage"));{
				model.addAttribute("Successmessage",
						request.getParameter("Successmessage"));
			}
			final String id = request.getParameter("id");

			final int userId = Integer.parseInt(id);
			session.setAttribute("userId", userId);

			AdminBO userBO = new AdminBO();
			userBO = adminService.retrieveCustomers(userId);

			List<AdminBO>userBOList=new ArrayList<AdminBO>();
			userBOList = adminService.retrieveCustomer();
			if(null != userBOList && 0!= userBOList.size()){
				model.addAttribute("customerBOList", userBOList);
			}

			if (null != request.getParameter("infoMessage")) {
				model.addAttribute("infoMessage",
						request.getParameter("infoMessage"));
			}
			if (userBO.getAdminId() ==userId ) {
				session.setAttribute("updatecustomer", reteriveprofile);
				model.addAttribute("functionType", "edit");
				model.addAttribute("editcustomer",userBO);
				return "create-customer";
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("user update get:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("user update get:" + ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "create-customer";
	}

	@RequestMapping(value = "/edit-user", method = RequestMethod.POST)
	public String edit (
			@Valid @ModelAttribute("updateuser") AdminUserBO adminBO,
			BindingResult result, HttpServletRequest request, Model model) throws MyJobKartException {
		List<AdminUserBO>userBOList=new ArrayList<AdminUserBO>();

		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin-sign-in.html";
		}

		try{
			long userId=0;

			if (null != session.getAttribute("adminId")) {
				userId=(Long)session.getAttribute("adminId");
				adminBO.setUserId(userId);
			}

			model.addAttribute("functionType", "edit");
			if (result.hasErrors()) {
				return "edit-user";
			}

			adminBO=adminService.updateuser(adminBO);
			if(0 !=adminBO.getId()){
				model.addAttribute("Successmessage","Admin user updated Successfully");

			}
			else{
				model.addAttribute("errorMessages",
						adminBO.getErrorMessage());
				adminBO.setErrorMessage(null);
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Edit adminuser has failed:" + ex.getMessage());
			}
			LOGGER.info("Edit adminuser has failed:" + ex.getMessage());
		}
		model.addAttribute("functionType", "add");
		return "redirect:/create-user.html";	

	}
	
	
	@RequestMapping(value = "/edit-customer", method = RequestMethod.POST)
	public String edit (
			@Valid @ModelAttribute("updatecustomer") AdminBO adminBO,
			BindingResult result, HttpServletRequest request, Model model) throws MyJobKartException {
		List<AdminBO>userBOList=new ArrayList<AdminBO>();

		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin-sign-in.html";
		}

		try{
			int userId=0;

			if (null != session.getAttribute("adminId")) {
				userId=(Integer)session.getAttribute("adminId");
				adminBO.setAdminId(userId);
			}

			model.addAttribute("functionType", "edit");
			if (result.hasErrors()) {
				return "edit-customer";
			}

			adminBO=adminService.updatecustomer(adminBO);
			if(0 !=adminBO.getAdminId()){
				model.addAttribute("Successmessage","Admin user updated Successfully");

			}
			else{
				model.addAttribute("errorMessages",
						adminBO.getErrorMessage());
				adminBO.setErrorMessage(null);
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Edit adminuser has failed:" + ex.getMessage());
			}
			LOGGER.info("Edit adminuser has failed:" + ex.getMessage());
		}
		model.addAttribute("functionType", "add");
		return "redirect:/create-customer.html";	
		//return "create-view";	

	}




	@RequestMapping(value = "/profile-view", method = RequestMethod.GET)
	public String retriveJobseeker(Model model, HttpServletRequest request)
			throws MyJobKartException, IOException, SerialException,
			SQLException {
		LOGGER.entry();
		HttpSession session = request.getSession();
		try {
			EmployerRegisterBO reteriveprofile = new EmployerRegisterBO();
			long userId=0;
			if (null!= session.getAttribute("adminId")) {
				userId =(long) session.getAttribute("adminId");
				reteriveprofile.setEmployerId(userId);
			}
			if(null != request.getParameter("update")){
				final String id = request.getParameter("id");
				EmployerRegisterBO registerBO = new EmployerRegisterBO();
				final long employerId = Long.parseLong(id);
				registerBO.setId(employerId);
				session.setAttribute("jobprofileId", employerId);
				registerBO = this.employerService.retriveJobseekerById(registerBO);
				model.addAttribute("updateProfile", registerBO);
				model.addAttribute("update", request.getParameter("update"));
			}

			else{
				model.addAttribute("updateProfile", new EmployerRegisterBO());
			}

			if(null != request.getParameter("report") && !request.getParameter("report").isEmpty() || null!=request.getParameter("updateReport")){
				//model.addAttribute("updateProfile", new EmployerRegisterBO());
				if(null!=session.getAttribute("reportSearchBO")){
					reteriveprofile = (EmployerRegisterBO) session.getAttribute("reportSearchBO");	
					employer=reteriveprofile;
					session.removeAttribute("reportSearchBO");
				}


				List<AdminUserBO>userBOList=new ArrayList<AdminUserBO>();
				userBOList = adminService.retrieveUser();
				List<String> userList=new ArrayList<String>();
				if(null != userBOList && 0!= userBOList.size()){
					for(AdminUserBO userlist: userBOList){
						userList.add(userlist.getName()+"-"+userlist.getId());
					}

					model.addAttribute("userBOList", userList);
				}
				//	model.addAttribute("viewRecords", new EmployerRegisterBO());
				model.addAttribute("reportSearch","report");
			}




			/*if(null != request.getParameter("create")){
				model.addAttribute("type","add");
				//	model.addAttribute("updateProfile", new EmployerRegisterBO());
				model.addAttribute("createProfile", new EmployerRegisterBO());
				//	model.addAttribute("searchjobseeker", new EmployerRegisterBO());
				//return"profile-view";
			}*/




			if(null != request.getParameter("name")){
				reteriveprofile.setFirstName(request.getParameter("name"));
				model.addAttribute("firstName", reteriveprofile.getFirstName());
			}
			if(null != request.getParameter("mobileno") && !request.getParameter("mobileno").isEmpty()){
				String mobile = request.getParameter("mobileno");
				reteriveprofile.setMobileNo(mobile);
				model.addAttribute("mobileNo", reteriveprofile.getMobileNo());
			}else{
				reteriveprofile.setMobileNo(null);
			}

			if(null != request.getParameter("mail")&& !request.getParameter("mail").isEmpty()){
				reteriveprofile.setEmailAddress(request.getParameter("mail"));
				model.addAttribute("emailAddress", reteriveprofile.getEmailAddress());
			}
			if(null != request.getParameter("industrytype")&& !request.getParameter("industrytype").isEmpty()){
				reteriveprofile.setEmailAddress(request.getParameter("industrytype"));
				model.addAttribute("industryType", reteriveprofile.getIndustryType());
			}

			if(null != request.getParameter("company")&& !request.getParameter("company").isEmpty()){
				reteriveprofile.setCompanyName(request.getParameter("company"));
				model.addAttribute("companyName", reteriveprofile.getCompanyName());
			}
			
			if(null !=request.getParameter("reports")){
				model.addAttribute("reports",request.getParameter("reports"));
			}
			int page = 1;
			int maxRecord = Integer.parseInt(MyjobkartResourceBundle
					.getValue("record.page.limit"));
			if(null !=request.getParameter("page")&& !request.getParameter("page").isEmpty()){	
				final String paging = request.getParameter("page");
				if (null != paging) { 
					page = Integer.parseInt(paging);
					model.addAttribute("paging",page);
				}
			}
			if (null == session.getAttribute("adminId")){
				return "redirect:/admin-sign-in.html";
			}
			if (null != request.getParameter("successmessage")) {
				model.addAttribute("successmessage",
						request.getParameter("successmessage"));
			}

			if (null != request.getParameter("infoMessage")) {
				model.addAttribute("infoMessage",
						request.getParameter("infoMessage"));
			}
			if (null != request.getParameter("errormessage")) {
				model.addAttribute("errormessage",
						request.getParameter("errormessage"));
			}
			// Display Tags
			String userType = (String) session.getAttribute("userType");
			/*model.addAttribute("userType", userType);*/
			session.setAttribute("userType", userType);
			reteriveprofile.setUserType(userType);

			// model.addAttribute("userType", userType);

			long totalJobseekerProfileCount = employerService.employerCount(reteriveprofile);
			EmployerRegisterBO profile = new EmployerRegisterBO();
			model.addAttribute("search", profile);
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			reteriveprofile.setRecordIndex(startingRecordIndex);
			reteriveprofile.setMaxRecord(maxRecord);
			reteriveprofile.setPagination("pagination");
			reteriveprofile = this.employerService
					.retriveJobseeker(reteriveprofile);
			if (null != reteriveprofile.getJobseekerProfileList()
					&& 0 != reteriveprofile.getJobseekerProfileList()
					.size()) {
				List<EmployerRegisterBO> profileList = reteriveprofile.getJobseekerProfileList();

				model.addAttribute("searchJobseeker", PaginationClass
						.paginationLimitedRecords(page,profileList, maxRecord,
								totalJobseekerProfileCount));
			}


		}catch (final MyJobKartException jb) {
			jb.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker profile view failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Jobseeker profile view failed:" + jb.getErrorCode()
					+ jb);
		}
		model.addAttribute("searchjobseeker", new EmployerRegisterBO());
		return "profile-view";
	}




	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request)
			throws MyJobKartException, MalformedURLException {
		final HttpSession session = request.getSession();
		String emailId = (String) session.getAttribute("lstatusemailId");
		LoginStatusVO loginStatusVO = new LoginStatusVO();
		loginStatusVO.setUserName(emailId);
		adminService.editLoginStatus(loginStatusVO);
		session.invalidate();
		return "redirect:/admin-sign-in.html";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request)
			throws MyJobKartException, MalformedURLException {

		return "redirect:/admin-sign-in.html";
	}
	@RequestMapping(value = "/delete-employer", method = RequestMethod.GET)
	public String jobseekerDeleteProfile(Model model, HttpServletRequest request)
			throws MyJobKartException, FileNotFoundException, NumberFormatException, SerialException, SQLException {
		HttpSession session = request.getSession();
		EmployerRegisterBO bo=new EmployerRegisterBO();
		AdminController.LOGGER.entry();
		String id = null;
		long deletedId = 0;
		if (request.getParameter("id") != null) {
			id = request.getParameter("id");
			deletedId = Long.parseLong(id);
		}
		EmployerRegisterBO deleteProfile = new EmployerRegisterBO();
		deleteProfile.setId(deletedId);
		deleteProfile.setDelete(true);
		deleteProfile = employerService
				.deleteProfile(deleteProfile);
		if (null != deleteProfile.getResponse()) {
			model.addAttribute("successmessage",
					"Data has been Successfully Deleted");
		} else {
			model.addAttribute("infoMessagemessage","deleted failed");
		}
		paginations(deleteProfile, request, model);
		if(null!=request.getParameter("deleteReport") && !request.getParameter("deleteReport").isEmpty()){

			model.addAttribute("updateProfile", new EmployerRegisterBO());
			model.addAttribute("reportSearch","report");
		}
		model.addAttribute("searchjobseeker", new EmployerRegisterBO());

		AdminController.LOGGER.exit();
		return "redirect:/profile-view";
	}
	@RequestMapping(value="/employer-view",method=RequestMethod.GET)
	public String employerview(Model model,HttpServletRequest request) 
			throws SerialException, SQLException{
		try{
			HttpSession session = request.getSession();
			EmployerRegisterBO registerBO = new EmployerRegisterBO();
			long jobseekerId = 0;
			List<EmployerRegisterBO> registerBOList = new ArrayList<EmployerRegisterBO>();
			EmployerRegisterBO BO  = new EmployerRegisterBO();
			EmployerRegisterBO empBO=new EmployerRegisterBO();
			if (null != request.getParameter("id")) {
				final String id = request.getParameter("id");
				jobseekerId = Long.parseLong(id);
				long userId=0;

				if(null!= session.getAttribute("adminId")) {
					userId =(long) session.getAttribute("adminId");
					BO.setEmployerId(userId);
				}
				if(null !=request.getParameter("reports")){
					model.addAttribute("reports",request.getParameter("reports"));
				}
				BO.setId(jobseekerId);
				if(null == BO.getMobileNo()){
					BO.setMobileNo(null);
				}
			}
			BO = employerService.retriveJobseeker(BO);
			List<String> descriptionList = new ArrayList<String>();
			List<String> dateList = new ArrayList<String>();
			if (null != BO.getJobseekerProfileList()
					&& 0 != BO.getJobseekerProfileList().size()) {
				registerBOList=BO.getJobseekerProfileList();

				for(EmployerRegisterBO regBO:registerBOList){
					if(null !=  regBO.getDescriptionList()){
						for(String des: regBO.getDescriptionList()){
							descriptionList.add(des);
						}
					}
					if(null !=BO.getEmaildateList()){
						for(String date :BO.getEmaildateList()){
							dateList.add(date);
						}
						
					}
					model.addAttribute("dateList",dateList);
					model.addAttribute("viewemployer",registerBOList.get(0));
					model.addAttribute("describe",descriptionList);
					model.addAttribute("updateemployer",registerBO);
					
				}
			}
		/*	for(String date :BO.getEmaildateList()){
				String  parts=date;
				String st=parts;
				System.out.println(st +"date");
			}*/

			/*for(String des:descriptionList){

				String[] parts = des.split(":");

				String st = parts[0];
				String ts = parts[1];

				System.out.println(st +"des    date" +ts);

			}*/

		}
		catch(MyJobKartException e){
			e.printStackTrace();
		}

		return "employer-view";
	}


	@RequestMapping(value = { "/employer-description.html" }, method = RequestMethod.POST)
	public @ResponseBody
	String registerBO(@RequestParam 
			String description,String date,long id, Model model,HttpServletRequest request) {
		AdminLoginBO loginBO=new AdminLoginBO();
		EmployerRegisterBO BO=new EmployerRegisterBO();
		long jobseekerId = 0;
		String updateStatus = null;
		HttpSession session =request.getSession();
		if (null != session.getAttribute("adminId")){
			long ids  = (long) session.getAttribute("adminId");
			jobseekerId = ids;
		}
		String status="Inprocess";
		BO.setId(id);
		BO.setDescription(description);
		BO.setDate(date);
		BO.setAdminId(jobseekerId);
		BO.setStatus(status);
		BO = employerService.adddescription(BO);
		if(null != BO ){
			updateStatus = "successfully updated";
		}
		return updateStatus;
	}

}