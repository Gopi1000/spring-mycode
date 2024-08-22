package com.demo.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.springboot.dao.CustomerRepository;
import com.demo.springboot.model.CustomersBo;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository Dao;

	public void saveCustomer(CustomersBo s) {
		Dao.save(s);
		System.out.println("successfully added in to the database");
	}

	public Iterable<CustomersBo> findAll() {
		return Dao.findAll();
	}

	public CustomersBo findById(Long id) {
		return Dao.getByid(id);
	}
}
