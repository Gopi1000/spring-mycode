package com.scube.springcrm.dao;

import java.util.List;

import com.scube.springcrm.model.CustomerBo;

public interface CustomerDao {

	String insertcustomerdetails(CustomerBo bo);

	List<CustomerBo> getCustomerDetails();

	CustomerBo getParticularValue(int ids);

	String updateCustomer(CustomerBo bo);

	String deleteCustomer(int ids);

	

	

}
