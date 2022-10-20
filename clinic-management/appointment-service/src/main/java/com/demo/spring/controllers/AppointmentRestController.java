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

import com.demo.spring.dto.AppointmentDTO;
import com.demo.spring.entity.Appointment;
import com.demo.spring.exceptions.AppointmentNotFoundException;
import com.demo.spring.repositories.AppointmentRepository;
import com.demo.spring.service.AppointmentService;
import com.demo.spring.util.Message;

import io.swagger.v3.oas.annotations.OpenAPI30;

@RestController
@RequestMapping("/appointment")
@OpenAPI30
public class AppointmentRestController {
	@Autowired
	AppointmentRepository appointmentRepo;
	@Autowired
	AppointmentService appointmentService;
	/*
	this will list all the appointments
	*/
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> findAllAppointments() {
		return appointmentService.findAllAppointmentsService();
	}
	/*
	this will list all the appointments based on doctorId and date
	*/
	@GetMapping(path = "/list/{doctorID}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> findAppointmentsByDate(@PathVariable("doctorID") int doctorID,
			@PathVariable("date") String date) throws AppointmentNotFoundException {
		return appointmentService.findAppointmentsByDateService(doctorID, date);
	}
	/*
	this will create new appointment (takes input in JSON)
	*/
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
	return appointmentService.createAppointmentService(appointmentDTO);
	}
}
