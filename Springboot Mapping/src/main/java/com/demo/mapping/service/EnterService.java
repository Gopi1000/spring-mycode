package com.demo.mapping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.demo.mapping.dao.EnterRepository;
import com.demo.mapping.model.Users;

@Service
public class EnterService {

	@Autowired
	EnterRepository enterDao;

	public void saveCustomer(Users s) {
		enterDao.save(s);
		System.out.println("successfully added in to the database");
	}

	public Iterable<Users> findAll() {
		return enterDao.findAll();
	}

	public Users login(String email, String password) {
		Users user = enterDao.findByEmail(email);
	if (user != null && user.getPassword().equals(password)) {
			user.setPassword(null);
			return user;

		} else
			return (Users) ResponseEntity.notFound();
	}

	public Boolean updatepassword(String email, String password, String oldpassword) {
		Users userObj = enterDao.findByEmail(email);
		if (userObj != null && userObj.getPassword().equals(oldpassword)) {
			userObj.setPassword(password);
			enterDao.save(userObj);
			return true;
		} else {
			return false;
		}
		
	}

}
