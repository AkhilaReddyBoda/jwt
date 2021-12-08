package com.springboot.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jwt.model.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {
	
	
	Employee findByEmail(String email);
	
}
