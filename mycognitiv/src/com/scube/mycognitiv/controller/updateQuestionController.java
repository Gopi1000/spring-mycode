package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scube.mycognitiv.bo.CategoryBO;
import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.service.CandidateService;
import com.scube.mycognitiv.service.CandidateServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;


/**
 * Servlet implementation class updateQuestionController
 */
@WebServlet("/updateQuestion")
public class updateQuestionController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	CandidateService service = new CandidateServiceImpl();
	AdminService adminService = new AdminServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateQuestionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		try{
			String category=request.getParameter("category");
			CategoryBO catObj=adminService.getCategoryByName(category);
			int categoryId=catObj.getCategoryId();
			String subCategory=request.getParameter("subcategory");
			SubcategoryBO subcategoryBO=adminService.getSubCategoryByName(subCategory);
			int subaCategoryId=subcategoryBO.getSubcategoryId();
		QuizQuestion updatebo = new QuizQuestion();
		String id = request.getParameter("quesId");
		int quesId = Integer.parseInt(id);
		String quesweightage = request.getParameter("complexity");
		float complexity = Float.parseFloat(quesweightage);
		String ques = request.getParameter("question");
		String[] st=new String[4];
		st[0] =request.getParameter("answer1");
		st[1] =request.getParameter("answer2");
		st[2] =request.getParameter("answer3");
		st[3] =request.getParameter("answer4");
		String ans = request.getParameter("correctAnswer");
		String questype = request.getParameter("questionType");
		updatebo.setQuesId(quesId);
		updatebo.setComplexity(complexity);
		updatebo.setQuestion(ques);
		updatebo.setQuestionOptions(st);
		updatebo.setCorrectAnswer(ans);
		updatebo.setQuestionType(questype);
		
		updatebo = service.updateQuestion(updatebo);
		request.setAttribute("action", "postEdit");
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("subCategoryId", subaCategoryId);
		if(null!=updatebo){
			request.setAttribute("errorMessage", "Question Added Successfully.");
			RequestDispatcher rd= request.getRequestDispatcher("EditQuestions");
			 rd.forward(request,response);
		}
		else{
			request.setAttribute("errorMessage", "Question Added Failed.");
			RequestDispatcher rd= request.getRequestDispatcher("EditQuestions");
			 rd.forward(request,response);
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
