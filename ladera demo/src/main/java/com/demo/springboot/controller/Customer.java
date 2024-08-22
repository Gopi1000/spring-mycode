package com.demo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.springboot.model.CustomersBo;
import com.demo.springboot.service.CustomerService;

@RestController
@CrossOrigin("*")
public class Customer {

	@Autowired
	CustomerService service;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/register")
	public void savecustomer(@RequestBody CustomersBo s) {
		service.saveCustomer(s);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getall")
	public Iterable<CustomersBo> getAllUsers() {
		return service.findAll();

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	public ResponseEntity<CustomersBo> getBookingById(@PathVariable Long id) {
		return new ResponseEntity<CustomersBo>(service.findById(id), HttpStatus.OK);
	}
}
