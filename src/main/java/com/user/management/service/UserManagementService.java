package com.user.management.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.user.management.binding.Login;
import com.user.management.binding.UnlockAccForm;
import com.user.management.binding.UserForm;


public interface UserManagementService {
	
	
	public String login (Login loginForm);
	
	public Map<Integer, String> getCountries ( ) ;

	public Map<Integer, String> getStates (Integer countryId);

	public Map<Integer, String> getCities (Integer stateId);

	public String registerUser (UserForm userForm);
	
	public String emailCheck (String email);

	public String unlockAccount (UnlockAccForm accForm);

	public String forgotPwd (String email);
	
	
	
	

}
