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
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.spring.entity.Credentials;
import com.demo.spring.entity.Patient;
import com.demo.spring.repository.CredentialsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CredentialsControllerTest {

	@LocalServerPort
	int port;

	@Autowired
	MockMvc mvc;

	@Autowired
	CredentialsRepository credentialsRepository;
	
	@Test
    void testFinduserSuccess() throws Exception{
		List<Credentials> credentialList = new ArrayList<>();
		Credentials credentials = new Credentials("admin","admin");
		ObjectMapper mapper = new ObjectMapper();
		String credentialJson = mapper.writeValueAsString(credentials);
		credentialList.add(credentials);
		when(credentialsRepository.findUser("admin",
				"admin")).thenReturn(credentialList);
		mvc.perform(post("/login/find").content(credentialJson).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.status").value("User Found"));
        
    }
	@Test
    void testFinduserFailure() throws Exception{
		List<Credentials> credentialList = new ArrayList<>();
		Credentials credentials = new Credentials("admin","admin");
		ObjectMapper mapper = new ObjectMapper();
		String credentialJson = mapper.writeValueAsString(credentials);
		when(credentialsRepository.findUser("admin",
				"admin")).thenReturn(credentialList);
		mvc.perform(post("/login/find").content(credentialJson).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.status").value("User Not Found"));
        
    }
	
	@Test
	void testaddUserFailure() throws Exception {
		Credentials credentials = new Credentials("admin","admin");
		ObjectMapper mapper = new ObjectMapper();
		String credentialJson = mapper.writeValueAsString(credentials);
		when(credentialsRepository.existsById("admin")).thenReturn(true);
		mvc.perform(post("/patient/save").content(credentialJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("User Name already used"));
	}
	
	@Test
	void testaddUserSuccess() throws Exception {
		Credentials credentials = new Credentials("admin","admin");
		ObjectMapper mapper = new ObjectMapper();
		String credentialJson = mapper.writeValueAsString(credentials);
		when(credentialsRepository.existsById("admin")).thenReturn(false);
		mvc.perform(post("/patient/save").content(credentialJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("User Added"));
	}
	
	
	
	
	
	
}