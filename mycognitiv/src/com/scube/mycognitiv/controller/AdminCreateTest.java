package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.CreateTestBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;

@WebServlet("/adminCreateTest")
public class AdminCreateTest extends ControllerBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger
			.getLogger(AdminCreateTest.class);

	AdminService service = new AdminServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreateTestBO createTestBO=new CreateTestBO();
		int categoryRef=0;
		List<Integer> subcategoryRef=new ArrayList<Integer>();
		int selfRateref=0;
		int totalqusRef=0;
		String testName = null;
		if(null!=request.getParameter("testName")) {
			  testName=request.getParameter("testName");
		}
		if(null!=request.getParameter("category")) {
			String category=request.getParameter("category");
			if(null!=category) {
				categoryRef=Integer.parseInt(category);
			}
		}

		if(null!=request.getParameter("subcategories")) {
			String[] subCategories=request.getParameterValues("subcategories");
			if(null!=subCategories) {
				for(String subcategory:subCategories) {
				int subcate=Integer.parseInt(subcategory);
				subcategoryRef.add(subcate);
				}
			}
		}

		if(null!=request.getParameter("selfRate")) {
			String selfRate=request.getParameter("selfRate");
			if(null!=selfRate) {
				selfRateref=Integer.parseInt(selfRate);
			}
		}
		if(null!=request.getParameter("noOfQuestions")) {
			String totalQuestion=request.getParameter("noOfQuestions");
			if(null!=totalQuestion) {
				 totalqusRef=Integer.parseInt(totalQuestion);
			}
		}

		createTestBO.setCategoryId(categoryRef);
		createTestBO.setSubcategoryId(subcategoryRef);
		createTestBO.setSelfRate(selfRateref);
		createTestBO.setTotalQuestion(totalqusRef);
		createTestBO.setTestName(testName);

		try {
			createTestBO=service.createTestAdmin(createTestBO);
			if (null!=createTestBO.getErrorMessage()) {

				request.setAttribute("errorMessage",
						createTestBO.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("view_admin_test");
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage",
						createTestBO.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
				rd.forward(request, response);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
