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
import com.scube.mycognitiv.bo.CreateTestBO;
import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;

@WebServlet("/editTest")
public class EditTestController extends ControllerBase {
	AdminService adminService=new AdminServiceImpl();
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		CreateTestBO createTestBO=new CreateTestBO();
		HttpSession session=request.getSession(false);
		List<SubcategoryBO> subcategoryList;
		List<CategoryBO> categoryList;
		CategoryBO categoryBO=new CategoryBO();
		SubcategoryBO subCategory=new SubcategoryBO();
		try {
			subcategoryList = adminService.retrievesubcategory(subCategory);
			if(null!=subcategoryList&&subcategoryList.size()>0){
				request.getSession(false).setAttribute("subcategoryList",
						subcategoryList);
			} 
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			categoryList = adminService.retrievecategory(categoryBO);
			if(null!=categoryList&&categoryList.size()>0){
				request.getSession(false).setAttribute("categoryList", categoryList);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}			
		if(null!=request.getParameter("testId")) {
			String testIdRef=request.getParameter("testId");
			if(null!=testIdRef) {
				int test_Id=Integer.parseInt(testIdRef);
				createTestBO.setTestId(test_Id);
				createTestBO=adminService.editTestByTestId(createTestBO);
				if(null!=createTestBO) {
					request.setAttribute("editTestObj", createTestBO);
					request.setAttribute("subCategoryRef", createTestBO.getSubcategory());
					session.setAttribute("test_Id",test_Id);
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsps/edit_admin_test.jsp");
					rd.forward(request, response);

				}
			}
		}
	}
public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	checkAuthentication(request,response);
	CreateTestBO createTestBO=new CreateTestBO();
	int categoryId=0;
	List<Integer> subCategoryId=new ArrayList<Integer>();
	int selfRate=0;
	int totalQuestionsRef=0;
	int testId=0;
	HttpSession session=request.getSession(false);
	if(null!=request.getParameter("category")) {
		String categoryRef=request.getParameter("category");
		if(null!=categoryRef) {
			categoryId=Integer.parseInt(categoryRef);
			createTestBO.setCategoryId(categoryId);
		}
	}
	if(null!=request.getParameter("subcategories")) {
		String subCategoryRef[]=request.getParameterValues("subcategories");
		if(null!=subCategoryRef) {
			for (String subcate : subCategoryRef) {
				if(null!=subcate) {
				int	subCategory=Integer.parseInt(subcate);
				subCategoryId.add(subCategory);					
				}
			}
			createTestBO.setSubcategoryId(subCategoryId);
		}
	}
	if(null!=request.getParameter("selfRate")) {
		String selfRateRef=request.getParameter("selfRate");
		if(null!=selfRateRef) {
			selfRate=Integer.parseInt(selfRateRef);
			createTestBO.setSelfRate(selfRate);
		}
	}
	if(null!=request.getParameter("noOfQuestions")) {
		String totalQuestions=request.getParameter("noOfQuestions");
		if(null!=totalQuestions) {
			totalQuestionsRef=Integer.parseInt(totalQuestions);
			createTestBO.setTotalQuestion(totalQuestionsRef);

		}
	}
	if(null!=session.getAttribute("test_Id")) {
		testId=(int)session.getAttribute("test_Id");
		createTestBO.setTestId(testId);
	}
	if(null!=createTestBO) {
		createTestBO=adminService.updateTest(createTestBO);
		if(null!=createTestBO) {
			request.setAttribute("updateInfoMessage","Test Has Been Updated Sucessfully!!");
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsps/view_admin_test.jsp");
			rd.forward(request, response);
		}
	}
}

}
