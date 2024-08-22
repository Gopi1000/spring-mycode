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

@WebServlet("/ChangePassword")
public class ChangePasswordController extends ControllerBase{

	private static final long serialVersionUID = 1L;

	CandidateService service = new CandidateServiceImpl();

	public ChangePasswordController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null!=request.getSession(false).getAttribute("resetEmailAddress")) {
			String emailAddress=(String) request.getSession(false).getAttribute("resetEmailAddress");
			request.setAttribute("emailAddress", emailAddress);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserBO userBO=new UserBO();

		String emailAddress=request.getParameter("emailAddress");
		String password=request.getParameter("password");
		String confirmPassword=request.getParameter("confirmPassword");

		//System.out.println("sasa::"+password);
		try {

			if(null!=password && null!=confirmPassword && !password.equals(confirmPassword)) {
				userBO.setErrorMessage("Please Assign Password and Confirm Password same");
				request.setAttribute("errorMessage",userBO.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/change_password.jsp");
				rd.forward(request, response);
			}
			if(null!=emailAddress && null!= password && null!=confirmPassword && password.equals(confirmPassword)) {
				userBO = service.isExistEmailAddress(emailAddress);
				if(null!=userBO && null!= userBO.getEmail()
						&& emailAddress.equals(userBO.getEmail())) {
					userBO.setPassword(password);
					userBO.setConfirmPassword(confirmPassword);
					userBO=service.changePassword(userBO);

					if (null!=userBO.getErrorMessage()) {
						if(userBO.getUserType().equalsIgnoreCase("Company")) {
							request.setAttribute("errorMessage",
									userBO.getErrorMessage());
							RequestDispatcher rd = request
									.getRequestDispatcher("/WEB-INF/jsps/company_login.jsp");
							rd.forward(request, response);
						}else {
							request.setAttribute("errorMessage",
									userBO.getErrorMessage());
							RequestDispatcher rd = request
									.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
							rd.forward(request, response);
						}
					}
				}}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
