package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scube.mycognitiv.bo.CategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;

/**
 * Servlet implementation class CreateCategoryController
 */
@WebServlet(urlPatterns ={"/create_category" , "/view_category"})
public class CreateCategoryController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	AdminService service = new AdminServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateCategoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		HttpSession session = request.getSession(false);
		
		
		String applicationContextPath = request.getContextPath();
		if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/create_category")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/create_category.jsp");
			dispatcher.forward(request, response);
		}
		
		else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_category")) {
			CategoryBO categoryBO = new CategoryBO();
			List<CategoryBO> categoryList;

			int pageNo = 0;
			int categoryCount = 0;
			categoryCount = service.getCategoryCount();
			if (null == session.getAttribute("categoryCount")) {
				categoryCount = service.getCategoryCount();
				session.setAttribute("categoryCount", categoryCount);
			} else if (0 < categoryCount) {
				session.setAttribute("categoryCount", categoryCount);
			} else if (null != session.getAttribute("categoryCount")) {
				session.removeAttribute("categoryCount");
			}
			if (null != request.getParameter("d-49216-p")) {
				String pageNum = request.getParameter("d-49216-p");
				pageNo = Integer.parseInt(pageNum);
			} else {
				pageNo = 1;
			}
			int maxRecord = 10;
			int startingRecord = paginationPageValues(pageNo, maxRecord);
			categoryBO.setRecordIndex(startingRecord);
			categoryBO.setRecordsPerPage(maxRecord);
			try {
				categoryList = service.retrievecategory(categoryBO);
				if (null != categoryList && categoryList.size() > 0) {
					request.getSession(false).setAttribute("categoryList", categoryList);
					request.setAttribute("maxRecordRef", maxRecord);
				} else {
					request.setAttribute("errorMessage", "No category record found!");
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_category.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		checkAuthentication(request,response);
		CategoryBO bo=new CategoryBO();
		String category=request.getParameter("category");
		//System.out.println("sasa::"+category);
		bo.setCategory(category);
		bo.setIsDelete(false);
		try {
			bo=service.createCategory(bo);
			if (null!=bo.getErrorMessage()) {

				request.setAttribute("errorMessage",
						bo.getErrorMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("view_category");
	//			rd.forward(request, response);
	//			doGet(request, response);
				response.sendRedirect("view_category");
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
	private static int paginationPageValues(int pageid, int recordPerPage) {
		int pageRecords = 0;
		if (pageid == 1) {
			pageRecords = 0;
		} else {
			pageRecords = (pageid - 1) * recordPerPage + 1;
			pageRecords = pageRecords - 1;
		}
		return pageRecords;
	}

}
