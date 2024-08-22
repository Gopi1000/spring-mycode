package customercontroller;

import java.io.IOException;
//import java.io.PrintWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import customerbo.CustomerBo;
import customerservice.CustomerService;
import customerservice.CustomerServiceImpl;

public class CustomerController extends HttpServlet {

	
	

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
       // response.setContentType("text/jsp");
		
		PrintWriter go=response.getWriter();
		
		CustomerService co=new CustomerServiceImpl();
		
		CustomerBo bo=new CustomerBo();
		
		
		bo.setCustomerName(request.getParameter("customername"));
		bo.setEmailid(request.getParameter("emailid"));
		bo.setAddress(request.getParameter("address"));
		bo.setMobileNo(request.getParameter("mobileNo"));
		bo.setPassword(request.getParameter("password"));
		
		String count =co.insertcustomerdetails(bo);
		
		
		if(null!=count) {
			
		    System.out.println("Registration Successful! Please Login");
			
			//response.sendRedirect("new.jsp");
			
			List<CustomerBo> car=new ArrayList<CustomerBo>();
			
			car=co.getCustomerDetails();
			
			if(null!=car&& car.size()>0) {
				
				request.setAttribute("carlist",car);
				
				RequestDispatcher reg=request.getRequestDispatcher("customerreglist.jsp");
				
				reg.forward(request,response);
				
				
				
			}
		}                                                                              
			
		else {
			
			go.println("<h1>Registration Failed</h1>");
		
		     }
	   }
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		System.out.println("k");
		System.out.println("k");
		String id=(request.getParameter("id"));
		int ids=Integer.parseInt(id);
		CustomerService co=new CustomerServiceImpl();
		CustomerBo bo=new CustomerBo();
		bo=co.getParticularValue(ids);
		
		request.setAttribute("particular",bo);
		
		RequestDispatcher reg=request.getRequestDispatcher("customerparticular.jsp");
		
		reg.forward(request,response);
	}
			
	
}



