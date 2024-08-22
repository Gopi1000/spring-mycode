package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.service.CandidateService;
import com.scube.mycognitiv.service.CandidateServiceImpl;
import com.scube.mycognitiv.service.QuestionAndExamService;
import com.scube.mycognitiv.service.QuestionAndExamServiceImpl;
import com.scube.mycognitiv.utils.Exam;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("/exam")
public class ExamController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ExamController.class);
	int questionCount = 1;
	CandidateService service = new CandidateServiceImpl();
	QuestionAndExamService questionAndExamService = new QuestionAndExamServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAuthentication(request, response);
		doPost(request, response);
	}

	QuizQuestion q = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAuthentication(request, response);

		boolean finish = false;
		HttpSession session = request.getSession(false);
		try {

			ArrayList<String> questiontype = (ArrayList<String>) request.getSession(false).getAttribute("questiontype");
			// System.out.println("checktype:::"+questiontype);
			if (null != questiontype && questiontype.contains("options")) {
				String answer = request.getParameter("answer");
				// System.out.println("ans:::::"+answer);
			}
			if (null != questiontype && questiontype.contains("Multiple Select")) {
				String[] answer = request.getParameterValues("answer");
				// System.out.println("ans:::::"+answer);
			}
			int testId = 0;
			if (null != session.getAttribute("testId")) {
				testId = (int) session.getAttribute("testId");
			}
			// System.out.println(session.getAttribute("currentExam"));
			if (null == session.getAttribute("currentExam")) {
				session = request.getSession(false);
				int totalQuestions = Integer.valueOf(session.getAttribute("totalNumberOfQuizQuestions").toString());

				Exam newExam = new Exam(request);
				session.setAttribute("currentExam", newExam);
				newExam.setTotalNumberOfQuestions(totalQuestions);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
				Date date = new Date();
				String started = dateFormat.format(date);
				session.setAttribute("started", started);
			}

			Exam exam = (Exam) request.getSession(false).getAttribute("currentExam");

			if (exam.currentQuestion == 0) {
				q = exam.questionList.get(exam.currentQuestion);
				session.setAttribute("quest", q);
			}

			String action = request.getParameter("action");

			int minute = -1;
			int second = -1;
			if (request.getParameter("minute") != null) {
				minute = Integer.parseInt(request.getParameter("minute"));
				second = Integer.parseInt(request.getParameter("second"));
				request.getSession(false).setAttribute("min", request.getParameter("minute"));
				request.getSession(false).setAttribute("sec", request.getParameter("second"));
			}

			int selectedRadio = -1;
			exam.selections.put(exam.currentQuestion, selectedRadio);

			if (null != request.getParameter("answer") && questiontype.contains("options")
					&& !request.getParameter("answer").isEmpty() && q.getQuestionType().equals("options")) {
				int radio = Integer.parseInt(request.getParameter("answer"));
				exam.selections.put(exam.currentQuestion, radio);
				exam.questionList.get(exam.currentQuestion).setUserSelected(radio);

			} else if (null != request.getParameterValues("answer") && questiontype.contains("Multiple Select")
					&& q.getQuestionType().equals("Multiple Select")) {
				String[] answers = request.getParameterValues("answer");
				System.out.println(Arrays.toString(answers));
				if (null != answers) {

					ArrayList<Integer> selectId = new ArrayList<>();
					Integer[] numbers = new Integer[answers.length];
					for (int i = 0; i < answers.length; i++) {
						numbers[i] = Integer.parseInt(answers[i]);
						selectId.add(Integer.parseInt(answers[i]));
					}
					exam.selection.put(exam.currentQuestion, numbers);
					exam.questionList.get(exam.currentQuestion).setUsersSelected(selectId);
				}
			}

			else if (questiontype.contains("text")) {
				QuizQuestion answerbo = new QuizQuestion();
				String textanswer = request.getParameter("answer");
				exam.questionList.get(exam.currentQuestion).setDescription(textanswer);

			}

			if ("Next".equals(action)) {

				questionCount = questionCount + 1;
				exam.currentQuestion++;
				q = exam.questionList.get(exam.currentQuestion);
				session.setAttribute("quest", q);
			} else if ("Previous".equals(action)) {
				// System.out.println("You clicked Previous Button");
				exam.currentQuestion--;
				q = exam.questionList.get(exam.currentQuestion);
				session.setAttribute("quest", q);

			} else if ("Finish Exam".equals(action) || (minute == 0 && second == 0)) {
				finish = true;

				String min = (String) request.getSession(false).getAttribute("min");
				String sec = (String) request.getSession(false).getAttribute("sec");
				// System.out.println("......."+min);
				String timeSpent = min + ":" + sec;

				int categoryId = (int) request.getSession(false).getAttribute("categoryId");
				ArrayList<Integer> subcategoryList = (ArrayList<Integer>) request.getSession(false)
						.getAttribute("subcategoryId");
				ArrayList<Integer> subcategoryId = new ArrayList<Integer>();
				if (null != subcategoryList && subcategoryList.size() > 0) {
					for (int i = 0; i < subcategoryList.size(); i++) {
						subcategoryId.add(subcategoryList.get(i));
					}
				}

				if (questiontype.equals("text")) {

					ArrayList<QuizQuestion> questionList = exam.questionList;
					int userid = (int) request.getSession(false).getAttribute("userId");
					QuizQuestion answerbo = questionAndExamService.insertanswer(questionList, userid, testId);
					if (null != answerbo.getErrorMessage()) {
						request.setAttribute("errorMessage", answerbo.getErrorMessage());
						request.getRequestDispatcher("/WEB-INF/jsps/ansregsucess.jsp").forward(request, response);
					}
				} else if (questiontype.contains("options")) {
					int totalQuestions = (Integer) session.getAttribute("totalNumberOfQuizQuestions");

					ArrayList<QuizQuestion> questionList = exam.questionList;
					int userid = (int) request.getSession(false).getAttribute("userId");
					QuizQuestion answerbo = questionAndExamService.insertanswer(questionList, userid, testId);

					int result = service.calculateResult(exam, exam.questionList.size(), timeSpent, totalQuestions,
							subcategoryId, categoryId, testId, userid);
					if (result != 0) {
						request.setAttribute("result", result);
					} else {
						request.setAttribute("result", 0);
						request.setAttribute("errorMessage", "data base error please contact admin");
					}

					request.getRequestDispatcher("/WEB-INF/jsps/result.jsp").forward(request, response);
				} else if (questiontype.contains("Multiple Select")) {
					int totalQuestions = (Integer) session.getAttribute("totalNumberOfQuizQuestions");

					ArrayList<QuizQuestion> questionList = exam.questionList;
					int userid = (int) request.getSession(false).getAttribute("userId");
					QuizQuestion answerbo = questionAndExamService.insertanswer(questionList, userid, testId);

					int result = service.calculateResult(exam, exam.questionList.size(), timeSpent, totalQuestions,
							subcategoryId, categoryId, testId, userid);
					if (result != 0) {
						request.setAttribute("result", result);
					} else {
						request.setAttribute("result", 0);
						request.setAttribute("errorMessage", "data base error please contact admin");
					}

					request.getRequestDispatcher("/WEB-INF/jsps/result.jsp").forward(request, response);
				}
			}
			if (finish != true) {
				request.getRequestDispatcher("/WEB-INF/jsps/exam.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}

	}

}
