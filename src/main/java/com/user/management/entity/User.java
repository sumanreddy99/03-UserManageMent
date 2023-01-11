package com.user.management.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="USER_DTLS")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name =  "USER_ID")
	private Integer userId;
	
	@Column(name =  "FNAME")
	private String firstName;
	
	@Column(name =  "LNAME")
	private String lastName;
	
	@Column(name =  "EMAIL")
	private String email;
	
	@Column(name =  "PHNO")
	private Long phno;
	
	@Column(name =  "DOB")
	private LocalDate dob;
	
	@Column(name =  "GENDER")
	private String gender;
	
	@Column(name =  "COUNTRY")
	private Integer countryId;
	
	@Column(name =  "STATE")
	private Integer stateId;
	
	@Column(name =  "CITY")
	private Integer cityId;
	
	@Column(name =  "USER_PWD")
	private String password;
	
	@Column(name =  "ACC_STATUS")
	private String activeStatus;

	
	
}
