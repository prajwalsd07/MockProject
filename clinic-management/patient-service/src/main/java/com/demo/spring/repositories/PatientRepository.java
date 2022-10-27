package com.demo.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.spring.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	@Query("select p from Patient p where p.firstName=:firstName")
	public List<Patient> findAllByFirstName(String firstName);

}
