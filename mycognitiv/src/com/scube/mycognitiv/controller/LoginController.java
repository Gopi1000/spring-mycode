package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.scube.mycognitiv.bo.RegistrationBO;
import com.scube.mycognitiv.bo.ResultBO;
import com.scube.mycognitiv.bo.UserBO;
import com.scube.mycognitiv.service.CandidateService;
import com.scube.mycognitiv.service.CandidateServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns = { "/login"})
public class LoginController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	// create the object from service
	CandidateService service = new CandidateServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userType=request.getParameter("user");
		request.setAttribute("userType", userType);
		RequestDispatcher rd = request
				.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("username");
			String password = request.getParameter("password");

			if (null!=email&& null!=password) {
				RegistrationBO bo = new RegistrationBO();
				bo.setEmail(email);
				bo.setPassword(password);
				bo = service.authendicate(bo);

				if (null!=bo && bo.getRegId()>0) {
					if (bo.getUserType().equalsIgnoreCase("Candidate")) {
						ResultBO bos = getTestDetails(bo.getRegId());

						HttpSession session = request.getSession(false);
						session.setAttribute("user", bo.getUserName());
						session.setAttribute("email",bo.getEmail());
						session.setAttribute("userId", bo.getRegId());
						session.setAttribute("userType", bo.getUserType());
						session.setAttribute("testDetails",
								bos.getResultBOList());
						RequestDispatcher rd = request
								.getRequestDispatcher("/WEB-INF/jsps/home.jsp");
						rd.forward(request, response);
					} else if (bo.getUserType().equalsIgnoreCase("Admin")) {
						HttpSession session = request.getSession(false);
						session.setAttribute("user", bo.getUserName());
						session.setAttribute("email",bo.getEmail());
						session.setAttribute("userId", bo.getRegId());
						session.setAttribute("userType", bo.getUserType());
						UserBO bos = null;
						try {
							bos = service.retrieveAllCandidate(bos);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (null != bos.getUserList()
								&& bos.getUserList().size() > 0) {
							request.setAttribute("userList", bos.getUserList());
							session.setAttribute("userList", bos.getUserList());
						} else {
							request.setAttribute("errorMessage",
									"No data found");
						}
						ResultBO bo1 = null;
						try {
							bo1 = service.retrieveTestDetail();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (null != bo1.getResultBOList()
								&& bo1.getResultBOList().size() > 0) {
							request.setAttribute("testName",
									bo1.getResultBOList());
							session.setAttribute("testName",
									bo1.getResultBOList());
						} else {
							session.setAttribute("testName", "No data found");
							request.setAttribute("errorMessage",
									"No data found");
						}

						RequestDispatcher rd = request
								.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
						rd.forward(request, response);
					}else if (bo.getUserType().equalsIgnoreCase("Company")) {
						HttpSession session = request.getSession(false);
						session.setAttribute("user", bo.getUserName());
						session.setAttribute("companymail", bo.getEmail());
						session.setAttribute("userId", bo.getRegId());
						session.setAttribute("userType", bo.getUserType());
						RequestDispatcher rd = request
								.getRequestDispatcher("/WEB-INF/jsps/company_home.jsp");
						rd.forward(request, response);
					}else if (bo.getUserType().equalsIgnoreCase("Professor")) {						
						HttpSession session = request.getSession(false);
						session.setAttribute("user", bo.getUserName());
						session.setAttribute("email", bo.getEmail());
						session.setAttribute("userId", bo.getRegId());
						session.setAttribute("userType", bo.getUserType());
						RequestDispatcher rd = request
								.getRequestDispatcher("/WEB-INF/jsps/professor_home.jsp");
						rd.forward(request, response);
					} 			
					
				} else {
					request.setAttribute("errorMessage",
							"Invalid username or password");
					RequestDispatcher rd = request
							.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("errorMessage",
						"Please Enter Mandatory Filds ");
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
				rd.forward(request, response);
			}
		} catch (Exception sqe) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(sqe.getMessage() + sqe);
			}
			//System.out.println("Error : While Fetching records from database");
		}
	}

	public ResultBO getTestDetails(int userId) throws SQLException {
		ResultBO bo = new ResultBO();
		try {
			bo = service.getTakenTestDetails(userId);
			} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}
}
