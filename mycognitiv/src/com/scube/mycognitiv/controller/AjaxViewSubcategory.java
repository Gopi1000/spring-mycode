package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;

/**
 * Servlet implementation class AjaxViewSubcategory
 */
@WebServlet("/AjaxViewSubcategory")
public class AjaxViewSubcategory extends ControllerBase {
	private static final long serialVersionUID = 1L;
	AdminService adminService = new AdminServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxViewSubcategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = null;
		ArrayList<SubcategoryBO> List=new ArrayList<SubcategoryBO>();
		//System.out.println("ajaxcid::"+request.getParameter("category"));
		SubcategoryBO bo;
		String category=request.getParameter("category");
		 int categoryId=Integer.parseInt(category);
	      try {
	    	
	    	 ArrayList<SubcategoryBO> subcategoryList=adminService.getsubcategory(categoryId);
	    	 if(null!=subcategoryList&&subcategoryList.size()>0){
	    		 for(SubcategoryBO iterate:subcategoryList){
	    			 bo=new SubcategoryBO();
	    			 bo.setSubcategoryId(iterate.getSubcategoryId());
	    			bo.setSubcategory(iterate.getSubcategory());
	    			
	    			 List.add(bo);
	    		 }
	    	 }
	    	 json = new Gson().toJson(List);
	 		response.setContentType("application/json");
	 		response.getWriter().write(json);
	         
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
