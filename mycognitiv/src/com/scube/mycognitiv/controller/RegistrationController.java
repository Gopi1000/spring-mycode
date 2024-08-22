package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.RegistrationBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.service.CandidateService;
import com.scube.mycognitiv.service.CandidateServiceImpl;


/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/checkRegister")
public class RegistrationController extends ControllerBase {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(RegistrationController.class);
	// create the object from service 
	CandidateService service=new CandidateServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		checkAuthentication(request,response);

		try {

			String userName = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phoneNo = request.getParameter("phoneNo");
			Long phoneNumber = Long.parseLong(phoneNo);
			UserBO user = service.isExistEmailAddress(email);
			if(null==user) {

				if (!userName.isEmpty() && !email.isEmpty() && !password.isEmpty()
						&&0!=phoneNumber) {
					RegistrationBO registrationBO = new RegistrationBO();
					registrationBO.setEmail(email);
					registrationBO.setUserName(userName);
					registrationBO.setPassword(password);
					registrationBO.setPhoneNumber(phoneNumber);
					registrationBO = service.candidateRegistration(registrationBO);

					if (registrationBO.getId() != 0
							&& !registrationBO.getResponse().isEmpty()) {
						request.setAttribute("newUser", userName);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
						dispatcher.forward(request, response);
					} else {
						request.setAttribute("errorMessage",
								registrationBO.getErrorMessage());
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
			}
		} catch (Exception sqe) {
			sqe.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(sqe.getMessage() + sqe);
			}
		}

	}

}
