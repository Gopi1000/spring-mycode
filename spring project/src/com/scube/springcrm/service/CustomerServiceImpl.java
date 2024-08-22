package com.scube.springcrm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.scube.springcrm.dao.CustomerDao;
import com.scube.springcrm.model.CustomerBo;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDao dao;
	
	
	

	public String insertcustomerdetails(CustomerBo bo) {

    return dao.insertcustomerdetails(bo);
	}

	public List<CustomerBo> getCustomerDetails() {

	List<CustomerBo> car=new ArrayList<CustomerBo>();

	car=dao.getCustomerDetails();

	return car;
	}
	
	public	CustomerBo getParticularValue(int ids) {
	
	return dao.getParticularValue(ids);
    }
	
	public String updateCustomer(CustomerBo bo) {
	
    return dao.updateCustomer(bo);
	}

	public String deleteCustomer(int ids) {
	
	return dao.deleteCustomer(ids);
	}
	
	
	
	

}

