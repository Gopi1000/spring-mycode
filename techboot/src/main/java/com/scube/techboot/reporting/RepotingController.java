package com.scube.techboot.reporting;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scube.techboot.bo.CampaignBO;
import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.StudentCouresBO;
import com.scube.techboot.controller.Pagination;
import com.scube.techboot.reporting.ExcelReportView;
import com.scube.techboot.reporting.PDFBuilder;
import com.scube.techboot.service.CampaignService;
import com.scube.techboot.service.CompanyService;
import com.scube.techboot.service.ProductService;
import com.scube.techboot.service.StudentService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.TechbootResourceBundle;



@Controller
public class RepotingController {
	@Autowired
	private StudentService studentService;
	 @Autowired
		private CompanyService companyservice;
	 @Autowired
		private CampaignService campaignService;
	    
	
	@RequestMapping(value="student-general-reporting",method=RequestMethod.GET)
	public String studentReporting(Model model,HttpServletRequest request,HttpSession session)throws FileNotFoundException{
		return "student-general-reporting";
		
	}
	
	@RequestMapping(value="/vieww",method=RequestMethod.GET)    
	public String viewemp(Model model,HttpServletRequest request,HttpSession session) throws ClassNotFoundException, SQLException{   
		
		List<StudentCouresBO> EnrollmentList = new ArrayList<StudentCouresBO>();
		if(null!=session.getAttribute("adminId")) {
			StudentCouresBO student = new StudentCouresBO();
			student.setIsDelete(false);
			EnrollmentList = studentService.getEntrollmentList(student);
			model.addAttribute("EnrollmentList",EnrollmentList);
			model.addAttribute("searchCourse",new StudentCouresBO());
		}
		return "view";
	}
	
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public String searchCourse(@ModelAttribute("searchCourse") StudentCouresBO StudentCouresBO,BindingResult result,HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) {

		
		
		//long studentId=(long) session.getAttribute("studentId");       
	//long studentId=2;
	
		StudentCouresBO studentCouresBO=new StudentCouresBO();
		CourseBO coursebo=new CourseBO();
		coursebo.setCourseName(StudentCouresBO.getCourseBO().getCourseName());

		studentCouresBO.setCourseBO(coursebo);

		List<StudentCouresBO>couresList=new ArrayList<StudentCouresBO>();
		couresList=studentService.searchCoures(studentCouresBO,0);
		if(null!=couresList&&!couresList.isEmpty()&&couresList.size()>0) {
			model.addAttribute("studentCoureslist", couresList);
			model.addAttribute("searchCourse",studentCouresBO); 
		}

		return "view";
	}
	
	@RequestMapping(value = "/viewcompany", method = RequestMethod.GET)
	public String viewCompany(Model model, HttpServletRequest request,HttpSession session) throws Exception{
	   	
		   String value=CheckingStatus.checkSession(request,session);
			if(null!=value){
				return value;
			}
			String paging=null;
			model.addAttribute("companyValue",new CompanyBO());
			if(null!=request.getParameter("succesmessage")) {
				model.addAttribute("succesmessage", request.getParameter("succesmessage"));
			}
			if(null!=request.getParameter("errorMessage")) {
				model.addAttribute("errorMessage", request.getParameter("errorMessage"));
			}	
			if(null!=request.getParameter("page")){
				paging=request.getParameter("page");
			}
	        CompanyBO companyBO=new CompanyBO();
			companyBO.setIsDelete(false);
	    
			companyPagination(companyBO,paging,model,session);
			return "view-company";
		}
		private void companyPagination(CompanyBO companyBO,String paging,Model model,HttpSession session) throws Exception{

			List<CompanyBO> companyList=new ArrayList<CompanyBO>();
			List<CompanyBO> pagecompanylist=new ArrayList<CompanyBO>();
			CompanyBO companyBo=new CompanyBO();
			long totalcompanycount=0;
			int page=1;
			int maxRecord=0;
			String Record=TechbootResourceBundle.getValue("pagination.count");
			if(null!=Record){
				maxRecord=Integer.parseInt(Record);
			}
			long totalcompanyRecordCount=0;
			companyBo.setIsDelete(false);

			if(null!=paging){
				page=Integer.parseInt(paging);
			}

			if(null!=companyBO){
				//company
				if(null!=session.getAttribute("companyId")){
					CompanyBO company=new CompanyBO();
					int id=(int) session.getAttribute("companyId");
					company.setCompanyId(id);
					companyBO=companyservice.getCompanyObject(company);
					companyList.add(companyBO);
				}
				//admin
				else{
					companyBO.setIsDelete(false);
					companyList=companyservice.retriveCompany(companyBO);
				}
				if(null!=companyList && companyList.size()>0 && !companyList.isEmpty()) {
					totalcompanyRecordCount=companyList.size();
					totalcompanycount=totalcompanyRecordCount; 
				}
				int startingRecordIndex=paginationPageValues(page,maxRecord);
				companyBo.setRecordIndex(startingRecordIndex);
				companyBo.setMaxRecord(maxRecord);
				companyBo.setPagination("pagination");
				//company
				if(null!=session.getAttribute("companyId")){
					CompanyBO company=new CompanyBO();
					int id=(int) session.getAttribute("companyId");
					companyBo.setCompanyId(id);
					company=companyservice.getCompanyObject(companyBo);
					company.setS_No(1);
					pagecompanylist.add(company);
				}//admin
				else{
					pagecompanylist=companyservice.listOfPageCompany(companyBo);
				}
				if(null!=pagecompanylist && !pagecompanylist.isEmpty() && pagecompanylist.size()>0){
					List<CompanyBO> companylists=new ArrayList<CompanyBO>();
	                companylists.addAll(pagecompanylist);
					model.addAttribute("companylists",
							Pagination.paginationLimitedRecords(page,companylists,maxRecord,totalcompanycount));
				}
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
	
	
	 @RequestMapping(value = "downloadEexcel", method = RequestMethod.GET)
	    public  ModelAndView downloadExcel(Model model,HttpServletRequest request,HttpSession session) {
	
		List<StudentCouresBO> EnrollmentList = new ArrayList<StudentCouresBO>();
		String reportType=request.getParameter("type");
		if(null!=reportType &&reportType.equals("excel")) {

			if(null!=session.getAttribute("adminId")) {
				StudentCouresBO student = new StudentCouresBO();
				student.setIsDelete(false);
				EnrollmentList = studentService.getEntrollmentList(student);
			}
			
				return new ModelAndView(new ExcelReportView(),"EnrollmentList",EnrollmentList);
			}
				
			return new ModelAndView("student-report","EnrollmentList", EnrollmentList);
		
	    }

	 @RequestMapping(value = "downloadPDF", method = RequestMethod.GET)
	    public  ModelAndView downPdf(Model model,HttpServletRequest request,HttpSession session) {
		
		List<StudentCouresBO> EnrollmentList = new ArrayList<StudentCouresBO>();

		if(null!=session.getAttribute("adminId")) {
			StudentCouresBO student = new StudentCouresBO();
			student.setIsDelete(false);
			EnrollmentList = studentService.getEntrollmentList(student);
		if(null!=EnrollmentList && !EnrollmentList.isEmpty() && EnrollmentList.size()>0) {
				return new ModelAndView(new PDFBuilder(), "EnrollmentList", EnrollmentList);
			}
		}
			return new ModelAndView( "downloadPDF" ,"EnrollmentList", EnrollmentList);
		
	    }
	 



@RequestMapping(value="/viewcampaign" ,method = RequestMethod.GET)
public String viewCampaign(Model model, HttpServletRequest request,HttpSession session) throws FileNotFoundException{
	
	String value=CheckingStatus.checkSession(request,session);
	if(null!=value){
		return value;
	}
    String paging=null;
	model.addAttribute("campaignBO",new CampaignBO());
	if(null!=request.getParameter("successMessage")){
		model.addAttribute("successMessage",request.getParameter("successMessage"));
	}
	if(null!=request.getParameter("errorMessage")){
		model.addAttribute("errorMessage",request.getParameter("errorMessage"));
	}
	CampaignBO campaignBO=new CampaignBO();
	campaignBO.setIsDelete(false);
	campaignBO.setSending_status(true);

	if(null!=request.getParameter("page")){
		paging=request.getParameter("page");
	}
	//pagination
	campaignPagination(campaignBO,paging,model,session);
	return "view-campaign";

}

private void campaignPagination(CampaignBO campaignBO, String paging, Model model, HttpSession session) throws FileNotFoundException {
	// TODO Auto-generated method stub
	long count=0;
	long totalcampaigncount=0;
	int page=1;
	int maxRecord=10;
	long totalcampaignRecordcount=0;
	if(null!=paging){
		page=Integer.parseInt(paging);
	}
	List<CampaignBO> pagecampaignlist=new ArrayList<CampaignBO>();
	campaignBO.setIsDelete(false);
	campaignBO.setSending_status(true);

	//company
	if(null!=session.getAttribute("companyId")){
		int id=(int) session.getAttribute("companyId");
		CompanyBO companyBO=new CompanyBO();
		companyBO.setCompanyId(id);
		campaignBO.setCompanyBO(companyBO);
		count=campaignService.getListOfCompanyCampaign(campaignBO);
	}
	else{
		count=campaignService.getListOfCampaign(campaignBO);
	}
	if(0!=count){
		totalcampaignRecordcount=count;
	}
	int StartingRecordIndex=paginationPageValues(page, maxRecord);
	campaignBO.setRecordIndex(StartingRecordIndex);
	campaignBO.setMaxRecord(maxRecord);
	campaignBO.setPagination("pagination");

	//company
	if(null!=session.getAttribute("companyId")){
		int id=(int) session.getAttribute("companyId");
		CompanyBO companyBO=new CompanyBO();
		companyBO.setCompanyId(id);
		campaignBO.setCompanyBO(companyBO);
		pagecampaignlist=campaignService.listOfCampaign(campaignBO);
	}
	//admin
	else{
		pagecampaignlist=campaignService.listOfCampaign(campaignBO);
	}
	if(null!=pagecampaignlist && !pagecampaignlist.isEmpty() && pagecampaignlist.size()>0){
		model.addAttribute("campaignlist",pagecampaignlist);
		List<CampaignBO> campaignLists=new ArrayList<CampaignBO>();
		totalcampaigncount=totalcampaignRecordcount;
		campaignLists.addAll(pagecampaignlist);
		model.addAttribute("campaignLists",Pagination.paginationLimitedRecords(page, campaignLists, maxRecord, totalcampaigncount));
	}
	else{
		model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.campaign"));
	}

}


private int paginationPageValues1(int pageid, int recordPerPage) {
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
}

