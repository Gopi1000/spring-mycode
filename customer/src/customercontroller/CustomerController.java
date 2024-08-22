package customercontroller;

import java.sql.SQLException;
import java.util.Scanner;

import customerservice.CustomerService;
import customerservice.CustomerServiceImpl;

public class CustomerController {

	public static void main(String[] args) throws SQLException {
		CustomerService co=new CustomerServiceImpl();
		String output=null;
		Scanner sc=new Scanner(System.in);
        String need=sc.nextLine();
		switch(need) {
		case "tablecreate": System.out.println("user want to table creation");
	   output=	co.tableCreate(need);
		System.out.println("TABLE CREATED");
		break;
		}
		if(output.equals("created")) {
		String needs ="insertvalue";
		String outputs = co.insertvalue(needs);
		System.out.println("value added");
		
		if (outputs.equals("done")) {
			String need1="updatetable";
			String output1=co.updatetable(need1);
			System.out.println("value updated");
			
		if (output1.equals("updated"))	{
			String need2="delete";
			String output2=co.delete(need2);
			System.out.println("deleted");
			System.out.println("all are successfully executed");
		}
		}
		
	}
	
	}
		}
		



