package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DoctorDTOTest {

	@Test
	void testDoctorDTO() {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setFirstName("suraj");
		doctorDTO.setLastName("mogali");
		doctorDTO.setEmail("surajmogali@gmail.com");
		doctorDTO.setDoctorID(101);

		assertEquals("suraj", doctorDTO.getFirstName());
		assertEquals("mogali", doctorDTO.getLastName());
		assertEquals("surajmogali@gmail.com", doctorDTO.getEmail());
		assertEquals(101, doctorDTO.getDoctorID());

	}
}