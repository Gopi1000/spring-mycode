package com.demo.mapping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.mapping.model.Users;


@Repository
public interface EnterRepository extends JpaRepository<Users, Long> {

	Users findByEmail(String email);


}
