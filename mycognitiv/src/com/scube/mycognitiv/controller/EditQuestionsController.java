package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scube.mycognitiv.bo.CategoryBO;
import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class EditQuestionsController
 */
@WebServlet("/EditQuestions")
public class EditQuestionsController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	AdminService service=new AdminServiceImpl();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditQuestionsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response);
		checkAuthentication(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		checkAuthentication(request,response);
		int categoryId = 0,subcategoryId=0;
	    if(null!=request.getParameter("categoryId")) {
	    	String cat=request.getParameter("categoryId");
			int catId=Integer.parseInt(cat);
			categoryId=catId;
			String subCat=request.getParameter("subCategoryId");
			int subcatId=Integer.parseInt(subCat);
			subcategoryId=subcatId;
		}else {
			String category=request.getParameter("category");
			CategoryBO catObj = null;
			try {
				catObj = service.getCategoryByName(category);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(null!=catObj) {
			categoryId=catObj.getCategoryId();
			}
			String subcategory=request.getParameter("subcategory");
			SubcategoryBO subcategoryBO = null;
			try {
				subcategoryBO = service.getSubCategoryByName(subcategory);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(null!=subcategoryBO) {
			subcategoryId=subcategoryBO.getSubcategoryId();
			}
		}		
		
		HttpSession session=request.getSession(false);
		
		ArrayList<QuizQuestion> questionList = null;
		
		/*int quesId = 0;
			if(null != request.getParameter("quesId")){
			String id=request.getParameter("quesId");
			quesId=Integer.parseInt(id);
		}*/
		
		int totalQuestionsCount=0;
		totalQuestionsCount=service.getQuestionsCount(categoryId,subcategoryId);
		QuizQuestion quizQuestionObj=new QuizQuestion();
		if(null==session.getAttribute("questionCount")) {
			totalQuestionsCount=service.getQuestionsCount(categoryId,subcategoryId);
			session.setAttribute("questionCount", totalQuestionsCount);
		}else if(0<totalQuestionsCount){
			session.setAttribute("questionCount", totalQuestionsCount);
		}else if(null!=session.getAttribute("questionCount")){
			session.removeAttribute("questionCount");
		}
		int pageNo=0;
		if(null!=request.getParameter("d-49216-p")) {
             String pageNum=request.getParameter("d-49216-p");
             pageNo=Integer.parseInt(pageNum);
		}else {
			pageNo=1;
		}
		
		int maxRecord =10;
		int startingRecord = paginationPageValues(pageNo, maxRecord);
		quizQuestionObj.setRecordIndex(startingRecord);
		quizQuestionObj.setRecordsPerPage(maxRecord);
		try {
			if(null==questionList){
				questionList = service.retrievequestions(categoryId,subcategoryId,quizQuestionObj);
			}
			if(null!=questionList&&questionList.size()>0){
				request.setAttribute("questionList", questionList);
				session.setAttribute("maxRecordRef", maxRecord);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/jsps/view_questions.jsp");
		dispatcher.forward(request, response);

		
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
	}

}
