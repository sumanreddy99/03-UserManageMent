package com.user.management.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.entity.State;
@Repository
public interface StateRepo extends JpaRepository<State, Serializable> {
	
	//select * from state where countryId=?
	public  List<State> findByCountryId(Integer countryId);

}
