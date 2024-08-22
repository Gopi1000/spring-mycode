package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class AjaxServelt
 */
@WebServlet("/updateQuestionAjax")
public class AjaxUpdateQuestion extends ControllerBase {
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			QuizQuestion updatebo=new QuizQuestion();
			AdminService adminService = new AdminServiceImpl();
			String qType=request.getParameter("qType").trim();
			String inputValue=request.getParameter("inputValue").trim();
			String qId=request.getParameter("qId").trim();
			int quesId=Integer.parseInt(qId);
			String tempId=request.getParameter("tempId").trim();
			int Id=Integer.parseInt(tempId);
			updatebo.setUpdateType(qType);
			updatebo.setQuesId(quesId);
			if (qType .equals("question")) {


				updatebo.setQuestion(inputValue);


			}

			else if (qType .equals("option")) {


				updatebo.setOption(inputValue);
				updatebo.setOptionId(Id);
			}
			else{
				updatebo.setAnswer(inputValue);
				updatebo.setAnswerId(Id);
			}
			String json = null;
			updatebo=adminService.updateQuestionDetails(updatebo);
			if(null != updatebo.getResponse() && !updatebo.getResponse().isEmpty()){
				json = new Gson().toJson("Success");
				response.setContentType("application/json");
				response.getWriter().write(json);
			}
			else{
				json = new Gson().toJson("Failed");
				response.setContentType("application/json");
				response.getWriter().write(json);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}	
}
