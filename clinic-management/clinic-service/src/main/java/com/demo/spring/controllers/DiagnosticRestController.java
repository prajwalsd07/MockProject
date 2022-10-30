package com.demo.spring.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dto.DiagnosticDTO;
import com.demo.spring.entity.Diagnostic;
import com.demo.spring.exceptions.DiagnosticNotFoundException;
import com.demo.spring.exceptions.DiagnosticTestExistsException;
import com.demo.spring.service.DiagnosticService;
import com.demo.spring.util.Message;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/clinic")
public class DiagnosticRestController {
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	DiagnosticService diagnosticService;
	
	/**
	 * this will add new test to the diagnostic table
	 * @param DiagnosticDTO
	 * @return
	 * @throws DiagnosticTestExistsException
	 */
	@Timed(value = "requests.add.test")
	@PostMapping(path="/diagnostic/addTest" ,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> addTest(@RequestBody DiagnosticDTO DiagnosticDTO) throws DiagnosticTestExistsException{
		logger.info("this method had a call to add diagnostic service");
		return ResponseEntity.ok(diagnosticService.addTestService(DiagnosticDTO));
	}
	
	/**
	 * this will delete test from diagnostic table based on diagnostic-Id
	 * @param id
	 * @return
	 * @throws DiagnosticNotFoundException
	 */
	@Timed(value = "requests.delete.test")
	@DeleteMapping(path="/diagnostic/deleteTest/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> deleteTest(@PathVariable("id") Integer id) throws DiagnosticNotFoundException{
		logger.info("this method had a call to delete diagnostic service");
		return ResponseEntity.ok(diagnosticService.deleteTestService(id));
	}
	/*
	 * this will list all the diagnostic tests
	 */
	@Timed(value = "requests.list.all.diagnostic")
	@GetMapping(path="/diagnostic/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Diagnostic>> listAllDiagnostic() {
		logger.info("this method had a call to list all diagnostic service");
			return ResponseEntity.ok(diagnosticService.listAllDiagnosticService());
	}
}
