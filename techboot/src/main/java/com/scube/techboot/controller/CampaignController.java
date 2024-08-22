package com.scube.techboot.controller;


import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.scube.techboot.bo.CampaignBO;
import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.CourseBO;
import com.scube.techboot.bo.ProductServiceBO;
import com.scube.techboot.service.CampaignService;
import com.scube.techboot.service.CompanyService;
import com.scube.techboot.service.CourseService;
import com.scube.techboot.service.ProductService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.SaveImagesToFolder;
import com.scube.techboot.utils.TechbootResourceBundle;

@Controller
public class CampaignController {

	private static final String CREATE_CAMPAIGN ="/create-campaign";
	private static final String VIEW_CAMPAIGN ="/view-campaign";
	private static final String EDIT_CAMPAIGN ="/edit-campaign";
	private static final String DELETE_CAMPAIGN ="/delete-campaign";
	private static final String VIEW_CAMPAIGN_DETAILS ="/view-campaign-details";
	private static final String SEARCH_CAMPAIGN ="/search-Campaign";
	private static final String GET_COMPANYTYPE="/getCompanyType";
	private static final String RETRIVE_SERVICE ="/retriveService";
	private static final String RETRIVE_COURSE ="/retriveCourse";


	@Autowired
	private ProductService productService;
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private CampaignService campaignService;
	@Autowired
	private CompanyService companyservice;
	
	@ModelAttribute("listservice")
	public List<ProductServiceBO> getListService(HttpSession session) {
		List<ProductServiceBO> listservice=new ArrayList<ProductServiceBO>();
		ProductServiceBO ProductService=new ProductServiceBO();
		ProductService.setIsDelete(false);
		if(null!=session.getAttribute("companyId")){
			int id=(int) session.getAttribute("companyId");
			CompanyBO companyBO=new CompanyBO();
			companyBO.setCompanyId(id);
			ProductService.setCompanyBO(companyBO);
			listservice=productService.listofpageservice(ProductService);
		}
		else{
			listservice=productService.listservice(ProductService);
		}
		return listservice;
	}
	
	@ModelAttribute("listCourse")
	public List<CourseBO> getListCourse(HttpSession session) {
		   List<CourseBO> listCourse=new ArrayList<CourseBO>();
		CourseBO courseBO=new CourseBO();
		courseBO.setIsDelete(false);
		if(null!=session.getAttribute("companyId")){
			int id=(int) session.getAttribute("companyId");
			CompanyBO companyBO=new CompanyBO();
			companyBO.setCompanyId(id);
			courseBO.setCompanyBO(companyBO);
			listCourse=courseService.getViewCourseList(courseBO);
		}
		else{
			listCourse=courseService.getViewCourseList(courseBO);
		}
		return listCourse;
	}

	@ModelAttribute("companylist")
	public List<CompanyBO> getListCompany(HttpSession session) {

		CompanyBO companybo=new CompanyBO();
		companybo.setIsDelete(false);
		List<CompanyBO> companylist=companyservice.retriveCompany(companybo);
		return companylist;
	}


	@RequestMapping(value=CREATE_CAMPAIGN ,method = RequestMethod.GET)
	public String getCampaign(Model model, HttpServletRequest request,HttpSession session){
    	String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		System.out.println("create-campaign");
		CampaignBO campaign=new CampaignBO();
		model.addAttribute("campaignBO", campaign);
		return "create-campaign";
	}


	@RequestMapping(value=CREATE_CAMPAIGN ,method = RequestMethod.POST)
	public String postCampaign(@Valid @ModelAttribute("campaignBO") CampaignBO campaignBO,BindingResult bindingResult, Model model, HttpServletRequest request
			,HttpSession session, @RequestParam("Template") MultipartFile excelfile) throws IOException{

		String name=null;
		String path=null;
		String templateName=null;
		if(bindingResult.hasErrors()){
			return "create-campaign";
		}

		if(campaignBO.getCategory().equalsIgnoreCase("Email") && null!=campaignBO.getCategory()){
			if(null!=excelfile){
				long campaignId=campaignService.lsatObject();
				long value=campaignId+1;
				path=TechbootResourceBundle.getValue("company.Template");
				//String imgContentType = excelfile.getContentType();
				name=excelfile.getOriginalFilename();
				String v[]=name.split("\\.");
				templateName = value + "." + v[1];
				campaignBO.setMessage(templateName);
			}
		}
		CompanyBO companyBO=new CompanyBO();
		if(null!=campaignBO){
			//company
			if(null!=session.getAttribute("companyId")){
				int id=(int) session.getAttribute("companyId");
				companyBO.setCompanyId(id);
				campaignBO.setCompanyBO(companyBO);
			}
			//admin
			else{
				String companyName=campaignBO.getCompanyBO().getCompanyName();
				int id=Integer.parseInt(companyName);
				companyBO.setCompanyId(id);
				campaignBO.setCompanyBO(companyBO);
			}
		}
		
		String name1=null;
		if(null!=campaignBO){
			CampaignBO campaign=new CampaignBO();
			
			if(null!=campaignBO.getProductService().getServiceName()&&!campaignBO.getProductService().getServiceName().equalsIgnoreCase("select")){
				name1=campaignBO.getProductService().getServiceName();
				long serviceId=Long.parseLong(name1);
				ProductServiceBO productServiceBo=new ProductServiceBO();
				productServiceBo.setServiceId(serviceId);
				campaignBO.setProductService(productServiceBo);
			}
			if(null!=campaignBO.getCourse().getCourseName()&&!campaignBO.getCourse().getCourseName().equalsIgnoreCase("select"))
			{
				name1=campaignBO.getCourse().getCourseName();
				int courseId=Integer.parseInt(name1);
				CourseBO courseBo=new CourseBO();
				courseBo.setCourseId(courseId);
				campaignBO.setCourse(courseBo);;
			}
				

				if(null!=name1){
					campaign=campaignService.saveCompaign(campaignBO,session);

					/*if(null!=campaign){
						CampaignBO newCampaign=campaignService.getCampaignObject(campaign);
						//SMS Function
						if(null!=newCampaign.getProductService().getServiceSpecification()){
							boolean status=campaignService.getSpecification(newCampaign);
						}
					}*/

					if(null!=campaign &&campaignBO.getCategory().equalsIgnoreCase("Email") && null!=campaignBO.getCategory()){
						SaveImagesToFolder.saveImageToFolder(templateName, excelfile, path);
					}

					if(null!=campaign){
						model.addAttribute("successMessage", TechbootResourceBundle.getValue("create.campaign"));
						return "redirect:/view-campaign";
					}
				}
			}	

		model.addAttribute("message", TechbootResourceBundle.getValue("error.campaign"));
		return "redirect:/view-campaign";
	}

	@RequestMapping(value=VIEW_CAMPAIGN ,method = RequestMethod.GET)
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

	@RequestMapping(value=EDIT_CAMPAIGN, method=RequestMethod.GET)
	public String editCampaign(Model model, HttpServletRequest request,HttpSession session){
		
    	String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}
        CampaignBO campaignBo=new CampaignBO();
		CampaignBO campaign=new CampaignBO();
		if(null!=request.getParameter("campaignId")){
			String value=request.getParameter("campaignId");
			int id=Integer.parseInt(value);
			campaignBo.setCampaignId(id);
		}
		if(0!=campaignBo.getCampaignId()){
			campaign=campaignService.getCampaignObject(campaignBo);
		}
		if(null!=campaign){
			session.setAttribute("campaignCT", campaign.getCreatedTime());
			session.setAttribute("campaignCB", campaign.getCreatedBy());
			session.setAttribute("edit-CompanyId", campaign.getCompanyBO().getCompanyId());
			model.addAttribute("campaign", campaign);
			return "edit-campaign";
		}
		return "redirect:/view-campaign";

	}

	@RequestMapping(value=EDIT_CAMPAIGN, method=RequestMethod.POST)
	public String editCampaign(@Valid @ModelAttribute("campaign")CampaignBO campaignBo,Model model,HttpServletRequest request,HttpServletResponse response,BindingResult bindingResult,
			HttpSession session) throws FileNotFoundException{

		if(null!=request.getParameter("campaignId")){
			String value=request.getParameter("campaignId");
			int id=Integer.parseInt(value);
			campaignBo.setCampaignId(id); 
		}
	
		if(null!=campaignBo){
			boolean status=campaignService.updateCampaign(campaignBo, session);
			if(status){
				model.addAttribute("successMessage", TechbootResourceBundle.getValue("update.campaign"));
				return "redirect:/view-campaign";
			}
		}
		return "redirect:/view-campaign";
	}

	@RequestMapping(value=DELETE_CAMPAIGN, method=RequestMethod.GET)
	public String deleteCampaign(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		
		String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}
        CampaignBO campaignBo=new CampaignBO();
		if(null!=request.getParameter("campaignId")){
			String value=request.getParameter("campaignId");
			int id=Integer.parseInt(value);
			campaignBo.setCampaignId(id); 
		}
		if(0!=campaignBo.getCampaignId()){
			boolean status=campaignService.deleteCampaign(campaignBo);
			if(status){
				model.addAttribute("successMessage", TechbootResourceBundle.getValue("delete.campaign"));
				return "redirect:/view-campaign";
			}
		}
		return "redirect:/view-campaign";

	}

	@ResponseBody
	@RequestMapping(value=RETRIVE_SERVICE, method=RequestMethod.GET)
	public List<ProductServiceBO> listOfService(@RequestParam String companyId,HttpServletRequest request,HttpServletResponse response,Model model){
		
     ProductServiceBO ServiceBO=new ProductServiceBO();
		if(null!=companyId){
			CompanyBO companyBO=new CompanyBO();
			String value=companyId;
			int companyid=Integer.parseInt(value);
			companyBO.setCompanyId(companyid);
			ServiceBO.setCompanyBO(companyBO);
			List<ProductServiceBO> listservices=productService.listofpageservice(ServiceBO);
			if(null!=listservices && !listservices.isEmpty()){
				model.addAttribute("listservices", listservices);
				return listservices;
			}
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value=RETRIVE_COURSE, method=RequestMethod.GET)
	public List<CourseBO> getViewCourseList(@RequestParam String companyId,HttpServletRequest request,HttpServletResponse response,Model model){
		
     CourseBO courseBO=new CourseBO();
		if(null!=companyId){
			CompanyBO companyBO=new CompanyBO();
			String value=companyId;
			int companyid=Integer.parseInt(value);
			companyBO.setCompanyId(companyid);
			courseBO.setCompanyBO(companyBO);
			List<CourseBO> listCourse=courseService.getViewCourseList(courseBO);
			if(null!=listCourse && !listCourse.isEmpty()){
				model.addAttribute("listCourse", listCourse);
				return listCourse;
			}
		}
		return null;
	}

	@RequestMapping(value=VIEW_CAMPAIGN_DETAILS, method=RequestMethod.GET)
	public String viewCampaignMessage(Model model,HttpServletRequest request,HttpSession session){
		
		String values=CheckingStatus.checkSession(request,session);
		if(null!=values){
			return values;
		}
		CampaignBO campaignBo=new CampaignBO();
		if(null!=request.getParameter("campaignId")){
			String value=request.getParameter("campaignId");
			int id=Integer.parseInt(value);
			campaignBo.setCampaignId(id);
		}
		campaignBo=campaignService.getCampaignObject(campaignBo);
		if(null!=campaignBo.getMessage()){
			model.addAttribute("campaignBo",campaignBo);
			return "view-campaign-details";
		}

		return "redirect:/view-campaign";

	}

	@RequestMapping(value=SEARCH_CAMPAIGN, method=RequestMethod.POST)
	public String searchCampaign(@ModelAttribute("campaignBO") CampaignBO campaignBO,Model model,HttpServletRequest request,
			HttpSession session) throws FileNotFoundException{

		long count=0;
		long totalcampaigncount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		}
		List<CampaignBO> campaignlist=new ArrayList<CampaignBO>();
		//Second search value
		if(null!=campaignBO.getCampaignName()){
			model.addAttribute("searchvalue",campaignBO.getCampaignName());
		}
		if(null!=campaignBO){
			campaignBO.setIsDelete(false);
			campaignBO.setSending_status(true);

			//company
			if(null!=session.getAttribute("companyId")){
				int id=(int) session.getAttribute("companyId");
				CompanyBO companyBo=new CompanyBO();
				companyBo.setCompanyId(id);
				campaignBO.setCompanyBO(companyBo);
				count=campaignService.listOfCompanyCampaign(campaignBO);
			}
			
			else{
				count=campaignService.listOfCampaignCount(campaignBO);
			}
			if(0!=count){
				totalcampaigncount=count;
			}
			else{
				model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.search"));
				return  "redirect:/view-campaign";
			}
			//Search pagination
			int StartingRecordIndex=paginationPageValues(page, maxRecord);
			campaignBO.setRecordIndex(StartingRecordIndex);
			campaignBO.setMaxRecord(maxRecord);
			campaignBO.setPagination("pagination");

			//limited Record
			campaignlist=campaignService.searchCampaign(campaignBO);
			if(null!=campaignlist && !campaignlist.isEmpty() && campaignlist.size()>0){
				model.addAttribute("campaignLists",Pagination.paginationLimitedRecords(page, campaignlist, maxRecord, totalcampaigncount));
			}
		}
		return "view-campaign";	
	}
	
	 @ResponseBody
	    @RequestMapping(value=GET_COMPANYTYPE,method=RequestMethod.GET)
	    public String getCompanyType(@RequestParam String companyId,HttpServletRequest request,
	    		HttpServletResponse response,Model model) throws Exception{
	    	if(null!=companyId && !companyId.isEmpty()){
	    		CompanyBO company=new CompanyBO();
	    		int id=Integer.parseInt(companyId);
	    		company.setCompanyId(id);
	    		company=companyservice.getCompanyObject(company);
	    		String companyType=null;
	    	
	    		if(null!=company){
	    			companyType=company.getCompanyType();
	    			return companyType;
	    		}  		
			
	    		}
			return null;
	    	
	    }
}
