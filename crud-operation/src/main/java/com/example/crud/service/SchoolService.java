package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.dao.SchoolRepository;
import com.example.crud.model.School;

@Service
public class SchoolService {

	@Autowired
	SchoolRepository repository;

	public List<School> getalltopics() {
		return (List<School>) repository.findAll();
	}

	public void saveSchool(School s) {

		repository.save(s);
		System.out.println("successfully added in to the database");
	}

	public void updateSchool(int id, School s) {

		repository.save(s);
	}

	public void deleteSchool(int id) {
		repository.deleteById(id);
		System.out.println("successfully deleted");
	}

}
