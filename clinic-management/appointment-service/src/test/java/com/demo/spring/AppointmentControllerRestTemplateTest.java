package com.demo.spring;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.demo.spring.dto.AppointmentDTO;
import com.demo.spring.dto.DoctorDTO;
import com.demo.spring.dto.PatientDTO;
import com.demo.spring.util.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"patientServer=http://localhost:${wiremock.server.port}",
		"clinicServer=http://localhost:${wiremock.server.port}" })

@AutoConfigureWireMock(port = 0)
 class AppointmentControllerRestTemplateTest {

	@LocalServerPort
	int port;

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	void testAddAppointmentPatientFailure() throws Exception {

		AppointmentDTO appointment = new AppointmentDTO(21, 1, 2, "2022-10-14");
		DoctorDTO doctorDTO = new DoctorDTO(1, "prajwal", "sd", "psd@gmail.com");
		PatientDTO patientDTO = new PatientDTO(1, "jeevan", "rh", "jrh@gmail.com");

		ObjectMapper mapper = new ObjectMapper();
		String doctorJson = mapper.writeValueAsString(doctorDTO);
		String patientJson = mapper.writeValueAsString(patientDTO);

		Message message = new Message("No Patient Found");
		String messageJson = mapper.writeValueAsString(message); 

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<AppointmentDTO> req = new HttpEntity<>(appointment, headers);

		stubFor(get(urlEqualTo("/clinic/doctor/1"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(doctorJson)));
		stubFor(get(urlEqualTo("/patient/2"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(patientJson)));

		ResponseEntity<String> response = testRestTemplate.exchange("http://localhost:" + port + "/appointment/add",
				HttpMethod.POST, req, String.class);

		Assertions.assertEquals(messageJson, response.getBody());
	}
	
	@Test
	void testAddAppointmentDoctorFailure() throws Exception {

		AppointmentDTO appointment = new AppointmentDTO(21, 2, 1, "2022-10-14");
		DoctorDTO doctorDTO = new DoctorDTO(1, "prajwal", "sd", "psd@gmail.com");
		PatientDTO patientDTO = new PatientDTO(1, "jeevan", "rh", "jrh@gmail.com");

		ObjectMapper mapper = new ObjectMapper();
		String doctorJson = mapper.writeValueAsString(doctorDTO);
		String patientJson = mapper.writeValueAsString(patientDTO);

		Message message = new Message("Doctor Not Found");
		String messageJson = mapper.writeValueAsString(message); 

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<AppointmentDTO> req = new HttpEntity<>(appointment, headers);

		stubFor(get(urlEqualTo("/clinic/doctor/2"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(doctorJson)));
		stubFor(get(urlEqualTo("/patient/1"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(patientJson)));

		ResponseEntity<String> response = testRestTemplate.exchange("http://localhost:" + port + "/appointment/add",
				HttpMethod.POST, req, String.class);

		Assertions.assertEquals(messageJson, response.getBody());
	}

	@Test
	void testAddAppointmentSuccess() throws Exception {

		AppointmentDTO appointment = new AppointmentDTO(20, 3, 3, "2022-10-14");
		DoctorDTO doctorDTO = new DoctorDTO(3, "prajwal", "sd", "psd@gmail.com");
		PatientDTO patientDTO = new PatientDTO(3, "jeevan", "rh", "jrh@gmail.com"); 

		ObjectMapper mapper = new ObjectMapper();
		String doctorJson = mapper.writeValueAsString(doctorDTO);
		String patientJson = mapper.writeValueAsString(patientDTO);

		Message message = new Message("Appointment saved");
		String messageJson = mapper.writeValueAsString(message);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<AppointmentDTO> req = new HttpEntity<>(appointment, headers);

		
		stubFor(get(urlEqualTo("/patient/3"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(patientJson)));
		stubFor(get(urlEqualTo("/clinic/doctor/3"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(doctorJson)));

		ResponseEntity<String> response = testRestTemplate.exchange("http://localhost:" + port + "/appointment/add",
				HttpMethod.POST, req, String.class);

		Assertions.assertEquals(messageJson, response.getBody());
	}
}
