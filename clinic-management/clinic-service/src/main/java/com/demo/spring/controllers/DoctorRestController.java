package com.demo.spring.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Doctor;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.service.DoctorService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/clinic")
public class DoctorRestController {
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	DoctorService doctorService;

	/**
	 * this will list all the doctors data
	 * @return
	 * @throws DoctorNotFoundException
	 */
	@Timed(value = "requests.list.all.doctors")
	@GetMapping(path = "/doctor/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Doctor>> listAllDoctor() throws DoctorNotFoundException {
		List<Doctor> doctorList = doctorService.listAllDoctorService();
		if (doctorList.isEmpty()) {
			logger.error("Exception : Doctor Not Found Exception thrown");
			throw new DoctorNotFoundException();
		} else {
			logger.info("this method had a call to list all doctor service");
			return ResponseEntity.ok(doctorList);
		}
	}
	
	/**
	 * this method will find doctor by Id
	 * @param doctorId
	 * @return
	 * @throws DoctorNotFoundException
	 */
	@Timed(value = "requests.find.doctor")
	@GetMapping(path = "/doctor/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Doctor> findDoctor(@PathVariable("doctorId") int doctorId) throws DoctorNotFoundException {
		logger.info("this method had a call to find doctor service");
		return doctorService.findDoctorService(doctorId);
	}
	

	
}
