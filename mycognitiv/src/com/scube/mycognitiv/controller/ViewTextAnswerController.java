package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scube.mycognitiv.bo.ResultBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.service.QuestionAndExamService;
import com.scube.mycognitiv.service.QuestionAndExamServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class ViewTextAnswerController
 */
@WebServlet("/ViewTextAnswer")
public class ViewTextAnswerController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	AdminService adminService = new AdminServiceImpl();
	QuestionAndExamService questionAndExamService = new QuestionAndExamServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTextAnswerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost( request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		try{
		UserBO bo=new UserBO();
		String user=request.getParameter("userName");
		if (null!=user){
		int userid=0;
		
		 userid=Integer.parseInt(user);
			ArrayList<ResultBO> testtaken=questionAndExamService.retrieveteststatus(userid);
			if(null!=testtaken&&testtaken.size()>0){
				request.getSession(false).setAttribute("testtaken", testtaken);
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/taken_test.jsp");
				rd.forward(request, response);
			}else {
				
				request.setAttribute("infoMesseage", "Candidate Not Taken Test");
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/user_select.jsp");
				rd.forward(request, response);
				
			}
		}
		else {
			int userid = (int) request.getSession(false).getAttribute("userId");
			 ArrayList<QuizQuestion> answerList = adminService.retrieveanswer(userid);
				if(null!=answerList&&answerList.size()>0){
					request.getSession(false).setAttribute("answerList",
							answerList);
					RequestDispatcher rd = request
							.getRequestDispatcher("/WEB-INF/jsps/view_user_textanswer.jsp");
					rd.forward(request, response);
				}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
