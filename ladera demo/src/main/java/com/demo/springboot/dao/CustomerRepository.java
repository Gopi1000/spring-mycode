package com.demo.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.springboot.model.CustomersBo;

@Repository
public interface CustomerRepository extends JpaRepository<CustomersBo, Long> {

	CustomersBo findByEmail(String email);

	CustomersBo getByid(Long id);

}
