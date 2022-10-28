package com.demo.spring;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.spring.entity.Appointment;
import com.demo.spring.repositories.AppointmentRepository;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

class AppointmentRestControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	AppointmentRepository appointmentRepo;

	@Test
	void testfindAllAppointmentsSuccess() throws Exception {
		List<Appointment> list = new ArrayList<>();
		list.add(new Appointment(6, 106, 206, "2022-10-17"));
		when(appointmentRepo.findAll()).thenReturn(list);
		mvc.perform(get("/appointment/list")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(content()
						.json("[ {'appointmentID': 6,'doctorID': 106,'patientID': 206,'date': '2022-10-17'}]"));
	}

	@Test
	void testfindAppointmentsByDateSuccess() throws Exception {
		List<Appointment> list = new ArrayList<>();
		list.add(new Appointment(6, 106, 206, "2022-10-17"));
		when(appointmentRepo.findAllByDate(106, "2022-10-17")).thenReturn(list);
		mvc.perform(get("/appointment/listByDate/106/2022-10-17")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(content()
						.json("[ {'appointmentID': 6,'doctorID': 106,'patientID': 206,'date': '2022-10-17'}]"));

	}

	@Test
	void testfindAppointmentsByDateFailure() throws Exception {
		List<Appointment> list = new ArrayList<>();

		when(appointmentRepo.findAllByDate(106, "2022-10-17")).thenReturn(list);

		mvc.perform(get("/appointment/listByDate/106/2022-10-17")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("There are no Appointments"));

	}
	
	

}
