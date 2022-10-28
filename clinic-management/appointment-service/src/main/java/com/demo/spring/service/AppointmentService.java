package com.demo.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.spring.dto.AppointmentDTO;
import com.demo.spring.entity.Appointment;
import com.demo.spring.exceptions.AppointmentNotFoundException;
import com.demo.spring.repositories.AppointmentRepository;
import com.demo.spring.util.Message;

@Service
public class AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepo;
 
	public ResponseEntity<List<Appointment>> findAllAppointmentsService() {
		return ResponseEntity.ok(appointmentRepo.findAll());
	}

	public ResponseEntity<List<Appointment>> findAppointmentsByDateService(int doctorID, String date) throws AppointmentNotFoundException {
		List<Appointment> listAppointment = appointmentRepo.findAllByDate(doctorID, date);
		if (listAppointment.isEmpty()) {
			throw new AppointmentNotFoundException();
		} else {
			return ResponseEntity.ok(listAppointment);
		}
}
	
	
	public ResponseEntity<Message> createAppointmentService(AppointmentDTO appointmentDTO) { 
		
			Appointment appointment = new Appointment(appointmentDTO.getDoctorID(),
					appointmentDTO.getPatientID(), appointmentDTO.getDate());
			appointmentRepo.save(appointment);
			return ResponseEntity.ok(new Message("Appointment saved"));
		

	}
}
