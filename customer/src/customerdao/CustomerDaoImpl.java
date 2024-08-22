package customerdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDaoImpl implements CustomerDao {
	
	public String tableCreate(String need) throws SQLException {
		
		
		try{
			
		    if(need.equals("tablecreate")) {
				
			
			//driver rigister
			Class.forName("com.mysql.jdbc.Driver");
			//create connection
		    Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","admin");
			//create statement
			Statement s=con.createStatement();
		    String sql = "CREATE TABLE customer_details " + "(customer_id INTEGER not NULL, " + " customer_name VARCHAR(255), " + 
					" city_name VARCHAR(255), " + 
					" age INTEGER, " + " PRIMARY KEY ( customer_id))";
			s.executeUpdate(sql);
			//close connection
			con.close();
			}}
	        catch (Exception e) {
		    System.out.println(e);
		    return null;
	}
			
				// TODO Auto-generated method stub
			return "created";
	}
			

     public String insertvalue (String needs) throws SQLException {
    	 try{
 			
 			if(needs.equals("insertvalue")) {
 				
 			
 				//driver rigister
 			Class.forName("com.mysql.jdbc.Driver");
 			//create connection
 			Connection
 			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","admin");
 			//create statement
 			Statement s=con.createStatement();
 			String sql1 =("insert into customer_details(customer_id, customer_name, city_name, age) value (1,'gopi','thuraiyur',25),(2,'kannan','trichy',27),(3,'punith','bangalore',26)");
 			s.executeUpdate(sql1);
 			//close connection
 			con.close();
 			}}
    	 
    	 
 			catch (Exception e) {
 			System.out.println(e);
 			return null;
 			}
    	    return "done";
     }
     
     public String updatetable (String need1) throws SQLException {
    	 try{
 			
 			if(need1.equals("updatetable")) {
 				
 			
 				//driver rigister
 			Class.forName("com.mysql.jdbc.Driver");
 			//create connection
 			Connection
 			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","admin");
 			//create statement
 			Statement s=con.createStatement();
 			String sql1 =("update customer_details set age=10 where customer_id =1");
 			s.executeUpdate(sql1);
 			//close connection
 			con.close();
 			}}
    	 
    	 
 			catch (Exception e) {
 			System.out.println(e);
 			return null;
 			}
    	    return "updated";
     }
     public String delete (String need2) throws SQLException {
    	 try{
 			
 			if(need2.equals("delete")) {
 				
 			
 				//driver rigister
 			Class.forName("com.mysql.jdbc.Driver");
 			//create connection
 			Connection
 			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","admin");
 			//create statement
 			Statement s=con.createStatement();
 			String sql1 =("delete from customer_details where customer_id =1");
 			s.executeUpdate(sql1);
 			//close connection
 			con.close();
 			}}
    	 
    	 
 			catch (Exception e) {
 			System.out.println(e);
 			return null;
 			}
    	    return "deleted";
     }
}
