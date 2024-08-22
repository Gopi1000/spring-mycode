package com.scube.mycognitiv.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.scube.mycognitiv.dao.AdminDao;
import com.scube.mycognitiv.dao.AdminDaoImpl;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;
import com.scube.mycognitiv.utils.CreateDOM;
import com.scube.mycognitiv.utils.Exam;
import com.scube.mycognitiv.utils.QuizQuestion;

/**
 * Servlet implementation class FileUploadController
 */
@WebServlet("/fileUpload")
public class FileUploadController extends ControllerBase {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(FileUploadController.class);
	// create the instance object
	String testName;
	String category;
	String subcategory;
	String fileName;
	AdminService service = new AdminServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadController() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		checkAuthentication(request,response);
		List<QuizQuestion> questions = new ArrayList<QuizQuestion>();
		QuizQuestion bo = new QuizQuestion();

		ArrayList<Integer> subcategoryid = new ArrayList<>();

		File file = null;
		int maxMemSize = 5000 * 1024;
		ServletContext context = getServletContext();
		String filePath = context.getInitParameter("file-upload");
		try {
			// Verify the content type
			String contentType = request.getContentType();
			if ((contentType.indexOf("multipart/form-data") >= 0)) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// maximum size that will be stored in memory
				factory.setSizeThreshold(maxMemSize);
				// Location to save data that is larger than maxMemSize.
				factory.setRepository(new File("E:\\Quizzes"));
				LOGGER.info("get the local directry");
				if (ServletFileUpload.isMultipartContent(request)) {
					List<FileItem> multiparts = new ServletFileUpload(
							new DiskFileItemFactory()).parseRequest(request);
					for (FileItem item : multiparts) {
						if (!item.isFormField()) {
							fileName = new File(item.getName()).getName();
							if (fileName.lastIndexOf("\\") >= 0) {
								file = new File(filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\")));
								LOGGER.info("File Writing");
							} else {
								file = new File(filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\") + 1));
								LOGGER.info("File Writing" + file);
							}
							item.write(file);
						} else {
							if (item.getFieldName()
									.equalsIgnoreCase("category")) {
								category = item.getString();
								int categoryId = Integer.parseInt(category);
								bo.setCategoryId(categoryId);
							}
							if (item.getFieldName()
									.equalsIgnoreCase("subcategory")) {
								subcategory = item.getString();
								int subcategoryId = Integer
										.parseInt(subcategory);
								
								bo.setSubcategoryId(subcategoryId);
							}
						}
					}
				}
				Document document = null;
				AdminDao adminDAOImpl = new AdminDaoImpl();
				try {
					document = CreateDOM.getDOM(file.getAbsolutePath());
					String totalNumberOfQuizQuestions = document
							.getElementsByTagName("totalQuizQuestions").item(0)
							.getTextContent();
					Exam exam = new Exam(file.getAbsolutePath(),
							Integer.parseInt(totalNumberOfQuizQuestions));
					for (int i = 0; i < Integer
							.parseInt(totalNumberOfQuizQuestions); i++) {
						exam.currentQuestion = i;
						exam.setQuestion(exam.currentQuestion, request);
						questions.add(exam.questionList
								.get(exam.currentQuestion));
					}
					for (QuizQuestion q : questions) {
						q.setTestId(bo.getTestId());
						q.setCategoryId(bo.getCategoryId());
						q.setSubcategoryId(bo.getSubcategoryId());
						q.setComplexity(0);
						bo = adminDAOImpl.createQuestions(q);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != bo.getResponse() && !bo.getResponse().isEmpty()) {
				LOGGER.info("File has been Uploaded");
				request.setAttribute("errorMessage",
						"File has been Uploaded Successfully");
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage",
						"question details not saved");
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsps/upload_questions.jsp");
				rd.forward(request, response);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(ex.getMessage() + ex);
			}
		}
	}
}