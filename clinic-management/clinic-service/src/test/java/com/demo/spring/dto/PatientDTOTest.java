package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PatientDTOTest {

	@Test
	void testPatientDTO() {
		PatientDTO patientDTO = new PatientDTO();
		PatientDTO patient1 = new PatientDTO(1,"j","w","h");
		patientDTO.setFirstName("john");
		patientDTO.setLastName("walker");
		patientDTO.setEmail("johnwalker@gmail.com");
		patientDTO.setPatientId(100);

		assertEquals("john", patientDTO.getFirstName());
		assertEquals("walker", patientDTO.getLastName());
		assertEquals("johnwalker@gmail.com", patientDTO.getEmail());
		assertEquals(100, patientDTO.getPatientId());

	}

}