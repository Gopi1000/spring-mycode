package com.scube.mycognitiv.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scube.mycognitiv.bo.SubcategoryBO;
import com.scube.mycognitiv.service.AdminService;
import com.scube.mycognitiv.service.AdminServiceImpl;

/**
 * Servlet implementation class EditPriorityController
 */
@WebServlet("/EditPriority")
public class EditPriorityController extends ControllerBase {
	private static final long serialVersionUID = 1L;
	AdminService service = new AdminServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPriorityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAuthentication(request,response);
		try{
			
		SubcategoryBO updatebo;
		ArrayList<SubcategoryBO> updateList=new ArrayList<SubcategoryBO>();
		String size=request.getParameter("listsize");
		int listsize=Integer.parseInt(size);
		for(int i=1;i<=listsize;i++){
			
			updatebo=new SubcategoryBO();
			String prioritys=request.getParameter("priority"+i);
			int priority1=Integer.parseInt(prioritys);
			updatebo.setPriority(priority1);
			String subcategory=request.getParameter("subcategory"+i);
			updatebo.setSubcategory(subcategory);
			updateList.add(updatebo);
		}
			
		ArrayList<SubcategoryBO> updateList1=service.updatepriority(updateList);
		if(null!=updateList1&&updateList1.size()>0){
			request.setAttribute("errorMessage", "Data Updated Successfully.");

			RequestDispatcher rd = request
					.getRequestDispatcher("view_category");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("errorMessage", "Priority Updated Failed.");
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/jsps/admin_home.jsp");
			rd.forward(request, response);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
