package customerdao;

import java.util.List;

import customerbo.CustomerBo;

public interface CustomerDao {

	String insertcustomerdetails(CustomerBo bo);

	List<CustomerBo> getCustomerDetails();

	CustomerBo getParticularValue(int ids);

	String updateCustomer(CustomerBo bo);

	String deleteCustomer(int ids);

	

}
