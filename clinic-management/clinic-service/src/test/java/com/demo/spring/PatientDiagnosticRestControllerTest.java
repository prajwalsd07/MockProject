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

import com.demo.spring.dto.PatientDTO;
import com.demo.spring.dto.PatientDiagnosticDTO;
import com.demo.spring.repositories.DiagnosticRepository;
import com.demo.spring.util.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"patientServer=http://localhost:${wiremock.server.port}" })

@AutoConfigureWireMock(port = 0)
class PatientDiagnosticRestControllerTest {

	@LocalServerPort
	int port;

	@Autowired
	TestRestTemplate testRestTemplate;

	@Autowired
	DiagnosticRepository diagnosticRepository;

	@Test
	void testaddTestToPatientSuccess() throws Exception {

		PatientDiagnosticDTO patientDiagnostic = new PatientDiagnosticDTO(400, 4, 220);
		PatientDTO patientDTO = new PatientDTO(220, "jeevan", "rh", "jrh@gmail.com");

		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patientDTO);

		Message message = new Message("Test added successfully");
		String messageJson = mapper.writeValueAsString(message);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<PatientDiagnosticDTO> req = new HttpEntity<>(patientDiagnostic, headers);

		stubFor(get(urlEqualTo("/patient/220"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(patientJson)));

		ResponseEntity<String> response = testRestTemplate.exchange(
				"http://localhost:" + port + "/clinic/patientDiagnostic/save/4/220", HttpMethod.POST, req,
				String.class);

		Assertions.assertEquals(messageJson, response.getBody());
	}

	@Test
	void testaddTestToPatientFailure() throws Exception {

		PatientDiagnosticDTO patientDiagnostic = new PatientDiagnosticDTO(400, 4, 220);
		PatientDTO patientDTO = new PatientDTO(220, "jeevan", "rh", "jrh@gmail.com");

		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patientDTO);

		Message message = new Message("No Patient Found");
		String messageJson = mapper.writeValueAsString(message);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<PatientDiagnosticDTO> req = new HttpEntity<>(patientDiagnostic, headers);

		stubFor(get(urlEqualTo("/patient/221"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(patientJson)));

		ResponseEntity<String> response = testRestTemplate.exchange(
				"http://localhost:" + port + "/clinic/patientDiagnostic/save/5/221", HttpMethod.POST, req,
				String.class);

		Assertions.assertEquals(messageJson, response.getBody());
	}
}
