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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.CourseCategoryBO;
import com.scube.techboot.bo.CourseRegistrationBO;
import com.scube.techboot.bo.SubcategoryBO;
import com.scube.techboot.bo.QuestionsBO;
import com.scube.techboot.service.QuestionsService;
import com.scube.techboot.utils.CheckingStatus;
import com.scube.techboot.utils.Dropdownutils;
import com.scube.techboot.utils.TechbootResourceBundle;

@Controller
public class QuestionsController {
	@Autowired
	private QuestionsService questionsService;

	//URL
	private static final String CREATE_QUESTIONS  ="/add_questions";
	private static final String LIST_QUESTIONS  ="/view-addQuestions";
	private static final String SEARCH_QUESTIONS  ="/search-question";
	private static final String EDIT_QUESTIONS  ="/edit-Questions";
	private static final String DELETE_QUESTIONS  ="/delete-Questions";
	private static final String VIEW_QUESTION_DETAILS ="/view-Questions-details";
	private static final String QUESTION_VALIDATIONS="/questionValidations";


	@ModelAttribute("subCategoryList")
	private List<SubcategoryBO> listOfCourseSubCategory(){
		List<SubcategoryBO> subCategoryList= new ArrayList<SubcategoryBO>();
		subCategoryList=questionsService.getCourseSubCatogery();
		return subCategoryList;
	}
	@ModelAttribute("CategoryList")
	private List<CategoryBO> listOfCategory(){
		List<CategoryBO> categoryList= new ArrayList<CategoryBO>();
		CategoryBO categoryBO=new CategoryBO();
		categoryList=questionsService.getCatogery();
		return categoryList;
	}
	  private void getQuestionType(Model model) {
			// TODO Auto-generated method stub
			List<String> questionType = Dropdownutils.getQuestionType();
			model.addAttribute("questionTypeList",questionType);
		}
	@RequestMapping(value=CREATE_QUESTIONS,method=RequestMethod.GET)
	public String getQuestion(Model model,HttpServletRequest request,HttpSession session) {
		List<SubcategoryBO> subCategoryList= new ArrayList<SubcategoryBO>();
		subCategoryList=questionsService.getCourseSubCatogery();
		QuestionsBO QuestionsBO=new QuestionsBO();
		getQuestionType(model);
		model.addAttribute("QuestionsBO",QuestionsBO);
		model.addAttribute("subCategoryList",subCategoryList);
		return "add_questions";
	}
	@RequestMapping(value = CREATE_QUESTIONS, method = RequestMethod.POST)
	public String postQuestion(@Valid @ModelAttribute("QuestionsBO") QuestionsBO questionsBO,BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response,Model model,HttpSession session) throws SerialException, SQLException, IOException{
		if(bindingResult.hasErrors()){
			return "add_questions";
		}
		SubcategoryBO subCategoryBO=new SubcategoryBO();
		if(null!=questionsBO){
			String subcategory=questionsBO.getSubCategoryBO().getSubcategory();
			int id=Integer.parseInt(subcategory);
			subCategoryBO.setSubcategoryId(id);
			questionsBO.setSubCategoryBO(subCategoryBO);
		}
		if(null!=questionsBO){ 
			questionsBO=questionsService.saveQuestions(questionsBO);
		}
		if(null!=questionsBO){
		model.addAttribute("successMessage",TechbootResourceBundle.getValue("create.questions"));
		}
		return "redirect:/view-addQuestions";

	}


	@RequestMapping(value=LIST_QUESTIONS ,method = RequestMethod.GET)
	public String viewQuestion(Model model, HttpServletRequest request,HttpSession session) throws FileNotFoundException{
		QuestionsBO questionsBO=new QuestionsBO();
		String paging=null;
		if(null!=request.getParameter("successMessage")){
			model.addAttribute("successMessage",request.getParameter("successMessage"));
		}
		if(null!=request.getParameter("errorMessage")){
			model.addAttribute("errorMessage",request.getParameter("errorMessage"));
		}
		questionsBO.setIsDelete(false);
		questionsBO.setSending_status(true);
		if(null!=request.getParameter("page")){
			paging=request.getParameter("page");
		}
		//pagination
		questionsPagination(questionsBO,paging,model,session);
		model.addAttribute("QuestionsObject",new QuestionsBO());
		return "view-addQuestions";

	}
	private void questionsPagination(QuestionsBO questionsBO, String paging, Model model, HttpSession session) throws FileNotFoundException {
		// TODO Auto-generated method stub
		long count=0;
		long totalQuestionscount=0;
		int page=1;
		int maxRecord=10;
		long totalQuestionsRecordcount=0;
		if(null!=paging){
			page=Integer.parseInt(paging);
		}
		List<QuestionsBO> pageQuestionslist=new ArrayList<QuestionsBO>();
		questionsBO.setIsDelete(false);
		questionsBO.setSending_status(true);
		if(null!=questionsBO){
			count=questionsService.getListOfQuestions(questionsBO);
		}
		if(0!=count){
			totalQuestionsRecordcount=count;
		}
		int StartingRecordIndex=paginationPageValues(page, maxRecord);
		questionsBO.setRecordIndex(StartingRecordIndex);
		questionsBO.setMaxRecord(maxRecord);
		questionsBO.setPagination("pagination");

		if(null!=questionsBO){
			pageQuestionslist=questionsService.ListOfQuestions(questionsBO);
		}
		if(null!=pageQuestionslist && !pageQuestionslist.isEmpty() && pageQuestionslist.size()>0){
			model.addAttribute("QuestionsList",pageQuestionslist);
			List<QuestionsBO> QuestionsLists=new ArrayList<QuestionsBO>();
			totalQuestionscount=totalQuestionsRecordcount;
			QuestionsLists.addAll(pageQuestionslist);

			model.addAttribute("QuestionsLists",Pagination.paginationLimitedRecords(page, QuestionsLists, maxRecord, totalQuestionscount));
		}
		else{
			model.addAttribute("errorMessage", TechbootResourceBundle.getValue("error.question"));
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

	@RequestMapping(value=SEARCH_QUESTIONS,method=RequestMethod.POST)
	public String searchQuestion(@ModelAttribute("QuestionsObject") QuestionsBO questionsBO,Model model,HttpServletRequest request,
			HttpSession session) throws FileNotFoundException{
		SubcategoryBO SubcategoryBO=new SubcategoryBO();
		CategoryBO categoryBO=new 	CategoryBO();
		long count=0;
		long totalQuestioncount=0;
		int page=1;
		int maxRecord=0;
		String Record=TechbootResourceBundle.getValue("pagination.count");
		if(null!=Record){
			maxRecord=Integer.parseInt(Record);
		} 
		List<QuestionsBO> Questionslist=new ArrayList<QuestionsBO>();
		//Second search value
		if(null!=questionsBO.getQuestion()){
			model.addAttribute("searchvalue",questionsBO.getQuestion());
		}
		if(null!=questionsBO.getQuestionType()){
			model.addAttribute("searchvalue",questionsBO.getQuestionType());
		}
		if(null!=questionsBO){
			count=questionsService.listOfQuestionCount(questionsBO);
		}
		if(0!=count){
			totalQuestioncount=count;
		}
		//Search pagination
		int StartingRecordIndex=paginationPageValues(page, maxRecord);
		questionsBO.setRecordIndex(StartingRecordIndex);
		questionsBO.setMaxRecord(maxRecord);
		questionsBO.setPagination("pagination");
		//limited Record
		Questionslist=questionsService.searchQuestion(questionsBO);
		if(null!=Questionslist && !Questionslist.isEmpty() && Questionslist.size()>0){
			model.addAttribute("QuestionsLists",Pagination.paginationLimitedRecords(page, Questionslist, maxRecord, totalQuestioncount));
			return "view-addQuestions";	
		}
		return "view-addQuestions";	
	}
	@RequestMapping(value= EDIT_QUESTIONS,method = RequestMethod.GET)
	public String getEditQuestion(Model model,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException{
		QuestionsBO questionsBO=new QuestionsBO();
		SubcategoryBO SubcategoryBO=new SubcategoryBO();
		CategoryBO categoryBO=new 	CategoryBO();
		if(null!=request.getParameter("questionId")){
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			questionsBO.setQuestionId(questionId);
		}
		if(null!=request.getParameter("categoryId")){
			int categoryId=Integer.parseInt(request.getParameter("categoryId"));
			categoryBO.setCategoryId(categoryId);
			SubcategoryBO.setCategoryBO(categoryBO);
			questionsBO.setSubCategoryBO(SubcategoryBO);
		}
		if(null!=request.getParameter("subcategoryId")){
			int subcategoryId=Integer.parseInt(request.getParameter("subcategoryId"));
			SubcategoryBO.setSubcategoryId(subcategoryId);
			questionsBO.setSubCategoryBO(SubcategoryBO);
		}
		questionsBO = questionsService.editQuestion(questionsBO);
		if(0!=questionsBO.getQuestionId()){
			model.addAttribute("editQuestionBo", questionsBO);
			return "edit-Questions";
		}
		model.addAttribute("errorMessage",TechbootResourceBundle.getValue("error.question"));
		return "redirect:/add_questions";
	}
	@RequestMapping(value=EDIT_QUESTIONS, method=RequestMethod.POST)
	public String editQuestion(@Valid @ModelAttribute("editQuestionBo")QuestionsBO questionsBO,Model model,HttpServletRequest request,HttpServletResponse response,BindingResult bindingResult,
			HttpSession session) throws FileNotFoundException{
		SubcategoryBO SubcategoryBO=new SubcategoryBO();
		CategoryBO categoryBO=new CategoryBO();
		if(null!=request.getParameter("questionId")){
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			questionsBO.setQuestionId(questionId);
		}
		if(null!=request.getParameter("categoryId")){
			int categoryId=Integer.parseInt(request.getParameter("categoryId"));
			categoryBO.setCategoryId(categoryId);
			SubcategoryBO.setCategoryBO(categoryBO);
			questionsBO.setSubCategoryBO(SubcategoryBO);
		}
		if(null!=request.getParameter("subcategoryId")){
			int subcategoryId=Integer.parseInt(request.getParameter("subcategoryId"));
			SubcategoryBO.setSubcategoryId(subcategoryId);
			questionsBO.setSubCategoryBO(SubcategoryBO);
		}
		if(null!=questionsBO){
			boolean status=questionsService.updateQuestion(questionsBO);
			if(status){
				model.addAttribute("successMessage", TechbootResourceBundle.getValue("update.Question"));
				return "redirect:/view-addQuestions";
			}
		}
		return "redirect:/view-addQuestions";
	}
	@RequestMapping(value=DELETE_QUESTIONS,method=RequestMethod.GET)
	public String deleteCompany(Model model,HttpServletRequest request,HttpSession session)throws FileNotFoundException{ 
		QuestionsBO questionsBO=new QuestionsBO();
		if(null!=request.getParameter("questionId")){
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			questionsBO.setQuestionId(questionId);
		}

		if(null!=questionsBO){
			boolean status=questionsService.deleteQuestion(questionsBO);
			if(status){
				model.addAttribute("successMessage", TechbootResourceBundle.getValue("delete.Question"));
				return "redirect:/view-addQuestions";
			}
		}
		return "redirect:/view-addQuestions";
	}
	 @RequestMapping(value =VIEW_QUESTION_DETAILS,method=RequestMethod.GET)
	 public String viewQuestionDeatail(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws FileNotFoundException{
		    QuestionsBO questionsBO=new QuestionsBO();
		 	SubcategoryBO SubcategoryBO=new SubcategoryBO();
			CategoryBO categoryBO=new CategoryBO();
			if(null!=request.getParameter("questionId")){
				int questionId=Integer.parseInt(request.getParameter("questionId"));
				questionsBO.setQuestionId(questionId);
			}
			if(null!=request.getParameter("categoryId")){
				int categoryId=Integer.parseInt(request.getParameter("categoryId"));
				categoryBO.setCategoryId(categoryId);
				SubcategoryBO.setCategoryBO(categoryBO);
				questionsBO.setSubCategoryBO(SubcategoryBO);
			}
			if(null!=request.getParameter("subcategoryId")){
				int subcategoryId=Integer.parseInt(request.getParameter("subcategoryId"));
				SubcategoryBO.setSubcategoryId(subcategoryId);
				questionsBO.setSubCategoryBO(SubcategoryBO);
			}
			if(0!=questionsBO.getQuestionId()){
				questionsBO=questionsService.viewQuestionDetails(questionsBO);
			}
				if(null!=questionsBO){
					model.addAttribute("Questionlist",questionsBO);
					return "view-Questions-details";
				}
			return "redirect:/view-addQuestions";
		}
	   @ResponseBody
	    @RequestMapping(value=QUESTION_VALIDATIONS,method=RequestMethod.GET)
	    public String questionValidations(@RequestParam String question,HttpServletRequest request){
	    	if(null!=question && !question.isEmpty()){
	    		boolean status=questionsService.questionValidations(question);
	    		if(status){
	    			return "QUESTIONS";
	    		}
	    	}
			return null;
	    }
}