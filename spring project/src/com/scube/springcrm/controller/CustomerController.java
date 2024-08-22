package com.scube.springcrm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scube.springcrm.model.CustomerBo;
import com.scube.springcrm.service.CustomerService;


  @Controller
  public class CustomerController {
	  
	@Autowired
	CustomerService co;
	
	@RequestMapping(value="/customer-create", method = RequestMethod.POST)
	public String createCustomer(@ModelAttribute CustomerBo customer,Model model) throws IOException, ServletException {		
		String response=null;
	
		if(null!=customer) {
		 response =co.insertcustomerdetails(customer);		
			}	
		
		if(null!=response) {			
		    System.out.println("Registration Successful");			
			List<CustomerBo> car=new ArrayList<CustomerBo>();
			car=co.getCustomerDetails();
			
		if(null!=car&& car.size()>0) {	
				System.out.println("value retrieved");
				model.addAttribute("carlist",car);				
			}
		}                                                                              
			
		return "customer-list";
		
	   }
	
	
	@RequestMapping(value="/customer-create", method = RequestMethod.GET)	
	public String createCustomer(Model model) throws IOException, ServletException{		
		
		CustomerBo bo=new CustomerBo();		
		model.addAttribute("customer", bo);
		return "customer-create";
	}
			
	
}



