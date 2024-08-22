package com.scube.springcrm.service;

import java.util.List;

import com.scube.springcrm.model.CustomerBo;

public interface CustomerService {

	String insertcustomerdetails(CustomerBo bo);

	List<CustomerBo> getCustomerDetails();

	CustomerBo getParticularValue(int ids);

	String updateCustomer(CustomerBo bo);

	String deleteCustomer(int ids);

	

	

	

	

	

}
