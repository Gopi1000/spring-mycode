package com.demo.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.example.dao.UserRepository;
import com.demo.example.model.UserModel;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public UserModel saveOrUpdatevalue(UserModel user) {
		userRepo.save(user);
		user.setPassword(null);
		return user;
	}

	public Iterable<UserModel> findAll() {
		return userRepo.findAll();
	}

	public UserModel findById(Long id) {
		return userRepo.getByid(id);
	}

	public UserModel update(Long id, UserModel c) {
		userRepo.save(c);
		c.setPassword(null);
		return c;
	}

	public void delete(Long id) {
		userRepo.deleteById(id);
		;
	}

	public UserModel login(String email, String password) {
		UserModel user = userRepo.findByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			user.setPassword(null);
			return user;

		} else
			return (UserModel) ResponseEntity.notFound();
	}

	public UserModel updatepassword(String email, String password, String oldpassword) {
		UserModel userObj = userRepo.findByEmail(email);
		if (userObj != null && userObj.getPassword().equals(oldpassword)) {
			userObj.setPassword(password);
			userRepo.save(userObj);
			return userObj;
		} else {
			return (UserModel) ResponseEntity.notFound();
		}

	}

}
