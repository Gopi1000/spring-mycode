package customerservice;

import java.sql.SQLException;

public interface CustomerService {

	String tableCreate(String need) throws SQLException;
	
	String insertvalue(String need) throws SQLException;

	String updatetable(String need1)throws SQLException;

	String delete(String need2)throws SQLException;
	
	
	
	

}
