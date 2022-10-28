package com.demo.spring.controllers;

import java.util.List;

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
import com.demo.spring.service.PatientService;
import com.demo.spring.util.Message;

import io.swagger.v3.oas.annotations.OpenAPI30;

@RestController
@RequestMapping("/patient")
@OpenAPI30
public class PatientRestController {
	@Autowired
	PatientService patientService;

	@Autowired
	PatientRepository patientRepo;

	/*
		this will take patientId as input and returns Patient Details
		*/
	@GetMapping(path = "/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> findPatient(@PathVariable("patientId") int patientId)
			throws PatientNotFoundException {
		return patientService.findPatientService(patientId);
	}
	/*
	this will add new patient to patient Table (JSON as input)
	*/
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> savePatient(@RequestBody PatientDTO patientDTO) {
		return patientService.savePatientService(patientDTO);
	}
	/*
	this is used to update patient details.
	this will take patient details in JSON as input and checks patient exists and returns Patient updated
	*/
	@PatchMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updatePatient(@RequestBody PatientDTO patientDTO) {
		return patientService.updatePatientService(patientDTO);

	}
	/*
	this will list all patient details based on first name
	*/
	@GetMapping(path = "/list/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Patient>> findPatientByFirstName(@PathVariable("firstName") String firstName)
			throws PatientNotFoundException {
		return patientService.findPatientByFirstNameService(firstName);
	}

}
