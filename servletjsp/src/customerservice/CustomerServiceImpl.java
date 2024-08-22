package customerservice;

import java.util.ArrayList;
import java.util.List;

import customerbo.CustomerBo;
import customerdao.CustomerDao;
import customerdao.CustomerDaoImpl;

public class CustomerServiceImpl implements CustomerService {
	
	public String insertcustomerdetails(CustomerBo bo) {
		
		CustomerDao dao=new CustomerDaoImpl();
		
		return dao.insertcustomerdetails(bo);
	}

	public List<CustomerBo> getCustomerDetails() {
		
		CustomerDao dao=new CustomerDaoImpl();
		
		List<CustomerBo> car=new ArrayList<CustomerBo>();
		
		car=dao.getCustomerDetails();
		
		return car;
	}
public	CustomerBo getParticularValue(int ids) {
	CustomerDao dao=new CustomerDaoImpl();
	return dao.getParticularValue(ids);
	
		
	}
public String updateCustomer(CustomerBo bo) {
	CustomerDao dao=new CustomerDaoImpl();
	return dao.updateCustomer(bo);
}

public String deleteCustomer(int ids) {
	CustomerDao dao=new CustomerDaoImpl();
	return dao.deleteCustomer(ids);
}
		 
	}

