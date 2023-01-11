package com.user.management.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.entity.City;

@Repository
public interface CityRepo extends JpaRepository<City, Serializable> {
	
	//select * from city where stateId=?
	public List<City> findByStateId(Integer stateId);
}
