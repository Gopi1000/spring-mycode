package com.demo.mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.mapping.model.Users;
import com.demo.mapping.service.EnterService;

@RestController
@CrossOrigin("*")
public class Enter {
	
	 @Autowired
	 EnterService enterservice;
	 
	 @CrossOrigin(origins = "http://localhost:4200")
	 @PostMapping("/register")
	 public void savecustomer(@RequestBody Users s) {
		 enterservice.saveCustomer(s);
	    }
	 @CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/getall")
	    public Iterable<Users> getAllUsers(){
	        return enterservice.findAll();
	    }
	 
	    @CrossOrigin(origins = "http://localhost:4200")
		@PostMapping("/login")
		public Users login(@RequestParam("email") String email, @RequestParam("password") String password) {
			System.out.println(email);
			System.out.println(password);
			return enterservice.login(email, password);

		}
	    @CrossOrigin(origins = "http://localhost:4200")
	    @PutMapping("changepassword")
	    public Boolean changepassword(@RequestParam("email") String email,@RequestParam("password") String password,
	    		@RequestParam("oldpassword")String oldpassword) {
					return enterservice.updatepassword(email,password,oldpassword);
	    }
		 
}
