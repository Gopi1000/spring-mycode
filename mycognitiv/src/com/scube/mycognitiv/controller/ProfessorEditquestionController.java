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

import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class ProfessorEditquestionController
 */
@WebServlet("/ProfessorEditquestion")
public class ProfessorEditquestionController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	AdminService service=new AdminServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfessorEditquestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		String category=request.getParameter("category");
		int categoryId=Integer.parseInt(category);
		String subcategory=request.getParameter("subcategory");
		int subcategoryId=Integer.parseInt(subcategory);
		QuizQuestion quizObj=new QuizQuestion();
		int quesId = 0;
		ArrayList<QuizQuestion> questionList = null;
		if(null != request.getParameter("quesId")){
			String id=request.getParameter("quesId");
			 quesId=Integer.parseInt(id);
			}
		try {
			if(null==questionList){
				
			questionList = service.retrievequestions(categoryId,subcategoryId,quizObj);
			}
			if(null!=questionList&&questionList.size()>0){
				request.setAttribute("questionList", questionList);
				
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/jsps/professor_editquestions.jsp");
		dispatcher.forward(request, response);
		
	}

}
