package com.user.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="COUNTRY")
public class Country {

	@Id
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "COUNTRY_NAME")
	private String countryName;
	
}
