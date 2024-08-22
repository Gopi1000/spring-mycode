package com.scube.techboot.controller;



import java.io.FileNotFoundException;
import java.util.ArrayList;
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

import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.CustomerBO;
import com.scube.techboot.bo.FeedbackBO;
import com.scube.techboot.bo.StudentCouresBO;
import com.scube.techboot.bo.StudentRegisterBO;
import com.scube.techboot.service.CourseService;
import com.scube.techboot.service.StudentService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.Dropdownutils;
import com.scube.techboot.utils.TechbootResourceBundle;


@Controller
public class StudentController extends ControllerUtils {
	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;
	//URL
	private static final String STUDENT_REGISTER="/student-register";
	private static final String EMAILADDRESS_VALIDATION="/emailAddressVerification";
	private static final String MOBILENO_VALIDATION="/mobileNoVerification";
	private static final String VIEW_STUDENT="/view-student";
	private static final String EDIT_COURSE_ENROLLMENT="/edit-course-enrollment";
	private static final String  DELETE_COURSE_ENROLLMENT="/delete-course-enrollment";
	//my modification
	private static final String SEARCH_STUDENT="/search-student";
	private static final String EDIT_STUDENT="/edit-student";
	private static final String DELETE_STUDENT="/delete-student";
	//

	@RequestMapping(value =STUDENT_REGISTER, method = RequestMethod.GET)
	public String studentRegister(Model model,HttpServletRequest request,HttpSession session) {
		
		getCity(model);
		getstate(model);
		getLanguage(model);
		getmaritalStatus(model);
		getGender(model);
		getCourse(model);


		model.addAttribute("studentRegisterBO",new StudentRegisterBO());
		
		
		if(null!=getUserRole() && getUserRole().equalsIgnoreCase("admin")) {
			return "student-register-admin";
		}

		return "student-register";
	}

	private void getCourse(Model model) {
		CourseBO courseBO=new CourseBO();
		courseBO.setIsDelete(false);
		List<CourseBO> courselist=courseService.getViewCourseList(courseBO);
		model.addAttribute("courselist", courselist);
	}

	private void getStudents(Model model) {
		StudentRegisterBO student = new StudentRegisterBO();
		student.setIsDelete(false);
		student.setSending_status(true);
		List<StudentRegisterBO> studentList = studentService.studentList(student);
		if(null!=studentList && !studentList.isEmpty() && studentList.size()>0) {
			model.addAttribute("studentList", studentList);
		}

	}


	@RequestMapping(value ="student-home", method = RequestMethod.GET)
	public String studentHome(Model model,HttpServletRequest request,HttpSession session) {
		
		model.addAttribute("userType", getUserRole());
		//Enrollment count	
		long totalEnrollmentcount=0;
		long totalEnrollementRecordCount=0;
		long studentId=(long) session.getAttribute("studentId");
		List<StudentCouresBO> list=studentService.getStudentCouresList(studentId);
		totalEnrollementRecordCount=list.size();
		totalEnrollmentcount=totalEnrollementRecordCount;
		model.addAttribute("enrollmentCounts", totalEnrollmentcount);
		return "student-home";
	}



	@RequestMapping(value = STUDENT_REGISTER, method = RequestMethod.POST)
	public String saveStudent(@Valid  @ModelAttribute("studentRegisterBO") StudentRegisterBO studentRegisterBO, BindingResult bindingResult, Model model, HttpServletRequest request,HttpSession session) throws Exception{

		if(bindingResult.hasErrors()){
			if(null!=session.getAttribute("adminId")){
				return "student-register-admin";
			}else{
				return "student-register";
			}
		}
		
		if(null!=session.getAttribute("adminId")){
			long adminId= (long) session.getAttribute("adminId");//
			studentRegisterBO.setCreatedBy(adminId);
		}
		
		if(null!=studentRegisterBO) {
			studentRegisterBO=studentService.saveStudent(studentRegisterBO);
		}
		if(null!=studentRegisterBO) {
			model.addAttribute("studentRegisterBO", studentRegisterBO);

		}
		
		if(null!=getUserRole() && getUserRole().equalsIgnoreCase("admin")) {
			return "redirect:/view-student-register";
		}
		return "redirect:/sign-in";
	}
	
	
	@RequestMapping(value ="/view-student-register", method = RequestMethod.GET)
	public String viewStudentRegister(Model model,HttpServletRequest request,HttpSession session)throws FileNotFoundException {
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
					return value;
		}
		String paging=null;
		model.addAttribute("studentBo",new StudentRegisterBO());
		if(null!=request.getParameter("successMessage")){
			model.addAttribute("successMessage", request.getParameter("successMessage"));
		}

		if(null!=request.getParameter("errorMessage")){
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}

		StudentRegisterBO studentBo = new StudentRegisterBO();
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
		}
		//Pagination
		studentPagination(studentBo ,paging,model,session,request);
		return "view-student-register";
	}
		/*StudentRegisterBO student = new StudentRegisterBO();
		student.setDelete(false);
		student.setSending_status(true);
		List<StudentRegisterBO> studentList = studentService.studentList(student);
		if(null!=studentList && !studentList.isEmpty() && studentList.size()>0) {
			model.addAttribute("studentList", studentList);
		}
		
		return "view-student-register";}*/
		
	private void studentPagination(StudentRegisterBO studentBo, String paging, Model model, HttpSession session,HttpServletRequest request) throws FileNotFoundException {
		// TODO Auto-generated method stub
		long totalCustomercount=0;
		int page=1;
		long totalCustomerRecordCount=0;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}
		
		List<StudentRegisterBO> studentList=new ArrayList<StudentRegisterBO>();
		List<StudentRegisterBO> pagestudentList =new ArrayList<StudentRegisterBO>();
		StudentRegisterBO studentBO=new StudentRegisterBO();
		if(null!=paging){
			page=Integer.parseInt(paging);
		}
		//Search Pagination Function
		if(null!=request.getParameter("search") && request.getParameter("search")!=""){
			String value=request.getParameter("search");
			studentBo.setFirstName(value);
			studentList=studentService.totalSearchStudent(studentBo);
			if(null!=studentList && !studentList.isEmpty() &&studentList.size()>0){
				totalCustomercount=studentList.size();
			}
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			studentBo.setRecordIndex(startingRecordIndex);
			studentBo.setMaxRecord(maxRecord);
			studentBo.setPagination("pagination");
			pagestudentList=studentService.searchStudent(studentBo);
					}
		//view Pagination
		else{
			studentBo.setIsDelete(false);
			studentBo.setSending_status(true);	
			studentList=studentService.studentList(studentBo);
			if((null!=studentList && !studentList.isEmpty() && studentList.size()>0)){
				totalCustomerRecordCount=studentList.size();
				totalCustomercount = totalCustomerRecordCount;
			}
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			studentBO.setRecordIndex(startingRecordIndex);
			studentBO.setMaxRecord(maxRecord);
			studentBO.setPagination("pagination");
			studentBO.setIsDelete(false);
			studentBO.setSending_status(true);
			pagestudentList= studentService.listOfPageStudent(studentBO);
		}
		if(null!=pagestudentList && !pagestudentList.isEmpty() && pagestudentList.size()>0){
			model.addAttribute("studentList", pagestudentList);
			List<StudentRegisterBO> studentLists=new ArrayList<StudentRegisterBO>();
			studentLists.addAll(pagestudentList);
			model.addAttribute("studentLists",
					Pagination.paginationLimitedRecords(page, studentLists, maxRecord, totalCustomercount));
		}/*else{
					model.addAttribute("errorMessage", TechbootResourceBundle.getValue("create.errer"));
				}*/
				
	}
	//pagination 
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
	
	@RequestMapping(value =SEARCH_STUDENT, method = RequestMethod.POST)
	public String searchStudent(@Valid  @ModelAttribute("studentBo") StudentRegisterBO studentBO, BindingResult bindingResult, Model model, HttpServletRequest request,HttpSession session) throws Exception{
		long totalCustomercount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}
		
		String paging=null;
		List<StudentRegisterBO> studentLists=new ArrayList<StudentRegisterBO>();
		List<StudentRegisterBO> listsOfStudent=new ArrayList<StudentRegisterBO>();
		//search Second page
		if(null!=studentBO.getFirstName()){
			model.addAttribute("searcgValue", studentBO.getFirstName());
		}
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
			page=Integer.parseInt(paging);
		}
		listsOfStudent=studentService.totalSearchStudent(studentBO);
		if(null!=listsOfStudent && !listsOfStudent.isEmpty() &&listsOfStudent.size()>0){
			totalCustomercount=listsOfStudent.size();
		}else{
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
			return "redirect:/view-student-register";
		}
		//search pagination
		int startingRecordIndex = paginationPageValues(page, maxRecord);
		studentBO.setRecordIndex(startingRecordIndex);
		studentBO.setMaxRecord(maxRecord);
		//customerBo.setPagination("pagination");

		if(null!=studentBO){
			studentLists=studentService.searchStudent(studentBO);
			if(null!=studentLists && !studentLists.isEmpty() && studentLists.size()>0){
				model.addAttribute("studentLists",
						Pagination.paginationLimitedRecords(page, studentLists, maxRecord, totalCustomercount));
			}
		}
		return "view-student-register";


	}
	
	/*@RequestMapping(value =SEARCH_STUDENT, method = RequestMethod.POST)
	public String searchStudent(@Valid  @ModelAttribute("studentBo") StudentRegisterBO studentBO, BindingResult bindingResult, Model model, HttpServletRequest request,HttpSession session) throws Exception{
		long totalCustomercount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}
		
		String paging=null;
		List<StudentRegisterBO> studentLists=new ArrayList<StudentRegisterBO>();
		List<StudentRegisterBO> listsOfStudent=new ArrayList<StudentRegisterBO>();
		//search Second page
		if(null!=studentBO.getFirstName()){
			model.addAttribute("searcgValue", studentBO.getFirstName());
		}
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
			page=Integer.parseInt(paging);
		}
		listsOfStudent=studentService.totalSearchStudent(studentBO);
		if(null!=listsOfStudent && !listsOfStudent.isEmpty() &&listsOfStudent.size()>0){
			totalCustomercount=listsOfStudent.size();
		}else{
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
			return "redirect:/view-student-register";
		}
		//search pagination
		int startingRecordIndex = paginationPageValues(page, maxRecord);
		studentBO.setRecordIndex(startingRecordIndex);
		studentBO.setMaxRecord(maxRecord);
		//customerBo.setPagination("pagination");

		if(null!=studentBO){
			studentLists=studentService.searchStudent(studentBO);
			if(null!=studentLists && !studentLists.isEmpty() && studentLists.size()>0){
				model.addAttribute("studentLists",
						Pagination.paginationLimitedRecords(page, studentLists, maxRecord, totalCustomercount));
			}
		}
		return "view-student-register";
	}*/
		
	
	private void getCity(Model model) {
		// TODO Auto-generated method stub
		List<String> cityList= Dropdownutils.getCityType();
		model.addAttribute("cityList", cityList);

	}
	private void getstate(Model model) {
		// TODO Auto-generated method stub
		List<String> StateList= Dropdownutils.getStateType();
		model.addAttribute("StateList", StateList);
	}
	private void getLanguage(Model model) {
		// TODO Auto-generated method stub
		List<String> languageList= Dropdownutils.getLanguageType();
		model.addAttribute("languageList", languageList);
	}
	private void getmaritalStatus(Model model) {
		// TODO Auto-generated method stub
		List<String> maritalList= Dropdownutils.getMaritalStatusType();
		model.addAttribute("maritalList", maritalList);
	}
	private void getGender(Model model) {
		// TODO Auto-generated method stub
		List<String> genderList= Dropdownutils.getGenderType();
		model.addAttribute("genderList", genderList);
	}

	@ResponseBody
	@RequestMapping(value=EMAILADDRESS_VALIDATION,method=RequestMethod.GET)
	public String emailAddressValidation(@RequestParam String emailAddress,HttpServletRequest request){
		if(null!=emailAddress && !emailAddress.isEmpty()){
			boolean status=studentService.emailAddressValidation(emailAddress);
			if(status){
				return "EMAILADDRESS";
			}
		}
		return null;

	}

	@ResponseBody
	@RequestMapping(value= MOBILENO_VALIDATION,method=RequestMethod.GET)
	public String mobileValidation(@RequestParam String mobileNo,HttpServletRequest request){
		if(null!=mobileNo && !mobileNo.isEmpty()){
			long mobilenumber= Integer.parseInt(mobileNo);
			boolean status=studentService.mobileValidation(mobilenumber);
			if(status){
				return "MOBILENO";
			}
		}
		return null;

	}
	
	
	
	
	@RequestMapping(value = VIEW_STUDENT, method = RequestMethod.GET)
	public String viewStudent(Model model, HttpServletRequest request,HttpSession session) throws Exception{
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}	
		long studentId=0;//add
		if(null!=session.getAttribute("studentId")){        
			studentId=(long) session.getAttribute("studentId");
		}
		if(null!=request.getParameter("studentid")){ 
			int id=Integer.parseInt(request.getParameter("studentid"));
			studentId=id;
		}
		StudentRegisterBO StudentRegisterBO =studentService.getStudentProfile(studentId);
		if(null!=StudentRegisterBO) {
			model.addAttribute("StudentRegisterBO", StudentRegisterBO);
			return "view-Student";
		}
		return "redirect:/sign-in";

	}
	@RequestMapping(value ="create-course-enrollment", method = RequestMethod.GET)
	public String courseEnrollment(Model model, HttpServletRequest request,HttpSession session) throws Exception{
	//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}	
		
		model.addAttribute("studentCouresBO", new StudentCouresBO());
		getCourse(model);
		if(null!=session.getAttribute("adminId")) {
			getStudents(model);
		}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}
		return "create-course-enrollment";

	}


	@RequestMapping(value ="create-course-enrollment", method = RequestMethod.POST)
	public String courseEnrollment(@ModelAttribute("studentCouresBO") StudentCouresBO studentCouresBO,Model model, HttpServletRequest request,HttpSession session) throws Exception{

		if(null!=session.getAttribute("adminId")) {
			long adminId=(long) session.getAttribute("adminId");
			studentCouresBO.setCreatedBy(adminId);

			String Id=studentCouresBO.getStudentLoginId().getStudentRegisterBO().getFirstName();
			long studentId=Integer.parseInt(Id);
			boolean status=studentService.isCourseExist(studentCouresBO,studentId); 
			if(status==true) {
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.enroll"));
				return "redirect:/create-course-enrollment";
			}

			long studentCourseId=studentService.saveCourseEnrollment(studentCouresBO);
			if(0<studentCourseId) {
				return "redirect:/view-course-enrollment";
			}
		}

		long studId=(long) session.getAttribute("studentId");

		boolean status=studentService.isCourseExist(studentCouresBO,studId); 
		if(status) {
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.enroll"));
			return "redirect:/create-course-enrollment";
		}

		long studentId=(long) session.getAttribute("studentId");

		long studentCourseId=studentService.saveCourseEnrollment(studentCouresBO,studentId);

		if(0<studentCourseId) {
			model.addAttribute("successMessage", TechbootResourceBundle.getValue("enroll.sucess"));
			return "redirect:/view-course-enrollment";
		}
		return "create-course-enrollment";

	}

	@RequestMapping(value ="view-course-enrollment", method = RequestMethod.GET)
	public String viewEnrollment(Model model, HttpServletRequest request,HttpSession session) throws Exception{
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}
		if(null!=request.getParameter("successMessage")) {
			model.addAttribute("successMessage", request.getParameter("successMessage"));
		}
		List<StudentCouresBO> EnrollmentList = new ArrayList<StudentCouresBO>();
		if(null!=session.getAttribute("adminId")) { 
			StudentCouresBO student = new StudentCouresBO(); 
			student.setIsDelete(false); 
			student.setSending_status(true);
			EnrollmentList = studentService.getEntrollmentList(student); 
		}else
		{ 
			long studentId=(long)session.getAttribute("studentId");
			EnrollmentList=studentService.getStudentCouresList(studentId); 
			model.addAttribute("searchCourse",new StudentCouresBO());
		}
		if(null!=EnrollmentList&&0<EnrollmentList.size()) {
			model.addAttribute("studentCoureslist", EnrollmentList);
			//model.addAttribute("searchCourse",new StudentCouresBO()); 
		}
		
		/*
		 * String paging=null; if(null!=request.getParameter("successMessage")){
		 * model.addAttribute("successMessage",request.getParameter("successMessage"));
		 * } if(null!=request.getParameter("page")){
		 * paging=request.getParameter("page"); } StudentCouresBO courseBo = new
		 * StudentCouresBO(); coursePagination(courseBo,paging,model,session);
		 * model.addAttribute("searchCourse",new StudentCouresBO());
		 */
		return "view-course-enrollment";
	}

	/*
	 * private void coursePagination(StudentCouresBO courseBo, String paging, Model
	 * model, HttpSession session) throws FileNotFoundException { long count=0; long
	 * totalCourseCount=0; int page=1; int maxRecord=0; String
	 * Record=TechbootResourceBundle.getValue("pagination.count");
	 * maxRecord=Integer.parseInt(Record); if(null!=paging){
	 * page=Integer.parseInt(paging); } if(null!=session.getAttribute("adminId")) {
	 * count = studentService.retrieveCourseCount(courseBo); } if(0<count) {
	 * totalCourseCount = count; } int
	 * StartingRecordIndex=paginationPageValues(page, maxRecord);
	 * courseBo.setRecordIndex(StartingRecordIndex);
	 * courseBo.setMaxRecord(maxRecord); courseBo.setPagination("pagination");
	 * if(null!=session.getAttribute("adminId")) { List<StudentCouresBO>
	 * EnrollmentList = studentService.getEntrollmentList(courseBo);;
	 * if(null!=EnrollmentList && !EnrollmentList.isEmpty() &&
	 * EnrollmentList.size()>0){
	 * model.addAttribute("enrollmentLists",Pagination.paginationLimitedRecords(
	 * page, EnrollmentList, maxRecord, totalCourseCount)); } }
	 * 
	 * }
	 * 
	 * private int paginationPageValues(int page, int maxRecord) { // TODO
	 * Auto-generated method stub int pageRecords = 0; if (page == 1) { pageRecords
	 * = 0; } else { pageRecords = (page - 1) * maxRecord + 1; pageRecords =
	 * pageRecords - 1; } return pageRecords; }
	 */
	@RequestMapping(value= EDIT_COURSE_ENROLLMENT,method = RequestMethod.GET)
	public String getEditCourseEnrollement(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws FileNotFoundException{
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
		return value;
				}
		StudentCouresBO StudentCouresBO=new StudentCouresBO();
		CourseBO courseBO =new CourseBO();
		StudentRegisterBO student = new StudentRegisterBO();
		//model.addAttribute("studentCouresBO", new StudentCouresBO());
		//getCourse(model);

		if(null!=request.getParameter("couresId")){
			int couresId=Integer.parseInt(request.getParameter("couresId"));
			courseBO.setCourseId(couresId);
			StudentCouresBO.setCourseBO(courseBO);
			
		}
		if(null!=request.getParameter("studentId")){
			long studentId=Integer.parseInt(request.getParameter("studentId"));
			student.setStudentRegisterId(studentId);
			StudentCouresBO.setStudentRegisterBO(student);
		}
		StudentCouresBO=studentService.getEditCourseEnrollement(StudentCouresBO);

		if(null!=StudentCouresBO) {
			model.addAttribute("StudentCouresBO",StudentCouresBO);
			getCourse(model);
			//smodel.addAttribute("courselist", courseBO);
		}

		return "edit-course-enrollment";

	}

	@RequestMapping(value= EDIT_COURSE_ENROLLMENT,method = RequestMethod.POST)
	public String editCourseEnrollment(@ModelAttribute("StudentCouresBO") StudentCouresBO studentCouresBO,Model model, HttpServletRequest request,HttpSession session) throws Exception{
	//checking login
	String value=CheckingStatus.checkSession(request,session);
	if(null!=value){
	return value;
	}
	boolean status=false;
	boolean check=false;
	if(null!=session.getAttribute("studentId")){
		long studentId=(long) session.getAttribute("studentId");
		check=studentService.isCourseExist(studentCouresBO,studentId); 
		if(check==true) {
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.enroll"));
			return "redirect:/view-course-enrollment";
			}
		studentCouresBO.setModifiedBy(studentId);
		status=studentService.editSaveCourseEnrollment(studentCouresBO,studentId);
	}
	if(null!=session.getAttribute("adminId")){
		long adminId= (long) session.getAttribute("adminId");
		long studentId= studentCouresBO.getStudentRegisterBO().getStudentRegisterId();
		check=studentService.isCourseExist(studentCouresBO,studentId); 
		if(check==true) {
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.enroll"));
			return "redirect:/view-course-enrollment";
			}
		studentCouresBO.setModifiedBy(adminId);
		status=studentService.editSaveCourseEnrollment(studentCouresBO,studentId);
	}
	if(status){
		model.addAttribute("successMessage", TechbootResourceBundle.getValue("update.success"));
	}else{
		model.addAttribute("errorMessage", TechbootResourceBundle.getValue("update.error"));
		}
	return"redirect:/view-course-enrollment";

	}

	@RequestMapping(value=DELETE_COURSE_ENROLLMENT,method=RequestMethod.GET)
	public String deleteCourseEnrollemt(Model model,HttpServletRequest request,HttpSession session)throws FileNotFoundException{
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
		return value;
	}
		
		StudentCouresBO StudentCouresBO=new StudentCouresBO();
		CourseBO CourseBO=new CourseBO();
		if(null!=request.getParameter("studentCouresId")) {
			String id=request.getParameter("studentCouresId");
			long studentCouresId=Integer.parseInt(id);
			StudentCouresBO.setStudentCouresId(studentCouresId);
		}
		boolean success=studentService.deleteCourseEnrollemt(StudentCouresBO);
		if(success) {
			model.addAttribute("successMessage", TechbootResourceBundle.getValue("delete.success"));
			return "redirect:/view-course-enrollment";    
		}
		model.addAttribute("errorMessage", TechbootResourceBundle.getValue("update.error"));
		return "redirect:/view-course-enrollment";                   

	}


	@RequestMapping(value="/search-enrollment-course",method = RequestMethod.POST)
	public String searchCourse(@ModelAttribute("searchCourse") StudentCouresBO StudentCouresBO,BindingResult result,HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) {

		long studentId=(long) session.getAttribute("studentId");       

		StudentCouresBO studentCouresBO=new StudentCouresBO();
		CourseBO coursebo=new CourseBO();
		coursebo.setCourseName(StudentCouresBO.getCourseBO().getCourseName());

		studentCouresBO.setCourseBO(coursebo);

		List<StudentCouresBO>couresList=new ArrayList<StudentCouresBO>();
		couresList=studentService.searchCoures(studentCouresBO,studentId);
		if(null!=couresList&&!couresList.isEmpty()&&couresList.size()>0) {
			model.addAttribute("studentCoureslist", couresList);
			model.addAttribute("searchCourse",studentCouresBO); 
		}

		return "view-course-enrollment";
	}

	@RequestMapping(value="/feedback",method=RequestMethod.GET)
	public String feedback(HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session){

		long studentId=(long) session.getAttribute("studentId");
		List<StudentCouresBO> enrollmentList =studentService.getStudentCouresList(studentId);

		if(null!=enrollmentList&&0<enrollmentList.size()) {
			model.addAttribute("enrollmentList", enrollmentList);
		}

		model.addAttribute("feedBackBo", new FeedbackBO());

		return "feedback";

	}
	@RequestMapping(value="/feedback",method=RequestMethod.POST)
	public String feedBack(@ModelAttribute("feedBackBo")FeedbackBO feedbackBO,HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session){
		long studentId=(long) session.getAttribute("studentId");
		StudentRegisterBO studentRegisterBO=new StudentRegisterBO();
		studentRegisterBO.setStudentRegisterId(studentId);
		feedbackBO.setStudentRegisterBO(studentRegisterBO);
		long feedbackId=studentService.saveFeedback(feedbackBO);
		if(0<feedbackId){

		}
		return "redirect:/view-feedback";
	}


	@RequestMapping(value="/view-feedback",method=RequestMethod.GET)
	public String view(HttpServletRequest request,HttpServletResponse response,Model model){
		List<FeedbackBO>feedbackbolist=new ArrayList<FeedbackBO>();
		feedbackbolist=studentService.viewFeedback();

		if(null!=feedbackbolist&& !feedbackbolist.isEmpty()){
			model.addAttribute("feedbackbolist",feedbackbolist);
		}
		return "view-feedback";
	}
	@RequestMapping(value="/edit-feedback",method=RequestMethod.GET)
	public String edit(HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session){
		String feedbackId=request.getParameter("feedbackId");
		long id=Long.parseLong(feedbackId);
		FeedbackBO feedbackBO=new FeedbackBO();
		feedbackBO=studentService.editFeedback(id);
		if(null!=feedbackBO){
			model.addAttribute("feedBackBo", feedbackBO);
		}

		long studentId=(long) session.getAttribute("studentId");
		List<StudentCouresBO> enrollmentList =studentService.getStudentCouresList(studentId);

		if(null!=enrollmentList&&0<enrollmentList.size()) {
			model.addAttribute("enrollmentList", enrollmentList);
		}
		return "edit-feedback";

	}
	@RequestMapping(value="/edit-feedback",method=RequestMethod.POST)
	public String editfeedback(@ModelAttribute("feedBackBo")FeedbackBO feedbackBO,HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session){
		boolean status=studentService.updateFeedback(feedbackBO);
		if(status){
			return "redirect:/view-feedback";
		}
		return "edit-feedback";
	}
	@RequestMapping(value="/delete-feedback",method=RequestMethod.GET)
	public String deletefeedback(HttpServletRequest request,HttpServletResponse response,Model model){
		FeedbackBO feedbackBO=new FeedbackBO();
		String feedbackId=request.getParameter("feedbackId");
		long id=Long.parseLong(feedbackId);
		feedbackBO.setFeedbackId(id);
		boolean status=studentService.deletefeedback(feedbackBO);
		if(status){
			return "redirect:/view-feedback";
		}
		return "redirect:/view-feedback";

	}
	@RequestMapping(value =EDIT_STUDENT, method = RequestMethod.GET) 
	public String updateStudent(Model model,HttpServletRequest request,HttpSession session) {
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		long studentId=0;
		if(null!=request.getParameter("studentid")){ 
			int id=Integer.parseInt(request.getParameter("studentid"));
			studentId=id;
		}
		StudentRegisterBO StudentRegisterBO =studentService.getStudentProfile(studentId);
		if(null!=StudentRegisterBO) {
			getCity(model);
			getstate(model);
			getmaritalStatus(model);
			getGender(model);
			model.addAttribute("studentRegisterBO", StudentRegisterBO);
				
			if(null!=getUserRole() && getUserRole().equalsIgnoreCase("admin")) {
				return "edit-student-register";
			}
		}
		 return "redirect:/view-student-register";
	}
	@RequestMapping(value =EDIT_STUDENT, method = RequestMethod.POST) 
	public String updateSaveStudent(@Valid  @ModelAttribute("studentRegisterBO") StudentRegisterBO studentRegisterBO, BindingResult bindingResult, Model model, HttpServletRequest request,HttpSession session) throws Exception{
		boolean status=false;
		if(bindingResult.hasErrors()){
			return "student-sign-up";
		}
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		
		if(null!=getUserRole() && getUserRole().equalsIgnoreCase("admin")) {
			if(null!=session.getAttribute("adminId")){
			long adminId= (long) session.getAttribute("adminId");
			studentRegisterBO.setModifiedBy(adminId);
			}
			status=studentService.updateStudent(studentRegisterBO);
			}
		if(status==true){
			model.addAttribute("successMessage", TechbootResourceBundle.getValue("update.Success"));
			return "redirect:/view-student-register";
		}
		
		model.addAttribute("errorMessage", TechbootResourceBundle.getValue("update.error"));
		return "redirect:/view-student-register";
	}
	@RequestMapping(value = DELETE_STUDENT, method = RequestMethod.GET) 
	public String deleteStudent(Model model,HttpServletRequest request,HttpSession session) throws Exception {
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		long studentId=0;//add
		if(null!=request.getParameter("studentid")){ 
			int id=Integer.parseInt(request.getParameter("studentid"));
			studentId=id;
		}
		StudentRegisterBO studentRegisterBO =new StudentRegisterBO();
		studentRegisterBO.setStudentRegisterId(studentId);
		studentRegisterBO.setIsDelete(true);
		studentRegisterBO.setSending_status(false);
		boolean result =studentService.deleteStudent(studentRegisterBO);
		if(result==true){
			model.addAttribute("successMessage", TechbootResourceBundle.getValue("delete.Success"));
			return"redirect:/view-student-register";
		}
		model.addAttribute("errorMessage", TechbootResourceBundle.getValue("delete.error"));
		return "redirect:/view-student-register";
}
}

