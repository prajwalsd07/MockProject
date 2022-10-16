package com.demo.spring.controllers;

import java.sql.Date;
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
import com.demo.spring.util.Message;

import io.swagger.v3.oas.annotations.OpenAPI30;

@RestController
@RequestMapping("/appointment")
@OpenAPI30
public class AppointmentRestController {
	@Autowired
	AppointmentRepository appointmentRepo;

	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> findAllAppointments() {
		return ResponseEntity.ok(appointmentRepo.findAll());
	}

	@GetMapping(path = "/list/{doctorID}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> findAppointmentsByDate(@PathVariable("doctorID") int doctorID,
			@PathVariable("date") Date date) throws AppointmentNotFoundException {
		List<Appointment> listAppointment = appointmentRepo.findAllByDate(doctorID, date);
		if (listAppointment.isEmpty()) {
			throw new AppointmentNotFoundException();
		} else {
			return ResponseEntity.ok(listAppointment);
		}
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> getAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		if (appointmentRepo.existsById(appointmentDTO.getAppointmentID())) {
			return ResponseEntity.ok(new Message("Appointment already exists"));
		} else {
			Appointment appointment = new Appointment(appointmentDTO.getAppointmentID(), appointmentDTO.getDoctorID(),
					appointmentDTO.getPatientID(), appointmentDTO.getDate());
			appointmentRepo.save(appointment);
			return ResponseEntity.ok(new Message("Appointment saved"));
		}

	}
}
