package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.spring.dto.PatientDTO;
import com.demo.spring.entity.Patient;
import com.demo.spring.exceptions.PatientNotFoundException;
import com.demo.spring.repositories.PatientRepository;
import com.demo.spring.util.Message;

@Service
public class PatientService {

	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	@Autowired
	PatientRepository patientRepo;

	public ResponseEntity<Patient> findPatientService(int patientId) throws PatientNotFoundException {
		Optional<Patient> patientOp = patientRepo.findById(patientId);
		if (patientOp.isPresent()) {
			logger.info("patient object succesfully returned with patientId {}",patientId);
			return ResponseEntity.ok(patientOp.get());
		} else {
			logger.error("Exception : Patient Not Found Exception thrown");
			throw new PatientNotFoundException();
		}

	}

	public ResponseEntity<Message> savePatientService(PatientDTO patientDTO) {
		
		Optional<Patient> patientex = patientRepo.findByEmail(patientDTO.getEmail());
		if (patientex.isPresent()) {
			logger.error("Patient already exists");
			return ResponseEntity.ok(new Message("Patient already exists"));
		} else {
			Patient patient = new Patient(patientDTO.getFirstName(),
					patientDTO.getLastName(), patientDTO.getEmail());
			patientRepo.save(patient);
			logger.info("patient saved successfully");
			return ResponseEntity.ok(new Message("Patient saved"));
		}

	}

	public ResponseEntity<Message> updatePatientService(PatientDTO patientDTO) {
		Optional<Patient> patientOps = patientRepo.findById(patientDTO.getPatientId());
		if (patientOps.isPresent()) {
			Patient patient = new Patient(patientDTO.getFirstName(),
					patientDTO.getLastName(), patientDTO.getEmail());
			patientRepo.save(patient);
			logger.info("patient details updated successfully");
			return ResponseEntity.ok(new Message("Patient updated"));
		} else {
			logger.error("Patient Does not exists");
			return ResponseEntity.ok(new Message("Patient does not exists"));
		}

	}

	public ResponseEntity<List<Patient>> findPatientByFirstNameService(String firstName)
			throws PatientNotFoundException {
		List<Patient> patientList = patientRepo.findAllByFirstName(firstName);
		if (patientList.isEmpty()) {
			logger.error("Exception : Patient Not Found Exception thrown");
			throw new PatientNotFoundException();
		} else {
			logger.info("patient list by first name returned successfully");
			return ResponseEntity.ok(patientList);
		}
	}
}
