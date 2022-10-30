package com.demo.spring.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.ServerConfiguration;
import com.demo.spring.dto.AppointmentDTO;
import com.demo.spring.dto.DoctorDTO;
import com.demo.spring.dto.PatientDTO;
import com.demo.spring.entity.Appointment;
import com.demo.spring.exceptions.AppointmentNotFoundException;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.exceptions.PatientNotFoundException;
import com.demo.spring.repositories.AppointmentRepository;
import com.demo.spring.service.AppointmentService;
import com.demo.spring.util.Message;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.OpenAPI30;

@RestController
@EnableConfigurationProperties(ServerConfiguration.class)
@RequestMapping("/appointment")
@OpenAPI30
public class AppointmentRestController {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	@Autowired
	AppointmentRepository appointmentRepo;
	@Autowired
	AppointmentService appointmentService;

	@Autowired
	ServerConfiguration server;

	@Autowired
	RestTemplate restTemplate;
/**
 * this method will list all appointments
 * @return
 */
	
	@Timed(value = "requests.find.appointments")
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> findAllAppointments() {
		logger.info("this method had a call to list Appointment service");
		return appointmentService.findAllAppointmentsService();
	}

	/**
	 * this will list all the appointments based on doctorId and date
	 * @param doctorID
	 * @param date
	 * @return
	 * @throws AppointmentNotFoundException
	 */
	@Timed(value = "requests.find.appointments.date")
	@GetMapping(path = "/listByDate/{doctorID}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> findAppointmentsByDate(@PathVariable("doctorID") int doctorID,
			@PathVariable("date") String date) throws AppointmentNotFoundException {
		logger.info("this method had a call to find Appointment by date service");
		return appointmentService.findAppointmentsByDateService(doctorID, date);
	}

	/**
	 * this will create new appointment
	 * @param appointmentDTO
	 * @return
	 * @throws PatientNotFoundException
	 * @throws DoctorNotFoundException
	 */
	@Timed(value = "requests.add.appointments")
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> addAppointment(@RequestBody AppointmentDTO appointmentDTO)
			throws PatientNotFoundException, DoctorNotFoundException {
		logger.info("this method had a call to add new Appointment service");
		PatientDTO patientDTO = restTemplate
				.getForEntity(server.getPatientServer() + "/patient/" + appointmentDTO.getPatientID(), PatientDTO.class)
				.getBody();

		if (patientDTO!= null && patientDTO.getPatientId() != null && patientDTO.getPatientId().intValue()==(appointmentDTO.getPatientID().intValue())) {

			DoctorDTO doctorDTO = restTemplate
					.getForEntity(server.getClinicServer() + "/clinic/doctor/" + appointmentDTO.getDoctorID(),
							DoctorDTO.class)
					.getBody();

			if (doctorDTO!=null && doctorDTO.getDoctorID() != null && (doctorDTO.getDoctorID().equals(appointmentDTO.getDoctorID()))) {
				return appointmentService.createAppointmentService(appointmentDTO);
			} else {
				logger.error("Exception : Doctor Not Found Exception thrown");
				throw new DoctorNotFoundException();
			}

		} else {
			logger.error("Exception : Patient Not Found Exception thrown");
			throw new PatientNotFoundException();
		}

	}
}
