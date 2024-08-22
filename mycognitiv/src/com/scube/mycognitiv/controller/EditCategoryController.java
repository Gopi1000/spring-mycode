package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scube.mycognitiv.bo.CategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;

@WebServlet("/EditCategory")
public class EditCategoryController extends ControllerBase{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AdminService service = new AdminServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		CategoryBO bo=new CategoryBO();
		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		try {
			bo=service.editCategory(categoryId);
			if (null!=bo.getCategory() && bo.getCategoryId()>0) {
				
				request.setAttribute("categorybo",bo);
				request.setAttribute("errorMessage",
						bo.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/edit_category.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage",
						bo.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		
	}

}
