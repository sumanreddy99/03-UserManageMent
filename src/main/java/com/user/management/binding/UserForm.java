package com.user.management.binding;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
public class UserForm{
	
	
	private String email;
	private Long phno;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String gender;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	
	
	
	

}
