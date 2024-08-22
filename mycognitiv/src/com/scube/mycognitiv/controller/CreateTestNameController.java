package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scube.mycognitiv.bo.CreateTestBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;

/**
 * Servlet implementation class TestNameController
 */
@WebServlet("/createTestName")
public class CreateTestNameController extends ControllerBase {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// create the instance object
		String testName;
		String testLevel;
		// create object from service class
		AdminService service = new AdminServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		checkAuthentication(request,response);
		
		
		String name=request.getParameter("testName");	
		
		CreateTestBO testbo=new CreateTestBO ();
		testbo.setTestName(name);
		if (null!=testbo) {
		
			request.getSession(false).setAttribute("errorMessage",
					testbo.getResponse());
			
			
			
			
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/jsps/create_test.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("errorMessage",
					testbo.getErrorMessage());
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
			rd.forward(request, response);
		
	}

	}
	}
