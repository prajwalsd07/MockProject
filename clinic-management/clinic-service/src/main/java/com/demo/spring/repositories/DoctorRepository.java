package com.demo.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
