package com.example.crud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.School;
import com.example.crud.service.SchoolService;

@RestController
public class AppController {

	@Autowired
	SchoolService schoolservice;

	@RequestMapping("/userslist")
	public List<School> getalltopics() {
		return schoolservice.getalltopics();
	}

	@PostMapping(value = "/saveschool")
	public void saveSchool(@RequestBody School s) {

		schoolservice.saveSchool(s);

	}

	@PutMapping(value = "/schools/{id}")
	public void updateSchool(@RequestBody School s, @PathVariable int id) {

		schoolservice.updateSchool(id, s);
	}

	@DeleteMapping(value = "/deleteschools/{id}")
	public void deleteSchool(@PathVariable int id) {
		schoolservice.deleteSchool(id);
	}

}
