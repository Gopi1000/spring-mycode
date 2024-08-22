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

@WebServlet("/DeleteCategory")
public class DeleteCategoryController extends ControllerBase {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AdminService service = new AdminServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		checkAuthentication(request,response);
		CategoryBO bo=new CategoryBO();
		int categoryId=0;
		if(null!=request.getParameter("categoryId")) {
		categoryId=Integer.parseInt(request.getParameter("categoryId"));
		}
		if(categoryId>0) {
			try {
				bo.setCategoryId(categoryId);
				boolean flag=service.deleteCategory(bo);
				if(flag) {
					
					request.setAttribute("infoMesseage", "Delete Category SuccessFully!!");
					RequestDispatcher rd=request.getRequestDispatcher("view_category");
					rd.forward(request, response);	
				}
				else {
					
					request.setAttribute("infoMesseage", "Delete Category Failed!!");
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsps/view_category.jsp");
					rd.forward(request, response);	
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}

}
