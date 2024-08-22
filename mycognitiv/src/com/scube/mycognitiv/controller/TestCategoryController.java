package com.scube.mycognitiv.controller;

import java.io.IOException;
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

import com.scube.mycognitiv.bo.CreateTestBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.service.CandidateService;
import com.scube.mycognitiv.service.CandidateServiceImpl;
import com.scube.mycognitiv.service.QuestionAndExamService;
import com.scube.mycognitiv.service.QuestionAndExamServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("/takeTestCategory")
public class TestCategoryController extends ControllerBase {
	ArrayList<QuizQuestion> questionList = null;
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(TestCategoryController.class);
	int questionCount = 1;
	CandidateService service = new CandidateServiceImpl();
	AdminService adminService = new AdminServiceImpl();

	QuestionAndExamService questionAndExamService = new QuestionAndExamServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAuthentication(request, response);
		try {
			if (null != request.getParameter("testId")) {
				int testId = Integer.valueOf(request.getParameter("testId").toString());
				request.setAttribute("testId", testId);
				HttpSession session = request.getSession(false);
				session.setAttribute("testId", testId);
				doPost(request, response);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Take Exam block :: " + ex.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAuthentication(request, response);
		try {

			if (null != request.getParameter("testId")) {
				int testId = Integer.valueOf(request.getAttribute("testId").toString());
				request.setAttribute("testId", testId);
				CreateTestBO testBo = new CreateTestBO();
				testBo.setTestId(testId);
				List<CreateTestBO> createTestBOList = adminService.retrieveTestByTestId(testId);

				if (null != createTestBOList && createTestBOList.size() > 0) {

					for (CreateTestBO test : createTestBOList) {

						int categoryid = test.getCategoryId();
						List<Integer> subcategoryid = test.getSubcategoryId();

						int selfrating = test.getSelfRate();
						int totalquestion = test.getTotalQuestion();
						boolean fulltest = false;
						if (subcategoryid.size() > 0) {
							fulltest = false;
						} else {
							fulltest = true;
						}
						int userid = Integer.valueOf(request.getSession(false).getAttribute("userId").toString());
						request.getSession(false).setAttribute("currentExam", null);
						request.getSession(false).setAttribute("totalNumberOfQuizQuestions", null);
						request.getSession(false).setAttribute("quizDuration", null);
						request.getSession(false).setAttribute("min", null);
						request.getSession(false).setAttribute("sec", null);
						QuizQuestion questiontype = adminService.retrievequestiontype(categoryid);

						request.getSession(false).setAttribute("questiontype", questiontype.getQuestionTypes());
						ArrayList<QuizQuestion> questionsList = questionAndExamService.retrievequestions(categoryid,
								subcategoryid, fulltest, selfrating, totalquestion, userid, questiontype);

						if (null != questionsList && !questionsList.isEmpty() && questionsList.size() > 0) {

							request.getSession(false).setAttribute("totalNumberOfQuizQuestions", +questionsList.size());
							request.getSession(false).setAttribute("quizDuration", "15");
							request.getSession(false).setAttribute("min", "15");
							request.getSession(false).setAttribute("sec", 0);
							request.getSession(false).setAttribute("categoryId", categoryid);
							request.getSession(false).setAttribute("subcategoryId", subcategoryid);
							request.getSession(false).setAttribute("examQuestionObjList", questionsList);

							RequestDispatcher dispatcher = request
									.getRequestDispatcher("/WEB-INF/jsps/quizDetails.jsp");
							dispatcher.forward(request, response);
						} else {
							request.setAttribute("errorMessage", "There is No questions.");
							RequestDispatcher dispatcher = request
									.getRequestDispatcher("/view_admin_test");
							dispatcher.forward(request, response);
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
