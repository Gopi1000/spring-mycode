package customerservice;

import java.sql.SQLException;

import customerdao.CustomerDao;
import customerdao.CustomerDaoImpl;

public  class CustomerServiceImpl implements CustomerService {
	
    CustomerDao dao=new CustomerDaoImpl();
    
	public String tableCreate(String need) throws SQLException {
		
	return dao.tableCreate(need);
	}
	
	
	public String insertvalue(String need)throws SQLException{
	
		return dao.insertvalue(need);
	}
	
	public String updatetable(String need1)throws SQLException {
		
		return dao.updatetable(need1);
	}
	public String delete(String need2)throws SQLException {
		
		return dao.delete(need2);
	}
	
	


	}
	


	

	
	
	

