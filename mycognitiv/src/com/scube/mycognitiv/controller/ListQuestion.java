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
import javax.servlet.http.HttpSession;

import com.scube.mycognitiv.bo.CategoryBO;
import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class ListQuestion
 */
@WebServlet("/list_questions")
public class ListQuestion extends ControllerBase {
	private static final long serialVersionUID = 1L;
	AdminService service=new AdminServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		String applicationContextPath = request.getContextPath();
		List<SubcategoryBO> subcategoryList;
		checkAuthentication(request,response);
	    if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/list_questions")) {
			try {
				 
				List<QuizQuestion> questionList;
				
				QuizQuestion quizQuestion = new QuizQuestion();
				questionList = service.retrieveQuestion(quizQuestion);
				if(null!=questionList&&questionList.size()>0){
					request.setAttribute("questionList", questionList);
					/* session.setAttribute("maxRecordRef", maxRecord); */
				}
				
				
				if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/list_questions")) {
					try {
						
						SubcategoryBO subCategory = new SubcategoryBO();
						int totalSubCategoryCount = 0;
						totalSubCategoryCount = service.getSubCategoryCount();
						if (null == session.getAttribute("subCategoryCount")) {
							totalSubCategoryCount = service.getSubCategoryCount();
							session.setAttribute("subCategoryCount", totalSubCategoryCount);
						} else if (0 < totalSubCategoryCount) {
							session.setAttribute("subCategoryCount", totalSubCategoryCount);
						} else if (null != session.getAttribute("subCategoryCount")) {
							session.removeAttribute("subCategoryCount");
						}
						int pageNum = 0;
						if (null != request.getParameter("d-49216-p")) {
							pageNum = Integer.parseInt(request.getParameter("d-49216-p"));
						} else {
							pageNum = 1;
						}
						try {
							int maxRecord = 10;
							int startingRecord = paginationPageValues(pageNum, maxRecord);
							subCategory.setRecordIndex(startingRecord);
							subCategory.setRecordsPerPage(maxRecord);
							subcategoryList = service.retrievesubcategory(subCategory);
							if (null != subcategoryList && subcategoryList.size() > 0) {
								request.getSession(false).setAttribute("subcategoryList", subcategoryList);
								request.setAttribute("maxRecordRef", maxRecord);

							} else {
								request.setAttribute("errorMessage", "No Subcategory records found!");
							}
							
						} catch (SQLException e) {

							e.printStackTrace();
						}
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/list_questions.jsp");
			dispatcher.forward(request, response);
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
}


