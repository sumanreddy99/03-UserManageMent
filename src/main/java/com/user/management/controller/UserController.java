package com.user.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.binding.Login;
import com.user.management.binding.UnlockAccForm;
import com.user.management.binding.UserForm;
import com.user.management.service.UserManagementService;

@RestController
public class UserController {
	
	@Autowired
	private UserManagementService service;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody Login loginForm)
	{
		String status=service.login(loginForm);
		return new ResponseEntity<>(status , HttpStatus.OK);
		
	}
	
	@GetMapping("/country")
	public Map<Integer, String> getCountries()
	{
		return service.getCountries();
		
	}
	
	@GetMapping("/state/{countryId}")
	public Map<Integer, String> getStates (@PathVariable  Integer countryId)
	{
		return service.getStates(countryId); 
				
	}
 
	@GetMapping("/city/{stateId}") 
	public Map<Integer, String> getCities (@PathVariable  Integer stateId)
	{
	    return	service.getCities(stateId);
	}
	
	
	
	@PostMapping("/userRegistration")
	public ResponseEntity<String> registerUser (@RequestBody   UserForm userForm)
	{
		String status = service.registerUser(userForm);
		
		return new ResponseEntity<String>(status, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/mailCheck/{email}")
	public String checkEmail (@PathVariable String email)
	{
		return service.emailCheck(email);
		
	}
	
	@PostMapping("/unlockAcct")
	public ResponseEntity<String> unlockAccount (@RequestBody UnlockAccForm accForm)
	{
		String status=service.unlockAccount(accForm);
		
		return new ResponseEntity<String>(status,HttpStatus.OK);
		
	}
	
	@GetMapping("/forgotpwd/{email}")
	public ResponseEntity<String> forgotPwd (@PathVariable String email)
	{
		String status=service.forgotPwd(email);
		
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
}
