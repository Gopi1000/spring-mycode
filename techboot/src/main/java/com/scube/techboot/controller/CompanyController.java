package com.scube.techboot.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.security.TechBootUser;
import com.scube.techboot.service.CompanyService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.Dropdownutils;
import com.scube.techboot.utils.SaveImagesToFolder;
import com.scube.techboot.utils.TechbootResourceBundle;

@Controller
public class CompanyController extends ControllerUtils{
	private static final ServletRequest requst = null;
    //URL
	private static final String CREATE_COMPANY="/create-company";
	private static final String REGISTER_COMPANY="/register-company";
	private static final String VIVE_COMPANY="/view-company";
	private static final String EDIT_COMPANY="/edit-company";
	private static final String DELETE_COMPANY="/delete-company";
	private static final String SEARCH_COMPANY="/search-company";
	private static final String VIEW_COMPANYDETAILS="/view-companydetails";
	private static final String COMPANYNAME_VALIDATIONS="/companyNameValidations";
	private static final String EMAILADDRESS_VALIDATIONS="/emailAddressVerifications";
	private static final String WEBSITE_VALIDATIONS="/websiteVerifications";
	private static final String EDIT_COMPANY_USER="/edit_company_user";
	private static final String VIEW_COMPANYUSER="/view-company-user";

    private static final  Logger LOGGER=Logger.getLogger(CompanyController.class);

    @Autowired
	private CompanyService companyservice;
    String cid;
    
    @RequestMapping(value= REGISTER_COMPANY,method=RequestMethod.GET)
    public String getRegisterCompany(Model model,HttpServletRequest request)
    {
    
    	dropdown(model);
    	getCompanyType(model);
    	if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}
        else{
			model.addAttribute("companyBo",new CompanyBO());
		}
    		return "register-company";
    	}

    @RequestMapping(value=REGISTER_COMPANY,method=RequestMethod.POST)
    public String postRegisterCompany(@Valid @ModelAttribute("companyBo") CompanyBO companyBo,BindingResult bindingResult,@RequestParam("companyLogos") MultipartFile companyLogo,Model model,
			HttpServletRequest request,HttpSession session) throws Exception{
    	
    	if(bindingResult.hasErrors()){
			System.out.println(bindingResult.getErrorCount());
			dropdown(model);
			getCompanyType(model);
			return "register-company";
		}
    	if(null!=companyBo){
			//is exist's
			CompanyBO companyBO=new CompanyBO();
			companyBO.setemailAddress(companyBo.getemailAddress());
			if(companyservice.isValidCompanyDetails(companyBO)){
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.email"));
				return "redirect:/register-company";
			}
			companyBO.setemailAddress(null);
			companyBO.setCompanyName(companyBo.getCompanyName());
			if(companyservice.isValidCompanyDetails(companyBO)){
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.companyName"));
				return "redirect:/register-company";
			}
			companyBO.setCompanyName(null);
			companyBO.setCompanyWebSite(companyBo.getCompanyWebSite());
			if(companyservice.isValidCompanyDetails(companyBO)){
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.website"));
				return "redirect:/register-company";
			}
		}
    	boolean status;
    	status=CheckingStatus.status(companyBo.getemailAddress(),companyBo.getConformEmailAddress());
    	if(status){
    		status=CheckingStatus.status(companyBo.getPassword(),companyBo.getConformPassword());
        	if(!status){
        		model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.password"));
        		dropdown(model);
    			return "register-company";
        	}
    	}else{
    	model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.confirmEmail"));
    	dropdown(model);
		return "register-company";
    	}
    	String imageName = null;
		long lastcompanyId=0;
		//company logo
		if(null!=companyBo){
			CompanyBO companyBO=companyservice.retriveOfCompanyId(companyBo);
			if(null!=companyBO){
				lastcompanyId =companyBO.getCompanyId();
				long lastEmployerSequenceId=lastcompanyId+1;
				String imgContentType = companyLogo.getContentType();
				String temp[] = imgContentType.split("/");
				imageName = lastEmployerSequenceId + "." + temp[1];
				companyBo.setImageName(imageName);
			}
		}
    	
    	if(null!=companyBo) {
    		companyBo=companyservice.saveCompanyDetails(companyBo,session);
		}

		//save local company Logo
		if(0!=companyBo.getCompanyId()){
			String imgPathName = TechbootResourceBundle.getValue("company.Logo");
			SaveImagesToFolder.saveImageToFolder(imageName, companyLogo, imgPathName);
		}
		if(companyBo.getCompanyId()!=0) {
			model.addAttribute("succesmessage", TechbootResourceBundle.getValue("register.company"));
			return "redirect:/sign-in";
		}
	return "redirect:/register-company";
   }
	  private void dropdown(Model model) {
		// TODO Auto-generated method stub
		List<String> industryList = Dropdownutils.getIndustryType();
		model.addAttribute("industryList", industryList);
	}
	  private void getCompanyType(Model model) {
			// TODO Auto-generated method stub
			List<String> companyType = Dropdownutils.getCompanyType();
			model.addAttribute("companyTypeList", companyType);
		}
	  
	  @RequestMapping(value= CREATE_COMPANY,method=RequestMethod.GET)
		public String getCreateCompany(Model model,HttpServletRequest request,HttpSession session) {

	    	String value=CheckingStatus.checkSession(request,session);
			if(null!=value){
				return value;
			}
			getCompanyType(model);
			dropdown(model);
			if(null!=request.getParameter("errorMessage")) {
				model.addAttribute("errorMessage", request.getParameter("errorMessage"));
			}
					
			if(null!=getUserRole() && getUserRole().equals("admin") ){
				model.addAttribute("companybo",new CompanyBO());
	           }
			 return "create-company";
		}
	  
	  @ResponseBody
	    @RequestMapping(value=WEBSITE_VALIDATIONS,method=RequestMethod.GET)
	    public String websiteValidations(@RequestParam String website,HttpServletRequest request){
	    	if(null!=website && !website.isEmpty()){
	    		boolean status=companyservice.websiteValidations(website);
	    		if(status){
	    			return "WEBSITE";
	    		}
	    	}
			return null;
	    	
	    }
	
	    @ResponseBody
	    @RequestMapping(value=COMPANYNAME_VALIDATIONS,method=RequestMethod.GET)
	    public String companyNameValidations(@RequestParam String companyName,HttpServletRequest request){
	    	if(null!=companyName && !companyName.isEmpty()){
	    		boolean status=companyservice.companyNameValidations(companyName);
	    		if(status){
	    			return "COMPANYNAME";
	    		}
	    	}
			return null;
	    	
	    }
	    
	    @ResponseBody
	    @RequestMapping(value=EMAILADDRESS_VALIDATIONS,method=RequestMethod.GET)
	    public String emailAddressValidations(@RequestParam String emailAddress,HttpServletRequest request){
	    	if(null!=emailAddress && !emailAddress.isEmpty()){
	    		boolean status=companyservice.emailAddressValidations(emailAddress);
	    		if(status){
	    			return "EMAILADDRESS";
	    		}
	    	}
			return null;
	    	
	    }
	    
	@RequestMapping(value=CREATE_COMPANY,method=RequestMethod.POST)
	public String postCreateCompany(@Valid @ModelAttribute("companybo") CompanyBO companyBo,BindingResult bindingResult,@RequestParam("companyLogos") MultipartFile companyLogo,Model model,
			HttpServletRequest request,HttpSession session) throws Exception{

		if(bindingResult.hasErrors()){
			System.out.println(bindingResult.getErrorCount());
			dropdown(model);
			getCompanyType(model);
			return "create-company";
		}
		if(null!=companyBo){
			//is exist's
			CompanyBO companyBO=new CompanyBO();
			companyBO.setemailAddress(companyBo.getemailAddress());
			if(companyservice.isValidCompanyDetails(companyBO)){
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.email"));
				return "redirect:/create-company";
			}
			companyBO.setemailAddress(null);
			companyBO.setCompanyName(companyBo.getCompanyName());
			if(companyservice.isValidCompanyDetails(companyBO)){
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.companyName"));
				return "redirect:/create-company";
			}
			companyBO.setCompanyName(null);
			companyBO.setCompanyWebSite(companyBo.getCompanyWebSite());
			if(companyservice.isValidCompanyDetails(companyBO)){
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.website"));
				return "redirect:/create-company";
			}
		}
		boolean status;
    	status=CheckingStatus.status(companyBo.getemailAddress(),companyBo.getConformEmailAddress());
    	if(status){
    		status=CheckingStatus.status(companyBo.getPassword(),companyBo.getConformPassword());
        	if(!status){
        		model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.password"));
        		dropdown(model);
        		return "create-company";
        	}
    	}else{
    	model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.confirmEmail"));
    	dropdown(model);
		return "create-company";
    	}
		CompanyBO companybo=new CompanyBO();
		String imageName = null;
		long lastcompanyId=0;
		//company logo
		if(null!=companyBo){
			CompanyBO companyBO=companyservice.retriveOfCompanyId(companyBo);
			if(null!=companyBO){
				lastcompanyId =companyBO.getCompanyId();
				long lastEmployerSequenceId=lastcompanyId+1;
				String imgContentType = companyLogo.getContentType();
				String temp[] = imgContentType.split("/");
				imageName = lastEmployerSequenceId + "." + temp[1];
				companyBo.setImageName(imageName);
			}
		}
		
		//create company
		if(null!=session.getAttribute("adminId")){		
			if(null!=companyBo) {
				companybo=companyservice.saveCompanyDetails(companyBo,session);
			}
			//save local company Logo
			if(0!=companybo.getCompanyId()){
				String imgPathName = TechbootResourceBundle.getValue("company.Logo");
				SaveImagesToFolder.saveImageToFolder(imageName, companyLogo, imgPathName);
			}
			if(0!=companybo.getCompanyId()){
				model.addAttribute("succesmessage", TechbootResourceBundle.getValue("create.company"));
				return "redirect:/view-company";
			}
		}
		return "redirect:/create-company";
	}
	
   @RequestMapping(value = VIVE_COMPANY, method = RequestMethod.GET)
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
       //Pagination
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

	@RequestMapping(value=VIEW_COMPANYDETAILS , method=RequestMethod.GET)
	public String viewCompanyDetails(Model model,HttpServletRequest request,HttpSession session) throws Exception{
		
    	String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		CompanyBO  companyBO=new CompanyBO();
		// company
		if(null!=session.getAttribute("companyId"))
		{
    int companyid=(int) session.getAttribute("companyId");
           companyBO.setCompanyId(companyid);
		}
		
	  // admin
        
		if(null!=request.getParameter("company_Id")){
			String id= request.getParameter("company_Id");
			int companyid =Integer.parseInt(id);
			companyBO.setCompanyId(companyid);
		}

		if(0!=companyBO.getCompanyId()){
			CompanyBO company=companyservice.getCompanyObject(companyBO);
			if(null!=company) {
				model.addAttribute("viewCompanyDetails", company);
				return "view-companydetails";
			}
		}
     return "redirect:/view-company";
	}

	@RequestMapping(value = EDIT_COMPANY, method = RequestMethod.GET)
	public String getEditCompanyUser(Model model, HttpServletRequest request,HttpSession session) throws Exception{
		
    	String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		CompanyBO company=new CompanyBO();
		if(null!=request.getParameter("company_Id")){
			String id= request.getParameter("company_Id");
			int companyid =Integer.parseInt(id);
			company.setCompanyId(companyid);
			if(0!=company.getCompanyId()){
				dropdown(model);
				CompanyBO companyBo=companyservice.getCompanyObject(company);
				if(null!=companyBo) {
					model.addAttribute("companyBo", companyBo);
					return "edit-company";
				}
			}
		}
		return "redirect:/view-company";
	}

	@RequestMapping(value = EDIT_COMPANY, method = RequestMethod.POST)
	public String postUpdateCompany(@Valid @ModelAttribute("companybo") CompanyBO companyBo ,Model model, HttpServletRequest request,
			HttpSession session) throws Exception{

		if(null!=request.getParameter("company_Id")){
			String id=request.getParameter("company_Id");
			int companyid=Integer.parseInt(id);
			companyBo.setCompanyId(companyid);
		}
		if(0!=companyBo.getCompanyId()) {
			boolean status = companyservice.updateCompany(companyBo,session);
			if(status) {
				model.addAttribute("succesmessage", TechbootResourceBundle.getValue("updated.company"));
				return "redirect:/view-company";
			}else {
				model.addAttribute("message", "Doesnot Exists");
			}
		}
		return "redirect:/view-company";
	}

	@RequestMapping(value=DELETE_COMPANY,method=RequestMethod.GET)
	public String deleteCompany(Model model,HttpServletRequest request,HttpSession session)throws FileNotFoundException{ 

    	String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		CompanyBO companybo=new CompanyBO();
        if(null!=request.getParameter("company_Id")) {
			String id=request.getParameter("company_Id");
			int companyid=Integer.parseInt(id);
			companybo.setCompanyId(companyid);
		}
		if(0!=companybo.getCompanyId()) {
			boolean status=companyservice.deleteCompany(companybo);
			if(status){
				model.addAttribute("succesmessage",TechbootResourceBundle.getValue("deleted.company"));
				return "redirect:/view-company";
			}else {
				return "redirect:/view-company";
			}
		}
		return  "redirect:/view-company";
	}

    @RequestMapping(value = SEARCH_COMPANY, method = RequestMethod.POST)
	public String searchCompany(@ModelAttribute("companyValue") CompanyBO companyBO ,Model model, HttpServletRequest request,
			HttpSession session) throws Exception{
		//using
		model.addAttribute("companyValue",new CompanyBO());
		List<CompanyBO> companyList=new ArrayList<CompanyBO>();
		long totalCustomercount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}
		if(null!=companyBO){
			int totalCompany=companyservice.searchCompany(companyBO);
			if(0!= totalCompany){
				totalCustomercount=totalCompany;
			}
			else{
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
				return  "redirect:/view-company";
			}
			//search pagination
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			companyBO.setRecordIndex(startingRecordIndex);
			companyBO.setMaxRecord(maxRecord);
			companyBO.setPagination("pagination");
			//Limit Recored 1-10
			companyList=companyservice.searchPageCompany(companyBO);
			if(null!=companyList && !companyList.isEmpty() && companyList.size()>0){
				model.addAttribute("companylists",
						Pagination.paginationLimitedRecords(page, companyList, maxRecord, totalCustomercount));
			}
		}
		return "view-company";
	}
    
    //changes for self
    @RequestMapping(value = EDIT_COMPANY_USER, method = RequestMethod.GET)
	public String getEditCompany(Model model, HttpServletRequest request,HttpSession session) throws Exception{
		
    	String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		CompanyBO company=new CompanyBO();
		if(null!=request.getParameter("company_Id")){
			 cid= request.getParameter("company_Id");
			int companyid =Integer.parseInt(cid);
			company.setCompanyId(companyid);
			if(0!=company.getCompanyId()){
				dropdown(model);
				CompanyBO companyBo=companyservice.getCompanyUserObject(company);
				if(null!=companyBo) {
					model.addAttribute("companyBo", companyBo);
					return "edit_company_user";
				}
			}
    }
		return "redirect:/view-company";
	}
    
    @RequestMapping(value = EDIT_COMPANY_USER, method = RequestMethod.POST)
	public String postUpdateCompanyUser(@Valid @ModelAttribute("companyBo") CompanyBO companyBo1 ,Model model, HttpServletRequest request,
			HttpSession session) throws Exception{
    	
    	companyBo1.setCompanyId(Integer.parseInt(cid));

		/*if(null!=request.getParameter("company_Id")){
			String id=request.getParameter("company_Id");
			int companyid=Integer.parseInt(id);
			companyBo1.setCompanyId(companyid);
		}*/
		if(0!=companyBo1.getCompanyId()) {
			boolean status = companyservice.updateCompanyUser(companyBo1,session);
			if(status) {
				model.addAttribute("succesmessage", TechbootResourceBundle.getValue("updated.company"));
				return "redirect:/view-company-user";
			}else {
				model.addAttribute("message", "Doesnot Exists");
			}
		}
		return "redirect:/view-company-user";
	}

    
    @RequestMapping(value=VIEW_COMPANYUSER, method=RequestMethod.GET)
	public String viewCompanyUser(HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session)throws Exception
	{
    	 String value=CheckingStatus.checkSession(request,session);
 		if(null!=value){
 			return value;
 		}
 		//String paging=null;
 		//model.addAttribute("companyValue",new CompanyBO());
 		if(null!=request.getParameter("succesmessage")) {
 			model.addAttribute("succesmessage", request.getParameter("succesmessage"));
 		}
 		if(null!=request.getParameter("errorMessage")) {
 			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
 		}	
 		       	
			CompanyBO company=new CompanyBO();
			int id=(int) session.getAttribute("companyId");
			company.setCompanyId(id);
			company=companyservice.getCompanyUserobject(company);
			company.setS_No(1);
        
        List<CompanyBO> companylists=new ArrayList<CompanyBO>();
        companylists.add(company)	;		
		
		if(null!=companylists && companylists.size()>0)
		{
			model.addAttribute("companylists", companylists);
		}
		
		return "view-company-user";
		
		}
}
