package com.scube.techboot.controller;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scube.techboot.bo.CampaignBO;
import com.scube.techboot.bo.CampaignSmsTrakingBO;
import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.ContactBO;
import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.CourseRegistrationBO;
import com.scube.techboot.bo.CustomerBO;
import com.scube.techboot.bo.EventRegisterBO;
import com.scube.techboot.bo.EventsBO;
import com.scube.techboot.bo.LoginBO;
import com.scube.techboot.bo.NewslettersBO;
import com.scube.techboot.bo.ProductServiceBO;
import com.scube.techboot.bo.RecentNewsBO;
import com.scube.techboot.bo.StudentRegisterBO;
import com.scube.techboot.bo.TestimonialBO;
import com.scube.techboot.security.TechBootUser;
import com.scube.techboot.service.AdminService;
import com.scube.techboot.service.CampaignService;
import com.scube.techboot.service.CompanyService;
import com.scube.techboot.service.CourseService;
import com.scube.techboot.service.CustomerService;
import com.scube.techboot.service.EventService;
import com.scube.techboot.service.ProductService;
import com.scube.techboot.service.RecentNewsService;
import com.scube.techboot.service.StudentService;
import com.scube.techboot.service.TestimonialService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.TechbootResourceBundle;


@Controller
public class AdminController extends ControllerUtils {


	@Autowired
	private AdminService adminService;
	
	@Autowired
	private StudentService studentService;


	@Autowired
	private CompanyService companyService;

	@Autowired 
	private TestimonialService testimonialService;

	@Autowired
	private EventService eventService;

	@Autowired
	private RecentNewsService recentNewsService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CampaignService campaignService;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request,HttpSession session) throws FileNotFoundException, ParseException{

		if(null!=request.getParameter("successMessage")){
			model.addAttribute("successMessage",request.getParameter("successMessage"));
		}
		TechBootUser user=getUserSecurity();
		CourseBO courseBO=new CourseBO();
		CompanyBO companyBO=new CompanyBO();

		if(null!=user && null!=user.getCompany())
		{
			int companyId=user.getCompany().getCompanyId();
			companyBO.setCompanyId(companyId);
			courseBO.setCompanyBO(companyBO);

		}
		courseBO.setIsDelete(false);

		List<CourseBO> courselist=courseService.viewCourseList(courseBO);
		if(null!=courselist && !courselist.isEmpty() && courselist.size()>0) {
			if(null!=courselist.get(0)){
				CourseBO courseBO1=courselist.get(0);
				session.setAttribute("ind0", courseBO1);
			}
			if(null!=courselist && courselist.size()>1){
				session.setAttribute("ind1", courselist.get(1));
			}
			if(null!=courselist && courselist.size()>2){
				session.setAttribute("ind2", courselist.get(2));
			}
			model.addAttribute("courselist", courselist);
		}
		EventsBO eventBO=new EventsBO();

		if(null!=user && null!=user.getCompany())
		{
			int companyId=user.getCompany().getCompanyId();
			companyBO.setCompanyId(companyId);
			eventBO.setCompanyBO(companyBO);

		}
		List<EventsBO> eventlist= eventService.viewListEvent(eventBO,session);
		if(null!=eventlist && !eventlist.isEmpty() && eventlist.size()>0){
			model.addAttribute("eventlists",eventlist);

		}
		TestimonialBO testimonialBo=new TestimonialBO();
		List<TestimonialBO> testinglist=testimonialService.viewTestimonial(testimonialBo);
		if(null!=testinglist && !testinglist.isEmpty() && testinglist.size()>0){
			model.addAttribute("testinglists",testinglist);
		}
		model.addAttribute("newsLettersBO", new NewslettersBO());
		RecentNewsBO recentNewsBO=new RecentNewsBO();
		List<RecentNewsBO> newslist=recentNewsService.getViewRecentNewsList(recentNewsBO);
		if(null!=newslist && !newslist.isEmpty() && newslist.size()>0){
			model.addAttribute("newslists",newslist);
			return "home";
		}
		return "home";

	}

	@RequestMapping(value="/sign-in", method=RequestMethod.GET)
	public String login(Model model, HttpServletRequest request){
		LoginBO login = new LoginBO();
		model.addAttribute("login" , login);
		if(null!=request.getParameter("errorMessage")){
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}
		if(null!=request.getParameter("succesmessage")){
			model.addAttribute("succesmessage", request.getParameter("succesmessage"));
		}
		return "sign-in";
	}

	@RequestMapping(value = "/admin-profile", method = RequestMethod.GET)
	public String adminProfile(Model model, HttpServletRequest request,HttpSession session) throws Exception{

		long adminId=(long) session.getAttribute("adminId");

		LoginBO loginBO =adminService.getAdminProfile(adminId);
		if(null!=loginBO) {
			model.addAttribute("adminProfile", loginBO);
			return "admin-profile";
		}
		return "sign-in";
	}

	@RequestMapping(value = "/admin-home", method = RequestMethod.GET)
	public String adminhome(Model model, HttpServletRequest request,HttpSession session) {
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		//events counts
		EventsBO eventBO=new EventsBO();
		long count=eventService.retrieveOfEventsCount(eventBO);
		if(0!=count){
			model.addAttribute("eventsCounts", count);
		}
		//service counts
		ProductServiceBO ServiceBO=new ProductServiceBO();
		List<ProductServiceBO>serviceList=productService.listservice(ServiceBO);
		if(null!=serviceList && !serviceList.isEmpty() && serviceList.size()>0){
			long serviceCounts=serviceList.size();
			model.addAttribute("serviceCounts", serviceCounts);
		}
		//campaign counts
		CampaignBO campaignBO=new CampaignBO();
		campaignBO.setIsDelete(false);
		campaignBO.setSending_status(true);
		long campaignCount=campaignService.getListOfCampaign(campaignBO);
		if(0!=campaignCount){
			model.addAttribute("campaignCounts", campaignCount);
		}
		//customer counts
		CustomerBO customerBo=new CustomerBO();
		List<CustomerBO> customerList=customerService.retriveCustomer(customerBo);
		if(null!=customerList && !customerList.isEmpty() && customerList.size()>0){
			long customerCount=customerList.size();
			model.addAttribute("customerCounts", customerCount);
		}
		//events registration counts
		EventRegisterBO eventRegisterBO= new EventRegisterBO();
		long values=eventService.retrieveOfEventsRegistrationCount(eventRegisterBO);
		if(0!=values){
			model.addAttribute("eventRegistrationCounts", values);
		}

		//course registration Counts
		CourseRegistrationBO courseRegister=new CourseRegistrationBO();
		List<CourseRegistrationBO> courseRegisterLists=courseService.viewCourseRegistrationList(courseRegister);
		if(null!=courseRegisterLists && !courseRegisterLists.isEmpty() && courseRegisterLists.size()>0){
			long recordValue=courseRegisterLists.size();
			model.addAttribute("courseRegistrationCount", recordValue);	
		}

		//course counts
		CourseBO courseBO=new CourseBO();
		long coursesCount=courseService.retrieveCourseCount(courseBO);
		if(0!=coursesCount){
			model.addAttribute("coursesCounts", coursesCount);
		}
		//contact counts
		ContactBO contactBo=new ContactBO();
		long contactCount=adminService.retrieveContactCount(contactBo);
		if(0!=contactCount){
			model.addAttribute("contactCounts", contactCount);
		}
		return "admin-home";
	}

	@RequestMapping(value = "/company-home", method = RequestMethod.GET)
	public String companyHome(Model model, HttpServletRequest request,HttpSession session) throws Exception {
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		CompanyBO companyBO=new CompanyBO();
		/*if(null!=session.getAttribute("companyId")){
			int id=(int) session.getAttribute("companyid");
			companyBO.setCompanyId(id);
		}*/
		HttpSession sessions=request.getSession();
		CompanyBO company=new CompanyBO();
		if(null!=session.getAttribute("companyId")){
			int id=(int) session.getAttribute("companyId");
			companyBO.setCompanyId(id);
			company=companyService.getCompanyObject(companyBO);
			if(null!=company.getCompanyType())
			{
				sessions.setAttribute("companyType",company.getCompanyType());
			}

		}
		//service counts
		ProductServiceBO ServiceBO=new ProductServiceBO();
		ServiceBO.setCompanyBO(companyBO);
		List<ProductServiceBO>serviceList=productService.listservice(ServiceBO);
		if(null!=serviceList && !serviceList.isEmpty() && serviceList.size()>0){
			long count=serviceList.size();
			model.addAttribute("serviceCounts", count);
		}
		//campign counts
		CampaignBO campaignBO=new CampaignBO();
		campaignBO.setIsDelete(false);
		campaignBO.setSending_status(true);
		campaignBO.setCompanyBO(companyBO);
		long counts=campaignService.getListOfCompanyCampaign(campaignBO);
		if(0!=counts){
			model.addAttribute("campaignCounts", counts);
		}
		//customer counts
		CustomerBO customerBo=new CustomerBO();
		customerBo.setCompanyBO(companyBO);
		List<CustomerBO> customerList=customerService.retriveCustomer(customerBo);
		if(null!=customerList && !customerList.isEmpty() && customerList.size()>0){
			long values=customerList.size();
			model.addAttribute("customerCounts", values);
		}
		//course counts
		CourseBO courseBO=new CourseBO();
		courseBO.setCompanyBO(companyBO);
		long courseCount=courseService.retrieveCourseCompanyCount(courseBO);
		if(0!=courseCount){
			model.addAttribute("courseCounts", courseCount);
		}
		//events counts
		EventsBO eventBO=new EventsBO();
		eventBO.setCompanyBO(companyBO);
		long eventsCounts=eventService.retrieveOfEventsCompanyCount(eventBO);
		if(0!=eventsCounts){
			model.addAttribute("eventsCounts", eventsCounts);
		}
		//events registration
		EventRegisterBO eventRegisterBO= new EventRegisterBO();
		eventRegisterBO.setCompanyBO(companyBO);
		long values=eventService.retrieveOfEventsRegistrationCount(eventRegisterBO);
		if(0!=values){
			model.addAttribute("eventRegistrationCounts", values);
		}
		//course registration
		CourseRegistrationBO courseRegister=new CourseRegistrationBO();
		courseRegister.setCompanyBO(companyBO);
		long val=courseService.retrieveOfCourseRegistrationCount(courseRegister);
		if(0!=val){
			model.addAttribute("courseRegistrationCounts", val);
		}
		return "company-home";
	}

	@RequestMapping(value = "/logouts", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:/home";
	}


	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String homeTwo(Model model, HttpServletRequest request) {
		return "home2";
	}




	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model, HttpServletRequest request) {
		model.addAttribute("contactBo",new ContactBO());
		if(null!=request.getParameter("successMessage")){
			model.addAttribute("successMessage",request.getParameter("successMessage"));
		}
		if(null!=request.getParameter("errorMessage")){
			model.addAttribute("errorMessage",request.getParameter("errorMessage"));
		}
		return "contact";
	}

	@RequestMapping(value="/contact", method = RequestMethod.POST)
	public String postContact(@Valid @ModelAttribute("contactBo") ContactBO contactBo,BindingResult bindingresult, HttpServletRequest request,
			HttpServletResponse response,Model model) throws FileNotFoundException{

		if(bindingresult.hasErrors()){
			return "contact";
		}
		Boolean status;
		if(null!=contactBo){
			if(status=adminService.saveContact(contactBo)){
				model.addAttribute("successMessage",TechbootResourceBundle.getValue("create.contact"));
				return "redirect:/contact";
			}
		}
		return "contact";
	}

	@RequestMapping(value="/view-contact", method=RequestMethod.GET)
	public String viewContact(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		ContactBO contactBo=new ContactBO();
		if(null!=session.getAttribute("adminId")){
			contactBo.setisDelete(false);
			contactBo.setSending_status(true);
			List<ContactBO> contactlist=adminService.viewContact(contactBo);
			if(null!=contactlist && !contactlist.isEmpty() && contactlist.size()>0){
				model.addAttribute("contactlists",contactlist);
				return "view-contact";
			}
		}
		model.addAttribute("errorMessage",TechbootResourceBundle.getValue("error.contact"));
		return "redirect:/contact";

	}

	@RequestMapping(value="/view-contact-details",method=RequestMethod.GET)
	public String getContactDetails(Model model,HttpServletRequest request,HttpSession session){

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		ContactBO contactBo=new ContactBO();
		if(null!=session.getAttribute("adminId")){
			if(null!=request.getParameter("contactId")){
				String values=request.getParameter("contactId");
				int ContactId=Integer.parseInt(values);
				contactBo.setContactId(ContactId);
			}
			contactBo=adminService.getContactDetails(contactBo);
			if(null!=contactBo){
				model.addAttribute("contactBo",contactBo);
				return "view-contact-details";
			}
		}
		return "redirect:/view-contact";

	}

	//Sms Tracking
	@RequestMapping(value="/view-Sms-Tracking",method=RequestMethod.GET)
	public String viewSmsTracking(Model model,HttpServletRequest request,HttpSession session){

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}

		CampaignSmsTrakingBO campaignSmsTrakingBO=new CampaignSmsTrakingBO();
		model.addAttribute("campaignSmsTrakingBO",campaignSmsTrakingBO );
		return "view-Sms-Tracking";
	}

	@RequestMapping(value="/search-sms-tracking",method=RequestMethod.POST)
	public String searchSmsTracking(@ModelAttribute("campaignSmsTrakingBO")CampaignSmsTrakingBO campaignSmsTrakingBO,Model model, HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		model.addAttribute("campaignSmsTrakingBO",new CampaignSmsTrakingBO ());
		List<CampaignSmsTrakingBO> campaignSmsTrackingList=new ArrayList<CampaignSmsTrakingBO>();

		long totalTrackingCount=0;

		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}
		//Second page
		if(null!=request.getParameter("page")){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int startingRecordIndex = paginationPageValues(page, maxRecord);	
		campaignSmsTrakingBO.setRecordIndex(startingRecordIndex);
		campaignSmsTrakingBO.setMaxRecord(maxRecord);
		campaignSmsTrakingBO.setPagination("pagination");
		//Limit Recored 1-10
		campaignSmsTrackingList=adminService.viewSmsTracking(campaignSmsTrakingBO);
		totalTrackingCount=campaignSmsTrackingList.size();
		if(null!=campaignSmsTrackingList && !campaignSmsTrackingList.isEmpty() && campaignSmsTrackingList.size()>0){
			model.addAttribute("campaignSmsTrackingList",
					Pagination.paginationLimitedRecords(page,campaignSmsTrackingList,maxRecord,totalTrackingCount));

			return "view-Sms-Tracking";
		}
		return "redirect:/view-Sms-Tracking";

	}

	private int paginationPageValues(int page, int maxRecord) {
		int pageRecords = 0;
		if (page == 1) {
			pageRecords = 0;
		} else {
			pageRecords = (page - 1) * maxRecord + 1;
			pageRecords = pageRecords - 1;
		}
		return pageRecords;

	}


	@RequestMapping(value="/view-smstarcking-details",method=RequestMethod.GET)
	public String smsTrackingDetails(Model model,HttpServletRequest request,HttpSession session) throws Exception{
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		CampaignSmsTrakingBO campaignSmsTrakingBO=new CampaignSmsTrakingBO();
		if(null!=request.getParameter("trackinId")){
			String id= request.getParameter("trackinId");
			int trackingId=Integer.parseInt(id);
			campaignSmsTrakingBO.setTrackinId(trackingId);
		}
		if(0!=campaignSmsTrakingBO.getTrackinId()){
			CampaignSmsTrakingBO SmsTraking=adminService.smsTrackingDetails(campaignSmsTrakingBO);
			if(null!=SmsTraking){
				model.addAttribute("SmsTraking", SmsTraking);
				return "view-smstracking-details";

			}
		}

		return "redirect:/view-Sms-Tracking";
	}




	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String blog(Model model, HttpServletRequest request) {
		return "blog";
	}

	@ResponseBody
	@RequestMapping(value = "/ajax_news",method=RequestMethod.GET)
	public String newsLettersCreations(@RequestParam String email) throws FileNotFoundException{
		NewslettersBO newslettersBO=new NewslettersBO();
		String message=null;
		if(null!=email){
			newslettersBO.setEmailAddress(email);
			if(adminService.findByEmail(newslettersBO.getEmailAddress())){
				message = TechbootResourceBundle.getValue("Validate.Email");
				return message;
			}
			newslettersBO.setIsDelete(false);
			newslettersBO.setSendingStatus(true);
			newslettersBO.setCreatedDate(new Date());
			NewslettersBO newsletters=adminService.newsLettersCreations(newslettersBO);
			if(null!=newsletters && 0!=newsletters.getNewsLettersId()){
				message = TechbootResourceBundle.getValue("RegisterSuccess");
			}else{
				message = TechbootResourceBundle.getValue("Validate.EnquiryPostPoned");
				return message;
			}
		}
		return message;

	}

	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public String about(Model model, HttpServletRequest request) {
		return "about-us";

	}






	/*@RequestMapping(value = "/event-sigle", method = RequestMethod.GET)
	public String eventSigle(Model model, HttpServletRequest request) {
		return "event-sigle";
	}*/

	/*@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String pageNotFount(Model model, HttpServletRequest request) {
		return "404";
	}*/

	/*@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public String aboutUs(Model model, HttpServletRequest request) {
		return "about-us";
	}*/

	/*@RequestMapping(value = "/forum", method = RequestMethod.GET)
	public String forum(Model model, HttpServletRequest request) {
		return "forum";
	}*/
	/*@RequestMapping(value = "/forum-single", method = RequestMethod.GET)
	public String forumSingle(Model model, HttpServletRequest request) {
		return "forum-single";
	}
	 */
	/*@RequestMapping(value = "/instructors-list", method = RequestMethod.GET)
	public String instructorsList(Model model, HttpServletRequest request) {
		return "instructors-list";
	}*/

	/*@RequestMapping(value = "/instructor-single", method = RequestMethod.GET)
	public String instructorSingle(Model model, HttpServletRequest request) {
		return "instructor-single";
	}*/

	/*@RequestMapping(value = "/login-register", method = RequestMethod.GET)
	public String loginRegister(Model model, HttpServletRequest request) {
		return "login-register";
	}*/



	/*@RequestMapping(value = "/blog-single", method = RequestMethod.GET)
	public String blogSingle(Model model, HttpServletRequest request) {
		return "blog-single";
	}
	 */
	/*@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shop(Model model, HttpServletRequest request) {
		return "shop";
	}

	@RequestMapping(value = "/single-product", method = RequestMethod.GET)
	public String singleProduct(Model model, HttpServletRequest request) {
		return "single-product";
	}

	@RequestMapping(value = "/cartage", method = RequestMethod.GET)
	public String cartage(Model model, HttpServletRequest request) {
		return "cartage";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(Model model, HttpServletRequest request) {
		return "checkout";
	}*/
	@RequestMapping(value = "/list-users", method = RequestMethod.GET)
	public String userList(Model model, HttpServletRequest request,HttpSession session) throws Exception{

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		String paging=null;
		model.addAttribute("usesrLogin",new LoginBO());

		if(null!=request.getParameter("successmessage")) {
			model.addAttribute("successmessage", request.getParameter("successmessage"));
		}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}	
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
		}
		LoginBO loginBO=new LoginBO();
		model.addAttribute("login", loginBO);

		//loginBO.setIsDelete(false);
		//Pagination
		loginPagination(loginBO,paging,model,session);
		return "list-users";
	}
	private void loginPagination(LoginBO loginBO,String paging,Model model,HttpSession session) throws Exception{


		List<LoginBO> pageLoginlist=new ArrayList<LoginBO>();
		LoginBO loginBo=new LoginBO();
		int loginListCount=0;
		long totalUserCount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}

		if(null!=paging){
			page=Integer.parseInt(paging);
		}

		if(null!=loginBO){

			if(null!=session.getAttribute("adminId")){

				loginListCount=adminService.retriveUser(loginBO);
			}
			if(loginListCount>0)
			{
				totalUserCount=loginListCount;
			}

			int startingRecordIndex=paginationPageValues(page,maxRecord);
			loginBo.setRecordIndex(startingRecordIndex);
			loginBo.setMaxRecord(maxRecord);
			loginBo.setPagination("pagination");

			pageLoginlist=adminService.listOfUsers(loginBo);

			if(null!=pageLoginlist && !pageLoginlist.isEmpty() && pageLoginlist.size()>0){
				List<LoginBO> loginlists=new ArrayList<LoginBO>();
				loginlists.addAll(pageLoginlist);
				model.addAttribute("loginlist",
						Pagination.paginationLimitedRecords(page,loginlists,maxRecord,totalUserCount));
			}
		}
	}
	@RequestMapping(value = "/search-user", method = RequestMethod.POST)
	public String searchUser(@ModelAttribute("login") LoginBO loginBO ,Model model, HttpServletRequest request,
			HttpSession session) throws Exception{
		//using
		//model.addAttribute("login",new LoginBO());
		List<LoginBO> userList=new ArrayList<LoginBO>();
		long totalCount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}
		if(null!=loginBO){
			int totalUser=adminService.searchUser(loginBO);
			if(0!= totalUser){
				totalCount=totalUser;
			}
			else{
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
				return  "redirect:/list-users";
			}
			//search pagination
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			loginBO.setRecordIndex(startingRecordIndex);
			loginBO.setMaxRecord(maxRecord);
			loginBO.setPagination("pagination");
			//Limit Recored 1-10
			userList=adminService.searchLoginData(loginBO);
			if(null!=userList && !userList.isEmpty() && userList.size()>0){
				model.addAttribute("loginlist",
						Pagination.paginationLimitedRecords(page, userList, maxRecord, totalCount));
				return "list-users";
			}
		}
		return "list-users";
	}
	
	@RequestMapping(value="/view-user", method=RequestMethod.GET)
	public String viewUser(Model model,HttpServletRequest request,HttpSession session) throws Exception{
		//checking login
		String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}
		LoginBO loginBo=new LoginBO();
		String value=request.getParameter("loginId");
		long id=Long.parseLong(value);
		loginBo.setLoginId(id);



		loginBo=adminService.viewUser(loginBo);
		if(null!=loginBo){
			
			if(loginBo.getUserType().equalsIgnoreCase("student")){
				
				StudentRegisterBO StudentRegisterBO =studentService.getStudentProfile(loginBo.getStudentRegisterBO().getStudentRegisterId());
				if(null!=StudentRegisterBO) {
					model.addAttribute("StudentRegisterBO", StudentRegisterBO);
					return "view-Student";
				}
			}
            if(loginBo.getUserType().equalsIgnoreCase("company")){
            	CompanyBO companyBO=new CompanyBO();
            	companyBO.setCompanyId(loginBo.getCompanyBO().getCompanyId());
            	companyBO=companyService.getCompanyObject(companyBO);
    			if(null!=companyBO) {
    				model.addAttribute("viewCompanyDetails", companyBO);
    				return "view-companydetails";
    			}
				
			}
            if(loginBo.getUserType().equalsIgnoreCase("admin")) {
    			model.addAttribute("adminProfile", loginBo);
    			return "admin-profile";
            }
		}
		return "list-users";

	}

	@RequestMapping(value="/edit-user", method=RequestMethod.GET)
	public String editUser(Model model,HttpServletRequest request,HttpSession session) throws Exception{
		//checking login
		String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}
		LoginBO loginBo=new LoginBO();
		//int editCount=0;
		String value=request.getParameter("loginId");
		long id=Long.parseLong(value);
		loginBo.setLoginId(id);



		loginBo=adminService.editUser(loginBo);
		if(null!=loginBo){
			model.addAttribute("login",loginBo);
			return "edit-user";
		}
		return "list-users";

	}

	@RequestMapping(value="/edit-user",  method=RequestMethod.POST)
	public String editUser(@Valid @ModelAttribute("login") LoginBO loginBo,BindingResult bindingresult,
			HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{

		if(bindingresult.hasErrors()){
			model.addAttribute("login", loginBo);
			return "edit-user";	
		}
		boolean status;

		if(null!=loginBo){
			status=adminService.editUserData(loginBo);
			if(status=false)
			{
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.update"));
				return "redirect:/list-users";
			}
		}
		System.out.println(TechbootResourceBundle.getValue("update.user"));
		model.addAttribute("successmessage",TechbootResourceBundle.getValue("update.user"));
		return "redirect:/list-users";
	}

	@RequestMapping(value="/delete-user", method=RequestMethod.GET)
	public String deleteTestimonial(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		//checking login
		String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}

		boolean status;
		LoginBO loginBo=new LoginBO();
		if(null!=request.getParameter("loginId")){
			String value=request.getParameter("loginId");
			long id=Long.parseLong(value);
			loginBo.setLoginId(id);			
		}

		if(null!=loginBo){
			if(status=adminService.deleteUser(loginBo)){
				model.addAttribute("successmessage",TechbootResourceBundle.getValue("delete.user"));
				return "redirect:/list-users";
			}
		}
		return "redirect:/list-users";

	}@RequestMapping(value="/deletecontact",method=RequestMethod.GET)
	public String deletecontact(@RequestParam(required = false) String contactId, HttpServletRequest req,HttpServletResponse res,Model model) throws ClassNotFoundException, SQLException{

		int i=Integer.parseInt(contactId);  
		int result =adminService.deletecontact(i);
		return "view-contact";
	}

	@RequestMapping(value="/editcontact",method=RequestMethod.GET)
	public String editcontact(@RequestParam(required = false) String contactId, HttpServletRequest req,HttpServletResponse res,Model model) throws ClassNotFoundException, SQLException{
		int i=Integer.parseInt(contactId); 
		ContactBO result =adminService.editcontact(i);
		HttpSession session = req.getSession();
		session.setAttribute("contactId",result.getContactId());
		model.addAttribute("editcontact",result);
		return "editcontact";
	}

	@RequestMapping(value="/editcontact1",method=RequestMethod.POST)
	public String updatecontact(HttpServletRequest req,HttpServletResponse res,@ModelAttribute("editcontact")
	ContactBO bo1,Model model,HttpSession session){

		System.out.println(bo1.getContactId());
		System.out.println(bo1.getEmailAddress());
		System.out.println(bo1.getMessage());
		System.out.println(bo1.getYourName());
		System.out.println(bo1.getContactNumber());
		Integer contatID=(Integer)session.getAttribute("contactId");

		bo1.setContactId(contatID);
		System.out.println(bo1.getContactId());
		int value= adminService.updatecontact(bo1);
		return "view-contact";
	}

}



