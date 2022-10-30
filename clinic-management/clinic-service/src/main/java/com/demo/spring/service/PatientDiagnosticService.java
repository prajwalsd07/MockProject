package com.demo.spring.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.entity.Diagnostic;
import com.demo.spring.entity.PatientDiagnostic;
import com.demo.spring.exceptions.DiagnosticNotFoundException;
import com.demo.spring.repositories.DiagnosticRepository;
import com.demo.spring.repositories.PatientDiagnosticRepository;
import com.demo.spring.util.Message;
import com.demo.spring.util.ServerConfiguration;

@Service
public class PatientDiagnosticService {
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	@Autowired
	RestTemplate restTemplate;

	@Autowired 
	ServerConfiguration server;

	@Autowired
	DiagnosticRepository diagnosticRepository;

	@Autowired
	PatientDiagnosticRepository patientDiagnosticRepository;

	public Message addTestToPatient(int patientId, int testId) throws DiagnosticNotFoundException {
		Optional<Diagnostic> diagnosticOp = diagnosticRepository.findById(testId);
		if (diagnosticOp.isPresent()) {
			PatientDiagnostic patientDiagnostic = new PatientDiagnostic(testId, patientId);
			patientDiagnosticRepository.save(patientDiagnostic);
			logger.info("Test added to patient succefully");
			return new Message("Test added successfully");
		} else {
			logger.error("Exception : Diagnostic Not Found Exception thrown");
			throw new DiagnosticNotFoundException();
		}

	}
}