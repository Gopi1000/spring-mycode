package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.CompanyRegistrationBO;
import com.scube.mycognitiv.service.CompanyService;
import com.scube.mycognitiv.service.CompanyServiceImpl;

@WebServlet("/companyRegister")
public class CompanyRegistrationController extends ControllerBase{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger
			.getLogger(CompanyRegistrationController.class);
	
	CompanyService company=new CompanyServiceImpl();
	
	public CompanyRegistrationController(){
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		checkAuthentication(request,response);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("company_register.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		HttpSession session = request.getSession(false);
		try {
			String cname = request.getParameter("companyName");
			String cemail = request.getParameter("companyEmail");
			String cNo = request.getParameter("companyContactNo");
			Long cNumber = Long.parseLong(cNo);
			String clocation = request.getParameter("companyLocation");
			String cwebsite = request.getParameter("companyWebsite");
			String cpass = request.getParameter("password");
			if(!cname.isEmpty()&& !cemail.isEmpty()&& cNumber!=0 && !clocation.isEmpty()&&
					!cwebsite.isEmpty() && !cpass.isEmpty()) {
				CompanyRegistrationBO companyReg=new CompanyRegistrationBO();
				companyReg.setCompanyName(cname);
				companyReg.setCompanyEmail(cemail);
				companyReg.setCompanyContactNo(cNumber);
				companyReg.setCompanyLocation(clocation);
				companyReg.setCompanyWebsite(cwebsite);
				companyReg.setPassword(cpass);
				companyReg=company.registeration(companyReg);
				if (companyReg.getId() != 0
						&& !companyReg.getResponse().isEmpty()) {
					request.setAttribute("newUser", cname);
					request.setAttribute("userType", companyReg.getUserType());
					request.setAttribute("errorMessage", companyReg.getResponse());
					if(null!=session.getAttribute("userType") && session.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("view_companies");
						dispatcher.forward(request, response);	
					}else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/WEB-INF/jsps/company_login.jsp");
					dispatcher.forward(request, response);
					}
				} else {
					request.setAttribute("errorMessage",
							companyReg.getErrorMessage());
					RequestDispatcher rd = request
							.getRequestDispatcher("/WEB-INF/jsps/company_register.jsp");
					rd.forward(request, response);
				}
				
			}else {
				request.setAttribute("errorMessage",
						"Please Enter Mandatory Filds");
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/company_register.jsp");
				rd.forward(request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
	}
	
	
}
