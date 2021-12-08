package com.springboot.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jwt.dao.EmployeeDAO;
import com.springboot.jwt.model.Employee;
import com.springboot.jwt.model.JwtRequest;
import com.springboot.jwt.model.JwtResponse;
import com.springboot.jwt.service.EmployeeService;
import com.springboot.jwt.service.UserService;
import com.springboot.jwt.utility.JWTUtility;

@RestController
public class HomeController {
	
	@Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService empService;
    @Autowired
    private EmployeeDAO empDao;

	/*
	 * @GetMapping("/") public String home() { return"welcome"; }
	 */
	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }
	
	@PostMapping("/addEmp")
	public Object addEmployee(@RequestBody Employee employee) {
		Employee chechEmp = empDao.findByEmail(employee.getEmail());
		if(chechEmp == null) {
			return empService.addEmployee(employee);
		}
		else {
			return "Email already used";
		}
	}
	@GetMapping("/getEmp/{id}")
	public Object getEmp(@PathVariable Long id) {
		return empService.findEmp(id);
	}
	
}
