package com.scube.techboot.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.CustomerBO;
import com.scube.techboot.service.CompanyService;
import com.scube.techboot.service.CustomerService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.Dropdownutils;
import com.scube.techboot.utils.TechbootResourceBundle;


@Controller
public class CustomerController {

	//URL
	private static final String CREATE_CUSTOMER ="/create-customer";
	private static final String VIEW_CUSTOMER ="/view-customer";
	private static final String EDIT_CUSTOMER ="/edit-customer";
	private static final String DELETE_CUSTOMER ="/delete-customer";
	private static final String VIEW_CUSTOMER_DETAILS ="/customer-viewdetails";
	private static final String SEARCH_CUSTOMER ="/search-Customer";
	//private static final String EMAIL_VALIDATIONS ="/EmailValidations";
	//private static final String MOBILE_VALIDATIONS ="/mobileVerifications";	
	//private static final String WHATSAPP_VALIDATIONS ="/whatsappVerifications";
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CompanyService companyservice;



	@ModelAttribute("companylist")
	private List<CompanyBO> listOfCompany(){
		List<CompanyBO> companylist= new ArrayList<CompanyBO>();
		CompanyBO companybo=new CompanyBO();
		companybo.setIsDelete(false);
		companylist=companyservice.retriveCompany(companybo);
		return companylist;
	}


	@RequestMapping(value = CREATE_CUSTOMER, method = RequestMethod.GET)
	public String customerDetails(Model model, HttpServletRequest request,HttpSession session){
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		CustomerBO customerBo=new CustomerBO();
		dropdown(model);
		model.addAttribute("customerBo", customerBo);
		return "create-customer";

	}

	private void dropdown(Model model) {
		// TODO Auto-generated method stub
		List<String> areaList = Dropdownutils.getFunctionarea();
		model.addAttribute("functionArea", areaList);

		List<String> industryList = Dropdownutils.getIndustryType();
		model.addAttribute("industryList", industryList);
		
		/*
		 * List<String> customerList = Dropdownutils.getCustomerType();
		 * model.addAttribute("customerList",customerList);
		 * 
		 * List<String> specificationList = Dropdownutils.getSpecificationType();
		 * model.addAttribute("specificationList", specificationList);
		 */
	}
    @ResponseBody
    @RequestMapping(value = "/emailid", method = RequestMethod.GET)
    public String emailValidations(@RequestParam String emailaddress,@RequestParam String companyId,Model model,HttpServletRequest request){
 	CompanyBO companyBO=new CompanyBO(); 
 	int companyid=Integer.parseInt(companyId);
    	companyBO.setCompanyId(companyid);
    	CustomerBO customerBO=new CustomerBO();
  	customerBO.setCompanyBO(companyBO);
    	customerBO.setEmailId(emailaddress);
    	boolean status=customerService.emailValidations(customerBO);
    	if(status){
    		return "1";
    	}
    	return null;
     }
    @ResponseBody
    @RequestMapping(value = "/mobilenumber", method = RequestMethod.GET)
    public String mobileValidations(@RequestParam String mobileNumber,@RequestParam String companyId,Model model,HttpServletRequest request){
    	CompanyBO companyBO=new CompanyBO(); 
    	int companyid=Integer.parseInt(companyId);
    	companyBO.setCompanyId(companyid);
    	CustomerBO customerBO=new CustomerBO();
    	customerBO.setCompanyBO(companyBO);
    	long mobilenumber=Long.parseLong(mobileNumber);
    	customerBO.setMobileNumber(mobilenumber);
    	boolean status=customerService.mobileValidations(customerBO);
    	if(status){
    		return "2";
    	}
    	return null;
     }
    @ResponseBody
    @RequestMapping(value = "/whatsappnumber", method = RequestMethod.GET)
    public String whatsAppValidations(@RequestParam String whatsAppNumber,@RequestParam String companyId,Model model,HttpServletRequest request){
    	CompanyBO companyBO=new CompanyBO(); 
    	int companyid=Integer.parseInt(companyId);
    	companyBO.setCompanyId(companyid);
    	CustomerBO customerBO=new CustomerBO();
    	customerBO.setCompanyBO(companyBO);
    	long whatsNumber=Long.parseLong(whatsAppNumber);
    	customerBO.setWhatsappNumber(whatsNumber);
    	boolean status=customerService.whatsAppValidations(customerBO);
    	if(status){
    		return "3";
    	}
    	return null;
     }
    
    @ResponseBody
    @RequestMapping(value="/email", method = RequestMethod.GET)
    public String email(@RequestParam String emailaddress,Model model,HttpServletRequest request) {
    	CustomerBO customerBO=new CustomerBO();
    	customerBO.setEmailId(emailaddress);
    	boolean status=customerService.emailIdCheck(customerBO);
    	if(status) {
    		return "a";
    	}
		return null;
    	
    }
    
    @ResponseBody
    @RequestMapping(value="/mobile", method = RequestMethod.GET)
    public String mobileNumber(@RequestParam String mobileNumber,Model model,HttpServletRequest request) {
    	CustomerBO customerBO=new CustomerBO();
    	long mobilenumber=Long.parseLong(mobileNumber);
    	customerBO.setMobileNumber(mobilenumber);
    	
    	boolean status=customerService.mobileNumberCheck(customerBO);
    	if(status) {
    		return "b";
    	}
		return null;
    	
    }
    
    @ResponseBody
    @RequestMapping(value="/whatsapp", method = RequestMethod.GET)
    public String whatsappNumber(@RequestParam String whatsAppNumber,Model model,HttpServletRequest request) {
    	CustomerBO customerBO=new CustomerBO();
    	long whatsNumber=Long.parseLong(whatsAppNumber);
    	customerBO.setWhatsappNumber(whatsNumber);
    	boolean status=customerService.whatsappNumberCheck(customerBO);
    	if(status) {
    		return "c";
    	}
		return null;
    	
    }
   
    @RequestMapping(value = CREATE_CUSTOMER, method = RequestMethod.POST)
	public String postCustomerDetails(@Valid  @ModelAttribute("customerBo") CustomerBO customerBo, BindingResult bindingResult, Model model, HttpServletRequest request
			,HttpSession session) throws FileNotFoundException{
    	
		if(bindingResult.hasErrors()){
			return "create-customer";
		}
		boolean status=customerService.emailcheck(customerBo.getEmailId());
		if(status) {
			model.addAttribute("errorMessage", "emailId already exists");
			return "redirect:/create-customer";
		}
	
		boolean statu=customerService.mobilecheck(customerBo.getMobileNumber());
		if(statu) {
			model.addAttribute("errorMessage", "mobileNumber already exists");
			return "redirect:/create-customer";
		}
		
		  boolean stat=customerService.whatsappCheck(customerBo.getWhatsappNumber()); 
		  if(stat) {
		  model.addAttribute("errorMessage","whatsappNumber already exists");
		  return
		  "redirect:/create-customer"; }
		 
		
		CompanyBO companyBO=new CompanyBO();
		if(null!=customerBo){
			if(null!=session.getAttribute("companyId")){
				int id=(int) session.getAttribute("companyId");
				companyBO.setCompanyId(id);
				customerBo.setCompanyBO(companyBO);
			}
			else{
				String name=customerBo.getCompanyBO().getCompanyName();
				int id=Integer.parseInt(name);
				companyBO.setCompanyId(id);
				customerBo.setCompanyBO(companyBO);
			}
			CustomerBO	customer=customerService.saveCustomerDetails(customerBo);
			if(0!=customer.getCustomerId()){
				model.addAttribute("sucessMessage",
						TechbootResourceBundle.getValue("create.customer"));
				return "redirect:/view-customer";
			}
			else{
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("errer.custome"));
				return "redirect:/view-customer";
			}
		}
		model.addAttribute("errorMessage", TechbootResourceBundle.getValue("errer.custome"));
		return "redirect:/view-customer";

	}

	@RequestMapping(value = VIEW_CUSTOMER, method = RequestMethod.GET)
	public String viewCustomer(Model model, HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		String paging=null;
		model.addAttribute("customerBo", new CustomerBO());
		if(null!=request.getParameter("sucessMessage")){
			model.addAttribute("sucessMessage", request.getParameter("sucessMessage"));
		}

		if(null!=request.getParameter("errorMessage")){
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}

		CustomerBO customerBo=new CustomerBO();
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
		}

		//Pagination
		customerPagination(customerBo ,paging,model,session,request);
		return "view-customer";
	}

	private void customerPagination(CustomerBO customerBo, String paging, Model model, HttpSession session,HttpServletRequest request) throws FileNotFoundException {
		// TODO Auto-generated method stub
		long totalCustomercount=0;
		int page=1;
		long totalCustomerRecordCount=0;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}

		List<CustomerBO> customerList=new ArrayList<CustomerBO>();
		List<CustomerBO> pageCustomerList =new ArrayList<CustomerBO>();
		CustomerBO customerBO=new CustomerBO();
		if(null!=paging){
			page=Integer.parseInt(paging);
		}

		//Search Pagination Function
		if(null!=request.getParameter("search") && request.getParameter("search")!=""){
			String value=request.getParameter("search");
			customerBo.setFirstName(value);
			customerList=customerService.totalSearchCustomer(customerBo);
			if(null!=customerList && !customerList.isEmpty() && customerList.size()>0){
				totalCustomercount=customerList.size();
			}
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			customerBo.setRecordIndex(startingRecordIndex);
			customerBo.setMaxRecord(maxRecord);
			customerBo.setPagination("pagination");
			pageCustomerList=customerService.searchCustomer(customerBo);
		}
		//view Pagination
		else{
			//company
			if(null!=session.getAttribute("companyId")){
				int id=(int) session.getAttribute("companyId");
				CompanyBO companyBO=new CompanyBO();
				companyBO.setCompanyId(id);
				customerBo.setCompanyBO(companyBO);
				customerList=customerService.retriveCustomer(customerBo);
				if((null!=customerList && !customerList.isEmpty() && customerList.size()>0)){
					totalCustomerRecordCount=customerList.size();
					totalCustomercount = totalCustomerRecordCount;
				}
			}//admin
			else{
				customerList=customerService.retriveCustomer(customerBo);
				if((null!=customerList && !customerList.isEmpty() && customerList.size()>0)){
					totalCustomerRecordCount=customerList.size();
					totalCustomercount = totalCustomerRecordCount;
				}
			}

			int startingRecordIndex = paginationPageValues(page, maxRecord);
			customerBO.setRecordIndex(startingRecordIndex);
			customerBO.setMaxRecord(maxRecord);
			customerBO.setPagination("pagination");

			//comapny
			if(null!=session.getAttribute("companyId")){
				int id=(int) session.getAttribute("companyId");
				CompanyBO companyBO=new CompanyBO();
				companyBO.setCompanyId(id);
				customerBO.setCompanyBO(companyBO);
				pageCustomerList= customerService.listOfPageCustomer(customerBO);

			}//admin
			else{
				pageCustomerList= customerService.listOfPageCustomer(customerBO);
			}
		}


		if(null!=pageCustomerList && !pageCustomerList.isEmpty() && pageCustomerList.size()>0){
			model.addAttribute("customerList", pageCustomerList);
			List<CustomerBO> customerLists=new ArrayList<CustomerBO>();
			customerLists.addAll(pageCustomerList);
			model.addAttribute("customerLists",
					Pagination.paginationLimitedRecords(page, customerLists, maxRecord, totalCustomercount));
		}else{
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("create.errer"));
		}
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


	@RequestMapping(value = EDIT_CUSTOMER, method = RequestMethod.GET)
	public String editCustomer(Model model, HttpServletRequest request,HttpSession session){
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		CustomerBO customerBo=new CustomerBO();
		CustomerBO customer=new CustomerBO();
		if(null!=request.getParameter("customerid")){
			String id=request.getParameter("customerid");
			int customerId=Integer.parseInt(id);
			session.setAttribute("customerid", customerId);
			customer.setCustomerId(customerId);
		}
		if(0!=customer.getCustomerId()){
			customerBo=customerService.editCustomer(customer);
		}
		if(null!=customerBo){
			dropdown(model);
			model.addAttribute("customerBo", customerBo);
		}

		else{
			model.addAttribute("errorMessage", "Customer can Not Be created");
			return "redirect:/view-customer";
		}
		return "edit-customer";

	}

	@RequestMapping(value = EDIT_CUSTOMER, method = RequestMethod.POST)
	public String updateCustomer(@Valid @ModelAttribute("customerBo") CustomerBO customerBo ,Model model, HttpServletRequest request,
			BindingResult bindingResult,HttpSession session) throws FileNotFoundException{
		Boolean success;
		if(bindingResult.hasErrors()){
			System.out.println(bindingResult.getErrorCount());
		}

		if(null!=request.getParameter("customerid")){
			String id=request.getParameter("customerid");
			int customerId=Integer.parseInt(id);
			customerBo.setCustomerId(customerId);
		}
		if(0!=customerBo.getCustomerId()){
			success=customerService.updateCustomer(customerBo);
			if(success){
				model.addAttribute("sucessMessage", TechbootResourceBundle.getValue("update.customer"));
				return "redirect:/view-customer";
			}else{
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("edite.errer"));
				return "redirect:/view-customer";
			}
		}

		return "redirect:/view-customer";
	}

	@RequestMapping(value = DELETE_CUSTOMER, method = RequestMethod.GET)
	public String deleteCustomer(Model model, HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		//checking login
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		CustomerBO customer=new CustomerBO();
		if(null!=request.getParameter("customerid")){
			String id=request.getParameter("customerid");
			int customerId=Integer.parseInt(id);
			customer.setCustomerId(customerId);
		}

		if(0!=customer.getCustomerId()){
			Boolean success=customerService.deleteCustomer(customer);
			if(success){
				model.addAttribute("sucessMessage", TechbootResourceBundle.getValue("delete.customer"));
				return "redirect:/view-customer";
			}
			else {
				return "redirect:/view-customer";
			}
		}

		return "redirect:/view-customer";
	}
	@RequestMapping(value = VIEW_CUSTOMER_DETAILS, method = RequestMethod.GET)
	public String viewDetailsCustomer(Model model, HttpServletRequest request,HttpSession session){

		CustomerBO customer=new CustomerBO();
		CustomerBO customerBO=new CustomerBO();
		if(null!=request.getParameter("viewcustomer")){
			String id=request.getParameter("viewcustomer");
			int customerId=Integer.parseInt(id);
			customer.setCustomerId(customerId);
		}
		if(0!=customer.getCustomerId()){
			customerBO=customerService.viewCustomerDetails(customer);
			if(null!=customerBO){
				model.addAttribute("viewcustomer", customerBO);
			}
			else{
				return "redirect:/view-customer";
			}
		}
		else{
			return "redirect:/view-customer";
		}

		return "customer-viewdetails";
	}


	@RequestMapping(value = SEARCH_CUSTOMER, method = RequestMethod.POST)
	public String searchCustomer(@ModelAttribute("customerBo") CustomerBO customerBo ,Model model, HttpServletRequest request,
			HttpSession session) throws FileNotFoundException{
		long totalCustomercount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}

		String paging=null;
		List<CustomerBO> customerLists=new ArrayList<CustomerBO>();
		List<CustomerBO> listsOfCustomer=new ArrayList<CustomerBO>();
		//search Second page
		if(null!=customerBo.getFirstName()){
			model.addAttribute("searcgValue", customerBo.getFirstName());
		}
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
			page=Integer.parseInt(paging);
		}


		listsOfCustomer=customerService.totalSearchCustomer(customerBo);
		if(null!=listsOfCustomer && !listsOfCustomer.isEmpty() && listsOfCustomer.size()>0){
			totalCustomercount=listsOfCustomer.size();
		}
		else{
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
			return "redirect:/view-customer";
		}

		//search pagination
		int startingRecordIndex = paginationPageValues(page, maxRecord);
		customerBo.setRecordIndex(startingRecordIndex);
		customerBo.setMaxRecord(maxRecord);
		//customerBo.setPagination("pagination");


		if(null!=customerBo){
			customerLists=customerService.searchCustomer(customerBo);
			if(null!=customerLists && !customerLists.isEmpty() && customerLists.size()>0){
				//totalCustomercount=customerLists.size();
				model.addAttribute("customerLists",
						Pagination.paginationLimitedRecords(page, customerLists, maxRecord, totalCustomercount));
			}
		}
		return "view-customer";
	}
}