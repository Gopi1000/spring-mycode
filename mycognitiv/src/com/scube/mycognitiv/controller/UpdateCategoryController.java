package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher ;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scube.mycognitiv.bo.CategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;


/**
 * Servlet implementation class UpdateCategoryController
 */
@WebServlet("/UpdateCategory")
public class UpdateCategoryController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	AdminService service = new AdminServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		try{
		CategoryBO updatebo=new CategoryBO();
		String id=request.getParameter("categoryId");
		int categoryId=Integer.parseInt(id);
		updatebo.setCategoryId(categoryId);
		String category=request.getParameter("category");
		updatebo.setCategory(category);
		updatebo=service.updatecategory(updatebo);
		if(null!=updatebo){
			request.setAttribute("errorMessage",updatebo.getErrorMessage());
			RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/jsps/edit_category.jsp");
			dispatcher.forward(request, response);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
