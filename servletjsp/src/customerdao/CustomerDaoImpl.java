package customerdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import customerbo.CustomerBo;

public class CustomerDaoImpl implements CustomerDao {
	
	
	public String insertcustomerdetails(CustomerBo bo) {
		
	    try{
			
			//if(bo.equals("co")) {
				
			
			//step-1
			Class.forName("com.mysql.jdbc.Driver");
			
			//Step2
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gopikannan","root","admin");
			             
			//step 3
			Statement s=con.createStatement();
			
			String sql = "insert into customer (customerId,customername,emailid,address,mobileNo,password) value ('"+bo.getCustomerId()+"','"+bo.getCustomerName()+"','"+bo.getEmailid()+"','"+bo.getAddress()+"','"+bo.getMobileNo()+"','"+bo.getPassword()+"')";
			
			s.executeUpdate(sql);
			
			//step 5
			con.close();
			
			//}
		}
			catch (Exception e) {
				
			System.out.println(e);
			
			return null;
			
			}
			
				return "created";
			}
	
	public List<CustomerBo> getCustomerDetails() {
		
		
		 try{
				
				//if(bo.equals("co")) {
					
				
				//step-1
				Class.forName("com.mysql.jdbc.Driver");
				
				//Step2
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gopikannan","root","admin");
				             
				//step 3
				Statement s=con.createStatement();
				
				String sql = "select * from customer";
				
				ResultSet sky=null;
				
				sky=s.executeQuery(sql);
				
				List<CustomerBo> car=new ArrayList<CustomerBo>();
				
				while(sky.next()) {
					
					CustomerBo bus=new CustomerBo();
					
					int customerId=sky.getInt(1);
					String customerName=sky.getString(2);
					String emailId=sky.getString(3);
					String address=sky.getString(4);
					String mobileNo=sky.getString(5);
					String password=sky.getString(6);
					
					bus.setCustomerId(customerId);
					bus.setCustomerName(customerName);
					bus.setEmailid(emailId);
					bus.setAddress(address);
					bus.setMobileNo(mobileNo);
					bus.setPassword(password);
					
					car.add(bus);
					
					}
				
			    //s.executeUpdate(sql);
				
				//step 5
				con.close();
				
				return car;
				
				//}
			}
				catch (Exception e) {
					
				System.out.println(e);
				
		        return null;
	}
		
	
	
	}
	
	
	
	public CustomerBo getParticularValue(int ids) {
		
try{
			
			//if(bo.equals("co")) {
				
			
			//step-1
			Class.forName("com.mysql.jdbc.Driver");
			
			//Step2
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gopikannan","root","admin");
			             
			//step 3
			Statement s=con.createStatement();
			
			
			String sql= "select * from customer where customerId='"+ids+"'";
			ResultSet sky=null;
			sky=s.executeQuery(sql);
			CustomerBo bus=new CustomerBo();
			while(sky.next()) {
				
			//CustomerBo bus=new CustomerBo();
			
			int customerId=sky.getInt(1);
			String customerName=sky.getString(2);
			String emailId=sky.getString(3);
			String address=sky.getString(4);
			String mobileNo=sky.getString(5);
			String password=sky.getString(6);
			
			bus.setCustomerId(customerId);
			bus.setCustomerName(customerName);
			bus.setEmailid(emailId);
			bus.setAddress(address);
			bus.setMobileNo(mobileNo);
			bus.setPassword(password);
			}
			return bus;}
			catch (Exception e) {
				
			System.out.println(e);
			
			return null;
			
			}
			
				
			
	
		
	}	
	
	
	public String updateCustomer(CustomerBo bo) {
		
		try {
		//step-1
		Class.forName("com.mysql.jdbc.Driver");
		
		//Step2
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gopikannan","root","admin");
		             
		//step 3
		Statement s=con.createStatement();
		
		
		String sql="update customer set customername='"+bo.getCustomerName()+"' , emailid='"+bo.getEmailid()+"' , address='"+bo.getAddress()+"' , password='"+bo.getPassword()+"' ,mobileNo='"+bo.getMobileNo()+"' where customerId="+bo.getCustomerId()+"";
		
		s.executeUpdate(sql);

		int count= s.getUpdateCount();
		
		//step 5
		con.close();
		
		//}
		return "updated";
	}
		catch (Exception e) {
			
		System.out.println(e);
		
		return null;
		
		
	}
		

	
	}
	
	
	public String deleteCustomer(int ids) {
		
		
		
		try {
			//step-1
			Class.forName("com.mysql.jdbc.Driver");
			
			//Step2
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gopikannan","root","admin");
			             
			//step 3
			Statement s=con.createStatement();
			
			
			String sql="delete from customer where customerId='"+ids+"'";
			
			s.executeUpdate(sql);

			int count= s.getUpdateCount();
			
			//step 5
			con.close();
			
			//}
			return "deleted";
		}
			catch (Exception e) {
				
			System.out.println(e);
		
		return null;
	}
}
}
	
	
	
	
	
	
	
	

