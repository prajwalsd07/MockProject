package com.demo.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.entity.PatientDiagnostic;

public interface PatientDiagnosticRepository extends JpaRepository<PatientDiagnostic, Integer> {

}
