package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.dao.AdminDao;
import com.scube.mycognitiv.dao.AdminDaoImpl;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class CreateQuestion
 */
@WebServlet("/CreateQuestion")
public class CreateQuestion extends ControllerBase {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CreateQuestion.class);
	AdminService service = new AdminServiceImpl();
	AdminDao adminDAOImpl = new AdminDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAuthentication(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("create_question");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		checkAuthentication(request, response);

		try {
			QuizQuestion bo = new QuizQuestion();
			String ques = request.getParameter("question");
			String[] st = new String[4];
			st[0] = request.getParameter("answer1");
			st[1] = request.getParameter("answer2");
			st[2] = request.getParameter("answer3");
			st[3] = request.getParameter("answer4");
			
			String cques = request.getParameter("correctAnswer");
			String questype = request.getParameter("questionType");
			 
			String cap = request.getParameter("complexity");
			Integer cNumber = Integer.parseInt(cap);
			String cat = request.getParameter("category");
			Integer cate = Integer.parseInt(cat);
			String scat = request.getParameter("subcategory");
			Integer scaty = Integer.parseInt(scat);
			if (!ques.isEmpty() && st.length > 0) {

				bo.setQuestion(ques);
				bo.setQuestionOptions(st);
				bo.setCorrectAnswer(cques);
				bo.setQuestionType(questype);
				bo.setComplexity(cNumber);
				bo.setCategoryId(cate);
				bo.setSubcategoryId(scaty);
				bo = adminDAOImpl.createQuestions(bo);

				if (null != bo.getQuestion()) {
					request.setAttribute("errorMessage", bo.getResponse());
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/create_question.jsp");
					dispatcher.forward(request, response);

				}
			} else {
				request.setAttribute("errorMessage", bo.getErrorMessage());
				RequestDispatcher rd = request.getRequestDispatcher("create_question");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage() + e);
			}
		}
	}
}
