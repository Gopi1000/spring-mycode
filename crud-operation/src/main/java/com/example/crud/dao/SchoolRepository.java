package com.example.crud.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.model.School;

@Repository
public interface SchoolRepository extends CrudRepository<School, Integer>{

}
