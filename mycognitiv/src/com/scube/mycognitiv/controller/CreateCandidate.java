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

@WebServlet("/createCandidate")
public class CreateCandidate extends ControllerBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger
			.getLogger(CreateCandidate.class);

	//Object from service 
	CandidateService service=new CandidateServiceImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		int regId=0;
		RegistrationBO registrationBO = new RegistrationBO();
		UserBO userBO=new UserBO();
		try {
			String userName = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phoneNo = request.getParameter("phoneNo");
			Long phoneNumber = Long.parseLong(phoneNo);
			if(null!=request.getSession(false).getAttribute("companyId")) {
				regId=(int) request.getSession(false).getAttribute("companyId");
			}else if(null!=request.getSession(false).getAttribute("adminId")) {
				regId=(int) request.getSession(false).getAttribute("adminId");
			}

			if(!userName.isEmpty() && !email.isEmpty() && !password.isEmpty()
					&&phoneNumber>0) {
				if(null!=request.getParameter("userId") && !request.getParameter("userId").isEmpty()) {
					regId=Integer.parseInt(request.getParameter("userId"));
					userBO.setUserId(regId);
					userBO.setIsDelete(false);
					userBO.setEmail(email);
					userBO.setUserName(userName);
					userBO.setPassword(password);
					userBO.setPhoneNo(phoneNumber);
					userBO = service.adminUpdateCandidate(userBO);
					if (null!=userBO && userBO.getUserId()>0 && null!=userBO.getErrorMessage()
							&& !userBO.getErrorMessage().isEmpty()) {
						request.setAttribute("errorMessage",userBO.getErrorMessage());
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("view_candidates");
						dispatcher.forward(request, response);
					} else {
						request.setAttribute("errorMessage",
								userBO.getErrorMessage());
						RequestDispatcher rd = request
								.getRequestDispatcher("/WEB-INF/jsps/create_candidate.jsp");
						rd.forward(request, response);
					}
				}else{
					registrationBO.setEmail(email);
					registrationBO.setUserName(userName);
					registrationBO.setPassword(password);
					registrationBO.setPhoneNumber(phoneNumber);
					registrationBO.setIsDelete(false);
					if(regId>0) {
						registrationBO.setCreatedBy(regId);
					}
					registrationBO = service.candidateRegistration(registrationBO);

					if (registrationBO.getId() != 0
							&& !registrationBO.getResponse().isEmpty()) {
						request.setAttribute("newUser", userName);
						request.setAttribute("errorMessage",
								registrationBO.getResponse());
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("view_candidates");
						dispatcher.forward(request, response);
					} else {
						request.setAttribute("errorMessage",
								registrationBO.getErrorMessage());
						RequestDispatcher rd = request
								.getRequestDispatcher("/WEB-INF/jsps/create_candidate.jsp");
						rd.forward(request, response);
					}
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
