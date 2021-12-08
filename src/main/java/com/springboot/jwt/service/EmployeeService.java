package com.springboot.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jwt.dao.EmployeeDAO;
import com.springboot.jwt.model.Employee;

@Service
public class EmployeeService {

	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	
	public Object addEmployee(Employee employee) {
	
		return employeeDAO.save(employee);
	}
	
	public Object findEmp(Long Id) {
		return employeeDAO.findById(Id);
	}
}
