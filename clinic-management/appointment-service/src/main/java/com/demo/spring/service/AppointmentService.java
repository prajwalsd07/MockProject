package com.demo.spring.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	@Autowired
	AppointmentRepository appointmentRepo;
 
	public ResponseEntity<List<Appointment>> findAllAppointmentsService() {
		logger.info("appointment list succesfully returned");
		return ResponseEntity.ok(appointmentRepo.findAll());
	}

	public ResponseEntity<List<Appointment>> findAppointmentsByDateService(int doctorID, String date) throws AppointmentNotFoundException {
		List<Appointment> listAppointment = appointmentRepo.findAllByDate(doctorID, date);
		if (listAppointment.isEmpty()) {
			logger.error("Exception : Appointment Not Found Exception thrown");
			throw new AppointmentNotFoundException();
		} else {
			logger.info("appointment list by date succesfully returned");
			return ResponseEntity.ok(listAppointment);
		}
}
	
	
	public ResponseEntity<Message> createAppointmentService(AppointmentDTO appointmentDTO) { 
		
			Appointment appointment = new Appointment(appointmentDTO.getDoctorID(),
					appointmentDTO.getPatientID(), appointmentDTO.getDate());
			appointmentRepo.save(appointment);
			logger.info("appointment created succesfully");
			return ResponseEntity.ok(new Message("Appointment saved"));
		

	}
}
