package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.ProffesorRegisterBO;
import com.scube.mycognitiv.service.CandidateService;
import com.scube.mycognitiv.service.CandidateServiceImpl;

/**
 * Servlet implementation class proffersorRegistrationController
 */
@WebServlet("/professorRegister")
public class professorRegistrationController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger
			.getLogger(professorRegistrationController.class);
	// create the object from service 
	CandidateService service=new CandidateServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public professorRegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
	RequestDispatcher dispatcher = request
				.getRequestDispatcher("professor_register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		try{
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phoneNo = request.getParameter("phoneNo");
		Long phoneNumber = Long.parseLong(phoneNo);

		if (!userName.isEmpty() && !email.isEmpty() && !password.isEmpty()
				&&0!=phoneNumber) {
			ProffesorRegisterBO proffesorregisterBO = new ProffesorRegisterBO();
			proffesorregisterBO.setEmail(email);
			proffesorregisterBO.setUserName(userName);
			proffesorregisterBO.setPassword(password);
			proffesorregisterBO.setPhoneNumber(phoneNumber);
			proffesorregisterBO = service.proffesorRegistration(proffesorregisterBO);

			if (proffesorregisterBO.getId() != 0
					&& !proffesorregisterBO.getResponse().isEmpty()) {
				request.setAttribute("newUser", userName);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/jsps/professor_login.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMessage",
						proffesorregisterBO.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/register.jsp");
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage",
					"Please Enter Mandatory Filds");
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/jsps/register.jsp");
			rd.forward(request, response);
		}
	} catch (Exception sqe) {
		sqe.printStackTrace();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(sqe.getMessage() + sqe);
		}
	}

}
}
