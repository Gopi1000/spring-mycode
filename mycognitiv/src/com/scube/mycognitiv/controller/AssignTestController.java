package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.CreateTestBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.service.CandidateService;
import com.scube.mycognitiv.service.CandidateServiceImpl;
import com.scube.mycognitiv.service.EmailService;
import com.scube.mycognitiv.service.EmailServiceImpl;

@WebServlet("/assignTest")
public class AssignTestController extends ControllerBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger
			.getLogger(AssignTestController.class);
	
	AdminService service = new AdminServiceImpl();
	EmailService emailService=new EmailServiceImpl();
	CandidateService candidateService=new CandidateServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId=request.getParameter("userId");
		 HttpSession session=request.getSession(false);
		 session.setAttribute("testUserId", userId);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreateTestBO createTestBO=new CreateTestBO();
		String testid=request.getParameter("category");
		int categoryRef=Integer.parseInt(testid);
		String id=(String) request.getSession(false).getAttribute("testUserId");
		int userId=Integer.parseInt(id);
		createTestBO.setTestId(categoryRef);
		createTestBO.setUserId(userId);
		createTestBO.setIsDelete(false);
		 try {
			 createTestBO=service.assignTestAdmin(createTestBO);
			UserBO candidate= candidateService.getCandidateDetails(userId);
			if(null!=candidate && null!=candidate.getEmail()) {
			String  message="Dear Candidate, You are invited for the skill assesment by www.mycognitiv.com.please complete your assesment.";
			 emailService.sendMail(candidate.getEmail(),message);
			}
			if (null!=createTestBO.getErrorMessage()) {
				
				request.setAttribute("errorMessage",
						createTestBO.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/assign_test.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage",
						createTestBO.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
				rd.forward(request, response);
			
		}
			
		} catch (SQLException | MessagingException e) {
					e.printStackTrace();
		}
		
	}

}
