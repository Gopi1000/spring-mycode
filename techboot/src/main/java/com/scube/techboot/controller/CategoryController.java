package com.scube.techboot.controller;


import java.io.FileNotFoundException;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.service.CategoryService;
import com.scube.techboot.utils.TechbootResourceBundle;

@Controller
public class CategoryController extends ControllerUtils{
	@Autowired
	CategoryService categoryservice;
	private static final  String  CREATE_CATEGORY="/create-category";
	private static final  String  VIEW_CATEGORY="/view-category";
	private static final  String  CHECK_CATEGORY_NAME = "/check_categoryName";
	private static final  String  EDIT_CATEGORY="/edit-category";
	private static final  String  DELETE_CATEGORY="/delete-category";
	private static final  String  SEARCH_CATEGORY="/search-category";
	
	@GetMapping(value=CREATE_CATEGORY)
	public String getCategory(Model model){
		//checking admin
		if(null==getUserRole()|| !getUserRole().equalsIgnoreCase("admin")){
			return "redirect:/sign-in";
		}
		model.addAttribute("CategoryBO",new CategoryBO());
		return "create-category";
		}
	
	@PostMapping(value=CREATE_CATEGORY)
	public String postCategory(@Valid @ModelAttribute("CategoryBO") CategoryBO categoryBo,BindingResult bindingResult,Model model,
		HttpServletRequest request,HttpSession session) throws Exception{
		int id=0;
		if(bindingResult.hasErrors()){
			return "create-category";
		}
		//checking admin
		if(null==getUserRole() || !getUserRole().equalsIgnoreCase("admin")){
			return "redirect:/sign-in";
		}
		if(null!=categoryBo && null!=session.getAttribute("adminId")){
			long adminId=(long) session.getAttribute("adminId");
			categoryBo.setCreatedBy(adminId);	
			id=categoryservice.saveCategory(categoryBo);
			}
		if(0!=id){
			model.addAttribute("successMessage",TechbootResourceBundle.getValue("create.category"));
			}
		else{
			model.addAttribute("errorMessage",TechbootResourceBundle.getValue("error.category"));
			return "redirect:/create-category";
		}
		return "redirect:/view-category";
		}
	
	@GetMapping(value=VIEW_CATEGORY)
	public String viewListOfCategory(Model model,HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		//checking admin
		if(null==getUserRole()|| !getUserRole().equals("admin")){
			return "redirect:/sign-in";
		}
		 String paging=null;
		 if(null!=request.getParameter("successMessage")){
			 model.addAttribute("successMessage",request.getParameter("successMessage"));
		 }
		 if(null!=request.getParameter("errorMessage")){
			 model.addAttribute("errorMessage",request.getParameter("errorMessage"));
		 }
		 if(null!=request.getParameter("page")){
			 paging=request.getParameter("page");
		 }
		 CategoryBO categoryBO=new CategoryBO();
		 model.addAttribute("categoryValue",new CategoryBO());
		 catogryPagination(categoryBO,paging,model,session);	
		 
		return"view-category";
	}
	
	private void catogryPagination(CategoryBO categoryBO, String paging, Model model, HttpSession session) throws FileNotFoundException {
		 List<CategoryBO> catgoryList=new ArrayList<CategoryBO>();
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
			 count=categoryservice.retrieveCategoryCount(categoryBO);
		 }
		 if(0!=count){
			 totalCategoryCount=count;
		 }
		 int StartingRecordIndex=paginationPageValues(page, maxRecord);
		 categoryBO.setRecordIndex(StartingRecordIndex);
		 categoryBO.setMaxRecord(maxRecord);
		 categoryBO.setPagination("pagination");	
		
		  catgoryList=categoryservice.viewCatogery(categoryBO);
		  if(null!=catgoryList && !catgoryList.isEmpty() && catgoryList.size()>0){
			  model.addAttribute("catgoryList", Pagination.paginationLimitedRecords(page, catgoryList, maxRecord, totalCategoryCount));
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
	 
	 @PostMapping(value=SEARCH_CATEGORY)
	 public String postSearchCategory(@Valid @ModelAttribute("categoryValue") CategoryBO categoryBo,BindingResult bindingResult,Model model,
			HttpServletRequest request,HttpSession session) throws Exception{
			int id=0;
			if(bindingResult.hasErrors()){
				return "redirect:/view-category";
			}
			 model.addAttribute("categoryValue",new CategoryBO());
			 List<CategoryBO> categoryList=new ArrayList<CategoryBO>();
			 long totalCategorycount=0;
			 int page=1;
			 int maxRecord=0;
			 String Record=TechbootResourceBundle.getValue("pagination.count");
			 if(null!=Record){
				 maxRecord=Integer.parseInt(Record);
			 }
			 if(null!=categoryBo){
				 int totalCategory=categoryservice.searchCategory(categoryBo);
				 if(0!= totalCategory){
					 totalCategorycount=totalCategory;
				 }
				 else{
					 model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.searchcategory"));
					 return  "redirect:/view-category";
				 }
			//search pagination
			 int startingRecordIndex = paginationPageValues(page, maxRecord);
			 categoryBo.setRecordIndex(startingRecordIndex);
			 categoryBo.setMaxRecord(maxRecord);
			 categoryBo.setPagination("pagination");
			 //Limit Recored 1-10
			 categoryList=categoryservice.searchPageCategory(categoryBo);
			 if(null!=categoryList && !categoryList.isEmpty() && categoryList.size()>0){
				model.addAttribute("catgoryList",
							 Pagination.paginationLimitedRecords(page, categoryList, maxRecord, totalCategorycount));
			 	}
			 }
		 return "view-category";
	 }
	 
	@ResponseBody
	@GetMapping(value=CHECK_CATEGORY_NAME)
	public String checkCategory(@RequestParam String categoryName,HttpServletRequest request, HttpServletResponse response){
		boolean status;
		CategoryBO categoryBO = new CategoryBO();
		categoryBO.setCategory(categoryName);
		if(status=categoryservice.categoryNameValidations(categoryBO)){
			return "OK";
				}
		return "NOTOK";
	}
	
	@GetMapping(value=EDIT_CATEGORY)
	public String editCategory(Model model,HttpServletRequest request) throws Exception{
		//checking admin
		if(null==getUserRole() || !getUserRole().equalsIgnoreCase("admin")){
			return "redirect:/sign-in";
		}
		CategoryBO categoryBo= new CategoryBO();
		if(null!=request.getParameter("categoryId")){
			categoryBo.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			categoryBo.setIsDelete(false);
			categoryBo.setSending_status(true);
			categoryBo=categoryservice.retrieveCategory(categoryBo);
		}
		if(null!=categoryBo){
			model.addAttribute("CategoryBO", categoryBo);
		}
		return "edit-category";
	}
		
	@PostMapping(value=EDIT_CATEGORY)
	public String editpostCategory(@Valid @ModelAttribute("CategoryBO") CategoryBO categoryBo,BindingResult bindingResult
			,Model model,HttpServletRequest request,HttpSession session) throws Exception{
		boolean status=false;
		if(bindingResult.hasErrors()){
			return "edit-category";
		}
		//checking login
		if(null!=getUserRole() && getUserRole().equalsIgnoreCase("admin")) {
			if(null!=session.getAttribute("adminId")){
			long adminId= (long) session.getAttribute("adminId");
			categoryBo.setModifiedBy(adminId);
			}
			status=categoryservice.updateSaveStudent(categoryBo);
		}else{
			return "redirect:/sign-in";
		}
		if(status){
			model.addAttribute("successMessage", TechbootResourceBundle.getValue("update.categoryname"));
		}else{
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.edit"));
		}
		return "redirect:/view-category";
	}
	
	@GetMapping(value=DELETE_CATEGORY)
	public String deleteCategory(Model model,HttpServletRequest request) throws Exception{
		boolean status=false;
		CategoryBO categoryBo= new CategoryBO();
		if(null!=request.getParameter("categoryId")){
			categoryBo.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			categoryBo.setIsDelete(true);
			categoryBo.setSending_status(false);
			status=categoryservice.deleteCategory(categoryBo);
		}
		if(status){
			model.addAttribute("successMessage", TechbootResourceBundle.getValue("category.delete"));
		}else{
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.delete"));
		}
		return "redirect:/view-category";
	}
	
	/*@RequestMapping(value=VIEW_CATEGORY, method=RequestMethod.GET)
	public String viewcat(Model m) throws ClassNotFoundException, SQLException{ 
		//List<CategoryBO> list=categoryservice.viewcat();
		 m.addAttribute("list",list); 
		 
		 return "view-category"; 

	}*/
	
}