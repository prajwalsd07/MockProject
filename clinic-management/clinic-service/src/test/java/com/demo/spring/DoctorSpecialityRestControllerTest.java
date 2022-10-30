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

import com.demo.spring.entity.Doctor;
import com.demo.spring.entity.DoctorSpeciality;
import com.demo.spring.repositories.DoctorRepository;
import com.demo.spring.repositories.DoctorSpecialityRepository;
import com.demo.spring.repositories.SpecialityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DoctorSpecialityRestControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	DoctorSpecialityRepository doctorSpecialityRepository;

	@MockBean
	DoctorRepository doctorRepository;
	
	@MockBean
	SpecialityRepository specialityRepository;

	@Test
	void testAddDoctorToSpecialitySuccess() throws Exception {
		DoctorSpeciality doctorSpeciality = new DoctorSpeciality(1, 101, 5);
		ObjectMapper mapper = new ObjectMapper();
		String doctorJson = mapper.writeValueAsString(doctorSpeciality);
		when(doctorRepository.existsById(101)).thenReturn(true);
		when(specialityRepository.existsById(5)).thenReturn(true);

		mvc.perform(
				post("/clinic/speciality/addDoctor").content(doctorJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Doctor added to speciality"));
	}

	@Test
	void testAddDoctorTOSpecialityFailure1() throws Exception {
		DoctorSpeciality doctorSpeciality = new DoctorSpeciality(105, 5);
		ObjectMapper mapper = new ObjectMapper();
		String doctorSpecialityJson = mapper.writeValueAsString(doctorSpeciality);
		when(doctorRepository.existsById(doctorSpeciality.getDoctorID())).thenReturn(false);

		mvc.perform(post("/clinic/speciality/addDoctor").content(doctorSpecialityJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Doctor Not Found"));
	}
	
	@Test
	void testAddDoctorTOSpecialityFailure2() throws Exception {
		DoctorSpeciality doctorSpeciality = new DoctorSpeciality(105, 5);
		ObjectMapper mapper = new ObjectMapper();
		String doctorSpecialityJson = mapper.writeValueAsString(doctorSpeciality);
		when(doctorRepository.existsById(doctorSpeciality.getDoctorID())).thenReturn(true);
		when(specialityRepository.existsById(doctorSpeciality.getSpecialityID())).thenReturn(false);

		mvc.perform(post("/clinic/speciality/addDoctor").content(doctorSpecialityJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Speciality Not Found"));
	}

	@Test
	void testRemoveDoctorSuccess() throws Exception {
		DoctorSpeciality doctorSpeciality = new DoctorSpeciality(105, 5);
		List<DoctorSpeciality> doctorSpecialityList = new ArrayList<>();
		doctorSpecialityList.add(doctorSpeciality);
		when(doctorSpecialityRepository.findByDoctorIdAndSpecialityId(105, 5)).thenReturn(doctorSpecialityList);

		mvc.perform(delete("/clinic/speciality/removeDoctor/105/5")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Doctor Removed from Speciality"));
	}

	@Test
	void testRemoveDoctorfailure() throws Exception {
		when(doctorSpecialityRepository.findById(101)).thenReturn(Optional.empty());

		mvc.perform(delete("/clinic/speciality/removeDoctor/101/4")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Doctor does not have Speciality"));
	}

	@Test
	void testlistDoctorinSpecialitySuccess() throws Exception {
		DoctorSpeciality doctorSpeciality = new DoctorSpeciality(105, 5);
		Doctor doctor = new Doctor(105, "bindu", "sp", "bindusp@gmail.com");
		List<Doctor> doctorList = new ArrayList<>();
		doctorList.add(doctor);
		List<DoctorSpeciality> list = new ArrayList<>();
		list.add(doctorSpeciality);
		ObjectMapper mapper = new ObjectMapper();
		String doctorJson = mapper.writeValueAsString(doctorList);
		when(doctorSpecialityRepository.listDoctorInSpeciality(5)).thenReturn(list);
		when(doctorRepository.findById(105)).thenReturn(Optional.of(doctor));

		mvc.perform(get("/clinic/speciality/list/5")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(doctorJson));
	}

	@Test
	void testlistDoctorinSpecialityFailure() throws Exception {
		List<DoctorSpeciality> list = new ArrayList<>();
		when(doctorSpecialityRepository.listDoctorInSpeciality(5)).thenReturn(list);
		mvc.perform(get("/clinic/speciality/list/5")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Speciality Not Found"));
	}
	
	@Test
	void testlistDoctorinSpecialityFailureTwo() throws Exception {
		DoctorSpeciality doctorSpeciality = new DoctorSpeciality(105, 5);
		List<DoctorSpeciality> list = new ArrayList<>();
		list.add(doctorSpeciality);
		when(doctorSpecialityRepository.listDoctorInSpeciality(5)).thenReturn(list);
		when(doctorRepository.findById(105)).thenReturn(Optional.empty());
		mvc.perform(get("/clinic/speciality/list/5")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Doctor Not Found"));
	}

}
