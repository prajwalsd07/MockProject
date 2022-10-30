package com.demo.spring.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.dto.PatientDTO;
import com.demo.spring.exceptions.DiagnosticNotFoundException;
import com.demo.spring.exceptions.PatientNotFoundException;
import com.demo.spring.service.PatientDiagnosticService;
import com.demo.spring.util.Message;
import com.demo.spring.util.ServerConfiguration;

import io.micrometer.core.annotation.Timed;

@EnableConfigurationProperties(ServerConfiguration.class)
@RestController
@RequestMapping("/clinic")
public class PatientDiagnosticRestController {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	@Autowired
	PatientDiagnosticService patientDiagnosticService;

	@Autowired
	ServerConfiguration server;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * this method will add test to patient
	 * @param testId
	 * @param patientId
	 * @return
	 * @throws PatientNotFoundException
	 * @throws DiagnosticNotFoundException
	 * @throws NullPointerException
	 */
	@Timed(value = "requests.patientdiag.save")
	@PostMapping(path = "/patientDiagnostic/save/{testId}/{patientId}")
	public ResponseEntity<Message> addTestToPatient(@PathVariable("testId") int testId,
			@PathVariable("patientId") int patientId)
			throws PatientNotFoundException, DiagnosticNotFoundException, NullPointerException {
		logger.info("this method had a call to add test to patient service");
		PatientDTO patientDTO = restTemplate
				.getForEntity(server.getPatientServer() + "/patient/{patientId}", PatientDTO.class, patientId)
				.getBody();
		if (patientDTO != null && patientDTO.getPatientId() != null && patientDTO.getPatientId() == patientId) {
			return ResponseEntity.ok(patientDiagnosticService.addTestToPatient(patientId, testId));
		} else {
			logger.error("Exception : Patient Not Found Exception thrown");
			throw new PatientNotFoundException();
		}
	}
}