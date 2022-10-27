package com.demo.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.spring.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	@Query("select d from Doctor d where d.email=:email")
	public Optional<Doctor> findByEmail(String email);
}
