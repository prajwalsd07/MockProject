package com.demo.spring.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	PatientService patientService;

	@Autowired
	PatientRepository patientRepo;
	/**
	 * this method will find patient with patientid
	 * @param patientId
	 * @return
	 * @throws PatientNotFoundException
	 */
	@GetMapping(path = "/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> findPatient(@PathVariable("patientId") int patientId)
			throws PatientNotFoundException {
		logger.info("this method had a call to find patient service");
		return patientService.findPatientService(patientId);
	}
	/**
	 * this will add new patient to patient Table
	 * @param patientDTO
	 * @return
	 */
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> savePatient(@RequestBody PatientDTO patientDTO) {
		logger.info("this method had a call to save patient service");
		return patientService.savePatientService(patientDTO);
	}
	/**
	 * this method will update patient details.
	 * @param patientDTO
	 * @return
	 */
	@PatchMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updatePatient(@RequestBody PatientDTO patientDTO) {
		logger.info("this method had a call update patient service");
		return patientService.updatePatientService(patientDTO);

	}
	/**
	 * this will list all patient details based on first name
	 * @param firstName
	 * @return
	 * @throws PatientNotFoundException
	 */
	@GetMapping(path = "/list/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Patient>> findPatientByFirstName(@PathVariable("firstName") String firstName)
			throws PatientNotFoundException {
		logger.info("this method had a call to find by first Name patient service");
		return patientService.findPatientByFirstNameService(firstName);
	}

}
