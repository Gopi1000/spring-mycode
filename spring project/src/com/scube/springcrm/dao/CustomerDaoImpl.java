package com.scube.springcrm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.springcrm.model.CustomerBo;
import com.scube.springcrm.util.CRMConnection;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	CRMConnection connection;

	public String insertcustomerdetails(CustomerBo bo) {

		try{
			Connection con=connection.getDBConnection();
			Statement s=con.createStatement();
			String sql = "insert into customer (customerId,customername,emailid,address,mobileNo,password) value ('"+bo.getCustomerId()+"','"+bo.getCustomerName()+"','"+bo.getEmailid()+"','"+bo.getAddress()+"','"+bo.getMobileNo()+"','"+bo.getPassword()+"')";
			s.executeUpdate(sql);
			con.close();

		}
		catch (Exception e) {				
			System.out.println(e);			
			return null;			
		}			
		    return "created";
	}

	public List<CustomerBo> getCustomerDetails() {


		try{

			Connection con=connection.getDBConnection();
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

			//step 5
			con.close();

			return car;

		}
		catch (Exception e) {

			System.out.println(e);

			return null;
		}



	}



	public CustomerBo getParticularValue(int ids) {

		try{

			Connection con=connection.getDBConnection();
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
			return bus;
			
		}
		
		    catch (Exception e) {
			System.out.println(e);
			
			return null;
		}
		}	


	public String updateCustomer(CustomerBo bo) {

		try {
			Connection con=connection.getDBConnection();
			Statement s=con.createStatement();
			String sql="update customer set customername='"+bo.getCustomerName()+"' , emailid='"+bo.getEmailid()+"' , address='"+bo.getAddress()+"' , password='"+bo.getPassword()+"' ,mobileNo='"+bo.getMobileNo()+"' where customerId="+bo.getCustomerId()+"";
			s.executeUpdate(sql);
			int count= s.getUpdateCount();		
			con.close();	
			if(count==1)
			return "updated";
		}
		    catch (Exception e) {
			System.out.println(e);
			
		}
		    return null;
	}


	public String deleteCustomer(int ids) {
		try {
			Connection con=connection.getDBConnection();
			Statement s=con.createStatement();
			String sql="delete from customer where customerId='"+ids+"'";
			s.executeUpdate(sql);
			int count= s.getUpdateCount();			
			con.close();		
			if(count==1)
			return "deleted";
		}
		    catch (Exception e) {
			System.out.println(e);			
		}
		    return null;
	}
}









