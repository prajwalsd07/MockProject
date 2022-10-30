package com.demo.spring;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.spring.entity.Patient;
import com.demo.spring.repositories.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PatientRestControllerTest {
	@LocalServerPort
	int port;

	@Autowired
	MockMvc mvc;

	@MockBean
	PatientRepository patientRepo;

	@Test
	void testfindPatientSuccess() throws Exception {

		Patient patient = new Patient(200, "bindu", "sp", "bindusp@gmail.com");
		when(patientRepo.findById(200)).thenReturn(Optional.of(patient));
		mvc.perform(get("/patient/200")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.email").value("bindusp@gmail.com"));

	}

	@Test
	void testfindPatientFailure() throws Exception {

		when(patientRepo.findById(100)).thenReturn(Optional.empty());
		mvc.perform(get("/patient/100")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("No Patient Found"));

	}

	@Test
	void testSavePatientSuccess() throws Exception {

		Patient patient = new Patient(300, "bindu", "sp", "bindusp@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patient);
		when(patientRepo.findByEmail("bindusp@gmail.com")).thenReturn(Optional.empty());
		mvc.perform(post("/patient/save").content(patientJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Patient saved"));
	}

	@Test
	void testSavePatientFailure() throws Exception {
		Patient patient = new Patient(200, "bindu", "sp", "bindusp@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patient);
		when(patientRepo.findByEmail("bindusp@gmail.com")).thenReturn(Optional.of(patient));
		mvc.perform(post("/patient/save").content(patientJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Patient already exists"));
	}

	@Test
	void testUpdatePatientSuccess() throws Exception {
		Patient patient = new Patient(200, "bindu", "sp", "bindusp@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patient);
		when(patientRepo.findById(200)).thenReturn(Optional.of(patient));
		mvc.perform(patch("/patient/update").content(patientJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Patient updated"));
	}

	@Test
	void testUpdatePatientFailure() throws Exception {
		Patient patient = new Patient(200, "bindu", "sp", "bindusp@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patient);
		when(patientRepo.findById(200)).thenReturn(Optional.empty());
		mvc.perform(patch("/patient/update").content(patientJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Patient does not exists"));
	}

	@Test
	void testfindPatientByFirstNameSuccess() throws Exception {
		List<Patient> list = new ArrayList<>();
		list.add(new Patient(200, "bindu", "sp", "bindusp@gmail.com"));
		when(patientRepo.findAllByFirstName("bindu")).thenReturn(list);
		mvc.perform(get("/patient/list/bindu")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(content().json(
						"[ {'patientId': 200,'firstName': 'bindu','lastName': 'sp', 'email': 'bindusp@gmail.com' }]"));
	}

	@Test
	void testlistByUserIdFailure() throws Exception {
		List<Patient> list = new ArrayList<>();
		when(patientRepo.findAllByFirstName("bindu")).thenReturn(list);
		mvc.perform(get("/patient/list/bindu")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("No Patient Found"));

	}

}
