package customerservice;

import java.util.List;

import customerbo.CustomerBo;

public interface CustomerService {

	String insertcustomerdetails(CustomerBo bo);

	List<CustomerBo> getCustomerDetails();

	CustomerBo getParticularValue(int ids);

	String updateCustomer(CustomerBo bo);

	String deleteCustomer(int ids);

	

	

	

}
