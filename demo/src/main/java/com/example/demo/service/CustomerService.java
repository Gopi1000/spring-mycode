package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.customerdao.CustomerDao;
import com.example.demo.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao dao;
	
	public List<Customer> getalltopics() {
		return (List<Customer>) dao.findAll();
	}
	
	public Optional<Customer> retrieveOne(int empid) {
		return dao.findById(empid);
	}

	public void saveCustomer(Customer s) {
		dao.save(s);
		System.out.println("successfully added in to the database");
	}

	public void updateCustomer(int id, Customer c) {
        dao.save(c);
	}

	public void deleteCustomer(int id) {
		dao.deleteById(id);
		System.out.println("successfully deleted");
	}

	



	

	
	
	

}
