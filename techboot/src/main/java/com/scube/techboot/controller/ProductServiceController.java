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

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.ProductServiceBO;
import com.scube.techboot.service.CampaignService;
import com.scube.techboot.service.CompanyService;
import com.scube.techboot.service.ProductService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.TechbootResourceBundle;


@Controller
public class ProductServiceController {
	//URL
	private static final String CREATE_SERVICE ="/create-service";
	private static final String VIEW_SERVICE ="/view-service";
	private static final String EDIT_SERVICE ="/edit-service";
	private static final String DELETE_SERVICE ="/delete-service";
	//private static final String VIEW_SERVICEDEATILS ="/view-serviceDetails";
	private static final String SEARCH_SERVICEDEATILS ="/search-service";
	private static final String VIEW_SERVICEDETAILS ="/view-serviceDetails";


	@Autowired
	private ProductService productService;

	@Autowired
	private CompanyService companyservice;

	@Autowired
	private CampaignService campaignService;


	@RequestMapping(value=CREATE_SERVICE,method=RequestMethod.GET )
	public String getCreateService(Model model,HttpServletRequest request,HttpSession session) throws Exception{

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		ProductServiceBO productServiceBO=new ProductServiceBO();
		//admin
		if(null!=session.getAttribute("adminId")){
			CompanyBO companyBO=new CompanyBO();
			companyBO.setIsDelete(false);
			List<CompanyBO> companyList=companyservice.retriveCompany(companyBO);
			if(null!=companyList && !companyList.isEmpty()){
				model.addAttribute("companyList", companyList);
			}
		}
		if(null!=request.getParameter("errorMessage")){
			model.addAttribute("errorMessage",request.getParameter("errorMessage"));
		}
		model.addAttribute("productService",productServiceBO);
		return "create-service";


	}
	@RequestMapping(value=CREATE_SERVICE , method=RequestMethod.POST)
	public String postCreateServices(@Valid @ModelAttribute("productService")ProductServiceBO productServiceBO,BindingResult bindingResult,
			Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws FileNotFoundException{
		boolean status;
		if(bindingResult.hasErrors()){
			return "create-service";
		}
		if(null!=session.getAttribute("companyId")) {
			int companyid=(int) session.getAttribute("companyId");
			CompanyBO companyBo=new CompanyBO();
			companyBo.setCompanyId(companyid);
			productServiceBO.setCompanyBO(companyBo);
			productServiceBO.setCreatedBy(companyid);
		}
		if(null!=session.getAttribute("adminId")){
			Long id=(Long) session.getAttribute("adminId");
			productServiceBO.setCreatedBy(id);	
			if(null!=productServiceBO.getCompanyBO().getCompanyName()){
				String value=productServiceBO.getCompanyBO().getCompanyName();
				int companyId=Integer.parseInt(value);
				CompanyBO companyBo=new CompanyBO();
				companyBo.setCompanyId(companyId);
				productServiceBO.setCompanyBO(companyBo);
			}
		}
		if(status=productService.isValidServiceName(productServiceBO)){
			model.addAttribute("errorMessage",TechbootResourceBundle.getValue("error.service"));
			return "redirect:/create-service";
		}
		if(null!=productServiceBO){
			ProductServiceBO company=productService.createServices(productServiceBO);
			if(0!=company.getServiceId()){
				model.addAttribute("succesmessage", TechbootResourceBundle.getValue("create.service"));
				return "redirect:/view-service";
			}
		}
		return "create-service";
	}


	@RequestMapping(value = VIEW_SERVICE, method = RequestMethod.GET)
	public String viewService(Model model, HttpServletRequest request,HttpSession session, String paging) throws FileNotFoundException{

		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		//using
		model.addAttribute("productService",new ProductServiceBO());

		if(null!=request.getParameter("succesmessage")){
			model.addAttribute("succesmessage", request.getParameter("succesmessage") );
		}
		if(null!=request.getParameter("errorMessage")) {
			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
		}
		ProductServiceBO ServiceBO=new ProductServiceBO();

		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
		}
		//pagination
		productServicepagination(ServiceBO,paging,model,session);
		return "view-service";

	}

	private void productServicepagination(ProductServiceBO serviceBO, String paging, Model model, HttpSession session) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<ProductServiceBO>serviceList=new ArrayList<ProductServiceBO>();
		List<ProductServiceBO>productList=new ArrayList<ProductServiceBO>();
		long totalservicecount=0;
		int page=1;
		int maxRecord=0;
		long totalServiceRecordCount=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}
		if(null!=paging){
			page=Integer.parseInt(paging);
		}
		//company
		if(null!=session.getAttribute("companyId")){
			int id=(int) session.getAttribute("companyId");
			CompanyBO companyBO=new CompanyBO();
			companyBO.setCompanyId(id);
			serviceBO.setCompanyBO(companyBO);
			serviceList=productService.listservice(serviceBO);

		}//admin
		else{
			serviceList=productService.listservice(serviceBO);
		}
		if((null!=serviceList && !serviceList.isEmpty() && serviceList.size()>0)){
			totalServiceRecordCount=serviceList.size();
			totalservicecount=totalServiceRecordCount;
		}
		int startingRecordIndex = paginationPageValues(page, maxRecord);
		serviceBO.setRecordIndex(startingRecordIndex);
		serviceBO.setMaxRecord(maxRecord);
		serviceBO.setPagination("pagination");
		//company
		if(null!=session.getAttribute("companyId")){
			int id=(int) session.getAttribute("companyId");
			CompanyBO companyBO=new CompanyBO();
			companyBO.setCompanyId(id);
			serviceBO.setCompanyBO(companyBO);
			productList=productService.listofpageservice(serviceBO);
		}//admin
		else{
			productList=productService.listofpageservice(serviceBO);
		}
		if(null!=productList && !productList.isEmpty() && productList.size()>0){
			List<ProductServiceBO> productlists=new ArrayList<ProductServiceBO>();
			productlists.addAll(productList);
			model.addAttribute("serviceList",Pagination.paginationLimitedRecords(page, productlists, maxRecord, totalservicecount));
		}
		else{
			model.addAttribute("infoMessage", TechbootResourceBundle.getValue("info.services"));
		}
	}

	private int paginationPageValues(int page, int maxRecord) {
		// TODO Auto-generated method stub
		int pageRecords = 0;
		if (page == 1) {
			pageRecords = 0;
		} else {
			pageRecords = (page- 1) * maxRecord + 1;
			pageRecords = pageRecords - 1;
		}
		return pageRecords;
	}

	@RequestMapping(value =VIEW_SERVICEDETAILS, method = RequestMethod.GET)
	public String viewDetailsService(Model model, HttpServletRequest request,HttpSession session){
		int id = 0;
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		ProductServiceBO productServiceBO=new ProductServiceBO();
		CompanyBO companyBO=new CompanyBO();
		if(null!=session.getAttribute("companyId")){
			id=(int) session.getAttribute("companyId");
			companyBO.setCompanyId(id);
			productServiceBO.setCompanyBO(companyBO);
		}
			/*String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}*/
			ProductServiceBO productServiceBo=new ProductServiceBO();
			ProductServiceBO serviceBo=new ProductServiceBO();
			if(null!=request.getParameter("serviceid")){
				String service=request.getParameter("serviceid");
				int id1=Integer.parseInt(service);
				productServiceBo.setServiceId(id1);
			}
			if(0!=productServiceBo.getServiceId()){
				serviceBo=productService.getServiceObject(productServiceBo);
			}

			if(null!=serviceBo){
				model.addAttribute("productService", serviceBo);
				return "view-service-details";
			}
			return "redirect:/view-service";

		}



		@RequestMapping(value=EDIT_SERVICE, method = RequestMethod.GET)
		public String getUpdateProductService(Model model, HttpServletRequest request,HttpSession session){

			String value=CheckingStatus.checkSession(request,session);
			if(null!=value){
				return value;
			}
			ProductServiceBO productServiceBo=new ProductServiceBO();
			ProductServiceBO serviceBo=new ProductServiceBO();
			if(null!=request.getParameter("serviceid")){
				String service=request.getParameter("serviceid");
				int id=Integer.parseInt(service);
				productServiceBo.setServiceId(id);
			}
			if(0!=productServiceBo.getServiceId()){
				serviceBo=productService.getServiceObject(productServiceBo);
			}
			if(null!=serviceBo){
				model.addAttribute("productService", serviceBo);
				return "edit-service";
			}
			return "redirect:/view-service";

		}


		@RequestMapping(value=EDIT_SERVICE , method=RequestMethod.POST)
		public String postUpdateServices(@Valid @ModelAttribute("productService")ProductServiceBO productServiceBo,
				Model model,HttpServletRequest request,HttpServletResponse response,
				HttpSession session) throws FileNotFoundException{

			Boolean status;
			if(null!=request.getParameter("serviceid")){
				String service=request.getParameter("serviceid");
				int id=Integer.parseInt(service);
				productServiceBo.setServiceId(id);
			}
			if(0!=productServiceBo.getServiceId()){
				if(status=productService.serviceUpdate(productServiceBo)){
					model.addAttribute("succesmessage", TechbootResourceBundle.getValue("updated.service"));
					return "redirect:/view-service";
				}
			}
			return "redirect:/view-service";
		}

		@RequestMapping(value=DELETE_SERVICE, method=RequestMethod.GET)
		public String deleteService(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{

			String value=CheckingStatus.checkSession(request,session);
			if(null!=value){
				return value;
			}
			Boolean status;
			ProductServiceBO service=new ProductServiceBO();
			if(null!=request.getParameter("serviceid"));{
				String id=request.getParameter("serviceid");
				long serviceId=Long.parseLong(id);
				service.setServiceId(serviceId);
			}
			if(0!=service.getServiceId()){
				if(status=campaignService.checkCampaign(service)){
					model.addAttribute("succesmessage" , TechbootResourceBundle.getValue("info.service"));
					return "redirect:/view-service";
				}
				if(status=productService.deleteService(service)){
					model.addAttribute("succesmessage", TechbootResourceBundle.getValue("deleted.service"));
					return "redirect:/view-service";
				}
				return "redirect:/view-service";
			}
			return "redirect:/view-service";

		}

		/*@RequestMapping(value =VIEW_SERVICEDEATILS, method = RequestMethod.GET)
	public String viewDetailsSrevice(Model model, HttpServletRequest request,HttpSession session){
		int id = 0;
		String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		ProductServiceBO productServiceBO=new ProductServiceBO();
		CompanyBO companyBO=new CompanyBO();
		if(null!=session.getAttribute("companyId")){
			id=(int) session.getAttribute("companyId");
					}

		companyBO.setCompanyId(id);
		productServiceBO.setCompanyBO(companyBO);
			productServiceBO=productService.getServiceObject(productServiceBO);

		if(null!=productServiceBO){
			model.addAttribute("productService", productServiceBO);
			return "view-servicedetails";
		}
		return "redirect:/view-service";

	}*/


		@RequestMapping(value=SEARCH_SERVICEDEATILS, method=RequestMethod.POST)
		public String searchServices(@ModelAttribute("productService")ProductServiceBO productServiceBo,
				Model model,HttpServletRequest request,HttpServletResponse response,
				HttpSession session) throws FileNotFoundException{
			List<ProductServiceBO> productList=new ArrayList<ProductServiceBO>();
			int serviceCount;
			//using
			model.addAttribute("productService",new ProductServiceBO());
			long totalservicecount=0;
			int page=1;
			int maxRecord=0;
			long totalServiceRecordCount=0;
			String Record=TechbootResourceBundle.getValue("pagination.count");
			if(null!=Record){
				maxRecord=Integer.parseInt(Record);
			}

			//company
			if(null!=session.getAttribute("companyId")) {
				CompanyBO companyBO=new CompanyBO();
				int id=(int) session.getAttribute("companyId");
				companyBO.setCompanyId(id);
				productServiceBo.setCompanyBO(companyBO);
				serviceCount=productService.searchPageService(productServiceBo);
			}
			else {
				serviceCount=productService.searchPageService(productServiceBo);

			}
			if(null!=productServiceBo){
				if(0!=serviceCount)
					totalservicecount=serviceCount;
			}
			int startingRecordIndex = paginationPageValues(page, maxRecord);
			productServiceBo.setRecordIndex(startingRecordIndex);
			productServiceBo.setMaxRecord(maxRecord);
			//Limit Recored 1-10	
			//Company	
			if(null!=session.getAttribute("companyId")) {
				CompanyBO companyBO=new CompanyBO();
				int id=(int) session.getAttribute("companyId");
				companyBO.setCompanyId(id);
				productServiceBo.setCompanyBO(companyBO);
				productList=productService.listofpageservice(productServiceBo);
			}
			else {
				productList=productService.listofpageservice(productServiceBo);
			}

			if((null!=productList && !productList.isEmpty() && productList.size()>0)){
				model.addAttribute("serviceList",Pagination.paginationLimitedRecords(page, productList, maxRecord, totalservicecount));
			}

			else{
				model.addAttribute("errorMessage" , TechbootResourceBundle.getValue("error.search"));
				return "redirect:/view-service";
			}

			return "view-service";
		}

	}
