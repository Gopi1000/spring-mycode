package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.scube.mycognitiv.service.QuestionAndExamService;
import com.scube.mycognitiv.service.QuestionAndExamServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class AddMarkedQuestionAjax
 */
@WebServlet("/AddMarkedQuestionAjax")
public class AddMarkedQuestionAjax extends ControllerBase {
	private static final long serialVersionUID = 1L;
	QuestionAndExamService questionAndExamService = new QuestionAndExamServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMarkedQuestionAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
		QuizQuestion addbo=new QuizQuestion();
		
		//System.out.println("ajaxid::"+request.getParameter("qId"));
		//System.out.println(request.getParameter("uId"));
		String qId=request.getParameter("qId");
	       int quesId=Integer.parseInt(qId);
	       String uId=request.getParameter("uId");
	       int userId=Integer.parseInt(uId);
	       addbo.setQuesId(quesId);
	       addbo.setUserId(userId);
	       String json = null;
			addbo=questionAndExamService.addMarkedQuestion(addbo);
			if(null != addbo.getResponse() && !addbo.getResponse().isEmpty()){
				json = new Gson().toJson(addbo.getResponse());
				response.setContentType("application/json");
				response.getWriter().write(json);
			}
			else{
				json = new Gson().toJson("Failed");
				response.setContentType("application/json");
				response.getWriter().write(json);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
