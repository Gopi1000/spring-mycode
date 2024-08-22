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

@WebServlet("/deleteTest")
public class DeleteTestController extends ControllerBase {
	AdminService adminService=new AdminServiceImpl();
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
		checkAuthentication(request,response);
		int testId=0;
		CreateTestBO createTestBO=new CreateTestBO();
		if(null!=request.getParameter("testId")) {
			String testIdRef=request.getParameter("testId");
			testId=Integer.parseInt(testIdRef);
			if(0<testId) {
				createTestBO.setTestId(testId);
				boolean statusFlag=adminService.deleteTest(createTestBO);
				if(statusFlag) {
					request.setAttribute("infoMessage","Test Has Been Deleted Sucessfully!!");
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsps/view_admin_test.jsp");
					rd.forward(request, response);
				}
			}
		}

	}
}
