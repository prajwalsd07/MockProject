package com.demo.spring;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.spring.entity.Diagnostic;
import com.demo.spring.repositories.DiagnosticRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DiagnosticRestControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	DiagnosticRepository diagnosticRepository;

	@Test
	void testListAllDiagnostic() throws Exception {
		Diagnostic diagnostic = new Diagnostic(7, "urography");
		List<Diagnostic> diagnosticList = new ArrayList<>();
		diagnosticList.add(diagnostic);
		ObjectMapper mapper = new ObjectMapper();
		String diagnosticJson = mapper.writeValueAsString(diagnosticList);
		when(diagnosticRepository.findAll()).thenReturn(diagnosticList);

		mvc.perform(get("/clinic/diagnostic/list")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(diagnosticJson));
	}

	@Test
	void testAddTestSuccess() throws Exception {
		Diagnostic diagnostic = new Diagnostic(7, "urography");
		ObjectMapper mapper = new ObjectMapper();
		String diagnosticJson = mapper.writeValueAsString(diagnostic);
		when(diagnosticRepository.existsById(7)).thenReturn(false);

		mvc.perform(post("/clinic/diagnostic/addTest").content(diagnosticJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("New test added"));
	}

	@Test
	void testAddTestFailure() throws Exception {
		Diagnostic diagnostic = new Diagnostic(7, "urography");
		ObjectMapper mapper = new ObjectMapper();
		String diagnosticJson = mapper.writeValueAsString(diagnostic);
		when(diagnosticRepository.existsById(7)).thenReturn(true);

		mvc.perform(post("/clinic/diagnostic/addTest").content(diagnosticJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("DiagnosticTest Already Exists"));
	}

	@Test
	void testdeleteTestSuccess() throws Exception {
		Diagnostic diagnostic = new Diagnostic(7, "urography");
		when(diagnosticRepository.findById(7)).thenReturn(Optional.of(diagnostic));

		mvc.perform(delete("/clinic/diagnostic/deleteTest/7")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Test deleted"));
	}

	@Test
	void testdeleteTestFailure() throws Exception {
		when(diagnosticRepository.findById(7)).thenReturn(Optional.empty());
		mvc.perform(delete("/clinic/diagnostic/deleteTest/7")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Diagnostic Not Found"));
	}

}
