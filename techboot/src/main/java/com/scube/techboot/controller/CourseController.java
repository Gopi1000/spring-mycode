package com.scube.techboot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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

import com.scube.techboot.bo.CampaignBO;
import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.CourseCategoryBO;
import com.scube.techboot.bo.CourseDetailsBO;
import com.scube.techboot.bo.CourseRegistrationBO;
import com.scube.techboot.bo.EventsBO;
import com.scube.techboot.bo.MetaTitleBO;
import com.scube.techboot.service.CompanyService;
import com.scube.techboot.service.CourseService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.SaveImagesToFolder;
import com.scube.techboot.utils.TechbootResourceBundle;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.MetaTagVO;


@Controller
public class CourseController {
	@Autowired
	private CourseService courseService;


	@Autowired
	private CompanyService companyservice;



	//URL
	private static final String CREATE_COURSE  ="/create-course";
	private static final String VIEW_COURSE  ="/view-course";
	private static final String EDIT_COURSE="/edit-course";
	private static final String VIEW_COURSE_DETAILS="/view-details-course";
	private static final String DELETE_COURSE="/delete-course";
	private static final String COURSE_LIST  ="/courses-list";
	private static final String COURSE_DETALIS  ="course-details/{courseName}";
	private static final String COURSE_REGISTER  ="register-course";
	private static final String VIEW_COURSE_REGISTRATION ="view-course-registration";
	private static final String COURSE_REGISTRATION_DETAILS ="view-course-details";
	private static final String CREATE_COURSE_DETALIS ="create-course-details";
	private static final String LISTVIEW_COURSE_DETAILS  ="listview-course-details";
	private static final String VIEW_SEARCH_COURSE="view-search-course";
	private static final String VIEW_SEARCHCOURSE_DETAILS="view-searchcourse-details";
	private static final String EDIT_COURSEDETAILS="/edit-courseDetails";
	private static final String DELETE_COURSEDETAILS="/delete-courseDetails";
	private static final String VIEW_COURSEDETAILS_DETAILS="/view-courseDetails-details";
	private static final String EMAILADDRESS_VALIDATIONS="/EmailaddressVerificationss";
	private static final String MOBILENUMBER_VALIDATIONS="/MobileNumberVerificationss";
	private static final String COURSENAME_VALIDATIONS="/courseNameValidations";
	private static final String SEARCH_COURSE_LIST = "/search-course-list";
	private static final String CREATE_COURSE_CATEGORY="/create-course-category";
	private static final String VIEW_COURSE_CATEGORY="/view-course-category";
	private static final String EDIT_COURSE_CATEGORY="/edit-course-category";
	private static final String DELETE_COURSE_CATEGORY="/delete-course-category";
	private static final String GETMATCHED_KEYWORDS="/getMachedKeywords";
	private static final String CHECK_CATEGORYNAME = "/check_courseCategoryName";
	private static final String EDIT_COURSE_REGISTRATION ="/edit_course_registration";
	private static final String DELETE_COURSE_REGISTRATION="/delect_course_registration";
	private static final String LIST_COURSE_REGISTRATION_DETAILS="/view-course-registration-details";
	/*	private static final String VIEW_LIST_OF_COURSE_CATEGORY_SEARCH="/search-courseCategoryName";
	 */	private static final String VIEW_CATEGORY_SEARCH="/search-courseCategoryName";

	 @ModelAttribute("companylist")
	 private List<CompanyBO> listOfCompany(){
		 List<CompanyBO> companylist= new ArrayList<CompanyBO>();
		 CompanyBO companybo=new CompanyBO();
		 companybo.setIsDelete(false);

		 companylist=companyservice.retriveCompany(companybo);
		 return companylist;
	 }



	 @ModelAttribute("courseCategoryList")
	 private List<CourseCategoryBO> listOfCoursecategory(){
		 List<CourseCategoryBO> courseCategoryList= new ArrayList<CourseCategoryBO>();
		 CourseCategoryBO catogry=new CourseCategoryBO();
		 catogry.setIsDelete(false);
		 courseCategoryList=courseService.viewCourseCatogery(catogry);
		 return courseCategoryList;
	 }
	   @ResponseBody
	    @RequestMapping(value=COURSENAME_VALIDATIONS,method=RequestMethod.GET)
	    public String courseNameValidations(@RequestParam String courseName,HttpServletRequest request){
	    	if(null!=courseName && !courseName.isEmpty()){
	    		boolean status=courseService.courseNameValidations(courseName);
	    		if(status){
	    			return "COURSENAME";
	    		}
	    	}
			return null;
	    }
	 @RequestMapping(value=CREATE_COURSE,method=RequestMethod.GET)
	 public String getCource(Model model,HttpServletRequest request,HttpSession session) {
		 String value=CheckingStatus.checkSession(request,session);
		 if(null!=value){
			 return value;
		 }
		 CourseBO courseBO=new CourseBO();
		 model.addAttribute("courseBO",courseBO);
		 return "create-course";
	 }

	 @RequestMapping(value = CREATE_COURSE, method = RequestMethod.POST)
	 public String postCourse(@Valid @ModelAttribute("courseBO") CourseBO courseBO,BindingResult bindingResult,@RequestParam("coursesLogos") MultipartFile courseLogo, HttpServletRequest request,
			 HttpServletResponse response,Model model,HttpSession session) throws SerialException, SQLException, IOException{
		 if(bindingResult.hasErrors()){
			 return "create-course";
		 }
		 /*if(null!=courseBO && null!=courseBO.getCourseName()){
				//is exist's
			 CourseBO CourseBO=new CourseBO();
			CourseBO.setCourseName(courseBO.getCourseName());
				if(courseService.courseNameValidations(CourseBO)){
					model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.courseName"));
				 	return "redirect:/create-course";
				   }
				}*/
		 CompanyBO companyBO=new CompanyBO();
		 if(null!=courseBO && null!=courseBO.getCompanyBO() && null!=courseBO.getCompanyBO().getCompanyName()){
			 String values=courseBO.getCompanyBO().getCompanyName();
			 int companyId=Integer.parseInt(values);
			 companyBO.setCompanyId(companyId);
			 courseBO.setCompanyBO(companyBO);
		 }
		 String imageName=null;
		 int lastCourseId = 0;
		 //logo
		 if(null!=courseBO) {
			 int count=(int)courseService.retriveOfCourse(courseBO);
			 lastCourseId=count;
			 long lastCourseSequenceId=lastCourseId+1;
			 String url="course-url"+lastCourseSequenceId;
			 String imgContentType=courseLogo.getContentType();
			 String temp[] = imgContentType.split("/");
			 imageName = lastCourseSequenceId + "." + temp[1];
			 courseBO.setImageName(imageName);
			 courseBO.setCourseUrl(url);
		 }
		 if(null!=courseBO) {
			 if(null!=session.getAttribute("companyId")){
				 int id=(int) session.getAttribute("companyId");
				 companyBO.setCompanyId(id);
				 courseBO.setCompanyBO(companyBO);
			 }
			 courseBO=courseService.saveCourse(courseBO);
		 }
		 //save local
		 if(null!=courseBO && 0!=courseBO.getCourseId()) {
			 String imgPathName = TechbootResourceBundle.getValue("course.Logo");
			 SaveImagesToFolder.saveImageToFolder(imageName,courseLogo,imgPathName);
		 }
		 model.addAttribute("successMessage",TechbootResourceBundle.getValue("create.course"));
		 return "redirect:/view-course";

	 }
	 @RequestMapping(value=VIEW_COURSE,method=RequestMethod.GET)
	 public String viewCourseList(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException {
		 /*	CourseBO courseBO=new CourseBO();*/
		 if(null!=request.getParameter("successMessage")){
			 model.addAttribute("successMessage",request.getParameter("successMessage"));
		 }
		 CourseBO course=new CourseBO();
		 //Search
		 model.addAttribute("courseObject", course);
		 //Pagination
		 //First page
		 List<CourseBO> courselist=new ArrayList<CourseBO>();
		 int page=1;
		 int maxRecord=0;
		 int recordValue=0;
		 String record=TechbootResourceBundle.getValue("pagination.count");
		 if(null!=record){
			 maxRecord=Integer.parseInt(record);
		 }
		 //Second page
		 if(null!=request.getParameter("page")){
			 page=Integer.parseInt(request.getParameter("page"));
		 }

		 //total count

		 //company

		 if(null!=session.getAttribute("companyId")){
			 CompanyBO companyBO=new CompanyBO();
			 CourseBO courseBO=new CourseBO();
			 int id=(int) session.getAttribute("companyId");
			 companyBO.setCompanyId(id);
			 courseBO.setCompanyBO(companyBO);
			 courselist=courseService.getViewCourseList(courseBO);
		 }
		 else {
			 course.setIsDelete(false);
			 courselist=courseService.getViewCourseList(course);
		 }
		 if(null!=courselist && !courselist.isEmpty() && courselist.size()>0){
			 recordValue=courselist.size();
		 }

		 int StartingRecordIndex=paginationPageValues(page, maxRecord);
		 course.setMaxRecord(maxRecord);
		 course.setRecordIndex(StartingRecordIndex);
		 //CourseList View
		 if(null!=session.getAttribute("companyId")){
			 CompanyBO companyBO=new CompanyBO();
			 CourseBO courseBO=new CourseBO();
			 int id=(int) session.getAttribute("companyId");
			 companyBO.setCompanyId(id);
			 courseBO.setCompanyBO(companyBO);
			 courselist=courseService.getViewCourseList(courseBO);
		 }
		 else {
			 course.setIsDelete(false);
			 courselist=courseService.getViewCourseList(course);
		 }

		 if(null!=courselist && !courselist.isEmpty() && courselist.size()>0){

			 model.addAttribute("courseList",Pagination.paginationLimitedRecords(page, courselist, maxRecord, recordValue));
			 return "view-course";
		 }
		 return "view-course";
	 }

	 @RequestMapping(value= EDIT_COURSE,method = RequestMethod.GET)
	 public String getEditCourse(Model model,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException{
		 CourseBO courseBO=new CourseBO();
		 if(null!=request.getParameter("courseId")){
			 int courseId=Integer.parseInt(request.getParameter("courseId"));
			 courseBO.setCourseId(courseId);
		 }
		 courseBO = courseService.editCourse(courseBO);
		 if(null!=courseBO && null!=courseBO.getCourseName()){
			 model.addAttribute("editCourseBo", courseBO);
			 return "edit-course";
		 }
		 model.addAttribute("errorMessage",TechbootResourceBundle.getValue("error.course"));
		 return "redirect:/create-course";
	 }

	 @RequestMapping(value = EDIT_COURSE,method = RequestMethod.POST)
	 public String postEditCourse(@Valid @ModelAttribute("editCourseBo") CourseBO editCourseBo,BindingResult bindingresult,@RequestParam("coursesLogos") MultipartFile courseLogo, HttpServletRequest request,
			 HttpServletResponse response,Model model,HttpSession session) throws RuntimeException, IOException{

		 if(bindingresult.hasErrors()){
			 return "edit-testimonial";	
		 }
		 boolean status;
		 String imageName  = editCourseBo.getImageName();
		 if(null!=editCourseBo){
			 if(status=courseService.postEditCourse(editCourseBo)){
				 if(null!=courseLogo && !courseLogo.getOriginalFilename().isEmpty()){
					 String imgPathName = TechbootResourceBundle.getValue("course.Logo");
					 SaveImagesToFolder.saveImageToFolder(imageName,courseLogo,imgPathName);
				 }
			 }
		 }
		 model.addAttribute("successMessage", TechbootResourceBundle.getValue("update.course"));
		 return "redirect:/view-course";
	 }

	 @RequestMapping(value = DELETE_COURSE,method=RequestMethod.GET)
	 public String deleteCourse(Model model,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException{
		 CourseBO courseBO=new CourseBO();
		 boolean status;
		 if(null!=request.getParameter("courseId")){
			 courseBO.setCourseId(Integer.parseInt(request.getParameter("courseId")));
		 }
		 if(status=courseService.deleteCourse(courseBO)){
			 model.addAttribute("successMessage", TechbootResourceBundle.getValue("delete.course"));
			 return "redirect:/view-course";
		 }
		 return "redirect:/create-course";
	 }

	 @RequestMapping(value = VIEW_COURSE_DETAILS,method=RequestMethod.GET)
	 public String viewCourseDetails(Model model,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException{
		 CourseBO courseBO=new CourseBO();
		 if(null!=request.getParameter("courseId")){
			 int courseId=Integer.parseInt(request.getParameter("courseId"));
			 courseBO.setCourseId(courseId);
		 }
		 courseBO = courseService.editCourse(courseBO);
		 if(null!=courseBO && null!=courseBO.getCourseName()){
			 model.addAttribute("courseBo", courseBO);
			 return "view-details-course";
		 }
		 model.addAttribute("errorMessage",TechbootResourceBundle.getValue("error.course"));
		 return "redirect:/create-course";
	 }
	 //auto complete
	 @RequestMapping(value = GETMATCHED_KEYWORDS, method = RequestMethod.GET, produces = "application/json")
	 public  @ResponseBody List<MetaTitleBO> getKeywords(@RequestParam String term, HttpServletResponse response) {
		 return simulateSearchResult(term);

	 }

	 private List<MetaTitleBO> simulateSearchResult(String term) {

		 MetaTitleBO metaTitleBO=new MetaTitleBO();
		 metaTitleBO.setMetaTitle(term);
		 List<MetaTitleBO> metalist = courseService.getKeywords(metaTitleBO);
		 return metalist;
	 }
	 //Course list page search

	 @RequestMapping(value = SEARCH_COURSE_LIST,method = RequestMethod.POST)
	 public String searchCourseList(@ModelAttribute("courseObject")CourseBO courseBO,Model model,HttpServletRequest request,HttpSession session ) throws FileNotFoundException{
		 List<CourseBO> courselist=new ArrayList<CourseBO>();
		 model.addAttribute("courseObject", new CourseBO());
		 int totalCourse=0;
		 int page=1;
		 int maxRecord=9;

		 //Second page
		 if(null!=request.getParameter("page")){
			 page=Integer.parseInt(request.getParameter("page"));
		 }

		 if(null!=courseBO){
			 if(null!=courseBO.getCourseName()){
				 MetaTitleBO metaTitleBO=new MetaTitleBO();
				 metaTitleBO.setMetaTitle(courseBO.getCourseName());
				 courseBO.setMetaTitleBO(metaTitleBO);
			 }
			 totalCourse=courseService.searchCourse(courseBO);
		 }
		 int StartingRecordIndex=paginationPageValues(page, maxRecord);
		 courseBO.setMaxRecord(maxRecord);
		 courseBO.setRecordIndex(StartingRecordIndex);
		 courseBO.setIsDelete(false);
		 courselist=courseService.searchPageCourse(courseBO);
		 if(null!=courselist && !courselist.isEmpty() && courselist.size()>0) {
			 session.setAttribute("courselist",Pagination.paginationLimitedRecords(page, courselist, maxRecord, totalCourse));
			 return "courses-list"; 
		 }else{
			 model.addAttribute("errorMessage",TechbootResourceBundle.getValue("course.List"));
			 return "courses-list"; 
		 }

		 /*return "redirect:/create-course";*/
	 }

	 @RequestMapping(value = COURSE_LIST, method = RequestMethod.GET)
	 public String coureseList(Model model, HttpServletRequest request,HttpSession session) throws FileNotFoundException {
		 CourseBO courseBO=new CourseBO();
		 CourseCategoryBO courseCategoryBO=new CourseCategoryBO();
		 List<CourseBO> courselist=new ArrayList<CourseBO>();
		 List<CourseCategoryBO> courseCategoryList=new ArrayList<CourseCategoryBO>();
		 if(null!=request.getParameter("successMessage")){
			 model.addAttribute("successMessage",request.getParameter("successMessage"));
		 }
		 if(null!=request.getParameter("courseCategoryId")){
			 String courseCategory=request.getParameter("courseCategoryId");
			 int categoryId=Integer.parseInt(courseCategory);
			 courseCategoryBO.setCourseCategoryId(categoryId);
			 courseBO.setCourseCategoryBO(courseCategoryBO);
		 }
		 model.addAttribute("courseObject", new CourseBO());
		 int page=1;
		 int recordTotal=0;
		 int maxRecord=9;

		 //Second page
		 if(null!=request.getParameter("page")){
			 page=Integer.parseInt(request.getParameter("page"));
		 }

		 courseBO.setIsDelete(true);
		 courselist=courseService.viewCourseList(courseBO);
		 if(null!=courselist && !courselist.isEmpty() && courselist.size()>0) {
			 recordTotal=courselist.size();
		 }

		 int StartingRecordIndex=paginationPageValues(page, maxRecord);
		 courseBO.setMaxRecord(maxRecord);
		 courseBO.setRecordIndex(StartingRecordIndex);
		 courseBO.setIsDelete(false);
		 courselist=courseService.viewCourseList(courseBO);
		 courseCategoryList = courseService.viewCourseCatogery(courseCategoryBO);
		 if(null!=courselist && !courselist.isEmpty() && courselist.size()>0 && 
				 null!=courseCategoryList && !courseCategoryList.isEmpty() && courseCategoryList.size()>0 ) {

			 model.addAttribute("courselist",Pagination.paginationLimitedRecords(page, courselist, maxRecord, recordTotal));
			 model.addAttribute("courseCategoryList",courseCategoryList);
			 return "courses-list";
		 }
		 model.addAttribute("errorMessage",TechbootResourceBundle.getValue("course.List"));
		 return "courses-list";
	 }

	 @RequestMapping(value= COURSE_REGISTER,method=RequestMethod.GET)
	 public String courseRegister(Model model,HttpServletRequest request,HttpSession session){
		 CourseRegistrationBO courseRegistrationBO=new CourseRegistrationBO();
		 CourseBO courseBO=new CourseBO();
		 if(null!=request.getParameter("InfoMessage")){
			 model.addAttribute("InfoMessage",request.getParameter("InfoMessage"));
		 }
		 if(null!=request.getParameter("courseId")){
			 String course= request.getParameter("courseId"); 
			 int courseid=Integer.parseInt(course);
			 courseBO.setCourseId(courseid);
			 courseBO=courseService.getCourseObject(courseBO);
			 if(null!=courseBO) {
				 courseRegistrationBO.setCourseBO(courseBO);
				 model.addAttribute("courseRegistrationBO", courseRegistrationBO);

				 return "register-course";

			 }
		 }

		 return "redirect:/create-course";
	 }


	 @RequestMapping(value=COURSE_REGISTER,method=RequestMethod.POST)
	 public String createCourseRegister(@Valid @ModelAttribute("courseRegisterBo") CourseRegistrationBO courseRegistrationBO,BindingResult result,HttpServletRequest request,
			 HttpServletResponse response,HttpSession session,Model model) throws FileNotFoundException{
		 if(result.hasErrors()){
			 model.addAttribute("courseRegistrationBO", courseRegistrationBO);
			 return "register-course";
		 }
		 CourseRegistrationBO courseRegistrationBo=new CourseRegistrationBO();
		 //validations
		 boolean status=courseService.isEmailAddressExixts(courseRegistrationBO);
		 if(!status){
			 boolean check=courseService.isMobileNumbaerExixts(courseRegistrationBO);
			 if(check){
				 model.addAttribute("courseId", courseRegistrationBO.getCourseBO().getCourseId());
				 model.addAttribute("InfoMessage", TechbootResourceBundle.getValue("Mobile.exixst"));
				 return "redirect:/register-course";
			 }
			 courseRegistrationBo=courseService.saveCourseRegister(courseRegistrationBO);
			 if(null!=courseRegistrationBo && 0!=courseRegistrationBo.getCourseRegisterId()){
				 model.addAttribute("successMessage",TechbootResourceBundle.getValue("register.course"));
				 return "redirect:/courses-list";
			 }
		 }else{
			 model.addAttribute("courseId", courseRegistrationBO.getCourseBO().getCourseId());
			 model.addAttribute("InfoMessage", TechbootResourceBundle.getValue("Email.exixst"));
			 return "redirect:/register-course";
		 }
		 return "register-course";
	 }

	 @RequestMapping(value = "/search-course", method = RequestMethod.POST)
	 public String searchCourse(@ModelAttribute("courseRegistrationBO") CourseRegistrationBO courseRegistrationBO ,Model model, HttpServletRequest request,
			 HttpSession session) throws Exception{
		 //Course List DropDown
		 CourseBO course=new CourseBO();
		 List<CourseBO> courselist=courseService.viewCourseList(course);
		 if(null!=courselist && !courselist.isEmpty() && courselist.size()>0){
			 model.addAttribute("courselist",courselist);
		 }

		 int page=1;
		 int maxRecord=0;
		 int recordValue=0;
		 String record=TechbootResourceBundle.getValue("pagination.count");
		 if(null!=record){
			 maxRecord=Integer.parseInt(record);
		 }

		 courseRegistrationBO.setDelete(true);
		 List<CourseRegistrationBO> courseRegisterLists=courseService.viewCourseRegistrationList(courseRegistrationBO);
		 if(null!=courseRegisterLists && !courseRegisterLists.isEmpty() && courseRegisterLists.size()>0){
			 recordValue=courseRegisterLists.size();	
		 }
		 int StartingRecordIndex=paginationPageValues(page, maxRecord);
		 courseRegistrationBO.setMaxRecord(maxRecord);
		 courseRegistrationBO.setRecordIndex(StartingRecordIndex);
		 //Passing Id In CourseReg List
		 if(null!=courseRegistrationBO.getCourseBO().getCourseName() && !courseRegistrationBO.getCourseBO().getCourseName().isEmpty()){
			 String coursevalue=courseRegistrationBO.getCourseBO().getCourseName();
			 int courseId=Integer.parseInt(coursevalue);
			 CourseBO courseBO=new CourseBO();
			 courseBO.setCourseId(courseId);
			 courseRegistrationBO.setCourseBO(courseBO);
			 List<CourseRegistrationBO> courseRegisterList=courseService.viewCourseRegistrationList(courseRegistrationBO);
			 if(null!=courseRegisterList && !courseRegisterList.isEmpty() && courseRegisterList.size()>0){
				 model.addAttribute("courseList",Pagination.paginationLimitedRecords(page, courseRegisterList, maxRecord, recordValue));

			 }
		 }
		 return "view-course-registration";
	 }


	 @RequestMapping(value=COURSE_REGISTRATION_DETAILS,method=RequestMethod.GET)
	 public String viewCourseDetail(Model model,HttpServletRequest request,HttpSession session){
		 CourseRegistrationBO courseRegistrationBO=new CourseRegistrationBO();
		 if(null!=request.getParameter("courseRegisterId")){
			 String values=request.getParameter("courseRegisterId");
			 long courseRegisterId=Long.parseLong(values);
			 courseRegistrationBO.setCourseRegisterId(courseRegisterId);
		 }
		 courseRegistrationBO=courseService.viewCourseDetails(courseRegistrationBO);
		 if(null!=courseRegistrationBO){
			 model.addAttribute("courseRegister",courseRegistrationBO);
			 return "view-course-details";
		 }
		 return "view-course-registration";
	 }


	 @RequestMapping(value=VIEW_COURSE_REGISTRATION,method=RequestMethod.GET)
	 public String viewCourseRegistration(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		 String value=CheckingStatus.checkSession(request,session);
		 if(null!=value){
			 return value;
		 }
		 CourseBO courseBO=new CourseBO();
		 model.addAttribute("courseRegistrationBO",new CourseRegistrationBO());
		 //Course List DropDown
		 List<CourseBO> courselist=courseService.viewCourseList(courseBO);
		 if(null!=courselist && !courselist.isEmpty() && courselist.size()>0){
			 model.addAttribute("courselist",courselist);
		 }
		 CourseRegistrationBO courseRegistrationBO=new CourseRegistrationBO();
		 //pagination
		 //First page
		 int page=1;
		 int maxRecord=0;
		 int recordValue=0;
		 String record=TechbootResourceBundle.getValue("pagination.count");
		 if(null!=record){
			 maxRecord=Integer.parseInt(record);
		 }
		 //Secont Page
		 if(null!=request.getParameter("page")){
			 page=Integer.parseInt(request.getParameter("page"));
		 }
		 //company total count
		 if(null!=session.getAttribute("companyId")){
			 CompanyBO companyBo=new CompanyBO();
			 int id=(int) session.getAttribute("companyId");
			 companyBo.setCompanyId(id);
			 courseRegistrationBO.setCompanyBO(companyBo);
			 long count=courseService.retrieveOfCourseRegistrationCount(courseRegistrationBO);
			 if(0!=count){
				 recordValue=(int) count;
			 }
			 //company list
			 List<CourseRegistrationBO> courseRegisterLists=courseService.viewCourseRegistrationList(courseRegistrationBO);
			 if(null!=courseRegisterLists && !courseRegisterLists.isEmpty() && courseRegisterLists.size()>0){
				 recordValue=courseRegisterLists.size();	
			 }
		 }
		 //admin total Count
		 courseRegistrationBO.setDelete(true);
		 List<CourseRegistrationBO> courseRegisterLists=courseService.viewCourseRegistrationList(courseRegistrationBO);
		 if(null!=courseRegisterLists && !courseRegisterLists.isEmpty() && courseRegisterLists.size()>0){
			 recordValue=courseRegisterLists.size();	
		 }
		 int StartingRecordIndex=paginationPageValues(page, maxRecord);
		 courseRegistrationBO.setMaxRecord(maxRecord);
		 courseRegistrationBO.setRecordIndex(StartingRecordIndex);
		 //CourseList View
		 courseRegistrationBO.setDelete(false);
		 List<CourseRegistrationBO> courseRegisterList=courseService.viewCourseRegistrationList(courseRegistrationBO);
		 if(null!=courseRegisterList && !courseRegisterList.isEmpty() && courseRegisterList.size()>0){
			 model.addAttribute("courseList",Pagination.paginationLimitedRecords(page, courseRegisterList, maxRecord, recordValue));
		 }
		 return "view-course-registration";
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
	 } //pagination end

	 @RequestMapping(value=CREATE_COURSE_DETALIS,method=RequestMethod.GET)
	 public String curseDetails(Model model,HttpServletRequest request,HttpSession session) {
		 String value=CheckingStatus.checkSession(request,session);
		 if(null!=value){
			 return value;
		 }
		 CourseDetailsBO courseDetailsBO=new CourseDetailsBO();
		 CourseBO courseBO=new CourseBO();
		 List<CourseBO> courselist=new ArrayList<CourseBO>();
		 //Company
		 if(null!=session.getAttribute("companyId")){
			 CompanyBO companyBO=new CompanyBO();
			 int id=(int) session.getAttribute("companyId");
			 companyBO.setCompanyId(id);
			 courseBO.setCompanyBO(companyBO);
			 courselist=courseService.getcurseDetails(courseBO);

		 }
		 else {
			 courselist=courseService.getcurseDetails(courseBO);
		 }
		 if(null!=courselist && !courselist.isEmpty() && courselist.size()>0) {
			 model.addAttribute("courselist", courselist);
		 }
		 model.addAttribute("courseDetailsBO",courseDetailsBO);
		 return "create-course-details";
	 }

	 @RequestMapping(value=CREATE_COURSE_DETALIS,method=RequestMethod.POST)
	 public String saveCurseDetails(@Valid @ModelAttribute("courseDetailsBO")CourseDetailsBO courseDetailsBO,BindingResult bindingResult,HttpServletRequest request,
			 HttpServletResponse response,Model model,HttpSession session) throws FileNotFoundException{
		 if(bindingResult.hasErrors()){
			 return "create-course-details";
		 }
		 CourseBO courseBO=new CourseBO();
		 CompanyBO companyBO=new CompanyBO();
		 /* if(null!=request.getParameter("company_id")){
			 int company_id=Integer.parseInt(request.getParameter("company_id"));
			 companyBO.setCompanyId(company_id);
			 courseDetailsBO.setCompanyBO(companyBO);
		 }*/ 
		 if(null!=courseDetailsBO){
			 if(null!=session.getAttribute("companyId")){
				 int id=(int) session.getAttribute("companyId");
				 companyBO.setCompanyId(id);
				 courseDetailsBO.setCompanyBO(companyBO);
			 }
			 //admin
			 else{
				 String companyName=courseDetailsBO.getCompanyBO().getCompanyName();
				 int id=Integer.parseInt(companyName);
				 companyBO.setCompanyId(id);
				 courseDetailsBO.setCompanyBO(companyBO);
			 }
		 }
		 if(null!=courseDetailsBO) {
			 String courseDetails=courseDetailsBO.getCurseBO().getCourseName();
			 int courseid=Integer.parseInt(courseDetails);
			 courseBO.setCourseId(courseid);
			 courseDetailsBO.setCurseBO(courseBO);
			 courseDetailsBO=courseService.saveCourseDetails(courseDetailsBO,session);
		 }
		 if(null!=courseDetailsBO && 0!=courseDetailsBO.getCourseDetailsId()){
			 model.addAttribute("successMessage",TechbootResourceBundle.getValue("create.courseDetails"));

		 }
		 return "redirect:/listview-course-details";
	 }


	 @RequestMapping(value=LISTVIEW_COURSE_DETAILS,method=RequestMethod.GET)
	 public String viewCourseDetails(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException {
		 CourseDetailsBO courseDetailsBO=new CourseDetailsBO();
		 if(null!=request.getParameter("successMessage")){
			 model.addAttribute("successMessage",request.getParameter("successMessage"));
		 }
		 List<CourseDetailsBO> courseList=new ArrayList<CourseDetailsBO>();
		 CourseDetailsBO courseDetails=new CourseDetailsBO();
		 model.addAttribute("courseDetailsObject", courseDetails);

		 //pagination
		 //First page

		 int page=1;
		 int maxRecord=0;
		 int recordValue=0;
		 String record=TechbootResourceBundle.getValue("pagination.count");
		 if(null!=record){
			 maxRecord=Integer.parseInt(record);
		 }
		 //Second page
		 if(null!=request.getParameter("page")){
			 page=Integer.parseInt(request.getParameter("page"));
		 }
		 //total count
		 if(null!=session.getAttribute("companyId")){
			 CompanyBO companyBO=new CompanyBO();
			 int id=(int) session.getAttribute("companyId");
			 companyBO.setCompanyId(id);
			 courseDetailsBO.setCompanyBO(companyBO);
			 courseList=courseService.viewCourseDetails(courseDetailsBO);
		 }
		 else {
			 courseDetailsBO.setDelete(false);
			 courseList=courseService.viewCourseDetails(courseDetailsBO);
		 }
		 if(null!=courseList && !courseList.isEmpty() && courseList.size()>0){
			 //model.addAttribute("courselist", courseList);
			 session.setAttribute("courseListAll", courseList);
			 recordValue=courseList.size();	
		 }
		 int StartingRecordIndex=paginationPageValues(page, maxRecord);
		 courseDetailsBO.setMaxRecord(maxRecord);
		 courseDetailsBO.setRecordIndex(StartingRecordIndex);
		 //CourseList View
		 courseDetailsBO.setIsDelete(false);
		 List<CourseDetailsBO> courseLists=courseService.viewCourseDetails(courseDetailsBO);
		 if(null!=courseLists && !courseLists.isEmpty() && courseLists.size()>0){

			 model.addAttribute("courseList",Pagination.paginationLimitedRecords(page, courseLists, maxRecord, recordValue));
			 return "view-course-details";
		 }
		 return "redirect:/create-course-details" ;
	 }

	 @RequestMapping(value=COURSE_DETALIS,method=RequestMethod.GET)
	 public String getCourseDetails(@PathVariable("courseName")String courseName,Model model,HttpServletRequest request,HttpSession session){
		 CourseDetailsBO courseDetailsBO=new CourseDetailsBO();
		 CourseBO courseBO=new CourseBO();
		 List<CourseCategoryBO> courseCategoryList=new ArrayList<CourseCategoryBO>();
		 CourseCategoryBO courseCategoryBO = new CourseCategoryBO();
		 List<MetaTagVO> metaTagList=new ArrayList<MetaTagVO>();
		 courseBO.setCourseName(courseName);
		 courseDetailsBO.setCurseBO(courseBO);
		 courseDetailsBO=courseService.getCourseDetails(courseDetailsBO);
		 if(null!=courseDetailsBO && null!=courseDetailsBO.getCurseBO()&&0!=courseDetailsBO.getCurseBO().getCourseId()) {
			 if(null!=courseDetailsBO.getMetataglist() &&  courseDetailsBO.getMetataglist().size()>0){
				 for(MetaTagVO metaTagVO:courseDetailsBO.getMetataglist()){
					 MetaTagVO metaTag=new MetaTagVO();
					 metaTag.setMetaName(metaTagVO.getMetaName());
					 metaTagList.add(metaTag);
				 }
			 }
			 model.addAttribute("metaTaqLists",metaTagList);
			 model.addAttribute("courseDetailsBO",courseDetailsBO);
			 courseCategoryList = courseService.viewCourseCatogery(courseCategoryBO);
			 if(null!=courseCategoryList && !courseCategoryList.isEmpty() && courseCategoryList.size()>0){
				 model.addAttribute("courseCategoryList", courseCategoryList);
			 }
			 return "course-details";
		 }
		 return "redirect:/home";
	 }

	 @RequestMapping(value=VIEW_SEARCH_COURSE,method=RequestMethod.POST)
	 public String searchCourse(@ModelAttribute("courseObject")CourseBO courseBO,Model model,HttpServletRequest request,HttpSession session ) throws FileNotFoundException {
		 CourseBO course=new CourseBO();
		 List<CourseBO> courseList=new ArrayList<CourseBO>();
		 int totalCourse;
		 long totalCourseCount=0;
		 int page=1;
		 int maxRecord=0;
		 String Record=TechbootResourceBundle.getValue("pagination.count");
		 if(null!=Record){
			 maxRecord=Integer.parseInt(Record);
		 }
		 //company
		 if(null!=session.getAttribute("companyId")) {
			 CompanyBO companyBO=new CompanyBO();
			 int id=(int) session.getAttribute("companyId");
			 companyBO.setCompanyId(id);
			 courseBO.setCompanyBO(companyBO);
			 totalCourse=courseService.searchCourse(courseBO);
		 }
		 //admin
		 else {
			 totalCourse=courseService.searchCourse(courseBO);
		 }

		 if(null!=courseBO) {
			 if(0!=totalCourse) {
				 totalCourseCount=totalCourse;
			 }
			 else{
				 model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
				 return  "redirect:/create-course";
			 }
			 //pagination
			 int startingRecordIndex = paginationPageValues(page, maxRecord);
			 courseBO.setRecordIndex(startingRecordIndex);
			 courseBO.setMaxRecord(maxRecord);
			 courseBO.setPagination("pagination");
			 //Limit Recored 1-10	
			 //Company	
			 if(null!=session.getAttribute("companyId")) {
				 CompanyBO companyBO=new CompanyBO();
				 int id=(int) session.getAttribute("companyId");
				 companyBO.setCompanyId(id);
				 courseBO.setCompanyBO(companyBO);
				 courseList=courseService.searchPageCourse(courseBO);
			 }
			 else {
				 courseList=courseService.searchPageCourse(courseBO);
			 }
			 if(null!=courseList && !courseList.isEmpty() && courseList.size()>0){
				 model.addAttribute("courseList",
						 Pagination.paginationLimitedRecords(page, courseList, maxRecord, totalCourseCount));
			 }
		 }
		 return "view-course";

	 }
	 //View course details page 
	 @RequestMapping(value=VIEW_SEARCHCOURSE_DETAILS,method=RequestMethod.POST)
	 public String searchCourseDetails(@ModelAttribute("courseDetailsObject")CourseDetailsBO courseDetailsBO,Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException {
		 CourseBO course=new CourseBO();
		 if(null!=courseDetailsBO) {
			 String courseDetails=courseDetailsBO.getCurseBO().getCourseName();
			 int courseid=Integer.parseInt(courseDetails);
			 course.setCourseId(courseid);
			 courseDetailsBO.setCurseBO(course);
			 List<CourseDetailsBO> courseLists=courseService.viewCourseDetails(courseDetailsBO);
			 model.addAttribute("courseList", courseLists);

		 }
		 model.addAttribute("searchelement", "searchelement");
		 return "view-course-details";
	 }
	 @RequestMapping(value = EDIT_COURSEDETAILS,method=RequestMethod.GET)
	 public String getEditCourseDetails(Model model,HttpServletRequest request,HttpServletResponse response){
		 CourseDetailsBO courseDetailsBo=new CourseDetailsBO();
		 if(null!=request.getParameter("courseDetailsId")){
			 int courseDetailsId=Integer.parseInt(request.getParameter("courseDetailsId"));
			 courseDetailsBo.setCourseDetailsId(courseDetailsId);
		 }
		 courseDetailsBo=courseService.getEditCourseDetails(courseDetailsBo);
		 if(null!=courseDetailsBo && null!=courseDetailsBo.getCurseBO()){
			 model.addAttribute("courseDetails", courseDetailsBo);
			 return "edit-courseDetails";
		 }
		 return "redirect:/create-course-details";
	 }

	 @RequestMapping(value = EDIT_COURSEDETAILS,method=RequestMethod.POST)
	 public String postEditCourseDetails(@Valid @ModelAttribute("courseDetails") CourseDetailsBO courseDetails,BindingResult bindingresult, HttpServletRequest request,
			 HttpServletResponse response,Model model,HttpSession session) throws FileNotFoundException{
		 boolean status;
		 CompanyBO companyBO=new CompanyBO();
		 if(null!=courseDetails){
			 if(null!=session.getAttribute("companyId")){
				 int id=(int) session.getAttribute("companyId");
				 companyBO.setCompanyId(id);
				 courseDetails.setCompanyBO(companyBO);
			 }
		 if(null!=courseDetails){
			 if(status=courseService.postEditCourseDetails(courseDetails)){
				 model.addAttribute("successMessage", TechbootResourceBundle.getValue("update.courseDetails"));
				 return "redirect:/listview-course-details";
			 }
		   }
		 }
		 return "redirect:/create-course-details";
	 }
	 @RequestMapping(value = DELETE_COURSEDETAILS,method=RequestMethod.GET)
	 public String deleteCourseDetails(Model model,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException{
		 CourseDetailsBO courseDetailsBo=new CourseDetailsBO();
		 boolean status;
		 if(null!=request.getParameter("courseDetailsId")){
			 int courseDetailsId=Integer.parseInt(request.getParameter("courseDetailsId"));
			 courseDetailsBo.setCourseDetailsId(courseDetailsId);
		 }
		 if(status=courseService.deleteCourseDetails(courseDetailsBo)){
			 model.addAttribute("successMessage", TechbootResourceBundle.getValue("delete.courseDetails"));
			 return "redirect:/listview-course-details";
		 }
		 return "redirect:/create-course-details";
	 }

	 @RequestMapping(value = VIEW_COURSEDETAILS_DETAILS,method=RequestMethod.GET)
	 public String viewDetailsOfCourseDetails(Model model,HttpServletRequest request,HttpServletResponse response){
		 CourseDetailsBO courseDetailsBo=new CourseDetailsBO();
		 CompanyBO companyBO=new CompanyBO();
		 boolean status;
		 if(null!=request.getParameter("courseDetailsId")){
			 int courseDetailsId=Integer.parseInt(request.getParameter("courseDetailsId"));
			 courseDetailsBo.setCourseDetailsId(courseDetailsId);
		 }
		 courseDetailsBo=courseService.getEditCourseDetails(courseDetailsBo);
		 if(null!=courseDetailsBo && null!=courseDetailsBo.getCurseBO()){
			 model.addAttribute("courseDetailsBo", courseDetailsBo);
			 return "view-courseDetails-details";
		 }
		 return "redirect:/create-course-details";

	 }

	 @ResponseBody
	 @RequestMapping(value=EMAILADDRESS_VALIDATIONS,method=RequestMethod.GET)
	 public String emailAddressValidations(@RequestParam String emailAddress,String courseId,HttpServletRequest request,
			 HttpServletResponse response){
		 CourseRegistrationBO courseRegistrationBo=new CourseRegistrationBO();
		 CourseBO courseBO=new CourseBO();
		 courseBO.setCourseId(Integer.parseInt(courseId));
		 courseRegistrationBo.setCourseBO(courseBO);
		 courseRegistrationBo.setEmailAddress(emailAddress);
		 boolean status=courseService.isEmailAddressExixts(courseRegistrationBo);
		 if(status){
			 return "OK";
		 }
		 return "NOTOK";
	 }

	 @ResponseBody
	 @RequestMapping(value=MOBILENUMBER_VALIDATIONS,method=RequestMethod.GET)
	 public String mobileNumberValidations(@RequestParam String mobileNumber,String courseId,HttpServletRequest request,
			 HttpServletResponse response){
		 CourseRegistrationBO courseRegistrationBo=new CourseRegistrationBO();
		 CourseBO courseBO=new CourseBO();
		 courseBO.setCourseId(Integer.parseInt(courseId));
		 courseRegistrationBo.setCourseBO(courseBO);
		 courseRegistrationBo.setMobileNumber(Long.parseLong(mobileNumber));
		 boolean status=courseService.isMobileNumbaerExixts(courseRegistrationBo);
		 if(status){
			 return "OK";
		 }
		 return "NOTOK";
	 }
	 @RequestMapping(value=CREATE_COURSE_CATEGORY,method=RequestMethod.GET)
	 public String courseCatogery(Model model,HttpServletRequest request,HttpSession session){ 
		 CourseCategoryBO courseCategoryBO=new CourseCategoryBO();
		 model.addAttribute("courseCategoryBO", courseCategoryBO);
		 return "create-course-category";
	 }

	 @RequestMapping(value=CREATE_COURSE_CATEGORY,method=RequestMethod.POST)
	 public String courseCatogery(@ModelAttribute("courseCategoryBO")CourseCategoryBO courseCategoryBO,BindingResult bindingResult,Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException {
		 if(bindingResult.hasErrors()){
			 return "create-course-category";
		 }

		 courseCategoryBO=courseService.courseCatogery(courseCategoryBO);
		 if(null!=courseCategoryBO && 0!=courseCategoryBO.getCourseCategoryId()){
			 model.addAttribute("successMessage", TechbootResourceBundle.getValue("create.category"));
			 return"redirect:/view-course-category";
		 }
		 model.addAttribute("errorMessage", TechbootResourceBundle.getValue("category.exixsts"));
		 return "create-course-category";
	 }

	 @RequestMapping(value=VIEW_COURSE_CATEGORY,method=RequestMethod.GET)
	 public String viewCourseCatogery(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException {
		 String paging=null;
		 if(null!=request.getParameter("successMessage")){
			 model.addAttribute("successMessage",request.getParameter("successMessage"));
		 }
		 if(null!=request.getParameter("page")){
			 paging=request.getParameter("page");
			 model.addAttribute("categoryValue",new CourseCategoryBO());
		 }
		 CourseCategoryBO categoryBO=new CourseCategoryBO();
		 model.addAttribute("categoryValue",new CourseCategoryBO());
		 courseCatogryPagination(categoryBO,paging,model,session);



		 return "view-course-category";

	 }	
	 private void courseCatogryPagination(CourseCategoryBO categoryBO, String paging, Model model, HttpSession session) throws FileNotFoundException {
		 List<CourseCategoryBO> catgoryList=new ArrayList<CourseCategoryBO>();
		 List<CourseCategoryBO> courseCatgoryList=new ArrayList<CourseCategoryBO>();
		 long count=0;
		 long totalcourseCategoryCount=0;
		 int page=1;
		 int maxRecord=0;
		 String Record=TechbootResourceBundle.getValue("pagination.count");
		 maxRecord=Integer.parseInt(Record);
		 if(null!=paging){
			 page=Integer.parseInt(paging);
		 }
		 if(null!=session.getAttribute("adminId")){
			 count=courseService.retrievecourseCategoryCount(categoryBO);
		 }
		 if(0!=count){
			 totalcourseCategoryCount=count;
		 }
		 int StartingRecordIndex=paginationPageValues(page, maxRecord);
		 categoryBO.setRecordIndex(StartingRecordIndex);
		 categoryBO.setMaxRecord(maxRecord);
		 categoryBO.setPagination("pagination");	
		 /*catgoryList=courseService.viewCourseCatogery(categoryBO);
		if(null!=catgoryList && !catgoryList.isEmpty() && catgoryList.size()>0){
			session.setAttribute("catgoryListAll",catgoryList);
			maxRecord=catgoryList.size();
		}*/
		 /*		if(null!=session.getAttribute("adminId")){
		  */	courseCatgoryList=courseService.viewCourseCatogery(categoryBO);
		  if(null!=courseCatgoryList && !courseCatgoryList.isEmpty() && courseCatgoryList.size()>0){
			  model.addAttribute("courseCatgoryList", Pagination.paginationLimitedRecords(page, courseCatgoryList, maxRecord, totalcourseCategoryCount));
		  }
	 }

	 @RequestMapping(value= EDIT_COURSE_CATEGORY,method = RequestMethod.GET)
	 public String getEditCoursecategory(Model model,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException{
		 CourseCategoryBO categoryBO=new CourseCategoryBO();
		 if(null!=request.getParameter("courseCategoryId")){
			 int categoryId=Integer.parseInt(request.getParameter("courseCategoryId"));
			 categoryBO.setCourseCategoryId(categoryId);
		 }
		 categoryBO = courseService.getEditCoursecategory(categoryBO);
		 if(null!=categoryBO && null!=categoryBO.getCourseCategoryName()){
			 model.addAttribute("editcategoryBO", categoryBO);
			 return "edit-course-category";
		 }
		 model.addAttribute("errorMessage",TechbootResourceBundle.getValue("error.course"));
		 return "redirect:/create-course";
	 }

	 @RequestMapping(value = EDIT_COURSE_CATEGORY,method = RequestMethod.POST)
	 public String postEditCoursecategory(@Valid @ModelAttribute("editcategoryBO") CourseCategoryBO categoryBO,BindingResult bindingresult, HttpServletRequest request,
			 HttpServletResponse response,Model model,HttpSession session) throws RuntimeException, IOException{
		 Boolean success;
		 if(bindingresult.hasErrors()){
			 return "edit-course-category";	
		 }

		 if(null!=request.getParameter("courseCategoryId")){
			 String id=request.getParameter("courseCategoryId");
			 int courseCategoryId=Integer.parseInt(id);
			 categoryBO.setCourseCategoryId(courseCategoryId);
		 }
		 if(0!=categoryBO.getCourseCategoryId()){
			 categoryBO.setIsDelete(false);
			 success=courseService.postEditCoursecategory(categoryBO);
			 if(success){
				 model.addAttribute("sucessMessage", TechbootResourceBundle.getValue("update.category"));
				 return "redirect:/view-course-category";
			 }

		 }
		 return "redirect:/edit-course-category";

	 }


	 @RequestMapping(value = DELETE_COURSE_CATEGORY,method=RequestMethod.GET)
	 public String deleteCourseCategory(Model model,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException{
		 CourseCategoryBO courseCategoryBO=new CourseCategoryBO();
		 boolean status;
		 if(null!=request.getParameter("courseCategoryId")){
			 courseCategoryBO.setCourseCategoryId(Integer.parseInt(request.getParameter("courseCategoryId")));
		 }
		 if(status=courseService.deleteCourseCategory(courseCategoryBO)){
			 model.addAttribute("successMessage", TechbootResourceBundle.getValue("delete.category"));
			 return "redirect:/view-course-category";
		 }
		 return "redirect:/create-course";
	 }

	 @ResponseBody
	 @RequestMapping(value=CHECK_CATEGORYNAME,method=RequestMethod.GET)
	 public String courseCategoryValidations(@RequestParam String categoryName,HttpServletRequest request,
			 HttpServletResponse response){
		 boolean status;
		 CourseCategoryBO courseCategoryBO = new CourseCategoryBO();
		 courseCategoryBO.setCourseCategoryName(categoryName);
		 if(status=courseService.courseCategoryValidations(courseCategoryBO)){
			 return "OK";
		 }
		 return "NOTOK";									
	 }
	 @RequestMapping(value=EDIT_COURSE_REGISTRATION,method = RequestMethod.GET)
	 public String EditCourseRegistration(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 CourseRegistrationBO courseRegistrationBO=new CourseRegistrationBO();
		 if(null!=request.getParameter("coursereg_id")){
			 int coursereg_id=Integer.parseInt(request.getParameter("coursereg_id"));
			 courseRegistrationBO.setCourseRegisterId(coursereg_id);	
		 }
		 courseRegistrationBO =courseService.editCourseRegistration(courseRegistrationBO);		
		 if(null!=courseRegistrationBO){
			 model.addAttribute("editCourseRegistrationBo", courseRegistrationBO);
			 session.setAttribute("CompanyId",courseRegistrationBO.getCompanyBO().getCompanyId());

			 return "edit_course_registration";	  
		 }
		 return "view-course-registration";
	 }

	 @RequestMapping(value =EDIT_COURSE_REGISTRATION,method = RequestMethod.POST)
	 public String postEditCourseRegistration(@ModelAttribute("editCourseRegistrationBo") CourseRegistrationBO courseRegistrationBO,HttpServletRequest request,HttpSession session,Model model) throws Exception{
		 if(null!=request.getParameter("coursereg_id")){
			 String id=request.getParameter("coursereg_id");
			 int coursereg_id=Integer.parseInt(id);
			 courseRegistrationBO.setCourseRegisterId(coursereg_id);	
			 if(null!=session.getAttribute("CompanyId")){
				 int ids=(int) session.getAttribute("CompanyId");
				 CompanyBO companyBO=new CompanyBO();
				 companyBO.setCompanyId(ids);
				 courseRegistrationBO.setCompanyBO(companyBO);	
			 }
		 }
		 courseRegistrationBO=courseService.PostCourseRegistration(courseRegistrationBO);
		 if(null!=courseRegistrationBO){
			 model.addAttribute("editCourseRegistrationBo",courseRegistrationBO);
			 return "view-course-registration";	

		 }
		 return "edit_course_registration";
	 }
	 @RequestMapping(value=DELETE_COURSE_REGISTRATION,method = RequestMethod.GET)
	 public String DelectCourseRegistration(Model model,HttpServletRequest request,HttpServletResponse response){
		 CourseRegistrationBO courseRegistrationBO=new CourseRegistrationBO();
		 if(null!=request.getParameter("coursereg_id")){
			 int coursereg_id=Integer.parseInt(request.getParameter("coursereg_id"));
			 courseRegistrationBO.setCourseRegisterId(coursereg_id);	
		 }
		 boolean check= courseService.DeleteCourseRegistration(courseRegistrationBO);

		 if(check){
			 model.addAttribute("deleteCourseRegistrationBo", courseRegistrationBO);
			 return "view-course-registration";	

		 }
		 return "edit_course_registration";
	 }
	 @RequestMapping(value =LIST_COURSE_REGISTRATION_DETAILS,method=RequestMethod.GET)
	 public String viewRegisterDeatail(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws FileNotFoundException{
		 /*String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}*/
		 CourseRegistrationBO courseRegistrationBO=new CourseRegistrationBO();
		 if(null!=request.getParameter("courseRegisterId")){
			 int courseRegisterId=Integer.parseInt(request.getParameter("courseRegisterId"));
			 courseRegistrationBO.setCourseRegisterId(courseRegisterId);	
		 }
		 courseRegistrationBO=courseService.viewCourseRegister(courseRegistrationBO);
		 if(null!=courseRegistrationBO){
			 model.addAttribute("courseRegistrationBO",courseRegistrationBO);
			 return "view-course-registration-details";
		 }

		 return "redirect:/view-course-registration";

	 }
	 /*@RequestMapping(value=VIEW_LIST_OF_COURSE_CATEGORY_SEARCH,method=RequestMethod.POST)
	public String searchCourseCategory(@ModelAttribute("categorylist")CourseCategoryBO categoryBO,Model model,HttpServletRequest request,HttpSession session ) throws FileNotFoundException {
		List<CourseCategoryBO> courseCatgoryList=new ArrayList<CourseCategoryBO>();
		if(null!=request.getParameter("courseCategoryId")){
			int courseCategoryId=Integer.parseInt(request.getParameter("courseCategoryId"));
			categoryBO.setCourseCategoryId(courseCategoryId);
		   courseCatgoryList=courseService.viewCourseCatogery(categoryBO);
		}
		if(null!=courseCatgoryList){
		   model.addAttribute("courseCatgoryList",courseCatgoryList);
	}
		model.addAttribute("searchelement","searchelement");
		return "view-course-details";

}*/
	 @RequestMapping(value = VIEW_CATEGORY_SEARCH, method = RequestMethod.POST)
	 public String searchCompany(@ModelAttribute("categoryValue") CourseCategoryBO courseCategoryBO ,Model model, HttpServletRequest request,
			 HttpSession session) throws Exception{
		 //using
		 model.addAttribute("categoryValue",new CourseCategoryBO());
		 List<CourseCategoryBO> categoryList=new ArrayList<CourseCategoryBO>();
		 long totalCategorycount=0;
		 int page=1;
		 int maxRecord=0;
		 String Record=TechbootResourceBundle.getValue("pagination.count");
		 if(null!=Record){
			 maxRecord=Integer.parseInt(Record);
		 }
		 if(null!=courseCategoryBO){
			 int totalCategory=courseService.searchCategory(courseCategoryBO);
			 if(0!= totalCategory){
				 totalCategorycount=totalCategory;
			 }
			 else{
				 model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
				 return  "redirect:/view-course-category";
			 }
			 //search pagination
			 int startingRecordIndex = paginationPageValues(page, maxRecord);
			 courseCategoryBO.setRecordIndex(startingRecordIndex);
			 courseCategoryBO.setMaxRecord(maxRecord);
			 courseCategoryBO.setPagination("pagination");
			 //Limit Recored 1-10
			 categoryList=courseService.searchPageCategory(courseCategoryBO);
			 if(null!=categoryList && !categoryList.isEmpty() && categoryList.size()>0){
				 model.addAttribute("courseCatgoryList",
						 Pagination.paginationLimitedRecords(page, categoryList, maxRecord, totalCategorycount));
			 }
		 }
		 return "view-course-category";
	 }
}
