package com.scube.techboot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.CourseDetailsBO;
import com.scube.techboot.bo.EventRegisterBO;
import com.scube.techboot.bo.EventsBO;
import com.scube.techboot.service.CompanyService;
import com.scube.techboot.service.EventService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.SaveImagesToFolder;
import com.scube.techboot.utils.TechbootResourceBundle;

@Controller
public class EventController {
	@Autowired
	private EventService eventService;

	@Autowired
	private CompanyService companyservice;
	//URL
	private static final String CREATE_EVENT="/create-event";
	private static final String VIEW_EVENT="/view-event";
	private static final String EDIT_EVENT="/edit-event";
	private static final String DELETE_EVENT="/delete-event";
	private static final String VIEW_EVENT_DETAILS= "/view-event-details";
	private static final String VIEW_EVENT_LIST="/events-list";
	private static final String CREATE_EVENT_REGISTER="/event-register/{titleName}";
	private static final String VIEW_EVENT_REGISTRATION="/view-events-registration";
	private static final String EMAILADDRESS_VALIDATIONS="/EmailaddressVerifications";
	private static final String MOBILENUMBER_VALIDATIONS="/MobileNumberVerifications";
	private static final String EVENT_REGISTRATION_DETAILS="/view-eventregistration-details";
	private static final String SEARCH_EVENT_REGISTRATION="/search-event-registration";
	private static final String VIEW_LIST_OF_EVENTS_SEARCH="/search-event";

	@ModelAttribute("companylist")
	private List<CompanyBO> listOfCompany(){
		List<CompanyBO> companylist= new ArrayList<CompanyBO>();
		CompanyBO companybo=new CompanyBO();
		companybo.setIsDelete(false);
		companylist=companyservice.retriveCompany(companybo);
		return companylist;
	}

	@RequestMapping(value =CREATE_EVENT, method = RequestMethod.GET)
	public String getEvent(Model model,HttpServletRequest request,HttpSession session) {

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		if(null!=request.getParameter("errorMessage")){
			model.addAttribute("errorMessage",request.getParameter("error.event"));
		}

		EventsBO eventsBO=new EventsBO();
		model.addAttribute("eventsBO",eventsBO);

		return "create-event";
	}

	@RequestMapping(value =CREATE_EVENT, method = RequestMethod.POST)
	public String postEvent(@Valid @ModelAttribute("eventsBO") EventsBO eventsBo,BindingResult result,@RequestParam("eventsLogos") MultipartFile evetsLogo, HttpServletRequest request,
			HttpServletResponse response,Model model,HttpSession session) throws SerialException, SQLException, IOException{

		if(result.hasErrors()){
			return "create-event";
		}
		EventsBO eventsBO=new EventsBO();
		CompanyBO companyBO=new CompanyBO();
		if(null!=eventsBo && null!=eventsBo.getCompanyBO() && null!=eventsBo.getCompanyBO().getCompanyName()){
			String values = eventsBo.getCompanyBO().getCompanyName();
			int id=Integer.parseInt(values);
			companyBO.setCompanyId(id);
			eventsBo.setCompanyBO(companyBO);
		}
		String imageName=null;
		long lasteventId = 0;
		//logo
		if(null!=eventsBo) {
			long count=(long)eventService.retriveOfEvent(eventsBo);
			if(0!=count) 
				lasteventId=count;
			long lastEventSequenceId=lasteventId+1;
			String imgContentType=evetsLogo.getContentType();
			String temp[] = imgContentType.split("/");
			imageName = lastEventSequenceId + "." + temp[1];
			eventsBo.setImageName(imageName);
		}
		if(null!=eventsBo) {
			if(null!=session.getAttribute("companyId")){
				int companyId= (int) session.getAttribute("companyId");
				companyBO.setCompanyId(companyId);
				eventsBo.setCompanyBO(companyBO);
			}
			eventsBO=eventService.saveEvent(eventsBo);
		}
		//save local
		if(null!=eventsBO  && 0!=eventsBO.getEventId()){
			String imgPathName = TechbootResourceBundle.getValue("event.Logo");
			SaveImagesToFolder.saveImageToFolder(imageName,evetsLogo,imgPathName);
		}
		model.addAttribute("successMessage", TechbootResourceBundle.getValue("create.event"));
		return "redirect:/view-event";
	}


	@RequestMapping(value=VIEW_EVENT,method=RequestMethod.GET)
	public String viewListEvent(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException, ParseException{

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		String paging=null;
		if(null!=request.getParameter("successMessage")){
			model.addAttribute("successMessage",request.getParameter("successMessage"));
			model.addAttribute("eventDetailsObject",new EventsBO());
		}
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
		}
		EventsBO eventBO=new EventsBO();
		UpcomingEventsPagination(eventBO,paging,model,session);
		model.addAttribute("eventDetailsObject",new EventsBO());
		return "view-event";
	}
	@RequestMapping(value=VIEW_LIST_OF_EVENTS_SEARCH,method=RequestMethod.POST)
	public String searchCourseDetails(@ModelAttribute("eventDetailsObject")EventsBO eventsBO,Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException {
		model.addAttribute("eventDetailsObject",new EventsBO());
		 List<EventsBO> eventList=new ArrayList<EventsBO>();
		 long totalCustomercount=0;
		 int page=1;
		 int maxRecord=0;
		 String Record=TechbootResourceBundle.getValue("pagination.count");
		 if(null!=Record){
			 maxRecord=Integer.parseInt(Record);
		 }
		 if(null!=eventsBO){
			 int totalEventName=eventService.searchEventName(eventsBO);
			 if(0!= totalEventName){
				 totalCustomercount=totalEventName;
			 }
			 else{
				 model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
				 return  "redirect:/view-event";
			 }
			 //search pagination
			 int startingRecordIndex = paginationPageValues(page, maxRecord);
			 eventsBO.setRecordIndex(startingRecordIndex);
			 eventsBO.setMaxRecord(maxRecord);
			 eventsBO.setPagination("pagination");
			 //Limit Recored 1-10upcomingeventlist
			 eventList=eventService.searchPageEvent(eventsBO);
			 if(null!=eventList && !eventList.isEmpty() && eventList.size()>0){
				 model.addAttribute("upcomingeventlist",
						 Pagination.paginationLimitedRecords(page, eventList, maxRecord, totalCustomercount));

			 }
		 }
		 return "view-event";
	}

	private void UpcomingEventsPagination(EventsBO eventBO, String paging, Model model, HttpSession session) throws FileNotFoundException, ParseException {
		// TODO Auto-generated method stub
		long count=0;
		long totalEventsCount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		maxRecord=Integer.parseInt(Record);
		List<EventsBO> eventlist=new ArrayList<EventsBO>();
		if(null!=paging){
			page=Integer.parseInt(paging);
		}
		if(null!=session.getAttribute("companyId")){
			int id=(int) session.getAttribute("companyId");
			CompanyBO companyBO=new CompanyBO();
			companyBO.setCompanyId(id);
			eventBO.setCompanyBO(companyBO);
			count=eventService.retrieveOfEventsCompanyCount(eventBO);
		}
		if(null!=session.getAttribute("adminId")) {
			count=eventService.retrieveOfEventsCount(eventBO);
		}
		if(0!=count){
			totalEventsCount=count;
		}
		int StartingRecordIndex=paginationPageValues(page, maxRecord);
		eventBO.setRecordIndex(StartingRecordIndex);
		eventBO.setMaxRecord(maxRecord);
		eventBO.setPagination("pagination");
		//company list
		if(null!=session.getAttribute("companyId")){
			int id=(int) session.getAttribute("companyId");
			CompanyBO companyBO=new CompanyBO();
			companyBO.setCompanyId(id);
			eventBO.setCompanyBO(companyBO);
			eventlist=eventService.viewListEvent(eventBO,session);
		}
		//admin list
		if(null!=session.getAttribute("adminId")){
			eventlist= eventService.viewListEvent(eventBO,session);
		}
		if(null!=eventlist && !eventlist.isEmpty() && eventlist.size()>0){
			model.addAttribute("upcomingeventlist",Pagination.paginationLimitedRecords(page, eventlist, maxRecord, totalEventsCount));
		}
		else{
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.event"));
		}
	}

	private int paginationPageValues(int pageid, int recordPerPage) {
		// TODO Auto-generated method stub
		int pageRecords = 0;
		if (pageid == 1) {
			pageRecords = 0;
		} else {
			pageRecords = (pageid - 1) * recordPerPage + 1;
			pageRecords = pageRecords - 1;
		}
		return pageRecords;
	}

	@RequestMapping(value=EDIT_EVENT,method=RequestMethod.GET)
	public String getEditEvent(Model model,HttpServletRequest request,HttpSession session)throws FileNotFoundException, ParseException{

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		EventsBO eventBO=new EventsBO();
		if(null!=request.getParameter("eventId")) {
			String values=request.getParameter("eventId");
			long id=Long.parseLong(values);
			eventBO.setEventId(id);
		}
		eventBO=eventService.getEventObjects(eventBO);
		if(null!=eventBO) {
			model.addAttribute("event",eventBO);
			return "edit-event";
		}
		return "create-event";

	}
	@RequestMapping(value=EDIT_EVENT,method=RequestMethod.POST)
	public String postEditEvent(@Valid @ModelAttribute("event")EventsBO eventBO,BindingResult result,@RequestParam("eventsLogos") MultipartFile evetsLogo, HttpServletRequest request,
			HttpServletResponse response,Model model,HttpSession session) throws RuntimeException, IOException{
		boolean status;
		CompanyBO companyBO=new CompanyBO();
		String imageName=eventBO.getImageName();
		if(null!=request.getParameter("eventId")){
			String value=request.getParameter("eventId");
			long id=Long.parseLong(value);
			eventBO.setEventId(id);

		}
		if(null!=eventBO){
			if(null==eventBO.getCompanyBO()||eventBO.getCompanyBO().getCompanyId()==0){
				if(null!=session.getAttribute("companyId")){
					int companyId= (int) session.getAttribute("companyId");
					companyBO.setCompanyId(companyId);
					eventBO.setCompanyBO(companyBO);
				}}
			if(status=eventService.postEditEvent(eventBO)) {
				if(null!=evetsLogo && !evetsLogo.getOriginalFilename().isEmpty()){
					String imgPathName = TechbootResourceBundle.getValue("event.Logo");
					SaveImagesToFolder.saveImageToFolder(imageName,evetsLogo,imgPathName);
				}
			}
		}
		model.addAttribute("successMessage",TechbootResourceBundle.getValue("update.event"));
		return "redirect:/view-event";
	}


	@RequestMapping(value=DELETE_EVENT, method=RequestMethod.GET)
	public String deleteevent(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		boolean status;
		EventsBO eventBo=new EventsBO();
		if(null!=request.getParameter("eventId")){
			String values=request.getParameter("eventId");
			long id=Long.parseLong(values);
			eventBo.setEventId(id);
		}
		if(null!=eventBo){
			if(status=eventService.deleteEvent(eventBo)){
				model.addAttribute("successMessage",TechbootResourceBundle.getValue("delete.event"));
				return "redirect:/view-event";
			}
		}
		return "redirect:/view-event";

	}

	@RequestMapping(value =VIEW_EVENT_DETAILS, method = RequestMethod.GET)
	public String viewDetailsCustomer(Model model, HttpServletRequest request,HttpSession session) throws ParseException{

		EventsBO eventsBO=new EventsBO();


		if(null!=request.getParameter("eventId")){
			String id=request.getParameter("eventId");
			int eventid=Integer.parseInt(id);
			eventsBO.setEventId(eventid);
		}
		if(0!=eventsBO.getEventId()){
			eventsBO=eventService.getEventObjects(eventsBO);
		}
		if(null!=eventsBO){
			model.addAttribute("viewevent", eventsBO);
			return "event-viewdetails";
		}
		return "redirect:/view-event";
	}



	@RequestMapping(value=VIEW_EVENT_LIST,method=RequestMethod.GET)
	public String listOfEvents(Model model,HttpServletRequest request,HttpSession session)throws FileNotFoundException, ParseException{

		EventsBO eventBO=new EventsBO();
		List<EventsBO> eventlist=new ArrayList<EventsBO>();
		if(null!=request.getParameter("successMessage")){
			model.addAttribute("successMessage",request.getParameter("create.event"));
		}
		// event list pagination
		int page=1;
		int recordTotal=0;
		int maxRecord=7;
		//Second page
		if(null!=request.getParameter("page")){
			page=Integer.parseInt(request.getParameter("page"));
		}
		eventBO.setIsDelete(true);
		eventlist= eventService.viewListEvent(eventBO,session);
		if(null!=eventlist && !eventlist.isEmpty() && eventlist.size()>0){
			recordTotal=eventlist.size();
		}
		int StartingRecordIndex=paginationPageValues(page, maxRecord);
		eventBO.setMaxRecord(maxRecord);
		eventBO.setRecordIndex(StartingRecordIndex);
		eventBO.setIsDelete(false);
		eventlist= eventService.viewListEvent(eventBO,session);
		if(null!=eventlist && !eventlist.isEmpty() && eventlist.size()>0) {
			model.addAttribute("eventlists",Pagination.paginationLimitedRecords(page, eventlist, maxRecord, recordTotal));
			return "events-list";
		}
		model.addAttribute("errorMessage",TechbootResourceBundle.getValue("error.event"));
		return "redirect:/create-event";

	}

	@RequestMapping(value=CREATE_EVENT_REGISTER,method=RequestMethod.GET)
	public String createEventRegister(@PathVariable("titleName")String titleName,Model model,HttpServletRequest request,HttpSession session) throws ParseException{

		EventsBO eventBO=new EventsBO();
		if(null!=request.getParameter("eventId")){
			eventBO.setEventId(Long.parseLong(request.getParameter("eventId")));
			eventBO=eventService.getEventObjects(eventBO);
		}
		if(null!=eventBO){
			EventRegisterBO eventRegisterBO=new EventRegisterBO();
			eventRegisterBO.setEventBo(eventBO);
			model.addAttribute("eventRegisterBo",eventRegisterBO);
			return "event-register";
		}
		return "create-event";
	}

	@RequestMapping(value=CREATE_EVENT_REGISTER,method=RequestMethod.POST)
	public String createEventRegister(@Valid @ModelAttribute("eventRegisterBo") EventRegisterBO eventRegisterBO,BindingResult result,HttpServletRequest request,
			HttpServletResponse response,HttpSession session,Model model) throws FileNotFoundException{

		if(result.hasErrors()){
			return "event-register";
		}
		CompanyBO companyBo=new CompanyBO();
		if(null!=eventRegisterBO.getEventBo().getCompanyBO()){
			int id=eventRegisterBO.getEventBo().getCompanyBO().getCompanyId();
			companyBo.setCompanyId(id);
			eventRegisterBO.setCompanyBO(companyBo);
		}
		EventRegisterBO eventRegisterBo=new EventRegisterBO();
		eventRegisterBo=eventService.saveEventRegister(eventRegisterBO);
		if(null!=eventRegisterBo && 0!=eventRegisterBo.getEventRegisterId()){
			model.addAttribute("successMessage",TechbootResourceBundle.getValue("register.event"));
			return "redirect:/home";
		}
		return "event-register";
	}

	@RequestMapping(value=VIEW_EVENT_REGISTRATION,method=RequestMethod.GET)
	public String viewEventRegistration(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException, ParseException{
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		EventsBO eventBO=new EventsBO();
		CompanyBO companyBo=new CompanyBO();
		if(null!=session.getAttribute("companyId")){
			int companyId=(int) session.getAttribute("companyId");
			companyBo.setCompanyId(companyId);
			eventBO.setCompanyBO(companyBo);
		}
		List<EventsBO> eventslists=eventService.retrieveCurrentEvents(eventBO);
		if(null!=eventslists && !eventslists.isEmpty() && eventslists.size()>0){
			model.addAttribute("eventslists",eventslists);
		}
		List<EventsBO> eventlist=eventService.retrievePastEvents(eventBO);
		if(null!=eventlist && !eventlist.isEmpty() && eventlist.size()>0){
			model.addAttribute("eventslist",eventlist);
		}
		//using
		model.addAttribute("eventRegisterBo",new EventRegisterBO());
		EventRegisterBO eventRegisterBO= new EventRegisterBO(); 
		//using the method Search Event register
		if(null!=session.getAttribute("pastId") || null!=session.getAttribute("upcomingId")){
			long totalRegisterCount=0;
			int page=1;
			int maxRecord=0;
			String Record=TechbootResourceBundle.getValue("pagination.count");
			if(null!=Record){
				maxRecord=Integer.parseInt(Record);
			}
			String paging=null;
			if(null!=request.getParameter("page")){
				paging=request.getParameter("page");
				page=Integer.parseInt(paging);
			}
			if(null!=session.getAttribute("pastId")){
				int eventId=(int) session.getAttribute("pastId");
				eventBO.setEventId(eventId);
				eventRegisterBO.setEventBo(eventBO);
				List<EventRegisterBO> pasteventlist=eventService.totalEventsCount(eventRegisterBO);
				if(null!=pasteventlist && !pasteventlist.isEmpty() && pasteventlist.size()>0){
					totalRegisterCount=pasteventlist.size();
					//Second search page
					model.addAttribute("searchvalue", session.getAttribute("pastId"));
				}
			}
			else{
				int eventId= (int) session.getAttribute("upcomingId");
				eventBO.setEventId(eventId);
				eventRegisterBO.setEventBo(eventBO);
				List<EventRegisterBO> currenteventlist=eventService.totalEventsCount(eventRegisterBO);
				if(null!=currenteventlist && !currenteventlist.isEmpty() && currenteventlist.size()>0){
					totalRegisterCount=currenteventlist.size();
					//Second search page
					model.addAttribute("searchvalue", session.getAttribute("upcomingId"));
				}
			}
			int StartingRecordIndex=paginationPageValues(page, maxRecord);
			eventRegisterBO.setRecordIndex(StartingRecordIndex);
			eventRegisterBO.setMaxRecord(maxRecord);
			List<EventRegisterBO> registerlist=eventService.searchEvents(eventRegisterBO);
			if(null!=registerlist && !registerlist.isEmpty() && registerlist.size()>0){
				session.removeAttribute("pastId");
				session.removeAttribute("upcomingId");
				model.addAttribute(model.addAttribute("eventregistrationlist",Pagination.paginationLimitedRecords(page, registerlist, maxRecord, totalRegisterCount)));
				return "view-register-event";
			}
		}
		else{
			String paging=null;
			if(null!=request.getParameter("page")){
				paging=request.getParameter("page");
			}
			EventRegisterBO eventRegisterBo=new EventRegisterBO();
			EventsRegistrationPagination(eventRegisterBo,paging,model,session);
			return "view-register-event";

		}
		return "event-register";
	}

	private void EventsRegistrationPagination(EventRegisterBO eventRegisterBo, String paging, Model model,
			HttpSession session) throws FileNotFoundException {
		// TODO Auto-generated method stub
		long count=0;
		long totalEventsRegistrationCount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		maxRecord=Integer.parseInt(Record);
		if(null!=paging){
			page=Integer.parseInt(paging);
		}
		if(null!=session.getAttribute("companyId")){
			CompanyBO companyBo=new CompanyBO();
			int companyId=(int) session.getAttribute("companyId");
			companyBo.setCompanyId(companyId);
			eventRegisterBo.setCompanyBO(companyBo);
			count=eventService.retrieveOfEventsRegistrationCount(eventRegisterBo);
		}
		if(null!=session.getAttribute("adminId")){
			count=eventService.retrieveOfEventsRegistrationCount(eventRegisterBo);
		}
		if(0!=count){
			totalEventsRegistrationCount=count;
		}
		List<EventRegisterBO> registerlist=new ArrayList<EventRegisterBO>();
		int StartingRecordIndex=paginationPageValues(page, maxRecord);
		eventRegisterBo.setRecordIndex(StartingRecordIndex);
		eventRegisterBo.setMaxRecord(maxRecord);
		if(null!=session.getAttribute("companyId")){
			CompanyBO companyBo=new CompanyBO();
			int companyId=(int) session.getAttribute("companyId");
			companyBo.setCompanyId(companyId);
			eventRegisterBo.setCompanyBO(companyBo);
		}
		if(null!=session.getAttribute("adminId")){
			registerlist=eventService.viewEventRegistration(eventRegisterBo);
		}
		registerlist=eventService.viewEventRegistration(eventRegisterBo);
		if(null!=registerlist && !registerlist.isEmpty() && registerlist.size()>0){
			model.addAttribute(model.addAttribute("eventregistrationlist",Pagination.paginationLimitedRecords(page, registerlist, maxRecord, totalEventsRegistrationCount)));
		}
	}
	@ResponseBody
	@RequestMapping(value=EMAILADDRESS_VALIDATIONS,method=RequestMethod.GET)
	public String emailValidations(@RequestParam String emailAddress,String eventId,HttpServletRequest request,Model model){
		EventsBO eventBO=new EventsBO();
		EventRegisterBO eventRegisterBo=new EventRegisterBO();
		long eventsId=Long.parseLong(eventId);
		eventBO.setEventId(eventsId);
		eventRegisterBo.setEventBo(eventBO);
		eventRegisterBo.setEmailAddress(emailAddress);
		EventRegisterBO eventRegisterBO=eventService.emailValidations(eventRegisterBo);
		if(null!=eventRegisterBO){
			String emailaddress=eventRegisterBO.getEmailAddress();
			return emailaddress;
		}
		return null;

	}

	@ResponseBody
	@RequestMapping(value=MOBILENUMBER_VALIDATIONS,method=RequestMethod.GET)
	public String mobileNumberValidations(@RequestParam String mobileNumber,String eventId,HttpServletRequest request,Model model){
		if(null!=mobileNumber && !mobileNumber.isEmpty()){
			long mobilenumber=Long.valueOf(mobileNumber);
			EventsBO eventBO=new EventsBO();
			EventRegisterBO eventRegisterBo=new EventRegisterBO();
			long eventsId=Long.parseLong(eventId);
			eventBO.setEventId(eventsId);
			eventRegisterBo.setEventBo(eventBO);
			eventRegisterBo.setMobileNumber(mobilenumber);
			EventRegisterBO eventRegisterBO=eventService.mobileNumberValidations(eventRegisterBo);
			if(null!=eventRegisterBO){
				long mobile=eventRegisterBO.getMobileNumber();
				String MobileNumber=String.valueOf(mobile);
				return MobileNumber;
			}
		}
		return null;

	}

	@RequestMapping(value=EVENT_REGISTRATION_DETAILS,method=RequestMethod.GET)
	public String ViewEventRegistrationDetails(Model model,HttpServletRequest request,HttpSession session){

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		EventRegisterBO eventRegisterBo=new EventRegisterBO();
		if(null!=request.getParameter("eventRegisterId")){
			String values=request.getParameter("eventRegisterId");
			int eventRegisterId=Integer.parseInt(values);
			eventRegisterBo.setEventRegisterId(eventRegisterId);
		}
		eventRegisterBo=eventService.ViewEventRegistrationDetails(eventRegisterBo);
		if(null!=eventRegisterBo){
			model.addAttribute("eventRegisterBo",eventRegisterBo);
			return "event-registration-details";
		}
		return "view-register-event";
	}

	@RequestMapping(value=SEARCH_EVENT_REGISTRATION,method=RequestMethod.POST)
	public String searchEvents(@ModelAttribute("eventRegisterBo") EventRegisterBO eventRegisterBO,HttpServletRequest request,
			HttpSession session,Model model){

		if(null!=eventRegisterBO.getEventBo().getTitleName() && !eventRegisterBO.getEventBo().getTitleName().isEmpty()){
			String value=eventRegisterBO.getEventBo().getTitleName();
			int EventId=Integer.parseInt(value);
			session.setAttribute("pastId", EventId);
		}
		if(null!=eventRegisterBO.getEventBo().getEventsName() && !eventRegisterBO.getEventBo().getEventsName().isEmpty()){
			String value=eventRegisterBO.getEventBo().getEventsName();
			int EventId=Integer.parseInt(value);
			session.setAttribute("upcomingId", EventId);
		}
		return "redirect:/view-events-registration";
	}

}
