package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.utils.Exam;
import com.scube.mycognitiv.utils.QuizQuestion;

@WebServlet("/exam/review")
public class ReviewController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ExamController.class);

	public ReviewController() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAuthentication(request, response);
		try {
			Exam exam = (Exam) request.getSession(false).getAttribute("currentExam");
			request.setAttribute("totalQuestion", exam.getTotalNumberOfQuestions());
			ArrayList<QuizQuestion> reviewQuestionList = new ArrayList<QuizQuestion>();
			QuizQuestion reviewbo = null;
			ArrayList<QuizQuestion> questionList = new ArrayList<QuizQuestion>();
			HttpSession session = request.getSession(false);
			questionList = (ArrayList<QuizQuestion>) session.getAttribute("examQuestionObjList");
			int userid = (int) request.getSession(false).getAttribute("userId");
			int i = 0;
			for (QuizQuestion bo : questionList) {
				reviewbo = new QuizQuestion();
				reviewbo.setUserId(userid);
				reviewbo.setQuestion(bo.getQuestion());
				reviewbo.setQuesId(bo.getQuesId());
				if (null != bo.getCorrectOptionsIndex()) {
					for (Integer in : bo.getCorrectOptionsIndex()) {

						if (null != reviewbo.getCorrectOption() && 0 < in) {
							reviewbo.setCorrectOption(reviewbo.getCorrectOption() + "," + in.toString());
						} else if (null == reviewbo.getCorrectOption()) {
							reviewbo.setCorrectOption(in.toString());
						}
					}
				}
				String[] QuestionOptions = new String[4];
				int count = 0;
				int index = 0;
				for (String string : bo.getQuestionOptions()) {
					String[] option = string.split("@`");
					QuestionOptions[count] = option[1];
					System.out.println(exam.getUserSelectionForQuestion(i));
					if (bo.getQuestionType().equals("options")) {
						if (exam.getUserSelectionForQuestion(i) == Integer.parseInt(option[0])) {
							reviewbo.setUserSelectedOption(Integer.toString((count + 1)));
						}
						count++;
					} else if (bo.getQuestionType().equals("Multiple Select")) {

						if (bo.getUsersSelected().size() > index) {
							if (bo.getUsersSelected().get(index) == Integer.parseInt(option[0])) {
								reviewbo.setUserSelected((count + 1));
								if (null != reviewbo.getUserSelectedOption()) {
									reviewbo.setUserSelectedOption(
											reviewbo.getUserSelectedOption() + "," + Integer.toString((count + 1)));

								} else if ((null == reviewbo.getUserSelectedOption())) {
									reviewbo.setUserSelectedOption(Integer.toString((count + 1)));
								}
								index++;
							}
							count++;
						}
					}
				}
				reviewbo.setQuestionOptions(QuestionOptions);
				reviewbo.setCorrectOptionIndex(bo.getCorrectOptionIndex());
				reviewQuestionList.add(reviewbo);
				i++;
			}

			request.getSession(false).setAttribute("reviewQuestions", reviewQuestionList);
			request.getRequestDispatcher("/WEB-INF/jsps/examReview2.jsp").forward(request, response);
		} catch (Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}

	}

	public static void main(String[] args) {
		Integer in = 1;
		String str = in.toString();
		System.out.println(str);
	}

}
