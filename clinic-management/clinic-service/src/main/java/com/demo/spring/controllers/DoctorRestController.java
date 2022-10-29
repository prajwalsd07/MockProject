package com.demo.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dto.DoctorDTO;
import com.demo.spring.entity.Doctor;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.service.DoctorService;
import com.demo.spring.util.Message;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/clinic")
public class DoctorRestController {

	@Autowired
	DoctorService doctorService;

	/*
	 * this will list all the doctors data
	 */
	@Timed(value = "requests.list.all.doctors")
	@GetMapping(path = "/doctor/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Doctor>> listAllDoctor() throws DoctorNotFoundException {
		List<Doctor> doctorList = doctorService.listAllDoctorService();
		if (doctorList.isEmpty()) {
			throw new DoctorNotFoundException();
		} else {
			return ResponseEntity.ok(doctorList);
		}
	}
	@Timed(value = "requests.find.doctor")
	@GetMapping(path = "/doctor/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Doctor> findDoctor(@PathVariable("doctorId") int doctorId) throws DoctorNotFoundException {
		return doctorService.findDoctorService(doctorId);
	}
	

	
}
