package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.service.CandidateService;
import com.scube.mycognitiv.service.CandidateServiceImpl;

@WebServlet("/ResetPassword")
public class ResetPasswordController extends ControllerBase{

	private static final long serialVersionUID = 1L;
	
	CandidateService service = new CandidateServiceImpl();
	
	public ResetPasswordController() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		UserBO userBO=new UserBO();
		String emailAddress=request.getParameter("emailAddress");
		
		try {
		userBO = service.isExistEmailAddress(emailAddress);
			if(null!=userBO && null!=userBO.getEmail() && emailAddress.equals(userBO.getEmail())) {
				
				request.getSession(false).setAttribute("emailAddress", userBO.getEmail());
				request.setAttribute("errorMessage",
						userBO.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/change_password.jsp");
				rd.forward(request, response);
			} else {
				userBO.setErrorMessage("Candidate Reset Password failed!please register");
				request.setAttribute("errorMessage",
						userBO.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/reset_password.jsp");
				rd.forward(request, response);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
