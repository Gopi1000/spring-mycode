package customercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customerbo.CustomerBo;
import customerservice.CustomerService;
import customerservice.CustomerServiceImpl;

public class UpdateCustomer extends HttpServlet  {
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
	       // response.setContentType("text/jsp");
			
			PrintWriter go=response.getWriter();
			
			CustomerService co=new CustomerServiceImpl();
			
			CustomerBo bo=new CustomerBo();
			
			String id=(request.getParameter("id"));
			int ids=Integer.parseInt(id);
			bo.setCustomerName(request.getParameter("customername"));
			bo.setEmailid(request.getParameter("emailid"));
			bo.setAddress(request.getParameter("address"));
			bo.setMobileNo(request.getParameter("mobileNo"));
			bo.setPassword(request.getParameter("password"));
			bo.setCustomerId(ids);
			String count =co.updateCustomer(bo);
			
if(null!=count) {
	List<CustomerBo> car=new ArrayList<CustomerBo>();
	
	car=co.getCustomerDetails();
				request.setAttribute("carlist",car);
				
				RequestDispatcher reg=request.getRequestDispatcher("customerreglist.jsp");
				
				reg.forward(request,response);
}


	
}

public void doGet(HttpServletRequest request,HttpServletResponse response) {
	String id=(request.getParameter("id"));
	int ids=Integer.parseInt(id);
	CustomerService co=new CustomerServiceImpl();
	String count=co.deleteCustomer(ids);
	
	if(count.equals("deleted")) {
		
		List<CustomerBo> car=new ArrayList<CustomerBo>();
		
		car=co.getCustomerDetails();
					request.setAttribute("carlist",car);
					
					RequestDispatcher reg=request.getRequestDispatcher("customerreglist.jsp");
					
					try {
						reg.forward(request,response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}
	
	
}
}