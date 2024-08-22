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

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.CategoryBO;
import com.scube.mycognitiv.bo.CompanyRegistrationBO;
import com.scube.mycognitiv.bo.CreateTestBO;
import com.scube.mycognitiv.bo.ResultBO;
import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.bo.UploadQuestionBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.service.CandidateService;
import com.scube.mycognitiv.service.CandidateServiceImpl;
import com.scube.mycognitiv.service.CompanyService;
import com.scube.mycognitiv.service.CompanyServiceImpl;
import com.scube.mycognitiv.service.QuestionAndExamService;
import com.scube.mycognitiv.service.QuestionAndExamServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

@WebServlet(urlPatterns = { "/candidate_register", "/professor_register", "/company_register", "/takeExam", "/logout",
		"/edit_questions", "/professor_editquestions", "/professor_editQuestion", "/editQuestion", "/deleteCategory",
		"/edit_priority", "/admin_home", "/company_home", "/view_questions", "/create_subcategory", "/create_question",
		"/candidate_details", "/admin_profile", "/company_profile", "/test_review", "/view_candidates",
		"/view_subcategory", "/view_testanswer", "/view_teststatus", "/viewTeststatus", "/view_results",
		"/upload_questions", "/create_test", "/view_test_results", "/view_takentest", "/viewTakentest",
		"/view_markedquestion", "/view_candidate_test_results", "/create_candidate", "/admin_create_test",
		"/view_admin_test", "/assign_test", "/change_password", "/reset_password", "/admin_candidate_view_test",
		"/delete_subcategory", "/edit_subcategory", "/delete_candidate", "/admin_edit_candidate", "/view_companies",
		"/delete_company", "/coding_test" })

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public QuizQuestion quizQuestion = null;
	private static final Logger LOGGER = Logger.getLogger(MainController.class);
	CandidateService service = new CandidateServiceImpl();
	CompanyService company = new CompanyServiceImpl();
	QuestionAndExamService questionAndExamService = new QuestionAndExamServiceImpl();
	// create object from service class
	AdminService adminService = new AdminServiceImpl();
	List<UploadQuestionBO> getTestName;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<SubcategoryBO> subcategoryList;
		HttpSession session = request.getSession(false);
		String applicationContextPath = request.getContextPath();
		if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/coding_test")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/coding_test.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/index.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/change_password")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/change_password.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/reset_password")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/reset_password.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/admin_home")) {

			request.setAttribute("userList", session.getAttribute("userList"));
			request.setAttribute("testName", session.getAttribute("testName"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/company_home")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/company_home.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/admin_profile")) {

			int adminId = (int) session.getAttribute("userId");
			try {
				UserBO bo = getCandidateDetails(adminId);
				request.setAttribute("profile", bo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/admin_profile.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/company_profile")) {

			String companyEmail = (String) session.getAttribute("companymail");
			try {
				CompanyRegistrationBO bo = getCompanyDetails(companyEmail);
				request.setAttribute("profile", bo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/company_profile.jsp");
			dispatcher.forward(request, response);
		}

		else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_questions")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_questions.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/create_subcategory")) {
			List<CategoryBO> categoryList;
			CategoryBO categoryBO = new CategoryBO();
			try {
				categoryList = adminService.retrievecategory(categoryBO);
				request.getSession(false).setAttribute("categoryList", categoryList);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/create_subcategory.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_subcategory")) {

			// List<SubcategoryBO> subcategoryList;
			SubcategoryBO subCategory = new SubcategoryBO();
			int totalSubCategoryCount = 0;
			totalSubCategoryCount = adminService.getSubCategoryCount();
			if (null == session.getAttribute("subCategoryCount")) {
				totalSubCategoryCount = adminService.getSubCategoryCount();
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
				subcategoryList = adminService.retrievesubcategory(subCategory);
				if (null != subcategoryList && subcategoryList.size() > 0) {
					request.getSession(false).setAttribute("subcategoryList", subcategoryList);
					request.setAttribute("maxRecordRef", maxRecord);

				} else {
					request.setAttribute("errorMessage", "No Subcategory records found!");
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_subcategory.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_testanswer")) {
			ArrayList<UserBO> userList;
			UserBO bo = new UserBO();
			try {
				if (null != session.getAttribute("userType")
						&& session.getAttribute("userType").toString().equalsIgnoreCase("company")) {
					bo.setCreatedBy(Integer.valueOf(session.getAttribute("userId").toString()));
				}
				userList = adminService.retrieveAllCandidate(bo);
				if (null != userList) {
					request.getSession(false).setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/user_select.jsp");
					dispatcher.forward(request, response);
				} else {
					request.getSession(false).setAttribute("infoMesseage", "NO Candidate Take Test!!!.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/user_select.jsp");
					dispatcher.forward(request, response);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
			/* answerList = adminService.retrieveanswer(); */
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_teststatus")) {
			try {
				int userid = (int) request.getSession(false).getAttribute("userId");
				ArrayList<ResultBO> testtaken = questionAndExamService.retrieveteststatus(userid);

				if (null != testtaken && testtaken.size() > 0) {
					request.getSession(false).setAttribute("testtaken", testtaken);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/taken_test.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "You have not taken any test yet!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/taken_test.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/viewTeststatus")) {
			int userId = 0;
			int testId = 0;
			if (null != request.getParameter("userId") && null != request.getParameter("testId")) {
				String uid = request.getParameter("userId");
				userId = Integer.parseInt(uid);
				String tid = request.getParameter("testId");
				testId = Integer.parseInt(tid);
			}
			try {
				ArrayList<QuizQuestion> testList = questionAndExamService.retrievetexttakentestbyId(userId, testId);
				if (null != testList && testList.size() > 0) {
					request.getSession(false).setAttribute("reviewQuestions", testList);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/WEB-INF/jsps/view_text_takentestbyId.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/edit_priority")) {
			// List<SubcategoryBO> subcategoryList;
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			try {
				subcategoryList = adminService.getsubcategory(categoryId);
				if (null != subcategoryList && subcategoryList.size() > 0) {
					request.setAttribute("subcategoryList", subcategoryList);
				} else {
					request.setAttribute("errorMessage", "No data found");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/edit_priority.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/create_question")) {
			try {
				List<CategoryBO> categoryList;
				CategoryBO categoryBO = new CategoryBO();
				categoryList = adminService.retrievecategory(categoryBO);
				request.getSession(false).setAttribute("categoryList", categoryList);
				SubcategoryBO subcategoryBO = null;
				// List<SubcategoryBO> subcategoryList;
				subcategoryList = adminService.retrievesubcategory(subcategoryBO);
				request.getSession(false).setAttribute("subcategoryList", subcategoryList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/create_question.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_candidates")) {
			UserBO bo = new UserBO();

			if (null != session.getAttribute("userType")
					&& session.getAttribute("userType").toString().equalsIgnoreCase("company")) {
				bo.setUserId(Integer.valueOf(session.getAttribute("userId").toString()));
			}
			try {
				bo = retrieveAllCandidate(request, response, bo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (null != bo.getUserList() && bo.getUserList().size() > 0) {
				request.setAttribute("userList", bo.getUserList());
			} else {
				request.setAttribute("errorMessage", "No data found");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_candidates.jsp");
			dispatcher.forward(request, response);

		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_companies")) {
			CompanyRegistrationBO companyBO = null;
			try {
				companyBO = retrieveAllCompanies(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != companyBO && null != companyBO.getCompanyList() && companyBO.getCompanyList().size() > 0
					&& !companyBO.getCompanyList().isEmpty()) {
				request.setAttribute("companyBOList", companyBO.getCompanyList());
			} else {
				request.setAttribute("errorMessage", "No data found");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_companies.jsp");
			dispatcher.forward(request, response);

		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/delete_company")) {

			int companyId = 0;
			CompanyRegistrationBO companyBO = new CompanyRegistrationBO();
			try {
				if (null != request.getParameter("companyId") && !request.getParameter("companyId").isEmpty()) {
					String id = request.getParameter("companyId");
					companyId = Integer.parseInt(id);
					companyBO.setCompanyRegId(companyId);
					companyBO.setIsDelete(true);
				}
				if (null != request.getParameter("email") && !request.getParameter("email").isEmpty()) {
					companyBO.setCompanyEmail(request.getParameter("email"));
				}
				try {
					companyBO = company.adminDeleteCompany(companyBO);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("errorMessage", "Company Deleted Successfully!!");
				response.sendRedirect(applicationContextPath + "/view_companies");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/candidate_details")) {
			// HttpSession session = request.getSession(false);
			int candidateId = (int) session.getAttribute("userId");
			try {
				UserBO bo = getCandidateDetails(candidateId);
				request.setAttribute("profile", bo);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/candidate_details.jsp");
			rd.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/test_review")) {
			ResultBO bo = null;
			String id = request.getParameter("testId");
			int testId = Integer.parseInt(id);
			String userId = request.getParameter("userId");
			int candidateId = Integer.parseInt(userId);
			// HttpSession session = request.getSession(false);
			session.setAttribute("candidateId", candidateId);
			try {
				bo = retrieveCandidateTestDetail(candidateId, testId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (null != bo.getResultBOList() && bo.getResultBOList().size() > 0) {
				request.setAttribute("reviewQuestions", bo.getResultBOList());
			} else {
				request.setAttribute("errorMessage", "No data found");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/test_review.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_results")) {
			ResultBO bo = null;
			try {
				bo = retrieveTestDetails();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (null != bo.getResultBOList() && bo.getResultBOList().size() > 0) {
				request.setAttribute("result", bo.getResultBOList());
			} else {
				request.setAttribute("errorMessage", "No data found");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_results.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_test_results")) {
			String id = request.getParameter("userId");
			int userId = Integer.parseInt(id);
			ResultBO bo = null;
			try {
				bo = retrieveCandidateTestDetails(userId, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (null != bo.getResultBOList() && bo.getResultBOList().size() > 0) {
				request.setAttribute("result", bo.getResultBOList());
			} else {
				request.setAttribute("errorMessage", "No data found");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_test_results.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_takentest")) {
			int userId = (int) request.getSession(false).getAttribute("userId");
			ResultBO bo = null;
			try {
				ArrayList<ResultBO> testtaken = questionAndExamService.retrievetakentest(userId);
				if (null != testtaken) {
					request.getSession(false).setAttribute("testtaken", testtaken);
				} else {
					request.setAttribute("errorMessage", "No data found");
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_takentest.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/viewTakentest")) {
			int userId = 0;
			int testId = 0;
			if (null != request.getParameter("userId") && null != request.getParameter("testId")) {
				String uid = request.getParameter("userId");
				userId = Integer.parseInt(uid);
				String tid = request.getParameter("testId");
				testId = Integer.parseInt(tid);
			}
			try {
				ArrayList<QuizQuestion> testList = questionAndExamService.retrievetakentestbyId(userId, testId);
				if (null != testList && testList.size() > 0) {
					request.getSession(false).setAttribute("reviewQuestions", testList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_takentestbyId.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_markedquestion")) {
			int userid = (int) request.getSession(false).getAttribute("userId");
			ArrayList<QuizQuestion> markedquestionList;
			try {
				markedquestionList = questionAndExamService.retrievemarkedquestions(userid);
				if (null != markedquestionList && markedquestionList.size() > 0) {
					request.getSession(false).setAttribute("markedquestion", markedquestionList);
				} else {
					request.setAttribute("errorMessage", "No data found");
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_markedquestion.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_candidate_test_results")) {
			String testType = request.getParameter("testType");
			// HttpSession session = request.getSession(false);
			int userId = (int) session.getAttribute("userId");
			ResultBO bo = null;
			try {
				bo = retrieveCandidateTestDetails(userId, testType);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (null != bo.getResultBOList() && bo.getResultBOList().size() > 0) {
				request.setAttribute("result", bo.getResultBOList());
			} else {
				request.setAttribute("errorMessage", "No data found");
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsps/view_candidate_test_results.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/upload_questions")) {
			try {
				List<CategoryBO> categoryList;
				CategoryBO categoryBO = new CategoryBO();
				categoryList = adminService.retrievecategory(categoryBO);
				request.getSession(false).setAttribute("categoryList", categoryList);
				SubcategoryBO subcategoryBO = null;
				// List<SubcategoryBO> subcategoryList;
				subcategoryList = adminService.retrievesubcategory(subcategoryBO);
				request.getSession(false).setAttribute("subcategoryList", subcategoryList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/upload_questions.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/edit_questions")) {
			try {

				List<CategoryBO> categoryList;
				CategoryBO categoryBO = new CategoryBO();
				categoryList = adminService.retrievecategory(categoryBO);
				request.getSession(false).setAttribute("categoryList", categoryList);

				SubcategoryBO subcategoryBO = null;
				subcategoryList = adminService.retrievesubcategory(subcategoryBO);
				request.getSession(false).setAttribute("subcategoryList", subcategoryList);

				SubcategoryBO subCategory = new SubcategoryBO();
				int totalSubCategoryCount = 0;
				totalSubCategoryCount = adminService.getSubCategoryCount();
				if (null == session.getAttribute("subCategoryCount")) {
					totalSubCategoryCount = adminService.getSubCategoryCount();
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
					subcategoryList = adminService.retrievesubcategory(subCategory);
					if (null != subcategoryList && subcategoryList.size() > 0) {
						request.getSession(false).setAttribute("subcategoryList", subcategoryList);
						request.setAttribute("maxRecordRef", maxRecord);

					} else {
						request.setAttribute("errorMessage", "No Subcategory records found!");
					}
					/*
					 * RequestDispatcher dispatcher =
					 * request.getRequestDispatcher("/WEB-INF/jsps/edit_questions.jsp");
					 * dispatcher.forward(request, response);
					 */
				} catch (SQLException e) {

					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/edit_questions.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/professor_editquestions")) {
			try {
				CategoryBO categoryBO = new CategoryBO();
				List<CategoryBO> categoryList;
				categoryList = adminService.retrievecategory(categoryBO);
				request.getSession(false).setAttribute("categoryList", categoryList);
				// List<SubcategoryBO> subcategoryList;
				SubcategoryBO subcategoryBO = null;
				subcategoryList = adminService.retrievesubcategory(subcategoryBO);
				request.getSession(false).setAttribute("subcategoryList", subcategoryList);

			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/editquestions.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/editQuestion")) {
			int quesId = 0;
			if (null != request.getParameter("quesId")) {
				String id = request.getParameter("quesId");
				quesId = Integer.parseInt(id);
			}
			try {
				quizQuestion = adminService.retrievequestionbyId(quesId);
				request.setAttribute("quizQuestion", quizQuestion);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_question.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/professor_editQuestion")) {
			int quesId = 0;
			if (null != request.getParameter("quesId")) {
				String id = request.getParameter("quesId");
				quesId = Integer.parseInt(id);
			}
			try {
				quizQuestion = adminService.retrievequestionbyId(quesId);
				request.setAttribute("quizQuestion", quizQuestion);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/professor_viewquestion.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/editCategory")) {
			int categoryId = 0;
			if (null != request.getParameter("categoryId")) {
				String id = request.getParameter("categoryId");
				categoryId = Integer.parseInt(id);
			}
			try {
				CategoryBO categorybo = adminService.retrievecategorybyId(categoryId);
				if (null != categorybo) {
					request.setAttribute("categorybo", categorybo);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/edit_category.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Error to be Occured");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_category.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/deleteCategory")) {
			int categoryId = 0;
			if (null != request.getParameter("categoryId")) {
				String id = request.getParameter("categoryId");
				categoryId = Integer.parseInt(id);
			}
			try {
				CategoryBO categorybo = adminService.deletecategory(categoryId);
				if (null != categorybo.getErrorMessage()) {
					request.setAttribute("categorybo", categorybo);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_category.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/delete_subcategory")) {

			int subcategoryId = 0;
			SubcategoryBO subcategoryBO = new SubcategoryBO();
			try {
				if (null != request.getParameter("subcategoryId")) {
					String id = request.getParameter("subcategoryId");
					subcategoryId = Integer.parseInt(id);
					subcategoryBO.setSubcategoryId(subcategoryId);
				}
				try {
					subcategoryBO = adminService.deleteSubCategory(subcategoryBO);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("errorMessage", "Deleted");
			} finally {
				response.sendRedirect(applicationContextPath + "/view_subcategory");
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/edit_subcategory")) {

			int subcategoryId = 0;
			SubcategoryBO subcategoryBO = new SubcategoryBO();
			try {
				if (null != request.getParameter("subcategoryId")) {
					String id = request.getParameter("subcategoryId");
					subcategoryId = Integer.parseInt(id);
					subcategoryBO.setSubcategoryId(subcategoryId);
				}
				subcategoryBO = adminService.getSubCategoryById(subcategoryBO);
				request.setAttribute("editSubcategory", subcategoryBO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/create_subcategory.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/admin_edit_candidate")) {

			int userId = 0;
			UserBO userBO = new UserBO();
			try {
				if (null != request.getParameter("userId")) {
					String id = request.getParameter("userId");
					userId = Integer.parseInt(id);
					userBO.setUserId(userId);
				}
				userBO = service.getCandidateDetails(userId);
				request.setAttribute("adminEditCandidate", userBO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/create_candidate.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/delete_candidate")) {

			int userId = 0;
			UserBO userBO = new UserBO();
			try {
				if (null != request.getParameter("userId")) {
					String id = request.getParameter("userId");
					userId = Integer.parseInt(id);
					userBO.setUserId(userId);
					userBO.setIsDelete(true);
				}
				try {
					userBO = service.adminDeleteCandidate(userBO);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("errorMessage", "Candidate Deleted Successfully!!");
			} finally {
				response.sendRedirect(applicationContextPath + "/view_candidates");
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/create_test")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/create_test.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/login")) {
			String userType = request.getParameter("user");
			request.setAttribute("userType", userType);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/candidate_register")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/candidate_register.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/professor_register")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/professor_register.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/professor_login")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/professor_login.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/company_register")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/company_register.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/company_login")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/company_login.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/takeExam")) {
			request.getSession(false).setAttribute("currentExam", null);
			request.getSession(false).setAttribute("totalNumberOfQuizQuestions", null);
			request.getSession(false).setAttribute("quizDuration", null);
			request.getSession(false).setAttribute("min", null);
			request.getSession(false).setAttribute("sec", null);
			if (request.getSession(false).getAttribute("user") == null) {
				request.getRequestDispatcher("/login").forward(request, response);
			} else {
				if (request.getSession(false).getAttribute("userType").toString().equalsIgnoreCase("Candidate")
						&& request.getParameter("inviteId").equalsIgnoreCase("0")) {
					// System.out.println("check");

					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/home.jsp");
					dispatcher.forward(request, response);
				} else if (request.getSession(false).getAttribute("userType").toString().equalsIgnoreCase("Admin")
						&& request.getParameter("inviteId").equalsIgnoreCase("0")) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
					dispatcher.forward(request, response);
				}
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/logout")) {
			request.getSession(false).removeAttribute("userId");
			request.getSession(false).removeAttribute("user");
			request.getSession(false).removeAttribute("email");
			request.getSession(false).removeAttribute("userType");
			request.getSession(false).invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("login");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/create_candidate")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/create_candidate.jsp");
			dispatcher.forward(request, response);
		}
		// else if (request.getRequestURI().equalsIgnoreCase(
		// applicationContextPath + "/admin_create_test")) {
		// RequestDispatcher dispatcher = request
		// .getRequestDispatcher("/WEB-INF/jsps/admin_create_test.jsp");
		// dispatcher.forward(request, response);
		// }
		else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/admin_create_test")) {
			List<CategoryBO> categoryList;
			CategoryBO categoryBO = new CategoryBO();
			try {
				if (null != request.getParameter("userId")) {
					String userId = request.getParameter("userId");
					// HttpSession session=request.getSession(false);
					session.setAttribute("testUserId", userId);
				}
				categoryList = adminService.retrievecategory(categoryBO);
				request.getSession(false).setAttribute("categoryList", categoryList);
				// List<SubcategoryBO> subcategoryList;
				SubcategoryBO subcategoryBO = null;
				subcategoryList = adminService.retrievesubcategory(subcategoryBO);
				request.getSession(false).setAttribute("subcategoryList", subcategoryList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/admin_create_test.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/view_admin_test")) {
			List<CreateTestBO> createTestBOList;
			CreateTestBO createTestBO = new CreateTestBO();
			int companyId = 0;
			int candidateId = 0;

			try {
				if (null != request.getParameter("userId")) {
					String id = request.getParameter("userId");
					candidateId = Integer.parseInt(id);
					createTestBO.setUserId(candidateId);
				}

				if (null != request.getSession(false).getAttribute("userType") && request.getSession(false)
						.getAttribute("userType").toString().equalsIgnoreCase("candidate")) {
					candidateId = Integer.valueOf(request.getSession(false).getAttribute("userId").toString());
					createTestBO.setUserId(candidateId);
					createTestBO.setUserType("candidate");
				}

				if (null != request.getSession(false).getAttribute("userType")
						&& request.getSession(false).getAttribute("userType").toString().equalsIgnoreCase("company")) {
					companyId = Integer.valueOf(request.getSession(false).getAttribute("userId").toString());
					createTestBO.setUserId(companyId);
					createTestBO.setUserType("company");

				}

				int totalTestCount = 0;
				totalTestCount = adminService.getTestCount(createTestBO);
				if (null == session.getAttribute("testCount")) {
					totalTestCount = adminService.getTestCount(createTestBO);
					session.setAttribute("testCount", totalTestCount);
				} else if (0 < totalTestCount) {
					session.setAttribute("testCount", totalTestCount);
				} else if (null != session.getAttribute("testCount")) {
					session.removeAttribute("testCount");
				}
				int pageNum = 0;
				if (null != request.getParameter("d-49216-p")) {
					pageNum = Integer.parseInt(request.getParameter("d-49216-p"));
				} else {
					pageNum = 1;
				}
				int maxRecord = 10;
				int startingRecord = paginationPageValues(pageNum, maxRecord);
				createTestBO.setRecordIndex(startingRecord);
				createTestBO.setRecordsPerPage(maxRecord);
				createTestBOList = adminService.retrieveTest(createTestBO);
				if (null != createTestBOList && createTestBOList.size() > 0) {
					request.setAttribute("adminAssignedTestList", createTestBOList);
					request.setAttribute("maxRecordRef", maxRecord);
				} else {
					request.setAttribute("infoMessage", "You have not assigned any skill test.");
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/view_admin_test.jsp");
				dispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/assign_test")) {
			List<CreateTestBO> createTestBOList;
			CreateTestBO createTestBO = new CreateTestBO();
			boolean mailFlag = false;
			try {
				if (null != request.getParameter("status")) {
					String mailStatus = request.getParameter("status");
					if (mailStatus.equalsIgnoreCase("sendingEmail")) {
						String userName = request.getParameter("userName");
						String emailAddress = request.getParameter("emailAddress");
						String password = request.getParameter("pwd");

						// mailFlag=service.sendingEmaiToCandidate(userName,emailAddress,password);
						if (mailFlag) {
							request.setAttribute("emailConfirmMessage", "Email Have Sent Sucessfully");
						}
					}
				}

				if (null != request.getParameter("userId")) {
					String userId = request.getParameter("userId");
					// HttpSession session=request.getSession(false);
					session.setAttribute("testUserId", userId);
				}
				createTestBOList = adminService.retrieveTest(createTestBO);
				if (null != createTestBOList && createTestBOList.size() > 0) {

					request.getSession(false).setAttribute("categoryList", createTestBOList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/assign_test.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else if (request.getRequestURI().equalsIgnoreCase(applicationContextPath + "/admin_candidate_view_test")) {
			List<CreateTestBO> createTestBOList;
			CreateTestBO createTestBO = new CreateTestBO();
			try {
				if (null != request.getParameter("userId")) {
					String userId = request.getParameter("userId");
					int usrid = Integer.parseInt(userId);
					createTestBO.setUserId(usrid);
				}
				createTestBOList = adminService.retrieveCandidateTest(createTestBO);
				if (null != createTestBOList && createTestBOList.size() > 0) {
					request.getSession(false).setAttribute("categoryList", createTestBOList);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/WEB-INF/jsps/admin_candidate_view_test.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	private CompanyRegistrationBO getCompanyDetails(String companyId) {
		CompanyRegistrationBO comp = new CompanyRegistrationBO();
		try {
			comp = company.getCompanydetails(companyId);
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return comp;
	}

	public UserBO getCandidateDetails(int userId) throws SQLException {
		UserBO bo = new UserBO();
		try {
			bo = service.getCandidateDetails(userId);
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return bo;
	}

	public UserBO retrieveAllCandidate(HttpServletRequest request, HttpServletResponse response, UserBO user)
			throws SQLException {
		UserBO bo = new UserBO();
		int candidateCount = 0;
		int pageNum = 0;
		int id = 0;
		HttpSession session = request.getSession(false);
		if (null != request.getParameter("d-49216-p")) {
			pageNum = Integer.parseInt(request.getParameter("d-49216-p"));
		} else {
			pageNum = 1;
		}
		if (null != user && user.getUserId() > 0) {
			candidateCount = service.getCandidateCount(user);
			session.setAttribute("candidatesCount", candidateCount);
		} else {
			candidateCount = service.getCandidateCount();
			if (null == session.getAttribute("questionCount")) {
				candidateCount = service.getCandidateCount();
				session.setAttribute("candidatesCount", candidateCount);
			} else if (0 < candidateCount) {
				session.setAttribute("candidatesCount", candidateCount);
			} else if (null != session.getAttribute("candidatesCount")) {
				session.removeAttribute("candidatesCount");
			}
		}

		int maxRecord = 10;
		int startingRecord = paginationPageValues(pageNum, maxRecord);
		bo.setRecordIndex(startingRecord);
		bo.setRecordsPerPage(maxRecord);
		try {
			if (null != user && user.getUserId() > 0) {
				bo = service.retrieveAllCandidate(user);
			} else {
				bo = service.retrieveAllCandidate(bo);
			}
			if (null != bo.getUserList() && 0 < bo.getUserList().size() && !bo.getUserList().isEmpty()) {
				request.setAttribute("maxRecordRef", maxRecord);
			}
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return bo;
	}

	private CompanyRegistrationBO retrieveAllCompanies(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CompanyRegistrationBO companyBO = new CompanyRegistrationBO();
		int companyCount = 0;
		int pageNum = 0;
		int id = 0;
		HttpSession session = request.getSession(false);
		if (null != request.getParameter("d-49216-p")) {
			pageNum = Integer.parseInt(request.getParameter("d-49216-p"));
		} else {
			pageNum = 1;
		}
		companyCount = company.getCompanyCount(companyBO);
		if (companyCount > 0) {
			session.setAttribute("companyCount", companyCount);
		}
		int maxRecord = 10;
		int startingRecord = paginationPageValues(pageNum, maxRecord);
		companyBO.setRecordIndex(startingRecord);
		companyBO.setRecordsPerPage(maxRecord);
		try {
			companyBO = company.retrieveAllCompanies(companyBO);
			if (null != companyBO && null != companyBO.getCompanyList() && companyBO.getCompanyList().size() > 0
					&& !companyBO.getCompanyList().isEmpty()) {
				request.setAttribute("maxRecordRef", maxRecord);
			}
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return companyBO;
	}

	public ResultBO retrieveTestDetails() throws SQLException {
		ResultBO bo = new ResultBO();
		try {
			bo = service.retrieveTestDetails();
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return bo;
	}

	/**
	 * This method used to reterive the test details by admin
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ResultBO retrieveCandidateTestDetails(int userId, String testType) throws SQLException {
		ResultBO bo = new ResultBO();
		try {
			bo = service.retrieveCandidateTestDetails(userId, testType);
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return bo;
	}

	/**
	 * This method used to reterive the test details by admin
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ResultBO retrieveCandidateTestDetail(int userId, int testId) throws SQLException {
		ResultBO bos = new ResultBO();
		try {
			bos = service.retrieveCandidateTestDetail(userId, testId);
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
		return bos;
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
