package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

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

	@Autowired
	PatientRepository patientRepo;

	public ResponseEntity<Patient> findPatientService(int patientId) throws PatientNotFoundException {
		Optional<Patient> patientOp = patientRepo.findById(patientId);
		if (patientOp.isPresent()) {
			return ResponseEntity.ok(patientOp.get());
		} else {
			throw new PatientNotFoundException();
		}

	}

	public ResponseEntity<Message> savePatientService(PatientDTO patientDTO) {
		if (patientRepo.existsById(patientDTO.getPatientId())) {
			return ResponseEntity.ok(new Message("Patient already exists"));
		} else {
			Patient patient = new Patient(patientDTO.getPatientId(), patientDTO.getFirstName(),
					patientDTO.getLastName(), patientDTO.getEmail());
			patientRepo.save(patient);
			return ResponseEntity.ok(new Message("Patient saved"));
		}

	}

	public ResponseEntity<Message> updatePatientService(PatientDTO patientDTO) {
		if (patientRepo.existsById(patientDTO.getPatientId())) {
			Patient patient = new Patient(patientDTO.getPatientId(), patientDTO.getFirstName(),
					patientDTO.getLastName(), patientDTO.getEmail());
			patientRepo.save(patient);
			return ResponseEntity.ok(new Message("Patient updated"));
		} else {
			return ResponseEntity.ok(new Message("Patient does not exists"));
		}

	}

	public ResponseEntity<List<Patient>> findPatientByFirstNameService(String firstName)
			throws PatientNotFoundException {
		List<Patient> patientList = patientRepo.findAllByFirstName(firstName);
		if (patientList.isEmpty()) {
			throw new PatientNotFoundException();
		} else {
			return ResponseEntity.ok(patientList);
		}
	}
}
