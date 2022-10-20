package com.demo.spring.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.entity.Diagnostic;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Integer> {
	
}
