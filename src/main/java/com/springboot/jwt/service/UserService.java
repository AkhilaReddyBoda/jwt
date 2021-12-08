package com.springboot.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.jwt.dao.EmployeeDAO;
import com.springboot.jwt.model.Employee;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private EmployeeDAO empDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee employee = empDAO.findByEmail(username);
		if (employee == null) {
			throw new UsernameNotFoundException("User derails not found");
		}

		return new EmpoyeeDetails(employee);
	}

}
