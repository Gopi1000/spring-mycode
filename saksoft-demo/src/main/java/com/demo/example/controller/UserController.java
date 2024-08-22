package com.demo.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.example.model.UserModel;
import com.demo.example.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	    @Autowired
	    UserService userService;
	    
		@PostMapping("/login")
		public UserModel login(@RequestParam("email") String email, @RequestParam("password") String password) {
			System.out.println(email);
			System.out.println(password);
			return userService.login(email, password);
        }
		
		 @PutMapping("changepassword")
		    public UserModel changepassword(@RequestParam("email") String email,@RequestParam("password") String password,
		    		@RequestParam("oldpassword")String oldpassword) {
						return userService.updatepassword(email,password,oldpassword);
		    }

	    @PostMapping("/add")
	    public ResponseEntity<UserModel> addvalue(@RequestBody UserModel user) {
	        return new ResponseEntity<UserModel>(userService.saveOrUpdatevalue(user), HttpStatus.CREATED);
	    }

	    @GetMapping("/getall")
	    public Iterable<UserModel> getAllvalues(){
	        return userService.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<UserModel> getById(@PathVariable Long id) {
	        return new ResponseEntity<UserModel>(userService.findById(id), HttpStatus.OK);
	    }
	    
	    @PutMapping("/update/{id}")
	   	public UserModel update(@RequestBody UserModel c, @PathVariable Long id) {
	    	return userService.update(id, c);
	   	}
	    
	    @DeleteMapping("/delete/{id}")
	   	public void deletereview(@PathVariable Long id) {
	    	userService.delete(id);
	   	}
	}

	
