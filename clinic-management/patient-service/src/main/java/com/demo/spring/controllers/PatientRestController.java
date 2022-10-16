package com.demo.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dto.PatientDTO;
import com.demo.spring.entity.Patient;
import com.demo.spring.exceptions.PatientNotFoundException;
import com.demo.spring.repositories.PatientRepository;
import com.demo.spring.util.Message;

import io.swagger.v3.oas.annotations.OpenAPI30;

@RestController
@RequestMapping("/patient")
@OpenAPI30
public class PatientRestController {

	@Autowired
	PatientRepository patientRepo;

	@GetMapping(path = "/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> findPatient(@PathVariable("patientId") int patientId)
			throws PatientNotFoundException {
		Optional<Patient> patientOp = patientRepo.findById(patientId);
		if (patientOp.isPresent()) {
			return ResponseEntity.ok(patientOp.get());
		} else {
			throw new PatientNotFoundException();
		}

	}

	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> savePatient(@RequestBody PatientDTO patientDTO) {
		if (patientRepo.existsById(patientDTO.getPatientId())) {
			return ResponseEntity.ok(new Message("Patient already exists"));
		} else {
			Patient patient = new Patient(patientDTO.getPatientId(), patientDTO.getFirstName(),
					patientDTO.getLastName(), patientDTO.getEmail());
			patientRepo.save(patient);
			return ResponseEntity.ok(new Message("Patient saved"));
		}

	}

	@PatchMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updatePatient(@RequestBody PatientDTO patientDTO) {
		if (patientRepo.existsById(patientDTO.getPatientId())) {
			Patient patient = new Patient(patientDTO.getPatientId(), patientDTO.getFirstName(),
					patientDTO.getLastName(), patientDTO.getEmail());
			patientRepo.save(patient);
			return ResponseEntity.ok(new Message("Patient updated"));
		} else {
			return ResponseEntity.ok(new Message("Patient does not exists"));
		}

	}

	@GetMapping(path = "/list/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Patient>> findPatientByFirstName(@PathVariable("firstName") String firstName)
			throws PatientNotFoundException {
		List<Patient> patientList = patientRepo.findAllByFirstName(firstName);
		if (patientList.isEmpty()) {
			throw new PatientNotFoundException();
		} else {
			return ResponseEntity.ok(patientList);
		}
	}

}
