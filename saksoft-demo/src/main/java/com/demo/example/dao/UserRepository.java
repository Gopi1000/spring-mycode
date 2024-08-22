package com.demo.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.example.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

	UserModel getByid(Long id);

	UserModel findByEmail(String email);

}
