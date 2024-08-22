package com.example.demo.customerdao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
