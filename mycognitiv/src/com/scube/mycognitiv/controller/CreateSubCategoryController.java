package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;

/**
 * Servlet implementation class CreateSubCategoryController
 */
@WebServlet("/CreateSubCategory")
public class CreateSubCategoryController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	AdminService service = new AdminServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateSubCategoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAuthentication(request,response);

		}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAuthentication(request,response);
		try {
			int subcategoryId = 0;
			SubcategoryBO subcategoryBO = new SubcategoryBO();
			String category = request.getParameter("category");
			String subcategory = request.getParameter("subcategory");
			if (null != request.getParameter("subcategoryId") && !request.getParameter("subcategoryId").isEmpty()) {
				subcategoryId = Integer.parseInt(request.getParameter("subcategoryId"));
				subcategoryBO.setSubcategoryId(subcategoryId);
			}
			subcategoryBO.setCategory(category);
			subcategoryBO.setSubcategory(subcategory);
			subcategoryBO = service.createSubCategory(subcategoryBO);
			if (null != subcategoryBO) {
				request.setAttribute("errorMessage", subcategoryBO.getErrorMessage());
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("view_subcategory");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMessage", subcategoryBO.getErrorMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
