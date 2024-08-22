package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.scube.mycognitiv.bo.UploadQuestionBO;
 
/**
 * Servlet implementation class AjaxServelt
 */
@WebServlet("/ajaxServelt")
public class AjaxServelt extends ControllerBase {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String testName = request.getParameter("testName").trim();
		TreeSet<String>testLevelList=new TreeSet<String>();
		 String json = null;
        if(testName == null || "".equals(testName)){
        	testLevelList.add("");
        }else{
        	HttpSession session=request.getSession(false);
        	if(null!=session.getAttribute("testLevel")){
        		List<UploadQuestionBO>getTestDetails=(List<UploadQuestionBO>) session.getAttribute("testLevel");
        		for(UploadQuestionBO testLevel:getTestDetails){
        			if(testName.equalsIgnoreCase(testLevel.getTestName())){
        				testLevelList.add(testLevel.getTestLevel());
        			}
        		}
        	}
        }
    
        json = new Gson().toJson(testLevelList);
		response.setContentType("application/json");
		response.getWriter().write(json);
        
        
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
