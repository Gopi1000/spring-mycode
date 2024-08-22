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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.bo.SubcategoryBO;
import com.scube.techboot.service.CategoryService;
import com.scube.techboot.service.SubcategoryService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.TechbootResourceBundle;


@Controller
public class SubcategoryController {
	
@Autowired
	private SubcategoryService subcategoryservice;
@Autowired
private CategoryService categoryservice;
   List<SubcategoryBO> subcategorylists=new ArrayList<SubcategoryBO>();

private static final String CREATE_SUBCATEGORY="/create-subcategory";
private static final String VIEW_SUBCATEGORY="/view-subcategory";
private static final String SEARCH_SUBCATEGORY="/search-subcategory";
private static final String EDIT_SUBCATEGORY="/edit-subcategory";
private static final String CHECK_SUBCATEGORY_NAME="/check-subcategoryName";

public void getcategory(Model model) {
	List<CategoryBO> listcategory=new ArrayList<CategoryBO>();
	CategoryBO categorybo=new CategoryBO();
	categorybo.setIsDelete(false);
	
	SubcategoryBO subcategorybo=new SubcategoryBO();
		subcategorybo.setCategoryBO(categorybo);
		listcategory=categoryservice.viewcat();
	model.addAttribute("listcategory",listcategory);
	
}


	
	@RequestMapping(value=CREATE_SUBCATEGORY, method=RequestMethod.GET)
	public String getSubcategory(Model model, HttpServletRequest request,HttpSession session){
    	String value=CheckingStatus.checkSession(request,session);
		if(null!=value){
			return value;
		}
		getcategory(model);
		System.out.println("create-subcategory");
		SubcategoryBO subcategoryBO=new SubcategoryBO();
		model.addAttribute("subcategory", subcategoryBO);
		return "create-subcategory";
	}
	
	@RequestMapping(value=CREATE_SUBCATEGORY, method=RequestMethod.POST)
	public String postSubcategory(@Valid @ModelAttribute("subcategory") SubcategoryBO subcategoryBO,BindingResult bindingResult,Model model,
			HttpServletRequest request,HttpSession session) throws Exception{
		
		if(bindingResult.hasErrors()){
			System.out.println(bindingResult.getErrorCount());
			return "create-subcategory";
		}
		
		SubcategoryBO subcategory=subcategoryservice.savesubcategory(subcategoryBO,session);
		
		if(0!=subcategory.getSubcategoryId()){
			model.addAttribute("succesMessage", TechbootResourceBundle.getValue("create.subcategory"));
		}
		else{
			model.addAttribute("errorMessage",TechbootResourceBundle.getValue("error.subcategory"));
			return "redirect:/create-subcategory";
		}
			return "redirect:/view-subcategory";
		}
		

	
		
	@RequestMapping(value=VIEW_SUBCATEGORY, method=RequestMethod.GET)
	public String viewSubcategory(SubcategoryBO subcategoryBO,HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session)throws Exception
	{
	
		 String value=CheckingStatus.checkSession(request,session);
	 		if(null!=value){
	 			return value;
	 		}
	 		
	 		if(null!=request.getParameter("succesmessage")) {
	 			model.addAttribute("succesmessage", request.getParameter("succesmessage"));
	 		}
	 		if(null!=request.getParameter("errorMessage")) {
	 			model.addAttribute("errorMessage", request.getParameter("errorMessage"));
	 		}	
	 		     				
				model.addAttribute("subcategory",new SubcategoryBO());
				subcategoryBO.setIsDelete(false);
				
				subcategorylists.add(subcategoryBO);
				
				subcategorylists=subcategoryservice.getSubcategoryObject();        
	       
	        		
			if(null!=subcategorylists && subcategorylists.size()>0)
			{
				model.addAttribute("subcategorylists", subcategorylists);
			}
			
			return "view-subcategory";
			
			}	

		
		/*pagination
		 String paging=null;		

			
			model.addAttribute("subcategory",new SubcategoryBO());
			// subcategoryPagination(subcategoryBO,paging,model,session);		
			
			subcategorylists=subcategoryservice.getSubcategoryObject();             
        		
		if(null!=subcategorylists && subcategorylists.size()>0)
		{
			model.addAttribute("subcategorylists", subcategorylists);
		}		
		return "view-subcategory";
		
		}	*/
	
	private void subcategoryPagination(SubcategoryBO subcategoryBO, String paging, Model model, HttpSession session) throws FileNotFoundException {
		// TODO Auto-generated method stub
		 List<SubcategoryBO> subcategorylists=new ArrayList<SubcategoryBO>();
		 long count=0;
		 long totalCategoryCount=0;
		 int page=1;
		 int maxRecord=0;
		 String Record=TechbootResourceBundle.getValue("pagination.count");
		 maxRecord=Integer.parseInt(Record);
		 if(null!=paging){
			 page=Integer.parseInt(paging);
		 }
		 if(null!=session.getAttribute("adminId")){
			 count=subcategoryservice.retrieveSubcategoryCount(subcategoryBO);
		 }
		 if(0!=count){
			 totalCategoryCount=count;
		 }
		 int StartingRecordIndex=paginationPageValues(page, maxRecord);
		 subcategoryBO.setRecordIndex(StartingRecordIndex);
		 subcategoryBO.setMaxRecord(maxRecord);
		 subcategoryBO.setPagination("pagination");	
		
		 subcategorylists=subcategoryservice.viewSubcategory(subcategoryBO);
		  if(null!=subcategorylists && !subcategorylists.isEmpty() && subcategorylists.size()>0){
			  model.addAttribute("subcategorylists", Pagination.paginationLimitedRecords(page, subcategorylists, maxRecord, totalCategoryCount));
		  }
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
		
	



	@RequestMapping(value=EDIT_SUBCATEGORY, method = RequestMethod.GET)
	public String getUpdateProductService(Model model, HttpServletRequest request,HttpSession session){

		
		SubcategoryBO subcategoryBO=new SubcategoryBO();
		SubcategoryBO subcategorybo=new SubcategoryBO();
		if(null!=request.getParameter("subcategoryId")){
			String subcategory=request.getParameter("subcategoryId");
			int id=Integer.parseInt(subcategory);
			subcategoryBO.setSubcategoryId(id);
		}
		if(0!=subcategoryBO.getSubcategoryId()){
			subcategorybo= subcategoryservice.editSubcategoryObject(subcategoryBO);
		}
		if(null!=subcategorybo){
			model.addAttribute("subcategory", subcategorybo);
			getcategory(model);
			return "edit-subcategory";
		}
		return "redirect:/view-subcategory";

	}
	
	@RequestMapping(value=EDIT_SUBCATEGORY , method=RequestMethod.POST)
	public String postUpdateSubcategory(@Valid @ModelAttribute("subcategory")SubcategoryBO subcategoryBO,
			Model model,HttpServletRequest request,HttpServletResponse response,
			HttpSession session) throws FileNotFoundException{

		Boolean status;
		if(null!=request.getParameter("subcategoryId")){
			String subcategory=request.getParameter("subcategoryId");
			int id=Integer.parseInt(subcategory);
			subcategoryBO.setSubcategoryId(id);
		}
		if(null!=session.getAttribute("adminId")){
			long id= (long) session.getAttribute("adminId");
			subcategoryBO.setModifiedBy(id);
		}
		if(0!=subcategoryBO.getSubcategoryId()){
			if(status=subcategoryservice.subcategoryUpdate(subcategoryBO)){
				model.addAttribute("succesMessage", TechbootResourceBundle.getValue("update.subcategory"));}
				else
				{
					model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.edit"));
				
				return "redirect:/view-subcategory";
			}
		}
		return "redirect:/view-subcategory";
	}

	
	@RequestMapping(value=SEARCH_SUBCATEGORY, method=RequestMethod.POST)
	public String postsearch( HttpServletRequest request,HttpServletResponse response,@ModelAttribute("subcategory") SubcategoryBO subcategorybo,Model model)
	{
		
		
		//model.addAttribute("subcategory", new SubcategoryBO());
		//subcategorylists =subcategoryservice.searchSubcategory(subcategorybo.getSubcategory());
		subcategorylists =subcategoryservice.searchSubcategory(subcategorybo);
		
		if(null!=subcategorylists &&subcategorylists.size()>0)
		{
			model.addAttribute("subcategorylists", subcategorylists);
		}
		
			model.addAttribute("subcategory", new SubcategoryBO());
			
		
		
		return "view-subcategory";
	
}
	
	@ResponseBody
	@GetMapping(value=CHECK_SUBCATEGORY_NAME)
	public String checkSubcategory(@RequestParam String subcategoryName,HttpServletRequest request, HttpServletResponse response){
		boolean status;
		SubcategoryBO subcategoryBO = new SubcategoryBO();
		subcategoryBO.setSubcategory(subcategoryName);
		if(status=subcategoryservice.subcategoryNameValidations(subcategoryBO)){
			return "OK";
				}
		return "NOTOK";
	}

}
