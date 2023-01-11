package com.user.management.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.entity.Country;
@Repository
public interface CountryRepo extends JpaRepository<Country, Serializable> {

}
