package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.customerdao.CustomerDao;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;


@RestController
public class CustomerController {
	
	   // demo project with jsp pages
	
		@Autowired
		CustomerDao dao;

		@GetMapping("/")
		public String viewpage(Model model) {
			List<Customer> listcustomer = dao.findAll();
			System.out.println("Get");
			if (null != listcustomer && listcustomer.size() > 0) {
				model.addAttribute("listcustomer", listcustomer);
			}
			return "index";
		}

		@GetMapping("/customer-create")
		public String viewHomePage() {
			return "create customer";
		}

		@PostMapping(value = "/add-customer")
		public String addcustomer(@ModelAttribute("customer") Customer std, Model model) {
			dao.save(std);
			System.out.println("successfully added in to the database");
			return "redirect:/";
		}

		@PutMapping(value = "/edit/{customerId}")
		public ModelAndView showEditPage(@PathVariable(name = "customerId") int customerId) {
			ModelAndView mav = new ModelAndView("update");
			Customer std = dao.findById(customerId).get();
			mav.addObject("customer", std);
			return mav;

		}

		@DeleteMapping("/delete/{customerId}")
		public String deletestudent(@PathVariable(name = "customerId") int customerId) {
			dao.deleteById(customerId);
			System.out.println("successfully deleted");
			return "redirect:/";
		}
		 
	  
	  
	 // demo project for crud operation
			
	@Autowired
	CustomerService service;
    
	//@ResponseBody
	@GetMapping("/list")
	public List<Customer> getalltopics() {
		return service.getalltopics();
	}
	
	@GetMapping("/list/{id}")
    public Optional<Customer> getById(@PathVariable("id") int id) {
      Optional<Customer> employee = service.retrieveOne(id);
	return employee;  
	}
      
	@PostMapping(value ="/savecustomer")
	public void savecustomer(@RequestBody Customer s) {
       service.saveCustomer(s);
    }

	@PutMapping(value = "/customer/{id}")
	public void updateCustomer(@RequestBody Customer c, @PathVariable int id) {
      service.updateCustomer(id, c);
	}

	@DeleteMapping(value = "/deletecustomer/{id}")
	public void deleteCustomer(@PathVariable int id) {
		service.deleteCustomer(id);
	}
			
	 

}
